use DrivingExams21

--a. 2 queries with the union operation; use UNION [ALL] and OR;
--b. 2 queries with the intersection operation; use INTERSECT and IN;
--c. 2 queries with the difference operation; use EXCEPT and NOT IN;

Select CNP as StudentCNP from Students where CurrentLesson <= 20 or DATEDIFF(DAYOFYEAR,StartingDate,GETDATE()) <=365
UNION 
SELECT CNP as InstructorCNP from InstructorsDrivingLicenses


SELECT CNP as StudentCNP from StudentsDrivingLicenses where Category='B' or Category='C'
UNION 
SELECT CNP from InstructorsDrivingLicenses where Category='B' or Category='C'


SELECT CandidateCNP from PracticalExams where CandidateScore < 21
intersect 
SELECT CandidateCNP  from TheoreticalExams where CandidateScore >= 22;

Select CNP as SupervisorCNP from Supervisors 
intersect
Select SupervisorCNP from PracticalExams;
