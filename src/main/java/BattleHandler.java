import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


/***
 *This class is used to handle in game battle mechanics
 *
 * @author De, Carlos and William
 * @version 1.0
 * @since 2018-10-01
 *
 */
public class BattleHandler
{
	private Die die;
	int defender_Id;


	/***
	 * default constructor
	 * defender_Id is ID of defender
	 */
	public BattleHandler()
	{
		die = new Die();
		this.defender_Id = -2;
	}

	/***
	 * this method will initalize the beggining of a battle
	 * @param attacker - is he player who attacks
	 * @param gameMap - game map with info of current army info
	 * @param playerList - the current list of player in gameMaster
	 * @param turnCounter - current player turn
	 *
	 */
	public void startBattle(Player attacker, Map gameMap, ArrayList<Player> playerList, int turnCounter)
	{
		Battle(attacker, gameMap);
		
		endBattle(attacker, gameMap, playerList, turnCounter);
	}

	/***
	 * The method lets attacker choose his/her starting territory location to attack
	 * The user selects territory thats not belonged to attracker, the program will prompt for re-enter until it correct
	 * After confirm territory, program will check to see if the army is sufficient to attack - IF not it will ask to other region
	 * Then program will ask for territory that player want to attack. Program will check for territory adjacency.
	 * Once attacker chooses territory to attack, the program will notify the player who own that territory.
	 * @param attacker the player who attacks
	 * @param gameMap the current game with current game info
	 */
	private void Battle(Player attacker, Map gameMap)
	{
		//print out the current map
		System.out.println("\n CURRENT MAP");

		for (int i = 0; i < gameMap.getRow(); i++) {
			for (int j = 0; j < gameMap.getCol(); j++) {
				System.out.printf("\t ID: %d, Units: %d", gameMap.getOwnerID(i, j), gameMap.getNumUnits(i, j));
			}
			System.out.println();
		}
		System.out.printf("\nThis is player's %d turn\n", attacker.getPlayerID());
		//get the coodinate of the territory

		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Select your territory to begin.");//attacker territory
		System.out.print("Enter x coordinate: ");
		int x = reader.nextInt();
		System.out.print("Enter y coordinate: ");
		int y = reader.nextInt();
		int ownerID = gameMap.getOwnerID(x, y); //get ownerId from this territory
		int attacker_ID = attacker.getPlayerID();

		int counter = 0;
		while (attacker_ID != ownerID) {
			System.out.println("This territory is not yours. Re-enter coordinates: ");
			System.out.print("Re-Enter x: ");
			x = reader.nextInt();
			System.out.print("Re-Enter y: ");
			y = reader.nextInt();
			ownerID = gameMap.getOwnerID(x, y);
			if (counter == 2) // re-print tha current player map
			{
				counter = 0;
				for (int i = 0; i < gameMap.getRow(); i++) {
					for (int j = 0; j < gameMap.getCol(); j++) {
						System.out.printf("\t ID: %d, Units: %d", gameMap.getOwnerID(i, j));
					}
					System.out.println();
				}
			}
		}

		int additional_army = addArmyEachTurn(x, y, attacker, gameMap);//update the army into territory
		boolean canAttack = gameMap.canAttack(x, y);

		//if attracker territory does not have enoguh unit to attack
		//also need to check option where the player only have 1 territoty left and dont have enough army to attack
		//not sure if this is needed bc the units will always be greater than 3
		while (!canAttack) {
			System.out.println("Insufficient Units to attack. ");
			System.out.println("Re-select your territory to begin.");//attacker territory
			System.out.print("Enter x coordinate: ");
			x = reader.nextInt();
			System.out.print("Enter y coordinate: ");
			y = reader.nextInt();
			ownerID = gameMap.getOwnerID(x, y); //get ownerId from this territory
			attacker_ID = attacker.getPlayerID();


			while (attacker_ID != ownerID) {
				System.out.println("This territory is not yours. Re-enter coordinates: ");
				System.out.print("Re-Enter x: ");
				x = reader.nextInt();
				System.out.print("Re-Enter y: ");
				y = reader.nextInt();
				ownerID = gameMap.getOwnerID(x, y);

				if (counter == 2) // re-print tha current player map
				{
					counter = 0;
					for (int i = 0; i < gameMap.getRow(); i++) {
						for (int j = 0; j < gameMap.getCol(); j++) {
							System.out.printf("\t ID: %d, Units: %d", gameMap.getOwnerID(i, j));
						}
						System.out.println();
					}
				}
			}

			canAttack = gameMap.canAttack(x, y);
		}

		//If there are enough units to attack, then
		System.out.println("Choose opponent's territory to attack: ");
		System.out.print("Enter x coordinate: ");
		int def_x_territory = reader.nextInt();
		System.out.print("Enter y coordinate: ");
		int def_y_territory = reader.nextInt();
		boolean areEnemyNeighbors = gameMap.areEnemyNeighbors(x, y, def_x_territory, def_y_territory); //check to see if this is valid

		//if attacker attack a territory
		// which is not neightbor, prompt to reselect unitl it's
		while (!areEnemyNeighbors) {
			System.out.println("Territory is not adjacent. Re-select territory to attack: ");
			System.out.print("Re-Enter x coordinate: ");
			def_x_territory = reader.nextInt();
			System.out.print("Re-Enter y coordinate: ");
			def_y_territory = reader.nextInt();
			areEnemyNeighbors = gameMap.areEnemyNeighbors(x, y, def_x_territory, def_y_territory);
		}

		//Notify defender the territory is under-attack
		this.defender_Id = gameMap.getOwnerID(def_x_territory, def_y_territory);
		if (areEnemyNeighbors) {
			System.out.printf("\nPLAYER %d, YOU ARE ATTACKED BY PLAYER %d\n", this.defender_Id, attacker_ID);
			System.out.println("READY TO BATTLE.\n");
		}


		//Setting up number of time to roll die
		int attacker_unit = gameMap.getNumUnits(x, y);
		int defender_unit = gameMap.getNumUnits(def_x_territory, def_y_territory);
		int attacker_num_die_roll = 0;
		int defender_num_die_roll = 0;
		int[] largest_die = new int[2];
		int units_moved_after_battle = 0;
		boolean keep_attack = true;
		int attack_counter = 1;

		while (attacker_unit >= 2 && defender_unit > 0 && keep_attack) {
			//for attacker
			System.out.printf("\nAttacker - Player %d - Your territory currently has %d units", attacker_ID, attacker_unit);
			System.out.print("\nHow many times do you want to roll ?( Max is 3): ");
			attacker_num_die_roll = reader.nextInt();
			while (attacker_unit - attacker_num_die_roll < 1 || attacker_num_die_roll > 3) //attacker must have at least 1 unit more # of die roll
			{
				System.out.print("Invalid. Re-enter number of time to roll: ");
				attacker_num_die_roll = reader.nextInt();
			}
			//for defender
			System.out.printf("\nDefender - Player %d - Your territory currently has %d units", defender_Id, defender_unit);
			System.out.print("\nHow many times do you want to roll ?( Max is 2):");
			defender_num_die_roll = reader.nextInt();
			while (defender_unit - defender_num_die_roll < 0 || defender_num_die_roll > 2) {
				System.out.print("Invalid. Re-enter number of time to roll: ");
				defender_num_die_roll = reader.nextInt();
			}

			//rolling die
			//1 is store value and other is to store ID
			int roll_value = 0;
			largest_die[0] = 0; //initialize to
			for (int i = 0; i < attacker_num_die_roll; i++) //attacker turn
			{
				roll_value = die.roll(); // 1 to 6
				if (roll_value > largest_die[0]) {
					largest_die[0] = roll_value;
					largest_die[1] = attacker_ID;
				}
			}

			//Defender turn
			for (int i = 0; i < defender_num_die_roll; i++) {
				roll_value = die.roll();
				if (roll_value >= largest_die[0]) //if value is equal, defender wins
				{
					largest_die[0] = roll_value;
					largest_die[1] = defender_Id;
				}
			}


			//after all dice rolled
			if (attacker_ID == largest_die[1]) //this mean attacker won
			{
				System.out.printf("\nRound %d, player %d won. \n", attack_counter, attacker_ID);
				gameMap.setNumUnits(def_x_territory, def_y_territory, defender_unit - 1); //reduce the number of unit by 1 for loser
			}

			if (defender_Id == largest_die[1]) // defender won
			{
				System.out.printf("\nRound %d, player %d won. \n", attack_counter, defender_Id);
				gameMap.setNumUnits(x, y, attacker_unit - 1);
			}

			attack_counter += 1;
			//re-calculate current units
			attacker_unit = gameMap.getNumUnits(x, y);
			defender_unit = gameMap.getNumUnits(def_x_territory, def_y_territory);

			System.out.printf("Attacker - Player %d - Territory now has %d units.\n", attacker_ID,attacker_unit);
			System.out.printf("Defender - Player %d - Territory now has %d units.\n", defender_Id,defender_unit);
			System.out.printf("Player %d, do you want to keep attacking?\n", attacker_ID);
			System.out.print("Enter 1 for yes and 0 to stop: ");
			int attack = reader.nextInt();

			if (attack == 0)
				keep_attack = false;
		}

		if (!keep_attack)
		{
			System.out.printf("Player %d decided to stop attack!\n",attacker_ID);
		}


		System.out.printf("The winner of last battle was: %d\n",largest_die[1]);
		//Attacker can not lose the territory
		if(attacker_unit >= 4)
			units_moved_after_battle = 3;
		else
			units_moved_after_battle = attacker_unit - 1;



		if(attacker_ID == largest_die[1]) //attacker win _
		{
			System.out.printf("The player %d has occupied territory %d, %d from player %d\n",attacker_ID,def_x_territory,def_y_territory,defender_Id);
			gameMap.setTerritory(def_x_territory, def_y_territory, attacker_ID, units_moved_after_battle);

		}


		if(defender_Id == largest_die[1])
		{
			System.out.printf("The player %d has successfully defended his territory",defender_Id);

		}

	}


