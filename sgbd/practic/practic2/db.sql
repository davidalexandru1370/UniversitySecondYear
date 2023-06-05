--create database practic3;

use practic3;
go
drop table PlayerTeams;
drop table Teams;
drop table TeamsType;
drop table Sports;
drop table Player;
go


create table TeamsType(
	id int primary key identity(1,1),
	maximumNumberOfMembers int,
	name nvarchar(100),
)

create Table Sports(
	id int primary key identity (1,1),
	name nvarchar(100),
	description nvarchar(100),
)

go
INSERT Into Sports values ('fotbal','desc');

create table Teams(
	id int primary key identity(1,1),
	teamsType int foreign key references TeamsType(id) on delete cascade,
	name nvarchar(100),
	startUpYear date,
	location nvarchar(100),
	sportId int foreign key references Sports(id) on delete cascade,
)

create table Player(
	id int primary key identity(1,1),
	name nvarchar(100),
	birthday date,
	gender bit,
)

create table PlayerTeams(
	id int primary key identity(1,1),
	playerId int foreign key references Player(id) on delete cascade,
	teamId int foreign key references Teams(id) on delete cascade,
	startDate date,
	stopDate date,
)