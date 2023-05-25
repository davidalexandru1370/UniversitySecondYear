--reproduce the update conflict under an optimistic isolation level (grade 10).

use DrivingExams21;

go
DELETE FROM Students where CNP='9753439475'
INSERT INTO Students(CNP,InstructorCNP,CurrentLesson, Name,StartingDate) values('9753439475','8603711577258',5,'david update oil',GETDATE())
ALTER DATABASE DrivingExams21 SET ALLOW_SNAPSHOT_ISOLATION ON
WAITFOR DELAY '00:00:05'
BEGIN TRAN
UPDATE Students SET Name='david update oil code1' where CNP = '9753439475'
WAITFOR DELAY '00:00:05'
COMMIT TRAN


 ALTER DATABASE DrivingExams21 SET ALLOW_SNAPSHOT_ISOLATION OFF
