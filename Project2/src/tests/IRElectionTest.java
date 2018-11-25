/*
 * File: IRElectionTest.java
 *
 * Description: this file contains the unit tests for all public methods within the IRElection class.
 *
 * Authors: Xiaochen Zhang
 */
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class IRElectionTest {
    private static BallotFile bf;
    private static IRElection e;

    @BeforeAll
    static void setup() {
        String simple_ir_file_path = FileSystems
                .getDefault()
                .getPath("testing", "simple_ir_ballot_file.csv")
                .toString();
        File ballot_file = new File(simple_ir_file_path);
        try {
            bf = new BallotFile(ballot_file);
            e = new IRElection(bf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void IRElectionConstructorSimpleIR() {
        assertDoesNotThrow(() -> {
            e = new IRElection(bf);
        });
    }

    @Test
    public void testToStringSimpleIR() {
        e.toString();
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("Election Type: IR\n");
        expected_string.append("Number of candidates: 4\n");
        expected_string.append("Candidates: Rosen (D), Kleinberg (R), Chou (I), Royce (L)\n");
        expected_string.append("Number of ballots: 6\n");
        expected_string.append("Winning candidate: Rosen (D) with 4 votes\n");
        StringBuffer audit_filename = new StringBuffer();
        StringBuffer invalidated_filename = new StringBuffer();
        audit_filename.append("audit_file_").append(bf.getFilename()).append("_").append(e.getTimeStamp()).append(".txt");
        invalidated_filename.append("invalidated_").append(e.getTimeStamp()).append(".txt");
        expected_string.append("The audit file '").append(audit_filename).append("'").append(" has been generated\n");
        expected_string.append("The invalidated ballots file '").append(invalidated_filename).append("'").append(" has been generated\n");
        assertEquals(expected_string.toString(), e.toString());
    }

    @Test
    public void testNumCandidatesSimpleIR() {
        assertEquals(4, e.getNumCandidates());
    }

    @Test
    public void testNumBallotsSimpleIR() {
        assertEquals(6, e.getNumBallots());
    }

    @Test
    public void testWinningCandidateSimpleIR() {
        String expected_candidate = "Rosen";
        Set<Candidate> candidate_winner = e.getCandidateWinners();
        assertEquals(expected_candidate, candidate_winner.iterator().next().getName());
    }

    @Test
    public void testWinningPartySimpleIR() {
        String expected_party = "D";
        Set<Party> party_winner = e.getPartyWinners();
        assertEquals(expected_party, party_winner.iterator().next().getName());
    }

    @Test
    void testCandidatesSimpleIR() {
        e.toString();
        String[] expected_names = {"Rosen", "Kleinberg", "Chou", "Royce"};
        Candidate[] candidates = e.getCandidates();
        for (int i = 0; i < candidates.length; i++) {
            Candidate c = candidates[i];
            if (!c.getName().equals(expected_names[i])) {
                fail("Found candidate " + i + " name " + c.getName() + ". Expected candidate " + i + " name " + expected_names[i]);
            }
        }
    }

    @Test
    void testQuota() {
        assertEquals(4,e.getQuota());
    }

    @Test
    void testNumInvalidatedBallotsSimpleIR() {
        e.toString();
        assertEquals(1, e.getNumInvalidatedBallots());}

    @Test
    void testTimeStamp() {
        e.toString();
        assertEquals(14, e.getTimeStamp().length());
    }
}
