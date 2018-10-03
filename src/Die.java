import java.util.Random;
import java.util.Scanner;
import java.util.Random;

public class Die
{
	int numSides;
	int value;
	
	public Die()
	{
		numSides = 6;
		value = 0;
	}
	
	public Die(int sides)
	{
		numSides = sides;
		value = 0;
	}
	
	public int roll()
	{

		Random rand = new Random();
		rand.setSeed(rand.nextInt(20));
		value = rand.nextInt(6) + 1;  //minimum is 1 , max is 6
		System.out.printf("%d\n",value);
		return  value;
	}
	
	public int getValue()
	{
		return value;
	}
}
