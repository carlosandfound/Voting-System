import java.util.Set;

public interface Election {
    void runElection(); // Want this method to be private, but Java 8 does not allow this. Java 9 does.
    Set<Candidate> getCandidateWinners();
    Set<Party> getPartyWinners();
}
