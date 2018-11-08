import java.util.ArrayList;

public class Base extends Thing {
    private ArrayList<Unit> units;
    private int baseID;
    private int xCoordinate;
    private int yCoordinate;
    private int level;
    private int levelCap = 5;

    public Base(int xCoordinate, int yCoordinate) {
        this.level = 1;
    }

    public void upgrade(){
        if(this.level < levelCap){
            this.level++;
            for (int i = 0; i < units.size(); i++) {
                units.get(i).increaseLevel();
            }
        }
    }

    public int getBaseID() {
        return baseID;
    }

    public void createUnit(int xCoordinate, int yCoordinate, UnitTypeEnum type){
        if(type == UnitTypeEnum.CAVALRY){
            Cavalry temp = new Cavalry(this.level, xCoordinate, yCoordinate);
            GameBoard.gameTiles[xCoordinate][yCoordinate].setThing(temp);
            units.add(temp);
        }
        if(type == UnitTypeEnum.RANGED){
            Ranged temp = new Ranged(this.level, xCoordinate, yCoordinate);
            GameBoard.gameTiles[xCoordinate][yCoordinate].setThing(temp);
            units.add(temp);
        }
        if(type == UnitTypeEnum.MELEE){
            Melee temp = new Melee(this.level, xCoordinate, yCoordinate);
            GameBoard.gameTiles[xCoordinate][yCoordinate].setThing(temp);
            units.add(temp);
        }
    }
}
