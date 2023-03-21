use DrivingExams21;
SELECT * from Tables;
create or alter view viewVehicles as
	Select V.CarPlate, V.InstructorCNP, I.Name as InstructorName from Vehicles V
	INNER JOIN  Instructors I on I.CNP=V.InstructorCNP


create or alter procedure insertVehicles (@numberOfRows int) as
	declare @FK varchar(100) = (SELECT TOP 1 CNP from Instructors)
	begin 
		while @numberOfRows > 0 begin
			INSERT INTO Vehicles(CarPlate,InstructorCNP) values(CONCAT('TestCarplate',@numberOfRows),@FK);
			set @numberOfRows = @numberOfRows - 1;
		end
	end


create or alter procedure deleteVehicles as
	begin
		Delete from Vehicles where CarPlate like 'Test%';
	end

execute insertInTables 'Vehicles';
execute insertIntoViews 'viewVehicles';
execute insertIntoTests 'insertVehicles';
execute insertIntoTests 'deleteVehicles';
execute insertIntoTestTables 'insertVehicles','Vehicles',1000,2
execute insertIntoTestTables 'deleteVehicles','Vehicles',1000,2

execute runAllTests;