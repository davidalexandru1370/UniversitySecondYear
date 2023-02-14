;; 9. Write a function that removes all occurrences of an atom from any level of a list.

(defun solve (l e)
    (cond
        ((and (atom l) (eq l e)) nil)
        ((atom l) (list l))
        (t (list (mapcan (lambda (x) (solve x e)) l)))
    )
)

(defun mainSolve(l e)
    (cond
    (t (car (solve l e)))
    )
)

(print (mainSolve '(1 2 3 (4 3 (6) 3) 2 3) 3))
(print (mainSolve '(1 2 3 4 3 5 3 3) 3))