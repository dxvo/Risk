import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

import java.lang.System;
public class GameMasterTest {



    GameMaster test = new GameMaster();
    Player tester1 = new Player(1);
    Player tester2 = new Player(2);

    @Test
    public void gameStart() {
        test.gameStart();


    }

    @Test
    public void next_turn() {
    }


    @Test
    public void gameSetup() {
    }

    @Test
    public void playerSetup() {
    }

    @Test
    public void mapSetup() {
    }

    @Test
    public void playerOrderSetup() {
    }

    @Test
    public void gameLoop() {
    }

    @Test
    public void playerTurn() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player playerTest1 = new Player(1);
        Player playerTest2 = new Player(2);
        playerList.add(playerTest1);
        playerList.add(playerTest2);
        assertEquals(1, playerTest1.getPlayerID());
        assertEquals(2, playerTest2.getPlayerID());
    }


    @Test
    public void gameCleanup() {

    }
}