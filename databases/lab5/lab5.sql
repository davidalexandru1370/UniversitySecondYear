use DrivingExams21;


if OBJECT_ID('Ta','U') IS NULL 
	create table Ta(
		aid int PRIMARY KEY IDENTITY (1,1),
		a2 int unique
	)

if OBJECT_ID('Tb','U') IS NULL
	create table Tb(
		bid int PRIMARY KEY IDENTITY (1,1),
		b2 int
	)

if OBJECT_ID('Tc','U') IS NULL
	create table Tc(
		cid int PRIMARY KEY IDENTITY (1,1),
		aid int,
		bid int,
		CONSTRAINT FK_Tc_Ta FOREIGN KEY (aid) REFERENCES Ta(aid),
		CONSTRAINT FK_Tc_Tb FOREIGN KEY (bid) REFERENCES Tb(bid) 
	)