;; .Write a function to determine the number of nodes on the level k from a n-tree represented as follows:
;;  (rootlist_nodes_subtree1 ... list_nodes_subtreen)
;;   Eg: tree is(a (b (c)) (d) (e (f))) and k=1 => 3 nodes

(defun solve(l level currentLevel)
    (format t "~d ~d~%" l currentLevel)
    (cond
        ((and (atom l) (equal level currentLevel)) 1)
        ((atom l) 0)
        (t (apply '+ (mapcar  #' (lambda (l) (solve l level (+ 1 currentLevel))) l)))
))

(print (solve '(a (b (c)) (d) (e (f))) 2 0))