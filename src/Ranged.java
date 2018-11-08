public class Ranged extends Unit {

    public Ranged(int level, int xCoordinate, int yCoordinate) {
        this.level = level;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = UnitTypeEnum.RANGED;
        this.numberOfMovesRemaining = GameController.getRangedNumberOfMoves();
        this.tile = GameBoard.gameTiles[xCoordinate][yCoordinate];
    }

    @Override
    public void printThing() {
        System.out.print(" R ");
    }
}
