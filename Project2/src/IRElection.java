/*
 * File: IRElection.java
 *
 * Description: this file contains the class implementation for the IRElection class used in the "Voting System" product. This class is the one responsible for running an instant-runoff election.
 *
 * Author: Carlos Alvarenga
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <h1>IRElection</h1>
 * <h2>Purpose</h2>
 * The IRElection class provides methods to obtain the results of an IR election specified by some {@code BallotFile}.
 */

public class IRElection implements Election {
    private BallotFile bf;
    private int candidates_line_num;
    private int ballots_start_line_num;
    private int num_candidates;
    private int num_ballots;
    private int quota;
    private int minority_criteria;      // the number of votes that the losing candidate has (i.e. minimum number of votes)
    private int winning_candidate_id;
    private boolean has_been_run;
    private StringBuffer audit_data;
    private StringBuffer audit_filename;
    private StringBuffer invalidated_data;
    private StringBuffer invalidated_filename;

    private int[][] ballots;
    private Candidate[] candidates;
    private Set<Integer> surviving_candidate_ids;   // contains the ids of the candidates who haven't lost the election yet
    private Set<Candidate> winning_candidate;       // set of sole winning candidate to conform to interface method
    private Set<Party> winning_party;               // set of party of sole winning candidate to conform to interface method
    private List<Integer> loser_ballot_ids;
    private List<Integer> votes;                    // list of cumulative votes for each candidate

    /**
     * Initializes an {@code IRElection} with the proper IR election parameters specified in {@code bf}.
     * @param bf A {@code BallotFile} object representing the ballot file to be considered.
     */
    public IRElection(BallotFile bf) {
        this.bf = bf;
        int num_candidates_line_num = 2;
        this.candidates_line_num = 3;
        int num_ballots_line_num = 4;
        this.ballots_start_line_num = 5;
        this.num_candidates = Integer.parseInt(bf.getLine(num_candidates_line_num));
        this.num_ballots = Integer.parseInt(bf.getLine(num_ballots_line_num));
        this.quota = (num_ballots/2) + 1;
        this.minority_criteria = quota;
        this.audit_data = new StringBuffer();
        this.audit_filename = new StringBuffer();
        this.invalidated_data = new StringBuffer();
        this.invalidated_filename = new StringBuffer();
        this.ballots = new int[num_ballots][num_candidates];
        this.candidates = new Candidate[num_candidates];
        this.winning_candidate_id = -1;
        this.has_been_run = false;
        this.surviving_candidate_ids = new HashSet<Integer>();
        this.winning_candidate = new HashSet<Candidate>();
        this.winning_party = new HashSet<Party>();
        this.loser_ballot_ids = new ArrayList<Integer>();
        this.votes = new ArrayList<Integer>();
    }

    /**
     * Method to obtain the winning candidate of an {@code IRElection} instance. A candidate is determined to be a winner
     * if it has won a majority of the votes in the election or been determined to be a winner by a "coin toss" in a scenario
     * where there's a tie between two candidates
     * @return A {@code Set} containing all {@code Candidate} objects that have been determined to be winners.
     */
    @Override
    public Set<Candidate> getCandidateWinners() {
        if (!has_been_run) {
            runElection();
        }
        return winning_candidate;
    }

    /**
     * Method to obtain the party of the winning candidate of an {@code IRElection} instance.
     * @return A {@code Set} containing all {@code Party} objects that have been determined to be winners.
     */
    @Override
    public Set<Party> getPartyWinners() {
        if (!has_been_run) {
            runElection();
        }
        return winning_party;
    }

