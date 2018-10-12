import java.lang.Math;

public class Map
{
	private Territory[][] data;
	private int row;
	private int col;

	/*
	public Map()
	{
		row = 0;
		col = 0;
		data = new Territory[row][col];
		initMap();
	}
	*/ // let just not define this

	public Map(int h, int w) //overloaded - constructor
	{
		row = h; //width is default to 6
		col = w; //height is default to 7 to avoid scaling complexity
		System.out.println("\nINITIALIZING MAP AND DISTRIBUTING ARMY UNITS...");
		data = new Territory[row][col];
		initMap();
	}

	public void setRow(int r) { row = r; }
	public void setCol (int c){col = c;}
	public int getRow(){return row;}
	public int getCol(){ return col;}

	// Initialization of Data and fill with
	public void initMap()
	{
		for(int x = 0; x < row; x++)
		{
			for(int y = 0; y < col; y++)
				data[x][y] = new Territory();
		}

	}

	public boolean isValidCoordinates(int x, int y)
	{
		return (x >= 0 || y >= 0 || x < row || y < col);
	}

	public int numTerritories()
	{
		return row * col;
	}
	
	public int getNumUnits(int x, int y)
	{
		if(isValidCoordinates(x, y) == false)
			return 0;
		return data[x][y].getNumUnits();
	}

	public void setNumUnits(int x, int y, int units)
	{
		if(isValidCoordinates(x, y) == false)
			return;
		data[x][y].setNumUnits(units);
	}
	
	public int numOwnedTerritories(int id)
	{
		int count = 0;
		for(int x = 0; x < row; x++)
			for(int y = 0; y < col; y++)
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


	public boolean canAttack(int x, int y)
	{
		if(isValidCoordinates(x, y) == false)
			return false;
		return data[x][y].getNumUnits() > 1;
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

		if(Math.abs(x2 - x1) + Math.abs(y2 - y1) == 1)
			if(data[x1][y1].getOwnerID() != data[x2][y2].getOwnerID())
				return true;

		return false;
	}




	public Territory[][] getData() {
		return data;
	}
}
