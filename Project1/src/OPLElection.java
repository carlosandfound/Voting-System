/*
 * File: OPLElection.java
 *
 * Description: this file contains the class implementation for the OPLElection class used in the "Voting System" product
 *
 * Authors: Justin Koo, Michael McLaughlin
 */

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * <h1>OPLElection</h1>
 * <h2>Purpose</h2>
 * The OPLElection class provides methods to obtain the results of an OPL election specified by some {@code BallotFile}.
 */
public class OPLElection implements Election {

    private static final int num_candidate_line_num = 2;
    private static final int candidates_line_num = 3;
    private static final int num_seats_line_num = 4;
    private static final int num_ballots_line_num = 5;
    private static final int ballot_start_line_num = 6;
    private BallotFile bf;
    private int num_candidates;
    private int num_seats;
    private int num_ballots;
    private int quota;
    private Candidate[] candidates;
    private Party[] parties;
    private Set<Candidate> candidate_winners;
    private Set<Party> party_winners;
    private boolean has_been_run;

    /**
     * Initializes an {@code OPLElection} with the proper OPL election parameters specified in {@code bf}.
     * @param bf A {@code BallotFile} object representing the ballot file to be considered.
     */
    public OPLElection(BallotFile bf) {
        this.bf = bf;
        this.has_been_run = false;
        this.num_candidates = Integer.parseInt(bf.getLine(num_candidate_line_num));
        this.num_seats = Integer.parseInt(bf.getLine(num_seats_line_num));
        this.num_ballots = Integer.parseInt(bf.getLine(num_ballots_line_num));
        this.quota = this.num_ballots / this.num_seats; // TODO: divide by zero error possible here
        populateCandidatesAndParties();
    }

    /**
     * Method to obtain the number of candidates present in an {@code OPLElection} instance.
     * @return An {@code int} denoting the number of candidates in the election.
     */
    public int getNumCandidates() {
        return num_candidates;
    }

    /**
     * Method to obtain the number of seats available in an {@code OPLElection} instance.
     * @return An {@code int} denoting the number of seats available in the election.
     */
    public int getNumSeats() {
        return num_seats;
    }

    /**
     * Method to obtain the number of ballots cast in an {@code OPLElection} instance.
     * @return An {@code int} denoting the number of ballots cast in the election.
     */
    public int getNumBallots() {
        return num_ballots;
    }

    /**
     * Method to obtain the quota for an {@code OPLElection} instance. The quota denotes the number of ballots any party
     * must obtain in order to receive 1 additional seat.
     * @return An {@code int} denoting the quota for the election.
     */
    public int getQuota() {
        return quota;
    }

    /**
     * Method to obtain all candidates present in an {@code OPLElection} instance.
     * @return A {@code Candidate[]} containing all {@code Candidate} objects present in the election.
     */
    public Candidate[] getCandidates() {
        return candidates;
    }

    /**
     * Method to obtain all parties present in an {@code OPLElection} instance.
     * @return A {@code Party[]} containing all {@code Party} objects present in the election.
     */
    public Party[] getParties() {
        return parties;
    }

