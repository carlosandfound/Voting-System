package elections;

import fileio.AuditFile;
import fileio.BallotFile;

import java.util.Set;

public class IRElection implements Election {

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
    public Set<Candidate> getCandidateWinners() {
        return null;
    }

    @Override
    public Set<Party> getPartyWinners() {
        return null;
    }
}
