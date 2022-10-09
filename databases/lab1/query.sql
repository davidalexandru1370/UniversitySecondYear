DELETE from DrivingExams.dbo.Instructors;
DBCC CHECKIDENT ('[DrivingExams].[dbo].[Instructors]','RESEED',0); 

Insert INTO DrivingExams.dbo.Instructors (CNP,Name,VehiclePlate) values ('432647823','Teo','SJ71FIL');
Insert INTO DrivingExams.dbo.Instructors (CNP,Name,VehiclePlate) values ('343242342','DavidInstructoru1','SJ71DAV');
Insert INTO DrivingExams.dbo.Instructors (CNP,Name,VehiclePlate) values ('549857343','DavidInstructoru2','SJ72DAV');
Insert INTO DrivingExams.dbo.Instructors (CNP,Name,VehiclePlate) values ('887895123','DavidInstructoru3','SJ73DAV');
Insert INTO DrivingExams.dbo.Instructors (CNP,Name,VehiclePlate) values ('789123206','DavidInstructoru4','SJ74DAV');