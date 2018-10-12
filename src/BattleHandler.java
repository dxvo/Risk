import java.util.Scanner;


public class BattleHandler
{
	public BattleHandler()
	{

	}
	
	public void startBattle(Player attacker, Map gameMap)
	{
		initBattle(attacker, gameMap);
		
		// Battle Code Here
		
		endBattle(attacker, gameMap);
	}
	
	private void initBattle(Player attacker, Map gameMap)
	{
		//print out the current map
		System.out.println("\n CURRENT MAP");

		for(int i = 0; i < gameMap.getRow(); i++)
		{
			for (int j = 0; j < gameMap.getCol(); j++)
			{
				System.out.printf("\t ID: %d, Units: %d", gameMap.getOwnerID(i,j),gameMap.getNumUnits(i,j));
			}
			System.out.println();
		}
		System.out.printf("\nPlayer's %d turn\n", attacker.getPlayerID());
		//get the coodinate of the territory

		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Select your territory to begin.");//attacker territory
		System.out.print("Enter x coordinate: ");
		int x = reader.nextInt();
		System.out.print("Enter y coordinate: ");
		int y = reader.nextInt();
		int ownerID = gameMap.getOwnerID(x,y); //get ownerId from this territory
		int attacker_ID = attacker.getPlayerID();

		int counter = 0;
		while(attacker_ID != ownerID)
		{
			System.out.println("This territory is not yours. Re-enter coordinates: ");
			System.out.print("Re-Enter x: ");
			x = reader.nextInt();
			System.out.print("Re-Enter y: ");
			y = reader.nextInt();
			ownerID = gameMap.getOwnerID(x,y);
			if(counter == 2) // re-print tha current player map
			{
				counter = 0;
				for(int i = 0; i < gameMap.getRow(); i++)
				{
					for (int j = 0; j < gameMap.getCol(); j++)
					{
						System.out.printf("\t ID: %d, Units: %d",gameMap.getOwnerID(i,j));
					}
					System.out.println();
				}
			}
		}

		int additional_army = addArmyEachTurn(x, y, attacker, gameMap);//update the army into territory
		boolean canAttack = gameMap.canAttack(x,y);

		//if not enough army to attack
		while (!canAttack)
		{
			System.out.println("Insufficient Units to attack. ");
			System.out.println("Re-select your territory to begin.");//attacker territory
			System.out.print("Enter x coordinate: ");
			x = reader.nextInt();
			System.out.print("Enter y coordinate: ");
			y = reader.nextInt();
			ownerID = gameMap.getOwnerID(x,y); //get ownerId from this territory
			attacker_ID = attacker.getPlayerID();

			counter = 0;
			while(attacker_ID != ownerID)
			{
				System.out.println("This territory is not yours. Re-enter coordinates: ");
				System.out.print("Re-Enter x: ");
				x = reader.nextInt();
				System.out.print("Re-Enter y: ");
				y = reader.nextInt();
				ownerID = gameMap.getOwnerID(x,y);

				if(counter == 2) // re-print tha current player map
				{
					counter = 0;
					for(int i = 0; i < gameMap.getRow(); i++)
					{
						for (int j = 0; j < gameMap.getCol(); j++)
						{
							System.out.printf("\t ID: %d, Units: %d",gameMap.getOwnerID(i,j));
						}
						System.out.println();
					}
				}
			}

			canAttack = gameMap.canAttack(x,y);
		}

		//If there are enough units to attack, then
		System.out.println("Choose opponent's territory to attack: ");
		System.out.print("Enter x coordinate: ");
		int def_x_territory = reader.nextInt();
		System.out.print("Enter y coordinate: ");
		int def_y_territory = reader.nextInt();
		boolean areEnemyNeighbors = gameMap.areEnemyNeighbors(x,y,def_x_territory,def_y_territory); //check to see if this is valid

		//if not neightbor, select unit it's
		while (!areEnemyNeighbors)
		{
			System.out.println("Territory is not adjacent. Re-select territory to attack: ");
			System.out.print("Re-Enter x coordinate: ");
			def_x_territory = reader.nextInt();
			System.out.print("Re-Enter y coordinate: ");
			def_y_territory = reader.nextInt();
			areEnemyNeighbors = gameMap.areEnemyNeighbors(x,y,def_x_territory,def_y_territory);
		}

		if(areEnemyNeighbors)
			System.out.println("Ready to battle. Rolling dice. ");


	}

	//get additional unit and then add update the total army
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

	private void endBattle(Player attacker, Map gameMap)
	{
		
	}

}

/*
Idea:
1. can consider each row in the map as a continent..if a player owns the entire row,
then award players with additional army
*/