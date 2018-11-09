package elections;

import fileio.*;

import java.util.Set;

public class OPLElection implements Election {

    private static int num_candidate_line_num = 2;
    private static int num_seats_line_num = 3;
    private static int candidates_line_num = 4;
    private static int num_ballots_line_num = 5;
    private static int ballot_start_line_num;
    private BallotFile bf;
    private int num_candidates;
    private int num_seats;
    private int num_ballots;
    private int quota;
    private Candidate[] candidates;
    private Party[] parties;
    private Set<Candidate> winning_candidates;
    private Set<Party> winning_parties;

    private boolean has_been_run;

    public OPLElection(BallotFile bf) {
        this.bf = bf;
        this.has_been_run = false;
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
        if (has_been_run) {
            return;
        }

        has_been_run = true;
    }

    @Override
    public Set<Candidate> getCandidateWinners() {
        if (!has_been_run) {
            runElection();
        }
        return null;
    }

    @Override
    public Set<Party> getPartyWinners() {
        if (!has_been_run) {
            runElection();
        }
        return null;
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
