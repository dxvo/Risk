/***
 * This Territory class is use to store player info
 * ownerID - the player who owns this territory
 * numUnits - How many units does this territory currently have
 *
 * @author De, Carlos and Will
 * @version 1.0
 * @since 2019-10
 */
public class Territory
{
	private int ownerID;
	private int numUnits;

	/***
	 * default constructor
	 * ownerID is set to -1 because the index is start at 0 to avoid confusion
	 */
	public Territory()
	{
		ownerID = -1;
		numUnits = 0;
	}

	/***
	 * setter method
	 * @param id - assign the owner ID for this territory
	 */
	public void setOwnerID(int id) { ownerID = id; }

	/***
	 * getter method
	 * @return return the ID of play who currenly owns this territory
	 */
	public int getOwnerID()
	{
		return ownerID;
	}

	/***
	 * setter method
	 * @param units - The unumber of units that needs to be set for this territory
	 */
	public void setNumUnits(int units){ numUnits = units;}

	/***
	 * getter method
	 * @return - The Number of Units that this territory currenly stores
	 */
	public int getNumUnits() { return numUnits;}

}
