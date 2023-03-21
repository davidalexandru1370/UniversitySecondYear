;; 12. Write a function that substitutes an element through another one at all levels of a given list.


(defun solve(l e1 e2)
    (cond
        ((equal l e1) e2)
        ((atom l ) l)
        (t (mapcar (lambda (x) (solve x e1 e2)) l))
    )
)

(print (solve '(1 2 3 (4 5 3) (3 2 (3 4 (3 5)) 6)) 3 15))