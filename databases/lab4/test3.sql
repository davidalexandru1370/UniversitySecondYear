use DrivingExams21;

--number of instructors for each category
create or alter view viewInstructorDrivingLicenses as
	SELECT ID.CNP, COUNT(ID.CNP) AS NumberOfCategories from InstructorsDrivingLicenses ID
	JOIN Categories C on Id.Category = C.Category
	GROUP BY ID.CNP

create or alter procedure insertInstructorsDrivingLicenses(@numberOfRows int) as 
	begin
		--make sure we have enough data to insert
		declare @numberOfInstructors int = (SELECT COUNT(*) from Instructors);
		declare @numberOfCategories int  = (SELECT COUNT(*) from Categories);

		while @numberOfRows > @numberOfCategories * @numberOfInstructors begin
			INSERT into Instructors(CNP,Name) values(CONCAT('Test',@numberOfInstructors + 1),'Test');
			set @numberOfInstructors = @numberOfInstructors + 1;
		end

		while @numberOfRows > 0 begin
			declare categoriesCursor cursor scroll for
				Select Category From Categories;
			declare @category varchar(100);
			open categoriesCursor
			fetch next from categoriesCursor into @category;
			while @@FETCH_STATUS = 0 and @numberOfRows > 0 begin
				INSERT INTO InstructorsDrivingLicenses(CNP,Category) values (,)
				fetch next from categoriesCursor into @category;
				set @numberOfRows = @numberOfRows - 1;
			end
		end
	end


	