# Quiz 5

## Question 1: Mars 

This algorithm runs in $O(n)$ because it is a simple `for` loop over the input
containing a single `if` statement. (The modulo operator is used, which may be linear or logarithmic with the size
of its arguments, but it is trivial to change this construct into another `if` statement, thereby
reducing this term to constant also).

This algorithm has linear space complexity because the input space has linear complexity and the 
auxiliary space is constant. For auxilary space, the algorithm uses two variables, each of 
a constant size (one constant array used for indexing and one counter).


