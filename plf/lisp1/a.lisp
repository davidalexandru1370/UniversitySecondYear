;a) Write twice the n-th element of a linear list. Example: for (10 20 30 40 50) and n=3 will produce (10 20 30 30 40 50).
;flow model twiceNThElement(l - input list i - current index - n - input index to duplicate value) (i,i,i)
(defun twiceNThElement (l i n)
    (cond
    ((null l) nil)
    ((eq i n) (cons (car l) (cons (car l) (twiceNThElement(cdr l) (+ i 1) n))))
    (t (cons (car l) (twiceNThElement(cdr l) (+ i 1) n)))
    )
)


(defun solve(x n)
    (twiceNThElement x 1 n)
)

(print(solve '(10 20 30 40 50) 3))

;b) Write a function to return an association list with the two lists given as parameters. 
;Example: (A B C) (X Y Z) --> ((A.X) (B.Y) (C.Z)).

(defun associationList (l1 l2)
    (cond
    ((null l1) l2)
    ((null l2) l1)
    (t (cons (cons (car l1) (car l2)) (associationList(cdr l1) (cdr l2))))
    )
)

(print (associationList '(A B C) '(X Y Z)))

;c) Write a function to determine the number of all sublists of a given list, on any level. 
;A sublist is either the list itself, or any element that is a list, at any level. Example: 
;(1 2 (3 (4 5) (6 7)) 8 (9 10)) => 5 lists:
;(list itself, (3 ...), (4 5), (6 7), (9 10)).


(defun determineNumberOfSublists_aux (l)
    (cond
    ((null l) 0)
    ((LISTP (car l)) (+ 1 (determineNumberOfSublists_aux(car l)) (determineNumberOfSublists_aux (cdr l))))
    (T (determineNumberOfSublists_aux (cdr l)))
    )
)

(defun determineNumberOfSublists (l)
    (cond
    ((null l) 0)
    (t (+ 1 (determineNumberOfSublists_aux l)))
    )
)

(print (determineNumberOfSublists '(1 2 (3 (4 5) (6 7)) 8 (9 10))))

;d) Write a function to return the number of all numerical atoms in a list at superficial level.

(defun getNumberOfNumericalAtoms (l)
    (cond
    ((null l) 0)
    ((NUMBERP (car l)) (+ 1 (getNumberOfNumericalAtoms(cdr l))))
    (T (getNumberOfNumericalAtoms(cdr l)))
    )
)

(print (getNumberOfNumericalAtoms '(1 2 (3 (4 5) (6 7)) 8 (9 10))))