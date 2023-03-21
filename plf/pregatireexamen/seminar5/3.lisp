;; 3. Build a list with the positions of the minimum number from a linear list.

(defun pozMin (l minC pozC listPoz)
    (cond
        ((null l) listPoz)
        ((not (numberp (car l))) (pozMin (cdr l) minC (+ 1 pozC) listPoz))
        ((not (numberp minC)) (pozMin (cdr l) (car l) (+ pozC 1) (list pozC)))
        ((= minC (car l)) (pozMin (cdr l) minC (+ pozC 1) (append listPoz (list pozC))))
        ((< (car l) minC) (pozMin (cdr l) (car l) (+ pozC 1) (list pozC))) 
        (t (pozMin (cdr l) minC (+ pozC 1) listPoz))
    )
)

(defun pozMinMain (l)
    (pozMin l (car l) 1 nil)
)

(print (pozMinMain '(1 2 3 1 9 8 10 1)))