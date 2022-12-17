;6. Write a function that returns the maximum of numeric atoms in a list, at any level.

;; (defun myMax(a b)
;;     (cond 
;;     ((and (not (numberp a)) (not (numberp b))) nil)
;;     ((not (numberp a)) b)
;;     ((not (numberp b)) a)
;;     ((> a b) a)
;;     (t b)
;;     )
;; )

;; (defun solve (l)
;;     (cond 
;;     ((null l) nil)
    
;;     (t (myMax (car l) (maxList (cdr l))))
;;     )
;; )



;; (defun maxList (l)
;;     (cond
;;         ((null l) NIL)
;;         (T (mapcar #'myMax (car l) (car (cdr l))))
;;     )
;; )

(defun solve(l)
    (cond
        (T (apply #'max (liniarize l)))
    )
)

(defun liniarize (l)
    (cond
    ((null l) NIL)
    ((numberp (car l)) (cons (car l) (liniarize (cdr l))))
    ((listp (car l)) (append (MAPCAN #'liniarize (list (car l))) (MAPCAN #'liniarize (list (cdr l)))))
    (T (liniarize (cdr l)))
    )
)

(print (liniarize '((1) 2 (3 4) A B (5 (6 7)) 8)))

(defun testMaxList ()
    (assert 
    (and
        (equal 19 (solve '((19) A 2 3 (5 6) (2 (18)))))
        (equal 10 (solve '(-2 -3 (-10) 2 (10 (9 1 (3)) 4))))
        (equal -1 (solve '(-5 -6 (-10 (-8 -2) -1))))
        (equal -10 (solve '(B A (-10 (-83 -22) -12 C))))
    ))
)
(testMaxList)
;(print (maxList '((19) 10 2 3 (5 6) (2 (18)))))