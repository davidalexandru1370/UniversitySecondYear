;; 11. Write a function to determine the depth of a list.

(defun solve(l level)
    (cond
    ((atom l) level)
    (t (apply #'max (mapcar (lambda (x) (solve x (+ 1 level))) l)))
))

(print (solve '(1 (2 (3)) (4) (5 (6 (7)))) -1))