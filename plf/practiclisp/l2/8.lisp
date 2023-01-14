;; 8. Return the list of nodes of a tree of type (2) accessed inorder.

(defun inorderTraversal(l)
    (cond
        ((null (car l))nil)
        (t
            (append (inorderTraversal (cadr l)) (list (car l)) (inorderTraversal (caddr l)))
        )    
    )
)

(print (inorderTraversal '(A (B) (C (D) (E)))))