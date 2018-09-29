import java.util.Scanner;


public class GameMaster
{
	private Map gameMap;
	private Player playerList[];
	private BattleHandler battleHandler;
	private int maxPlayers; // dont think we need this
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

	private void gameSetup()
	{
		// Player Setup
		playerSetup();

		// Map Setup
		mapSetup();

		// Decide Player Order
		playerOrderSetup();
	}


	private void playerSetup()
	{
		// Prompt Number of Players and
		// Initialize Players
		int num_player = 0;
		do {

			Scanner reader = new Scanner(System.in);  // Reading from System.in
			System.out.print("Enter number of player between 1 and 6: ");
			num_player = reader.nextInt(); // Scans the next token of the input as an int //once finishe
			if(num_player <= 0)
				System.out.println("Number of player cant be smaller than 0");
			if (num_player > 6)
				System.out.println("Number of player cant be greater than 6");

		} while (num_player <= 0 || num_player > 6);



	}

	private void gameLoop()
	{
		// Begin loop, starting with the initial "playerTurn" value

		// Inside Loop call playerTurn()
	}


	private void gameCleanup()
	{

	}

	private Player registerPlayer(int id) // where do we call this method
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

}
