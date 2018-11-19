package Game;

public class GameState {
    private int BOARD_HEIGHT;
    private int BOARD_WIDTH;
    private String currentPlayer;

    private String[] players;
    private Base[] bases;
    private Unit[] units;
    private TileTypeEnum[][] gameTiles;

    public GameState(String[] players, Base[] bases, Unit[] units, TileTypeEnum[][] tiles) {
        this.players = players;
        this.bases = bases;
        this.units = units;
        this.gameTiles = tiles;
    }

    public void setBoardHeight(int BOARD_HEIGHT) {
        this.BOARD_HEIGHT = BOARD_HEIGHT;
    }

    public void setBoardWidth(int BOARD_WIDTH) {
        this.BOARD_WIDTH = BOARD_WIDTH;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
