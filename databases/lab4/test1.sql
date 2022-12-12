--a table with a single-column primary key and no foreign keys;
use DrivingExams21;


SELECT * from Tables;

create or alter view viewInstructors as
	SELECT * from Instructors;


create or alter procedure insertInstructors (@numberOfRows int) as 
	begin
		WHILE @numberOfRows > 0 begin
				INSERT INTO Instructors(CNP,Name) values (CONCAT('Test',@numberOfRows),'TestName');
				set @numberOfRows = @numberOfRows - 1;
		end
	end

create or alter procedure deleteInstructors as
	begin
		Delete from Instructors Where CNP like 'Test%';
	end

execute insertInTables 'Instructors';
execute insertIntoViews 'viewInstructors';
execute insertIntoTests 'insertInstructors';
execute insertIntoTests 'deleteInstructors';
execute insertIntoTestTables 'insertInstructors','Instructors',100,1;
execute insertIntoTestTables 'deleteInstructors','Instructors',100,3;

SELECT * FROM TestTables;
SELECT * FROM Instructors;
SELECT * from Tests
SELECT * FROM Views;
execute runAllTests


