use DrivingExams21
GO
DELETE FROM Students where CNP = '3423876423'
INSERT INTO Students(CNP,InstructorCNP, Name,StartingDate) values('3423876423','8603711577258','david non-repeteable read',GETDATE())
BEGIN TRAN
WAITFOR DELAY '00:00:04'
UPDATE Students
SET Name = 'david non-repeteable read update'
where CNP  = '3423876423'
COMMIT TRAN

