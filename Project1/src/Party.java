import java.util.ArrayList;
import java.util.List;

public class Party {

    private String name;
    private int num_candidates;
    private int num_seats;
    private int num_votes;
    private int num_remaining_votes;
    private List<Integer> acquired_ballots;

    public Party(String name, int num_candidates) {
        this.name = name;
        this.num_candidates = num_candidates;
        this.num_seats = 0;
        this.num_votes = 0;
        this.acquired_ballots = new ArrayList<Integer>();
    }

    public String getName() {
        return name;
    }

    public int getNumCandidates() {
        return num_candidates;
    }

    public int getNumSeats() {
        return num_seats;
    }

    public int getNumVotes() {
        return num_votes;
    }

    public List<Integer> getAcquiredBallots() {
        return acquired_ballots;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumSeats(int num_seats) { this.num_seats = num_seats; };

    public void setNumCandidates(int num_candidates) {
        this.num_candidates = num_candidates;
    }

    public void acquireBallot(int ballot_id) {
        this.num_votes += 1;
        acquired_ballots.add(ballot_id);
    }
}
