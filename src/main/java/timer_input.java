import java.io.*;

/***
 * This timer class is to set a time limit for user
 *The time limit can be change. The current value is set to 6000 ms or 6s
 * If the user does not input a choice while it is his/her turn, then he/she loses the turn
 * The class has 1 private parameter which is choice. This choice is the player choice option during player turn
 * @author De Vo
 * @version 1.1
 * @since 2018-11
 */
public class timer_input
{
    private int choice = 0;

    /***
     * The method set time limit to get user input
     * If player does not enter a choice within 6s, the choice to set to be 6
     * The choice = 6 which means the player has ended his turn and turn is turned to next player in Playerlist
     * @return - the interger value of current player if enter within the set time limit
     */
    public int get_input(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();

        try
        {
            while ((System.currentTimeMillis() - startTime) < 6 * 1000
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
