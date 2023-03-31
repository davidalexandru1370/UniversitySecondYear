;; 6.Write a function that returns the maximum of numeric atoms in a list,at any level.

(defun solve(l)
    (cond
        ((numberp l) l)
        ((atom l) -99999)
        (t (apply #'max (mapcar 'solve l)))
    )
)

(print (solve '(5 7 (3 11 (2 (13))) 8))) 