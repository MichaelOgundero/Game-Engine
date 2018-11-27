package Game;

public class Cavalry extends Unit {
    private static transient int cavalryHealth = 10;
    private static transient int cavalryStrength = 6;
    private static transient int cavalryNumberOfMoves = 5;

    public Cavalry(int level, int xCoordinate, int yCoordinate, String username) {
        this.level = level;
        this.health = cavalryHealth;
        this.attackStrength = cavalryStrength;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = UnitTypeEnum.CAVALRY;
        this.numberOfMovesRemaining = cavalryNumberOfMoves;
        this.tile = GameBoard.gameBoardHolder.get(username).gameTiles[xCoordinate][yCoordinate];

        this.id = counter;
        counter++;
        this.playerBelongsTo = username;
    }

    @Override
    public void resetMoves() {
        this.numberOfMovesRemaining = cavalryNumberOfMoves;
    }

//    @Override
//    public String getPlayerBelongsTo() {
//        return this.playerBelongsTo;
//    }
}
