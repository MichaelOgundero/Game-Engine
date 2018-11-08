public class Cavalry extends Unit {

    public Cavalry(int level, int xCoordinate, int yCoordinate) {
        this.level = level;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = UnitTypeEnum.CAVALRY;
        this.numberOfMovesRemaining = GameController.getCavalryNumberOfMoves();
        this.tile = GameBoard.gameTiles[xCoordinate][yCoordinate];
    }

    @Override
    public void printThing() {
        System.out.print(" C ");
    }
}
