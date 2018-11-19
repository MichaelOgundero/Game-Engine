package Game;

public class Melee extends Unit {

    public Melee(int level, int xCoordinate, int yCoordinate) {
        this.level = level;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = UnitTypeEnum.MELEE;
        this.numberOfMovesRemaining = GameController.getInstance().getMeleeNumberOfMoves();
        this.tile = GameBoard.gameTiles[xCoordinate][yCoordinate];
    }

    @Override
    public void printThing() {
        System.out.print(" M ");
    }
}