    /**
     * Method to obtain the results of an {@code IRElection} instance.
     * @return A {@code String} containing a short description of the election parameters as well as the winner.
     */
    @Override
    public String toString() {
        if (!has_been_run) {
            runElection();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(electionInfotoString());
        sb.append("Winning candidate: ");
        sb.append(winning_candidate.iterator().next().getName());
        sb.append(" (").append(winning_party.iterator().next().getName()).append(")");
        int winning_votes = winning_candidate.iterator().next().getNumVotes();
        sb.append(" with ").append(winning_votes);
        if (winning_votes == 1)
            sb.append(" vote\n");
        else
            sb.append(" votes\n");
        sb.append("The audit file '").append(audit_filename).append("'").append(" has been generated\n");
        sb.append("The invalidated ballots file '").append(invalidated_filename).append("'").append(" has been generated\n");
        return sb.toString();
    }

    /**
     * Method to obtain the number of candidates present in an {@code IRElection} instance.
     * @return An {@code int} denoting the number of candidates in the election.
     */
    public int getNumCandidates() {
        return num_candidates;
    }

    /**
     * Method to obtain the number of ballots cast in an {@code IRElection} instance.
     * @return An {@code int} denoting the number of ballots cast in the election.
     */
    public int getNumBallots() {
        return num_ballots;
    }

    /**
     * Method to obtain the quota for an {@code IRElection} instance. The quota denotes the number of ballots any candidate
     * must obtain in order to win the election.
     * @return An {@code int} denoting the quota for the election.
     */
    public int getQuota() {
        return quota;
    }

    /**
     * Method to obtain all candidates present in an {@code IRElection} instance.
     * @return A {@code Candidate[]} containing all {@code Candidate} objects present in the election.
     */
    public Candidate[] getCandidates() {
        return candidates;
    }

    /*
     * ALL private "helper" methods are below
     */

    /*
     * Performs the algorithm to run an IR election. This method modifies most of the member variables in the class
     */
    private void runElection() {
        if (has_been_run) {
            // Prevent "running" the election more than once
            return;
        }
        audit_data.append(electionInfotoString());
        invalidated_data.append("Invalid ballots with their line number relative to the ballot file:\n");
        this.populateCandidates();
        int ballot_id = 0;
        // Allocate votes of each ballot to the first place candidates.
        for (int i = ballots_start_line_num; i < num_ballots + ballots_start_line_num; i++) {
            String ballot_str = bf.getLine(i);
            String ballot[] = ballot_str.split(",");
            if (isBallotValid(ballot))
                sortBallot(ballot_id, ballot);
            else
                gatherInvalidBallotsInfo(ballot_str, ballot_id, i);
            ballot_id++;
        }
        calculateWinner();
        gatherWinnerInfo(winning_candidate_id);
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        writeToAuditFile(timeStamp);
        writeToInvalidFile(timeStamp);
        has_been_run = true;
    }

    /*
     * Initializes the member variables "candidates" and "surviving_candidate_ids"
     */
    private void populateCandidates() {
        String candidates_str = bf.getLine(candidates_line_num);
        String[] candidates_split = candidates_str.split(", ");

        for (int i = 0; i < num_candidates; i++) {
            String[] candidate_split = candidates_split[i].split(" ");
            String party = candidate_split[1].replaceAll("[(,)]", "");
            candidates[i] = new Candidate(candidate_split[0], party);
            surviving_candidate_ids.add(i);
            votes.add(0);
        }
    }

    /*
     * Responsible for calculating the winning candidate of the election.
     */
    private void calculateWinner() {
        findMajority(surviving_candidate_ids);

        while (!hasWinner()) {
            int loser_id = determineLoser();
            minority_criteria = quota;
            surviving_candidate_ids.remove(loser_id);
            reallocateVotes(loser_id);
            findMajority(surviving_candidate_ids);
        }

        winning_candidate_id = surviving_candidate_ids.iterator().next();
        winning_candidate.add(candidates[winning_candidate_id]);
        winning_party.add(new Party(candidates[winning_candidate_id].getParty(), 1));
    }

    /*
     * Returns the election parameters: election type, number of candidates, candidates and number of ballots
     */
    private String electionInfotoString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Election Type: IR\n");
        sb.append("Number of candidates: ").append(num_candidates).append("\n");
        sb.append("Candidates: ").append(bf.getLine(3)).append("\n");
        sb.append("Number of ballots: ").append(num_ballots).append("\n");
        return sb.toString();
    }

    /*
     * Returns the name and party of a candidate as it is specified in the ballot file
     */
    private String candidateInfotoString(int candidates_id) {
        StringBuilder sb = new StringBuilder();
        sb.append(candidates[candidates_id].getName()).append(" (").append(candidates[candidates_id].getParty()).append(")");
        return sb.toString();
    }

    /*
     * Returns true or false depending on whether or not a ballot is valid
     */
    private boolean isBallotValid(String[] ballot) {
        int num_ranked_needed = (num_candidates + 1) / 2;
        int num_candidates_ranked = 0;
        for (int i = 0; i < ballot.length; i++) {
            if (!ballot[i].equals(""))
                num_candidates_ranked++;
            if (num_candidates_ranked >= num_ranked_needed) {
                return true;
            }
        }
        return false;
    }

