/*
 * File: Candidate.java
 *
 * Description: this file contains the class implementation for the Candidate class used in the "Voting System" product
 *
 * Author: Carlos Alvarenga, Justin Koo
 */
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Candidate</h1>
 * <h2>Purpose</h2>
 * The Candidate class provides a object model of candidates that participate in elections.
 */
public class Candidate {

    private String name;
    private String party;
    private int num_votes;
    private List<Integer> acquired_ballots;
    private List<Integer> total_round_votes;
    private List<Integer> update_round_votes;

    /**
     * Intializes a {@code Candidate} instance with the parameters specified.
     * @param name A {@code String} denoting the name of the candidate.
     * @param party A {@code String} denoting the name of the party that the candidate belongs to.
     */
    public Candidate(String name, String party) {
        this.name = name;
        this.party = party;
        this.num_votes = 0;
        acquired_ballots = new ArrayList<Integer>();
        total_round_votes = new ArrayList<Integer>();
        update_round_votes = new ArrayList<Integer>();
    }

    /**
     * Accessor method for the name of the {@code Candidate} instance.
     * @return A {@code string} denoting the name of the candidate.
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method for the name of the party the {@code Candidate} instance belongs to.
     * @return A {@code string} denoting the name of the party the candidate belongs to.
     */
    public String getParty() {
        return party;
    }

    /**
     * Method to obtain the number of votes the {@code Candidate} instance possesses.
     * @return An {@code int} denoting the number of votes the candidate possesses.
     */
    public int getNumVotes() {
        return num_votes;
    }

    /**
     * Method to obtain a list of all ballots acquired by the {@code Candidate} instance.
     * @return A {@code List<Integer>} containing all ballot IDS the candidate possesses.
     */
    public List<Integer> getAcquiredBallots() {
        return acquired_ballots;
    }

    /**
     * Method to acquire the list of total votes per round for the {@code Candidate} instance.
     * @return A {@code List<Integer>} containing all votes a candidate possesses for each round.
     */
    public List<Integer> getTotalRoundVotes() {
        return total_round_votes;
    }

    /**
     * Method to acquire the list of the number of votes that are added and/or subtracted each round for the {@code Candidate} instance.
     * @return A {@code List<Integer>} containing all added and/or subtracted votes a candidate possesses for each round.
     */
    public List<Integer> getUpdatedRoundVotes() {
        return update_round_votes;
    }

    /**
     * Mutator method for the name of the {@code Candidate} instance.
     * @param name A {@code String} denoting the new name of the candidate.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator method for the name of the party the {@code Candidate} instance belongs to.
     * @param party A {@code String} denoting the name of the party the candidate belongs to.
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     * Method to acquire possession of a ballot for the {@code Candidate} instance.
     * @param ballot_id An {@code int} denoting the ballot ID that is to be acquired by the candidate.
     */
    public void acquireBallot(int ballot_id) {
        num_votes ++;
        acquired_ballots.add(ballot_id);
    }

    /**
     * Method to remove a vote for the {@code Candidate} instance.
     */
    public void removeVote() {
        if (num_votes > 0)
            num_votes--;
    }

    /**
     * Method to update (1) the total number of votes and (2) number of added/subtracted votes since last round that a
     * {@code Candidate} instance currently possess as of the most recently finished round
     * @param votes An {@code int} denoting the total number of votes that the candidate currently possesses
     */
    public void updateRoundVotes(int votes) {
        int size = total_round_votes.size();
        total_round_votes.add(votes);
        if (size == 0) { // number of added votes is the same as total votes since it's the first round
            update_round_votes.add(votes);
        } else {
            int update_votes = votes - total_round_votes.get(size-1); // number of votes added or subtracted since last round
            update_round_votes.add(update_votes);
        }
    }
}
