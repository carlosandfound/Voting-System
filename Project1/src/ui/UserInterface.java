package ui;

import elections.Election;
import fileio.*;
import java.util.Scanner;

public class UserInterface {

    private Scanner reader;
    private BallotFile bf;
    private AuditFile af;
    private Election e;

    public UserInterface() {
        reader = new Scanner(System.in);
        String ballot_filename = requestBallotFilename();
    }

    public String requestBallotFilename() {
        System.out.println("Enter the ballot file you wish to use.");
        return reader.nextLine();
    }

    public void displayResults() {

    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
    }
}
