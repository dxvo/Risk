import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class GameMaster
{
	private Map gameMap;
	private ArrayList <Player> playerList;

	private BattleHandler battleHandler;

	private int numPlayers;
	private int maxPlayers; //did not really use this
	private int playerTurn; // i did not use this
	private int numUnits;

	
	public GameMaster()
	{
		//gameMap = new Map();
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
			if(numPlayers <= 1)

				System.out.println("Number of player cant be smaller than 1");
			if (numPlayers > 6)

				System.out.println("Number of player cant be greater than 6");

		} while (numPlayers <= 1 || numPlayers > 6);

		for (int i = 0 ; i < numPlayers; i++)
		{
			Player player = new Player(i); //initialize player ID - start with 0
			numUnits = 50 - numPlayers * 5;
			player.setNumUnits(numUnits); // set player number of units
			player.setNumBenchedUnits(numUnits); //set bench unit equal to numunits when just started
			playerList.add(player); // append player into list
		}

		System.out.println("SETTING UP PLAYERS AND UNITS...");
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
		int row = 0;
		int col = 0;

		System.out.println("\nPLAYERS' TERRITORIES MAP ");
		do{
			System.out.print("Enter map width: ");
			col = reader.nextInt();
			if(col <= 0 )
				System.out.println("Must be greater than 0. Re-enter width: ");
		} while(col <= 0);

		do{
			System.out.print("Enter map height: ");
			row = reader.nextInt();
			if(row <= 0 )
				System.out.println("Must be greater than 0. Re-enter height: ");
		} while(row <= 0);

		if(row*col < 42)
		{
			do{
				System.out.println("Area is not big enough. Area value must be least 42).\n Re-enter map dimension. ");

				do{
					System.out.print("Enter map width: ");
					col = reader.nextInt();
					if(col <= 0 )
						System.out.println("Must be greater than 0. Re-enter width: ");
				} while(col <= 0);

				do{
					System.out.print("Enter map height: ");
					row = reader.nextInt();
					if(row <= 0 )
						System.out.println("Must be greater than 0. Re-enter height: ");
				} while(row <= 0);
			} while (row*col < 42);
		}

		col = 6; //over-write!
		row = 7; //over-write for simplicity
		gameMap = new Map(row, col); //each map pixel is a territoty by calling getData(int x, int y)
		int numToFill = 42/numPlayers + 1; //number of turn it take to fill 42 territories

		for (int cycle = 0; cycle < numToFill; cycle++)
		{
			for (int cell_col = 0; cell_col < 6; cell_col++) // # row number
			{
				for (int id = 0; id < numPlayers; id++)
				{
					//Random rand = new Random();
					//rand.setSeed(rand.nextInt(100));
					//int cell_row = rand.nextInt(6);//col is 6 so 0 to 5
					int cell_row = (int)(Math.random()*7);
					int cellownerID = gameMap.getOwnerID(cell_row,cell_col); //get owner ID at the cell

					if(cellownerID != -1 && cellownerID != id)
					{
						do{
							//Random rand2 = new Random();
							//rand2.setSeed(rand.nextInt(10));
							//cell_row = rand2.nextInt(6);
							cell_row = (int)(Math.random() * 7); //7 is exclusive
							cellownerID = gameMap.getOwnerID(cell_row,cell_col);
						}while(cellownerID != -1 && cellownerID != id);
					}
					gameMap.setId(cell_row,cell_col,id); //set player Id into that cell
				}
			}
		}



		//Count number of territories for each player
		for(int i = 0; i < numPlayers; i++)
		{
			int territories_own = gameMap.numOwnedTerritories(playerList.get(i).getPlayerID());
			playerList.get(i).setNumTerritories(territories_own);
			System.out.printf("Player %d, number of territories_own %d: \n",playerList.get(i).getPlayerID(),playerList.get(i).getNumTerritories());
		}

		//setting the units to map
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				int playerID = gameMap.getOwnerID(i,j);
				int player_territories_own	= playerList.get(playerID).getNumTerritories();
				int bench_unit = playerList.get(playerID).getNumBenchedUnits();
				if(bench_unit/player_territories_own < 2) //last turn
					gameMap.setNumUnits(i,j,bench_unit);
				else
					gameMap.setNumUnits(i,j,bench_unit/player_territories_own); //save units to cell

				bench_unit = bench_unit - bench_unit/player_territories_own; //new bench unit
				playerList.get(playerID).setNumBenchedUnits(bench_unit); //update bench unit
			}
		}


		//Randomly distribute player into territories
		System.out.println("\n CURRENT MAP");
		for(int i = 0; i < row; i++)
		{
			for (int j = 0; j < col; j++)
			{
				System.out.printf("\t ID: %d, Units: %d",gameMap.getOwnerID(i,j), gameMap.getNumUnits(i,j));
			}
			System.out.println();
		}

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
				do {
					System.out.printf("\nPlayer %d and Player %d have same leading value which is %d\n", i , roll_value[1], roll_value[0]);
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

		System.out.printf("\nPlayer %d goes first\n", roll_value[1]);


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
		{
			playerList.get(i).setPlayerID(turnID[i]); //The ArrayList now is in-order to use for game loop
			System.out.printf("Player %d, ", playerList.get(i).getPlayerID());
		}

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
