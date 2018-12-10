/*
 * File: UserInterface.java
 *
 * Description: this file contains the class implementation for the UserInterface class used in the "Voting System" product
 *
 * Author: Carlos Alvarenga, Justin Koo
 */

import javax.swing.*;
import java.io.*;

/**
 * <h1>UserInterface</h1>
 * <h2>Purpose</h2>
 * The UserInterface class provides all methods to prompt for and process user input.
 */
public class UserInterface {
    private boolean termination_request;
    private boolean has_been_run;

    /**
     * Initializes an instance of the "Voting System" user interface.
     */
    public UserInterface() {
        has_been_run = false;
        termination_request = false;
    }

    /**
     * Prompts the system user for the filename of the specific ballot file they wish to process.
     * @return A {@code String} denoting the filename of the ballot file that is to be processed.
     */
    public String requestBallotFilename() {
        String filename = "";
        if (!has_been_run) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
            GUI userInputPane = new GUI();
            int result = JOptionPane.showConfirmDialog(null, userInputPane, "Ballot File", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    filename = userInputPane.getUserInput();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                termination_request = true;
            }
            has_been_run = true;
        }
        return filename;
    }

    /**
     * Accessor method responsible for the termination_request variable of the {@code UserInterface} instance.
     * This variable denotes whether or not the user has pressed the cancel button on the GUI.
     * @return A {@code boolean} denoting whether or not the system user has exited out of the GUI
     */
    public boolean cancelButtonPressed() {
        return termination_request;
    }

    /**
     * Mutator method for the termination_request variable of the {@code UserInterface} instance.
     * @param termination_request A {@code boolean} denoting the termination request status of the user interface.
     */
    public void setTerminationRequest(boolean termination_request) {
        this.termination_request = termination_request;
    }

    /**
     * Accessor method for the has_been_run variable of the {@code UserInterface} instance.
     * This variables denotes whether or not an election had been run with the program.
     * @return A {@code string} denoting whether or not an election has been run.
     */
    public boolean getHasBeenRun() {
        return has_been_run;
    }

    /**
     * Mutator method for the has_been_run variable of the {@code UserInterface} instance.
     * @param has_been_run A {@code boolean} denoting whether or not an election has been run.
     */
    public void setHasBeenRun(boolean has_been_run) {
        this.has_been_run = has_been_run;
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
