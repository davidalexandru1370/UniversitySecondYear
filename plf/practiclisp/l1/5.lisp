;; c)Write a function to replace each sublist of a list with its last element.
;; A sublist is an element from the first level, which is a list.
;; Example: (a (b c) (d (e (f)))) ==> (a c (e (f))) ==> (a c (f)) ==> (a c f)
;; (a (b c) (d ((e) f))) ==> (a c ((e) f)) ==> (a c f)
;idee: parcurgem pana cand nu mai putem si luam ultimul element
;observatie: daca avem lista in lista, dorim sa parcurgem cdr l in loc de car l (daca exista cdr l) 
(defun getLastElement(l)
    (cond
        ((and (null (cdr l)) (atom (car l))) (car l))
        ((not (null (cdr l))) (getLastElement (cdr l)))
        ((and (listp (car l)) (null (cdr l))) (getLastElement (car l)))
    )
)

(defun solve(l)
    (cond
        ((null l) nil)
        ((atom l) (cons (car l) (solve (cdr l))))
        ((listp (car l)) (cons (getLastElement (car l)) (solve (cdr l))))    
        (t (cons (car l) (solve (cdr l))))
    )
)

(print (getLastElement '(d ((e) (f)))))
(print (getLastElement '(a e s b c d)))
(print (solve '(a (b c) (d ((e) (f))))))