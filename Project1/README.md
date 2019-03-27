Team 17

- Carlos Alvarenga (alvar357)
- Justin Koo (kooxx078)
- Michael McLaughlin (mclau361)
- Xiaochen Zhang (zhan6687)

It is assumed that the .csv ballot files and .java source files are in the same directory at the same level.

For the VotingSystem.java test to run correctly on IntelliJ, the .csv files must be within the /Project1 folder since the program is run at this folder level.

The program can be run in any folder in the linux terminal window assuming that the ballot files and class files are in the same directory level. The audit file will then be generated in this same directory. However, for IntelliJ, the specified filename must also contain the folder name if the ballot files are within a sub-folder. For example if the program is run in /Project1, and the ballot file 'test.csv' is in the sub-folder 'src', the file name must be 'src/test.csv' instead of just 'test.csv'. This also applies to a linux terminal window command.

The "main()" method is located in /src/VotingSystem.java, so to compile and run the program open a terminal window on UNIX, navigate to the proper directory and execute:
    javac VotingSystem.java
    java VotingSystem

JUnit 5 was used for automated testing, so one should ensure the proper dependencies are added to be able to run the tests in src/tests.
