import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import twitter4j.TwitterException;

import static org.junit.Assert.*;
import java.io.PrintStream;

public class TweeterTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private PrintStream console;
    Tweeter twitter = new Tweeter(console);
    @Test
    public void tweetOut() throws TwitterException {
        String test = "TestString";

        twitter.tweetOut(test);

        exception.expect(TwitterException.class);

    }
}