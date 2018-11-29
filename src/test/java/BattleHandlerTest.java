import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import java.util.ArrayList;
import twitter4j.TwitterException;
import java.io.*;

import static org.junit.Assert.*;

public class BattleHandlerTest {

    private BattleHandler battle = new BattleHandler();
    private ArrayList <Player> players = new ArrayList<>();
    Map gamemap = new Map(6,7);
    Player player1 = new Player(1);
    Player player2 = new Player(2);

    @Test
    public void startBattle() {
        assertNotNull(battle);
    }

    @Test
    public void endBattle(){
        int turnCounter = 0;
        String counter = String.valueOf(turnCounter);
        String message = "During Turn " + counter +"\n";
        PrintStream consolePrint = System.out;
        Tweeter tweet = new Tweeter(consolePrint);
        Map gamemap = new Map(6,7);
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        players.add(player1);
        players.add(player2);
        String id = "";
        String player_numTerritories = "";


        for(int i = 0; i < players.size(); i++)
        {
            id = String.valueOf(players.get(i).getPlayerID());
            player_numTerritories = String.valueOf(players.get(i).getNumTerritories());
            message += "The Player with ID = " + id + " Has " + player_numTerritories + " Territories.\n";

        }




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
        if(num_territory/3 <= 3)
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
        for(int i = 0; i < players.size(); i++){
            players.get(i).update(3);
        }
    }

}