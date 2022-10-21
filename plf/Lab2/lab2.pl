%12.
%a. Write a predicate to substitute in a list a value with all the elements of another list.
%b. Remove the n-th element of a list.

%removeNthElement(L - input list,K - position ,R - result list ,I - current index)
%flow model: (i,i,o,i), (i,i,i,i)

%substitute(L - input list,K - substitute element ,S - substitute list,R - result)
%flow model : (i,i,i,o), (i,i,i,i)

removeNthElement([],_,[],_,_):-
 !.

removeNthElement([],K,[],I,_):-
    K>I,
    !.

removeNthElement([H|T],K,[H|TR],I,N):-
    K=\=I,
    I1 is I+1,
    !,
    removeNthElement(T,K,TR,I1,N).

removeNthElement([H|T],K,R,I,N1):-
    I1 is I+1,
    N1 is H,
    !,
    removeNthElement(T,K,R,I1,N1).

remove(A,K,B,N):-
    removeNthElement(A,K,B,0,N).

append(R,R,[]).
append([H|R],R2,[H|T]):-
    append(R,R2,T).

substitute([],_,[],[]).
substitute([],_,[_],[]).
substitute([],_,[_|_],[]).

substitute([H|T],EL,L,R):-
    H=:=EL,
    append(R,R2,L),
    !,
    substitute(T,EL,L,R2).

substitute([H|T],EL,L,[H|R]):-
    substitute(T,EL,L,R),
    !.

test():-
    substitute([1,2,3,2],2,[5,6],[1,5,6,3,5,6]),
    substitute([1,2,3,2],2,[5],[1,5,3,5]),
    substitute([1,2,3,4],5,[5,6,7],[1,2,3,4]),
    substitute([1,2,3,2],2,[],[1,3]),
    substitute([],2,[3,4,5],[]).

newtests():-
    remove([1,2,3,4],0,[2,3,4],1),
    remove([1,2,3,4],1,[1,3,4],2),
    remove([1,2,3,4],2,[1,2,4],3),
    remove([1,2,3,4],3,[1,2,3],4),
    remove([1,2,3,4],-2,[1,2,3,4],4),
    remove([1,2,3,4],10,[1,2,3,4],0).