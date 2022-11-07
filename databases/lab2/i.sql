--i. 4 queries using ANY and ALL to introduce a subquery in the WHERE clause
--(2 queries per operator); rewrite 2 of them with aggregation operators,
--and the other 2 with IN / [NOT] IN.

use DrivingExams21;

SELECT S.CNP as 'Student CNP', S.Name as 'Student Name', I.Name as 'Instructor Name' from Students S
INNER JOIN Instructors I ON S.InstructorCNP = I.CNP
WHERE InstructorCNP = ANY (Select CNP from InstructorDetails Where CertificationExpiration < GETDATE())

SELECT * FROM InstructorDetails ID
INNER JOIN Instructors I on I.CNP = ID.CNP

