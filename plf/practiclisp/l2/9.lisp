;; 9. Convert a tree of type (1) to type (2).


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

(defun solve(l)
    (cond
        ((null l) nil)
        ((equal (cadr l) 2) (append (list (car l)) (cons (solve (left l)) (list (solve (right l))))))
        ((equal (cadr l) 1) (append (list (car l)) (list (solve (left l)))))
        (t (list (car l)))
))

(print (solve '(A 2 B 0 C 2 D 0 E 0)))