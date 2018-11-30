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
        game2.playerSetup();
        game2.playerOrderSetup();
        game2.mapSetup();
    }


    @Test
    public void playerSetup() {
        game2.playerSetup();
        assertEquals(35,playerList.get(1).getNumBenchedUnits());
        assertEquals(35,playerList.get(1).getNumUnits());
        assertEquals(3,playerList.size());
    }


    @Test
    public void mapSetup() {
        game2.mapSetup();
    }

    @Test
    public void playerOrderSetup() {
        game2.playerOrderSetup();
        assertEquals(2,playerList.get(0).getPlayerID());
        assertEquals(1,playerList.get(1).getPlayerID());
        assertEquals(0,playerList.get(2).getPlayerID());
    }

    @Test
    public void gameLoop() {
        Player player5 = new Player(5);
        playerList.add(player5);
        game.gameLoop();
        assertEquals(5,playerList.get(0).getPlayerID());

    }

    @Test
    public void playerTurn(int choice) {

        Player player5 = new Player(5);
        game2.playerTurn(player5);


    }

    @Test
    public void next_turn() {
        game2.next_turn(playerList.get(0));
        game2.next_turn(playerList.get(1));
    }

    @Test
    public void gameCleanup() {
        game2.gameCleanup();
    }
}