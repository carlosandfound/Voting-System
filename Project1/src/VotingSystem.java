import elections.*;
import fileio.*;
import ui.*;

public class VotingSystem {

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
