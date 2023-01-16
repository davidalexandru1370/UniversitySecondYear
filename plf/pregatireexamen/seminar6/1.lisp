;; 1.Compute the number of nodes from the even levelsof an n-ary tree, represented as (root (subtree_1) (subtree_2) ... (subtree_n)).
;; The level of the root is 1.
;; For example, for the tree (A (B (D (G) (H)) (E (I))) (C (F (J (L)) (K)))) the result is 7.

(defun countEven(l level)
    (cond
        ((and (atom l) (equal (mod level 2) 0)) 1)
        ((atom l) 0)
        (t (apply '+ (mapcar (lambda (x) (countEven x (+ 1 level))) l)))
    )
)

(print (countEven '(A (B (D (G) (H)) (E (I))) (C (F (J (L)) (K)))) 0))