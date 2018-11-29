import org.junit.Test;
import java.util.ArrayList;
import org.junit.Assert;



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
        int x = 3;
        int y = 3;
        Player player = new Player(4);
        Map gamemap = new Map (6,7);

        gamemap.setNumUnits(3,3,5);
        player.setNumUnits(10);
        player.setNumTerritories(3);
        int num_territory = player.getNumTerritories();
        int add_army = num_territory/3;
        if(add_army <= 3)
            add_army = 3;

        int current_units = player.getNumUnits();
        player.setNumUnits(current_units + add_army);

        int current_territory_unit = gamemap.getNumUnits(3,3);
        gamemap.setNumUnits(x,y, current_territory_unit + add_army);
        battle.addArmyEachTurn(x,y,player,gamemap);
        Assert.assertNotSame(-1,battle.addArmyEachTurn(x,y,player,gamemap));
    }

    @Test
    public void addObserver() {
        Player a = new Player(5);
        battle.addObserver(a);
        assertNotNull(players);
    }

    @Test
    public void removeObserver() {
        Player a = new Player(5);
        battle.removeObserver(a);
    }

    @Test
    public void setDefender_Id() {
        battle.setDefender_Id(3);
        for(Player player: players){
            player.update(3);
        }
    }
}