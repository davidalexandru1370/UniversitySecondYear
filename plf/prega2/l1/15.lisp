;; 15. Write a function that reverses a list together with all its sublists elements, at any level

(defun reverseList(l)
    (cond
        ((null l) nil)
        (t (append (reverseList (cdr l)) (list (car l))))
    )
)

(defun solve(l)
    (cond
        ((atom l) l)
        (t  (mapcar #'solve (reverseList l)))
    )
)

(print (solve '(1 2 3 (4 5 6 (7 8 9) 10) 11)))