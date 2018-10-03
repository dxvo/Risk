import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class GameMaster
{
	private Map gameMap;
	private ArrayList <Player> playerList;

	private BattleHandler battleHandler;

	private int numPlayers;
	private int maxPlayers; // dont think we need this
	private int playerTurn; //

	
	public GameMaster()
	{
		gameMap = new Map();
		battleHandler = new BattleHandler();
		playerList = new ArrayList <Player>(); //initialize the playerlist to empty
		numPlayers = 0;
		maxPlayers = 6;
		playerTurn = 0;
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

		// Decide Player Order
		playerOrderSetup();

		// Map Setup
		mapSetup();
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

		for (int i = 0 ; i < numPlayers; i++)
		{
			Player player = new Player(i); //initialize player ID - start with 0
			int numUnits = 50 - numPlayers * 5;
			player.setNumUnits(numUnits); // set player number of units
			player.setNumBenchedUnits(numUnits); //set bench unit equal to numunits when just started
			playerList.add(player); // append player into list
		}

		System.out.println("Setting up players and units...");
		for (int i=0; i<playerList.size(); i++)
		{
			System.out.printf("PlayerID: %d",playerList.get(i).getPlayerID());
			System.out.printf(" - Number of unit: %d\n",playerList.get(i).getNumUnits()); //playerList.get(i) return object type Player
		}

	}

	private void mapSetup()
	{
		// Prompt Dimensions
		// Init Map
		Scanner reader = new Scanner(System.in);// Reading from System.in
		int width = 0;
		int height = 0;
		System.out.println("\nSETTING UP MAP... ");
		do{
			System.out.print("Enter map width: ");
			width = reader.nextInt();
			if(width <= 0 )
				System.out.println("Can't be negative. Re-enter width: ");
		} while(width <= 0);

		do{
			System.out.print("Enter map height: ");
			height = reader.nextInt();
			if(height <= 0 )
				System.out.println("Can't be negative. Re-enter height: ");
		} while(height <= 0);

		Map map = new Map(width, height); // This will also fill  Territory class and initialize

	}

	private void playerOrderSetup()
	{
		//Properly will re-arrange the arraylist of playpler
		//NOW NEED TO ASSIGN TURN VALUE TO PLAYER
		//based on the number of player and roll dice
		// Roll Dice
		// Calculate Starting Player - Store in "playerTurn"
		Die die = new Die();
		int[] roll_value = new int[2];
		roll_value[0] = 0;
		System.out.println("\nSETTING UP PLAYERS' TURN...");
		for (int i = 0; i < numPlayers; i++)
		{
			System.out.printf("Player %d rolled: ", i);
			playerList.get(i).setDie_value(die.roll()); //set roll die value to the player

			if (i != 0 && playerList.get(i).getDie_value() == roll_value[0]) {
				System.out.printf("Player %d and Player %d have same leading value which is %d\n", i , roll_value[1], roll_value[0]);
				System.out.printf("Player %d rolls again. ", i);
				System.out.print("Value is: ");

				do {
					System.out.print("Both player still have same leading roll\n");
					System.out.printf("Player %d rolls again. ", i);
					System.out.print("Value is: ");
					playerList.get(i).setDie_value(die.roll()); //roll again and keep rolling if both players have same number
				} while (playerList.get(i).getDie_value() == roll_value[0]);

				if (playerList.get(i).getDie_value() > roll_value[0]) {
					roll_value[0] = playerList.get(i).getDie_value();
					roll_value[1] = i;
					System.out.printf("%d is now highest roll value.\n", roll_value[0]);
				}
			}
			if (playerList.get(i).getDie_value() > roll_value[0])//store the roll die value into array
			{
				roll_value[0] = playerList.get(i).getDie_value();// if roll value is greater than current value, then update
				roll_value[1] = i; // this is actually store the playerID
			}
		}

		System.out.printf("Player %d with highest roll value which is %d\n",roll_value[1],roll_value[0]);
		System.out.printf("Player %d goes first\n", roll_value[1]);


		int [] turnID = new int[numPlayers]; //store player turn ID
		turnID[0] = roll_value[1]; //store the Id of person who go first here
		for (int i = 1; i < numPlayers; i++)
		{
			if(turnID[i-1] != numPlayers - 1) //player start at 0
				turnID[i] = turnID[i - 1] + 1;
			else if (turnID[i - 1] == 5)
				turnID[i] = 0;
		}

		System.out.print("The player turn is: ");
		for (int i = 0; i < numPlayers; i++)
			System.out.printf("Player %d, ", turnID[i]);
		System.out.println();


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