    /*
     * Sort an individual ballot so that it contains the candidate ids in ranking sequential order from highest rank
     * (i.e. first place) to lowest place
     */
    private void sortBallot(int ballot_id, String[] ballot) {
        int end = num_candidates - 1;
        for (int i = 0; i < num_candidates; i++) {
            if ((i >= ballot.length) || (ballot[i].equals(""))) {
                ballots[ballot_id][end] = -1; // "-1" indicates no rank
                end--;
            } else {
                int rank = Integer.parseInt(ballot[i]);
                ballots[ballot_id][rank-1] = i;
                if (rank == 1) // only vote for first place candidate
                    voteFor(ballot_id, i, rank);
            }
        }
    }

    /*
     * Adds one vote for a candidate by modifying its "num_votes_ member variable
     */
    private void voteFor(int ballot_id, int candidate_id, int rank) {
        candidates[candidate_id].acquireBallot(ballot_id);
        int candidate_votes = candidates[candidate_id].getNumVotes();
        votes.set(candidate_id, candidate_votes);
        gatherVotingInfo(ballot_id, candidate_id, rank); // record voting process information on audit file
    }

    /*
     * Check if a surviving candidate has reached the quota (i.e. majority number of votes), thereby winning the election
     */
    private void findMajority(Set<Integer> surviving_candidate_ids) {
        int max_votes = Collections.max(votes);
        if (max_votes >= quota) { // majority exists
            winning_candidate_id = votes.indexOf(max_votes);
            surviving_candidate_ids.clear(); // only one surviving candidate left
            surviving_candidate_ids.add(winning_candidate_id);
        }
    }

    /*
     * Responsible for determining the next loser in the election by finding which candidate(s) has the smallest number of votes
     */
    private int determineLoser() {
        List<Integer> loser_candidate_ids =  new ArrayList<Integer>();
        for (int id: surviving_candidate_ids) {
            if (candidates[id].getNumVotes() < minority_criteria) {
                minority_criteria = candidates[id].getNumVotes();
            }
        }
        // check which candidates have the minimum number of votes
        for (int i: surviving_candidate_ids) {
            if (candidates[i].getNumVotes() == minority_criteria) {
                loser_candidate_ids.add(i);
            }
        }
        // only one candidate has the least number of votes
        if (loser_candidate_ids.size() == 1) {
            int loser_id = loser_candidate_ids.iterator().next();
            gatherLoserInfo(loser_candidate_ids, loser_id); // record process of determining a loser for audit file
            return loser_id;
        }
        // if multiple candidates have the same minimum number of votes, randomly pick one to eliminate
        return randomLoser(loser_candidate_ids);
    }

    /*
     * Responsible for randomly determining the losing candidate from a set of candidates with the same minimum number of votes
     */
    private int randomLoser(List<Integer> losers) {
        Random r = new Random();
        int low = 0;
        int high = losers.size();
        int loser_id = r.nextInt(high-low) + low;
        gatherLoserInfo(losers, loser_id); // record process of randomly picking a loser for audit file
        return (losers.get(loser_id));
    }

    /*
     * Responsible for redistributing the votes of the loser candidate to the appropriate candidate
     */
    private void reallocateVotes(int loser_id) {
        loser_ballot_ids = candidates[loser_id].getAcquiredBallots();
        int votes_reallocated = 0;
        for (int i = 0; i < loser_ballot_ids.size(); i++) {
            int loser_ballot_id = loser_ballot_ids.get(i);
            for (int j = 1; j < num_candidates; j++) {
                int new_id = ballots[loser_ballot_id][j];
                // check for the next highest ranked candidate that hasn't been eliminated
                if (surviving_candidate_ids.contains(new_id)) {
                    gatherReallocationVotingInfo(loser_id, new_id, votes_reallocated); // record reallocation process for audit file
                    voteFor(loser_ballot_id, new_id, j+1);
                    votes_reallocated++;
                    j = num_candidates; // next highest ranked candidate has been found so halt search
                }
            }
        }
    }

    /*
     * Check if the IR election has a winner
     */
    private boolean hasWinner() {
        if (surviving_candidate_ids.size() == 1) {
            return true;
        }
        return false;
    }

