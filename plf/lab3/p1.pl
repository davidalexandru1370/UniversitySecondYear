%2. 
%a. Sort a list with keeping double values in resulted list. E.g.: [4 2 6 2 3 4] --> [2 2 3 4 4 6]
%b. For a heterogeneous list, formed from integer numbers and list of numbers, write a predicate to sort every 
%sublist, keeping the doubles.
%Eg.: [1, 2, [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7] 
%=>[1, 2, [1, 4, 4], 3, 6, [1, 3, 7, 9, 10], 5, [1, 1, 1], 7].

append2([],R,R).
append2([H|R],R2,[H|T]):-
    append2(R,R2,T).

bubbleSort(List,R):-
    swap(List,NewList),
    !,
    bubbleSort(NewList,R).

bubbleSort(R,R).

swap([A,B|List],[B,A|List]):-
    check(B,A).

swap([A|List],[A|NewList]):-
    swap(List,NewList).

check(A,B):-
    A<B.

solveb(L,R):-
    solvebhelper(L,[],R).

solvebhelper([],Acc,Acc).

solvebhelper([H|T],Acc,R):-
    is_list(H),
    !,
    bubbleSort(H,R2),
    append2(Acc,[R2],R3),
    solvebhelper(T,R3,R).

solvebhelper([H|T],Acc,R):-
    number(H),
    !,
    append2(Acc,[H],R3),
    solvebhelper(T,R3,R).
    

