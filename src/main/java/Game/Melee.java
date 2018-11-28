package Game;

public class Melee extends Unit {
    private static transient int meleeHealth = 8;
    private static transient int MeleeAttack = 8;
    private static transient int meleeNumberOfMoves = 3;

    public Melee(int level, int xCoordinate, int yCoordinate, String username) {
        this.level = level;
        this.health = meleeHealth;
        this.attackStrength = MeleeAttack;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = UnitTypeEnum.MELEE;
        this.numberOfMovesRemaining = meleeNumberOfMoves;
        this.numberOfAttacksRemaining = 1;
        this.tile = GameBoard.gameBoardHolder.get(username).gameTiles[xCoordinate][yCoordinate];

        this.id = counter;
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
