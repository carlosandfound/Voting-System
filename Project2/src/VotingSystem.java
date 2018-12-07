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

/**
 * <h1>VotingSystem</h1>
 * <h2>Purpose</h2>
 * The VotingSystem class is the wrapper class for the "main()" method of the "Voting System" product.
 */
public class VotingSystem {

    /**
     * Provides an entry point for the execution of the "Voting System" product.
     * @param args A {@code String[]} specifying arguments provided during program execution. Currently the first
     * argument (if any) specifies the file name for the election file.
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        boolean valid_file = false;
        Election el = null;
        while (!valid_file) {
            ui = new UserInterface();
            valid_file = true;
            String ballot_filename;
            if (args.length == 0)
                ballot_filename = ui.requestBallotFilename();
            else
                ballot_filename = args[0];
            if (!ui.cancelButtonPressed()) {
                File ballot_file = new File(ballot_filename);
                try {
                    BallotFile bf = new BallotFile(ballot_file);
                    if (bf.getElectionType().equals("OPL")) {
                        el = new OPLElection(bf);
                    } else if (bf.getElectionType().equals("IR")) {
                        el = new IRElection(bf);
                    } else {
                        ui.displayInvalidElectionType(bf.getElectionType(), System.out);
                        valid_file = false;
                        if (args.length != 0) return;
                    }
                } catch (IOException e) {
                    ui.displayExceptionMessage(e, System.out);
                    valid_file = false;
                    if (args.length != 0) return;
                }
            }
        }
        if (!ui.cancelButtonPressed())
            ui.displayResults(el, System.out);
    }
}
