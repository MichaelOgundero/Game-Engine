package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameBoard {
    private static final int BOARD_WIDTH = 3;
    private static final int BOARD_HEIGHT = 3;
    private static final double obstaclePercentage = 0.2;
    public static HashMap<String, GameBoard> gameBoardHolder = new HashMap<>();

    public ArrayList<String> players = new ArrayList<>();
    public String currentPlayer;
    public int numberOfPlayers;
    public Tile[][] gameTiles = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
    public TileTypeEnum[][] tilesTypes = new TileTypeEnum[BOARD_WIDTH][BOARD_HEIGHT];

    public GameBoard(int seed, String[] players) {
        for (int i = 0; i < players.length; i++) {
            this.players.add(players[i]);
            gameBoardHolder.put(players[i], this);
        }

        currentPlayer = this.players.get(0);
        numberOfPlayers = players.length;
        generateTiles(seed);
    }

    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public void initializeBases() {
        new Base(0, 0, players.get(0));
        new Base(GameBoard.getBoardWidth() - 1, 0, players.get(1));

        if (numberOfPlayers == 3) {
            new Base(0, GameBoard.getBoardHeight() - 1, players.get(2));
        } else if (numberOfPlayers == 4) {
            new Base(0, GameBoard.getBoardHeight() - 1, players.get(2));
            new Base(GameBoard.getBoardWidth() - 1, GameBoard.getBoardHeight() - 1, players.get(3));
        }
    }

    public TileTypeEnum[][] getTileTypes() {
        return tilesTypes;
    }

    public void generateTiles(int seed) {
        if (seed == -1) {
            //=========================================CUSTOM MAP=======================================================

            //==========================================================================================================
        } else {
            Random generator = new Random(seed);
            for (int i = 0; i < BOARD_WIDTH; i++) {
                for (int j = 0; j < BOARD_HEIGHT; j++) {
                    if (generator.nextDouble() <= obstaclePercentage) {
                        gameTiles[i][j] = new Obstacle();
                    } else {
                        gameTiles[i][j] = new Ground();
                    }
                    tilesTypes[i][j] = gameTiles[i][j].type;
                }
            }
        }
    }
}
