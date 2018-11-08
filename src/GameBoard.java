import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    private static final int BOARD_WIDTH = 12;
    private static final int BOARD_HEIGHT = 12;
    private final double obstaclePercentage = 0.2;

    public static Tile[][] gameTiles = new Tile[BOARD_WIDTH][BOARD_HEIGHT];

    public GameBoard(int seed) {
        generateTiles(seed);
        drawTiles();
    }

    public void generateTiles(int seed){
        Random generator = new Random(seed);
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                if(generator.nextDouble() <= obstaclePercentage){
                    gameTiles[i][j] = new Obstacle();
                }else{
                    gameTiles[i][j] = new Ground();
                }
            }
        }
    }

    public void drawTiles(){
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                System.out.print("|");
                gameTiles[i][j].drawTile();
            }
            System.out.print("|");
            System.out.println();
        }
    }
}
