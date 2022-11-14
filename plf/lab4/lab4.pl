
subset([],[]).

subset([H|T],[H|L]):-
    subset(T,L).

subset([H|T],L):-
    subset(T,L).