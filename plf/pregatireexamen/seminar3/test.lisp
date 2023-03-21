;; (defun G(l)
;;     (mapcon #'LIST l)
;; )
;; ;; (print (append '(1 2 3) '(1 2)))
;; (print (G '(1 2 3 4)))
;; (print (mapcon #'G '(1 2 3 4)))
;; (print (apply #'append (mapcon #'G '(1 2 3 4))))

;; (defun G(L)
;;     (LIST (car l) (car l))
;; )

;; (setq Q 'G)
;; (setq P Q)
;; (print (FUNCALL P '(A B C)))


;; (defun F(X &REST Y)
;;     (cond 
;;         ((null Y) X)
;;         (T (APPEND X (MAPCAR #'CAR Y)))
;;     )
;; )
;; (print (F '(1 2) '(3 4 5 6) '(8 9 10) '(11 12 13)))
;; (print (append (F '(1 2)) (F '(3 4) '(5 6) '(7 8))))

;; (defun G(f l)
;;     (print f )
;;     (funcall f l)
;; )

;; (print (G #'(lambda (l) (G #'CDR l)) '(1 2 3)))


(defun solve(l)
    (cond
        ((and (numberp l) (equal (mod l 2) 0)) (+ 1 l))
        ((atom l) l)
        (t (mapcar #'solve l))
    )
)

(print (solve '(1 s 4 (2 f (7)))))