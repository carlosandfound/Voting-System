/*
 * File: UserInterfaceTest.java
 *
 * Description: this file contains the unit tests for all public methods within the UserInterface class.
 *
 * Authors: Carlos Alvarenga, Justin Koo
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.List;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    private UserInterface ui;

    @BeforeEach
    void setUp() {
        ui = new UserInterface();
    }

    @Test
    void userInterfaceConstructor() {
        assertDoesNotThrow(() -> {
            ui = new UserInterface();
        });
    }

    @Test
    void cancelButtonPressed() {
        assertTrue(!ui.cancelButtonPressed());
    }

    @Test
    void setTerminationRequest() {
        ui.setTerminationRequest(true);
        assertTrue(ui.cancelButtonPressed());
    }

    @Test
    void getHasBeenRun() {
        assertTrue(!ui.getHasBeenRun());
    }

    @Test
    void setHasBeenRun() {
        ui.setHasBeenRun(true);
        assertTrue(ui.getHasBeenRun());
    }

    @Test
    void requestBallotFilenameWhenHasBeenRun() {
        ui.setHasBeenRun(true);
        assertEquals("", ui.requestBallotFilename());
    }

    @Test
    void displayInvalidElectionType() {
        ConsoleOutputCapturer coc = new ConsoleOutputCapturer();
        coc.start();
        ui.displayInvalidElectionType("CPL", System.out);
        String capture = coc.stop();
        assertEquals("Detected election type as CPL. Election type must be IR or OPL\n", capture);
    }

    @Test
    void displayExceptionMessage() {
        Exception e = new Exception("This is an exception message");
        ConsoleOutputCapturer coc = new ConsoleOutputCapturer();
        coc.start();
        ui.displayExceptionMessage(e, System.out);
        String capture = coc.stop();
        assertEquals("This is an exception message\n", capture);
    }

    @Test
    void displayResultsNotEmpty() throws IOException {
        String filepath = FileSystems.getDefault().getPath("testing", "simple_opl_ballot_file.csv").toAbsolutePath().toString();
        File ballot_file = new File(filepath);
        BallotFile bf = new BallotFile((ballot_file));
        Election e = new OPLElection(bf);
        ConsoleOutputCapturer coc = new ConsoleOutputCapturer();
        coc.start();
        ui.displayResults(e, System.out);
        String capture = coc.stop();
        assertTrue(!capture.isEmpty());
    }

    /*
     * Utility method to capture console input and output for automated testing purposes.
     */
    public class ConsoleOutputCapturer {
        private ByteArrayOutputStream baos;
        private PrintStream previous;
        private boolean capturing;

        public void start() {
            if (capturing) {
                return;
            }

            capturing = true;
            previous = System.out;
            baos = new ByteArrayOutputStream();

            OutputStream outputStreamCombiner =
                    new OutputStreamCombiner(Arrays.asList(previous, baos));
            PrintStream custom = new PrintStream(outputStreamCombiner);

            System.setOut(custom);
        }

        public String stop() {
            if (!capturing) {
                return "";
            }

            System.setOut(previous);

            String capturedValue = baos.toString();

            baos = null;
            previous = null;
            capturing = false;

            return capturedValue;
        }

        private class OutputStreamCombiner extends OutputStream {
            private List<OutputStream> outputStreams;

            public OutputStreamCombiner(List<OutputStream> outputStreams) {
                this.outputStreams = outputStreams;
            }

            public void write(int b) throws IOException {
                for (OutputStream os : outputStreams) {
                    os.write(b);
                }
            }

            public void flush() throws IOException {
                for (OutputStream os : outputStreams) {
                    os.flush();
                }
            }

            public void close() throws IOException {
                for (OutputStream os : outputStreams) {
                    os.close();
                }
            }
        }
    }
}
