;b) Write a function to get from a given list the list of all atoms, on any level, but reverse order. 
;Example:(((A B) C) (D E)) ==> (E D C B A)

(defun solve(l1)
    (cond
    ((null l1) nil)
    ((listp (car l1)) (append (solve (cdr l1)) (solve (car l1))))
    (t (append (solve (cdr l1)) (list (car l1))))
    )
)

(print (solve '((((A B) C) D E))))