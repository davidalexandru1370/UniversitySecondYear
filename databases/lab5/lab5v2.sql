--Work on 3 tables of the form Ta(aid, a2, …), Tb(bid, b2, …), Tc(cid, aid, bid, …), where:

--aid, bid, cid, a2, b2 are integers;
--the primary keys are underlined;
--a2 is UNIQUE in Ta;
--aid and bid are foreign keys in Tc, referencing the primary keys in Ta and Tb, respectively.
use DrivingExams21;

--Vehicles (Ta)
--Practical Exams(Tb),
--Results(Tc),  

--a. Write queries on Ta such that their execution plans contain the following operators:

--clustered index scan;
--clustered index seek;
--nonclustered index scan;
--nonclustered index seek;
--key lookup.
--Create nonclustered index vehiclesCarPlateIndex on Vehicles(CarPlate)
SELECT * from Vehicles;
select id from Vehicles order by CarPlate; -- clustered index scan
select CarPlate from Vehicles where id = 2; --clustered index seek
select id from Vehicles; --non clustered index scan 
Select CarChasis from Vehicles where CarChasis > 5 -- non clustered index seek
Select CarPlate from Vehicles Where CarChasis = 2 -- key lookup


--b. Write a query on table Tb with a WHERE clause of the form WHERE b2 = value and analyze its execution plan.
--Create a nonclustered index that can speed up the query. Examine the execution plan again.

CREATE NONCLUSTERED INDEX practicalExamsScoreIndex on PracticalExams(CandidateScore,CandidateCNP); -- 0.04 ms
drop index practicalExamsScoreIndex on PracticalExams; -- 0.1 ms



declare @start datetime2 = SYSDATETIME();
Select PE.CandidateCNP from PracticalExams PE where CandidateScore >= 22;
declare @stop datetime2 = SYSDATETIME();
print CONCAT(DATEDIFF(MILLISECOND,@start,@stop)/1000,'.',DATEDIFF(MILLISECOND,@start,@stop)%1000,' ms'); 


--c. Create a view that joins at least 2 tables. 
--Check whether existing indexes are helpful;
--if not, reassess existing indexes / examine the cardinality of the tables.

SELECT COUNT(*) from PracticalExams;

SELECT * FROM TheoreticalExams;
SELECT * FROM Vehicles;
SELECT * FROM Results;

declare @rows int = 900
	declare myCursor cursor scroll for
			SELECT TOP 1000 TE.Id,PE.Id, PE.CandidateScore, TE.CandidateCNP,PE.CarId from TheoreticalExams TE
			inner join PracticalExams PE on TE.CandidateCNP = PE.CandidateCNP
		
		declare @practicalExamId int;
		declare @theoreticalExamId int;
		declare @theoreticalExamScore int;
		declare @practicalExamScore int;
		declare @candidateCNP nvarchar(100);
		declare @carId int;
		open myCursor;
		fetch next from myCursor into @theoreticalExamId, @practicalExamId,  @practicalExamScore, @candidateCNP, @carId;
	while @rows > 0 begin
		declare @finalResult bit = 0;
		if @practicalExamScore >= 22 begin
			set @finalResult = 1
		end
		
		INSERT INTO Results(CandidateCNP,CarId,FinalResult,PracticalExamId,TheoreticalExamId) values(@candidateCNP,@carId,@finalResult,@practicalExamId,@theoreticalExamId);
		set @rows = @rows - 1;
		fetch next from myCursor into @theoreticalExamId, @practicalExamId,  @practicalExamScore, @candidateCNP, @carId;
	end
	close myCursor;
	deallocate myCursor;

	SELECT * FROM Results;

create or alter view myView as 
	SELECT TOP 1000 PE.CandidateScore,V.CarChasis from PracticalExams PE
	inner join Results R on R.PracticalExamId = PE.Id
	inner join Vehicles V on R.CarId = V.Id
	where PE.CandidateScore <= 18

declare @start2 datetime2 = SYSDATETIME();
select  * from myView;
declare @stop2 datetime2 = SYSDATETIME();
print CONCAT(DATEDIFF(MILLISECOND,@start2,@stop2)/1000,'.',DATEDIFF(MILLISECOND,@start2,@stop2)%1000,' ms'); 