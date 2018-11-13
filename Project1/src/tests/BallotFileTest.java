/*
 * File: BallotFileTest.java
 *
 * Description: this file contains the unit tests for all public methods within the BallotFile class.
 *
 * Authors: Justin Koo
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import static org.junit.jupiter.api.Assertions.*;

class BallotFileTest {

    private static BallotFile bf;
    private static File ballot_file;

    @BeforeEach
    void setup() {
        ballot_file = new File(FileSystems.getDefault()
                .getPath("testing", "simple_opl_ballot_file.csv")
                .toString());
        try {
            bf = new BallotFile(ballot_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void BallotFileConstructorDoesNotThrowExceptions() {
        assertDoesNotThrow(() -> {
            BallotFile _bf = new BallotFile(ballot_file);
        });
    }

    @Test
    void BallotFileConstructorThrowsIOException() {
        assertThrows(IOException.class, () -> {
            String file_path = FileSystems.getDefault().getPath("testing", "doesnotexist.csv").toString();
            File doesNotExist = new File(file_path);
            BallotFile _bf = new BallotFile(doesNotExist);
        });
    }

    @Test
    void getFilename() {
        Assertions.assertEquals("simple_opl_ballot_file.csv", bf.getFilename());
    }

    @Test
    void getLine3SimpleOPL() {
        Assertions.assertEquals("[Pike,D], [Foster,D],[Deutsch,R], [Borg,R], [Jones,R],[Smith,I]",
                bf.getLine(3));
    }

    @Test
    void getLineLastLineSimpleOPL() {
        Assertions.assertEquals(",1,,,,", bf.getLine(bf.getNumLines()));
    }

    @Test
    void getLineThrowsIndexOutOfBoundsExceptionSimpleOPL() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            bf.getLine(bf.getNumLines() + 1);
        });
    }

    @Test
    void getElectionTypeSimpleOPL() {
        Assertions.assertEquals("OPL", bf.getElectionType());
    }
}