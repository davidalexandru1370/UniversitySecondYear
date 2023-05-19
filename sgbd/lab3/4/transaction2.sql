use DrivingExams21;
go

SET TRAN ISOLATION LEVEL SNAPSHOT
BEGIN TRAN

WAITFOR DELAY '00:00:05'

UPDATE Students SET Name='david update oil code2' where CNP = '9753439475'
commit tran
