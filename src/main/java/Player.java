/***
 * Player class which implement the observer class
 * playerID - store player uniqueID
 * numBenchedUnits - the number of units beginning of game
 * numTerritories - how many territories own
 * credit_balance - credit balance - Max is 100
 * defender_id is used for observer design pattern via update method
 * @author: De, Carlos and Will
 * @version: 1.2
 * @since 2018-10
 *
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


	/***
	 * Overloaded constructor with ID
	 * @param id - Player ID
	 * numUnits, numBenchedUnits, numTerritories are all set to 0
	 */
	public Player(int id)
	{
		playerID = id;
		numUnits = 0;
		numBenchedUnits = 0;
		numTerritories = 0;
		die_value = 0;
		defender_id = -1;
	}

	/***
	 * Set the player ID
	 * @param id - The ID value that is set to a particular player
	 */
	public void setPlayerID(int id)
	{
		if(id >= 0)
			playerID = id;
	}

	/***
	 * Used to distribute the units beginning of game
	 * @param units - the number of units to be distributed
	 */
	public void setNumUnits(int units)
	{
		if(units >= 0)
			numUnits = units;
	}

	/***
	 * Setter method  - use to set the number of bench unit players
	 * @param units - the bench units value to be set for player
	 */
	public void setNumBenchedUnits(int units)
	{
		if(units >= 0)
			numBenchedUnits = units;
	}

	/***
	 * Setter method - set the number of territories for a players
	 * @param count - the number of territory
	 */
	public void setNumTerritories(int count)
	{
		if(count >= 0)
			numTerritories = count;
	}

	/***
	 * Set die value to determine the player turn
	 * @param value - Value of Die to be set
	 */
	public void setDie_value(int value)
	{
		if(value >= 0)
			die_value = value;
	}


	/***
	 * setter method : Set the credit balance for current player
	 * @param balance - the amount of credit to be set for player
	 */
	public void setCredit_balance(int balance){credit_balance = balance;}

	/***
	 * getter method : return the current player credit balance
	 * @return the amount of in game credit
	 */
	public int getCredit_balance() {return  credit_balance;}

	/***
	 * Getter method - Return the player turn ID
	 * @return the ID of current player
	 */
	public int getPlayerID() { return playerID; }

	/***
	 * getter method - Return the number of Units a player has
	 * @return - the number of units
	 */
	public int getNumUnits() { return numUnits; }

	/***
	 * A getter method
	 * @return - The number of bench units of a player
	 */
	public int getNumBenchedUnits() { return numBenchedUnits; }

	/***
	 * A getter Method
	 * @return - The number of territories this player owns
	 */
	public int getNumTerritories() { return numTerritories; }

	/***
	 * Getter method
	 * @return - Return the Die Value
	 */
	public int getDie_value(){ return die_value;}

	/***
	 * This method is use particular for the update during battle mechanic
	 * implemented with observer desigb method
	 * @param id
	 */
	public void setDefender_id(int id){defender_id = id;}


	@Override
	/***
	 * this method needs to be implemented from parent - the Observer interface
	 * This is used to update the batte state change from BattleHandler
	 */
	public void update(Object id) {
		this.setDefender_id((int)id);
		System.out.printf("\nPlayer %d. Your territory is under attack\n", id);
	}
}
