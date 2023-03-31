%C . Să se scrie un program PROLOG care generează lista aranjamentelor de k elemente dintr-o listă de numere întregi, pentru
% care produsul elementelor e mai mic decât o valoare V dată. Se vor scrie modelele matematice și modelele de flux pentru
% predicatele folosite.
%aranjamente - combinari + permutari
insert(E,L, [E|L]).

insert(E,[H|T],[H|R]):-
    insert(E,T,R).

permutations([],[]).

permutations([H|T],R1):-
    perm(T,R),
    insert(H,R,R1).

combinations([E|_],1,[E]).
combinations([_|T],K,R):-
    combinations(T,K,R).

combinations([H|T],K,[H|R]):-
    K>1,
    K1 is K - 1,
    combinations(T,K1,R).

productList([],1).

productList([H|T],P):-
    productList(T,Rest),
    P is H*Rest.

checkCondition(L,V):-
    productList(L,P),
    P < V.

combinationsWithCondition(L,K,C,V):-
    combinations(L,K,C),
    checkCondition(C,V).

arrangments(L,K,O,V):-
    combinationsWithCondition(L,K,O1,V),
    permutations(O1,O).

solve(L,K,V,O):-
    findall(O1,arrangments(L,K,O1,V),O).
