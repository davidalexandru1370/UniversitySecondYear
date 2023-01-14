;; c)Write a function to compute the result of an arithmetic expression memorised in preorder on a stack.
;;  Examples:(+ 1 3) ==> 4  (1 + 3)
;; (+ * 2 4 3) ==> 11  [((2 * 4) + 3)
;; (+ * 2 4 -5 * 2 2) ==> 9  ((2 * 4) + (5 -(2 * 2))

(defun solve(stack)
    (cond
    ((null (cdddr stack)) 
    ( '(car stack) (cadr stack) (caddr stack)))
    (t (solve (cdr stack)))    
    )
)

(print (solve '(+ 1 3)))