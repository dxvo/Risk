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