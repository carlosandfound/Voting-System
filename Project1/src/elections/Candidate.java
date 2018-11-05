package elections;

import java.util.ArrayList;
import java.util.List;

public class Candidate {

    private String name;
    private String party;
    private int num_votes;
    private List<Integer> acquired_ballots;

    public Candidate(String name, String party) {
        this.name = name;
        this.party = party;
        this.num_votes = 0;
        acquired_ballots = new ArrayList<Integer>();
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public int getNum_votes() {
        return num_votes;
    }

    public List<Integer> getAcquired_ballots() {
        return acquired_ballots;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void acquireBallot(int ballot_id) {
        num_votes += 1;
        acquired_ballots.add(ballot_id);
    }
}
