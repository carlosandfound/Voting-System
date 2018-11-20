/*
 * File: VotingSystem.java
 *
 * Description: this file contains the class that contains the "main()" method of the "Voting System" product. This class
 * is the main driver of the entire program.
 *
 * Author: Carlos Alvarenga, Justin Koo
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * <h1>VotingSystem</h1>
 * <h2>Purpose</h2>
 * The VotingSystem class is the wrapper class for the "main()" method of the "Voting System" product.
 */
public class VotingSystem {

    /**
     * Provides an entry point for the execution of the "Voting System" product.
     * @param args A {@code String[]} specifying arguments provided during program execution. Currently none are required.
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        boolean not_valid_file = true;
        Election el = null;
        while (not_valid_file) {
            String ballot_filename = ui.requestBallotFilename(System.out, System.in);
            File ballot_file = new File(ballot_filename);
            try {
                BallotFile bf = new BallotFile(ballot_file);
                if (bf.getElectionType().equals("OPL")) {
                    el = new OPLElection(bf);
                    not_valid_file = false;
                } else if (bf.getElectionType().equals("IR")) {
                    el = new IRElection(bf);
                    not_valid_file = false;
                } else {
                    ui.displayInvalidElectionType(bf.getElectionType(), System.out);
                }
            } catch (IOException e) {
                ui.displayExceptionMessage(e, System.out);
            }
        }
        ui.displayResults(el, System.out);
    }
}
