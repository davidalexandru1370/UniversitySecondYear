% C. Dându-se o listă formată din numere întregi, să se genereze ȋn PROLOG lista aranjamentelor cu număr par de elemente,
% având suma număr impar. Se vor scrie modelele matematice și modelele de flux pentru predicatele folosite.
% Exemplu- pentru lista L=[2,3,4] ⇒ [[2,3],[3,2],[3,4],[4,3]] (nu neapărat în această ordine)
%aranjamente_sp(LI:lista,S:intreg,LE:intreg,C:lista,O:lista)
%LI - lista din care alegem elementele
%S - suma curenta a elementelor din C
%LE - lungimea lui C
%C - colectoare
%O - un aranjament al listei date ce are suma impara si lungimea para
%model flux: (i,i,i,i,i) - determinist, (i,i,i,i,o) - nedeterminist
%vom folosi varianta nedeterminsita (i,i,i,i,o).
aranjamente_sp(_,S,LE,C,O):- S mod 2 =:= 1,
LE mod 2 =:= 0,
O=C.
aranjamente_sp(LI,S,LE,C,O):- extragere(LI,EL),
elimina_prim(LI,EL,AUX),
S1 is S + EL,
LE1 is LE + 1,
aranjamente_sp(AUX,S1,LE1,[EL|C],O).
%extragere(L:lista,E:element)
%L - lista din care extragem un elemnet
%E - un element din lista
%model de flux (i,i) - determinist, (i,o) - nedeterminist
%vom folosi modelul (i,o) nedeterminist
extragere([H|_],H).
extragere([_|T],O):-
    extragere(T,O).
%elimina_prim(L:lista,E:element,O:lista)
%elimina prima aparitie a lui E din lista L
%L - lista pe care operam
%E - elementul ce-l stergem
%O - lista rezultata in urma elimintarii
%model de flux (i,i,i) - determinist; (i,i,o) - determinist
%vom folosi modelul (i,i,o)
elimina_prim([],_,[]):-!.
elimina_prim([H|T],E,[H|O]):-H\=E,
!,
elimina_prim(T,E,O).
elimina_prim([_|T],_,T).