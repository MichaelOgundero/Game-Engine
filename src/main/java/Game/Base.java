package Game;

import java.util.ArrayList;

import static Game.GameBoard.gameTiles;

public class Base extends Thing {
    private static transient int baseHealth = 100;
    private static transient int counter = 0;
    private transient ArrayList<Unit> units;
    private int baseID;
    private String playerBelongsTo;
    private int level;
    private transient int levelCap = 5;
    private int numberOfCreatableUnits = 1;

    public Base(int xCoordinate, int yCoordinate, String playerBelongsTo) {
        this.level = 1;
        this.health = baseHealth;
        this.playerBelongsTo = playerBelongsTo;
        units = new ArrayList<>();

        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.baseID = counter;
        counter++;

        this.tile = gameTiles[xCoordinate][yCoordinate];
        gameTiles[xCoordinate][yCoordinate].setThing(this);
        gameTiles[xCoordinate][yCoordinate].type = TileTypeEnum.GROUND;
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
            if (gameTiles[tileXCoordinate][tileYCoordinate].hasThing == true) {
                positions.remove(i);
                i--;
            }
        }

        return positions.toArray(new Position[0]);
    }

    public int getBaseID() {
        return baseID;
    }

    public String getPlayerBelongsTo() {
        return playerBelongsTo;
    }

    public void resetCreatableUnits(){
        this.numberOfCreatableUnits = 1;
    }

    public void createUnit(int xCoordinate, int yCoordinate, UnitTypeEnum type, String username) {
        if (type == UnitTypeEnum.CAVALRY) {
            Cavalry temp = new Cavalry(this.level, xCoordinate, yCoordinate, username);
            gameTiles[xCoordinate][yCoordinate].setThing(temp);
            this.tile = gameTiles[xCoordinate][yCoordinate];
            units.add(temp);
            GameController.getInstance().units.add(temp);
        }
        if (type == UnitTypeEnum.RANGED) {
            Ranged temp = new Ranged(this.level, xCoordinate, yCoordinate, username);
            gameTiles[xCoordinate][yCoordinate].setThing(temp);
            this.tile = gameTiles[xCoordinate][yCoordinate];
            units.add(temp);
            GameController.getInstance().units.add(temp);
        }
        if (type == UnitTypeEnum.MELEE) {
            Melee temp = new Melee(this.level, xCoordinate, yCoordinate, username);
            gameTiles[xCoordinate][yCoordinate].setThing(temp);
            this.tile = gameTiles[xCoordinate][yCoordinate];
            units.add(temp);
            GameController.getInstance().units.add(temp);
        }
        numberOfCreatableUnits--;
    }
}
