/*
 * File: PartyTest.java
 *
 * Description: this file contains the unit tests for all public methods within the Party class.
 *
 * Authors: Justin Koo
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest {
    private static BallotFile bf;
    private static OPLElection e;
    private static Party p;

    @BeforeEach
    void setup() {
        p = new Party("D", 3);
    }

    @Test
    void partyConstructor() {
        assertDoesNotThrow(() -> {
            p = new Party("R", 100);
        });
    }

    @Test
    void getName() {
        assertEquals("D", p.getName());
    }

    @Test
    void getNumCandidates() {
        assertEquals(3, p.getNumCandidates());
    }

    @Test
    void getNumSeats() {
        assertEquals(0, p.getNumSeats());
    }

    @Test
    void getNumVotes() {
        assertEquals(0, p.getNumVotes());
    }

    @Test
    void getAcquiredBallotsIsEmpty() {
        assertTrue(p.getAcquiredBallots().isEmpty());
    }

    @Test
    void setName() {
        p.setName("R");
        assertEquals("R", p.getName());
    }

    @Test
    void setNumCandidates() {
        p.setNumCandidates(4);
        assertEquals(4, p.getNumCandidates());
    }

    @Test
    void acquireBallotCorrectBallot() {
        p.acquireBallot(0);
        assertTrue(p.getAcquiredBallots().get(0) == 0);
    }

    @Test
    void acquireBallotCorrectNumVotes() {
        p.acquireBallot(0);
        assertTrue(p.getNumVotes() == 1);
    }
}