import org.junit.Test;

import static org.junit.Assert.*;

public class DieTest {
    Die test = new Die();

    @Test
    public void roll() {
        int test_value = 0;
        for (int i = 0; i < 10; i++)
        {
            test_value = test.roll();
            assertTrue(test_value >= 1 && test_value <= 6);
        }
    }


    @Test
    public void getValue() {
        test.setValue(5);
        assertEquals(5,test.getValue());
    }

    @Test
    public void setValue() {
        test.setValue(5);
        assertEquals(5,test.getValue());
    }
}