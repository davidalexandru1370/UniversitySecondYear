use DrivingExams21;
go

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
BEGIN TRAN
SELECT * FROM Students
WAITFOR DELAY '00:00:06'
SELECT * FROM Students
COMMIT TRAN