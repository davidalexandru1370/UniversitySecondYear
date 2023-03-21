
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

;; (defun myMax (a b)
;;     (cond
;;     ((null a) b)
;;     ((null b) a)
;;     ((> a b) a)
;;     (T b)
;;     )
;; )

;; (defun solve(l)
;;     (cond
;;         (T (apply #'max (liniarize l)))
;;     )
;; )



;6. Write a function that returns the maximum of numeric atoms in a list, at any level.
;0 - maxim 1 - minim
;flow model myMax(a - number  b -number) (i,i) (o,o)
(defun myMax(a b)
    (cond 
    ((> a b) a)
    (t b)
    )
)

(defun myMin (a b)
    (cond
    ((> a b) b)
    (T a)
    )
)

;flow model getMax(l - list) (i)
(defun computeMinOrMax (l dir)
    (cond
        ((null l) -999999)
        ((numberp l) l)
        ((atom l) -999999)
        ((equal dir 0) (apply #'max (mapcar (lambda (l2) (computeMinOrMax l2 dir)) l)))
        ((equal dir 1) (apply #'min (mapcar (lambda (l2) (computeMinOrMax l2 dir)) l)))
    )
)

;; (defun getMin (l)
;;     (cond
;;     ((null l) 999999)
;;     ((numberp l) l)
;;     (T
;;       (apply #'myMin (car l) (mapcar #'getMin (list (cdr l))))  
;;     )
;;     )
;; )

(defun solve (l dir) 
    (cond
    ;; ((equal dir 0) (getMax (liniarize l)))
    ;; (T (getMin (liniarize l)))
    (T (computeMinOrMax l dir))
    )
)

;flow model liniarize(l - list) (i)
(defun liniarize (l)
    (cond
    ((null l) NIL)
    ((numberp (car l)) (cons (car l) (MAPCAN #'liniarize (list (cdr l)))))
    ((listp (car l)) (append (MAPCAN #'liniarize (list (car l))) (MAPCAN #'liniarize (list (cdr l)))))
    (T (liniarize (cdr l)))
    )
)
 (print  (solve '((19 16) A D C (5 6) (2 (18))) 0))
(defun testMaxList ()
    (assert 
    (and
        (equal 19 (solve '((19 16) A D C (5 6) (2 (18))) 0))
        (equal 10 (solve '(-2 -3 (-10) 2 (10 (9 1 (3)) 4)) 0))
        (equal -1 (solve '(-5 -6 (-10 (-8 -2) -1)) 0))
        (equal -10 (solve '(B A (-10 (-83 -22) -12 C)) 0))
    ))
)

(defun testMinList ()
    (assert
    (and 
        (equal 2 (solve '((19 16) A D C (5 6) (2 (18))) 1))
        (equal -10 (solve '(-2 -3 (-10) 2 (10 (9 1 (3)) 4)) 1))
        (equal -10 (solve '(-5 -6 (-10 (-8 -2) -1)) 1))
        (equal -83 (solve '(B A (-10 (-83 -22) -12 C)) 1))
    ))
)
(testMaxList)
(testMinList)