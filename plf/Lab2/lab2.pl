%12.
%a. Write a predicate to substitute in a list a value with all the elements of another list.
%b. Remove the n-th element of a list.

%replaceElementWithList(K - integer, L - initial list, R - result list)
%flow model: (i i i), (i i o)

removeNthElement([],_,[],_).

removeNthElement([H|T],K,[H|TR],I):-
    K=\=I,
    I1 is I+1,
    removeNthElement(T,K,TR,I1).

removeNthElement([_|T],K,R,I):-
    I1 is I+1,
    removeNthElement(T,K,R,I1).

append(R,R,[]).
append([H|R],R2,[H|T]):-
    append(R,R2,T).

substitute([],_,[],[]).
substitute([],_,[_],[]).
substitute([],_,[_|_],[]).

substitute([H|T],EL,L,R):-
    H=:=EL,
    append(R,R2,L),
    substitute(T,EL,L,R2).

substitute([H|T],EL,L,[H|R]):-
    substitute(T,EL,L,R).

% replaceElementWithList(K,[H|T],[HR|TR]):-
%     T =:= K,
%     replaceElementWithList[K,[T],].

        
