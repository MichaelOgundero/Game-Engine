package Game;

public class Melee extends Unit {
    private static transient int meleeHealth = 8;
    private static transient int getMeleeAttack = 8;
    private static transient int meleeNumberOfMoves = 3;

    public Melee(int level, int xCoordinate, int yCoordinate, String username) {
        this.level = level;
        this.health = meleeHealth;
        this.attackStrength = getMeleeAttack;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = UnitTypeEnum.MELEE;
        this.numberOfMovesRemaining = meleeNumberOfMoves;
        this.tile = GameBoard.gameTiles[xCoordinate][yCoordinate];

        this.unitID = counter;
        counter++;
        this.playerBelongsTo = username;
    }

    @Override
    public void resetMoves() {
        this.numberOfMovesRemaining = meleeNumberOfMoves;
    }

//    @Override
//    public String getPlayerBelongsTo() {
//        return this.playerBelongsTo;
//    }
}
