;; 1. Write a function to check if an atom is member of a list (the list is non-liniar)
(defun solve(l e)
    (cond
        ((and (atom l) (equal l e)) T)
        ((atom l) nil)
        (t (some #'identity (mapcar (lambda (x) (solve x e)) l)))
    )
)

(print (solve '(A B C) 'A))