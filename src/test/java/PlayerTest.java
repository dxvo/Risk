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

    @Test
    public void setPlayerID() {
        testPlayer.setPlayerID(20);
        assertEquals(20, testPlayer.getPlayerID());
    }

    @Test
    public void setNumUnits() {
        testPlayer.setNumUnits(20);
        assertEquals(20, testPlayer.getNumUnits());
    }

    @Test
    public void setNumBenchedUnits() {
        testPlayer.setNumBenchedUnits(20);
        assertEquals(20, testPlayer.getNumBenchedUnits());
    }

    @Test
    public void setNumTerritories() {
        testPlayer.setNumTerritories(20);
        assertEquals(20, testPlayer.getNumTerritories());
    }

    @Test
    public void setDie_value() {
        testPlayer.setDie_value(20);
        assertEquals(20, testPlayer.getDie_value());
    }

    @Test
    public void setCredit_balance() {
        testPlayer.setCredit_balance(20);
        assertEquals(20, testPlayer.getCredit_balance());
    }

    @Test
    public void setDefender_id() {
        testPlayer.setDefender_id(20);
        assertEquals(20, testPlayer.getDefender_id());
    }

    @Test
    public void getDefender_id() {
        testPlayer.setDefender_id(20);
        assertEquals(20, testPlayer.getDefender_id());
    }

    @Test
    public void update() {
        testPlayer.setDefender_id(testPlayer.getPlayerID());
        assertEquals(testPlayer.getPlayerID(), testPlayer.getDefender_id());

    }
}