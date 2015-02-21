#### BubbleSort2DArrayRange
Example program implementing Bubble Sort on a specified range in a 2D object array.
This example implements a traditional bubblesort algorithm (courtesy of http://sorting-algorithms.com/bubble-sort).

```Java
for i = 1:n,
   swapped = false
   for j = n:i+1, 
       if a[j] < a[j-1], 
           swap a[j,j-1]
           swapped = true
   → invariant: a[1..i] in final position
   break if not swapped
end
```
 
It translates "j" into a meaningful 2D array address using integer division and modulus operations.

The Bubblesort method performs the comparison and swap operations inline, without abstracting the object type.
A future version may provide that abstraction, but it is currently left to the reader.
