use DrivingExams21;
go
DELETE FROM Students where CNP = '8623876423'

BEGIN TRAN
WAITFOR DELAY '00:00:06'
INSERT INTO Students(CNP,InstructorCNP, Name,StartingDate) values('8623876423','8603711577258','david phantom reads',GETDATE())
COMMIT TRAN

