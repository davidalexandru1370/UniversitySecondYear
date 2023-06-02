
--create database practic1;
go
use practic1;
go

drop table Comments;
drop table Posts;
drop table Likes;
drop table Pages;
drop table Category;
drop table Users;


create table Users(
	id int primary key identity(1,1),
	name nvarchar(100),
	city nvarchar(100),
	date_of_birth date
)
INSERT INTO Users values('david','zalau','01/01/2002');
INSERT INTO Users values('david2','oradea','01/01/2002');
INSERT INTO Users values('david3','salonta','01/01/2002');

create table Category(
	id int primary key identity(1,1),
	name nvarchar(100),
	description nvarchar(100),
)


create table Pages(
	id int primary key identity(1,1),
	name nvarchar(100),
	categoryId int foreign key references Category(id),
)

create table Likes(
	userId int foreign key references Users(id),
	pageId int foreign key references Pages(id),
)

create table Posts(
	id int primary key identity(1,1),
	userId int,
	date date,
	text nvarchar(100),
	numberOfShares int,
	CONSTRAINT FK_Posts_User foreign key (userId) references Users(id)
)

create table Comments(	
	id int primary key identity(1,1),
	postId int foreign key references Posts(id),
	text nvarchar(100),
	date date,
	isTopComment bit,
)
