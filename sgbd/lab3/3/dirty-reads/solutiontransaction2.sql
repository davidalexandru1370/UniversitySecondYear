use DrivingExams21;
GO

SET TRAN ISOLATION LEVEL READ COMMITTED
BEGIN TRAN
SELECT * FROM Students
WAITFOR DELAY '00:00:10'
SELECT * FROM Students
COMMIT TRAN