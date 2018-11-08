package ui;

import elections.*;
import java.io.*;

public class UserInterface {

    public String requestBallotFilename(String request, OutputStream out, InputStream in) {
        try {
            out.write(request.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String filename;
        try {
            filename = br.readLine();
            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void displayResults(Election e, OutputStream out) {
        StringBuilder sb = new StringBuilder();

        sb.append("The winning parties are:\n");
        for (Party p : e.getPartyWinners()) {
            sb.append(p.getName()).append(" with ").append(p.getNumVotes()).append(" votes\n");
        }

        sb.append("\nThe winning candidates are:\n");
        for (Candidate c : e.getCandidateWinners()) {
            sb.append(c.getName())
                    .append(" (")
                    .append(c.getParty())
                    .append(") ")
                    .append("with ")
                    .append(c.getNumVotes())
                    .append(" votes\n");
        }

        String result = sb.toString();
        try {
            out.write(result.getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
