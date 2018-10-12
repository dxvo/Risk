import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class GameMaster
{
	private Map gameMap;
	private ArrayList <Player> playerList;

	private BattleHandler battleHandler;
	Scanner reader;

	private Die die;
	private int numPlayers;
	private int maxPlayers; //did not really use this
	private int playerTurn; // i did not use this
	private int numUnits;
	private int row; //for map
	private int col; //for map
	private int credit_purchase; //for purchase function

	
	public GameMaster()
	{
		//gameMap = new Map();
		battleHandler = new BattleHandler();
		playerList = new ArrayList <Player>(); //initialize the playerlist to empty
		numPlayers = 0;
		maxPlayers = 6;
		playerTurn = 0;
		row = 0;
		col = 0;
		reader = new Scanner(System.in);
		die = new Die();
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


	private Player registerPlayer(int id) // where do we call this method
	{
		Player player = new Player(id);
		return player;
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
			System.out.printf(" - Number of unit: %d\n",playerList.get(i).getNumUnits());
		}

	}

	private void mapSetup()
	{
		// Prompt Dimensions
		// Init Map

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

		if(row*col != 42)
		{
			do{
				System.out.println("Area must be 42. Re-enter map dimension. ");

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
			} while (row*col != 42);
		}

		//col = 6; //over-write!
		//row = 7; //over-write for simplicity
		gameMap = new Map(row, col); //each map pixel is a territoty by calling getData(int x, int y)
		int numToFill = 42/numPlayers + 1; //number of turn it take to fill 42 territories

		for (int cycle = 0; cycle < numToFill; cycle++)
		{
			for (int cell_col = 0; cell_col < col; cell_col++) // # row number
			{
				for (int id = 0; id < numPlayers; id++)
				{
					//Random rand = new Random();
					//rand.setSeed(rand.nextInt(100));
					//int cell_row = rand.nextInt(6);//col is 6 so 0 to 5
					int cell_row = (int)(Math.random()*row);
					int cellownerID = gameMap.getOwnerID(cell_row,cell_col); //get owner ID at the cell

					if(cellownerID != -1 && cellownerID != id)
					{
						do{
							//Random rand2 = new Random();
							//rand2.setSeed(rand.nextInt(10));
							//cell_row = rand2.nextInt(6);
							cell_row = (int)(Math.random() * row); //7 is exclusive
							cellownerID = gameMap.getOwnerID(cell_row,cell_col);
						}while(cellownerID != -1 && cellownerID != id);
					}
					gameMap.setId(cell_row,cell_col,id); //set player Id into that cell
				}
			}
		}



		//Count number of territories for each player
		//print out the territories owned
		for(int i = 0; i < numPlayers; i++)
		{
			int territories_own = gameMap.numOwnedTerritories(playerList.get(i).getPlayerID());//count how many territories own
			playerList.get(i).setNumTerritories(territories_own);//this is # of territories to player
			System.out.printf("Player %d, number of territories_own %d; \n"
					,playerList.get(i).getPlayerID(), playerList.get(i).getNumTerritories());
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
		System.out.println("\n STARTING MAP");
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
		int[] roll_value = new int[2];
		roll_value[0] = 0;
		int die_value = 0;
		System.out.println("\nSETTING UP PLAYERS' TURN...\n");
		for (int i = 0; i < numPlayers; i++)
		{

			die_value = die.roll();
			System.out.printf("Player %d rolled %d \n", i,die_value);
			playerList.get(i).setDie_value(die_value); //set roll die value to the player

			if(die_value > roll_value[0])
			{
				roll_value[0] = die_value;
				roll_value[1] = i;
			}

			else if (i != 0 && playerList.get(i).getDie_value() == roll_value[0])
			{

				do {
					System.out.printf("\nPlayer %d and %d have same high rolled value which is %d\n",
							i , roll_value[1], roll_value[0]); //roll_value[1] is used to store the Id of player with
					System.out.printf("Player %d rolls again. ", i);
					int roll_again_value = die.roll();
					System.out.printf("Value is: %d\n",roll_again_value);
					playerList.get(i).setDie_value(roll_again_value);

					if (playerList.get(i).getDie_value() > roll_value[0]) {
						roll_value[0] = playerList.get(i).getDie_value();
						roll_value[1] = i;
						System.out.printf("%d is now highest roll value.\n", roll_value[0]);
					}
				} while (playerList.get(i).getDie_value() == roll_value[0]);

			}
		}

		System.out.printf("\nPlayer %d with rolled value %d goes first\n", roll_value[1],roll_value[0]);


		//This is to organize the playerlistID
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
		while(playerList.size() > 1) //while theres is 2 players or more in the playerlist
		{
			for(int i = 0; i < playerList.size(); i++) //break out for loop when a player is eleminated from game
			{
				System.out.printf("\nPLAYER %d TURN\n",playerList.get(i).getPlayerID());
				playerTurn(playerList.get(i));
				continue;
			}
		}

	}


	private void playerTurn(Player player)
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Choose your option to proceed: ");
		System.out.println("\t1. Attack: ");
		System.out.println("\t2. Purchase credit:  ");
		System.out.println("\t3. Trade in cards:  ");
		System.out.println("\t4. Transfer credit:  ");
		System.out.println("\t5. Buy undo:  ");
		System.out.println("\t6. End this turn:  "); //turn over to next player
		System.out.println("\t7. Quit game:  "); //need to remove this player from the playerlist

		System.out.print("YOUR CHOICE IS: ");
		int choice = reader.nextInt();

		if(choice == 1) //attack - call battlehandler
		{
			battleHandler.startBattle(player,gameMap);
		}

		if(choice == 2)
		{
			System.out.println("OK! Purchase game credits.");
			System.out.println("How many credits? $5/credit - Max credit balance is 100. ");
			int game_balance_before_purchase = player.getCredit_balance();
			System.out.printf("Your current credit balance is: %d\n", game_balance_before_purchase);
			System.out.print("Please enter amount to buy: ");
			credit_purchase = reader.nextInt();

			if(game_balance_before_purchase == 100)
			{
				System.out.println("Your balance is at maximum limit 100");
				System.out.println("Not eligibile to purchase more credit");
				playerTurn(player);
			}
			else
			{
				player.setCredit_balance(credit_purchase + game_balance_before_purchase ); //set the balance
				int new_game_balance = player.getCredit_balance();

				//This makes sure the game balance is not over 100
				while(new_game_balance > 100)
				{
					System.out.println("Game credit exceeds allowable limit.");
					System.out.print("Please enter a new amount: ");
					credit_purchase = reader.nextInt();
					player.setCredit_balance(credit_purchase + game_balance_before_purchase ); //set the balance
					new_game_balance = player.getCredit_balance();
				}

				System.out.printf("Your current balance is: %d\n", new_game_balance); //show new balance
				playerTurn(player); //recursive call
			}

		}

		if(choice == 3)
		{
			System.exit(0);

		}
		if(choice == 4)
		{
			//System.exit(0);
			System.out.println("Do nothing");

		}

		if(choice == 5){
			// if a particular choose to exit, then call this method and also remove that player from the game
			System.exit(0);
		}

		if(choice == 6){

			System.exit(0);
		}
		if(choice ==7)
		{

			System.exit(0);
		}

	}

	private void gameCleanup()
	{

	}
}
