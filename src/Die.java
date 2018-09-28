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
	
	public void roll()
	{
		
	}
	
	public int getValue()
	{
		return value;
	}
}
