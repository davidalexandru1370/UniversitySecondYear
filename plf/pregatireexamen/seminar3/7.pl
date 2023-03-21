%  f([],[]).

%  f([H|T],[H|S]):-
%      f(T,S).

%  f([H|T],S):-
%      H mod 2 =:= 0,
%      f(T,S).

% f([],0).

% f([H|T],S):-
%     f(T, S1),
%     S1 is S-H.

p(1).
p(2).

r(1).
r(2).

q(1).
q(2).

s:-
    p(X),
    q(Y),
    r(Z),
    !,
    write(X),
    nl.