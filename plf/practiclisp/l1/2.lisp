;b) Write a function to get from a given list the list of all atoms, on any level, but reverse order. 
;Example:(((A B) C) (D E)) ==> (E D C B A)

(defun solve(l1)
    (cond
    ((null l1) nil)
    ((listp (car l1)) (append (solve (cdr l1)) (solve (car l1))))
    (t (append (solve (cdr l1)) (list (car l1))))
    )
)

;(print (solve '((((A B) C) D E))))

;c) Write a function that returns the greatest common divisor of all numbers in a nonlinear list.

(defun mygcd(a b)
    (cond
    ((eq a b) a)
    ((> a b) (gcd (- a b) b))
    (t (gcd a (- b a)))
    )
)

(defun solve2(l)
    (cond 
    ( (null (cdr l)) (car l))
    ((listp (car l)) (mygcd (solve2 (cdr l)) (solve2 (car l))))
    (t (mygcd (car l) (solve2 (cdr l))))
    )
)

(print (solve2 '(18 3 6)))