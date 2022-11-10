/*
insert data – for at least 4 tables; at least one statement must violate referential integrity constraints;
update data – for at least 3 tables;
delete data – for at least 2 tables;

a. 2 queries with the union operation; use UNION [ALL] and OR;
b. 2 queries with the intersection operation; use INTERSECT and IN;
c. 2 queries with the difference operation; use EXCEPT and NOT IN;
*/

use DrivingExams21;
go
Delete from PracticalExams;
go
DELETE FROM Instructors;
go
INSERT INTO Instructors(CNP,Name) values ('8809219082745','Simion David');
INSERT INTO Instructors(CNP,Name) values ('9925170543526','Danut Dorin');
INSERT INTO Instructors(CNP,Name) values ('8609289040140','Haralamb Eugen');
INSERT INTO Instructors(CNP,Name) values ('4673261850041','Horea Dragos');
INSERT INTO Instructors(CNP,Name) values ('5408411857894','Apostol Emil');
INSERT INTO Instructors(CNP,Name) values ('4599542702892','Ilie Emilian');
INSERT INTO Instructors(CNP,Name) values ('2075047510772','Iacob Eusebiu');
INSERT INTO Instructors(CNP,Name) values ('7196776918679','Antoniu Ionel');
INSERT INTO Instructors(CNP,Name) values ('8603711577258','Vaida Valer');
INSERT INTO Instructors(CNP,Name) values ('3438600813142','Chis Teofil');
go

