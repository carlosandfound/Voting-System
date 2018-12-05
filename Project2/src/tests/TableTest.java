/*
 * File: TableTest.java
 *
 * Description: this file contains the unit tests for all public methods within the Table class.
 *
 * Authors: Michael McLaughlin, Xiaochen Zhang
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TableTest {

    private static BallotFile bf;
    private static IRElection e;
    private static Table t;

    @BeforeAll
    static void setup() {
        String ir_ballot_file_6_path = FileSystems
                .getDefault()
                .getPath("testing", "ir_ballot_file_6.csv")
                .toString();
        File ballot_file = new File(ir_ballot_file_6_path);
        try {
            bf = new BallotFile(ballot_file);
            e = new IRElection(bf);
            t = new Table();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void tableConstructorTest() {
        assertDoesNotThrow(() -> {
            t = new Table();
        });
    }

    @Test
    public void testToStringIRBF6() {
        e.toString();
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
        assertEquals(expected_string.toString(), e.getTable().toString()) ;
    }

    @Test
    public void testPopulateIRBF6() {
        e.toString();
        t.populate(e.getCandidates(), e.getExhaustedPileTotals(), e.getExhaustedPileUpdates(), e.getTotalNumVotes());
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
        assertEquals(expected_string.toString(), t.toString());
    }
}