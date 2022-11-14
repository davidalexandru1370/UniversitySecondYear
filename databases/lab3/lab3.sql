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
	alter table Instructors add  City nvarchar(255)
GO


CREATE PROCEDURE RemoveInstructorCityColumn
AS
	alter table Instructors drop column City
GO

--c. add / remove a DEFAULT constraint;

CREATE PROCEDURE AddDefaultToCandidateScoreInTheoreticalExams
AS
	alter table TheoreticalExams ADD CONSTRAINT DEFAULT_CandidateScore DEFAULT(0) for CandidateScore
GO

CREATE PROCEDURE RemoveDefaultToCandidateScoreInTheoreticalExams
AS
	ALTER TABLE TheoreticalExams DROP CONSTRAINT DEFAULT_CandidateScore
GO

--d. add / remove a primary key;
CREATE PROCEDURE AddPrimaryKeyTheoreticalExams
AS
	ALTER TABLE TheoreticalExams add constraint PK_TheoreticalExams primary key(Id)
GO

CREATE PROCEDURE RemovePrimaryKeyTheoreticalExams
AS
ALTER TABLE TheoreticalExams drop CONSTRAINT PK_TheoreticalExams
GO

--e. add / remove a candidate key;
CREATE PROCEDURE AddCarPlateUniqueInVehicles
AS
	ALTER TABLE Vehicles ADD CONSTRAINT CK_CarPlate_Vehicles UNIQUE (CarPlate)
GO

CREATE PROCEDURE RemoveCarPlateUniqueFromVehicles
AS
	ALTER TABLE VEHICLES DROP CONSTRAINT CK_CarPlate_Vehicles
Go

--f. add / remove a foreign key;
CREATE PROCEDURE AddBlacklistedForeignKey
AS
	ALTER TABLE BlacklistedInstructors ADD CONSTRAINT FK_Blacklisted_CNP FOREIGN KEY(CNP) REFERENCES Instructors(CNP) ON UPDATE CASCADE ON DELETE CASCADE
GO

CREATE PROCEDURE RemoveBlacklistedForeignKey
AS
	ALTER TABLE BlacklistedInstructors DROP CONSTRAINT FK_Blacklisted_CNP
GO

--g. create / drop a table.
CREATE PROCEDURE DropBlacklistedTable
AS
	drop table BlacklistedInstructors
GO


CREATE PROCEDURE CreateBlacklistedTable
AS
	CREATE TABLE BlacklistedInstructors(
		CNP NVARCHAR(100),
		CONSTRAINT PK_Blacklisted PRIMARY KEY (CNP)
	)
GO

create table versionTable (
	version int NOT NULL
)

DELETE FROM versionTable
insert into versionTable values (8)

create table proceduresTable (
	FromVersion INT NOT NULL,
	ToVersion INT NOT NULL,
	ProcedureName varchar(max),
	CONSTRAINT PK_ProceduresTable PRIMARY KEY (FromVersion,ToVersion),
)

insert into proceduresTable values(1,2,'ChangeCurrentLessonToTinyInt')
insert into proceduresTable values(2,1,'ChangeCurrentLessonToInt')
insert into proceduresTable values(2,3,'AddInstructoryCityColumn')
insert into proceduresTable values(3,2,'RemoveInstructorCityColumn')
insert into proceduresTable values(3,4,'AddDefaultToCandidateScoreInTheoreticalExams')
insert into proceduresTable values(4,3,'RemoveDefaultToCandidateScoreInTheoreticalExams')
insert into proceduresTable values(4,5,'AddPrimaryKeyTheoreticalExams')
insert into proceduresTable values(5,4,'RemovePrimaryKeyTheoreticalExams')
insert into proceduresTable values(5,6,'AddCarPlateUniqueInVehicles')
insert into proceduresTable values(6,5,'RemoveCarPlateUniqueFromVehicles')
insert into proceduresTable values(6,7,'CreateBlacklistedTable')
insert into proceduresTable values(7,6,'DropBlacklistedTable')
insert into proceduresTable values(7,8,'AddBlacklistedForeignKey')
insert into proceduresTable values(8,7,'RemoveBlacklistedForeignKey')

create procedure goToVersion(@newVersion int) 
AS
	declare @current int
	declare @procedureName varchar(max)
	select @current=version from versionTable

	if @newVersion > (select MAX(toVersion) from proceduresTable) or @newVersion < 1
		raiserror('Bad version',10,1)
		return;

	while @current > @newVersion begin
		select @procedureName=ProcedureName from proceduresTable where FromVersion=@current and ToVersion=@current-1
		exec (@procedureName)
		set @current=@current-1

	end

	while @current < @newVersion begin
		select @procedureName=ProcedureName from proceduresTable where FromVersion=@current and ToVersion=@current+1
		exec (@procedureName)
		set @current=@current+1
	end

	update versionTable set version=@newVersion
GO

execute goToVersion 1

SELECT * FROM versionTable

EXECUTE [dbo].[sp_helpconstraint] 'TheoreticalExams'
GO