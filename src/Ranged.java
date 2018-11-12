import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    @Override
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

        if (isMove == true) {
            for (int i = 0; i < positions.size(); i++) {
                int tileXCoordinate = positions.get(i).getxCoordinate();
                int tileYCoordinate = positions.get(i).getyCoordinate();
                if (GameBoard.gameTiles[tileXCoordinate][tileYCoordinate].hasThing == isMove) {
                    positions.remove(i);
                    i--;
                }
            }
        }

        int neighborSize = positions.size();
        for (int i = 0; i < neighborSize; i++) {
            Unit temp = new Unit();
            temp.xCoordinate = positions.get(i).getxCoordinate();
            temp.yCoordinate = positions.get(i).getyCoordinate();

            Position[] tempPosition = temp.getMoves(false);
            for (int j = 0; j < tempPosition.length; j++) {
                positions.add(tempPosition[j]);
                System.out.println("dfadfdsafdsaf");
            }
        }

        //Delete Duplicates
        Set<Position> posSet = new HashSet();
        posSet.addAll(positions);
        positions.clear();
        positions.addAll(posSet);

        return positions.toArray(new Position[0]);
    }
}
