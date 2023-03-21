%13.The list a1, ..., an is given and it consists of distinct integers. 
%Write a predicate to determine all subsets with aspect of "mountain" 
%(a set has a "mountain" aspect if the elements increase to a certain point and then decrease).
%flow model subsets(L - initial list - R result list) (i,o), (i,i)
%flow model is_mountain(L - input list, F - ascending/descending flag 0 - ascending 1 - descending) (i,i), (i,o) 
%flow model solve(L- input list, R - result list) (i,i), (i,o)

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
    [A,B|_]=R,
    A<B,
    is_mountain(R,0).

solveall(I,R2):-
    findall(R,solve(I,R),R2).


test():-
    solveall([5,6,7,1,2],[[5,6,7,1],[5,6,7,2],[5,6,1],[5,6,2],[5,7,1],[5,7,2],[6,7,1],[6,7,2]]),
    solveall([5,6,7,8],[]),
    solveall([8,7,6,5],[]),
    solveall([5,6,7,1,8],[[5,6,7,1],[5,6,1],[5,7,1],[6,7,1]]).