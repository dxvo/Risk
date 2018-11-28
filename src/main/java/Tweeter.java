import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;

public class Tweeter
{
    private Twitter twitter;
    private PrintStream consolePrint;
    private List<Status> statuses;



    public Tweeter(PrintStream console)
    {
        //Makes an instance of twitter - this is re-usable and thread safe
        //connects to twitter and performs authorization
        //twitter = new TwitterFactory().getInstance();
        twitter = TwitterFactory.getSingleton();
        consolePrint = console;
        statuses = new ArrayList<Status>();
    }

    /* *********** Part 1 ********* */
    /* This method will tweet a given message */
    public void tweetOut(String message) throws TwitterException//, IOException
    {
            Status status = twitter.updateStatus(message);
            System.out.println("Successfully updated the status to [" + status.getText() + ']');

    }

}
