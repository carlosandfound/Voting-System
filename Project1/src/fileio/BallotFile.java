package fileio;

public class BallotFile {

    private String filename;
    private String[] data;

    public BallotFile(String filename) {
        this.filename = filename;

        // TODO: read "filename" and load the data into "data"
    }

    public String getFilename() {
        return filename;
    }

    public String getLine(int line_num) {
        return data[line_num-1];
    }

    public String getElectionType() {
        return getLine(1);
    }
}
