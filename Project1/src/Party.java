/*
 * File: Party.java
 *
 * Description: this file contains the class implementation for the Party class used in the "Voting System" product
 *
 * Authors: Justin Koo
 */

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>>Party</h1>
 * <h2>Purpose</h2>
 * The Party class provides a object model of political parties that participate in elections.
 */
public class Party {

    private String name;
    private int num_candidates;
    private int num_seats;
    private int num_votes;
    private int num_remaining_votes;
    private List<Integer> acquired_ballots;

    /**
     * Initializes an {@code Party} instance with the parameters specified.
     * @param name A {@code String} denoting the name of the party.
     * @param num_candidates A {@code int} denoting the number of candidates within the party.
     */
    public Party(String name, int num_candidates) {
        this.name = name;
        this.num_candidates = num_candidates;
        this.num_seats = 0;
        this.num_votes = 0;
        this.acquired_ballots = new ArrayList<Integer>();
    }

    /**
     * Accessor method for the name of the {@code Party} instance.
     * @return A {@code String} denoting the name of the party.
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method for the number of candidates within the {@code Party} instance.
     * @return An {@code int} denoting the number of candidates within the party.
     */
    public int getNumCandidates() {
        return num_candidates;
    }

    /**
     * Method to obtain the number of seats possessed by the {@code Party} instance.
     * @return An {@code int} denoting the number of seats possessed by the party.
     */
    public int getNumSeats() {
        return num_seats;
    }

    /**
     * Method to obtain the number of votes possessed by the {@code Party} instance.
     * @return An {@code int} denoting the number of votes possessed by the party.
     */
    public int getNumVotes() {
        return num_votes;
    }

    /**
     * Method to obtain the list of ballots acquired by the {@code Party} instance.
     * @return A {@code List<Integer>} containing all the acquired ballots of the party.
     */
    public List<Integer> getAcquiredBallots() {
        return acquired_ballots;
    }

    /**
     * Mutator method for the name of the {@code Party} instance.
     * @param name A {@code String} denoting the new name of the party.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to change the number of seats the {@code Party} instance has.
     * @param num_seats An {@code int} denoting the new number of seats for the party.
     */
    public void setNumSeats(int num_seats) { this.num_seats = num_seats; };

    public void setNumCandidates(int num_candidates) {
        this.num_candidates = num_candidates;
    }

    /**
     * Method to acquire a ballot for the {@code Party} instance.
     * @param ballot_id A {@code int} denoting the ballot ID that is to be acquired by the party.
     */
    public void acquireBallot(int ballot_id) {
        this.num_votes += 1;
        acquired_ballots.add(ballot_id);
    }
}
