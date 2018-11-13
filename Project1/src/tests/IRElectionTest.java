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
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("Election Type: IR\n");
        expected_string.append("Number of candidates: 4\n");
        expected_string.append("Candidates: Rosen (D), Kleinberg (R), Chou (I), Royce (L)\n");
        expected_string.append("Number of ballots: 6\n");
        expected_string.append("Winning candidate: Rosen (D) with 4 votes\n");
        assertTrue(e.toString().contains(expected_string));
    }

    @Test
    public void testAuditFileNameSimpleIR(){
        e.toString();
        assertTrue(e.getAuditFileName().contains("audit_file_"));
        assertTrue(e.getAuditFileName().contains(".txt"));
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
    public void testMajorityCriteria() {
        assertEquals(4, e.getMajorityCriteria());
    }
}
