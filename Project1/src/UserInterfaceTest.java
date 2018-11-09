
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
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
    void requestBallotFilenameSuccess() {
        String input = "simple_opl_ballot_file.csv";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String ballot_filename = ui.requestBallotFilename(System.out, System.in);
        assertEquals(ballot_filename, input);
    }

    @Test
    void requestBallotFilenameFail() {
        String typo_input = "simples_opl_ballot_file.csv";
        String corrected_input = "simple_opl_ballot_file.csv";
        System.setIn(new ByteArrayInputStream(typo_input.getBytes()));
        String ballot_filename = ui.requestBallotFilename(System.out, System.in);
        assertNotEquals(ballot_filename, corrected_input);
    }

    @Test
    void displayInvalidElectionTypeSuccess() {
        ConsoleOutputCapturer coc = new ConsoleOutputCapturer();
        coc.start();
        ui.displayInvalidElectionType("CPL", System.out);
        String capture = coc.stop();
        assertEquals("Detected election type as CPL. Election type must be IR or OPL\n", capture);
    }

    @Test
    void displayInvalidElectionTypeFail() {
        ConsoleOutputCapturer coc = new ConsoleOutputCapturer();
        coc.start();
        ui.displayInvalidElectionType("IR", System.out);
        String capture = coc.stop();
        assertNotEquals("Detected election type as CPL. Election type must be IR or OPL\n", capture);
    }

    @Test
    void displayExceptionMessageSuccess() {
        Exception e = new Exception("This is an exception message");
        ConsoleOutputCapturer coc = new ConsoleOutputCapturer();
        coc.start();
        ui.displayExceptionMessage(e, System.out);
        String capture = coc.stop();
        assertEquals("This is an exception message\n", capture);
    }

    @Test
    void displayExceptionMessageFail() {
        Exception e = new Exception("This is an exception message");
        ConsoleOutputCapturer coc = new ConsoleOutputCapturer();
        coc.start();
        ui.displayExceptionMessage(e, System.out);
        String capture = coc.stop();
        assertNotEquals("Thiss is an exception message\n", capture);
    }

    @Disabled
    @Test
    void displayResults() {
    }

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