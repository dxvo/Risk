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


    @Before
    public void setup() throws Exception {
        this.gameMap = new Map(6,7);
        this.die = new Die();
        this.numPlayers = 3;
        this.row = gameMap.getRow();
        this.col = gameMap.getCol();
        this.battleHandler = new BattleHandler();
        this.playerList = new ArrayList<Player>();
    }



    @Test
    public void gameStart() {
        //game2.gameSetup();
        //game2.gameLoop();
        //game2.gameCleanup();
    }

    @Test
    public void gameSetup() {
        //game2.playerSetup();
        //game2.playerOrderSetup();
        //game2.mapSetup();
    }


    @Test
    public void playerSetup() {
        for (int i = 0 ; i < numPlayers; i++)
        {
            Player player = new Player(i); //initialize player ID - start with 0
            numUnits = 50 - numPlayers * 5;
            player.setNumUnits(numUnits); // set player number of units
            player.setNumBenchedUnits(numUnits); //set bench unit equal to numunits when just started
            playerList.add(player); // append player into list
        }
        assertEquals(35,playerList.get(1).getNumBenchedUnits());
        assertEquals(35,playerList.get(1).getNumUnits());
        assertEquals(3,playerList.size());
    }


    @Test
    public void mapSetup() {
        int numToFill = 42/this.numPlayers + 1;

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