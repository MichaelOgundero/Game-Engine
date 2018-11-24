package Game;

import java.util.ArrayList;

public class Unit extends Thing {
    protected int numberOfMovesRemaining;
    protected int level;
    protected transient int attackStrength;
    protected int unitID;
    protected UnitTypeEnum type;
    protected static transient int counter;

    public void move(int xCoordinate, int yCoordinate) {
        GameBoard.gameTiles[this.xCoordinate][this.yCoordinate].setThing(null);
        GameBoard.gameTiles[this.xCoordinate][this.yCoordinate].setHasThing(false);
        GameBoard.gameTiles[xCoordinate][yCoordinate].setThing(this);
        this.tile = GameBoard.gameTiles[xCoordinate][yCoordinate];
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        numberOfMovesRemaining--;
    }

    public void attack(int xCoordinate, int yCoordinate) {
        GameBoard.gameTiles[xCoordinate][yCoordinate].getThing().setHealth(GameBoard.gameTiles[xCoordinate][yCoordinate].getThing().getHealth() - attackStrength);
    }

    public Position[] getMoves(boolean isMove) {

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
            if (GameBoard.gameTiles[tileXCoordinate][tileYCoordinate].hasThing == isMove) {
                positions.remove(i);
                i--;
            }
        }

        return positions.toArray(new Position[0]);
    }

    public int getUnitID() {
        return unitID;
    }

    public void increaseLevel() {
        this.level++;
    }

    public void resetMoves(){}
}