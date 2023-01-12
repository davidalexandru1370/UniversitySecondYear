;; 2. Return the list of nodes on the k-th level of a tree of type (1).
;;     A
;;     /\
;;    B  C
;;       /\
;;      D  E

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


(defun solve(l level currentLevel)
    (cond
        ((null l) nil)
        ((equal level currentLevel  ) (list (car l)))
        (t (append (solve (right l) level (+ currentLevel 1)) (solve (left l) level (+ currentLevel 1))))
))

(print (solve '(A 2 B 0 C 2 D 0 E 0) 2 0))