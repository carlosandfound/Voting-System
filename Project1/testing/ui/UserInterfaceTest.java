package ui;

import elections.IRElection;
import elections.OPLElection;
import fileio.BallotFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

    private OPLElection opl;
    private IRElection ir;
    private BallotFile bf;
    private UserInterface ui;

    @BeforeEach
    void setUp() {
        ui = new UserInterface();
    }

    @Test
    void requestBallotFilenameSuccess() {
        String input = "simple_opl_ballot_file.csv";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String ballot_filename = ui.requestBallotFilename("Enter a ballot filename", System.out, System.in);
        assertEquals(ballot_filename, input);
    }

    @Test
    void requestBallotFilenameFail() {
        String typo_input = "simples_opl_ballot_file.csv";
        String corrected_input = "simple_opl_ballot_file.csv";
        System.setIn(new ByteArrayInputStream(typo_input.getBytes()));
        String ballot_filename = ui.requestBallotFilename("Enter a ballot filename", System.out, System.in);
        assertNotEquals(ballot_filename, corrected_input);
    }

    @Disabled
    @Test
    void displayResults() {
    }
}