DECLARE @DB_NAME AS VARCHAR(100)='DrivingExams';
DROP DATABASE DrivingExams;
CREATE DATABASE DrivingExams;

CREATE TABLE DrivingExams.dbo.Categories(
	Category NVARCHAR(100) NOT NULL PRIMARY KEY,
	MandatoryLessons int NOT NULL,
);

CREATE TABLE DrivingExams.dbo.Instructors(
	Id int PRIMARY KEY IDENTITY(1,1),
	CNP NVARCHAR(100) UNIQUE NOT NULL,
	Name NVARCHAR(100) NOT NULL,
	VehiclePlate NVARCHAR(100) UNIQUE NOT NULL,
);

CREATE TABLE DrivingExams.dbo.InstructorsDrivingLicenses(
	Id int PRIMARY KEY IDENTITY(1,1),
	CNP NVARCHAR(100) NOT NULL,
	Category NVARCHAR(100) NOT NULL,
	FOREIGN KEY (CNP) REFERENCES Categories(Category),
	FOREIGN KEY (CNP) REFERENCES Instructors(CNP),
);

CREATE TABLE DrivingExams.dbo.SupervisorsDrivingLicenses(
	Id int PRIMARY KEY IDENTITY(1,1),
	CNP NVARCHAR(100) NOT NULL,
	Category NVARCHAR(100) NOT NULL,
)

CREATE TABLE DrivingExams.dbo.Students(
	CNP NVARCHAR(100) PRIMARY KEY,
	Name NVARCHAR(100) NOT NULL,
	CurrentLesson int NOT NULL,
	InstructorId int NOT NULL,
	StartingDate DATE NOT NULL,
	StudyingCategory NVARCHAR(100) NOT NULL,
	FOREIGN KEY (InstructorId) REFERENCES Instructors(Id)
);

CREATE TABLE DrivingExams.dbo.StudentsDrivingLicenses(
	Id int PRIMARY KEY IDENTITY(1,1),
	CNP NVARCHAR(100) NOT NULL,
	Category NVARCHAR(100) NOT NULL,
	FOREIGN KEY (Category) REFERENCES Categories(Category),
	FOREIGN KEY (CNP) REFERENCES Students(CNP)
);

Create Table DrivingExams.dbo.Supervisors(
	Id int PRIMARY KEY IDENTITY(1,1),
	CNP NVARCHAR(100) NOT NULL,
	Name NVARCHAR(100) NOT NULL,
);
