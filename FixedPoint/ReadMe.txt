Solution for Art & Logic Programming Challenge Problem 1a

This project provides a class, FixedPoint, that can encode 
and decode a float value packed into a 32-bit integer with 
the following structure.

First bit is the sign bit (1 = negative)
Next 16 bits are for the integer portion of the float value
Last 15 bits are for the fraction portion of the float value

Read the JavaDoc comments for the FixedPoint class and its 
methods to understand how to use it.  Also review the 
unit test in the test class FixedPointTest (src/test/java) 
for examples of using the FixedPoint class.

The project can be used to create a runnable Java jar file,
with the main class being FixedPointApp (src/main/java).  You
can run the FixedPoint.jar file in a terminal window by 
executing java -jar FixedPoint.jar--output will display in 
the terminal window.



