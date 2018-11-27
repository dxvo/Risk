import org.junit.Test;

import static org.junit.Assert.*;

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
    public void initMap() {
    }

    @Test
    public void isValidCoordinates() {
    }

    @Test
    public void numTerritories() {
    }

    @Test
    public void getNumUnits() {
    }

    @Test
    public void setNumUnits() {
    }

    @Test
    public void numOwnedTerritories() {
    }

    @Test
    public void getOwnerID() {
    }

    @Test
    public void setId() {
    }

    @Test
    public void setTerritory() {
    }

    @Test
    public void canAttack() {
    }

    @Test
    public void hasEnemyNeighbor() {
    }

    @Test
    public void areEnemyNeighbors() {
    }

    @Test
    public void getData() {
    }
}