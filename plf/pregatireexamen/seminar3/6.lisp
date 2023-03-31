;; D. Un arbore n-ar se reprezintă în LISP astfel (nod subarbore1 subarbore2 .....). Se cere să se verifice dacă un nod x apare pe
;; un nivel par în arbore. Nivelul rădăcinii se consideră a fi 0. Se va folosi o funcție MAP.
;; Exemplu pentru arborele (a (b (g)) (c (d (e)) (f)))
;; a) x=g => T b) x=h => NIL

(defun sau(l)
    (cond
        ((null l) nil)
        (t (or (car l) (sau (cdr l))))
    )
)

(defun solve(l level x)
    (cond
        ((and (atom l) (equal l x) (equal (mod level 2) 0)) t)
        ((atom l) nil)
        (t (funcall #'sau(mapcar #'(lambda (l2) (solve l2 (+ 1 level) x)) l)))
    )
)

(print (solve '(a (b (g)) (c (d (e)) (f))) -1 'g))