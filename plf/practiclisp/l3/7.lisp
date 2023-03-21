;; 7.Write a function that substitutes an element E with 
;; all elements of a list L1 at all levels of a given list L.

(defun solve(l elem l2)
    (cond
        ((and (not (equal l elem)) (atom l)) l)
        ((and (equal l elem) (atom l)) l2)
        (t (mapcar #' (lambda (l) (solve l elem l2)) l))
    )
)

(print (solve '(1 2 3 ( 4 5 3) 3) 3 '(5 6)))