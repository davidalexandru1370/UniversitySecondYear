% genereazaLista(i, n) = { [n] , i = n
% { i + genereazaLista(i+1, n), altfel (i < n)
%
% genereazaLista(i:intreg, n:intreg, r:list)
% i - numarul pe care l adaugam in lista
% n - val maxima din lista
% r - lista rezultat
%
% model de flux (i, i, o) - determinist - cel folosit de noi
% (i, i, i) - determinist
genereazaLista(N, N, [N]):-
    !.
genereazaLista(I, N, [I|R]):-
    I < N,
    I1 is I + 1,
    genereazaLista(I1, N, R).
% insereaza(e, l1,...,ln) = 1. e + l1l2...ln
% 2. l1 + insereaza(e, l2...ln)
%
% insereaza(E: element, L:List, LRez:list)
% E - elementul pe care dorim sa-l inseram pe toate pozitiile
% L - lista in care o sa fie inserat elementul E
% LRez - lista rezultat
%
% (i, i, o) - nedeterminist - pe acesta o sa-l folosim
% (i, i, i) - determinist
insereaza(E, L, [E|L]).
insereaza(E, [H|T], [H|Rez]) :-
    insereaza(E, T, Rez).
% perm(l1,...,ln) = 1. [] daca n = 0
% 2. insereaza(l1, permutari(l2,...,ln))
% perm(L:list, LRez:list)
% (i, o) – nedeterminist
% (i, i) - determinist
permutari([], []).
permutari([E|T], P) :-
    permutari(T, L),
    insereaza(E, L, P).
% conditie(l1,...,ln) = { adevarat , n < 2
% { fals , n >= 2 si abs(l1-l2)<2
% { conditie(l2,...,ln), n >= 2 si abs(l1-l2)>=2
% conditie(L:list)
% L - lista pe care o verificam daca respecta conditia din enunt sau nu
% model de flux (i) - determinist
conditie([_]):-
    !.
conditie([L1,L2|T]):-
    D is abs(L1-L2),
    D >= 2,
    conditie([L2|T]).
% permConditie(l, k) = 1. permutari(l), conditie(permutari(l)) = adev
% permConditie(L: List, O:List).
% L - lista pe care trebuie sa facem permutarile cu conditie
% O - permutarea ce respecta conditia
% Model de flux: (i, i) - determinist,
% (i, o) - nedeterminist - cel folosit
permConditie(L, O):-
    permutari(L, O),
    conditie(O).

% main(n) = U(permConditie(genereazaLista(1,n)))
%
% main(N:intreg, O:List)
% N - elementul maxim din lista noastra
% O - lista rezultat
% Model de flux (i, o) - determinist - folosim
% (i, i) - determinist
main(N, O):-
    genereazaLista(1, N, L),
    findall(O1, permConditie(L, O1), O).