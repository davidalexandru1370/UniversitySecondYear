%a. Write a predicate to determine the sum of two numbers written in list representation.
%b. For a heterogeneous list, formed from integer numbers and list of digits, write a predicate to compute the 
%sum of all numbers represented as sublists.
%Eg.: [1, [2, 3], 4, 5, [6, 7, 9], 10, 11, [1, 2, 0], 6] => [8, 2, 2]
len([],0).
len([_|T],R):- len(T,R1), R is R1+1.

append2([],E,[E]).
append2([H|T],E,[H|R]):-
    append2(T,E,R).

rev_list([],[]).
rev_list([H|T],R):-
    rev_list(T,R1),
    append2(R1,H,R).

sum2([],[],0,[]).
sum2([],[],1,[1]).
sum2([],[B|TB],C,[BC|R]):-
    BC is (B+C) mod 10,
    BC - B - C =:=0, %no carry
    sum2([],TB,0,R).
sum2([],[B|TB],C,[BC|R]):-
    BC is (B+C) mod 10,
    BC - B - C =\= 0, %carry
    sum2([],TB,1,R).
sum2([A|TA],[B|TB],C,[ABC|R]):-
    ABC is (A+B+C) mod 10,
    ABC - A - B - C =:= 0, %no carry
    sum2(TA,TB,0,R).
sum2([A|TA],[B|TB],C,[ABC|R]):-
    ABC is (A+B+C) mod 10,
    ABC - A - B - C =\= 0, %carry
    sum2(TA,TB,1,R).


sum_lists(A,[],A).
sum_lists([],B,B).
sum_lists(A,B,R):-
    len(A,LA),
    len(B,LB),
    LA=<LB, !,
    rev_list(A,RA),
    rev_list(B,RB),
    sum2(RA,RB,0,RS),
    rev_list(RS,R).
sum_lists(A,B,R):-
    rev_list(A,RA),
    rev_list(B,RB),
    sum2(RB,RA,0,RS),
    rev_list(RS,R).
