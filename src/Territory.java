public class Territory
{
	private int ownerID;
	private int numUnits;
	
	public Territory()
	{
		ownerID = -1;
		numUnits = 0;
	}
	
	public void setOwnerID(int id) { ownerID = id; }

	public int getOwnerID()
	{
		return ownerID;
	}

	public void setNumUnits(int units){ numUnits = units;}

	public void increase_Unit(){numUnits = numUnits +1;} //distributed at beginning

	public int getNumUnits() { return numUnits;}

}
