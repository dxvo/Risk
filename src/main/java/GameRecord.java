public class GameRecord
{
	private TurnDelta turnDeltas[];
	private int turnCapacity;
	private int numTurns;
	private int currentTurn;
	
	public GameRecord(int recLen)
	{
		setTurnCapacity(recLen);
	}
	
	public void setTurnCapacity(int capacity)
	{
		if(capacity <= 0) capacity = 1;
		turnCapacity = capacity;
		
		turnDeltas = new TurnDelta[turnCapacity];
		
		currentTurn = 0;
		numTurns = 0;
	}
	
	public void recordTurn(int aID, int aX, int aY, boolean aV, int dID, int dX, int dY)
	{
		if(currentTurn >= turnCapacity) return;
		
		turnDeltas[currentTurn].setDelta(aID, aX, aY, aV, dID, dX, dY);
		
		currentTurn++;
		numTurns = currentTurn;
	}
	
	public TurnDelta getTurnDelta(int turn)
	{
		if(turn <= 0)	turn = 0;
		else if(turn >= currentTurn)	turn = currentTurn - 1;
		else if(turn >= turnCapacity)	turn = turnCapacity - 1;
		
		return turnDeltas[turn];
	}	
}
