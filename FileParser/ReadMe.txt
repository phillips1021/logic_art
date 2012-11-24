Solution for Art & Logic Programming Challenge Problem 2

This project provides the code that is the solution to 
problem 2 of the Art & Logic programming challenge.

The project uses Maven to managa dependencies and
to build.

This project provides a FileParserService class that 
can parse a file structured per the business rules 
of the problem and the populates a collection of
Section objects (see class Section) with the file's
contents.

The Section objects can then be manipulated 
to get specific values and to set specific 
values.

The FileParserService includes behavior to write 
formatted contents back to the a file.

See the unit tests (src/test/java) for examples
of how to use the FileParserService and Section
classes.

You can run an application that uses the FileParser 
by creating a Jar of this project (the main class
is FileParserApp (see src/main/java) and executing 
the Jar file with two arguments - the first argument is the 
path to the file (e.g. "/usr/local") and the second
argument is the file name to parse ("testfile.txt").

For example if you use mvn -e -DskipTests=true assembly:assembly 
to create the Jar file you would execute it at the command
line with:

java -jar FileParserApp-jar-with-dependencies.jar /usr/local testfile.txt 

The example application reads in the testfile.txt data and parses it into
a collection of Section objects (see src/main/resources).  Does some
getting and setting of values.  The writes the updated collection 
of Section objects to a file named new_testfile.txt in the same
folder as testfile.txt.





