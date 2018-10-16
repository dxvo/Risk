public class TurnDelta
{
	private int attackerID;
	private int attackerX;
	private int attackerY;
	private int attackerTroopsLost;
	private boolean attackerVictory;

	
	private int defenderID;
	private int defenderX;
	private int defenderY;
	private int defenderTroopsLost;

	public TurnDelta()
	{
		
	}
	
	public void setDelta(int aID, int aX, int aY, int aTL, boolean aV, int dID, int dX, int dY, int dTL)
	{
		attackerID = aID;
		attackerX = aX;
		attackerY = aY;
		attackerTroopsLost= aTL;
		attackerVictory = aV;
		
		defenderID = dID;
		defenderX = dX;
		defenderY = dY;
		defenderTroopsLost = dTL;
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
