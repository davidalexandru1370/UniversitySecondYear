;c)Write a function that, with a list given as parameter, inverts only continuous sequences of atoms.
;Example:(a b c (d (e f) g h i)) ==> (c b a (d (f e) i h g))

;; (defun solve(l)
;;     (cond
;;         ((null l) nil)
;;         ((listp (car l)) (append (solve (cdr l)) (list (car l))))
;;         (t (append (solve (cdr l )) (list (car l))))
;;     )
;; )

;; (defun solve(l)
;;     (cond
;;         ((null l) nil)
;;         ((listp (car l)) (append (solve (cdr l)) (list (solve (car l)))))
;;         ((atom (car l)) (solve (cdr l)) (append () (list (car l))))
;;     )
;; )

(defun solve(l aux)

    (cond 
        ((null l) aux)
        ((listp (car l)) (append aux (append (list (solve (car l) nil)) (solve (cdr l) nil))))
        (t (solve (cdr l) (append (list (car l)) aux)))
    )
)

(print (solve '(a b c (d (e f) g h i)) ()))
(print (solve '(a b c d e) ()))