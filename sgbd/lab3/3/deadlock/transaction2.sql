
BEGIN TRAN
UPDATE TheoreticalExams SET CandidateScore=23 WHERE CandidateCNP='0125282099674'
WAITFOR DELAY '00:00:10'


UPDATE Students set Name = 'transaction2' where CNP='0125282099674'
Commit tran
