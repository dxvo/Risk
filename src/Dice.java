import java.util.Arrays;
import java.util.Random;


public class Dice {
	Random generator = new Random();
	int sides = 0;
	public Dice() {
		sides = 6;
	}
	public Dice(int sides1) {
		sides = sides1;
	}
	
	public int roll() {
		int result = generator.nextInt(sides) + 1;
		return result;
	}
	
	public int[] rollMultiple(int nTimes) {
		int array1[] = new int[nTimes];
		for (int i = 0; i < nTimes; i++){
			int result = this.roll();
			array1[i] = result;
		}
		Arrays.sort(array1);
		return array1;
	}
}

//needs to get number of Armies from player to find out how many Dice can be used
//player can then choose up to (Number of Armies - 1) dice to use
//Dice roles must be sorted Afterwards

/* For example, if an attacker has three armies, he/she can roll a maximum of two dice.
A player does have the option of rolling less than their maximum number of dice allotted. 
This would decrease the potential number of armies lost but would reduce the odds of winning.
Once the dice are rolled, they are placed in descending order. 
The attacker's highest die is then compared to the defender's highest and 
if each player has at least two dice then the second largest for both sides is compared.
The attacker loses one army for every die in the compared pairs that is less than or equal to 
the defender's die and the defender loses one army for every compared die that is less than the attacker's die.
*/