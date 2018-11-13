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
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IRElection implements Election {
    private BallotFile bf;
    private int candidates_line_num;
    private int ballots_start_line_num;
    private int num_candidates;
    private int num_ballots;
    private int majority_criteria;
    private int minority_criteria;
    private int winning_candidate_id;
    private boolean has_been_run;
    private StringBuffer data;
    private StringBuffer audit_filename;

    private int[][] ballots;
    private Candidate[] candidates;
    private Set<Integer> surviving_candidate_ids;
    private Set<Candidate> surviving_candidates;
    private Set<Party> surviving_parties;
    private List<Integer> loser_ballot_ids;
    private List<Integer> votes;


    public IRElection(BallotFile bf) {
        this.bf = bf;
        int num_candidates_line_num = 2;
        this.candidates_line_num = 3;
        int num_ballots_line_num = 4;
        this.ballots_start_line_num = 5;
        this.num_candidates = Integer.parseInt(bf.getLine(num_candidates_line_num));
        this.num_ballots = Integer.parseInt(bf.getLine(num_ballots_line_num));
        this.majority_criteria = (num_ballots/2) + 1;
        this.minority_criteria = majority_criteria;
        this.data = new StringBuffer();
        audit_filename = new StringBuffer();
        this.ballots = new int[num_ballots][num_candidates];
        this.candidates = new Candidate[num_candidates];
        this.winning_candidate_id = -1;
        this.has_been_run = false;
        this.surviving_candidate_ids = new HashSet<Integer>();
        this.surviving_candidates = new HashSet<Candidate>();
        this.surviving_parties = new HashSet<Party>();
        this.loser_ballot_ids = new ArrayList<Integer>();
        this.votes = new ArrayList<Integer>();
    }

    private void runElection() {
        if (!has_been_run) {
            data.append(electionInfotoString());
            this.populateCandidates();
            int ballot_id = 0;
            for (int i = ballots_start_line_num; i < num_ballots + ballots_start_line_num; i++) {
                String ballot[] = bf.getLine(i).split(",");
                sortBallot(ballot_id, ballot);
                ballot_id++;
            }

            findMajority(surviving_candidate_ids);

            while (!hasWinner()) {
                int loser_id = determineLoser();
                minority_criteria = majority_criteria;
                surviving_candidate_ids.remove(loser_id);
                reallocateVotes(loser_id);
                findMajority(surviving_candidate_ids);
            }

            winning_candidate_id = surviving_candidate_ids.iterator().next();
            surviving_candidates.add(candidates[winning_candidate_id]);
            Party winning_party = new Party(candidates[winning_candidate_id].getParty(), 1);
            surviving_parties.add(winning_party);
            gatherWinnerInfo(winning_candidate_id);
            writeToAuditFile();
            has_been_run = true;
        }
    }

    @Override
    public Set<Candidate> getCandidateWinners() {
        if (!has_been_run) {
            runElection();
        }
        return surviving_candidates;
    }

    @Override
    public Set<Party> getPartyWinners() {
        if (!has_been_run) {
            runElection();
        }
        return surviving_parties;
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
        sb.append(surviving_candidates.iterator().next().getName());
        sb.append(" (").append(surviving_parties.iterator().next().getName()).append(")");
        int winning_votes = surviving_candidates.iterator().next().getNumVotes();
        sb.append(" with ").append(winning_votes);
        if (winning_votes == 1)
            sb.append(" vote\n");
        else
            sb.append(" votes\n");
        sb.append("The audit file '").append(getAuditFileName()).append("'").append(" has been generated\n");
        return sb.toString();
    }

    public String getAuditFileName() {
        return audit_filename.toString();
    }


    private String electionInfotoString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Election Type: IR\n");
        sb.append("Number of candidates: ").append(num_candidates).append("\n");
        sb.append("Candidates: ").append(bf.getLine(3)).append("\n");
        sb.append("Number of ballots: ").append(num_ballots).append("\n");
        return sb.toString();
    }

    private String candidateInfotoString(int candidates_id) {
        StringBuilder sb = new StringBuilder();
        sb.append(candidates[candidates_id].getName()).append(" (").append(candidates[candidates_id].getParty()).append(")");
        return sb.toString();
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
            votes.add(0);
        }
    }

    private void sortBallot(int ballot_id, String[] ballot) {
        int end = num_candidates - 1;
        for (int i = 0; i < num_candidates; i++) {
            if ((i >= ballot.length) || (ballot[i].equals(""))) {
                ballots[ballot_id][end] = -1; // no rank
                end--;
            } else {
                int rank = Integer.parseInt(ballot[i]);
                ballots[ballot_id][rank-1] = i;
                if (rank == 1) // only vote for first place candidate
                    voteFor(ballot_id, i, rank);
            }
        }
    }

    private void voteFor(int ballot_id, int candidate_id, int rank) {
        candidates[candidate_id].acquireBallot(ballot_id);
        int candidate_votes = candidates[candidate_id].getNumVotes();
        votes.set(candidate_id, candidate_votes);
        gatherVotingInfo(ballot_id, candidate_id, rank);
    }

    private void findMajority(Set<Integer> surviving_candidate_ids) {
        int max_votes = Collections.max(votes);
        if (max_votes >= majority_criteria) { // majority exists
            winning_candidate_id = votes.indexOf(max_votes);
            surviving_candidate_ids.clear();
            surviving_candidate_ids.add(winning_candidate_id);
        }
    }

    private int determineLoser() {
        List<Integer> loser_candidate_ids =  new ArrayList<Integer>();
        for (int id: surviving_candidate_ids) {
            if (candidates[id].getNumVotes() < minority_criteria) {
                minority_criteria = candidates[id].getNumVotes();
            }
        }
        for (int i: surviving_candidate_ids) {
            if (candidates[i].getNumVotes() == minority_criteria) {
                loser_candidate_ids.add(i);
            }
        }
        if (loser_candidate_ids.size() == 1) {
            int loser_id = loser_candidate_ids.iterator().next();
            gatherLoserInfo(loser_candidate_ids, loser_id);
            return loser_id;
        }
        return randomLoser(loser_candidate_ids);
    }

    private void reallocateVotes(int loser_id) {
        loser_ballot_ids = candidates[loser_id].getAcquiredBallots();
        int votes_reallocated = 0;
        for (int i = 0; i < loser_ballot_ids.size(); i++) {
            int loser_ballot_id = loser_ballot_ids.get(i);
            for (int j = 1; j < num_candidates; j++) {
                int new_id = ballots[loser_ballot_id][j];
                if (surviving_candidate_ids.contains(new_id)) {
                    gatherReallocationVotingInfo(loser_id, new_id, votes_reallocated);
                    voteFor(loser_ballot_id, new_id, j+1);
                    votes_reallocated++;
                    j = num_candidates;
                }
            }
        }
    }

    private boolean hasWinner() {
        if (surviving_candidate_ids.size() == 1) {
            return true;
        }
        return false;
    }

    private int randomLoser(List<Integer> losers) {
        Random r = new Random();
        int low = 0;
        int high = losers.size();
        int loser_id = r.nextInt(high-low) + low;
        gatherLoserInfo(losers, loser_id);
        return (losers.get(loser_id));
    }

    private void gatherVotingInfo(int ballot_id, int candidate_id, int rank) {
        data.append("Candidate ").append(candidateInfotoString(candidate_id));
        data.append(" has gained a vote in ballot ").append(ballot_id+1).append(" as rank ").append(rank).append(".\n");
        data.append("Candidate ").append(candidateInfotoString(candidate_id));
        data.append(" now has a total of ").append(candidates[candidate_id].getNumVotes()).append(" vote(s).\n");
    }

    private void gatherLoserInfo(List<Integer> losers, int loser_id) {
        if (losers.size() == 1) {
            data.append("Candidate ").append(candidateInfotoString(loser_id));
            data.append(" has lost the election with ").append(minority_criteria).append(" vote(s).\n");
        } else {
            data.append("There are ").append(losers.size()).append(" candidates with ").append(minority_criteria).append(" votes(s).\n");
            data.append("These candidates are ");
            for (int i = 0; i < losers.size(); i++) {
                if (i == losers.size() - 1)
                    data.append(candidateInfotoString(losers.get(i))).append(".\n");
                else
                    data.append(candidateInfotoString(losers.get(i))).append(", ");
            }
            data.append("By random chance, candidate ").append(candidateInfotoString(loser_id));
            data.append(" has lost the election with ").append(minority_criteria).append(" vote(s).\n");
        }
    }

    private void gatherReallocationVotingInfo(int loser_id, int new_id, int votes_reallocated) {
        if (votes_reallocated == 1) {
            data.append("Reallocating the votes of ").append(loser_ballot_ids.size()).append(" ballot(s) from ");
            data.append("losing candidate ").append(candidateInfotoString(loser_id)).append(".\n");
        }
        data.append("A vote has been reallocated from candidate ").append(candidateInfotoString(loser_id));
        data.append(" to candidate ").append(candidateInfotoString(new_id)).append(".\n");
    }

    private void gatherWinnerInfo(int winner_id) {
        data.append("Candidate ").append(candidateInfotoString(winner_id));
        if (candidates[winner_id].getNumVotes() >=  minority_criteria) {
            data.append(" has won the election with a majority of ");
        } else {
            data.append(" has won the election with ");
        }
        data.append(minority_criteria).append(" vote(s).\n");
    }

    private void writeToAuditFile() {
        //StringBuffer filename = new StringBuffer();
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        audit_filename.append("audit_file_").append(bf.getFilename()).append("_").append(timeStamp).append(".txt");
        //System.out.println(filename);
        
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(String.valueOf(audit_filename));
            bw = new BufferedWriter(fw);
            bw.write(data.toString());
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
