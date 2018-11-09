package elections;

import fileio.BallotFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import static org.junit.jupiter.api.Assertions.*;

class OPLElectionTest {
    private static BallotFile bf;
    private static OPLElection e;
    @BeforeAll
    static void setup() {
        String simple_opl_file_path = FileSystems
                .getDefault()
                .getPath("testing", "simple_opl_ballot_file.csv")
                .toString();
        File ballot_file = new File(simple_opl_file_path);
        try {
            bf = new BallotFile(ballot_file);
            e = new OPLElection(bf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void OPLElectionConstructorSimpleOPL() {
        assertDoesNotThrow(() -> {
            e = new OPLElection(bf);
        });
    }

    @Test
    void getNumCandidatesSimpleOPL() {
        assertEquals(6, e.getNumCandidates());
    }

    @Test
    void getNumSeatsSimpleOPL() {
        assertEquals(3, e.getNumSeats());
    }

    @Test
    void getNumBallotsSimpleOPL() {
        assertEquals(9, e.getNumBallots());
    }

    @Test
    void getQuotaSimpleOPL() {
        assertEquals(3, e.getQuota());
    }

    @Test
    void getCandidatesSimpleOPL() {
        String[] expected_names = {"Pike", "Foster", "Deutsch", "Borg", "Jones", "Smith"};
        String[] expected_parties = {"D", "D", "R", "R", "R", "I"};
        Candidate[] candidates = e.getCandidates();
        for (int i = 0; i < candidates.length; i++) {
            Candidate c = candidates[i];
            if (!c.getName().equals(expected_names[i])) {
                fail("Found candidate " + i + " name " + c.getName() + ". Expected candidate " + i + " name " + expected_names[i]);
            } else if (!c.getParty().equals(expected_parties[i])) {
                fail("Found candidate " + i + " party " + c.getParty() + ". Expected candidate " + i + " party " + expected_parties[i]);
            }
        }
    }

    @Test
    void getPartiesSimpleOPL() {
        String[] expected_parties = {"D", "R", "I"};
        int[] expected_num_candidates = {2, 3, 1};
        Party[] parties = e.getParties();
        for (int i = 0; i < parties.length; i++) {
            Party p = parties[i];
            if (!p.getName().equals(expected_parties[i])) {
                fail("Found party " + i + " name " + p.getName() + ". Expected party " + i + " name " + expected_parties[i]);
            } else if (p.getNumCandidates() != expected_num_candidates[i]) {
                fail("Found party " + i + " number candidates " + p.getNumCandidates() + ". Expected party " + i + " number candidates " + expected_num_candidates[i]);
            }
        }
    }

    @Disabled
    @Test
    void runElection() {
    }

    @Disabled
    @Test
    void getCandidateWinners() {
    }

    @Disabled
    @Test
    void getPartyWinners() {
    }
}