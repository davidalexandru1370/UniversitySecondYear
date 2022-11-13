use DrivingExams21;

--a. modify the type of a column;
CREATE PROCEDURE ChangeCurrentLessonToTinyInt
AS
	alter table Students alter column CurrentLesson tinyint
GO

CREATE PROCEDURE ChangeCurrentLessonToInt
AS
	alter table Students alter column CurrentLesson int
GO


--b. add / remove a column;
CREATE PROCEDURE AddInstructoryCityColumn
AS
	alter table Instructors add column City nvarchar(255)
GO

CREATE PROCEDURE RemoveInstructorCityColumn
AS
	alter table Instructors drop column City
GO

--c. add / remove a DEFAULT constraint;
