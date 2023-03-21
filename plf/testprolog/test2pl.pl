%Given a numerical linear list consisting of integers, delete all sequences (with at least two
%elements) of negative numbers.
%Se da o lista formata din numere intregi. Se cere sa se elimine din lista toate subsirurile (de
%minimum 2 elemente) formate din valori negative.
%[1 -3 -4 2 -5 -6 8] -> [1 2 8]
%[1 -3 4 2 -4 2] -> [1 -3 4 2 -4 2]
%[-4 -3 -2 -1] -> []
%[1 -3 2 -3 -4 1 -3 -4 -5 6] -> [1 -3 2 1 6]
%flow model solve(L - initial list, R - result, C - counter for negative elements) (i,i,i),(i,o,o), (i,o,i),(i,i,o)


append2([],R,R).
append2([H|R],R2,[H|T]):-
    append2(R,R2,T).

solve([],[],_).

solve([H|T],[H|R],_):-
    H >= 0,
    solve(T,R,0),
    !.

solve([H|T],R,C):-
    H<0,
    solve(T,R,C),
    !.


test():-
    solve([1,-3,-4,2,-5,-6,8],[1,2,8],0),
    %solve([1,-3,4,2,-4,2],[1,-3,4,2,-4,2],0),
    solve([-4,-3,-2,-1],[],0).
    %solve([1,-3,2,-3,-4,1,-3,-4,-5,6],[1,-3,2,1,6],0).
    

