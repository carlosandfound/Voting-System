/*
 * File: TableTest.java
 *
 * Description: this file contains the unit tests for all public methods within the Table class.
 *
 * Authors: Carlos Alvarenga, Michael McLaughlin, Xiaochen Zhang
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TableTest {

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
    private static Table t9;

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

            t9 = new Table();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void tableConstructorTest() {
        assertDoesNotThrow(() -> {
            t1 = new Table();
            t2 = new Table();
            t3 = new Table();
            t4 = new Table();
            t5 = new Table();
            t6 = new Table();
            t7 = new Table();
            t8 = new Table();
        });
    }

    @Test
    public void testToStringIRBF1() {
        e1.toString();
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        4|       +1|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        1|       +1|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        5|       +5|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        System.out.println("Table for ir_ballot_file_1.csv:\n" + e1.getTable().toString());
        assertEquals(expected_string.toString(), e1.getTable().toString());
    }

    @Test
    public void testPopulateIRBF1() {
        t1.populate(e1.getCandidates(), e1.getExhaustedPileTotals(), e1.getExhaustedPileUpdates(), e1.getTotalNumVotes());
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        4|       +1|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        1|       +1|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        5|       +5|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");
        assertEquals(expected_string.toString(), t1.toString());
    }

    @Test
    public void testToStringIRBF2() {
        e2.toString();
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        5|       +2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        5|       +5|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        System.out.println("Table for ir_ballot_file_2.csv:\n" + e2.getTable().toString());
        assertEquals(expected_string.toString(), e2.getTable().toString());
    }

    @Test
    public void testPopulateIRBF2() {
        t2.populate(e2.getCandidates(), e2.getExhaustedPileTotals(), e2.getExhaustedPileUpdates(), e2.getTotalNumVotes());
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        5|       +2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        5|       +5|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");
        assertEquals(expected_string.toString(), t2.toString());
    }

    @Test
    public void testToStringIRBF3() {
        e3.toString();
        StringBuilder expected_string1 = new StringBuilder();
        expected_string1.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string1.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        0|       -3|\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Kleinberg         |R        |        2|       +2|        2|        0|        3|       +1|        5|       +2|\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        1|       +1|\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_string1.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        StringBuilder expected_string2 = new StringBuilder();
        expected_string2.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string2.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        6|       +3|\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Kleinberg         |R        |        2|       +2|        2|        0|        3|       +1|        0|       -3|\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_string2.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        System.out.println("Table for ir_ballot_file_3.csv:\n" + e3.getTable().toString());
        assertTrue(expected_string1.toString().equals(e3.getTable().toString()) || expected_string2.toString().equals(e3.getTable().toString()));
    }


    @Test
    public void testPopulateIRBF3() {
        t3.populate(e3.getCandidates(), e3.getExhaustedPileTotals(), e3.getExhaustedPileUpdates(), e3.getTotalNumVotes());
        StringBuilder expected_string1 = new StringBuilder();
        expected_string1.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string1.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        0|       -3|\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Kleinberg         |R        |        2|       +2|        2|        0|        3|       +1|        5|       +2|\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        1|       +1|\n");
        expected_string1.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string1.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_string1.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        StringBuilder expected_string2 = new StringBuilder();
        expected_string2.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string2.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        6|       +3|\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Kleinberg         |R        |        2|       +2|        2|        0|        3|       +1|        0|       -3|\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string2.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string2.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_string2.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        assertTrue(expected_string1.toString().equals(t3.toString()) || expected_string2.toString().equals(t3.toString()));
    }

    @Test
    public void testToStringIRBF4() {
        e4.toString();
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        3|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        2|       +2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        5|       +5|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        System.out.println("Table for ir_ballot_file_4.csv:\n" + e4.getTable().toString());
        assertEquals(expected_string.toString(), e4.getTable().toString());
    }

    @Test
    public void testPopulateIRBF4() {
        t4.populate(e4.getCandidates(), e4.getExhaustedPileTotals(), e4.getExhaustedPileUpdates(), e4.getTotalNumVotes());
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        3|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        2|       +2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        5|       +5|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");
        assertEquals(expected_string.toString(), t4.toString());
    }

    @Test
    public void testToStringIRBF5() {
        e5.toString();
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        6|       +6|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        6|       +6|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");

        System.out.println("Table for ir_ballot_file_5.csv:\n" + e5.getTable().toString());
        assertEquals(expected_string.toString(), e5.getTable().toString());
    }

    @Test
    public void testPopulateIRBF5() {
        t5.populate(e5.getCandidates(), e5.getExhaustedPileTotals(), e5.getExhaustedPileUpdates(), e5.getTotalNumVotes());
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        6|       +6|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        6|       +6|\n");
        expected_string.append("+------------------+---------+---------+---------+\n");
        assertEquals(expected_string.toString(), t5.toString());
    }

    @Test
    public void testToStringIRBF6() {
        e6.toString();
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        0|       +0|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        System.out.println("Table for ir_ballot_file_6.csv:\n" + e6.getTable().toString());
        assertEquals(expected_string.toString(), e6.getTable().toString());
    }

    @Test
    public void testPopulateIRBF6() {
        t6.populate(e6.getCandidates(), e6.getExhaustedPileTotals(), e6.getExhaustedPileUpdates(), e6.getTotalNumVotes());
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        0|       +0|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");
        assertEquals(expected_string.toString(), t6.toString());
    }

    @Test
    public void testToStringIRBF7() {
        e7.toString();
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        5|       +2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        1|       +1|        1|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        System.out.println("Table for ir_ballot_file_7.csv:\n" + e7.getTable().toString());
        assertEquals(expected_string.toString(), e7.getTable().toString());
    }

    @Test
    public void testPopulateIRBF7() {
        t7.populate(e7.getCandidates(), e7.getExhaustedPileTotals(), e7.getExhaustedPileUpdates(), e7.getTotalNumVotes());
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        5|       +2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        1|       +1|        1|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");
        assertEquals(expected_string.toString(), t7.toString());
    }

    @Test
    public void testToStringIRBF8() {
        e8.toString();
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        4|       +1|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        1|       +1|        2|       +1|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");

        System.out.println("Table for ir_ballot_file_8.csv:\n" + e8.getTable().toString());
        assertEquals(expected_string.toString(), e8.getTable().toString());
    }


    @Test
    public void testPopulateIRBF8() {
        t8.populate(e8.getCandidates(), e8.getExhaustedPileTotals(), e8.getExhaustedPileUpdates(), e8.getTotalNumVotes());
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------+-------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|         Candidates         |      Round 1      |      Round 2      |      Round 3      |      Round 4      |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |  Party  |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Rosen             |D        |        3|       +3|        3|        0|        3|        0|        4|       +1|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kleinberg         |R        |        2|       +2|        2|        0|        2|        0|        0|       -2|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Chou              |I        |        0|        0|        0|        0|        0|        0|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Royce             |L        |        1|       +1|        1|        0|        0|       -1|        0|        0|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |         |        0|        0|        0|        0|        1|       +1|        2|       +1|\n");
        expected_string.append("+------------------+---------+---------+---------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |         |        6|       +6|                                                           |\n");
        expected_string.append("+------------------+---------+---------+---------+-----------------------------------------------------------+\n");
        assertEquals(expected_string.toString(), t8.toString());
    }

    @Test
    public void testPopulateStringWriteupExample() {
        Candidate c1 = new Candidate("Bob Kiss", "Progressive");
        Candidate c2 = new Candidate("Kurt Wright", "Republican");
        Candidate c3 = new Candidate("Andy Montroll", "Democrat");
        Candidate c4 = new Candidate("Dan Smith", "Independent");
        Candidate c5 = new Candidate("James Simpson", "Green");
        Candidate c6 = new Candidate("Adam Johnson", "Liberterian");
        Candidate[] candidates = {c1, c2, c3, c4, c5, c6};

        c1.updateRoundVotes(2585);
        c1.updateRoundVotes(2981);
        c1.updateRoundVotes(4313);

        c2.updateRoundVotes(2951);
        c2.updateRoundVotes(3294);
        c2.updateRoundVotes(4061);

        c3.updateRoundVotes(2063);
        c3.updateRoundVotes(2554);
        c3.updateRoundVotes(0);

        c4.updateRoundVotes(1306);
        c4.updateRoundVotes(0);
        c4.updateRoundVotes(0);

        c5.updateRoundVotes(35);
        c5.updateRoundVotes(0);
        c5.updateRoundVotes(0);

        c6.updateRoundVotes(36);
        c6.updateRoundVotes(0);
        c6.updateRoundVotes(0);

        List<Integer> exhausted_pile_totals = new ArrayList<>();
        List<Integer> exhausted_pile_updates = new ArrayList<>();
        exhausted_pile_totals.add(4);
        exhausted_pile_totals.add(151);
        exhausted_pile_totals.add(606);
        exhausted_pile_updates.add(4);
        exhausted_pile_updates.add(4);
        exhausted_pile_updates.add(147);
        exhausted_pile_updates.add(455);
        int votes = 8980;

        t9.populate(candidates, exhausted_pile_totals, exhausted_pile_updates, votes);
        StringBuilder expected_string = new StringBuilder();
        expected_string.append("+----------------------------------+-------------------+-------------------+-------------------+\n");
        expected_string.append("|            Candidates            |      Round 1      |      Round 2      |      Round 3      |\n");
        expected_string.append("+------------------+---------------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|    Candidate     |     Party     |  Votes  |    ±    |  Votes  |    ±    |  Votes  |    ±    |\n");
        expected_string.append("+------------------+---------------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Bob Kiss          |Progressive    |     2585|    +2585|     2981|     +396|     4313|    +1332|\n");
        expected_string.append("+------------------+---------------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Kurt Wright       |Republican     |     2951|    +2951|     3294|     +343|     4061|     +767|\n");
        expected_string.append("+------------------+---------------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Andy Montroll     |Democrat       |     2063|    +2063|     2554|     +491|        0|    -2554|\n");
        expected_string.append("+------------------+---------------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Dan Smith         |Independent    |     1306|    +1306|        0|    -1306|        0|        0|\n");
        expected_string.append("+------------------+---------------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|James Simpson     |Green          |       35|      +35|        0|      -35|        0|        0|\n");
        expected_string.append("+------------------+---------------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Adam Johnson      |Liberterian    |       36|      +36|        0|      -36|        0|        0|\n");
        expected_string.append("+------------------+---------------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Exhausted Pile    |               |        4|       +4|      151|       +4|      606|     +147|\n");
        expected_string.append("+------------------+---------------+---------+---------+---------+---------+---------+---------+\n");
        expected_string.append("|Totals            |               |     8980|    +8980|                                       |\n");
        expected_string.append("+------------------+---------------+---------+---------+---------------------------------------+\n");
        System.out.println("Table for example provided in write-up:\n" + t9.toString());
        assertEquals(expected_string.toString(), t9.toString());
    }
}
