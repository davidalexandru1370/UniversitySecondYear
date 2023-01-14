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
        ()
    )
)

(print (solve '(A (B) (C (D) (E)))))