--i. 4 queries using ANY and ALL to introduce a subquery in the WHERE clause
--(2 queries per operator); rewrite 2 of them with aggregation operators,
--and the other 2 with IN / [NOT] IN.

use DrivingExams21;

--get students with a instructor with expired certification
SELECT top 5 S.CNP as 'Student CNP', S.Name as 'Student Name', I.Name as 'Instructor Name' from Students S
INNER JOIN Instructors I ON S.InstructorCNP = I.CNP
WHERE InstructorCNP = ANY (Select CNP from InstructorDetails ID Where CertificationExpiration < GETDATE() and ID.CNP = S.InstructorCNP)

--get students with enough lessons for some driving licenses
SELECT S.Name, S.CurrentLesson from Students S where CurrentLesson >= ANY(Select MIN(MandatoryLessons) from Categories)

--get instructors with students who has only students who passed the practical exam from first try
SELECT I.Name,COUNT(*) as Students FROM Instructors I
INNER JOIN Students S ON S.InstructorCNP = I.CNP
INNER JOIN PracticalExams P on S.CNP = P.CandidateCNP
group by P.CandidateCNP,I.Name,S.CNP
HAVING P.CandidateCNP = ALL(SELECT CandidateCNP from PracticalExams P2 where  P2.CandidateScore < 21)


SELECT (Select I.Name from Instructors I where i.CNP = id.CNP) as 'Instructor Name',ID.CNP, CertificationIssued FROM InstructorDetails ID WHERE ID.CertificationIssued >= ALL(SELECT CertificationIssued FROM InstructorDetails)
