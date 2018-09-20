public class Player{
	//firrrssstt cooooommmiitt
	private int NumOfInfantry;
	private int	NumOfTerritories;
	private int NumOfBenchedInfantry;
	
	public Player() {
		NumOfInfantry = 0;
		NumOfBenchedInfantry = 0;
		NumOfTerritories = 0;	
	}
	
	public Player(int n) {
		NumOfInfantry = 40-((n-2)*5);
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
}