	/***
	 * This is to give a player additional units for each turns
	 * @param x - x coordinate on gamemap
	 * @param y - current y coordinate on gamemap
	 * @param attacker - the attakcer player
	 * @param gamemap - current gamemap
	 * @return - return the additional number of player that gets added for the current player
	 */
	public int addArmyEachTurn(int x, int y, Player attacker, Map gamemap)
	{
		int att_terri_own = attacker.getNumTerritories(); //number of territory that owned by attacker
		int additional_army = att_terri_own/3;
		if(additional_army <= 3)
			additional_army = 3;

		int current_units = attacker.getNumUnits();
		attacker.setNumUnits(current_units + additional_army); //update total units owned by attacker

		int current_territory_unit = gamemap.getNumUnits(x,y);
		gamemap.setNumUnits(x,y, current_territory_unit + additional_army); //update the total unit in that territory
		return additional_army;
	}

	/***
	 *
	 * This method Tweets out messages the result when battle ends
	 * @param attacker - the attakcer
	 * @param gameMap - current gamemap
	 * @param playerList - the current list of player that still in the game
	 * @param turnCounter -
	 */
	private void endBattle(Player attacker, Map gameMap, ArrayList<Player> playerList, int turnCounter)
	{
		PrintStream consolePrint = System.out;
		Tweeter tweet = new Tweeter(consolePrint);
		String counter = String.valueOf(turnCounter);
		String message = "During Turn " + counter +"\n";
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

}
