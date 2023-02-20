--1 c si d
--2 e
--3 e
--create database Games;
use Games;

if OBJECT_ID('Results','U') is not null
	drop table Results

if OBJECT_ID('Sports','U') is not null
	drop table Sports

if OBJECT_ID('Venue','U') is not null
	drop table Venue

if OBJECT_ID('SportsEvents','U') is not null
	drop table SportsEvents

if OBJECT_ID('SportEvents','U') is not null
	drop table SportEvents

if OBJECT_ID('Athletes','U') is not null
	drop table Athletes

if OBJECT_ID('Countries','U') is not null
	drop table Countries

create table Countries(
	id int primary key identity(1,1),
	name nvarchar(100)
)

create table Athletes(
	id int primary key identity(1,1),
	name nvarchar(100),
	country int foreign key references Countries(id),
	date_of_birth datetime,
)

create table SportEvents(
	id int primary key identity(1,1),
	name nvarchar(100)
)

create table SportsEvents(
	sportEventId int,
	sportId int,
	primary key (sportEventId,sportId)
)

create table Venue(
	id int primary key identity(1,1),
	address nvarchar(10),
	city nvarchar(100),
)

create table Sports( --de complemtat
	id int primary key identity(1,1),
	name nvarchar(100),
	venueId int foreign key references Venue(id),
)

create table Results(
	id int primary key identity(1,1),
	athleteId int foreign key references Athletes(id),
	sportEventId int foreign key references SportEvents(id),
	rank int ,
	result_date datetime,
	result nvarchar(100) null,
)

INSERT INTO Countries values('c1');
INSERT INTO Countries values('c2');


insert into Athletes values('a1',1,'12/07/2002')
insert into Athletes values('a2',2,'12/07/2002')
insert into Athletes values('a3',1,'12/07/2002')
insert into Athletes values('a4',1,'12/07/2002')

insert into SportEvents values('100m freestyle');
insert into SportEvents values('200m butterfly');
insert into SportEvents values('pilotaj');

insert into Venue values('ad1','city1');
insert into Venue values('ad2','city1');
insert into Venue values('ad3','city2');

insert into Sports values('swimming',1);
insert into Sports values('driving',2);

insert into SportsEvents values(1,1);
insert into SportsEvents values(2,1);
insert into SportsEvents values(3,2);

insert into Results values(1, 1, 1, GETDATE(),'PB')
insert into Results values(2, 2, 1, GETDATE(),'WR')
insert into Results values(3, 1, 3, GETDATE(),'OR')
insert into Results values(2, 2, 2, GETDATE(),null)
insert into Results values(3, 1, 1, GETDATE(),'N/A')

--SELECT * FROM Results

--2)
go
create or alter procedure addResult(@athleteId int ,@sportEventId int, @rank int, @resultType nvarchar(100), @date datetime) as begin
	
	declare @check bit = 0;
	
	IF EXISTS(Select * from Results R where R.athleteId = @athleteId and R.sportEventId = @sportEventId) begin
		set @check = 1
	end

	if @check = 1 begin -- update
		UPDATE Results SET rank=@rank, result=@resultType, result_date=@date where athleteId=@athleteId and @sportEventId=@sportEventId
		return
	end

	INSERT INTO Results values(@athleteId,@sportEventId,@rank,@date,@resultType);
	--add
end

go
--exec addResult 1, 3, 2, 'WR', '01/20/2023'; 

SELECT * FROM SportsEvents;

--3
go
create or alter view view1 as

	SELECT A.name from Athletes A
	where (SELECT COUNT(DISTINCT R.sportEventId) from Results R where A.id = R.athleteId) = (SELECT COUNT(*) from SportsEvents S where S.sportId = 1)
	
go

SELECT * FROM view1;
