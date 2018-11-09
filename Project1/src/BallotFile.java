/*
 * File: BallotFile.java
 *
 * Description: this file contains the class implementation for the BallotFile class used in the "Voting System" product
 *
 * Author: Justin Koo
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>BallotFile</h1>
 * <h2>Purpose</h2>
 * The BallotFile class provides utility methods to automatically read a specific {@code File ballot_file} and to obtain
 * information from each line of it.
 */
public class BallotFile {

    private String filename;
    private List<String> data;

    /**
     * Initializes a {@code BallotFile instance} by reading each line of the specified {@code ballot_file}
     * @param ballot_file A {@code File} object specifying the ballot file to be used.
     * @throws IOException Throws an {@code IOException} if opening or reading the specified {@code ballot_file} fails.
     */
    public BallotFile(File ballot_file) throws IOException {
        this.filename = ballot_file.getName();
        this.data = new ArrayList<>();
        FileInputStream bf_input_stream = new FileInputStream(ballot_file);
        BufferedReader br = new BufferedReader(new InputStreamReader(bf_input_stream));

        // TODO: perhaps read the entire file just once instead of multiple times to save time?
        String line;
        while ((line = br.readLine()) != null) {
            data.add(line);
        }
    }

    /**
     * Accessor method for the filename of the {@code ballot_file} passed into the constructor
     * @return a {@code String} denoting the filename of the specified {@code ballot_file}.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Provides a method to obtain a specific line from the specified {@code ballot_file} passed into the constructor.
     * @param line_num The line number for which the contents of the {@code ballot_file} is to be returned.
     * @return a {@code String} denoting the contents of the specified {@code ballot_file} at line {@code line_num}.
     */
    public String getLine(int line_num) {
        return data.get(line_num - 1);
    }

    /**
     * Provides a method to obtain the number of lines within the specified {@code ballot_file} passed into the constructor.
     * @return a {@code int} denoting the number of lines contained within {@code ballot_file}.
     */
    public int getNumLines() {
        return data.size();
    }

    /**
     * Provides a method to obtain the election type of the specified {@code ballot_file} passed into the constructor.
     * @return a {@code String} denoting the election type of {@code ballot_file}, e.g. "IR" or "OPL".
     */
    public String getElectionType() {
        return getLine(1);
    }
}
