package ui;

import elections.Election;
import elections.IRElection;
import elections.OPLElection;
import fileio.*;
import java.util.Scanner;

public class UserInterface {

    private Scanner reader;

    public UserInterface() {
        reader = new Scanner(System.in);
    }

    public String requestBallotFilename() {
        System.out.println("Enter the ballot file you wish to use.");
        return reader.nextLine();
    }

    public void displayResults(Election e) {

    }
}
