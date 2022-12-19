--Work on 3 tables of the form Ta(aid, a2, …), Tb(bid, b2, …), Tc(cid, aid, bid, …), where:

--aid, bid, cid, a2, b2 are integers;
--the primary keys are underlined;
--a2 is UNIQUE in Ta;
--aid and bid are foreign keys in Tc, referencing the primary keys in Ta and Tb, respectively.
use DrivingExams21;
--Results, Practical Exams, Vehicles

--a. Write queries on Ta such that their execution plans contain the following operators:

--clustered index scan;
--clustered index seek;
--nonclustered index scan;
--nonclustered index seek;
--key lookup.


