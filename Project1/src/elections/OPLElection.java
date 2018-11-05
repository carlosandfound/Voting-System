package elections;

import fileio.*;

import java.util.Set;

public class OPLElection implements Election {

    private int num_candidate_line_num;
    private int num_seats_line_num;
    private int candidates_line_num;
    private int num_ballots_line_num;
    private int ballot_start_line_num;
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
        this.num_candidate_line_num = 2;
        this.candidates_line_num = 3;
        this.num_seats_line_num = 4;
        this.num_ballots_line_num = 5;
    }

    public OPLElection(BallotFile bf,
                       AuditFile af,
                       int num_candidates_line_num,
                       int candidates_line_num,
                       int num_ballots_line_num,
                       int ballot_start_line_num) {
        this.bf = bf;
        this.af = af;
        this.num_candidate_line_num = num_candidates_line_num;
        this.candidates_line_num = candidates_line_num;
        this.num_ballots_line_num = num_ballots_line_num;
        this.ballot_start_line_num = ballot_start_line_num;
    }

    public int getNumCandidates() {
        return num_candidates;
    }

    public int getNumBallots() {
        return num_ballots;
    }

    public int getQuota() {
        return quota;
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

    private void populateCandidates() {

    }

    private void voteFor(int candidate_id, int ballot_id) {

    }

    private void sortPartiesOnRemainingVotes() {

    }

    private void sortCandidatesOnVotes() {

    }
}
