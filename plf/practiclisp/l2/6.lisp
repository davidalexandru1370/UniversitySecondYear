;; 6. Return the list of nodes of a tree of type (1) accessed inorder.


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

(defun inorderTraversal(l)
    (cond
        ((null l) nil)
        (t (append (inorderTraversal (left l)) (list (car l)) (inorderTraversal (right l))))
        ;; ((checkExistenceRight l elem) (cons (car l) (getPath (rightTraverse (cddr l) 0 0) elem)))
        ;; ((checkExistenceLeft l elem) (cons (car l) (getPath (leftTraverse (cddr l) 0 0) elem)))
    )
)

(print (inorderTraversal '(A 2 B 0 C 2 D 0 E 0)))