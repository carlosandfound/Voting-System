/*
 * File: IRElectionTest.java
 *
 * Description: this file contains the unit tests for all public methods within the IRElection class.
 *
 * Authors: Carlos Alvarenga, Xiaochen Zhang, Michael McLaughlin
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class IRElectionTest {
    private static BallotFile bf1;
    private static BallotFile bf2;
    private static BallotFile bf3;
    private static BallotFile bf4;
    private static BallotFile bf5;
    private static BallotFile bf6;
    private static BallotFile bf7;
    private static BallotFile bf8;
    private static IRElection e1;
    private static IRElection e2;
    private static IRElection e3;
    private static IRElection e4;
    private static IRElection e5;
    private static IRElection e6;
    private static IRElection e7;
    private static IRElection e8;
    private static Table t1;
    private static Table t2;
    private static Table t3;
    private static Table t4;
    private static Table t5;
    private static Table t6;
    private static Table t7;
    private static Table t8;

    @BeforeAll
    static void setup() {
        String ir_ballot_file_1_path = FileSystems
                .getDefault()
                .getPath("testing", "ir_ballot_file_1.csv")
                .toString();
        File ballot_file_1 = new File(ir_ballot_file_1_path);

        String ir_ballot_file_2_path = FileSystems
                .getDefault()
                .getPath("testing", "ir_ballot_file_2.csv")
                .toString();
        File ballot_file_2 = new File(ir_ballot_file_2_path);

        String ir_ballot_file_3_path = FileSystems
                .getDefault()
                .getPath("testing", "ir_ballot_file_3.csv")
                .toString();
        File ballot_file_3 = new File(ir_ballot_file_3_path);

        String ir_ballot_file_4_path = FileSystems
                .getDefault()
                .getPath("testing", "ir_ballot_file_4.csv")
                .toString();
        File ballot_file_4 = new File(ir_ballot_file_4_path);

        String ir_ballot_file_5_path = FileSystems
                .getDefault()
                .getPath("testing", "ir_ballot_file_5.csv")
                .toString();
        File ballot_file_5 = new File(ir_ballot_file_5_path);

        String ir_ballot_file_6_path = FileSystems
                .getDefault()
                .getPath("testing", "ir_ballot_file_6.csv")
                .toString();
        File ballot_file_6 = new File(ir_ballot_file_6_path);

        String ir_ballot_file_7_path = FileSystems
                .getDefault()
                .getPath("testing", "ir_ballot_file_7.csv")
                .toString();
        File ballot_file_7 = new File(ir_ballot_file_7_path);
        
        String ir_ballot_file_8_path = FileSystems
                .getDefault()
                .getPath("testing", "ir_ballot_file_8.csv")
                .toString();
        File ballot_file_8 = new File(ir_ballot_file_8_path);

        try {
            bf1 = new BallotFile(ballot_file_1);
            e1 = new IRElection(bf1);
            t1 = new Table();

            bf2 = new BallotFile(ballot_file_2);
            e2 = new IRElection(bf2);
            t2 = new Table();

            bf3 = new BallotFile(ballot_file_3);
            e3 = new IRElection(bf3);
            t3 = new Table();

            bf4 = new BallotFile(ballot_file_4);
            e4 = new IRElection(bf4);
            t4 = new Table();

            bf5 = new BallotFile(ballot_file_5);
            e5 = new IRElection(bf5);
            t5 = new Table();

            bf6 = new BallotFile(ballot_file_6);
            e6 = new IRElection(bf6);
            t6 = new Table();

            bf7 = new BallotFile(ballot_file_7);
            e7 = new IRElection(bf7);
            t7 = new Table();

            bf8 = new BallotFile(ballot_file_8);
            e8 = new IRElection(bf8);
            t8 = new Table();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void IRElectionConstructorSimpleIR() {
        assertDoesNotThrow(() -> {
            e1 = new IRElection(bf1);
            e2 = new IRElection(bf2);
            e3 = new IRElection(bf3);
            e4 = new IRElection(bf4);
            e5 = new IRElection(bf5);
            e6 = new IRElection(bf6);
            e7 = new IRElection(bf7);
            e8 = new IRElection(bf8);
        });
    }

    @Test
    public void testToStringSimpleIR() {
        e1.toString();
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("Election Type: IR\n");
        expected_string.append("Number of candidates: 4\n");
        expected_string.append("Candidates: Rosen (D), Kleinberg (R), Chou (I), Royce (L)\n");
        expected_string.append("Number of ballots: 6\n");
        expected_string.append("Winning candidate: Rosen (D) with 4 votes\n");
        StringBuffer audit_filename = new StringBuffer();
        StringBuffer invalidated_filename = new StringBuffer();
        StringBuffer short_report_filename = new StringBuffer();
        audit_filename.append("audit_file_").append(bf1.getFilename()).append("_").append(e1.getTimeStamp()).append(".txt");
        invalidated_filename.append("invalidated_").append(e1.getTimeStamp()).append(".txt");
        short_report_filename.append("short_report_").append(e1.getTimeStamp()).append(".txt");
        expected_string.append("The audit file '").append(audit_filename).append("'").append(" has been generated\n");
        expected_string.append("The invalidated ballots file '").append(invalidated_filename).append("'").append(" has been generated\n");
        expected_string.append("The short report '").append(short_report_filename).append("'").append(" has been generated\n");
        expected_string.append("The following table shows the progression of votes through the election rounds: \n");
        expected_string.append(e1.getTable().toString());
        assertEquals(expected_string.toString(), e1.toString());
    }

    @Test
    public void testNumCandidatesSimpleIR() {
        assertEquals(4, e1.getNumCandidates());
    }

    @Test
    public void testNumBallotsSimpleIR() {
        assertEquals(6, e1.getNumBallots());
    }

    @Test
    public void testWinningCandidateSimpleIR() {
        String expected_candidate = "Rosen";
        Set<Candidate> candidate_winner = e1.getCandidateWinners();
        assertEquals(expected_candidate, candidate_winner.iterator().next().getName());
    }

    @Test
    public void testWinningPartySimpleIR() {
        String expected_party = "D";
        Set<Party> party_winner = e1.getPartyWinners();
        assertEquals(expected_party, party_winner.iterator().next().getName());
    }

    @Test
    void testCandidatesSimpleIR() {
        e1.toString();
        String[] expected_names = {"Rosen", "Kleinberg", "Chou", "Royce"};
        Candidate[] candidates = e1.getCandidates();
        for (int i = 0; i < candidates.length; i++) {
            Candidate c = candidates[i];
            if (!c.getName().equals(expected_names[i])) {
                fail("Found candidate " + i + " name " + c.getName() + ". Expected candidate " + i + " name " + expected_names[i]);
            }
        }
    }

    @Test
    void testQuota() {
        assertEquals(4,e1.getQuota());
    }

    @Test
    void testNumInvalidatedBallotsSimpleIRBF1() {
        e1.toString();
        assertEquals(1, e1.getNumInvalidatedBallots());
    }

    @Test
    void testNumInvalidatedBallotsSimpleIRBF3() {
        e3.toString();
        assertEquals(0, e3.getNumInvalidatedBallots());
    }

    @Test
    void testNumInvalidatedBallotsSimpleIRBF6() {
        e6.toString();
        assertEquals(2, e6.getNumInvalidatedBallots());
    }

    @Test
    void testTimeStamp() {
        e1.toString();
        assertEquals(14, e1.getTimeStamp().length());
    }

    @Test
    void testDate() {
        e1.toString();
        assertEquals(10, e1.getDate().length());
    }

    @Test
    public void testGetTableIRBF1() {
        e1.toString();
        StringBuilder expected_table = new StringBuilder();
        expected_table.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_table.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        4|       +1|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Chou              |I        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Royce             |L        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        1|       +1|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Totals            |         |        5|       +5|                                                           |\n");
        expected_table.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        assertEquals(expected_table.toString(), e1.getTable().toString());
    }

    @Test
    public void testGetTableIRBF2() {
        e2.toString();
        StringBuilder expected_table = new StringBuilder();
        expected_table.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_table.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        5|       +2|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Royce             |L        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Totals            |         |        5|       +5|                                                           |\n");
        expected_table.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        assertEquals(expected_table.toString(), e2.getTable().toString());
    }

    @Test
    public void testGetTableIRBF3() {
        e3.toString();
        StringBuilder expected_table1 = new StringBuilder();
        expected_table1.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_table1.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_table1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table1.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_table1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table1.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        0|       -3|\n");
        expected_table1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table1.append("|Kleinberg         |R        |        2|       +2|        2|        0|        3|       +1|        5|       +2|\n");
        expected_table1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table1.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table1.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_table1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table1.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        1|       +1|\n");
        expected_table1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table1.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_table1.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        StringBuilder expected_table2 = new StringBuilder();
        expected_table2.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_table2.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_table2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table2.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_table2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table2.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        6|       +3|\n");
        expected_table2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table2.append("|Kleinberg         |R        |        2|       +2|        2|        0|        3|       +1|        0|       -3|\n");
        expected_table2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table2.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table2.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_table2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table2.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table2.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_table2.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        assertTrue(expected_table1.toString().equals(e3.getTable().toString()) || expected_table2.toString().equals(e3.getTable().toString()));
    }

    @Test
    public void testGetTableIRBF4() {
        e4.toString();
        StringBuilder expected_table = new StringBuilder();
        expected_table.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_table.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        3|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Royce             |L        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        2|       +2|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Totals            |         |        5|       +5|                                                           |\n");
        expected_table.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        assertEquals(expected_table.toString(), e4.getTable().toString());
    }

    @Test
    public void testGetTableIRBF5() {
        e5.toString();
        StringBuilder expected_table = new StringBuilder();
        expected_table.append("+----------------------------+-------------------+\n");
        expected_table.append("|         Candidates         |      Round 1      |\n");
        expected_table.append("+------------------+---------+---------+---------+\n");
        expected_table.append("|    Candidate     |  Party  |  Votes  |    ±    |\n");
        expected_table.append("+------------------+---------+---------+---------+\n");
        expected_table.append("|Rosen             |D        |        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+\n");
        expected_table.append("|Kleinberg         |R        |        6|       +6|\n");
        expected_table.append("+------------------+---------+---------+---------+\n");
        expected_table.append("|Chou              |I        |        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+\n");
        expected_table.append("|Royce             |L        |        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+\n");
        expected_table.append("|Exhausted Pile    |         |        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+\n");
        expected_table.append("|Totals            |         |        6|       +6|\n");
        expected_table.append("+------------------+---------+---------+---------+\n");

        assertEquals(expected_table.toString(), e5.getTable().toString());
    }

    @Test
    public void testGetTableIRBF6() {
        e6.toString();
        StringBuilder expected_table = new StringBuilder();
        expected_table.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_table.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Rosen             |D        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Royce             |L        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Totals            |         |        0|       +0|                                                           |\n");
        expected_table.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        assertEquals(expected_table.toString(), e6.getTable().toString());
    }

    @Test
    public void testGetTableIRBF7() {
        e7.toString();
        StringBuilder expected_table = new StringBuilder();
        expected_table.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_table.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        5|       +2|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Kleinberg         |R        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Exhausted Pile    |         |        0|        0|        0|        0|        1|       +1|        1|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_table.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        assertEquals(expected_table.toString(), e7.getTable().toString());
    }

    @Test
    public void testGetTableIRBF8() {
        e8.toString();
        StringBuilder expected_table = new StringBuilder();
        expected_table.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_table.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        4|       +1|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Kleinberg         |R        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Exhausted Pile    |         |        0|        0|        0|        0|        1|       +1|        2|       +1|\n");
        expected_table.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_table.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_table.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        assertEquals(expected_table.toString(), e8.getTable().toString());
    }


    @Test
    public void testExhaustedPileTotalsIRBF1() {
        e1.toString();
        assertEquals(Arrays.asList(0, 0, 0, 1), e1.getExhaustedPileTotals());
    }

    @Test
    public void testExhaustedPileTotalsIRBF2() {
        e2.toString();
        assertEquals(Arrays.asList(0, 0, 0, 0), e2.getExhaustedPileTotals());
    }

    @Test
    public void testExhaustedPileTotalsIRBF4() {
        e4.toString();
        assertEquals(Arrays.asList(0, 0, 0, 2), e4.getExhaustedPileTotals());
    }

    @Test
    public void testExhaustedPileTotalsIRBF5() {
        e5.toString();
        assertEquals(Arrays.asList(0), e5.getExhaustedPileTotals());
    }

    @Test
    public void testExhaustedPileTotalsIRBF7() {
        e7.toString();
        assertEquals(Arrays.asList(0, 0, 1, 1), e7.getExhaustedPileTotals());
    }

    @Test
    public void testExhaustedPileTotalsIRBF8() {
        e8.toString();
        assertEquals(Arrays.asList(0, 0, 1, 2), e8.getExhaustedPileTotals());
    }

    @Test
    public void testExhaustedPileUpdatesIRBF1() {
        e1.toString();
        assertEquals(Arrays.asList(0, 0, 0, 1), e1.getExhaustedPileUpdates());
    }

    @Test
    public void testExhaustedPileUpdatesIRBF2() {
        e2.toString();
        assertEquals(Arrays.asList(0, 0, 0, 0), e2.getExhaustedPileUpdates());
    }

    @Test
    public void testExhaustedPileUpdatesIRBF4() {
        e4.toString();
        assertEquals(Arrays.asList(0, 0, 0, 2), e4.getExhaustedPileUpdates());
    }

    @Test
    public void testExhaustedPileUpdatesIRBF5() {
        e5.toString();
        assertEquals(Arrays.asList(0), e5.getExhaustedPileUpdates());
    }

    @Test
    public void testExhaustedPileUpdatesIRBF7() {
        e7.toString();
        assertEquals(Arrays.asList(0, 0, 1, 0), e7.getExhaustedPileUpdates());
    }

    @Test
    public void testExhaustedPileUpdatesIRBF8() {
        e8.toString();
        assertEquals(Arrays.asList(0, 0, 1, 1), e8.getExhaustedPileUpdates());
    }

    @Test
    public void testTotalNumVotesIRBF1() {
        e1.toString();
        assertEquals(5, e1.getTotalNumVotes());
    }

    @Test
    public void testTotalNumVotesIRBF3() {
        e3.toString();
        assertEquals(6, e3.getTotalNumVotes());
    }

    @Test
    public void testTotalNumVotesIRBF6() {
        e6.toString();
        assertEquals(0, e6.getTotalNumVotes());
    }
}
