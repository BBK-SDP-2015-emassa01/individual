# individual
SML project

SUMMARY:

--> The SML is an interpreter for a simple machine language SML. The general form of a machine language instruction is
label instruction register-list.

REFLECTION:

--> SML uses Java reflection to create the instances of the subclasses. The SML language to be extended without having to modify
the original code.

--> Reflection is also used in the Testing of the Scan and ScanInt methods in the translator class

--> SML uses Java reflection to create the instances of the subclasses. The SML language to be extended without having to modify
the original code.

--> ERROR CHECKING

Duplicate labels are not allowed.
Registers are only allowed in the range 0-31

--> TESTING
* As the project required the modification of existing code, I checked these methods using JUnit 4.0. For new methods that were created to enable the use of reflection, I carried out Test Driven Development.
* I started testing this project using System.out.println statements and this was more comprehensively tested with the use of J-Unit tests which helped to identify the sources of code-errors.

My testing and development process followed the folling pattern:
* 1. I wrote tests for the code we were given.  I used reflection to test the private methods in the Translator class (scan() and scanInt()) during the first phase of my testing.
* 2. I removed println() statements during the second phase of testing.
* 3. I wrote tests for my new methods during the third phase of testing.
* 4. I completed and extended the program using reflection.

--> EXTENDING THE SML
* As the program is extended, instructions must have the convention class name 'instruction' with a capitalised first letter + "Instruction". For example, for an 'add' instruction, the class name must be "AddInstruction".

ASSUMPTIONS OF THE SML
-->
* The sml will assume that the default platform encoding is suitable as it will perform a byte to String (or String to byte) conversion. 
* The sml assumes that the programs are read in the English language (i.e., to UpperCase methods will not work on International Characters

Thanks & Enjoy!







Project Description:

WHAT THE SML DOES

The general form of a machine language instruction is
'label instruction register-list'
where;

label: is the label for the line. Other instructions might "jump" to that label.

instruction: is the actual instruction.

register-list: the list of registers that the instruction manipulates.


In SML, there are instructions for adding, multiplying, dividing, subtracting, storing and re-
trieving integers, and for conditionally branching to other labels (like an if statement).


Registers are simple, integer, storage areas in computer memory, much like variables.
In SML, there are 32 registers, numbered 0, 1, . . . , 31.


SML has the following instructions:

* L1 add r s1 s2 Add the contents of registers s1 and s2 and store the result in register r
* L1 sub r s1 s2 Subtract the contents of register s2 from the contents of s1 and store the result in register r
* L1 mul r s1 s2 Multiply the contents of registers s1 and s2 and store the result in register r
* L1 div r s1 s2 Divide (Java integer division) the contents of register s1 by the contents of register s2 and store the result in register r
* L1 out s1 Print the contents of register s1 on the Java console (using println)
* L1 lin r x Store integer x in register r
* L1 bnz s1 L2 If the contents of register s1 is not zero, then make the statement labeled L2 the next one to execute
where
* L1 is any identifier, actually, any sequence of non-whitespace characters.

Each statement of a program must be labeled with a different identifer.

Each of s1, s2, and r is an integer in the range 0. . . 31 and refers to one of the 32 registers in the machine that executes language SML.


Completed 01.02.2015 by Esha Massand
