import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class timer
{
    private String str = "";

    TimerTask task = new TimerTask()
    {
        public void run()
        {
            if( str.equals(""))
                System.out.println( "You input nothing. Switching to next player..." );
            //System.exit( 0 );
            return;
        }
    };

    public int getInput() throws Exception
    {
        Timer timer = new Timer();
        timer.schedule( task, 3*1000 );
        System.out.println( "Please Input within 3 seconds: " );
        BufferedReader in = new BufferedReader(
                new InputStreamReader( System.in ) );
        str = in.readLine();
        int n = Integer.parseInt(str);//this is to convert str into int
        timer.cancel();
        return n;
    }

}
