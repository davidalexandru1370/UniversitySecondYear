;; 7. Write a function that substitutes an element E with all elements of a list L1 at all levels of a given list 
;; L

(defun myAppend(l1 l2)
    (cond
        ((null l1) l2)
        (t (cons (car l1) (myAppend (cdr l1) l2)))
    )
)


(defun solve(l1 e l2)
    (cond
        ((equal l1 e) l2)
        ((atom l1) l1)
        (t (mapcar #' (lambda (l) (solve l e l2)) l1))
    )
)

(print (solve '(1 2 3 ( 4 5 3) 3) 3 '(5 6)))