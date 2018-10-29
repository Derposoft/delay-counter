# delay-counter
support for n-input gates soon tm

## how to use:

to count up all of the delays of a combinatorial circuit, enter each "layer" of gates separated by '+', beginning from the gate leading to the output. Separate gates in each layer by commas.

Ex: Given the following crude representation of a circuit:

input1 ->  
XOR  ->  
input2 ->  
NAND ->  
input3 ->  
AND  ->  
input4 ->  
OR -> output  
input5 ->  
XNOR ->  
input6 ->  
NAND ->  
input7 ->  
OR   ->  
input8 ->  


L1 = or  
L2 = nand,nand  
L3 = xor,and,xnor,or  

Expression to be inputted to the program:  
> or + nand,nand + xor,and,xnor,or

##### Important: every layer must have 2^n number of gates. "wire" (delay = 0) can be used to fill in for paths that have already reached the input and are finished.
(for example, L4 = wire,wire,wire,wire,wire,wire,wire,wire could also be added to the previous input to obtain the same result)

### but what if there are multiple outputs??  
you can join the two outputs by using a wire! Take the following circuit:  
![alt text](https://www.sanfoundry.com/wp-content/uploads/2016/09/plc-program-implement-combinatorial-logic-circuit-2-02.png)  

By beginning the input prompt like so:  
> wire + or,or + or,or,and,and...  

all of the paths can be traversed and the maximum delay path between the two outputs will be returned.
