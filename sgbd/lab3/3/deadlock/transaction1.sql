use DrivingExams21
BEGIN TRAN
UPDATE Students set Name = 'transaction1' where CNP='0125282099674'
WAITFOR DELAY '00:00:10'

UPDATE TheoreticalExams SET CandidateScore=26 WHERE CandidateCNP='0125282099674'
COMMIT TRAN