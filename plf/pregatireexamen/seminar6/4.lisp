;Dandu-se o lista liniara, sa se stearga toate secventele de valori numerice consecutive. De exemplu
;(1 2 c 4 6 7 8 i 10 j) -> (c 4 i 10 j)

;; (defun getStopIndexConsecutiveSequence(l index)
;;     (cond
;;     ((null l) index)
;;     ((not (numberp (car l))) index)
;;     ((not (numberp (car (cdr l)))) index)
;;     ((not (equal (- (car (cdr l)) (car l)) 1)) -1)
;;     (t (getStopIndexConsecutiveSequence (cdr l) (+ 1 index)))
;; ))

;; (defun solve(l result currentIndex)
;;     (print (getStopIndexConsecutiveSequence l currentIndex))
;;     (cond
;;     ((null l) result)
;;     ((not (numberp (car l))) (solve (cdr l) (append result (list (car l))) (+ 1 currentIndex)))
;;     ((< currentIndex (- (getStopIndexConsecutiveSequence l currentIndex) 1)) (solve (cdr l) result (+ 1 currentIndex))) 
;;     (t (solve (cdr l) result (+ 1 currentIndex)))
;; ))



(print (solve '(1 2 c 4 6 7 8 i 10 j) () 0))

