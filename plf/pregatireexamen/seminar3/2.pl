insert(E,L,[E|L]).
insert(E,[H|T],[H|R]):-
    insert(E,T,R).


perm([],[]).
perm([H|T],R1):-
    perm(T,R),
    insert(H,R,R1).

arr([H|_],1,[H]).
arr([_|T],K,R):-
    arr(T,K,R).
arr([H|T],K,R1):-
    K > 1,
    K1 is K - 1,
    arr(T,K1,R),
    insert(H,R,R1).

solve(L, R):-
    findall(R1,perm(L,R1),R).