# delay-counter v0.1
support for:  
n-input gates
prop and contam delays (as opposed to just one at a time)  
maybe more stuff if i get bored  
soon tm  

## how to use this:
### step 1: enter in values

Change the delays for each gate by tweaking the "static int (gate)" values at the beginning of the code file before compiling

### step 2: enter in expression

To count up all of the delays of a combinatorial circuit, enter each "layer" L_n of gates separated by '+', beginning from the gate leading to the output. Separate gates in each layer by commas.

Ex: Given the following circuit:  

![ex1](https://raw.githubusercontent.com/Derposoft/delay-counter/master/im%20in%20too%20deep.JPG)

L1 = nand  
L2 = and,xnor  
L3 = and,or,and,and  

Expression to be inputted to the program:  
> nand + and,xnor + and,or,and,and  

##### Important: every layer must have 2^n number of gates. "wire" (delay = 0) can be used to fill in for paths that have already reached the input and are finished.
(for example, L4 = wire,wire,wire,wire,wire,wire,wire,wire could also be added to the previous input to obtain the same result)

### but what if there are multiple outputs??  
you can join the two outputs by using a wire! Take the following circuit:  
![please render](https://raw.githubusercontent.com/Derposoft/delay-counter/master/plc-program-implement-combinational-logic-circuit-2-02.png)  

By beginning the input prompt like so:  
> wire + or,or + or,or,and,and + ...  

all of the paths can be traversed and the maximum delay path between the two outputs will be returned.
  
### one gate in L4 has a not gate feeding into it but all of the other paths have terminated. is there any way to NOT type 31 "wire,wire,..."s just for the one not gate???  

yes. If gates within a layer are separated by spaces instead of commas, their sum of their delays will be used. If there is a clear maximum-delay path between the inputs to a gate, it may be easier to manually enter the gates in that path separated by spaces in some cases rather than adding another layer L_n and be forced to pad it with ~2^n "wire,"s.  

##### For example, the circuit in the image above this could be easily squished into 2 layers (not including the joining wire at the beginning), like so:  

> wire + or,or + or and and, or and and, and and not, and and  

In this case, since we know that the "y1 -> or -> or -> and -> and" paths must have a greater delay than the "y1 -> or -> or -> and -> wire" paths, it would be pointless to enter in the other paths when we only need the path with the highest delay. Additionally, each branch of the "y1 -> or" gates are identical. Therefore, it is much more efficient to directly sum "or and and" to avoid having to enter a lengthy expression for L3 or L4.
