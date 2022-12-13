use DrivingExams21;

--number of instructors for each category
create or alter view viewInstructorDrivingLicenses as
	SELECT ID.CNP, COUNT(ID.CNP) AS NumberOfCategories from InstructorsDrivingLicenses ID
	JOIN Categories C on Id.Category = C.Category
	GROUP BY ID.CNP

create or alter procedure insertInstructorsDrivingLicenses(@numberOfRows int) as 
	begin
		--make sure we have enough data to insert
		declare @numberOfInstructors int = (SELECT COUNT(*) from Instructors WHERE CNP like 'Test%');
		declare @numberOfCategories int  = (SELECT COUNT(*) from Categories);

		while @numberOfRows > (@numberOfCategories * @numberOfInstructors) begin
			INSERT into Instructors(CNP,Name) values(CONCAT('Test',@numberOfInstructors + 1),'Test');
			set @numberOfInstructors = @numberOfInstructors + 1;
		end

		declare InstructorsAndCategoriesCrossJoinCursor cursor scroll for
			SELECT CNP, C.Category from Instructors as I
			CROSS JOIN Categories as C
			Where I.CNP like 'Test%';
		declare @instructorCNP varchar(100);
		declare @category varchar(100);
		open InstructorsAndCategoriesCrossJoinCursor;
		fetch NEXT from InstructorsAndCategoriesCrossJoinCursor into @instructorCNP, @category
		while (@numberOfRows > 0 and @@FETCH_STATUS = 0) begin
			INSERT INTO InstructorsDrivingLicenses(CNP,Category) values(@instructorCNP,@category);
			set @numberOfRows = @numberOfRows - 1;
			fetch next from InstructorsAndCategoriesCrossJoinCursor into @instructorCNP, @category
		end
		close InstructorsAndCategoriesCrossJoinCursor;
		deallocate InstructorsAndCategoriesCrossJoinCursor;
	end


create or alter procedure deleteInstructorDrivingLicenses as
	begin
		DELETE from InstructorsDrivingLicenses where CNP like 'Test%';	
	end
	

SELECT * FROM TABLES;
execute insertInTables 'InstructorsDrivingLicenses';
execute insertIntoViews 'viewInstructorDrivingLicenses';
execute insertIntoTests 'insertInstructorsDrivingLicenses';
execute insertIntoTests 'deleteInstructorDrivingLicenses';
execute insertIntoTestTables 'insertInstructorsDrivingLicenses', 'InstructorsDrivingLicenses',1000,1
execute insertIntoTestTables 'deleteInstructorDrivingLicenses', 'InstructorsDrivingLicenses', 1000,3

execute runAllTests;
