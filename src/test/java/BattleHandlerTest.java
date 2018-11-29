import org.junit.Test;
import java.util.ArrayList;



import static org.junit.Assert.*;

public class BattleHandlerTest {
    private BattleHandler battle = new BattleHandler();
    private ArrayList <Player> players = new ArrayList<>();

    @Test
    public void startBattle() {
        assertNotNull(battle);
    }

    @Test
    public void addArmyEachTurn() {

    }

    @Test
    public void addObserver() {
        Player a = new Player(5);
        battle.addObserver(a);
        assertNotNull(players);
    }

    @Test
    public void removeObserver() {
    }

    @Test
    public void setDefender_Id() {
    }
}