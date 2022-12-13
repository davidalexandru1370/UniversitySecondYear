--Tests – holds data about different tests;
--Views – holds data about a set of views from the database, used to assess the performance of certain SQL queries;

--Tables – holds data about tables that can take part in tests;

--TestTables – junction table between Tests and Tables (which tables take part in which tests);

--TestViews – junction table between Tests and Views (which views take part in which tests);

--TestRuns – contains data about different test runs;
use DrivingExams21;
exec sp_databases;

create or alter procedure selectView (@view varchar(100)) as
	begin
		if not exists(Select Views.ViewID from Views where Name=@view) begin
			print CONCAT('View with name = ',@view,' does not exists!');
			return;
		end
		
		declare @command varchar(100)= CONCAT('SELECT * from ', @view);
		execute (@command);
	end

create or alter procedure insertInTables (@tableName varchar(50)) as
	begin
		if @tableName in (SELECT Name from Tables) begin
			print CONCAT( @tableName ,' table already present in Tables');
			return
		end
		
		if @tableName not in (SELECT TABLE_NAME from INFORMATION_SCHEMA.TABLES)
		begin
			print CONCAT(@tableName, ' does not exist!');
			return
		end

		Insert into Tables(name) values(@tableName);
	end

--a table with a single-column primary key and no foreign keys;
--a table with a single-column primary key and at least one foreign key;
--a table with a multicolumn primary key,


execute insertInTables Instructors
execute insertInTables Vehicles
execute insertInTables InstructorsDrivingLicenses

SELECT * from INFORMATION_SCHEMA.VIEWS;

create or alter procedure insertIntoViews (@viewName varchar(100)) as
	begin
		if @viewName in (SELECT Name from Views) begin
			print CONCAT(@viewName, ' view already present in Views');
			return;
		end

		if @viewName not in (SELECT TABLE_NAME from INFORMATION_SCHEMA.VIEWS) begin
			print CONCAT(@viewName, ' does not exists!');
			return;
		end
		Insert into Views(Name) values (@viewName);
	end

create or alter procedure insertIntoTests(@testName varchar(100)) as
	begin
		if @testName in (SELECT Name from Tests) begin
			print CONCAT(@testName, ' test already present in Tests');
			return;
		end

		Insert into Tests(Name) values(@testName)
	end


create or alter procedure insertIntoTestTables(@testName varchar(100), @tableName varchar(100), @noOfRows int, @position int) as
	begin 
		if @testName not in (Select Name from Tests) begin
			print CONCAT('Does not exists a test with name = ', @testName);
			return
		end

		if @tableName not in (Select Name from Tables) begin
			print CONCAT('Does not exists a table with name = ', @tableName);
			return
		end

		if @noOfRows < 0 begin
			print CONCAT(@noOfRows, ' can not be negative!');
			return;
		end

		if @position < 0 begin
			print CONCAT(@position, ' can not be negative!');
			return;
		end

		if exists(
			select * from TestTables testTables 
			inner join Tests tests on testTables.TestID=tests.TestID
			WHERE tests.Name = @testName and testTables.Position = @position
		)
		declare @testId int = (Select TestId from Tests where Name = @testName);
		declare @tableId int = (Select TableId from Tables where Name = @tableName);
		Insert into TestTables(TestId, TableID,NoOfRows,Position) values(@testId,@tableId,@noOfRows,@position);


	end


create or alter procedure insertIntoTestViews(@testName varchar(100), @viewName varchar(100)) as
	begin
		if not exists(Select Name from Tests where Name=@testName) begin
			print CONCAT(@testName, ' does not exists!');
			return;
		end
		if not exists(Select Name from Views where Name=@viewName) begin
			print CONCAT(@viewName, 'does not exists!')
			return;
		end;


		Insert Into TestViews(TestID,ViewID) values (
		(Select TestID from Tests where Name=@testName),
		(Select ViewId from Views where Name=@viewName)
		)
	end

