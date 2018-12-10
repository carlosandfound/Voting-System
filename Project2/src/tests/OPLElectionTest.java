/*
 * File: OPLElectionTest.java
 *
 * Description: this file contains the unit tests for all public methods within the OPLElection class.
 *
 * Authors: Justin Koo
 */

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OPLElectionTest {
    private static BallotFile bf;
    private static OPLElection e;

    @BeforeEach
    void setup() {
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
        Assertions.assertEquals(6, e.getNumCandidates());
    }

    @Test
    void getNumSeatsSimpleOPL() {
        Assertions.assertEquals(3, e.getNumSeats());
    }

    @Test
    void getNumBallotsSimpleOPL() {
        Assertions.assertEquals(9, e.getNumBallots());
    }

    @Test
    void getQuotaSimpleOPL() {
        Assertions.assertEquals(3, e.getQuota());
    }

    @Test
    void getCandidatesNamesSimpleOPL() {
        String[] expected_names = {"Pike", "Foster", "Deutsch", "Borg", "Jones", "Smith"};
        Candidate[] candidates = e.getCandidates();
        for (int i = 0; i < candidates.length; i++) {
            Candidate c = candidates[i];
            if (!c.getName().equals(expected_names[i])) {
                fail("Found candidate " + i + " name " + c.getName() + ". Expected candidate " + i + " name " + expected_names[i]);
            }
        }
    }

    @Test
    void getCandidatesPartiesSimpleOPL() {
        String[] expected_parties = {"D", "D", "R", "R", "R", "I"};
        Candidate[] candidates = e.getCandidates();
        for (int i = 0; i < candidates.length; i++) {
            Candidate c = candidates[i];
            if (!c.getParty().equals(expected_parties[i])) {
                fail("Found candidate " + i + " party " + c.getParty() + ". Expected candidate " + i + " party " + expected_parties[i]);
            }
        }
    }

    @Test
    void getPartiesNamesSimpleOPL() {
        String[] expected_parties = {"D", "R", "I"};
        Party[] parties = e.getParties();
        for (int i = 0; i < parties.length; i++) {
            Party p = parties[i];
            if (!p.getName().equals(expected_parties[i])) {
                fail("Found party " + i + " name " + p.getName()
                        + ". Expected party " + i + " name " + expected_parties[i]);
            }
        }
    }

    @Test
    void getPartiesNumCandidatesSimpleOPL() {
        int[] expected_num_candidates = {2, 3, 1};
        Party[] parties = e.getParties();
        for (int i = 0; i < parties.length; i++) {
            Party p = parties[i];
            if (p.getNumCandidates() != expected_num_candidates[i]) {
                fail("Found party " + i + " number candidates " + p.getNumCandidates()
                        + ". Expected party " + i + " number candidates " + expected_num_candidates[i]);
            }
        }
    }

    @Test
    void getCandidateWinnersNotEmptySimpleOPL() {
        assertTrue(!e.getCandidateWinners().isEmpty());
    }

    @Test
    void getPartyWinnersNotEmptySimpleOPL() {
        assertTrue(!e.getPartyWinners().isEmpty());
    }

    @Test
    void toStringNotEmptySimpleOPL() {
        assertTrue(!e.toString().isEmpty());
    }
}