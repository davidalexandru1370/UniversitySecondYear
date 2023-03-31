use DrivingExams21;

if OBJECT_ID('Ta','U') IS NULL 
	create table Ta(
		aid int PRIMARY KEY IDENTITY (1,1),
		a2 int unique,
		a3 int
	)

if OBJECT_ID('Tb','U') IS NULL
	create table Tb(
		bid int PRIMARY KEY IDENTITY (1,1),
		b2 int
	)

if OBJECT_ID('Tc','U') IS NULL
	create table Tc(
		cid int PRIMARY KEY IDENTITY (1,1),
		aid int,
		bid int,
		CONSTRAINT FK_Tc_Ta FOREIGN KEY (aid) REFERENCES Ta(aid),
		CONSTRAINT FK_Tc_Tb FOREIGN KEY (bid) REFERENCES Tb(bid) 
	)




create or alter procedure insertIntoTa(@rows int) as 
	begin
		while @rows > 0 begin
			INSERT INTO Ta(a2,a3) values(@rows,@rows % 100)
			set @rows = @rows - 1;
		end
	end


create or alter procedure insertIntoTb(@rows int) as 
	begin
		while @rows > 0 begin
			INSERT INTO Tb(b2) values(@rows*3 + 6);
			set @rows = @rows - 1;
		end
	end

create or alter procedure insertIntoTc as
	begin
			declare AandBTables cursor scroll local for
				SELECT A.aid, b.bid from (SELECT TOP 100 aid from Ta ) A
				CROSS JOIN (SELECT TOP 100 bid from Tb) b

			declare @aid int;
			declare @bid int;
			declare @rows int = 20000;
			open AandBTables
			fetch NEXT from AandBTables into @aid, @bid
			
			while @rows > 0 and @@FETCH_STATUS = 0 begin
				INSERT INTO Tc(aid,bid) values (@aid,@bid);
				fetch NEXT from AandBTables into @aid, @bid
				set @rows = @rows - 1;
			end

			close AandBTables;
			deallocate AandBTables;
	end

execute insertIntoTa 10000
execute insertIntoTb 10000
execute insertIntoTc

DELETE FROM Tc
DELETE FROM Ta
DELETE FROM Tb


--a. Write queries on Ta such that their execution plans contain the following operators:
--clustered index scan;
--clustered index seek;
--nonclustered index scan;
--nonclustered index seek;
--key lookup.

create nonclustered index taIndex on Ta(a3);
drop index taIndex on Ta;
sp_helpindex Ta;
--CTRL + L
select * from Ta order by aid; -- clustered index scan
select * from Ta where aid = 2; -- clustered index seek
select a3 from Ta where a3 > 50 -- non-clustured index seek
select a3 from Ta order by a3 -- non clustured index scan

select a3 from Ta where a2 = 14 -- key lookup


--b. Write a query on table Tb with a WHERE clause of the form WHERE b2 = value and analyze its execution plan. 
--   Create a nonclustered index that can speed up the query. Examine the execution plan again.

declare @start datetime2 = SYSDATETIME();
select  b.b2 from Tb b where b.b2 = 24183 ;
declare @stop datetime2 = SYSDATETIME();
print CONCAT(DATEDIFF(MILLISECOND,@start,@stop)/1000,'.',DATEDIFF(MILLISECOND,@start,@stop)%1000,' ms'); 

create nonclustered index tbIndex on Tb(b2) -- 0.0 ms with index
drop index tbIndex on Tb -- ~ 0.80 ms without index


--c. Create a view that joins at least 2 tables. Check whether existing indexes are helpful;
--if not, reassess existing indexes / examine the cardinality of the tables.


create or alter view tcView as
	SELECT top 1000 tb.b2,ta.a3 from Tc t
		inner join Tb tb on tb.bid = t.bid 
		inner join Ta ta on ta.aid = t.aid
		where tb.b2 > 1000 and ta.a3 < 50

declare @start2 datetime2 = SYSDATETIME();
select * from tcView;
declare @stop2 datetime2 = SYSDATETIME();
print CONCAT(DATEDIFF(MILLISECOND,@start2,@stop2)/1000,'.',DATEDIFF(MILLISECOND,@start2,@stop2)%1000,' ms');