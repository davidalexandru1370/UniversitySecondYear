/*
insert data – for at least 4 tables; at least one statement must violate referential integrity constraints;
update data – for at least 3 tables;
delete data – for at least 2 tables;

a. 2 queries with the union operation; use UNION [ALL] and OR;
b. 2 queries with the intersection operation; use INTERSECT and IN;
c. 2 queries with the difference operation; use EXCEPT and NOT IN;
*/

use DrivingExams8;
go
DELETE FROM Instructors;
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('8809219082745','Simion David','SJ12VDA');
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('9925170543526','Danut Dorin','SJ13DAV');
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('8609289040140','Haralamb Eugen','SJ30RIA');
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('4673261850041','Horea Dragos','SJ65DWN');
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('5408411857894','Apostol Emil','SJ67GRZ');
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('4599542702892','Ilie Emilian','SJ50RTM');
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('2075047510772','Iacob Eusebiu','SJ98ATM');
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('7196776918679','Antoniu Ionel','SJ91AAY');
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('8603711577258','Vaida Valer','SJ40MMC');
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('3438600813142','Chis Teofil','SJ70TEO');
go

DELETE FROM Students
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('3199705865004','Loredana Ileana',2,'8809219082745','01/01/2022');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('2168278601776','Iuliu Dorina',20,'8809219082745','10/11/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('6889232605884','Ramona Mircea',15,'9925170543526','10/01/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('6831736316573','Filimon Raul',16,'4673261850041','3/20/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('1318543676570','Gavril Adi',3,'5408411857894','05/06/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('2815078598327','Ioan Carol',1,'2075047510772','03/13/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('8785629202432','Flaviu Ionel',20,'8609289040140','04/06/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('1685902704761','Ladislau Marin',0,'8809219082745','01/02/2020');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('5424667600275','Raul Radu',15,'9925170543526','03/04/2020');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('0125282099674','Muresan Marian',20,'4673261850041','6/10/2022');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('0729419925257','Balas Andrei',0,'3438600813142','3/04/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('1967230149969','Gurza Roland',15,'8603711577258','4/10/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('6822153945653','Olar Serban',5,'7196776918679','01/01/2020');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('2411165833346','Horincar Mihai',7,'2075047510772','3/04/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('8414031374178','Gergely Krisztian',20,'5408411857894','07/17/2020');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('3240118986611','Vlasan Darius',13,'4599542702892','02/03/2022');
go

DELETE FROM StudentsDrivingLicenses
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('3199705865004','B');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('2168278601776','C');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('6889232605884','D');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('6831736316573','A');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('1318543676570','B');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('2815078598327','C+E');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('8785629202432','B+E');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('1685902704761','D+E');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('5424667600275','B');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('0125282099674','B');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('0729419925257','C');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('1967230149969','B');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('6822153945653','B');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('2411165833346','C');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('8414031374178','C+E');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('3240118986611','B');
go

DELETE FROM Categories;
INSERT INTO Categories(Category,MandatoryLessons) values('A',10);
INSERT INTO Categories(Category,MandatoryLessons) values('B',20);
INSERT INTO Categories(Category,MandatoryLessons) values('B+E',15);
INSERT INTO Categories(Category,MandatoryLessons) values('C',30);
INSERT INTO Categories(Category,MandatoryLessons) values('C+E',25);
INSERT INTO Categories(Category,MandatoryLessons) values('D',30);
INSERT INTO Categories(Category,MandatoryLessons) values('D+E',25);
go

DELETE  from InstructorsDrivingLicenses;
go
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8809219082745','B');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8809219082745','C');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8809219082745','A');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('9925170543526','B');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('9925170543526','B+E');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('9925170543526','C');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('9925170543526','C+E');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8609289040140','B');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8609289040140','C');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('4673261850041','A');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('4673261850041','B');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('5408411857894','B');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('5408411857894','B+E');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('5408411857894','C');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('5408411857894','C+E');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('5408411857894','D');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('4599542702892','B');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('4599542702892','C');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('4599542702892','C+E');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('4599542702892','D');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('2075047510772','B');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('2075047510772','C');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('7196776918679','A');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('7196776918679','B');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8603711577258','A');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8603711577258','B');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8603711577258','B+E');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8603711577258','C');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8603711577258','D');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8603711577258','D+E');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('3438600813142','B');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('3438600813142','C');

DELETE FROM Supervisors
go
INSERT INTO Supervisors(CNP,Name) values('4043402719748','Ion Filip');
INSERT INTO Supervisors(CNP,Name) values('6387498973155','Dragomir Rafael');
INSERT INTO Supervisors(CNP,Name) values('0248959795222','Dorin Mircea');
INSERT INTO Supervisors(CNP,Name) values('8637723874592','Octavian Mihail');
go

Insert into SupervisorsDrivingLicenses(CNP,Category) values('4043402719748','A');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('4043402719748','B');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('4043402719748','B+E');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('4043402719748','C');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('4043402719748','C+E');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('4043402719748','D');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('4043402719748','D+E');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('6387498973155','A');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('6387498973155','B');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('6387498973155','B+E');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('6387498973155','C');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('6387498973155','C+E');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('6387498973155','D');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('6387498973155','D+E');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('0248959795222','A');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('0248959795222','B');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('0248959795222','B+E');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('0248959795222','C');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('0248959795222','C+E');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('0248959795222','D');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('0248959795222','D+E');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('8637723874592','A');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('8637723874592','B');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('8637723874592','B+E');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('8637723874592','C');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('8637723874592','C+E');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('8637723874592','D');
Insert into SupervisorsDrivingLicenses(CNP,Category) values('8637723874592','D+E');
go