    /*
     * Responsible for modifying the member variables audit_data and invalid_ballots to record information regarding
     * invalid ballots
     */
    private void gatherInvalidBallotsInfo(String ballot, int ballot_id, int line_number) {
        audit_data.append("Ballot ").append(ballot_id+1).append(" (i.e. '").append(ballot);
        audit_data.append("') at line ").append(line_number).append(" in the ballot file has been invalidated.\n");
        invalidated_data.append("Ballot ").append(ballot_id+1).append(" (i.e. '").append(ballot);
        invalidated_data.append("') at line number ").append(line_number).append("\n");
    }

    /*
     * Responsible for modifying the member variable audit_data to record information regarding the process of a
     * candidate gaining a ballot vote in an IR election
     */
    private void gatherVotingInfo(int ballot_id, int candidate_id, int rank) {
        audit_data.append("Candidate ").append(candidateInfotoString(candidate_id));
        audit_data.append(" has gained a vote in ballot ").append(ballot_id+1).append(" as rank ").append(rank).append(".\n");
        audit_data.append("Candidate ").append(candidateInfotoString(candidate_id));
        audit_data.append(" now has a total of ").append(candidates[candidate_id].getNumVotes()).append(" vote(s).\n");
    }

    /*
     * Responsible for modifying the member variable audit_data to record information regarding the process of a
     * candidate being eliminated from the the IR election
     */
    private void gatherLoserInfo(List<Integer> losers, int loser_id) {
        if (losers.size() == 1) {
            audit_data.append("Candidate ").append(candidateInfotoString(loser_id));
            audit_data.append(" has lost the election with ").append(minority_criteria).append(" vote(s).\n");
        } else {
            audit_data.append("There are ").append(losers.size()).append(" candidates with ").append(minority_criteria).append(" votes(s).\n");
            audit_data.append("These candidates are ");
            for (int i = 0; i < losers.size(); i++) {
                if (i == losers.size() - 1)
                    audit_data.append(candidateInfotoString(losers.get(i))).append(".\n");
                else
                    audit_data.append(candidateInfotoString(losers.get(i))).append(", ");
            }
            audit_data.append("By random chance, candidate ").append(candidateInfotoString(loser_id));
            audit_data.append(" has lost the election with ").append(minority_criteria).append(" vote(s).\n");
        }
    }

    /*
     * Responsible for modifying the member variable audit_data to record information regarding the process of a losing
     * candidate's vote being redistributed to another candidate
     */
    private void gatherReallocationVotingInfo(int loser_id, int new_id, int votes_reallocated) {
        if (votes_reallocated == 1) {
            audit_data.append("Reallocating the votes of ").append(loser_ballot_ids.size()).append(" ballot(s) from ");
            audit_data.append("losing candidate ").append(candidateInfotoString(loser_id)).append(".\n");
        }
        audit_data.append("A vote has been reallocated from candidate ").append(candidateInfotoString(loser_id));
        audit_data.append(" to candidate ").append(candidateInfotoString(new_id)).append(".\n");
    }

    /*
     * Responsible for modifying the member variable audit_data to record information regarding the winning candidate
     */
    private void gatherWinnerInfo(int winner_id) {
        audit_data.append("Candidate ").append(candidateInfotoString(winner_id));
        if (candidates[winner_id].getNumVotes() >=  minority_criteria) {
            audit_data.append(" has won the election with a majority of ");
        } else {
            audit_data.append(" has won the election with ");
        }
        audit_data.append(minority_criteria).append(" vote(s).\n");
    }

    /*
     * Write all the information stored in member variable audit_data to an audit file.
     */
    private void writeToAuditFile(String timeStamp) {
        audit_filename.append("audit_file_").append(bf.getFilename()).append("_").append(timeStamp).append(".txt");
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(String.valueOf(audit_filename));
            bw = new BufferedWriter(fw);
            bw.write(audit_data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
     * Write all the information stored in member variable invalid_ballots to a file named invalidated_dateofelection.xxx
     */
    private void writeToInvalidFile(String timeStamp) {
        invalidated_filename.append("invalidated_").append(timeStamp).append(".txt");
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(String.valueOf(invalidated_filename));
            bw = new BufferedWriter(fw);
            bw.write(invalidated_data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
