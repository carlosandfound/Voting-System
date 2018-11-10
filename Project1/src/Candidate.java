/*
 * File: Candidate.java
 *
 * Description: this file contains the class implementation for the Candidate class used in the "Voting System" product
 *
 * Author: Carlos Alvarenga, Justin Koo
 */
import java.util.ArrayList;
import java.util.List;

public class Candidate {

    private String name;
    private String party;
    private int num_votes;
    private int first_place_votes;
    private List<Integer> acquired_ballots;
    private List<Integer> acquired_first_place_ballots;
    private List<Integer>[] irv_acquired_votes;

    public Candidate(String name, String party) {
        this.name = name;
        this.party = party;
        this.num_votes = 0;
        this.first_place_votes = 0;
        acquired_ballots = new ArrayList<Integer>();
        acquired_first_place_ballots = new ArrayList<Integer>();
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public int getNumVotes() { return num_votes; }

    public int getNumFirstPlaceVotes() {
        return first_place_votes;
    }

    public List<Integer> getAcquiredBallots() {
        return acquired_ballots;
    }

    public List<Integer> getAcquiredFirstPlaceBallots() {
        return acquired_first_place_ballots;
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

    /*public void acquireFirstPlaceBallot(int ballot_id) {
        first_place_votes += 1;
        acquired_first_place_ballots.add(ballot_id);
    }*/
}
