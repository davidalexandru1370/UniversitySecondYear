;; 2. Return the list of nodes on the k-th level of a tree of type (1).

(defun traverseRight(l nrNoduri nrMuchii)
    (cond
        ((null l) nil)
        ((equal nrMuchii (- nrNoduri 1)) nil)
        (t (cons (car l) (cons (cadr l) (traverseRight (caddr l) (+ 1 nrNoduri) (+ (cadr l) nrMuchii)))))
    )
)

(defun traverseLeft(l nrNoduri nrMuchii)
    (cond
        ((null l) nil)
        ((equal nrMuchii (- nrNoduri 1)) l)
        (t (traverseRight (cdr l) (+ 1 nrNoduri) (+ (cadr l) nrMuchii)))
    )
)

(defun right(l)
    (traverseRight l 0 0)
)

(defun left(l)
    (traverseLeft l 0 0)
)

(defun solve(l level currentLevel)
    (cond
        ((null l) nil)
        ((equal level currentLevel)  (car l))
        (t (cons (solve (right (cddr l)) level (+ currentLevel 1)) (solve (left (cddr l)) level (+ currentLevel 1))))
))

(print (solve '(A 2 B 0 C 2 D 0 E 0) 1 0))