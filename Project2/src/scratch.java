import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Class use for testing purposes
 */
public class scratch {

    public static void main(String[] args) {
        Candidate c1 = new Candidate("Bob", "P");
        Candidate c2 = new Candidate("Kurt", "R");
        Candidate c3 = new Candidate("Andy", "D");
        Candidate c4 = new Candidate("Dan", "I");
        Candidate c5 = new Candidate("James", "G");
        Candidate c6 = new Candidate("Write-in", "N/A");


        Candidate[] candidates = {c1, c2, c3, c4, c5, c6};

        c1.updateRoundVotes(2585);
        c1.updateRoundVotes(2981);
        c1.updateRoundVotes(4313);

        c2.updateRoundVotes(2951);
        c2.updateRoundVotes(3294);
        c2.updateRoundVotes(4061);

        c3.updateRoundVotes(2063);
        c3.updateRoundVotes(2554);
        c3.updateRoundVotes(0);

        c4.updateRoundVotes(1306);
        c4.updateRoundVotes(0);
        c4.updateRoundVotes(0);

        c5.updateRoundVotes(35);
        c5.updateRoundVotes(0);
        c5.updateRoundVotes(0);

        c6.updateRoundVotes(36);
        c6.updateRoundVotes(0);
        c6.updateRoundVotes(0);

        List<Integer> exhausted_pile_totals = new ArrayList<>();
        List<Integer> exhausted_pile_updates = new ArrayList<>();
        exhausted_pile_totals.add(4);
        exhausted_pile_totals.add(151);
        exhausted_pile_totals.add(606);
        exhausted_pile_updates.add(4);
        exhausted_pile_updates.add(4);
        exhausted_pile_updates.add(147);
        exhausted_pile_updates.add(455);

        int votes = 8980;

        Table table = new Table();


        table.populate(candidates, exhausted_pile_totals, exhausted_pile_updates, votes);

        System.out.println(table);
    }
}
