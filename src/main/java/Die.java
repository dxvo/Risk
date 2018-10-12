import java.lang.Math;

public class Die
{
	int value;
	
	public Die() { value = 0;}

	public int roll()
	{
		value = (int)(Math.random() * 6) + 1; // 1 to 6
		return  value;
	}
	
	public int getValue() {return value; }
	public void setValue(int a){ value = a; }
}
