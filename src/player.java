public class player{
	//firrrssstt cooooommmiitt
	private int NumOfInfantry;
	private int	NumOfTerritories;
	private int NumOfBenchedInfantry;
	private int playerID;

	public player() {
		NumOfInfantry = 0;
		NumOfBenchedInfantry = 0;
		NumOfTerritories = 0;
	}

	public player(int ID, int numPlayers) {
		playerID = ID;
		NumOfInfantry = 40-((numPlayers-2)*5);
		NumOfBenchedInfantry = NumOfInfantry;
		NumOfTerritories = 0;
	}


	public void setInfantry(int x) {
		this.NumOfInfantry += x;
	}
	public int getInfantry() {
		return NumOfInfantry;
	}
	public void setBenchedInfantry(int x) {
		this.NumOfInfantry += x;
	}
	public int getBenchedInfantry() {
		return NumOfBenchedInfantry;
	}
	public void setTerritories(int x) {
		this.NumOfTerritories += x;
	}
	public int getTerritories() {
		return NumOfTerritories;
	}
	public void setID(int x) {
		this.playerID= x;
	}
	public int getID() {
		return playerID;
	}
}