Team 17

- Carlos Alvarenga (alvar357)
- Justin Koo (kooxx078)
- Michael McLaughlin (mclau361)
- Xiaochen Zhang (zhan6687)

For the test files to run correctly on IntelliJ, all .csv files must be within the Project2/testing folder since the test classes are in the Project2/testing folder and the ballot files are assumed to be in this same folder level.

GUI functionality 
- Selecting the "Enter filename" option allows you to enter the filename in a text field
- Selecting the "Search For File" option allows you to search for the file on disk
- When selecting "Search For File", select the "Search" button to commenze file searching. This will take you to a new window where you can search for the file. In this window, selecting the "Cancel" button exits out this window, discarding any choice. Selecting the "Open" button saves and confirms selection of the file selected in the window.
- When done, select the "OK" button to effectively provide the selected ballot file to the program. Selecting "Cancel" will close the GUI window and end the program.

The program can be run in any folder in the linux terminal window and the generated output files will be generated in this same directory level. There are two ways to run the program.

1.) Specifying the filname as a command-line argument:
- In IntelliJ, it's assumed that the .csv ballot file is in the same directory level as the .java class files. If the ballot file is within a sub-folder of the current directory, the specified filename must also contain the sub-folder name. For example if the program is run in Project2/src, and the ballot file 'test.csv' is in the sub-folder 'testing' (i.e. Project2/src/testing, the filename provided must be 'testing/test.csv' instead of just 'test.csv'. This also applies to a linux terminal window command.
- If the ballot isn't anywhere within the current directory holding the class files, the full path name of the file must be provided as the filename.

2.) Not specifying the filname as a command-line argument and instead providing it through the GUI prompt:
- Providing the filename through the GUI window's text field (i.e. "Enter Filename" option) works exactly like specifying the filename as a command-line argument (see section above)
- Providing the filename through the GUI window's search-for-file option (i.e. "Search for File" option) can handle a file in any directory (i.e. the ballot file doesn't have to be in the same directory as the one holding the class files)

The "main()" method is located in /src/VotingSystem.java, so to compile and run the program open a terminal window on UNIX, navigate to the proper directory and execute:
    javac VotingSystem.java
    java VotingSystem [filename]
- If no [filename] is specified, then the program will proceed to prompt for a filename during its execution.
- If a filename is specified, then the program will not prompt for a filename during its execution.
- The program will terminate immediately if the [filename] specified by the user as a command-line argument is an invalid file to be read.
- The program will not terminate if the [filename] specified by the user through the GUI prompt is an invalid file to be read. It will instead continue prompting for the file through the GUI. The program will only terminate in this case if the user selects the "Cancel" button on the GUI window or closes the GUI window.

JUnit 5 was used for automated testing, so one should ensure the proper dependencies are added to be able to run the tests in Project2/testing.
