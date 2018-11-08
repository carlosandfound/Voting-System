package fileio;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class BallotFile {

    private String filename;
    private List<String> data;

    public BallotFile(File ballot_file) {
        this.filename = ballot_file.getName();
        this.data = new ArrayList<>();
        try {
            FileInputStream bf_input_stream = new FileInputStream(ballot_file);
            BufferedReader br = new BufferedReader(new InputStreamReader(bf_input_stream));

            // TODO: perhaps read the entire file just once instead of multiple times to save time?
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilename() {
        return filename;
    }

    public String getLine(int line_num) {
        return data.get(line_num - 1);
    }

    public String getElectionType() {
        return getLine(1);
    }
}
