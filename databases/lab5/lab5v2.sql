--Work on 3 tables of the form Ta(aid, a2, �), Tb(bid, b2, �), Tc(cid, aid, bid, �), where:

--aid, bid, cid, a2, b2 are integers;
--the primary keys are underlined;
--a2 is UNIQUE in Ta;
--aid and bid are foreign keys in Tc, referencing the primary keys in Ta and Tb, respectively.
use DrivingExams21;

--Vehicles (Ta)
--Practical Exams(Tb),
--Results(Tc),  

--a. Write queries on Ta such that their execution plans contain the following operators:

--clustered index scan;
--clustered index seek;
--nonclustered index scan;
--nonclustered index seek;
--key lookup.
--Create nonclustered index vehiclesCarPlateIndex on Vehicles(CarPlate)
SELECT * from Vehicles;
select id from Vehicles order by CarPlate; -- clustered index scan
select CarPlate from Vehicles where id = 2; --clustered index seek
select id from Vehicles; --non clustered index scan 
Select CarChasis from Vehicles where CarChasis > 5 -- non clustered index seek
Select CarPlate from Vehicles Where CarChasis = 2 -- key lookup