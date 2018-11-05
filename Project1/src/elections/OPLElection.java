package elections;

import fileio.*;

import java.util.Set;

public class OPLElection implements Election {

    private static int num_candidate_line_num;
    private static int num_seats_line_num;
    private static int candidates_line_num;
    private static int num_ballots_line_num;
    private static int ballot_start_line_num;
    private BallotFile bf;
    private AuditFile af;
    private int num_candidates;
    private int num_seats;
    private int num_ballots;
    private int quota;
    private Candidate[] candidates;
    private Party[] parties;
    private Set<Candidate> winning_candidates;
    private Set<Party> winning_parties;

    public OPLElection(BallotFile bf, AuditFile af) {
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
