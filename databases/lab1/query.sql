use DrivingExams;
go;

DELETE from Instructors;
DELETE from Categories;
DELETE from Students;
DBCC CHECKIDENT ('[DrivingExams].[dbo].[Instructors]','RESEED',0); 

Insert INTO Instructors (CNP,Name,VehiclePlate) values ('432647823','Teo','SJ71FIL');
Insert INTO Instructors (CNP,Name,VehiclePlate) values ('343242342','DavidInstructoru1','SJ71DAV');
Insert INTO Instructors (CNP,Name,VehiclePlate) values ('549857343','DavidInstructoru2','SJ72DAV');
Insert INTO Instructors (CNP,Name,VehiclePlate) values ('887895123','DavidInstructoru3','SJ73DAV');
Insert INTO Instructors (CNP,Name,VehiclePlate) values ('789123206','DavidInstructoru4','SJ74DAV');

Insert Into Categories (Category,MandatoryLessons) values('A',10);
Insert Into Categories (Category,MandatoryLessons) values('B',20);
Insert Into Categories (Category,MandatoryLessons) values('B+E',20);
Insert Into Categories (Category,MandatoryLessons) values('C',20);
Insert Into Categories (Category,MandatoryLessons) values('C+E',20);
Insert Into Categories (Category,MandatoryLessons) values('D',20);
Insert Into Categories (Category,MandatoryLessons) values('D+E',20);

Insert into Students (CNP,CurrentLesson,InstructorId,Name,StartingDate)
values(5543543534,2,1,'David','10.9.2022',1)

Insert into Students (CNP,CurrentLesson,InstructorId,Name,StartingDate)
values(4328749832,2,1,'David','10.9.2022',2)


