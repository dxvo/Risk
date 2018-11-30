import twitter4j.TwitterException;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;


/***
 * This class is to used to set up the game initally
 * It include gameMap which store player territories and number of unit
 * playerList is to store the list of player in the game
 * numPlayers is the number of players in the game
 * NumUnits : depends on the number of players, the number of units initially distributed may vary
 * row and col is gameMap width and height
 * credit_purchase: show the player current balance which can be used to buy cards and undo
 *
 * @author DeVo, Carlos and Will
 * @version 1.1
 * @since 2018-10
 */
public class GameMaster
{
	private Map gameMap;
	private ArrayList <Player> playerList;
	private timer_input timer_input;
	private BattleHandler battleHandler;
	private Scanner reader;
	private Die die;
	private int numPlayers;
	private int numUnits;
	private int row; //for map
	private int col; //for map
	private int credit_purchase; //for purchase function
	private int turnCounter = 1;
	private int choice;

	public GameMaster()
	{
		//gameMap = new Map();
		battleHandler = new BattleHandler();
		playerList = new ArrayList <Player>(); //initialize the playerlist to empty
		numPlayers = 0;
		row = 0;
		col = 0;
		reader = new Scanner(System.in);
		die = new Die();
		timer_input = new timer_input();
		choice = 0;
	}

	/***
	 * This overloaded constructor which is used to start the game when using chatBot
	 * @param player - this is the number of player and it is set to be 3
	 */
	public GameMaster(int player)
	{
		//gameMap = new Map();
		battleHandler = new BattleHandler();
		playerList = new ArrayList <Player>(); //initialize the playerlist to empty
		numPlayers = player;
		row = 0;
		col = 0;
		reader = new Scanner(System.in);
		die = new Die();
		timer_input = new timer_input();
		choice = 0;
	}

	/***
	 * This method starts the game
	 * The sequence is setting up the game, the game loop and game Cleanup
	 */
	protected void gameStart()
	{
		gameSetup();

		gameLoop();

		gameCleanup();
	}

	/***
	 * This method is to setup player: territories, number of units, player turns
	 * It also set up the info for gamemap which holds the player territories and number of units in each territory
	 */
	protected void gameSetup()
	{
		playerSetup();

		playerOrderSetup();

		mapSetup();
	}

	/***
	 * Setting up player
	 * Get user input for how many players. Number of players need to be greater than 1 and less than 6
	 * Distribute the beginning number of units based on how many players
	 * Player is then store into the player arraylist
	 */
	protected void playerSetup()
	{
		/*
		if(numPlayers == 0 )
		{
			do {
				Scanner reader = new Scanner(System.in);  // Reading from System.in
				System.out.print("Enter number of player between 1 and 6: ");
				numPlayers = reader.nextInt(); //numPlayers is an attribution of the GameMaster class
				if(numPlayers <= 1)

					System.out.println("Number of player cant be smaller than 1");
				if (numPlayers > 6)

					System.out.println("Number of player cant be greater than 6");

			} while (numPlayers <= 1 || numPlayers > 6);
		}
		*/

		for (int i = 0 ; i < numPlayers; i++)
		{
			Player player = new Player(i); //initialize player ID - start with 0
			numUnits = 50 - numPlayers * 5;
			player.setNumUnits(numUnits); // set player number of units
			player.setNumBenchedUnits(numUnits); //set bench unit equal to numunits when just started
			playerList.add(player); // append player into list
		}

		/*
		System.out.println("SETTING UP PLAYERS AND UNITS...");
		for (int i=0; i<playerList.size(); i++)
		{
			System.out.printf("PlayerID: %d",playerList.get(i).getPlayerID());
			System.out.printf(" - Number of unit: %d\n",playerList.get(i).getNumUnits());
		}
		*/
	}

	/***
	 * Get user input on game map width and height. The game map area is default to 42.
	 * User is prompt to re-enter if inputs mutiplication is different than 42
	 * Randomly distribute player territories and how many units each territory hold
	 */
	protected void mapSetup()
	{
		/*
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
		*/

		gameMap = new Map(row, col);
		int numToFill = 42/numPlayers + 1; //number of turn it take to fill 42 territories

		for (int cycle = 0; cycle < numToFill; cycle++)
		{
			for (int cell_col = 0; cell_col < col; cell_col++) // # row number
			{
				for (int id = 0; id < numPlayers; id++)
				{
					int cell_row = (int)(Math.random()*row);
					int cellownerID = gameMap.getOwnerID(cell_row,cell_col); //get owner ID at the cell

					if(cellownerID != -1 && cellownerID != id)
					{
						do{
							cell_row = (int)(Math.random() * row); //7 is exclusive
							cellownerID = gameMap.getOwnerID(cell_row,cell_col);
						}while(cellownerID != -1 && cellownerID != id);
					}
					gameMap.setId(cell_row,cell_col,id); //set player Id into that cell
				}
			}
		}

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
		for (int i = 0; i < col; i++)
			System.out.printf("\t\t\t\t%d   ",i);

		System.out.println();
		for(int i = 0; i < row; i++)
		{
			System.out.print(i);
			for (int j = 0; j < col; j++)
			{
				System.out.printf("\t ID: %d, Units: %d",gameMap.getOwnerID(i,j), gameMap.getNumUnits(i,j));
			}
			System.out.println();
		}

	}

