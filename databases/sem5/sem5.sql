use Trains;


IF OBJECT_ID('RoutesStations', 'U') IS NOT NULL
	drop table RoutesStations

IF OBJECT_ID('Stations', 'U') IS NOT NULL
	drop table RoutesStations

IF OBJECT_ID('Routes', 'U') IS NOT NULL
	drop table RoutesStations

IF OBJECT_ID('Trains', 'U') IS NOT NULL
	drop table RoutesStations

IF OBJECT_ID('TrainTypes', 'U') IS NOT NULL
	drop table RoutesStations



CREATE TABLE TrainTypes(
	id int PRIMARY KEY IDENTITY(1,1),
	name varchar(100),
	description varchar(100)
)

create table Stations(
	id int primary key identity(1,1),
	name varchar(100) unique
)

create table Trains(
	id int primary key identity(1,1),
	name varchar(100),
	trainTypeId int references TrainTypes(id)
)

create table Routes(
	id int primary key identity(1,1),
	name varchar(100) unique,
	trainId int references Trains(id)
)

create table RoutesStations(
	idRoute int references Routes(id),
	idStation int references Stations(id),
	arrival TIME,
	departure TIME,
	primary key (idRoute, idStation)
)

insert into Trains (name, id) VALUES ('t1', 1) ,('t2',2),('t3',3);

