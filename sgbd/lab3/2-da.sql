--create a stored procedure that inserts data in tables that are in a m:n relationship
--; if an insert fails, try to recover as much as possible from the entire operation:
--for example, if the user wants to add a book and its authors, succeeds creating the authors
--, but fails with the book, the authors should remain in the database (grade 5);
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
create or alter procedure addAddTheoreticalExamRecover(@id integer, @candidateCnp varchar(100), @supervisorCnp varchar(100), @candidateScore integer, @examDate date)
as
BEGIN
	SET NOCOUNT ON
	BEGIN TRAN
	BEGIN TRY
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
		INSERT INTO LogTable values('add recover','theoreticalExams',GETDATE())
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
		SELECT ERROR_MESSAGE() AS ErrorMessage;  
	END CATCH
END

go
create or alter procedure addStudentRecover(@cnp varchar(100),	 @name varchar(100), @currentLesson integer, @startingDate date) 
as
begin
	SET NOCOUNT ON
	BEGIN TRAN
	BEGIN TRY
		if(dbo.validateStudent(@cnp,@name,@currentLesson) <> 1)
		begin
			RAISERROR('INVALID student data',14,1)
		end

		if exists (SELECT * FROM Students S where S.CNP = @cnp)
		begin
			RAISERROR('Student already exists',14,1)
		end

		INSERT INTO Students(CNP, Name, CurrentLesson, InstructorCNP, StartingDate) values(@cnp,@name,@currentLesson,'8603711577258',@startingDate)
		INSERT INTO LogTable values('add recover','student',GETDATE())
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
	END CATCH
end

go
create or alter procedure addSupervisorRecover(@cnp varchar(100), @name varchar(100)) 
as
begin
	SET NOCOUNT ON
	BEGIN TRAN
	BEGIN TRY
		if(dbo.validateSupervisor(@cnp, @name) <> 1)
		begin
			RAISERROR('INVALID supervisor data',14,1)
		end


		if exists (SELECT * FROM Supervisors S where S.CNP = @cnp)
		begin
			RAISERROR('Supervisor already exists',14,1)
		end

		INSERT INTO Supervisors(CNP,Name) values(@cnp,@name)
		INSERT INTO LogTable values('add recover','supervisor',GETDATE())
		SELECT * FROM TheoreticalExams where Id = 1000
		COMMIT TRAN
	END TRY
	BEGIN CATCH 
		ROLLBACK TRAN
	END CATCH
end

go

create or alter procedure addGoodScenario as
begin
		EXEC addStudentRecover '2284578999','David Recover',6,'2022-02-02'
		EXEC addSupervisorRecover '9991249099', 'Vasile Recover'
		EXEC addAddTheoreticalExamRecover 1000, '2284578999', '9991249099', 25, '2022-02-02'
end

go
create or alter procedure addBadScenario as
begin
		EXEC addStudentRecover '2284566999','David Recover Bad',6,'2022-02-02'
		EXEC addSupervisorRecover '9991249999', 'Vasile Recover Bad'
		EXEC addAddTheoreticalExamRecover 1001, '2284566999', '9991249999', 2665, '2022-02-02'
end

DELETE  FROM Students where CNP = '2284578999'
DELETE FROM Supervisors where CNP = '9991249099'
DELETE FROM TheoreticalExams where Id = 1000
DELETE  FROM Students where CNP = '2284566999'
DELETE FROM Supervisors where CNP = '9991249999'
DELETE FROM TheoreticalExams where Id = 1001
exec addGoodScenario
exec addBadScenario
SELECT * from LogTable
SELECT * FROM TheoreticalExams
SELECT * FROM Students
SELECT * FROM Supervisors

