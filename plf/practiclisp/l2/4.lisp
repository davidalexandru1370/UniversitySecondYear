;; 4.Convert a tree of type (2) to type (1).
;; (A (B) (C (D) (E))) -> (A 2 B 0 C 2 D 0 E 0)
;;     A
;;     /\
;;    B  C
;;       /\
;;      D  E

(defun solve(l)
    (cond
        ((null l) nil)
        ((null (cadr l)) (append (list (car l)) (list 0)))
        ((null (caddr l)) (append (list (car l)) (list 1) (solve (cadr l))))
        (t (append (list (car l)) (list 2) (solve (cadr l)) (solve (caddr l))))
    )
)

(print (solve '(A (B) (C (D) (E)))))