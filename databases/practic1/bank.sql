--CREATE DATABASE Bank;
go
use Bank;
if OBJECT_ID('Transactions','U') is not null
	drop table Transactions

if OBJECT_ID('Card','U') is not null
	drop table Card

if OBJECT_ID('BankAccounts','U') is not null
	drop table BankAccounts

if OBJECT_ID('Customers','U') is not null
	drop table Customers

if OBJECT_ID('ATM','U') is not null
	drop table ATM

create table Customers(
	id int  identity (1,1) not null PRIMARY KEY,
	name nvarchar(100) not null,
	date_of_birth datetime not null,
)

create table BankAccounts(
	IBAN nvarchar(100) not null PRIMARY KEY,
	balance int not null default 0,
	customerId int FOREIGN KEY REFERENCES Customers(id) ON DELETE CASCADE
)

create table Card(
	id int not null primary key IDENTITY(1,1),
	bankAccountId nvarchar(100) FOREIGN KEY REFERENCES BankAccounts(IBAN),
	cvv int not null,
)

create table ATM(
	id int not null primary key IDENTITY(1,1),
	address nvarchar(100)
)

create table Transactions(
	id int not null primary key IDENTITY(1,1),
	atmId int not null FOREIGN KEY REFERENCES ATM(id) ON DELETE CASCADE,
	moneyWithdrawed int,
	cardId int FOREIGN KEY REFERENCES Card(id) ON DELETE Cascade,
	operationDate datetime not null
)

INSERT INTO Customers(name, date_of_birth) values('david', '2002/06/20')
INSERT INTO Customers(name, date_of_birth) values('alexandru', '2002/07/19')
INSERT INTO Customers(name, date_of_birth) values('david alexandru', '2003/06/23')

INSERT INTO BankAccounts(IBAN,balance,customerId) values('iban2',1234,1)
INSERT INTO BankAccounts(IBAN,balance,customerId) values('iban3',1233,2)
INSERT INTO BankAccounts(IBAN,balance,customerId) values('iban1',123,3)

INSERT INTO Card(bankAccountId,cvv) values('iban1',388)
INSERT INTO Card(bankAccountId,cvv) values('iban2',489)
INSERT INTO Card(bankAccountId,cvv) values('iban3',985)

INSERT INTO ATM(address) values('piata cipariu')
INSERT INTO ATM(address) values('galeriile meses')

INSERT INTO Transactions(atmId,moneyWithdrawed,cardId,operationDate) values(1,10,1,SYSDATETIME())
INSERT INTO Transactions(atmId,moneyWithdrawed,cardId,operationDate) values(1,10,2,SYSDATETIME())
INSERT INTO Transactions(atmId,moneyWithdrawed,cardId,operationDate) values(2,10,1,SYSDATETIME())
INSERT INTO Transactions(atmId,moneyWithdrawed,cardId,operationDate) values(2,10,2,SYSDATETIME())
INSERT INTO Transactions(atmId,moneyWithdrawed,cardId,operationDate) values(1,10,1,SYSDATETIME())
INSERT INTO Transactions(atmId,moneyWithdrawed,cardId,operationDate) values(1,10,3,SYSDATETIME())
INSERT INTO Transactions(atmId,moneyWithdrawed,cardId,operationDate) values(1,15,3,SYSDATETIME())

go
create or alter procedure deleteTransactionByCardId(@cardId int) as begin
	if not exists(SELECT * from Card C where C.id=@cardId) begin
		print('invalid card')
		return
	end
	DELETE  FROM Transactions  where cardId=@cardId

end

SELECT * FROM Transactions;
--EXEC deleteTransactionByCardId 1

go
create or alter view task2 as
		--SELECT DISTINCT C.id as CardId,T.atmId from Card C
		--inner join Transactions T on T.cardId = C.id
		--group by C.id,T.atmId
		--having COUNT(*) = (SELECT COUNT(*) from ATM)
		SELECT C.id FROM Card C
		where (SELECT COUNT(distinct T.atmId) from Transactions T
			inner join ATM A on T.atmId = A.id where T.cardId = C.id) = (SELECT COUNT(*) FROM ATM) 
go
SELECT * From task2
		
go
create or alter function task3()
returns table as
	return SELECT C.cvv, C.id FROM Card C
	inner join Transactions T on C.id = T.id
	group by C.id,C.cvv
	having SUM(T.moneyWithdrawed) > 2

go
Select * from task3()
