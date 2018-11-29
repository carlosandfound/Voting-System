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
    private StringBuilder table;
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
        this.table = new StringBuilder();
        this.candidates_buffer = 24;    // 'Candidates' field is at least 36 characters long
        this.candidate_buffer = 14;     // 'Exhausted Pile' field is 14 characters long
        this.party_buffer = 5;          // 'Party' field is at least 10 characters long
        this.round_buffer = 17;         // 'Round' field is 21 characters long
        this.votes_buffer = 7;          // 'Votes' field is 6 characters long
        this.update_buffer = 7;         // 'Votes' field is 7 characters long
    }

    /**
     * Creates string representation a {@code Table} and populates it with the correct value corresponding with the proper IR election parameters specified in {@code IRElection}.
     * @param candidates An array of {@code IRElection} instances representing candidates in the election.
     */
    public void populate(Candidate[] candidates, List<Integer> exhausted_totals, List<Integer> exhausted_updates, int total_votes) {
        calculateCandidateFieldLength(candidates);
        calculatePartyFieldLength(candidates);
        int buffer = candidate_buffer + party_buffer + 5;
        if (buffer > candidates_buffer)
            candidates_buffer = buffer;
        int rounds = exhausted_totals.size();
        populateFirstRow(candidates, rounds);
        populateSecondRow(rounds);
        populateCandidateRows(candidates, total_votes, rounds);
        populateExhaustedPileRow(exhausted_totals, exhausted_updates, rounds);
        populateTotalsRow(total_votes, rounds);
    }

    /**
     * Method to obtain the populated IRV table of an {@code Table} instance.
     * @return A {@code String} containing table in string format ready to be printed to the console log.
     */
    @Override
    public String toString() {
        return table.toString();
    }

    /*
     * ALL private "helper" methods are below
     */

    public String padString(String s, int n, String padding) {
        if (n > 0) {
            return String.format("%1$-" + n + "s", s).replaceAll(" ", padding);
        }
        return "";
    }

    private void calculateCandidateFieldLength(Candidate[] candidates) {
        for (Candidate c: candidates) {
            if (c.getName().length() > candidate_buffer)
                candidate_buffer = c.getName().length();
        }
    }

    private void calculatePartyFieldLength(Candidate[] candidates) {
        for (Candidate c: candidates) {
            if (c.getParty().length() > party_buffer)
                party_buffer = c.getParty().length();
        }
    }

    private void populateFirstRow(Candidate[] candidates, int rounds) {
        table.append("+--").append(padString("", candidates_buffer, "-")).append("--+");
        for (int i = 1; i <= rounds; i++) {
            table.append("-").append(padString("", round_buffer, "-")).append("-+");
        }
        String candidates_title = "Candidates";
        int length = candidates_title.length();
        int left_pad = (candidates_buffer - length) / 2;
        int right_pad = candidates_buffer - left_pad - length;
        table.append("\n|  ").append(padString("", left_pad, " "));
        table.append("Candidates").append(padString("", right_pad, " ")).append("  |");
        for (int i = 1; i <= rounds; i++) {
            String round_title = "Round " + Integer.toString(i);
            length = round_title.length();
            left_pad = (round_buffer - length) / 2;
            right_pad = round_buffer - left_pad - length;
            table.append(" ").append(padString("", left_pad, " "));
            table.append(round_title).append(padString("", right_pad, " ")).append(" |");
        }
    }

    private void populateSecondRow(int rounds) {
        createInnerBorder(rounds);
        String candidate_title = "Candidate";
        int length = candidate_title.length();
        int left_pad = (candidate_buffer - length) / 2;
        int right_pad = candidate_buffer - left_pad - length;
        table.append("\n|  ").append(padString("", left_pad, " "));
        table.append("Candidate").append(padString("", right_pad, " ")).append("  |");
        String party_title = "Party";
        length = party_title.length();
        left_pad = (party_buffer - length) / 2;
        right_pad = party_buffer - left_pad - length;
        table.append("  ").append(padString("", left_pad, " "));
        table.append("Party").append(padString("", right_pad, " ")).append("  |");
        for (int i = 1; i <= rounds; i++) {
            table.append("  Votes  |    ±    |");
        }
        createInnerBorder(rounds);
    }

    private void populateCandidateRows(Candidate[] candidates, int votes, int rounds) {
        for (Candidate c: candidates) {
            String name = c.getName();
            String party = c.getParty();
            List<Integer> total_votes = c.getTotalRoundVotes();
            List<Integer> update_votes = c.getUpdatedRoundVotes();
            int buffer = candidate_buffer - name.length();
            table.append("\n|").append(name).append(padString("", buffer, " ")).append("    |");
            buffer = party_buffer - party.length();
            table.append(party).append(padString("", buffer, " ")).append("    |");
            populateVoteField(total_votes, update_votes, rounds);
            createInnerBorder(rounds);
        }
    }

    private void populateExhaustedPileRow(List<Integer> exhausted_totals, List<Integer> exhausted_updates, int rounds) {
        String exhausted_pile_title = "Exhausted Pile";
        int length = exhausted_pile_title.length();
        int buffer = candidate_buffer - length; // 11-14
        table.append("\n|").append("Exhausted Pile").append(padString("", buffer, " ")).append("    |");
        table.append(padString("", party_buffer, " ")).append("    |");
        populateVoteField(exhausted_totals, exhausted_updates, rounds);
        createInnerBorder(rounds);
    }

    private void populateTotalsRow(int votes, int rounds) {
        String totals_title = "Totals";
        int length = totals_title.length();
        String votes_str = Integer.toString(votes);
        String update_votes_str = "+" + Integer.toString(votes);
        int buffer = candidate_buffer - length;
        table.append("\n|").append(totals_title).append(padString("", buffer, " ")).append("    |");
        table.append(padString("", party_buffer, " ")).append("    |");
        buffer = votes_buffer - votes_str.length();
        table.append("  ").append(padString("", buffer, " ")).append(votes_str).append("|");
        buffer = votes_buffer - update_votes_str.length();
        table.append("  ").append(padString("", buffer, " ")).append(update_votes_str).append("|");
        for (int i = 1; i < rounds; i++) {
            if (i != rounds - 1)
                table.append(padString("", round_buffer+3, " "));
            else
                table.append(padString("", round_buffer+2, " ")).append("|");
        }
        table.append("\n+").append(padString("", candidate_buffer, "-")).append("----");
        table.append("+").append(padString("", party_buffer, "-")).append("----");
        table.append("+").append(padString("", votes_buffer, "-")).append("--");
        table.append("+").append(padString("", update_buffer, "-")).append("--+");
        for (int i = 1; i < rounds; i++) {
            if (i != rounds - 1)
                table.append(padString("", round_buffer+3, "-"));
            else
                table.append(padString("", round_buffer+2, "-")).append("+");
        }
    }

    private void populateVoteField(List<Integer> total_votes, List<Integer> update_votes, int rounds) {
        for (int i = 0; i < rounds; i++) {
            String total_votes_str = Integer.toString(total_votes.get(i));
            String update_votes_str;
            int num_votes_updates = update_votes.get(i);
            if (num_votes_updates > 0)
                update_votes_str = "+" + Integer.toString(num_votes_updates);
            else
                update_votes_str = Integer.toString(num_votes_updates);
            int buffer = votes_buffer - total_votes_str.length();
            table.append("  ").append(padString("", buffer, " ")).append(total_votes_str).append("|");
            buffer = update_buffer - update_votes_str.length();
            table.append("  ").append(padString("", buffer, " ")).append(update_votes_str).append("|");
        }
    }

    public void createInnerBorder(int rounds) {
        table.append("\n+--").append(padString("", candidate_buffer, "-")).append("--+");
        table.append("--").append(padString("", party_buffer, "-")).append("--+");
        for (int i = 1; i <= rounds; i++) {
            table.append("---------+---------+");
        }
    }
}

