;b)Write a function to determine the successor of a number represented digit by digit as a list,
; without transforming the representation of the number from list to number. Example: (1 9 3 5 9 9) --> (1 9 3 6 0 0)

(defun reverseList(l)
    (cond 
        ((null  (cdr l)) (list (car l)))
        (t (append (reverseList (cdr l)) (list (car l))))
    )
)


(defun increment(l remainder result)
    (cond
        ((null l) result)
        ((eq remainder 0) (increment (cdr l) 0 (append result (list (car l)))))
        (t (increment (cdr l) (truncate (+ (car l) remainder) 10) (append result (list (mod (+ (car l) remainder) 10)))))
    )
)

(defun solve(l)
    (cond
        (t (reverseList (increment (reverseList l) 1 ())))
    )
)
(print (reverseList '(1 9 3 5 9 9)))
(print (solve '(1 9 3 5 9 9)))
(print(truncate 10 5))