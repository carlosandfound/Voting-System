package elections;

import fileio.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

    public OPLElection(BallotFile bf) {
        this.bf = bf;
        this.has_been_run = false;
        this.num_candidates = Integer.parseInt(bf.getLine(num_candidate_line_num));
        this.num_seats = Integer.parseInt(bf.getLine(num_seats_line_num));
        this.num_ballots = Integer.parseInt(bf.getLine(num_ballots_line_num));
        this.quota = this.num_ballots / this.num_seats; // TODO: divide by zero error possible here
        populateCandidatesAndParties();
    }

    public int getNumCandidates() {
        return num_candidates;
    }

    public int getNumSeats() {
        return num_seats;
    }

    public int getNumBallots() {
        return num_ballots;
    }

    public int getQuota() {
        return quota;
    }

    public Candidate[] getCandidates() {
        return candidates;
    }

    public Party[] getParties() {
        return parties;
    }

    @Override
    public void runElection() {
        if (has_been_run) {
            return;
        }

        has_been_run = true;
    }

    @Override
    public Set<Candidate> getCandidateWinners() {
        if (!has_been_run) {
            runElection();
        }
        return null;
    }

    @Override
    public Set<Party> getPartyWinners() {
        if (!has_been_run) {
            runElection();
        }
        return null;
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

    private void voteFor(int candidate_id, int ballot_id) {

    }

    private void sortPartiesOnRemainingVotes() {

    }

    private void sortCandidatesOnVotes() {

    }
}
