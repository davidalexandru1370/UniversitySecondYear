%2. 
%a. Sort a list with keeping double values in resulted list. E.g.: [4 2 6 2 3 4] --> [2 2 3 4 4 6]
%b. For a heterogeneous list, formed from integer numbers and list of numbers, write a predicate to sort every 
%sublist, keeping the doubles.
%Eg.: [1, 2, [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7] 
%=>[1, 2, [1, 4, 4], 3, 6, [1, 3, 7, 9, 10], 5, [1, 1, 1], 7].
%=>[7,6,5,3,2,1,]
%descrescator numerele si lista din lista sortata crescator si adaugate la final
%flow model bubbleSort(OP - operator ,L - input list,R - result list): (i,i,i), (i,o)
%flow model swap(L1 - initial list, L2 - list after swapped elements):- (o,o)
%flow model solveb2(L-input list, R - Result list): (i,o),(i,i)
%flow model solvebhelper2(L-input list,AccNr - list with numbers,AccL - list with lists, R - Result list): (i,i,i,i),(i,o,o,o),(i,o,i,o),(i,i,o,o),(i,o,o,i).
%flow model solveb(L - input list , R - result list): (i,o), (i,i)

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

solveb(L,R):-
    solvebhelper(L,[],R).

solvebhelper([],Acc,Acc).

solvebhelper([H|T],Acc,R):-
    is_list(H),
    !,
    bubbleSort(<,H,R2),
    append2(Acc,[R2],R3),
    solvebhelper(T,R3,R).

solvebhelper([H|T],Acc,R):-
    number(H),
    !,
    append2(Acc,[H],R3),
    solvebhelper(T,R3,R).

solveb2(L,R):-
    solvebhelper2(L,[],[],R).

solvebhelper2([],AccNR,AccL,R):-
    bubbleSort(>,AccNR,AccNrSort),
    append2(AccNrSort,[],R3),
    !,
    append(R3,AccL,R).

solvebhelper2([H|T],AccNR,AccL,R):-
    is_list(H),
    !,
    bubbleSort(<,H,R2),
    append2(AccL,[R2],R3),
    solvebhelper2(T,AccNR,R3,R).

solvebhelper2([H|T],AccNR,AccL,R):-
    number(H),
    !,
    append2(AccNR,[H],R3),
    solvebhelper2(T,R3,AccL,R).

testeb2:-
    solveb2([1,2,[4,1,4],3,6,[7,10,1,3,9],5,[1,1,1],7], [7,6,5,3,2,1,[1,4,4],[1,3,7,9,10],[1,1,1]]),
    solveb2([1,2,3,4,5], [5,4,3,2,1]),
    solveb2([[9,8,7],[3,4,1],[2],[10,2,11,5,3,14]], [[7,8,9],[1,3,4],[2],[2,3,5,10,11,14]]).

testea:-
    bubbleSort(<,[4,2,6,2,3,4],[2,2,3,4,4,6]),
    bubbleSort(>,[1,2,3,4,5,6],[6,5,4,3,2,1]),
    bubbleSort(<,[],[]),
    bubbleSort(>,[0,5,0,1,6,29,6],[29,6,6,5,1,0,0]).

testeb:-
    solveb([1, 2, [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7],[1,2,[1,4,4],3,6,[1,3,7,9,10],5,[1,1,1],7]),
    solveb([1,2,3,4],[1,2,3,4]),
    solveb([[9,8,6],[],[7,1,3,4],[2,3,-1,-2]],[[6,8,9],[],[1,3,4,7],[-2,-1,2,3]]).