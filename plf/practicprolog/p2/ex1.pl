%a. Sort a list with removing the double values. E.g.: [4 2 6 2 3 4] --> [2 3 4 6]
%b. For a heterogeneous list, formed from integer numbers and list of numbers, write a predicate to sort every 
%sublist with removing the doubles.
%Eg.: [1, 2, [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7] =>
%[1, 2, [1, 4], 3, 6, [1, 3, 7, 9, 10], 5, [1], 7].

append2([],R,R).
append2([H|R],R2,[H|T]):-
    append2(R,R2,T).

bubbleSort(Rel,List,R):-
    swap(Rel,List,NewList),
    !,
    bubbleSort(Rel,NewList,R).

bubbleSort(_,R,R).

swap(Rel,[A,B|List],[B,A|List]):-
    check(Rel,B,A).

swap(Rel,[A|List],[A|NewList]):-
    swap(Rel,List,NewList).

check(Rel,A,B):-
    Goal=..[Rel,A,B],
    Goal.

numberOccurences([],_,0).


numberOccurences([H|T],E,S):-
    H=\=E,
    !,
    numberOccurences(T,E,S).


numberOccurences([_|T],E,S):-
    numberOccurences(T,E,S1),
    !,
    S is S1+1.

toSet(List,Set):-
    toSetHelper(List,[],Set).


toSetHelper([],Acc,Acc).

toSetHelper([H|T],Acc,Set):-
    numberOccurences(Acc,H,S),
    S>=1,
    !,
    toSetHelper(T,Acc,Set).

toSetHelper([H|T],Acc,Set):-
    numberOccurences(Acc,H,S),
    S == 0,
    toSetHelper(T,[H|Acc],Set).


solvea(L,R):-
    solveaux(L,R).


solveaux([H|T],R):-
    is_list(H),
    toSet(H,R2),
    bubbleSort(<,R2,R3),
    append2(R3,[H],R4),
    !,
    solveaux(T,R4).

solveaux([H|T],R):-
    number(H),
    append2(R,H,R2),
    solveaux(T,R2).

