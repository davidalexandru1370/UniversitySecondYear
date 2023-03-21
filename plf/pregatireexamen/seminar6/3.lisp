;; a)Write a function that determines if e is an element of a non-linear list(use logical OR).

(defun is_member (l e)
    (cond
        ((and (atom l) (equal l e)) t)
        ((atom l) nil)
        (t (some #'identity (mapcar #' (lambda (a) (is_member a e)) l)))
    )
)

(print (is_member '(1 2 (8 7) 3 4) 7))

;; b)Write a function that determines if all elements in anon-linear listare equal to e(use logical AND).

(defun all_like (l e)
    (cond
        ((and (atom l) (equal l e)) t)
        ((atom l) nil)
        (t (every #'identity (mapcar #'(lambda (a) (all_like a e)) l)))
    )
)

(print (all_like '(1 2 3) 2))
(print (all_like '(2 2 2) 2))
