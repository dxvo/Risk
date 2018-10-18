public class TurnDelta
{
	private int attackerID;
	private int attackerX;
	private int attackerY;

	private boolean attackerVictory;

	
	private int defenderID;
	private int defenderX;
	private int defenderY;


	public TurnDelta()
	{
		
	}
	
	public void setDelta(int aID, int aX, int aY, boolean aV, int dID, int dX, int dY)
	{
		attackerID = aID;
		attackerX = aX;
		attackerY = aY;

		attackerVictory = aV;
		
		defenderID = dID;
		defenderX = dX;
		defenderY = dY;

	}
	
	public int getAttackerID()
	{
		return attackerID;
	}
	public int getAttackerX()
	{
		return attackerX;
	}
	public int getAttackerY()
	{
		return attackerY;
	}
	public boolean getAttackerVictory()
	{
		return attackerVictory;
	}
	
	public int getDefenderID()
	{
		return defenderID;
	}
	public int getDefenderX()
	{
		return defenderX;
	}
	public int getDefenderY()
	{
		return defenderY;
	}
}
