# How to use

- Create a file named `datalog.txt`
- Launch a terminal and execute the StratificationModule.jar in the same directory as the datalog.txt
- Result will be writen in a `result_datalog.txt` file

# Syntax constraint for datalog.txt

### Declaration of the EDB relation :

- Begin with %EDB
- One relation per line

##### Example :

%EDB  
relation1(x,x,x)  
relation2(x,x,x)  
...  

### Declaration of the IDB rules :

- Begin with %IDB
- One rule per line
- `:-` to separate head and tail

##### Example :

% IDB  
rule1() :- p(x,x),q(x,x)  
rule2(x) :- p(x,x),q(x,x)  
...  
