--h. 4 queries with the GROUP BY clause, 3 of which also contain the HAVING clause;
--2 of the latter will also have a 
--subquery in the HAVING clause; use the aggregation operators: 
--COUNT, SUM, AVG, MIN, MAX;
use DrivingExams21;
-- get all vehicles of a instructors
SELECT  V.InstructorCNP,COUNT(V.InstructorCNP) as 'Total Cars' from Vehicles V GROUP BY V.InstructorCNP order by COUNT(V.InstructorCNP) DESC

--get the instructors with greater or equal students than 2
SELECT S.InstructorCNP from Students S GROUP BY S.InstructorCNP having count(S.InstructorCNP) >= 2

--get the instructors which have all categories of driving licenses
SELECT I.CNP, COUNT(*) as 'TOTAL CATEGORIES' from InstructorsDrivingLicenses I
group by I.CNP
having COUNT(*) = (SELECT COUNT(*) FROM Categories);
