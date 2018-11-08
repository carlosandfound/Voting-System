import elections.*;
import fileio.*;
import ui.*;

import java.io.File;
import java.nio.file.FileSystems;

public class VotingSystem {

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        String ballot_filename = ui.requestBallotFilename("Enter a ballot filename", System.out, System.in);
        File ballot_file = new File(FileSystems.getDefault().getPath("src", ballot_filename).toString());
        BallotFile bf = new BallotFile(ballot_file);
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
        ui.displayResults(e, System.out);
    }
}
