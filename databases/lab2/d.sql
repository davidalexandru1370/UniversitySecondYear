use DrivingExams21;

--d. 4 queries with INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN (one query per operator); one query will join 
--at least 3 tables, while another one will join at least two many-to-many relationships;

--get all data about students
Select  
	Carplate, 
	InstructorCNP, 
	PE.CandidateCNP,
	PE.SupervisorCNP, 
	PE.CandidateScore as PracticalExam, 
	TE.CandidateScore as TheoreticalExam,
	SDL.Category 
from Vehicles V
	INNER JOIN PracticalExams PE on CarId = V.Id
	INNER JOIN TheoreticalExams TE on PE.CandidateCNP= TE.CandidateCNP 
	INNER JOIN StudentsDrivingLicenses SDL on SDL.CNP = PE.CandidateCNP


SELECT 
	distinct
	SDL.CNP as StudentCNP,
	PE.CandidateScore as TheoreticalExamScore,
	SVDL.CNP as SupervisorCNP
	from StudentsDrivingLicenses SDL
	FULL JOIN PracticalExams PE ON PE.CandidateCNP= SDL.CNP
	FULL JOIN SupervisorsDrivingLicenses SVDL ON PE.SupervisorCNP = SVDL.CNP


SELECT
	S.StartingDate,
	S.CNP,
	S.Name,
	TE.ExamDate
	from Students S
	LEFT JOIN TheoreticalExams TE ON TE.CandidateCNP = S.CNP

SELECT 
	S.CNP,
	S.StartingDate,
	S.Name,
	TE.ExamDate
	from Students S
	RIGHT JOIN TheoreticalExams TE ON TE.CandidateCNP = S.CNP
	order by TE.ExamDate