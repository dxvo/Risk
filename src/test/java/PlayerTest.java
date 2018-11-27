import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player testPlayer = new Player(5);

    @Test
    public void getCredit_balanceTest() {
        testPlayer.setCredit_balance(100);
        assertEquals(100, testPlayer.getCredit_balance());
    }

    @Test
    public void getPlayerIDTest() {
        assertEquals(5, testPlayer.getPlayerID());
    }

    @Test
    public void getNumUnitsTest() {
        testPlayer.setNumUnits(10);
        assertEquals(10, testPlayer.getNumUnits());
    }

    @Test
    public void getNumBenchedUnitsTest() {
        testPlayer.setNumBenchedUnits(5);
        assertEquals(5, testPlayer.getNumBenchedUnits());
    }

    @Test
    public void getNumTerritoriesTest() {
        testPlayer.setNumTerritories(20);
        assertEquals(20, testPlayer.getNumTerritories());
    }
}