DELETE FROM Students
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('3199705865004','Loredana Ileana',2,'8809219082745','01/01/2022');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('2168278601776','Iuliu Dorina',20,'8809219082745','10/11/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('6889232605884','Ramona Mircea',15,'9925170543526','10/01/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('6831736316573','Filimon Raul',20,'4673261850041','3/20/2022');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('1318543676570','Gavril Adi',3,'5408411857894','05/06/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('2815078598327','Ioan Carol',1,'2075047510772','03/13/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('8785629202432','Flaviu Ionel',20,'8609289040140','04/06/2022');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('1685902704761','Ladislau Marin',0,'8809219082745','01/02/2020');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('5424667600275','Raul Radu',15,'9925170543526','03/04/2020');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('0125282099674','Muresan Marian',20,'4673261850041','6/10/2022');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('0729419925257','Balas Andrei',0,'3438600813142','3/04/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('1967230149969','Gurza Roland',15,'8603711577258','4/10/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('6822153945653','Olar Serban',5,'7196776918679','01/01/2020');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('2411165833346','Horincar Mihai',7,'2075047510772','3/04/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('8414031374178','Gergely Krisztian',20,'5408411857894','07/17/2022');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('3240118986611','Vlasan Darius',13,'4599542702892','02/03/2022');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('3240118978611','Ratiu Gabriel',0,NULL,'02/03/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('3240118932161','Laszlo David',0,NULL,'02/03/2021');
INSERT INTO Students(CNP,Name,CurrentLesson,InstructorCNP,StartingDate) values ('6554308932161','Rogoz Sergiu',0,NULL,'02/03/2022');
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
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('3240118978611','B');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('3240118932161','B');
INSERT INTO StudentsDrivingLicenses(CNP,Category) values ('6554308932161','B');
go

DELETE  from InstructorsDrivingLicenses;
go
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8809219082745','A');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8809219082745','B');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8809219082745','B+E');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8809219082745','C');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8809219082745','C+E');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8809219082745','D');
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('8809219082745','D+E');
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
--error
INSERT INTO InstructorsDrivingLicenses(CNP,Category) values('5408411867894','C+E');


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

DBCC CHECKIDENT('Vehicles',RESEED,0);
Insert into Vehicles(InstructorCNP,CarPlate) values('8809219082745','CJ30RIA');
Insert into Vehicles(InstructorCNP,CarPlate) values('9925170543526','SJ31TEO');
Insert into Vehicles(InstructorCNP,CarPlate) values('8609289040140','SJ32FIL');
Insert into Vehicles(InstructorCNP,CarPlate) values('4673261850041','SJ15MMC');
Insert into Vehicles(InstructorCNP,CarPlate) values('5408411857894','MM16AAY');
Insert into Vehicles(InstructorCNP,CarPlate) values('4599542702892','SJ20AAY');
Insert into Vehicles(InstructorCNP,CarPlate) values('2075047510772','BH21DWN');
Insert into Vehicles(InstructorCNP,CarPlate) values('7196776918679','MM30RTM');
Insert into Vehicles(InstructorCNP,CarPlate) values('8603711577258','BH13DAV');
Insert into Vehicles(InstructorCNP,CarPlate) values('3438600813142','SJ86GRZ');
Insert into Vehicles(InstructorCNP,CarPlate) values('8809219082745','CJ87ATM');
Insert into Vehicles(InstructorCNP,CarPlate) values('8809219082745','CJ90VFV');
Insert into Vehicles(CarPlate,InstructorCNP) values('BH56GRZ','4673261850041');
go

DELETE from TheoreticalExams
go
INSERT INTO TheoreticalExams(CandidateCNP,SupervisorCNP,ExamDate,CandidateScore) values('8414031374178','4043402719748','12/11/2021',26);
INSERT INTO TheoreticalExams(CandidateCNP,SupervisorCNP,ExamDate,CandidateScore) values('0125282099674','4043402719748','12/10/2021',24);
INSERT INTO TheoreticalExams(CandidateCNP,SupervisorCNP,ExamDate,CandidateScore) values('6831736316573','0248959795222','12/10/2021',18);
INSERT INTO TheoreticalExams(CandidateCNP,SupervisorCNP,ExamDate,CandidateScore) values('2168278601776','0248959795222','12/10/2021',21);
INSERT INTO TheoreticalExams(CandidateCNP,SupervisorCNP,ExamDate,CandidateScore) values('6831736316573','6387498973155','12/24/2021',21);
go

--luna/ziua/an
DELETE FROM InstructorDetails;
INSERT INTO InstructorDetails(CNP,CertificationIssued,CertificationExpiration) values('8809219082745','06/20/2019','06/20/2024');
INSERT INTO InstructorDetails(CNP,CertificationIssued,CertificationExpiration) values('9925170543526','05/19/2018','05/10/2023');
INSERT INTO InstructorDetails(CNP,CertificationIssued,CertificationExpiration) values('8609289040140','04/15/2019','04/15/2024');
INSERT INTO InstructorDetails(CNP,CertificationIssued,CertificationExpiration) values('4673261850041','11/22/2018','11/22/2023');
INSERT INTO InstructorDetails(CNP,CertificationIssued,CertificationExpiration) values('5408411857894','12/13/2016','12/13/2021');
INSERT INTO InstructorDetails(CNP,CertificationIssued,CertificationExpiration) values('4599542702892','11/13/2016','11/13/2021');
INSERT INTO InstructorDetails(CNP,CertificationIssued,CertificationExpiration) values('2075047510772','10/11/2015','10/11/2020');
INSERT INTO InstructorDetails(CNP,CertificationIssued,CertificationExpiration) values('7196776918679','07/09/2021','07/09/2026');
INSERT INTO InstructorDetails(CNP,CertificationIssued,CertificationExpiration) values('8603711577258','09/09/2020','09/09/2025');
INSERT INTO InstructorDetails(CNP,CertificationIssued,CertificationExpiration) values('3438600813142','11/11/2017','11/11/2022');

Insert into PracticalExams(CandidateCNP,SupervisorCNP,CandidateScore,ExamDate,CarId) values ('8414031374178','4043402719748',22,'05/10/2021',5);
Insert into PracticalExams(CandidateCNP,SupervisorCNP,CandidateScore,ExamDate,CarId) values ('0125282099674','4043402719748',18,'05/10/2021',4);


UPDATE Students set InstructorCNP = '3438600813142' where InstructorCNP is null and DATEDIFF(year,StartingDate,GETDATE()) < 1
UPDATE Categories set MandatoryLessons = 20 where MandatoryLessons between 0 and 19;
UPDATE Vehicles set CarPlate = 'SJ' + SUBSTRING(Carplate,3,6) where CarPlate NOT IN ('BH21DWN','BH13DAV','CJ90VFV')

DELETE FROM Students where InstructorCNP is null or DATEDIFF(DAYOFYEAR,StartingDate,GETDATE()) >= 365
DELETE FROM TheoreticalExams where CandidateScore between 22 and 26;
DELETE FROM Instructors where Name like 'Chis%';


INSERT INTO TheoreticalExams(CandidateCNP,SupervisorCNP,ExamDate,CandidateScore) values('8414031374178','4043402719748','12/11/2021',26);
INSERT INTO TheoreticalExams(CandidateCNP,SupervisorCNP,ExamDate,CandidateScore) values('0125282099674','4043402719748','12/10/2021',24);
go