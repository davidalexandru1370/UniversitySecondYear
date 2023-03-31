;; 10. Return the level of a nodeX in a tree of type (2). The level of the root element is 0.

(defun solve (l level node)
    (cond
        ((null l) -1)
        ((equal (car l) node) level)
        (t (max (solve (cadr l) (+ 1 level) node) (solve (caddr l) (+ 1 level) node)))
    )
)

(print (solve '(A (B) (C (D) (E))) 0 'E))