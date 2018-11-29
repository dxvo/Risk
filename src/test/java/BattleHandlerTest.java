import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import java.util.ArrayList;
import twitter4j.TwitterException;
import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class BattleHandlerTest {

    private BattleHandler battle = new BattleHandler();
    private ArrayList <Player> players = new ArrayList<>();
    Map gamemap = new Map(6,7);
    Player player1 = new Player(1);
    Player player2 = new Player(2);
    Player player3 = new Player(3);
    int defender_Id = -1;
    Die die = new Die();

    @Test
    public void startBattle() {
        players.add(player1);
        players.add(player2);
        int turnCounter = 0;

        battle.startBattle(player1,gamemap,players,turnCounter);
        battle.endBattle(player1,gamemap,players,turnCounter);
    }

    @Test
    public void Battle(){
        gamemap.setId(1,1,1);
        gamemap.setNumUnits(1,1,5);
        gamemap.setId(1,2,2);
        gamemap.setNumUnits(1,2,3);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        System.out.println("\n CURRENT MAP");
        for (int i = 0; i < gamemap.getRow(); i++) {
            for (int j = 0; j < gamemap.getCol(); j++) {
                System.out.printf("\t ID: %d, Units: %d", gamemap.getOwnerID(i, j), gamemap.getNumUnits(i, j));
            }
            System.out.println();
        }
        System.out.printf("\nThis is player's %d turn\n", player1.getPlayerID());


        int x = 1;
        int y = 1;
        int ownerID = gamemap.getOwnerID(x, y);
        int attacker_ID = player1.getPlayerID();
        assertEquals(ownerID,attacker_ID);

        battle.addArmyEachTurn(1,1,player1,gamemap);
        boolean canAttack = gamemap.canAttack(x, y);
        assertTrue(canAttack);
        assertFalse(gamemap.canAttack(10, 10));
        int x2 = 1;
        int y2 =2;
        boolean areEnemyNeighbors = gamemap.areEnemyNeighbors(x,y,x2,y2);
        assertTrue(areEnemyNeighbors);
        assertFalse(gamemap.areEnemyNeighbors(x,y,4,4));

        defender_Id = gamemap.getOwnerID(x2,y2);
        assertEquals(2,defender_Id);

        battle.addObserver(player2);
        battle.setDefender_Id(defender_Id);

        System.out.println("BATTLE BEGINS.\n");
        battle.removeObserver(player2);

        int attacker_unit = gamemap.getNumUnits(x, y);
        int defender_unit = gamemap.getNumUnits(x2, y2);
        int attacker_num_die_roll = 0;
        int defender_num_die_roll = 0;
        int[] largest_die = new int[2];
        int units_moved_after_battle = 0;
        boolean keep_attack = true;
        int attack_counter = 1;

        while (attacker_unit >= 2 && defender_unit > 0 && keep_attack){
            System.out.printf("\nAttacker - Player %d - Your territory currently has %d units", attacker_ID, attacker_unit);
            System.out.print("\nHow many times do you want to roll ?( Max is 3): ");
            attacker_num_die_roll = 1;
            defender_num_die_roll = 1;
            largest_die[0] = 6;
            largest_die[1] = attacker_ID;
            gamemap.setNumUnits(x2,y2,defender_unit -1);
            keep_attack = false;
        }

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