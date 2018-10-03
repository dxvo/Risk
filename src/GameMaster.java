import java.util.Scanner;
import java.util.ArrayList;

public class GameMaster
{
	private Map gameMap;
	private BattleHandler battleHandler;
	private ArrayList <Player> playerList;
	private int numPlayers;

	private int maxPlayers; // dont think we need this

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
		do {
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			System.out.print("Enter number of player between 1 and 6: ");
			numPlayers = reader.nextInt(); //numPlayers is an attribution of the GameMaster class
			if(numPlayers <= 0)

				System.out.println("Number of player cant be smaller than 0");
			if (numPlayers > 6)

				System.out.println("Number of player cant be greater than 6");

		} while (numPlayers <= 0 || numPlayers > 6);

		//This is to initialize the playerlist. The PlayerId and numofUnits also initialize
		playerList = new ArrayList <Player>(); //initialize the playerlist - which is an attribute of this class
		for (int i = 0 ; i < numPlayers; i++)
		{
			Player player = new Player(i); //initialize player ID - start with 0
			int numUnits = 50 - numPlayers * 5;
			player.setNumUnits(numUnits); // set player number of units
			playerList.add(player); // append player into list
		}

		for (int i=0; i<playerList.size(); i++) {
			System.out.println(playerList.get(i).getPlayerID());
			System.out.println(playerList.get(i).getNumUnits()); //playerList.get(i) return object type Player
		}

	}

	private void mapSetup()
	{
		// Prompt Dimensions
		// Init Map
		Scanner reader = new Scanner(System.in);// Reading from System.in
		int width = 0;
		int height = 0;
		do{
			System.out.print("Enter width: ");
			width = reader.nextInt();
			if(width <= 0 )
				System.out.println("Can't be negative. Re-enter width: ");
		} while(width <= 0);

		do{
			System.out.print("Enter height: ");
			height = reader.nextInt();
			if(height <= 0 )
				System.out.println("Can't be negative. Re-enter height: ");
		} while(height <= 0);

		Map map = new Map(width, height); // This will also fill call Territory class and initialize

	}

	private void playerOrderSetup()
	{
		// Roll Dice

		// Calculate Starting Player - Store in "playerTurn"

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
}
