/*
 * File: Election.java
 *
 * Description: this file contains the interface definition for the Election interface used in the "Voting System" product
 *
 * Authors: Justin Koo
 */

import java.util.Set;

/**
 * <h1>Election</h1>
 * <h2>Purpose</h2>
 * Requires that any object, most likely some sort of election, to implement methods to get the winners of an election.
 */
public interface Election {

    /**
     * Method to obtain all candidate winners of an election.
     * @return A {@code Set<Candidate>} containing all {@code Candidate} objects that are winners in the election.
     */
    Set<Candidate> getCandidateWinners();

    /**
     * Method to obtain all the party winners of an election.
     * @return A {@code Set<Party>} containing all {@code Party} objects that are winners in the election.
     */
    Set<Party> getPartyWinners();
}
