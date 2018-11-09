import elections.*;
import fileio.*;
import ui.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

public class VotingSystem {

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        boolean not_valid_file = true;
        Election el = null;
        while (not_valid_file) {
            String ballot_filename = ui.requestBallotFilename(System.out, System.in);
            File ballot_file = new File(FileSystems.getDefault().getPath("src", ballot_filename).toString());
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
                // TODO: handle cases in which an IOException is thrown, e.g. when the filename entered does not exist
                ui.displayExceptionMessage(e, System.out);
            }
        }
        ui.displayResults(el, System.out);
    }
}
