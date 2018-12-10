/*
 * File: SystemTest.java
 *
 * Description: this file contains the automated system tests for the "Voting System" product
 *
 * Authors: Justin Koo
 */

import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

public class SystemTest {

    private int runVotingSystemWithFilenameAsArgument(String filename) {
        String[] args = {filename};
        VotingSystem.main(args);
        System.out.println("\n");
        return 0;
    }

    @Test
    void VotingSystemIRBallotFile1NoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/ir_ballot_file_1.csv");
        });
    }

    @Test
    void VotingSystemIRBallotFile2NoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/ir_ballot_file_2.csv");
        });
    }

    @Test
    void VotingSystemIRBallotFile3NoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/ir_ballot_file_3.csv");
        });
    }

    @Test
    void VotingSystemIRBallotFile4NoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/ir_ballot_file_4.csv");
        });
    }

    @Test
    void VotingSystemIRBallotFile5NoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/ir_ballot_file_5.csv");
        });
    }

    @Test
    void VotingSystemIRBallotFile6NoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/ir_ballot_file_6.csv");
        });
    }

    @Test
    void VotingSystemIRBallotFile7NoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/ir_ballot_file_7.csv");
        });
    }

    @Test
    void VotingSystemIRBallotFile8NoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/ir_ballot_file_8.csv");
        });
    }

    @Test
    void VotingSystemLargeIRBallotFileNoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/large_ir_ballot_file.csv");
        });
    }

    @Test
    void VotingSystemOPLBallotFile0Candidate0Seat0BallotNoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/opl_ballot_file_0_candidate_0_seat_0_ballot.csv");
        });
    }

    @Test
    void VotingSystemOPLBallotFile0Candidate1Seat0BallotNoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/opl_ballot_file_0_candidate_1_seat_0_ballot.csv");
        });
    }

    @Test
    void VotingSystemOPLBallotFile1Candidate0Seat0BallotNoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/opl_ballot_file_1_candidate_0_seat_0_ballot.csv");
        });
    }

    @Test
    void VotingSystemOPLBallotFile1Candidate0Seat1BallotNoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/opl_ballot_file_1_candidate_0_seat_1_ballot.csv");
        });
    }

    @Test
    void VotingSystemOPLBallotFile1Candidate1Seat0BallotNoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/opl_ballot_file_1_candidate_1_seat_0_ballot.csv");
        });
    }

    @Test
    void VotingSystemOPLBallotFile1Candidate1Seat1BallotNoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/opl_ballot_file_1_candidate_1_seat_1_ballot.csv");
        });
    }

    @Test
    void VotingSystemSimpleOPLBallotFileNoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/simple_opl_ballot_file.csv");
        });
    }

    @Test
    void VotingSystemLargeOPLBallotFileNoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/large_opl_ballot_file.csv");
        });
    }

    @Test
    void VotingSystemArgumentProvidedLargeOPLBallotFileNoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/large_opl_ballot_file.csv");
        });
    }

    @Test
    void VotingSystemInvalidArgumentProvidedNoErrors() {
        assertDoesNotThrow(() -> {
            runVotingSystemWithFilenameAsArgument("testing/large_opl_ballot_file.csv");
        });
    }

    @Test
    void VotingSystemLargeOPLBallotFileUnder8Minutes() {
        assertTimeout(ofMinutes(8), () -> {
            runVotingSystemWithFilenameAsArgument("testing/large_opl_ballot_file.csv");
        });
    }

    @Test
    void VotingSystemLargeIRBallotFileUnder8Minutes() {
        assertTimeout(ofMinutes(8), () -> {
            runVotingSystemWithFilenameAsArgument("testing/large_ir_ballot_file.csv");
        });
    }
}
