/*
 * File: IRElection.java
 *
 * Description: this file contains the class implementation for the IRElection class used in the "Voting System" product. This class is the one responsible for running an instant-runoff election.
 *
 * Author: Carlos Alvarenga
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

public class IRElection implements Election {
    private BallotFile bf;
    private int candidates_line_num;
    private int ballots_start_line_num;
    private int num_candidates;
    private int num_ballots;
    private int majority_criteria;
    private int minority_criteria;
    private int winning_candidate_id;

    private int[][] ballots;
    private Candidate[] candidates;
    private Set<Integer> surviving_candidate_ids;
    private Set<Candidate> surviving_candidates;
    private Set<Party> surviving_parties;
    private List<Integer> loser_ballot_ids;


    public IRElection(BallotFile bf) {
        this.bf = bf;
        this.minority_criteria = 10000;
        int num_candidates_line_num = 2;
        this.candidates_line_num = 3;
        int num_ballots_line_num = 4;
        this.ballots_start_line_num = 5;
        this.num_candidates = Integer.parseInt(bf.getLine(num_candidates_line_num));
        this.num_ballots = Integer.parseInt(bf.getLine(num_ballots_line_num));
        this.majority_criteria = (num_ballots/2) + 1;
        this.ballots = new int[num_ballots][num_candidates];
        this.candidates = new Candidate[num_candidates];
        this.winning_candidate_id = -1;
        this.surviving_candidate_ids = new HashSet<Integer>();
        this.surviving_candidates = new HashSet<Candidate>();
        this.surviving_parties = new HashSet<Party>();
        this.loser_ballot_ids = new ArrayList<Integer>();
    }

    @Override
    public void runElection() {
        this.populateCandidates();
        int row = 0;
        for (int i = ballots_start_line_num; i < num_ballots+ballots_start_line_num; i++) {
            String ballot[] = bf.getLine(i).split(",");
            sortBallot(ballot, row);
            row++;
        }
        for (int i = 0; i < num_ballots; i++) {
            for (int j = 0; j < num_candidates; j++) {
                System.out.print(ballots[i][j]+" ");
            }
            System.out.println(" ");
        }

        System.out.println();
        for (int i = 0; i < num_candidates; i++) {
            System.out.println(candidates[i].getName() + ": " + candidates[i].getNumVotes() + ", " + candidates[i].getNumVotes() +candidates[i].getAcquiredBallots());
        }
        System.out.println();

        while (!hasWinner()) {
            System.out.println("NO WINNER............................................................................");
            int loser_id = determineLoser();
            minority_criteria = 10000;
            System.out.println(loser_id);
            surviving_candidate_ids.remove(loser_id);
            reallocateVotes(loser_id);
            for (int i: surviving_candidate_ids) {
                System.out.println("NAME: " + candidates[i].getName());
                System.out.println("VOTES: " + candidates[i].getNumVotes());
            }
        }

        winning_candidate_id = surviving_candidate_ids.iterator().next();
        surviving_candidates.add(candidates[winning_candidate_id]);
        Party winning_party = new Party(candidates[winning_candidate_id].getParty(), 1);
        surviving_parties.add(winning_party);
        for (int i: surviving_candidate_ids) {
            System.out.println("NAME OF WINNER: " + candidates[i].getName());
        }
    }

    @Override
    public Set<Candidate> getCandidateWinners() {
        return surviving_candidates;
    }

    @Override
    public Set<Party> getPartyWinners() {
        return surviving_parties;
    }

    public int getNumCandidates() {
        return num_candidates;
    }

    public int getNumBallots() {
        return num_ballots;
    }

    public int getMajorityCriteria() {
        return majority_criteria;
    }

    private void populateCandidates() {
        String candidates_str = bf.getLine(candidates_line_num);
        String[] candidates_split = candidates_str.split(", ");

        for (int i = 0; i < num_candidates; i++) {
            String[] candidate_split = candidates_split[i].split(" ");
            String party = candidate_split[1].replaceAll("[(,)]", "");
            candidates[i] = new Candidate(candidate_split[0], party);
            surviving_candidate_ids.add(i);
        }
    }

    private void sortBallot(String[] ballot, int row) {
        int end = num_candidates - 1;
        for (int i = 0; i < num_candidates; i++) {
            if ((i >= ballot.length) || (ballot[i].equals(""))) {
                ballots[row][end] = -1; // no rank
                end--;
            } else {
                int rank = Integer.parseInt(ballot[i]);
                ballots[row][rank-1] = i;
                if (rank == 1) {
                    voteFor(i, rank, row);
                }
            }
        }
    }

    private void voteFor(int candidates_id, int rank, int ballot_id) {
        candidates[candidates_id].acquireBallot(ballot_id);
        if (candidates[candidates_id].getNumVotes() >= majority_criteria) {
            winning_candidate_id = candidates_id;
            surviving_candidate_ids.clear();
            surviving_candidate_ids.add(candidates_id);
        }
    }

    private int determineLoser() {
        Set<Integer> loser_candidate_ids =  new HashSet<Integer>();
        for (int id: surviving_candidate_ids) {
            if (candidates[id].getNumVotes() < minority_criteria) {
                minority_criteria = candidates[id].getNumVotes();
            }
        }
        System.out.println("MIN: " + minority_criteria);
        for (int i: surviving_candidate_ids) {
            if (candidates[i].getNumVotes() == minority_criteria) {
                loser_candidate_ids.add(i);
            }
        }
        if (loser_candidate_ids.size() == 1) {
            return loser_candidate_ids.iterator().next();
        }
        return randomLoser(loser_candidate_ids);
    }

    private void reallocateVotes(int candidates_id) {
        loser_ballot_ids = candidates[candidates_id].getAcquiredBallots();
        if (loser_ballot_ids.size() == 0) {

        }
        for (int i = 0; i < loser_ballot_ids.size(); i++) {
            int loser_ballot_id = loser_ballot_ids.get(i);
            for (int j = 1; j < num_candidates; j++) {
                int winning_cand_id = ballots[loser_ballot_id][j];
                if (surviving_candidate_ids.contains(winning_cand_id)) {
                    voteFor(winning_cand_id, j+1, loser_ballot_id);
                }
            }
        }

    }

    private boolean hasWinner() {
        if ((surviving_candidate_ids.size() == 1) || (winning_candidate_id != -1)) {
            return true;
        }
        return false;
    }

    private int randomLoser(Set<Integer> losers) {
        Random r = new Random();
        int low = 0;
        int high = losers.size();
        return (r.nextInt(high-low) + low);
    }
}
