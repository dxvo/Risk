import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/***
 * This Class is used to tweet out the game result such as end of each battle, each turn or end of game
 * The class using Tweeter API with private accessKey
 *
 * @author Carlos
 * @version 1.2
 * @since 2018-10
 */
public class Tweeter
{
    private Twitter twitter;
    private PrintStream consolePrint;
    private List<Status> statuses;

    /***
     * Constructor method
     * Makes an instance of twitter - this is re-usable and thread safe
     * This connects to twitter and performs authorization
     * @param console the object type Printstream
     */
    public Tweeter(PrintStream console)
    {
        twitter = TwitterFactory.getSingleton();
        consolePrint = console;
        statuses = new ArrayList<Status>();
    }


    /***
     * This method will tweet a given message
     * @param message the message that will be send after each event
     * @throws TwitterException throw excecption if cant tweet message
     */
    public void tweetOut(String message) throws TwitterException
    {
            Status status = twitter.updateStatus(message);
            System.out.println("Successfully updated the status to [" + status.getText() + ']');

    }

}