    /**
     * Method to obtain the results of an {@code OPLElection} instance.
     * @return A {@code String} containing a short description of the election parameters as well as winners.
     */
    @Override
    public String toString() {
        if (!has_been_run) {
            runElection();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(electionInfoToString());
        sb.append(partyWinnersInfoToString());
        sb.append(candidateWinnersInfoToString());
        return sb.toString();
    }

    //TODO: Implement runElection()
    // Preconditions: an instance of OPLElection has been constructed with a valid BallotFile
    /*
        Postconditions:
            Each Candidate within "candidates" has the correct number of votes and list of acquired ballots.
            Each Party within "parties" has the correct number of votes, list of acquired ballots, and number of seats
            Member variable "candidate_winners" contains all candidates that have acquired a seat.
            Member variable "party_winners" contains all parties that have at least 1 seat.
     */

    /**
     * Method to run the primary algorithm of OPL elections. The {@code OPLElection} class is designed such that
     * the algorithm is executed only once per instance of {@code OPLElection}.
     */
    @Override
    public void runElection() {
        if (has_been_run) {
            // Prevent "running" the election more than once
            return;
        }
        /*
            Algorithm for OPL Elections goes here.
         */
        has_been_run = true;
    }

    /**
     * Method to obtain the winning candidates of an {@code OPLElection} instance. A candidate is determined to be a winner
     * if it has won a seat in the election.
     * @return A {@code Set} containing all {@code Candidate} objects that have been determined to be winners.
     */
    @Override
    public Set<Candidate> getCandidateWinners() {
        if (!has_been_run) {
            runElection();
        }
        return candidate_winners;
    }

    /**
     * Method to obtain the winning parties of an {@code OPLElection} instance. A party is determined to be a winner if
     * it has won at least 1 seat in the election.
     * @return A {@code Set} containing all {@code Party} objects that have been determined to be winners.
     */
    @Override
    public Set<Party> getPartyWinners() {
        if (!has_been_run) {
            runElection();
        }
        return party_winners;
    }

    private String electionInfoToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Election Type: OPL\n");
        sb.append("Number of candidates: ").append(num_candidates).append("\n");
        sb.append("Candidates: ");
        for (int i = 0; i < getNumCandidates(); i++) {
            Candidate c = candidates[i];
            sb.append("[").append(c.getName()).append(", ").append(c.getParty()).append("]");
            if (i < num_candidates - 1) {
                sb.append(", ");
            }
        }
        sb.append("\n");
        sb.append("Number of available seats: ").append(num_seats).append("\n");
        sb.append("Number of ballots cast: ").append(num_ballots).append("\n");
        return sb.toString();
    }

    private String partyWinnersInfoToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Winning parties (atleast 1 seat won):");
        int party_num = 0;
        for (Party p : party_winners) {
            sb.append(p.getName()).append(": ").append(p.getNumSeats()).append(" seats won");
            sb.append("\n");
            party_num++;
        }
        return sb.toString();
    }

    private String candidateWinnersInfoToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Winning candidates:");
        int candidate_num = 0;
        for (Candidate c : candidate_winners) {
            sb.append(c.getName());
            sb.append("\n");
            candidate_num++;
        }
        return sb.toString();
    }

    private void populateCandidatesAndParties() {
        this.candidates = new Candidate[this.num_candidates];
        Map<String, Integer> party_candidate_counts = new LinkedHashMap<>(); // LinkedHashMap to preserve ordering of key insertions
        String candidate_line = bf.getLine(candidates_line_num);
        String[] names_and_parties = candidate_line.split("]");
        for (int i = 0; i < names_and_parties.length; i++) {
            String candidate = names_and_parties[i]; //, [Foster,D
            candidate = candidate.trim();
            candidate = candidate.substring(1); // [Foster,D
            int opening_square_bracket_index = candidate.indexOf("[");
            candidate = candidate.substring(opening_square_bracket_index + 1); //Foster,D
            String[] name_and_party = candidate.split(","); //[Foster, D]
            String name = name_and_party[0].trim();
            String party = name_and_party[1].trim();
            candidates[i] = new Candidate(name, party);
            if (party_candidate_counts.containsKey(party)) {
                party_candidate_counts.put(party, party_candidate_counts.get(party) + 1);
            } else {
                party_candidate_counts.put(party, 1);
            }
        }
        this.parties = new Party[party_candidate_counts.size()];
        int party_id = 0;
        for (String party : party_candidate_counts.keySet()) {
            parties[party_id] = new Party(party, party_candidate_counts.get(party));
            party_id++;
        }
    }

    /*
        Possible good helper methods to use within "runElection()"
     */
    private void voteFor(int candidate_id, int ballot_id) {

    }

    private void sortPartiesOnRemainingVotes() {

    }

    private void sortCandidatesOnVotes() {

    }
}