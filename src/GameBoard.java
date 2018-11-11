import java.util.Random;

public class GameBoard {
    private static final int BOARD_WIDTH = 15;
    private static final int BOARD_HEIGHT = 15;
    private final double obstaclePercentage = 0.2;

    public static Tile[][] gameTiles = new Tile[BOARD_WIDTH][BOARD_HEIGHT];

    public GameBoard(int seed) {
        generateTiles(seed);

        //=============================================================================
        Ranged r = new Ranged(1, 2,2);
        r.tile = gameTiles[2][2];
        gameTiles[2][2].setThing(r);

        Melee m = new Melee(1,2,1);
        m.tile = gameTiles[2][1];
        gameTiles[2][1].setThing(m);

        Melee m2 = new Melee(1,2,0);
        m.tile = gameTiles[2][0];
        gameTiles[2][0].setThing(m);


        drawTiles();

        Position[] p = r.getMoves(false);
        for (int i = 0; i < p.length; i++) {
            gameTiles[p[i].getxCoordinate()][p[i].getyCoordinate()].isTarget = true;
        }


        drawTiles();
        //=============================================================================
    }

    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public void generateTiles(int seed){
        Random generator = new Random(seed);
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if(generator.nextDouble() <= obstaclePercentage){
                    gameTiles[j][i] = new Ground();

                }else{
                    gameTiles[j][i] = new Ground();
                }
            }
        }
    }

    public void drawTiles(){
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                System.out.print("|");
                gameTiles[j][i].drawTile();
            }
            System.out.print("|");
            System.out.println();
        }
    }
}
