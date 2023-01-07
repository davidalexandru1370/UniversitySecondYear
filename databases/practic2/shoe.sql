--CREATE DATABASE Shoes;
go
use Shoes;

if OBJECT_ID('Transactions','U') is not null
	drop table Transactions

if OBJECT_ID('ShopPresentations','U') is not null
	drop table ShopPresentations

if OBJECT_ID('Shoe','U') is not null
	drop table Shoe

if OBJECT_ID('ShoeModel','U') is not null
	drop table ShoeModel

if OBJECT_ID('Presentation','U') is not null
	drop table Presentation

if OBJECT_ID('Women','U') is not null
	drop table Women

go
CREATE TABLE Presentation(
	id int not null IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(100),
	city nvarchar(100)
)

CREATE TABLE Women(
	id int not null IDENTITY(1,1) PRIMARY KEY,
	maximumAmountToSpend int not null default 0
)

CREATE TABLE ShoeModel(
	id int not null IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(100) unique not null,
	season nvarchar(100) not null
)

CREATE TABLE Shoe(
	id int not null IDENTITY(1,1) PRIMARY KEY,
	price int not null,
	shoeModelId int not null FOREIGN KEY REFERENCES ShoeModel(id),
)

CREATE TABLE ShopPresentations(
	presentationId int not null FOREIGN KEY REFERENCES Presentation(id),
	shoeId int not null FOREIGN KEY REFERENCES Shoe(id),
	availableShoes int not null,
	PRIMARY KEY (presentationId,shoeId),
)

CREATE TABLE Transactions(
	id int not null IDENTITY(1,1) PRIMARY KEY,
	shoeId int not null FOREIGN KEY REFERENCES Shoe(id),
	womenId int not null FOREIGN KEY REFERENCES Women(id),
	amount int not null,
)

INSERT INTO Presentation values('prezentare1','zalau');
INSERT INTO Presentation values('prezentare2','mirsid');
INSERT INTO Presentation values('prezentare3','jibou');


INSERT INTO WOMEN values(1000);
INSERT INTO WOMEN values(2000);
INSERT INTO WOMEN values(3000);

INSERT INTO ShoeModel values('model1','vara')
INSERT INTO ShoeModel values('model2','vara')
INSERT INTO ShoeModel values('model3','iarna')

INSERT INTO Shoe(price,shoeModelId) values(50,1)
INSERT INTO Shoe(price,shoeModelId) values(150,2)
INSERT INTO Shoe(price,shoeModelId) values(250,3)
INSERT INTO Shoe(price,shoeModelId) values(200,2)

INSERT INTO ShopPresentations values(1,1,10);
INSERT INTO ShopPresentations values(1,3,10);
INSERT INTO ShopPresentations values(2,2,100);
INSERT INTO ShopPresentations values(2,3,140);
INSERT INTO ShopPresentations values(2,1,90);
INSERT INTO ShopPresentations values(3,1,60);
INSERT INTO ShopPresentations values(3,2,90);

INSERT INTO Transactions values(1, 1, 3)
INSERT INTO Transactions values(1, 2, 1)
INSERT INTO Transactions values(2, 1, 5)
INSERT INTO Transactions values(3, 1, 1)

--task 1

--task 2
