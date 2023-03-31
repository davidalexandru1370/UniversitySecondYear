;; 16.Write a function that produces the linear list of all atoms of a given list, from all levels,
;;  and written in the same order.Eg.: (((A B) C) (D E)) --> (A B C D E)

(defun solve (l)
    (cond
        ((and (not (listp l)) (atom l)) (list l))
        (t (apply #'append (mapcar 'solve l)))
    )
)

(print (solve '(((A B) C) (D E))))