;; Return the level of a node X in a tree of type (1). The level of the root element is 0.

(defun traverseLeft(l nrNoduri nrMuchii)
    (cond
        ((null l) nil)
        ((equal nrMuchii (- nrNoduri 1)) nil)
        (t (cons (car l) (cons (cadr l) (traverseLeft (caddr l) (+ 1 nrNoduri) (+ (cadr l) nrMuchii)))))
    )
)

(defun traverseRight(l nrNoduri nrMuchii)
    (cond
        ((null l) nil)
        ((equal nrMuchii (- nrNoduri 1)) l)
        (t (traverseRight (cddr l) (+ 1 nrNoduri) (+ (cadr l) nrMuchii)))
    )
)

(defun right(l)
    (traverseRight (cddr l) 0 0)
)

(defun left(l)
    (traverseLeft (cddr l) 0 0)
)

(defun solve(l level node)
(cond
    ((null l) -1)
    ((equal (car l) node) level)
    (t (max (solve (right l) (+ 1 level) node) (solve (left l) (+ 1 level) node)))
))

(print (solve '(A 2 B 0 C 2 D 0 E 0) 0 'B))
