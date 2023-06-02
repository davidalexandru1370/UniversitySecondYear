use DrivingExams21;

go

DROP TABLE IF EXISTS LogTable 
CREATE TABLE LogTable(
	Id INT IDENTITY PRIMARY KEY,
	TypeOperation VARCHAR(50),
	TableOperation VARCHAR(50),
	ExecutionDate DATETIME
);

GO

go
CREATE OR ALTER FUNCTION validateTheoreticalExam (@candidateCnp varchar(100), @supervisorCnp varchar(100), @candidateScore integer)
RETURNS INT
AS
begin
	DECLARE @result INT
	SET @result = 1
	if LEN(@candidateCnp) <> 10 or len(@supervisorCnp) <> 10 or @candidateScore < 0 or @candidateScore > 26
		set @result = 0
	RETURN @result
end
go

CREATE OR ALTER FUNCTION validateStudent (@candidateCnp varchar(100), @name varchar(100), @currentLesson integer)
RETURNS INT
AS
begin
	DECLARE @result INT
	SET @result = 1
	if LEN(@candidateCnp) <> 10 or @currentLesson < 0 or @currentLesson > 30 
		set @result = 0
	RETURN @result
end
go


CREATE OR ALTER FUNCTION validateSupervisor (@supervisorCnp varchar(100), @name varchar(100))
RETURNS INT
AS
BEGIN
	DECLARE @result INT
	SET @result = 1
	if LEN(@supervisorCnp) <> 10 or len(@name) = 0
	begin
		SET @result = 0
	end

	return @result
END


go
create or alter procedure addAddTheoreticalExam(@id integer, @candidateCnp varchar(100), @supervisorCnp varchar(100), @candidateScore integer, @examDate date)
as
BEGIN
	SET NOCOUNT ON
	if (dbo.validateTheoreticalExam(@candidateCnp,@supervisorCnp,@candidateScore) <> 1)
	begin 
		RAISERROR('Invalid theoretical exam', 14, 1)
	end

	IF NOT EXISTS (SELECT * FROM Students S where S.CNP = @candidateCnp)
	begin
		RAISERROR('Invalid student for theoretical exam', 14, 1)
	end


	IF NOT EXISTS (SELECT * FROM Supervisors S where S.CNP = @supervisorCnp)
	begin
		RAISERROR('Invalid supervisor for theoretical exam', 14, 1)
	end
	
	INSERT INTO TheoreticalExams(Id,CandidateCNP,SupervisorCNP,CandidateScore,ExamDate) values(@id, @candidateCnp, @supervisorCnp, @candidateScore, @examDate)
	INSERT INTO LogTable values('add','theoreticalExams',GETDATE())

END

go
create or alter procedure addStudent(@cnp varchar(100),	 @name varchar(100), @currentLesson integer, @startingDate date) 
as
begin
	SET NOCOUNT ON
	if(dbo.validateStudent(@cnp,@name,@currentLesson) <> 1)
	begin
		RAISERROR('INVALID student data',14,1)
	end

	if exists (SELECT * FROM Students S where S.CNP = @cnp)
	begin
		RAISERROR('Student already exists',14,1)
	end

	INSERT INTO Students(CNP, Name, CurrentLesson, InstructorCNP, StartingDate) values(@cnp,@name,@currentLesson,'8603711577258',@startingDate)
	INSERT INTO LogTable values('add','student',GETDATE())
end

go
create or alter procedure addSupervisor(@cnp varchar(100), @name varchar(100)) 
as
begin
	SET NOCOUNT ON
	if(dbo.validateSupervisor(@cnp, @name) <> 1)
	begin
		RAISERROR('INVALID supervisor data',14,1)
	end


	if exists (SELECT * FROM Supervisors S where S.CNP = @cnp)
	begin
		RAISERROR('Supervisor already exists',14,1)
	end

	INSERT INTO Supervisors(CNP,Name) values(@cnp,@name)
	INSERT INTO LogTable values('add','supervisor',GETDATE())

end

go
create or alter procedure commitScenario
as
begin
		BEGIN TRAN
		BEGIN TRY
			EXEC addStudent "2284578954","david",5,"2022-02-02"
			EXEC addSupervisor "9991249048", 'Vasile'
			EXEC addAddTheoreticalExam 999, "2284578954", "9991249048", 25, "2022-02-02"
			COMMIT TRAN
		END TRY
		BEGIN CATCH
			ROLLBACK TRAN
			RETURN
		END CATCH
end

exec commitScenario

SELECT * from LogTable
SELECT * FROM TheoreticalExams
SELECT * FROM Students
DELETE FROM Students where CNP='2284578954'
DELETE FROM Supervisors where CNP='9991249048'
DELETE FROM TheoreticalExams WHERE Id=999

go
create or alter procedure failScenario
as
begin
		BEGIN TRAN
		BEGIN TRY
			EXEC addStudent '2284578954','davidfail',55555,'2022-02-02'
			EXEC addSupervisor '9991249048', 'Vasilefail'
			EXEC addAddTheoreticalExam 999, '2284578954', '9991249048', 25, '2022-02-02'
			COMMIT TRAN
		END TRY
		BEGIN CATCH
			ROLLBACK TRAN
			    SELECT ERROR_MESSAGE() AS ErrorMessage;  
			RETURN
		END CATCH
end

exec failScenario

SELECT * from LogTable
SELECT * FROM TheoreticalExams
SELECT * FROM Students
SELECT * FROM Supervisors

