;; 1.Write a function to check if an atom is member ofa list (the list is non-liniar)

(defun solve(l elem)
    (cond
        ((and (atom l) (equal l elem)) t)
        ((atom l) nil)
        (t (some #'identity (mapcar #'(lambda (a) (solve a elem)) l)))
    )
)

(print (solve '(A B C) 'D))