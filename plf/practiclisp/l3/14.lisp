;; 14.Write a function that returns the number of atoms in a list, at anylevel.


(defun solve(l)
    (cond
        ((atom l) 1)
        (t (apply '+ (mapcar #'solve l)))
    )
)

(print (solve '(1 2 3 (A B (C D) E))))