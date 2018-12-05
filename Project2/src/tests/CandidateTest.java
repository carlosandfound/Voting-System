/*
 * File: CandidateTest.java
 *
 * Description: this file contains the unit tests for all public methods within the Candidate class.
 *
 * Authors: Justin Koo, Xiaochen Zhang
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CandidateTest {

    private static Candidate c;

    @BeforeEach
    void setup () {
        c = new Candidate("Borg", "D");
    }

    @Test
    void CandidateConstructor() {
        assertDoesNotThrow(() -> {
            c = new Candidate("McCain", "R");
        });
    }

    @Test
    void getName() {
        assertEquals("Borg", c.getName());
    }

    @Test
    void getParty() {
        assertEquals("D", c.getParty());
    }

    @Test
    void getNumVotes() {
        assertEquals(0, c.getNumVotes());
    }

    @Test
    void getAcquiredBallotsIsEmpty() {
        assertTrue(c.getAcquiredBallots().isEmpty());
    }

    @Test
    void setName() {
        c.setName("Pike");
        assertEquals("Pike", c.getName());
    }

    @Test
    void setParty() {
        c.setParty("R");
        assertEquals("R", c.getParty());
    }

    @Test
    void acquireBallotCorrectBallot() {
        c.acquireBallot(0);
        assertTrue(c.getAcquiredBallots().get(0) == 0);
    }

    @Test
    void acquireBallotCorrectNumVotes() {
        c.acquireBallot(0);
        assertEquals(1, c.getNumVotes());
    }

    @Test
    void testTotalRoundVotes() {
        assertEquals(Arrays.asList(), c.getTotalRoundVotes());
    }

    @Test
    void testUpdatedRoundVotes() {
        c.getUpdatedRoundVotes();
        assertEquals(Arrays.asList(), c.getUpdatedRoundVotes());

    }

    @Test
    void testRemoveVote() {
        c.removeVote();
        assertEquals(0, c.getNumVotes());
    }

    @Test
    void testUpdateRoundVotes() {
        c.updateRoundVotes(2);
        assertEquals(Arrays.asList(2), c.getTotalRoundVotes());
        assertEquals(Arrays.asList(2), c.getUpdatedRoundVotes());
    }
}