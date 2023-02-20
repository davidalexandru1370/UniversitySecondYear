--create database teste

create table a(
	id int primary key,
	c1 nvarchar(100),
	c2 int,
)

create table z(
	id int primary key,
	c1 nvarchar(100),
	c2 int,
)

SELECT * FROM a;

insert into a values(1,'a',10);
insert into a values(2,'b',5);
insert into a values(3,'c',30);
insert into a values(4,'a',24);
insert into a values(9,'b',11);
insert into a values(2,'c',2);
insert into a values(10,'b',0);

DELETE  FROM Z;

insert into z values(6,'a',4);
insert into z values(2,'b',54);
insert into z values(3,'c',0);
insert into z values(7,'a',43);
insert into z values(4,'b',12);
insert into z values(8,'c',13);
insert into z values(5,'b',32);

GO
SELECT 
	SUM(CASE WHEN A_ID IS NOT NULL AND Z_ID IS NULL THEN 1 ELSE 0 END) AS F1,
	SUM(CASE WHEN Z_ID IS NOT NULL AND A_ID IS NULL THEN 1 ELSE 0 END) AS F2, 
	SUM(CASE WHEN Z_ID IS NOT NULL AND A_ID IS NOT NULL THEN 1 ELSE 0 END) AS F3
FROM (SELECT DISTINCT A.ID AS A_ID, z.id AS Z_ID FROM A LEFT JOIN Z ON A.id=Z.id) T

go
CREATE OR ALTER TRIGGER TriggerOnUpdate ON Z for update as
	declare @n int = 0
	SELECT * FROM deleted
	select * FROM inserted
	select @n = @n + ABS(i.c2 - d.c2)
	FROM deleted d
	inner join inserted i
	on d.Id = i.id
	print @n


update z
set c2=10
where Id +c2 > 25