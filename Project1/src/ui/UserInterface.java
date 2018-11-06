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

    private String requestBallotFilename() {
        System.out.println("Enter the ballot file you wish to use.");
        return reader.nextLine();
    }

    public void displayResults(Election e) {

    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        String ballot_filename = ui.requestBallotFilename();
        BallotFile bf = new BallotFile(ballot_filename);
        // TODO: Automatically generate a name for auditfile based on timestamp
        AuditFile af = new AuditFile("TODO");
        Election e;
        if (bf.getElectionType().equals("OPL")) {
            e = new OPLElection(bf, af);
        } else if (bf.getElectionType().equals("IRV")) {
            e = new IRElection(bf, af);
        } else {
            throw new IllegalStateException("Election types must be either OPL or IRV");
        }
        e.runElection();
        ui.displayResults(e);
    }
}
