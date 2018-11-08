public class Unit extends Thing {
    protected int numberOfMovesRemaining;
    protected int level;
    protected int attackStrength;
    protected int unitID;
    protected UnitTypeEnum type;

    public void move(int xCoordinate, int yCoordinate){
        GameBoard.gameTiles[this.xCoordinate][this.yCoordinate].setThing(null);
        GameBoard.gameTiles[xCoordinate][yCoordinate].setThing(this);
        this.tile = GameBoard.gameTiles[xCoordinate][yCoordinate];
    }

    public void attack(int xCoordinate, int yCoordinate){
        GameBoard.gameTiles[xCoordinate][yCoordinate].getThing().setHealth(GameBoard.gameTiles[xCoordinate][yCoordinate].getThing().getHealth() - attackStrength);
    }

    public int getUnitID() {
        return unitID;
    }

    public void increaseLevel(){
        this.level++;
    }
}