;; 10. Define a function that replaces one node with another one in a n-tree represented as: root 
;; list_of_nodes_subtree1... list_of_nodes_subtreen) 
;; Eg: tree is (a (b (c)) (d) (e (f))) and node 'b' will be replace with node 'g' => tree (a (g (c)) (d) (e (f)))}


(defun solve(l elem replace)
    (cond
        ((and (atom l) (equal l elem)) replace)
        ((atom l) l)
        (t (mapcar (lambda (x) (solve x elem replace)) l))
    )
)

(print (solve '(a (b (c)) (d) (e (f))) 'b 'g))