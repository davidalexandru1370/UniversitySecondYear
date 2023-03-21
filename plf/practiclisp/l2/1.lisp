;; 1. For a given tree of type (1) return the path from the root node to a certain given node X.
;; (A 2 B 0 C 2 D 0 E 0)

;; (defun getPath(l X result)
;;     (cond 
;;         ((null l) nil)
;;         ((eq (car l) X) result)
;;         (t 
;;             ()
;;         )
;;     )
;; )
;;     A
;;     /\
;;    B  C
;;       /\
;;      D  E

(defun leftTraverse(l nrNoduri nrMuchii)
    (cond
        ((null l) nil)
        ((= nrMuchii (- nrNoduri 1)) nil)
        (t (cons (car l) (cons (cadr l) (leftTraverse (cddr l) (+ 1 nrNoduri) (+ (cadr l) nrMuchii)))))
    )
)

(defun rightTraverse(l nrNoduri nrMuchii)
    (cond
        ((null l) nil)
        ((= nrMuchii (- nrNoduri 1)) l)
        (t (rightTraverse (cddr l) (+ 1 nrNoduri) (+ (cadr l) nrMuchii)))
    )
)

(defun checkExistence(l elem)
    (cond
        ((null l) nil)
        ((equal (car l) elem) t)
        (t (checkExistence (cdr l) elem))
    )
)

(defun checkExistenceRight(l elem)
    (checkExistence (rightTraverse l 0 0) elem)
)

(defun checkExistenceLeft(l elem)
    (checkExistence (leftTraverse l 0 0) elem)    
)

(defun getPath(l elem)
    (cond
        ((null l) nil)
        ((equal (car l) elem)  elem)
        ((checkExistenceRight l elem) (cons (car l) (getPath (rightTraverse (cddr l) 0 0) elem)))
        ((checkExistenceLeft l elem) (cons (car l) (getPath (leftTraverse (cddr l) 0 0) elem)))
    )
)

(print (getPath '(A 2 B 0 C 2 D 0 E 0) 'D))