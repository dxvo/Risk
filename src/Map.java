public class Map
{
	private Territory [][] data;
	private int width;
	private int height;
	
	public Map()
	{
		width = 0;
		height = 0;
		data = new Territory[width][height];
		initMap();
	}
	
	public Map(int w, int h) //overloaded - constructor
	{
		width = w; //width is default to 6
		height = h; //height is default to 7 to avoid scaling complexity
		System.out.println("\nINITIALIZING MAP AND DISTRIBUTING ARMY UNITS...");
		data = new Territory[w][h];
		initMap();
	}

	// Initialization of Data and fill with
	public void initMap()
	{
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++)
				data[x][y] = new Territory();
	}

	public boolean isValidCoordinates(int x, int y)
	{
		return (x >= 0 || y >= 0 || x < width || y < height);
	}

	public int numTerritories()
	{
		return width * height;
	}
	
	public int getNumUnits(int x, int y)
	{
		if(isValidCoordinates(x, y) == false)
			return 0;
		return data[x][y].getNumUnits();
	}

	
	public int numOwnedTerritories(int id)
	{
		int count = 0;
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++)
				if(data[x][y].getOwnerID() == id)
					count++;
		return count;
	}

	public int getOwnerID(int x, int y)
	{
		if(isValidCoordinates(x,y))
			return data[x][y].getOwnerID();

		return 0;
	}

	public void setId(int x, int y, int id)
	{
		if(isValidCoordinates(x, y))
			data[x][y].setOwnerID(id);
	}

	// Mutators for Data
	public void setTerritory(int x, int y, int id, int units)
	{
		if(isValidCoordinates(x, y))
		{
			data[x][y].setOwnerID(id);
			data[x][y].setNumUnits(units);
		}
	}


	public void setNumUnits(int x, int y, int units)
	{
		if(isValidCoordinates(x, y) == false)
			return;
		data[x][y].setNumUnits(units);
	}

	public boolean hasEnemyNeighbor(int x, int y)
	{
		if(isValidCoordinates(x, y) == false)
			return false;

		if(isValidCoordinates(x, y-1))
			if(data[x][y-1].getOwnerID() != data[x][y].getOwnerID())
				return true;
		if(isValidCoordinates(x-1, y))
			if(data[x-1][y].getOwnerID() != data[x][y].getOwnerID())
				return true;
		if(isValidCoordinates(x+1, y))
			if(data[x+1][y].getOwnerID() != data[x][y].getOwnerID())
				return true;
		if(isValidCoordinates(x, y+1))
			if(data[x][y+1].getOwnerID() != data[x][y].getOwnerID())
				return true;

		return false;
	}

	public boolean areEnemyNeighbors(int x1, int y1, int x2, int y2)
	{
		if(isValidCoordinates(x1, y1) == false)
			return false;
		if(isValidCoordinates(x2, y2) == false)
			return false;

		if((x2 - x1) + (y2 - y1) == 1)
			if(data[x1][y1].getOwnerID() != data[x1][y1].getOwnerID())
				return true;

		return false;
	}


	public boolean canAttack(int x, int y)
	{
		if(isValidCoordinates(x, y) == false)
			return false;

		return data[x][y].getNumUnits() > 1;
	}

}
