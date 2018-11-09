import java.util.Set;

public class IRElection implements Election {

    BallotFile bf;
    int num_candidates_line_num;
    int candidates_line_num;
    int num_ballots_line_num;
    int ballots_start_line_num;
    int num_candidates;
    int num_ballots;
    int majority_criteria;
    int[][] ballots;
    Candidate[] candidates;
    Set<Integer> surviving_candidate_ids;
    Set<Candidate> surviving_candidates;
    Set<Party> surviving_parties;


    public IRElection(BallotFile bf) {
        this.bf = bf;
        num_candidates_line_num = 2;
        candidates_line_num = 3;
        num_ballots_line_num = 4;
        ballots_start_line_num = 5;
        num_candidates = Integer.parseInt(bf.getLine(num_candidates_line_num));
        num_ballots = Integer.parseInt(bf.getLine(num_ballots_line_num));
        majority_criteria = (num_ballots/2) + 1;
    }

    @Override
    public void runElection() {
        this.populateCandidates();
    }

    @Override
    public Set<Candidate> getCandidateWinners() {
        return null;
    }

    @Override
    public Set<Party> getPartyWinners() {
        return null;
    }

    public int getNumCandidates() { return num_candidates; }

    public int getNumBallots() { return num_ballots; }

    public int getMajorityCriteria() { return majority_criteria; }

    private void populateCandidates() {
        String candidates_str = bf.getLine(candidates_line_num);
        String[] candidates_split = candidates_str.split(", ");

        System.out.println("-"+candidates_split[0]+"-");
        System.out.println("-"+candidates_split[1]+"-");
        System.out.println("-"+candidates_split[2]+"-");
        System.out.println("-"+candidates_split[3]+"-");

        for (int i = 0; i < num_candidates; i++) {
            String[] candidate_split = candidates_split[i].split(" ");
            //String[] parties_split = candidate_split[i+1].split("");
            String party = candidate_split[1].replaceAll("[(,)]", "");
            //System.out.println(candidate_split[0]);
            //System.out.println(party);
            candidates[i] = new Candidate(candidate_split[0], party);
            candidates[i].getName();
            candidates[i].getParty();
            System.out.println("------");
        }
    }

    private void VoteFor(int candidates_id, int ballot_id) {

    }

    private int determineLoser() {
        return 0;
    }

    private void reallocateVotes(int candidates_id) {

    }

    private boolean hasWinner() {
        return false;
    }

    private int randomLoser(Set<Integer> losers) {
        return 0;
    }

}
