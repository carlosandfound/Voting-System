/*
 * File: UserInterface.java
 *
 * Description: this file contains the class implementation for the UserInterface class used in the "Voting System" product
 *
 * Author: Justin Koo
 */

import java.io.*;

/**
 * <h1>UserInterface</h1>
 * <h2>Purpose</h2>
 * The UserInterface class provides all methods to prompt for and process user input.
 */
public class UserInterface {

    /**
     * Initializes an instance of the "Voting System" user interface.
     */
    public UserInterface() {
        // No initializations necessary
    }

    /**
     * Prompts the system user for the filename of the specific ballot file they wish to process.
     * @param out An {@code OutputStream} through which the request is made.
     * @param in An {@code InputStream} through which the user provides the request response.
     * @return A {@code String} denoting the filename of the ballot file that is to be processed.
     */
    public String requestBallotFilename(OutputStream out, InputStream in) {
        String request = "Enter a ballot filename: ";
        try {
            out.write(request.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String filename;
        try {
            filename = br.readLine();
            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Displays information to the system users when the election type encountered  is unexpected by the system.
     * @param unexpected_election_type A {@code String} denoting the unexpected election type encountered.
     * @param out An {@code OutputStream} where the information is written to.
     */
    public void displayInvalidElectionType(String unexpected_election_type, OutputStream out) {
        String display = "Detected election type as " + unexpected_election_type + ". Election type must be IR or OPL\n";
        try {
            out.write(display.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays an exception message to the system users when any exception is encountered throughout the lifecycle of the program.
     * @param ex An {@code Exception} whose message is to be displayed.
     * @param out An {@code OutputStream} where the exception message is written to.
     */
    public void displayExceptionMessage(Exception ex, OutputStream out) {
        try {
            out.write(ex.getMessage().getBytes());
            out.write("\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the results of an election to the system users.
     * @param e The {@code Election} whose results are to be displayed.
     * @param out An {@code OutputStream} where the election results are written to.
     */
    public void displayResults(Election e, OutputStream out) {
        try {
            out.write(e.toString().getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
