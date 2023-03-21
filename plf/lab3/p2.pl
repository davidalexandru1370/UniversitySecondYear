numberOccurences([],_,0).

numberOccurences([H|T],E,S):-
    H=E,
    numberOccurences(T,E,S1),
    !,
    S is S1+1.

numberOccurences([H|T],E,S):-
    H =\=E,
    !,
    numberOccurences(T,E,S).

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