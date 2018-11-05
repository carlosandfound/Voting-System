package ui;

import elections.Election;
import elections.IRElection;
import elections.OPLElection;
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

        // TODO: Get the current time and use it as the audit_filename
        String audit_filename = "TODO";


        bf = new BallotFile(ballot_filename);
        af = new AuditFile(audit_filename);

        if (bf.getElectionType().equals("IRV")) {

        } else if (bf.getElectionType().equals("OPL")) {
            e = new OPLElection(bf, af);
        }
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
