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
			INSERT INTO Ta(a2,a3) values(@rows,2*(@rows) + 10)
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

--execute insertIntoTa 10000
--execute insertIntoTb 10000
--execute insertIntoTc


--a. Write queries on Ta such that their execution plans contain the following operators:
--clustered index scan;
--clustered index seek;
--nonclustered index scan;
--nonclustered index seek;
--key lookup.

create nonclustered index taIndex on Ta(a3)

select * from Ta order by aid;
select * from Ta where aid = 2;
