;Write a function to transform a linear list into a set.

(defun member2(x l)
    (cond
        ((null l) nil)
        ((eq x (car l)) t)
        (t (member2 x (cdr l)))
    )
)


(defun listToSet(l1 l2)
    (cond
        ((null l1) l2)
        ((not (member2 (car l1) l2)) (listToSet (cdr l1) (append l2 (list (car l1)))))
        (t (listToSet (cdr l1) l2))
    )
)

(print (listToSet '(1 1 2 2 3 3 4 5 6 6) ()))