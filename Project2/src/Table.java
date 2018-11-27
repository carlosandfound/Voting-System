/*
 * File: Table.java
 *
 * Description: this file contains the class implementation for the Table class used in the "Voting System" product.
 * This class is the one responsible for creating and populating the table displayed after an IRV election is run.
 *
 * Author: Carlos Alvarenga
 */

import java.util.*;

/**
 * <h1>Table</h1>
 * <h2>Purpose</h2>
 * The Table class provides methods to create, population and display the table that contains progression of votes
 * through each round of an IRV election obtain the results of an IR election.
 */

public class Table {
    private StringBuffer data;
    private int candidates_buffer;
    private int round_buffer;
    private int candidate_buffer;
    private int party_buffer;
    private int votes_buffer;
    private int update_buffer;

    // ±
    /**
     * Initializes an {@code Table} instance with paramteres initialized to default parameter values
     */
    public Table() {
        this.data = new StringBuffer();
        this.candidates_buffer = 36;    // 'Candidates' field is at least 36 characters long
        this.candidate_buffer = 25;     // 'Candidate' field is at least 25 characters long
        this.party_buffer = 10;         // 'Party' field is at least 10 characters long
        this.round_buffer = 21;         // 'Round' field is 21 characters long
        this.votes_buffer = 10;         // 'Votes' field is 10 characters long
        this.update_buffer = 10;        // 'Votes' field is 10 characters long
    }

    /**
     * Initializes an {@code Table} with the proper IR election parameters specified in {@code IRElection}.
     * @param candidates An array of {@code IRElection} instances representing candidates in the election.
     */
    public void initializeTable(Candidate[] candidates) {
        calculateCandidateFieldLength(candidates);
        calculatePartyFieldLength(candidates);
        candidates_buffer = candidate_buffer + party_buffer + 1;
    }

    /**
     * Method to obtain the populated IRV table of an {@code Table} instance.
     * @return A {@code String} containing table in string format ready to be printed to the console log.
     */
    @Override
    public String toString() {
        return data.toString();
    }

    /*
     * ALL private "helper" methods are below
     */

    private String padRight(String s, int n) {
        n += s.length();
        return String.format("%1$-" + n + "s", s);
    }

    private String padLeft(String s, int n) {
        n += s.length();
        return String.format("%1$" + n + "s", s);
    }

    private void calculateCandidateFieldLength(Candidate[] candidates) {
        for (Candidate c: candidates) {
            if (c.getName().length() > candidate_buffer)
                candidates_buffer = c.getName().length();
        }
    }

    private void calculatePartyFieldLength(Candidate[] candidates) {
        for (Candidate c: candidates) {
            if (c.getParty().length() > party_buffer)
                party_buffer = c.getParty().length();
        }
    }
}

