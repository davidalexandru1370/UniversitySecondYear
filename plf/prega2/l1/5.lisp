;; 5. Write a function that computes the sum of even numbers and the decrease the sum of odd numbers, 
;; at any level of a list.

(defun solve(l)
    (cond
        ((and (numberp l) (equal (mod l 2) 0)) l)
        ((and (numberp l) (equal (mod l 2) 1)) (* -1 l))
        ((atom l) 0)
        (t (apply '+ (mapcar #'solve l)))
    )
)

(print (solve '(1 2 (3 4 A (B C) 2))))