use DrivingExams21;

--g. 2 queries with a subquery in the FROM clause;                         

SELECT t.CandidateScore from (Select * from PracticalExams)t  


SELECT k.CandidateScore, k.PracticalExamScore from (SELECT P.CandidateScore, T.CandidateScore as PracticalExamScore from PracticalExams P inner join TheoreticalExams T ON T.CandidateCNP = P.CandidateCNP)k order by k.CandidateScore;