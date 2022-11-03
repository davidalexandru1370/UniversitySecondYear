/*
insert data – for at least 4 tables; at least one statement must violate referential integrity constraints;
update data – for at least 3 tables;
delete data – for at least 2 tables;

a. 2 queries with the union operation; use UNION [ALL] and OR;
b. 2 queries with the intersection operation; use INTERSECT and IN;
c. 2 queries with the difference operation; use EXCEPT and NOT IN;
*/

use DrivingExams;
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
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('8603711577258','Constantin Marin','SJ40MMC');
INSERT INTO Instructors(CNP,Name,VehiclePlate) values ('3438600813142','Chis Teofil','SJ70TEO');
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
--INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('3438600813143','C');
