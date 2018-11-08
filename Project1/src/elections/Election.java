package elections;

import java.util.Set;

public interface Election {
    void runElection();
    Set<Candidate> getCandidateWinners();
    Set<Party> getPartyWinners();
}
