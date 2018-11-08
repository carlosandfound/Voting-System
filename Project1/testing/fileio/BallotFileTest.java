package fileio;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class BallotFileTest {

    private static BallotFile bf;

    @BeforeAll
    static void setup() {
        File ballot_file = new File(FileSystems.getDefault()
                .getPath("testing", "simple_opl_ballot_file.csv")
                .toString());
        bf = new BallotFile(ballot_file);
    }

    @Test
    void getFilenameSuccess() {
        assertEquals("simple_opl_ballot_file.csv", bf.getFilename());
    }

    @Test
    void getFilenameFail() {
        assertNotEquals("simples_opl_ballot_file.csv", bf.getFilename());
    }

    @Test
    void getLine3SimpleOPLSuccess() {
        assertEquals("[Pike,D], [Foster,D],[Deutsch,R], [Borg,R], [Jones,R],[Smith,I]",
                bf.getLine(3));
    }

    @Test
    void getLine3SimpleOPLFail() {
        assertNotEquals("[Pike,R], [Foster,D],[Deutsch,R], [Borg,R], [Jones,R],[Smith,I]",
                bf.getLine(3));
    }

    @Test
    void getLineDoesNotExist() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            bf.getLine(300);
        });
    }

    @Test
    void getElectionTypeOPLSuccess() {
        assertEquals("OPL", bf.getElectionType());
    }

    @Test
    void getElectionTypeOPLFail() {
        assertNotEquals("IR", bf.getElectionType());
    }
}