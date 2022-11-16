%13.The list a1, ..., an is given and it consists of distinct integers. 
%Write a predicate to determine all subsets with aspect of "mountain" 
%(a set has a "mountain" aspect if the elements increase to a certain point and then decrease).


subsets([],[]).

subsets([H|T],[H|L]):-
    subsets(T,L).

subsets([_|T],L):-
    subsets(T,L).

len([],0).
len([_|T],R):-
     len(T,R1),
     R is R1+1.

is_mountain([_],1).

is_mountain([H1,H2|T],0):-
    H1<H2,
    is_mountain([H2|T],0).

is_mountain([H1,H2|T],0):-
    H1 >= H2,
    is_mountain([H2|T],1).

is_mountain([H1,H2|T],1):-
    H1 > H2,
    is_mountain([H2|T],1).

solve(I,R):-
    subsets(I,R),
    R=[A,B|_],
    A<B,
    is_mountain(R,0).

solveall(I,R2):-
    findall(R,solve(I,R),R2).


test():-
    