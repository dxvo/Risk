import java.util.Scanner;
public class GameMaster
{
	private Map gameMap;
	private player playerList[];
	private BattleHandler battleHandler;
	private int maxPlayers = 6;
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

	private player registerPlayer(int id, int numPlayers)
	{
		player player = new player(id, numPlayers );
		return player;
	}
	private void setUpMapinfantry()
	{
		//turn player places infantry, next turn player places infantry, repeat until no infantry
	}
	private void playerTurn(player player)
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
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number of Players (2-6): ");
		int n = sc.nextInt();
		sc.close();

		// Initialize Players
		playerList = new player[n];
		for (int i = 0; i < n; i++)
		{
			playerList[i] = registerPlayer(i, n);  ////////////////////////////////////////////Set up an array of players
		}

	}

	private void mapSetup()
	{
		// Prompt Dimensions

		// Init Map

		//place all inital infantry
		setUpMapinfantry();

	}

	private void playerOrderSetup()
	{
		//Create Dice
		Dice dice1 = new Dice();
		int maxRoll = 0;
		int roll = 0;
		int IDofMaxRoll = 0;

		// Roll Dice
		for (int i = 0; i < numPlayers; i++)
		{
			roll = dice1.roll();
			if (roll > maxRoll)
			{
				maxRoll = roll;
				IDofMaxRoll = playerList[i].getID();
			}
		}
		// Calculate Starting Player - Store in "playerTurn"
		playerTurn(playerList[IDofMaxRoll]);
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