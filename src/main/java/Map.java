import java.lang.Math;

/***
 * This class is GameMap. Its used to store players' territories and number of units
 * private data member is width and height (a 2D map)
 *
 */
public class Map
{
	private Territory[][] data;
	private int row;
	private int col;

	/***
	 * overloaded constructor. Initialized GameMap with user input height and width
	 * @param h - gameMap height parameter
	 * @param w - gameMap width parameter
	 */
	public Map(int h, int w) //overloaded - constructor
	{
		setRow(h);
		setCol(w);
		System.out.println("\nINITIALIZING MAP AND DISTRIBUTING ARMY UNITS...");
		data = new Territory[row][col];
		initMap();
	}

	/***
	 * Retrieve Territory at specified coordinate
	 * @param i - number of row
	 * @param j - number of column
	 */
	public Territory getData(int i, int j)
	{
		return data[i][j];
	}

	/***
	 * Set Territory at specified coordinate
	 * @param i - number of row
	 * @param j - number of column
	 */
	public void setData(int i, int j, Territory datapoint)
	{
		data[i][j] = datapoint;
	}
	/***
	 * GameMap height setter
	 * @param r - number of row
	 */
	public void setRow(int r) { row = r; }

	/***
	 * GameMap Width setter
	 * @param c - the number of columns given by user
	 */
	public void setCol (int c){col = c;}

	/***
	 * GameMap height getter method
	 * @return the number of rows (or height) of gameMAp
	 */
	public int getRow(){return row;}

	/***
	 * GameMap width getter method
	 * @return returns number of columns
	 */
	public int getCol(){ return col;}


	/***
	 * Initilized Map. Each Map cells is a data structure
	 */
	public void initMap()
	{
		for(int x = 0; x < row; x++)
		{
			for(int y = 0; y < col; y++)
				data[x][y] = new Territory();
		}

	}

	/***
	 * Check wether the current coordinate belongs to the Map or OutofBound
	 * @param x - map horizontal coordinate
	 * @param y - map vertical coordinate
	 * @return - true if the point(x,y) is not out of bound
	 */
	public boolean isValidCoordinates(int x, int y)
	{
		if (x >= 0 && y >= 0 && x < row && y < col)
			return true;
		else
		{
			System.out.println("\nCoordinates Are Not Valid...");
			return false;
		}

	}


	/***
	 * Getter to see how many units this location currently holds
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return - the number of units of the territory at this location
	 */
	public int getNumUnits(int x, int y)
	{
		if(isValidCoordinates(x, y) == false)
		{
			return 0;
		}
		return data[x][y].getNumUnits();
	}

	/***
	 * Setter method - set a specific number of unit to current location
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param units - the unit value that player want to set ( or randomly initialized from the beginning)
	 */
	public void setNumUnits(int x, int y, int units)
	{
		if(!isValidCoordinates(x, y))
		{
			return;
		}
		data[x][y].setNumUnits(units);
	}

	/***
	 * Return how many territories
	 * @param id - the player ID
	 * @return - The number of territories owned
	 */
	public int numOwnedTerritories(int id)
	{
		int count = 0;
		for(int x = 0; x < row; x++)
			for(int y = 0; y < col; y++)
				if(data[x][y].getOwnerID() == id)
					count++;
		return count;
	}

	/***
	 * Get Player ID at given location
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return  - The ID of the player who owns this location
	 */
	public int getOwnerID(int x, int y)
	{
		if(isValidCoordinates(x,y))
			return data[x][y].getOwnerID();
		return 0;
	}

	/***
	 * Set the owner ID for this location
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param id - The ID that will be set at this location
	 */
	public void setId(int x, int y, int id)
	{
		if(isValidCoordinates(x, y))
			data[x][y].setOwnerID(id);
	}


	/***
	 * Using player ID to set up the number of unit
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param id - the player ID
	 * @param units - The number of units
	 */
	public void setTerritory(int x, int y, int id, int units)
	{
		if(isValidCoordinates(x, y))
		{
			data[x][y].setOwnerID(id);
			data[x][y].setNumUnits(units);
		}
	}

	/***
	 * Check to see if this location is adjacent to attacker
	 * @param x - x coordinate of location that user wants to attack
	 * @param y - y coordinate of location that user wants to attack
	 * @return - true if this territory is adjacent to attacker territory
	 */
	public boolean canAttack(int x, int y)
	{
		if(isValidCoordinates(x, y) == false)
			return false;
		return data[x][y].getNumUnits() > 1;
	}


	/***
	 * Check if this player is neighbor enermy
	 * @param x1 - attacker x location
	 * @param y1 - attacker y location
	 * @param x2 - attacker x location
	 * @param y2 - attacker y location
	 * @return true if they are neighbors
	 */
	public boolean areEnemyNeighbors(int x1, int y1, int x2, int y2)
	{
		if(!isValidCoordinates(x1, y1))
			return false;
		if(!isValidCoordinates(x2, y2))
			return false;
		if(Math.abs(x2 - x1) + Math.abs(y2 - y1) == 1)
			if(data[x1][y1].getOwnerID() != data[x2][y2].getOwnerID())
				return true;
		return false;
	}

}
