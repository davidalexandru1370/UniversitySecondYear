use DrivingExams21;

GO

SET TRAN ISOLATION LEVEL REPEATABLE READ

BEGIN TRAN
SELECT * from Students
WAITFOR DELAY '00:00:06'
SELECT * from Students
COMMIT TRAN