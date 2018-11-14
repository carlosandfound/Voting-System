Team 17

- Carlos Alvarenga (alvar357)
- Justin Koo (kooxx078)
- Michael McLaughlin (mclau361)
- Xiaochen Zhang (zhan6687)

All .csv files located in the /testing folder should be moved/copied to the /src folder for the tests in /testing to run correctly. This is due to the fact that SystemTest.java assumes that the ballot files are located under the /src folder.

The "main()" method is located in /src/VotingSystem.java, so to compile and run the program open a terminal window on UNIX, navigate to the proper directory and execute:
    javac VotingSystem.java
    java VotingSystem

JUnit 5 was used for automated testing, so one should ensure the proper dependencies are added to be able to run the tests in src/tests.
