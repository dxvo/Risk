import java.lang.Math;

/***
 * This Die class is to roll a random number
 * @author De, William and Carlos
 * @version 1.0
 * @since 10/2018
 */
public class Die
{
	int value;

	/***
	 * constructor
	 * die value is set to 0 initially
	 */
	public Die() { value = 0;}


	/***
	 * randomly generate a nunber between 0 and 6
	 * @return value of die
	 */
	public int roll()
	{
		value = (int)(Math.random() * 6) + 1; // 1 to 6
		return  value;
	}

	/***
	 * getter function which returns die value
	 * @return
	 */
	public int getValue() {return value; }

	/***
	 * set die to certain value
	 * @param a: parameter value to be set for the die
	 */
	public void setValue(int a){ value = a;}
}
