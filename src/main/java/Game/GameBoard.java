package Game;

import java.util.Random;

public class GameBoard {
    private static final int BOARD_WIDTH = 15;
    private static final int BOARD_HEIGHT = 15;
    private final double obstaclePercentage = 0.2;

    public static Tile[][] gameTiles = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
    public static TileTypeEnum[][] tilesTypes = new TileTypeEnum[BOARD_WIDTH][BOARD_HEIGHT];

    public GameBoard(int seed) {
        generateTiles(seed);
    }

    public void initializeBases(String username, int xCoordinate, int yCoordinate){
        Base temp = new Base(xCoordinate, yCoordinate, username);
    }

    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public static TileTypeEnum[][] getTileTypes() {
        return tilesTypes;
    }

    public void generateTiles(int seed){
        if(seed == -1){
            //=========================================CUSTOM MAP=======================================================

            //==========================================================================================================
        }else{
            Random generator = new Random(seed);
            for (int i = 0; i < BOARD_HEIGHT; i++) {
                for (int j = 0; j < BOARD_WIDTH; j++) {
                    if(generator.nextDouble() <= obstaclePercentage){
                        gameTiles[j][i] = new Obstacle();
                    }else{
                        gameTiles[j][i] = new Ground();
                    }
                    tilesTypes[j][i] = gameTiles[j][i].type;
                }
            }
        }
    }
}
