public class GameState {
    private int BOARD_HEIGHT;
    private int BOARD_WIDTH;
    private String currentPlayer;

    private String[] players;
    private Base[] bases;
    private Unit[] units;
    private TileTypeEnum[][] gameTiles;

    public GameState(String[] players, Base[] bases, Unit[] units) {
        this.players = players;
        this.bases = bases;
        this.units = units;
    }
}
