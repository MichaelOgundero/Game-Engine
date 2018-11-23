package Game;

import java.util.ArrayList;

public class Base extends Thing {
    private transient ArrayList<Unit> units;
    private int baseID;
    private String playerBelongsTo;

    private int level;
    private transient int levelCap = 5;
    private static transient int counter = 0;

    public Base(int xCoordinate, int yCoordinate, String playerBelongsTo) {
        this.level = 1;
        units = new ArrayList<>();
        this.playerBelongsTo = playerBelongsTo;

        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.baseID = counter;

        counter++;
        GameController.getInstance().bases.add(this);
    }

    public void upgrade() {
        if (this.level < levelCap) {
            this.level++;
            for (int i = 0; i < units.size(); i++) {
                units.get(i).increaseLevel();
            }
        }
    }

    public Position[] getPlacement() {
        int boardWidth = GameBoard.getBoardWidth();
        int boardHeight = GameBoard.getBoardHeight();

        ArrayList<Position> positions = new ArrayList();

        //UPPER LEFT
        if (this.xCoordinate == 0 && this.yCoordinate == 0) {
            positions.add(new Position(this.xCoordinate + 1, this.yCoordinate));
            positions.add(new Position(this.xCoordinate, this.yCoordinate + 1));
        }
        //UPPER RIGHT
        else if (this.xCoordinate == boardWidth - 1 && this.yCoordinate == 0) {
            positions.add(new Position(this.xCoordinate - 1, this.yCoordinate));
            positions.add(new Position(this.xCoordinate, this.yCoordinate + 1));
        }
        //LOWER LEFT
        else if (this.xCoordinate == 0 && this.yCoordinate == boardHeight - 1) {
            positions.add(new Position(this.xCoordinate, this.yCoordinate - 1));
            positions.add(new Position(this.xCoordinate + 1, this.yCoordinate));
        }
        //LOWER RIGHT
        else if (this.xCoordinate == boardWidth - 1 && this.yCoordinate == boardHeight - 1) {
            positions.add(new Position(this.xCoordinate, this.yCoordinate - 1));
            positions.add(new Position(this.xCoordinate - 1, this.yCoordinate));
        }
        //TOP SIDE
        else if (this.yCoordinate == 0) {
            positions.add(new Position(this.xCoordinate - 1, this.yCoordinate));
            positions.add(new Position(this.xCoordinate + 1, this.yCoordinate));
            positions.add(new Position(this.xCoordinate, this.yCoordinate + 1));
        }
        //BOTTOM SIDE
        else if (this.yCoordinate == boardHeight - 1) {
            positions.add(new Position(this.xCoordinate - 1, this.yCoordinate));
            positions.add(new Position(this.xCoordinate + 1, this.yCoordinate));
            positions.add(new Position(this.xCoordinate, this.yCoordinate - 1));
        }
        //LEFT SIDE
        else if (this.xCoordinate == 0) {
            positions.add(new Position(this.xCoordinate, this.yCoordinate - 1));
            positions.add(new Position(this.xCoordinate, this.yCoordinate + 1));
            positions.add(new Position(this.xCoordinate + 1, this.yCoordinate));
        }
        //RIGHT SIDE
        else if (this.xCoordinate == boardWidth - 1) {
            positions.add(new Position(this.xCoordinate, this.yCoordinate - 1));
            positions.add(new Position(this.xCoordinate, this.yCoordinate + 1));
            positions.add(new Position(this.xCoordinate - 1, this.yCoordinate));
        }
        //OTHER
        else {
            positions.add(new Position(this.xCoordinate, this.yCoordinate - 1));
            positions.add(new Position(this.xCoordinate, this.yCoordinate + 1));
            positions.add(new Position(this.xCoordinate + 1, this.yCoordinate));
            positions.add(new Position(this.xCoordinate - 1, this.yCoordinate));
        }

        for (int i = 0; i < positions.size(); i++) {
            int tileXCoordinate = positions.get(i).getxCoordinate();
            int tileYCoordinate = positions.get(i).getyCoordinate();
            if (GameBoard.gameTiles[tileXCoordinate][tileYCoordinate].hasThing == true) {
                positions.remove(i);
                i--;
            }
        }

        return (Position[]) positions.toArray();
    }

    public int getBaseID() {
        return baseID;
    }

    public void createUnit(int xCoordinate, int yCoordinate, UnitTypeEnum type) {
        if (type == UnitTypeEnum.CAVALRY) {
            Cavalry temp = new Cavalry(this.level, xCoordinate, yCoordinate);
            GameBoard.gameTiles[xCoordinate][yCoordinate].setThing(temp);
            units.add(temp);
            GameController.getInstance().units.add(temp);
        }
        if (type == UnitTypeEnum.RANGED) {
            Ranged temp = new Ranged(this.level, xCoordinate, yCoordinate);
            GameBoard.gameTiles[xCoordinate][yCoordinate].setThing(temp);
            units.add(temp);
            GameController.getInstance().units.add(temp);
        }
        if (type == UnitTypeEnum.MELEE) {
            Melee temp = new Melee(this.level, xCoordinate, yCoordinate);
            GameBoard.gameTiles[xCoordinate][yCoordinate].setThing(temp);
            units.add(temp);
            GameController.getInstance().units.add(temp);
        }
    }
}
