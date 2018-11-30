import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameMasterTest {

    GameMaster game = new GameMaster();
    GameMaster game2 = new GameMaster(3);


    private Map gameMap;
    private ArrayList<Player> playerList;
    private BattleHandler battleHandler;
    private Die die;
    private int numPlayers;
    private int numUnits;
    private int row; //for map
    private int col; //for map
    private int credit_purchase; //for purchase function
    private int turnCounter;


    public GameMasterTest(){
        gameMap = new Map(6,7);
        die = new Die();
        numPlayers = 2;
        row = gameMap.getRow();
        col = gameMap.getCol();
        battleHandler = new BattleHandler();
        playerList = new ArrayList<>();

    }



    @Test
    public void gameStart() {
        this.gameSetup();
        this.gameLoop();
        this.gameCleanup();
    }

    @Test
    public void gameSetup() {
        this.playerSetup();
        this.playerOrderSetup();
        this.mapSetup();
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
    }

    @Test
    public void next_turn() {
    }

    @Test
    public void gameCleanup() {
    }
}