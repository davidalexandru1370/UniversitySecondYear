;; 4.Write a function that returns the product of numeric atoms in a list,at any level.

(defun solve (l)
    (cond
        ((numberp l) l)
        ((atom l) 1)
        (t (apply '* (mapcar #'solve l)))
    )
)

(print (solve '(1 2 (3 A (B)) 5)))
