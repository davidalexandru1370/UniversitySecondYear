use DrivingExams21

Select CNP as StudentCNP from Students where CurrentLesson <= 20 or DATEDIFF(DAYOFYEAR,StartingDate,GETDATE()) <=365
UNION 
SELECT CNP as InstructorCNP from InstructorsDrivingLicenses


SELECT CNP as StudentCNP from StudentsDrivingLicenses where Category='B' or Category='C'
UNION 
SELECT CNP from InstructorsDrivingLicenses where Category='B' or Category='C'