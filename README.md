# delay-counter

how to use:

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



