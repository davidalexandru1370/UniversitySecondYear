use DrivingExams21;

--f. 2 queries with the EXISTS operator and a subquery in the WHERE clause;
--select instructors with students who failed/passed the theoretical exam
Select distinct InstructorCNP from Students S where exists(Select * from TheoreticalExams P where P.CandidateCNP=S.CNP)

--select the cars who passed the driving exam
Select CarPlate from Vehicles V where exists(Select * from PracticalExams P where P.CarId=V.Id and p.CandidateScore<=21)

