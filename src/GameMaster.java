public class GameMaster
{
	private Map gameMap;
	private Player playerList[];
	private BattleHandler battleHandler;
	private int maxPlayers;
	private int numPlayers;
	private int playerTurn;
	
	public GameMaster()
	{
		gameMap = new Map();
		battleHandler = new BattleHandler();
	}
	
	public void gameStart()
	{
		gameSetup();
		gameLoop();
		gameCleanup();
	}
	
	private Player registerPlayer(int id)
	{
		Player player = new Player(id);
		return player;
	}
	
	private void playerTurn(Player player)
	{
		// Reward new benched Units
		
		// Place newly benched Units on territories
		
		// Choose an enemy neighbor to attack & Validate that action by checking the map
		
		// Engage Battle
		
		// Handle Results
	}
	
	private void playerSetup()
	{
		// Prompt Number of Players
		
		// Initialize Players
		
	}
	
	private void mapSetup()
	{
		// Prompt Dimensions
		
		// Init Map
		
	}
	
	private void playerOrderSetup()
	{
		// Roll Dice
		
		// Calculate Starting Player - Store in "playerTurn"
		
	}
	
	private void gameSetup()
	{
		// Player Setup
		playerSetup();
		
		// Map Setup
		mapSetup();
		
		// Decide Player Order
		playerOrderSetup();
	}
	
	private void gameLoop()
	{
		// Begin loop, starting with the initial "playerTurn" value
		
		// Inside Loop call playerTurn()
	}
	
	private void gameCleanup()
	{
		
	}
}
