/***
 * Player class
 */
public class Player implements Observer
{

	private int playerID;
	private int numUnits;
	private int numBenchedUnits;
	private int numTerritories;
	private int die_value;
	private int credit_balance;
	private int defender_id;


	
	public Player(int id)
	{
		playerID = id;
		numUnits = 0;
		numBenchedUnits = 0;
		numTerritories = 0;
		die_value = 0;
		defender_id = -1;
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


	public void setCredit_balance(int balance){credit_balance = balance;}

	public int getCredit_balance() {return  credit_balance;}

	public int getPlayerID() { return playerID; }
	
	public int getNumUnits() { return numUnits; }
	
	public int getNumBenchedUnits() { return numBenchedUnits; }
	
	public int getNumTerritories() { return numTerritories; }

	public int getDie_value(){ return die_value;}

	public void setDefender_id(int id){defender_id = id;}


	@Override
	/***
	 * this method needs to be implemented from parent - the Observer class
	 * This is used to update the batte state change from BattleHandler
	 */
	public void update(Object id) {
		this.setDefender_id((int)id);
		System.out.printf("\nPlayer %d. Your territory is under attack\n", id);
	}
}
