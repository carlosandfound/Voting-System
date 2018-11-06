package elections;

import fileio.AuditFile;
import fileio.BallotFile;

public class IRElection implements Election{

    BallotFile bf;
    AuditFile af;

    public IRElection(BallotFile bf, AuditFile af) {
        this.bf = bf;
        this.af = af;
    }

    @Override
    public void runElection() {

    }

    @Override
    public void getCandidateWinners() {

    }

    @Override
    public void getPartyWinners() {

    }
}
