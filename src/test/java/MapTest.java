import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class MapTest {

    Map testMap = new Map(6, 7);
    @Test
    public void setRow() {
        testMap.setRow(5);
        assertEquals(5, testMap.getRow());
    }

    @Test
    public void setCol() {
        testMap.setCol(6);
        assertEquals(6, testMap.getCol());
    }

    @Test
    public void getRow() {
        testMap.setCol(4);
        assertEquals(4, testMap.getCol());
    }

    @Test
    public void getCol() {
        testMap.setCol(3);
        assertEquals(3, testMap.getCol());
    }

    @Test
    public void initMap()
    {
        Territory test = new Territory();
        testMap.initMap();
        for(int x = 0; x < testMap.getRow(); x++)
        {
            for(int y = 0; y < testMap.getCol(); y++)
                assertEquals(-1, testMap.getOwnerID(x,y));
        }
    }

    @Test
    public void isValidCoordinates() {
        assertTrue(testMap.isValidCoordinates(5,5));
        assertFalse(testMap.isValidCoordinates(10,10));
    }

    @Test
    public void getNumUnits() {
        testMap.setNumUnits(5,5,20);
        assertEquals(20, testMap.getNumUnits(5,5));
        assertEquals(0,testMap.getNumUnits(10,10));
    }

    @Test
    public void setNumUnits() {
        testMap.setNumUnits(1,1,10);
        assertEquals(10, testMap.getNumUnits(1,1));
        testMap.setNumUnits(10,10,-1);
        assertNotEquals(-1, testMap.getNumUnits(10,10));
        assertNotNull((testMap.getNumUnits(10,10)));


    }

    @Test
    public void numOwnedTerritories() {
        assertEquals(0, testMap.numOwnedTerritories(1));
        testMap.setTerritory(1,1,1,0);
        assertEquals(1, testMap.numOwnedTerritories(1));
        testMap.setTerritory(2,2,1,0);
        assertEquals(2, testMap.numOwnedTerritories(1));
        testMap.setTerritory(2,2,1,0);
        assertEquals(3, testMap.numOwnedTerritories(1));
    }

    @Test
    public void getOwnerID() {
        assertFalse(testMap.isValidCoordinates(10,10));
        testMap.setId(3,3,3);
        int x = testMap.getOwnerID(3,3);
        assertEquals(3, x);
    }

    @Test
    public void setId() {
        assertFalse(testMap.isValidCoordinates(10,10));
        testMap.setId(2,2,2);
        int x = testMap.getOwnerID(2,2);
        assertEquals(2, x);
    }


    @Test
    public void setTerritory() {
        testMap.setTerritory(1,1,1,1);
        int x = testMap.getOwnerID(1, 1);
        assertEquals(1, x);
    }

    @Test
    public void canAttack() {
        assertFalse(testMap.canAttack(10,10));
        testMap.setNumUnits(1,1,1);
        assertFalse(testMap.canAttack(1,1));
        testMap.setNumUnits(1,1,2);
        assertTrue(testMap.canAttack(10,10));
    }

    @Test
    public void areEnemyNeighbors() {
        testMap.setId(1,1,1);
        testMap.setId(1,0,1);
        testMap.setId(1,2,2);
        assertFalse(testMap.areEnemyNeighbors(10,10,1,2));
        assertFalse(testMap.areEnemyNeighbors(1,1,10,10));
        assertTrue(testMap.areEnemyNeighbors(1,1,1,1));
        assertFalse(testMap.areEnemyNeighbors(1,0,1,1));


    }



}