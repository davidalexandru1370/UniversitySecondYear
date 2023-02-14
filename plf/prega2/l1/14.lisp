;; 14. Write a function that returns the number of atoms in a list, at any level.


(defun solve(l)
    (cond
        ((atom l) 1)
        (t (apply '+ (mapcar #'solve l)))
    )
)

(print (solve '(1 2 3 (4 5 3) (3 2 (3 4 (3 5)) 6))))

