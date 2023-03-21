%3.
%a. Merge two sorted lists with removing the double values.
%b. For a heterogeneous list, formed from integer numbers and list of numbers, merge all sublists with removing 
%the double values.
%[1, [2, 3], 4, 5, [1, 4, 6], 3, [1, 3, 7, 9, 10], 5, [1, 1, 11], 8] =>
%[1, 2, 3, 4, 6, 7, 9, 10, 11].


merge([],[],[]).
merge(A,[],A):-!.
merge([],B,B):-!.
merge([A|T1],[B|T2],[A|R]):-
    A<B,
    !,
    merge(T1,[B|T2],R).
merge([A|T1],[B|T2],[B|R]):-
    A>B,
    !,
    merge([A|T1],T2,R).
merge([A|T1],[B|T2],R):-
    A=:=B,
    merge([A|T1],T2,R).