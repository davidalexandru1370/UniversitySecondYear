--DECLARE @DB_NAME AS VARCHAR(100)='DrivingExams';
--alter database DrivingExams SET MULTI_USER with rollback immediate
--DROP DATABASE DrivingExams;
CREATE DATABASE DrivingExams7

go
USE  DrivingExams7;
go

CREATE TABLE Categories(
	Category NVARCHAR(100) NOT NULL PRIMARY KEY,
	MandatoryLessons int NOT NULL,
);

CREATE TABLE Instructors(
	CNP NVARCHAR(100) PRIMARY KEY NOT NULL,
	Name NVARCHAR(100) NOT NULL,
	VehiclePlate NVARCHAR(100) UNIQUE NOT NULL,
);

CREATE TABLE InstructorsDrivingLicenses(
	CNP NVARCHAR(100) NOT NULL,
	Category NVARCHAR(100) NOT NULL,
	Constraint FK_Category FOREIGN KEY (Category) REFERENCES Categories(Category)  ON DELETE CASCADE,
	Constraint FK_CNP FOREIGN KEY (CNP) REFERENCES Instructors(CNP)  ON DELETE CASCADE,
	CONSTRAINT PK_Category_CNP PRIMARY KEY (CNP,Category) 
);

CREATE TABLE Students(
	CNP NVARCHAR(100) PRIMARY KEY,
	Name NVARCHAR(100) NOT NULL,
	CurrentLesson int NOT NULL,
	InstructorCNP NVARCHAR(100) NULL,
	StartingDate DATE NOT NULL,
	FOREIGN KEY (InstructorCNP) REFERENCES Instructors(CNP) ON DELETE SET NULL
);

CREATE TABLE StudentsDrivingLicenses(
	CNP NVARCHAR(100) NOT NULL,
	Category NVARCHAR(100) NOT NULL,
	Constraint FK_StudentsDrivingLicenses_Category FOREIGN KEY (Category) REFERENCES Categories(Category) ON DELETE CASCADE,
	CONSTRAINT FK_StudentDrivingLicences_CNP FOREIGN KEY (CNP) REFERENCES Students(CNP) ON DELETE CASCADE,
	Constraint PK_StudentDrivingLicenses PRIMARY KEY (CNP,Category),
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
	Id int  PRIMARY KEY,
	CarPlate NVARCHAR(100) NOT NULL,
	InstructorCNP NVARCHAR(100) NOT NULL,
	Constraint FK_InstructorId FOREIGN KEY(InstructorCNP) REFERENCES Instructors(CNP)
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
	FOREIGN KEY (CandidateCNP) REFERENCES Students(CNP) ON DELETE CASCADE,
)



CREATE TABLE InstructorDetails(
	CNP NVARCHAR(100) PRIMARY KEY,
	CertificationIssued DATE NOT NULL,
	CertificationExpiration DATE NOT NULL,
	EnrolledStudents INT NOT NULL,
	Constraint FK_InstructorDetails_CNP FOREIGN KEY(CNP) REFERENCES Instructors(CNP) ON DELETE CASCADE,
)