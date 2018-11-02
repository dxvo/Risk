import java.io.*;
import java.util.Scanner;

public class timer_input
{
    private int choice = 0;

    public int get_input(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();

        try
        {
            while ((System.currentTimeMillis() - startTime) < 4 * 1000
                    && !in.ready()) {
            }
            if (in.ready()) {
                String str = "";
                str = in.readLine();
                choice = Integer.parseInt(str);
                System.out.println("You entered: " + str);

            } else {
                System.out.println("\nYou did not enter anything. ");
                choice = 6; // set choice to 6 to automatically move to next player
            }
        }

        catch (Exception e)
        {
            System.out.println( e );
        }
        return choice;
    }

}
