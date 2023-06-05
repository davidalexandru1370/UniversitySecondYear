use practic3
GO
--DELETE FROM Students where CNP = '3423876423'
--INSERT INTO Students(CNP,InstructorCNP, Name,StartingDate) values('3423876423','8603711577258','david non-repeteable read',GETDATE())
delete from TeamsType where id = 2;
SET IDENTITY_INSERT TeamsType ON;
insert into TeamsType(id,maximumNumberOfMembers,name) values(2, 10, 't2');
BEGIN TRAN
WAITFOR DELAY '00:00:04'
UPDATE TeamsType
SET Name = 't2 non-repeteable read update'
where id  = 2
COMMIT TRAN

SELECT * FROM TeamsType