	/***
	 * This method determine player turn by rolling die
	 * Player with highest roll value goes first
	 * Then next player turn is in clockwise direction
	 */
	protected void playerOrderSetup()
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
				} while (playerList.get(i).getDie_value() == roll_value[0]); //exit when values are different

				if (playerList.get(i).getDie_value() > roll_value[0]) {
					roll_value[0] = playerList.get(i).getDie_value();
					roll_value[1] = i;
					System.out.printf("%d is now highest roll value.\n", roll_value[0]);
				}

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

	/***
	 * While the list of player is greater than 1. The game keeps going until the condition is false
	 * The game winner is tweeted using tweet API
	 */
	protected void gameLoop()
	{
		while(playerList.size() > 1) //while theres is 2 players or more in the playerlist
		{
			for(int i = 0; i < playerList.size(); i++)
			{
				if(playerList.size() <= 1)
				{
					System.out.printf("Player %d wins",playerList.get(0).getPlayerID()); //only player left in playerlist
					System.exit(1);
				}
				else {
					playerTurn(playerList.get(i));
					turnCounter++;
					continue;
				}
			}
		}
		PrintStream consolePrint= System.out;
		Tweeter tweet = new Tweeter(consolePrint);
		String message = "";
		String player_ID = "";
		String player_numTerritories = "";

		for (int i=0; i<playerList.size(); i++)
		{
			player_ID = String.valueOf(playerList.get(i).getPlayerID());
			player_numTerritories = String.valueOf(playerList.get(i).getNumTerritories());
			message += "The Player with ID = " + player_ID + " Has " + player_numTerritories + " Territories.\n";
		}

		try
		{
			tweet.tweetOut(message);
		}
		catch(TwitterException name)
		{
			System.out.println("Error Tweeting on Twitter");

		}

	}

	/***
	 * This method show the available option during each player turn
	 * option 1: is to attack adjacent territory
	 * option2 : buy credit to get more card or buy under
	 * option3 : transfer credit to other players
	 * option4: trade in cards
	 * option5: undo the previous turn
	 * option6: pass the turn to next Player.
	 * If the player does not enter the choice within 10 seconds, the current player loses his/her turn to next player
	 * option7: Quit game and be removed from the playerList
	 * @param player - the current player turn
	 */
	protected void playerTurn(Player player)
	{
		System.out.printf("\nPLAYER %d TURN\n",player.getPlayerID());
		//Scanner reader = new Scanner(System.in);
		System.out.println("Choose your option to proceed: ");
		System.out.println("\t1. Attack. ");
		System.out.println("\t2. Purchase credit.  ");
		System.out.println("\t3. Transfer credit to other player.");
		System.out.println("\t4. Undo. ");
		System.out.println("\t5. End this turn.");
		System.out.println("\t6. Quit game. ");

		choice = timer_input.get_input();

		if(choice == 1) //attack - call battlehandler
		{
			battleHandler.startBattle(player,gameMap, playerList, turnCounter);
		}

		if(choice == 2)
		{
			System.out.println("OK! Purchase game credits.");
			System.out.println("How many credits? $5/credit ");
			int game_balance_before_purchase = player.getCredit_balance();
			System.out.printf("Your current credit balance is: %d\n", game_balance_before_purchase);
			System.out.print("Please enter amount to buy: ");
			credit_purchase = reader.nextInt();
			player.setCredit_balance(credit_purchase + game_balance_before_purchase ); //set the balance
			int new_game_balance = player.getCredit_balance();

			System.out.printf("Your current balance is: %d\n", new_game_balance); //show new balance
			playerTurn(player); //recursive call

		}

		if(choice == 3)
		{
			int received_player_Id;
			boolean valid_transfer = false;
			int transfer_balance = 0;
			int available_balance_before_transfer = 0;
			int received_player_position = -1;

			available_balance_before_transfer = player.getCredit_balance();
			System.out.printf("Your current balance is: %d\n", available_balance_before_transfer);

			if(available_balance_before_transfer <= 0)
			{
				System.out.println("You have 0 balance. Please purchase credits to transfer\n");
				playerTurn(player);
			}
			else
			{
				do {
					System.out.print("Enter player ID that you want to transfer to: ");
					received_player_Id = reader.nextInt();

					//iterate through playerlist. check to see if player still in the game
					for (int i = 0; i < playerList.size(); i++)
					{
						if (received_player_Id == playerList.get(i).getPlayerID() && received_player_Id != player.getPlayerID()) {
							valid_transfer = true;
							received_player_position = i;
							break;
						}
					}
					if(!valid_transfer)
						System.out.println("In-valid player ID.");

				}while (!valid_transfer);
			}

			if(valid_transfer)
			{
				System.out.print("How much credits do you want to transfer\n");
				transfer_balance = reader.nextInt();
				while(transfer_balance > available_balance_before_transfer)
				{
					System.out.println("Your transfer amount is more than your current balance. ");

					System.out.print("Enter transfer amount again: \n");
					transfer_balance = reader.nextInt();
				}
				player.TransferCredits(transfer_balance, playerList.get(received_player_position));
			}
			playerTurn(player);
		}

		if(choice == 4){
			// if a particular choose to exit, then call this method and also remove that player from the game
			System.exit(0);
		}

		//please keep choice = 6 to move to next turn
		if(choice == 5){
			next_turn(player);
		}
		if(choice ==6)
		{
			System.out.println("Thanks for playing! BYE ");
			System.exit(0);
		}

	}

	/***
	 * Transition to next player in the list
	 * This can occur when a player ceases to attack or simply wants to finish turn early
	 * @param player - the player who turn is next
	 */
	protected void next_turn(Player player)
	{
		int pos = playerList.indexOf(player); //find position of the current player in arraylist
		System.out.print("Moving to next player\n");
		if(pos == playerList.size() - 1)
			pos = -1;
		playerTurn(playerList.get(pos+1));
	}

	/***
	 * Part of game cycle
	 */
	protected void gameCleanup()
	{
		System.out.println("GAME OVER");
		return;
	}
}
