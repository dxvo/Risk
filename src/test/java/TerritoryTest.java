import org.junit.Test;

import static org.junit.Assert.*;

public class TerritoryTest {

    Territory tester = new Territory();

    @Test
    public void getNumUnitsTest() {
        tester.setNumUnits(20);
        assertEquals(20, tester.getNumUnits());
    }

    @Test
    public void getOwnerIdTest() {
        tester.setOwnerID(5);
        assertEquals(5, tester.getOwnerID());
    }
}