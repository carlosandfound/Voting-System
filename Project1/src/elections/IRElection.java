package elections;

import fileio.BallotFile;

import java.util.Set;

public class IRElection implements Election {

    BallotFile bf;

    public IRElection(BallotFile bf) {
        this.bf = bf;
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
