%3.
%a. Define a predicate to remove from a list all repetitive elements. 
%Eg.: l=[1,2,1,4,1,3,4] => l=[2,3])
%b. Remove all occurrence of a maximum value from a list on integer numbers.


remove(L,R):-
    remove2(L,L,R).

remove2([],_,[]).

remove2([H|T],L,[H|R]):-
    appearsaux(L,H,0,R3),
    R3 == 1,
    !,
    remove2(T,L,R).

remove2([_|T],L,R):-
    remove2(T,L,R).

appearsaux([],_,R2,R2).

appearsaux([H|T],E,R,R2):-
    H == E,
    R1 is R+1,
    !,
    appearsaux(T,E,R1,R2).

appearsaux([_|T],E,R,R2):-
    appearsaux(T,E,R,R2).


maxi([E],E).
maxi([H|T],R):-
    maxi(T,R),
    H<R,
    !.

maxi([H|_],H).

solveb(L,R):-
    maxi(L,M),
    solvebaux(L,M,R).

solvebaux([],_,[]).

solvebaux([H|T],M,[H|R]):-
     H =\= M,
    !,
    solvebaux(T,M,R).

solvebaux([_|T],M,R):-
    solvebaux(T,M,R).
