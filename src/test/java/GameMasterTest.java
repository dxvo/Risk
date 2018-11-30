import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameMasterTest {

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

    @Before
    public void setup() throws Exception {
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
    }

    @Test
    public void next_turn() {
    }

    @Test
    public void gameCleanup() {
    }
}