create or alter procedure runAllTests as 
	begin

		if (Select COUNT(*) from Tests) = 0 begin
			print 'There are no tests!';
			return;
		end
		
		declare @testStartingTime datetime2;
		declare @individualTestStartingTime datetime2;
		declare @individualTestEndingTime datetime2;
		declare @table varchar(50);
		declare @numberOfRows int;
		declare @position int;
		declare @view varchar(100);
		declare @viewId int;
		--declare @testId int = (Select Tests.Name from Tests where Name=@testName);

		declare @currentRunningTestId int;
		declare @currentRunningTestName varchar(100);

		set @currentRunningTestId = (Select max(TestRunId) + 1 from TestRunTables)
		if @currentRunningTestId is null begin
			set @currentRunningTestId = 1
		end

		declare tablesCursor cursor scroll for
			SELECT T1.Name,T2.NoOfRows,T2.Position,(Select Tests.Name from Tests where Tests.TestID = T2.TestID) as TestName from Tables T1
			inner join TestTables T2 on T1.TableId = T2.TableId
			order by T2.Position

		declare viewsCursor cursor for
			Select V.Name from Views V
		
		
			--delete
			declare @dateStartTesting datetime2 = SYSDATETIME();
			SET IDENTITY_INSERT TestRuns ON
			INSERT INTO TestRuns(TestRunID,Description,StartAt) values(@currentRunningTestId,'Test',@dateStartTesting);
			SET IDENTITY_INSERT TestRuns OFF
			declare @testHasStarted bit = 0;

			open tablesCursor 
			fetch last from tablesCursor into @table, @numberOfRows, @position, @currentRunningTestName
			while @@FETCH_STATUS = 0 begin
				if @currentRunningTestName like 'delete%'  begin
					print CONCAT('Running ', @currentRunningTestName);
					execute @currentRunningTestName
					print CONCAT('Finished running test ', @currentRunningTestName);
				end
				fetch prior from tablesCursor into @table, @numberOfRows, @position, @currentRunningTestName
			end

			close tablesCursor

			open tablesCursor
			--insert
			
			fetch first from tablesCursor into @table, @numberOfRows, @position, @currentRunningTestName
			declare @hasStartedTesting bit = 0;
			while @@FETCH_STATUS = 0 begin
				if @currentRunningTestName like 'insert%' begin
					set @individualTestStartingTime = @individualTestEndingTime;
					if @hasStartedTesting = 0 begin
						set @individualTestStartingTime = @dateStartTesting
					end
					execute @currentRunningTestName @numberOfRows
					set @individualTestEndingTime = SYSDATETIME();
					INSERT INTO TestRunTables(TestRunID,TableID,StartAt,EndAt) values(@currentRunningTestId,(SELECT Tables.TableID from Tables where Tables.Name=@table),@individualTestStartingTime,@individualTestEndingTime);
					set @hasStartedTesting = 1;
				end
					fetch next from tablesCursor into @table, @numberOfRows, @position, @currentRunningTestName; 
			end

			close tablesCursor;
			deallocate tablesCursor;
			
			--view
			open viewsCursor
			
			fetch NEXT from viewsCursor into @view;
			while @@FETCH_STATUS = 0 begin
				set @individualTestStartingTime = @individualTestEndingTime;
				execute	selectView @view;
				set @individualTestEndingTime = SYSDATETIME();
				INSERT INTO TestRunViews(TestRunID,ViewID,StartAt,EndAt) values (@currentRunningTestId,(Select Views.ViewID from Views where Name = @view),@individualTestStartingTime,@individualTestEndingTime);
				fetch next from viewsCursor into @view;
			end

			close viewsCursor;
			deallocate viewsCursor;

			update TestRuns 
			set EndAt=@individualTestEndingTime
			where TestRunID = @currentRunningTestId

	end

	go

SELECT * from TestRunTables;
SELECT * from TestRunViews;
SELECT * from TestRuns;

execute runAllTests;
DELETE FROM TestRunTables;
DELETE FROM  TestRuns;
DELETE FROM TestRunViews;

