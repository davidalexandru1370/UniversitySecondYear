--DECLARE @DB_NAME AS VARCHAR(100)='DrivingExams';
--alter database DrivingExams set single_user with rollback immediate
--DROP DATABASE DrivingExams;
CREATE DATABASE DrivingExams9

go
USE  DrivingExams9;
go

CREATE TABLE Categories(
	Category NVARCHAR(100) NOT NULL PRIMARY KEY,
	MandatoryLessons int NOT NULL,
);

CREATE TABLE Instructors(
	Id int PRIMARY KEY IDENTITY(1,1),
	CNP NVARCHAR(100) UNIQUE NOT NULL,
	Name NVARCHAR(100) NOT NULL,
	VehiclePlate NVARCHAR(100) UNIQUE NOT NULL,
);

CREATE TABLE InstructorsDrivingLicenses(
	Id int PRIMARY KEY IDENTITY(1,1),
	CNP NVARCHAR(100) NOT NULL,
	Category NVARCHAR(100) NOT NULL,
	FOREIGN KEY (CNP) REFERENCES Categories(Category),
	FOREIGN KEY (CNP) REFERENCES Instructors(CNP),
);

CREATE TABLE Students(
	CNP NVARCHAR(100) PRIMARY KEY,
	Name NVARCHAR(100) NOT NULL,
	CurrentLesson int NOT NULL,
	InstructorId int NOT NULL,
	StartingDate DATE NOT NULL,
	FOREIGN KEY (InstructorId) REFERENCES Instructors(Id) ON DELETE  NO ACTION
);

CREATE TABLE StudentsDrivingLicenses(
	Id int PRIMARY KEY IDENTITY(1,1),
	CNP NVARCHAR(100) NOT NULL,
	Category NVARCHAR(100) NOT NULL,
	FOREIGN KEY (Category) REFERENCES Categories(Category),
	FOREIGN KEY (CNP) REFERENCES Students(CNP)
);

Create Table Supervisors(
	CNP NVARCHAR(100) Primary Key NOT NULL,
	Name NVARCHAR(100) NOT NULL,

);

CREATE TABLE SupervisorsDrivingLicenses(
	Id int PRIMARY KEY IDENTITY(1,1),
	CNP NVARCHAR(100) NOT NULL,
	Category NVARCHAR(100) NOT NULL,
	FOREIGN KEY (CNP) REFERENCES Supervisors(CNP),
	FOREIGN KEY (Category) REFERENCES Categories(Category),
)

CREATE TABLE TheoreticalExams(
	Id int PRIMARY KEY IDENTITY (1,1),
	CandidateCNP NVARCHAR(100) NOT NULL,
	SupervisorCNP NVARCHAR(100) NOT NULL,
	CandidateScore int NOT NULL,
	ExamDate date NOT NULL,
	Score int NOT NULL,
	FOREIGN KEY (CandidateCNP) REFERENCES Students(CNP),
	FOREIGN KEY (SupervisorCNP) REFERENCES Supervisors(CNP),
);

CREATE TABLE Vehicles(
	Id int  PRIMARY KEY FOREIGN KEY REFERENCES Instructors(Id),
	CarPlate NVARCHAR(100) NOT NULL,
	InstructorId int NOT NULL,
);

CREATE TABLE PracticalExams(
	Id int PRIMARY KEY IDENTITY (1,1),
	CandidateCNP NVARCHAR(100) NOT NULL,
	SupervisorCNP NVARCHAR(100) NOT NULL,
	CandidateScore int NOT NULL,
	ExamDate date NOT NULL,
	CarId int NOT NULL,
	Score int NOT NULL,
	FOREIGN KEY (CandidateCNP) REFERENCES Students(CNP),
	FOREIGN KEY (SupervisorCNP) REFERENCES Supervisors(CNP),
	FOREIGN KEY (CarId) REFERENCES Vehicles(Id),
);

CREATE TABLE Results(
	Id int IDENTITY (1,1) PRIMARY KEY,
	CandidateCNP NVARCHAR(100) NOT NULL,
	FinalResult bit NOT NULL,
	FOREIGN KEY (CandidateCNP) REFERENCES Students(CNP),
)



CREATE TABLE InstructorDetails(
	Id int PRIMARY KEY FOREIGN KEY REFERENCES Instructors(Id),
	CertificationIssued DATE NOT NULL,
	CertificationExpiration DATE NOT NULL,
	EnrolledStudents INT NOT NULL,
)