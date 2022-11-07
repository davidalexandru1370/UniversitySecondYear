--h. 4 queries with the GROUP BY clause, 3 of which also contain the HAVING clause;
--2 of the latter will also have a 
--subquery in the HAVING clause; use the aggregation operators: 
--COUNT, SUM, AVG, MIN, MAX;
use DrivingExams21;

SELECT  V.InstructorCNP,COUNT(V.InstructorCNP) as 'Total Cars' from Vehicles V GROUP BY V.InstructorCNP order by COUNT(V.InstructorCNP) DESC

