;; Dandu-se o lista liniara sa se adauge in lista un element din N in N.
;; (1 2 3 4 5) 2 7 -> (1 2 7 3 4 7 5)
;; (1 2 3 4 5) 8 7 ->(1 2 3 4 5)
;; (1 2 3 4 5) 4 7 ->(1 2 3 4 7 5)
;; (1 2 3 4 5 6 7 8 9 10) 3 7 -> (1 2 3 7 4 5 6 7 7 8 9 7 10)

(defun myAppend (l1 l2)
  (cond
    ((null l1) l2)
    (t (cons (car l1) (myAppend (cdr l1) l2)))
  )
)

(defun solve(l currentIndex index element result)
    (cond
        ((null l) result)
        ((and (>= currentIndex 1) (equal (mod currentIndex index) 0 )) (solve (cdr l) (+ 1 currentIndex) index element (myAppend result (myAppend (list element) (list (car l))))))
        (t (solve (cdr l) (+ 1 currentIndex) index element (myAppend result (list (car l)))))
    )
)

(defun solveHelper(l index element)
    (solve l 0 index element ())
)

(defun teste()
    (assert
        (and
            (equal '(1 2 7 3 4 7 5) (solveHelper '(1 2 3 4 5) 2 7))
            (equal '(1 2 3 4 5) (solveHelper '(1 2 3 4 5) 8 7))
            (equal '(1 2 3 4 7 5) (solveHelper '(1 2 3 4 5) 4 7))
            (equal '(1 2 3 7 4 5 6 7 7 8 9 7 10) (solveHelper '(1 2 3 4 5 6 7 8 9 10) 3 7 ))
        )
    )
)

(teste)