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

;(print(solve '(10 20 30 40 50) 3))
(defun testTwiceNthElement ()
    (assert
    (and
        (equal '(10 20 30 30 40 50) (solve '(10 20 30 40 50) 3))
        (equal '(10 20 30 40 50) (solve '(10 20 30 40 50) 10))
        (equal '(10 10 20 30 40 50) (solve '(10 20 30 40 50) 1))
    ))
)

(testTwiceNthElement)
;b) Write a function to return an association list with the two lists given as parameters. 
;Example: (A B C) (X Y Z) --> ((A.X) (B.Y) (C.Z)).

(defun associationList (l1 l2)
    (cond
    ((null l1) l2)
    ((null l2) l1)
    (t (cons (cons (car l1) (car l2)) (associationList(cdr l1) (cdr l2))))
    )
)

;(print (associationList '(A B C) '(X Y Z)))
(defun testAssociationList ()
    (assert
    (and
        (equal '((A . X) (B . Y) (C . Z)) (associationList '(A B C) '(X Y Z)))
    ))
)
(testAssociationList)
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

;(print (determineNumberOfSublists '(1 2 (3 (4 5) (6 7)) 8 (9 10))))
(defun testDetermineNumberOfSublists ()
    (assert
    (and
        (equal 5 (determineNumberOfSublists '(1 2 (3 (4 5) (6 7)) 8 (9 10))))
        (equal 1 (determineNumberOfSublists '(1 2)))
        (equal 4 (determineNumberOfSublists '(1 2 (3) (4) (5))))
    ))
)
(testDetermineNumberOfSublists)
;d) Write a function to return the number of all numerical atoms in a list at superficial level.

(defun getNumberOfNumericalAtoms (l)
    (cond
    ((null l) 0)
    ((NUMBERP (car l)) (+ 1 (getNumberOfNumericalAtoms(cdr l))))
    (T (getNumberOfNumericalAtoms(cdr l)))
    )
)

;(print (getNumberOfNumericalAtoms '(1 2 (3 (4 5) (6 7)) 8 (9 10))))

(defun testGetNumberOfNumericalAtoms ()
    (assert
    (and
    (equal 3 (getNumberOfNumericalAtoms '(1 2 (3 (4 5) (6 7)) 8 (9 10))))
    ))
)
(testGetNumberOfNumericalAtoms)

(defun getNumberOfNumericalAtomsInDepth (l)
    (cond
    ((null l) 0)
    ((LISTP (car l)) (+ (getNumberOfNumericalAtomsInDepth (cdr l)) (getNumberOfNumericalAtomsInDepth (car l))))
    ((NUMBERP (car l)) (+ 1 (getNumberOfNumericalAtomsInDepth(cdr l))))
    (T (getNumberOfNumericalAtomsInDepth(cdr l)))
    )
)


(print (getNumberOfNumericalAtomsInDepth '(1 2 (3 (4 5) (6 7)) 8 (9 10))))




;13. For a given tree of type (2) return the path from the root node to a certain given node X.
(defun explorePath (l level node)
(cond
  ((null (car l)) nil)
  ((equal (car l) node) (list (car l)))
  (T 
    (setq left (explorePath (cadr l) (+ level 1) node ))
	  (cond 
		    ((null left) (setq right (explorePath (caddr l) (+ level 1) node ))
                     (cond
                         ((null right) NIL)
                         (T (append (list (car l)) right))
                      )
        )
		    (T (append (list (car l)) left ))
    )
 )
)
)

(defun testExplorePath ()
    (assert (and
        (equal '(A C I J K) (explorePath '(A (B) (C (D) (E))) 0 'E))
        )               
    ))


(print (explorePath '(A (B) (C (D) (E))) 0 'E ))
(print (explorePath '(A(B(D)(E(F(G)(H))))(C(I(J(K))))) 0 'A))
(print (explorePath '(A(B(D)(E(F(G)(H))))(C(I(J(K))))) 0 'K))