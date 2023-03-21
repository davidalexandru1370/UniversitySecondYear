;; 11.Return the level (and coresponded list of nodes) with maximum number of nodes for a tree of type (2). The level of the root element is 0.
;;     A
;;     /\
;;    B  C
;;       /\
;;      D  E

(defun getNumberOfNodesOnLevel(l level currentLevel)
(cond
    ((null l) 0)
    ((equal currentLevel level) 1)
    (t (+ (getNumberOfNodesOnLevel (cadr l) level (+ 1 currentLevel)) (getNumberOfNodesOnLevel (caddr l) level (+ 1 currentLevel))))
))

(defun getNumberOfNodesOnEachLevel(l level)
    (cond
        ((equal (getNumberOfNodesOnLevel l level 0) 0) nil)
        (t (append  (list (getNumberOfNodesOnLevel l level 0)) (getNumberOfNodesOnEachLevel l (+ 1 level))))
    )
)

(defun getLevelWithMostNodes(l index currentMax)
    (cond
        ((null l) index)
        ((> (car l) currentMax) (getLevelWithMostNodes (cdr l) index (car l)))
        (t (getLevelWithMostNodes (cdr l) (+ 1 index) (car l)))
    )
)

(defun getNodesOnLevel(l level currentLevel)
    (cond
        ((null l) nil)
        ((equal level currentLevel) (list (car l)))
        (t (append (getNodesOnLevel (cadr l) level (+ 1 currentLevel)) (getNodesOnLevel (caddr l) level (+ 1 currentLevel))))
    )
)

(print (getNumberOfNodesOnLevel '(A (B) (C (D) (E))) 2 0))
(print (getNumberOfNodesOnEachLevel '(A (B) (C (D) (E))) 0))
(print (getLevelWithMostNodes (getNumberOfNodesOnEachLevel '(A (B) (C (D) (E))) 0) 0 -1))
(print (getNodesOnLevel '(A (B) (C (D) (E))) (getLevelWithMostNodes (getNumberOfNodesOnEachLevel '(A (B) (C (D) (E))) 0) 0 -1) 0))