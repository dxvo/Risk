public class Player
{
	private int playerID;
	private int numUnits;
	private int numBenchedUnits;
	private int numTerritories;
	private int die_value;
	private int credit_balance;

	public Player()
	{
		playerID = 0;
		numUnits = 0;
		numBenchedUnits = 0;
		numTerritories = 0;
		die_value = 0;
	}
	
	public Player(int id)
	{
		playerID = id;
		numUnits = 0;
		numBenchedUnits = 0;
		numTerritories = 0;
		die_value = 0;
	}
	

	public void setPlayerID(int id)
	{
		if(id >= 0)
			playerID = id;
	}
	
	public void setNumUnits(int units)
	{
		if(units >= 0)
			numUnits = units;
	}
	
	public void setNumBenchedUnits(int units)
	{
		if(units >= 0)
			numBenchedUnits = units;
	}
	
	public void setNumTerritories(int count)
	{
		if(count >= 0)
			numTerritories = count;
	}

	public void setDie_value(int value)
	{
		if(value >= 0)
			die_value = value;
	}

	public void addBenchedUnits(int count)
	{
		if(count < 1)
			return;
		
		numBenchedUnits += count;
	}
	
	public void deployBenchedUnits(int count)
	{
		if(count < 1)
			return;
		
		if(numBenchedUnits < count)
		{
			numUnits += count - numBenchedUnits;
			numBenchedUnits = 0;
		}
		else
		{
			numUnits += count;
			numBenchedUnits -= count;
		}
	}

	public void setCredit_balance(int balance){credit_balance = balance;}

	public int getCredit_balance() {return  credit_balance;}

	public boolean isPlaying() { return (numUnits + numBenchedUnits > 0); }
	
	public int getPlayerID() { return playerID; }
	
	public int getNumUnits() { return numUnits; }
	
	public int getNumBenchedUnits() { return numBenchedUnits; }
	
	public int getNumTerritories() { return numTerritories; }

	public int getDie_value(){ return die_value;}
}
