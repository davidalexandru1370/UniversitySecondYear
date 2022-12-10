--Tests – holds data about different tests;
--Views – holds data about a set of views from the database, used to assess the performance of certain SQL queries;

--Tables – holds data about tables that can take part in tests;

--TestTables – junction table between Tests and Tables (which tables take part in which tests);

--TestViews – junction table between Tests and Views (which views take part in which tests);

--TestRuns – contains data about different test runs;
use DrivingExams21;
exec sp_databases;
SELECT * from INFORMATION_SCHEMA.TABLES

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


Select * from Tables
execute insertInTables Instructors
execute insertInTables Vehicles
execute insertInTables InstructorsDrivingLicenses
