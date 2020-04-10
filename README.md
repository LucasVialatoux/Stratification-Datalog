#### Syntax constraint for datalog.txt

##Declaration of the EDB relation :

- Begin with %EDB
- One relation per line

#Example :

%EDB
relation1(_,_,_)
relation2(_,_,_)
...

##Declaration of the IDB rules :

- Begin with %IDB
- One rule per line
- `:-` to separate head and tail

#Example :

% IDB
rule1() :- p(_,_),q(_,_)
rule2(_) :- p(_,_),q(_,_)
...
