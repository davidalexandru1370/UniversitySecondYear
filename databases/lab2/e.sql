use DrivingExams21;

--e. 2 queries with the IN operator and a subquery in the WHERE clause;
--in at least one case, the subquery must include a subquery in its own WHERE clause;

Select * from Instructors I where I.CNP IN
	(Select InstructorCNP from Vehicles group by InstructorCNP having count(*) >= 2)
	order by I.Name


SELECT * from StudentsDrivingLicenses S where S.CNP in 
	(
	Select T.CandidateCNP from TheoreticalExams T where T.CandidateCNP in 
		(
			Select P.CandidateCNP from PracticalExams P where p.CandidateScore <= 20
		)
	)