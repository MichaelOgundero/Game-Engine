package Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Ranged extends Unit {
    private static transient int rangedHealth = 10;
    private static transient int rangedAttack = 6;
    private static transient int rangedNumberOfMoves = 2;

    public Ranged(int level, int xCoordinate, int yCoordinate, String username) {
        this.level = level;
        this.health = rangedHealth;
        this.health = rangedAttack;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = UnitTypeEnum.RANGED;
        this.numberOfMovesRemaining = rangedNumberOfMoves;
        this.numberOfAttacksRemaining = 1;
        this.attackStrength = rangedAttack;
        this.tile = GameBoard.gameBoardHolder.get(username).gameTiles[xCoordinate][yCoordinate];

        this.id = counter;
        counter++;
        this.playerBelongsTo = username;
    }

    @Override
    public Position[] getAttacks(String username) {
        int boardWidth = GameBoard.getBoardWidth();
        int boardHeight = GameBoard.getBoardHeight();

        ArrayList<Position> positions = new ArrayList();

        if (numberOfAttacksRemaining > 0) {
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

            int neighborSize = positions.size();
            for (int i = 0; i < neighborSize; i++) {
                Unit temp = new Unit();
                temp.xCoordinate = positions.get(i).getxCoordinate();
                temp.yCoordinate = positions.get(i).getyCoordinate();

                Position[] tempPosition = temp.getMoves(false, username);
                for (int j = 0; j < tempPosition.length; j++) {
                    positions.add(tempPosition[j]);
                }
            }

            //remove all attack-able position without units on them
            for (int i = 0; i < positions.size(); i++) {
                int tileXCoordinate = positions.get(i).getxCoordinate();
                int tileYCoordinate = positions.get(i).getyCoordinate();
                if (GameBoard.gameBoardHolder.get(username).gameTiles[tileXCoordinate][tileYCoordinate].hasThing == false) {
                    positions.remove(i);
                    i--;
                }
            }
        }

        return positions.toArray(new Position[0]);
    }

    @Override
    public void resetMoves() {
        this.numberOfMovesRemaining = rangedNumberOfMoves;
    }
}
