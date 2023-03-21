%a. Write a predicate to determine the lowest common multiple of a list formed from integer numbers.
%b. Write a predicate to add a value v after 1-st, 2-nd, 4-th, 8-th, … element in a list.
 
gcd(A,A,A):-
    !.

gcd(A,B,R):-
    A>B,
    A1 is A-B,
    !,
    gcd(A1,B,R).

gcd(A,B,R):-
    A<B,
    B1 is B-A,
    !,
    gcd(A,B1,R).

lcm(A,B,R):-
    gcd(A,B,GCD),
    R is A*B/GCD.

lcmlist([A],A).

lcmlist([H|T],R):-
    lcmlist(T,Res),
    !,
    lcm(Res,H,R).


addaux([],_,_,_,[]).

addaux([H|T],V,Next,Curr,[H1|R]):-
    Next == Curr,
    !,
    H1 is H+V,
    Next1 is Next*2,
    Curr1 is Curr+1,
    addaux(T,V,Next1,Curr1,R).

addaux([H|T],V,Next,Curr,[H|R]):-
    Curr1 is Curr+1,
    addaux(T,V,Next,Curr1,R).

add(L,V,R):-
    addaux(L,V,1,1,R).