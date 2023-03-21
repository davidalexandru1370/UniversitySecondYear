;; D. Un arbore n-ar se reprezintă în LISP astfel ( nod subarbore1 subarbore2 .....). Se cere să se determine calea de la radăcină
;; către un nod dat. Se va folosi o funcție MAP.
;; Exemplu pentru arborele (a (b (g)) (c (d (e)) (f)))
;; a) nod=e => (a c d e) b) nod=v => ()


(defun cauta(l e)
    (cond
        ((null l) nil)
        ((and (atom (car l)) (equal (car l) e)) T)
        ((listp (car l)) (or (cauta (car l) e) (cauta (cdr l) e)))
        (t (cauta (cdr l) e))
    )
)

(defun drum(l e)
    (cond
        ((AND (atom l) (not (equal l e))) NIL)
        ((and (atom l) (equal l e)) e)
        ((and (listp l) (cauta l e)) (append (list (car l)) (mapcan #'(lambda (x) (drum x e)) l)))
    )
)

(print (drum '(a (b (g)) (c (d (e)) (f))) 'e))