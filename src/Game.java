import java.util.Scanner;

public class Game 
{
	public static void main() {	
		
		int n = start();
		createPlayers(n);
		//choose who goes first (dice roll);
		//choose territories();
		
		//while (win condition);
			//turn (queue) infantry/battle/territory
		
		
		
	}
	
	public static int start() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number of Players (2-6): ");
		int n = sc.nextInt(); 
		sc.close();
		return n;
	}
	
	public static void createPlayers(int n) {
	
		Player[] arrPlayer = new Player[n];//array list
		for (int i = 0; i < n; i++)
		{
			arrPlayer[i] = new Player();
			arrPlayer[i].setInfantry(40-((n-2)*5));
		}
	}
}



/*	@Objective:
 * 		Conquer all 42 Territories
 * 
 * 	@Start up:
 * 		for 2-6 players
 * 			each player gets (40 - ((n-2)*5) ) infantry pieces;
 *
 * 		Everyone rolls a die, highest roll gets to place an infantry piece on territory;
 * 		Clockwise from Highest roll, each player places an infantry onto an unoccupied territory;
 * 		After all 42 are claimed, each player places number of additional pieces in any of their territories;
 * 
 * 	@Turn: 
 * 
 *	@Battle:
 *		When attacking: at least one army must remain in your territory;
 *						you can only attack adjoining territories... Array? Grid?;
 *
 *		
 * 		needs to get number of Armies from player to find out how many Dice can be used;
 * 
 * 		Attacking player: choose up to (Number of Armies - 1) dice to use (Maximum of 3 Dice -> 4 Attacking Armies);
 * 		Defending player:	if (Armies Defending == 1) {use 1 die} 
 * 							else if (Armies Defending >=2) {use 2 Dice};
 * 
 * 		Dice Roles must be sorted After Rolling;
 * 		Dice Roles then be compared for both players in order from highest to lowest;
 * 		if ( Attacker DiceRoll > Defender DiceRoll) {Defender Loses Army};
 * 		if ( Attacker DiceRoll <= Defender DiceRoll) {Attacker Loses Army};
 * 
 * 	    if (Attacker Loses all their Armies) {Defender Keeps Territory};
 * 		if (Defender Loses all their Armies) {Attacker takes Territory};
 */