import com.google.gson.Gson;

import java.util.ArrayList;

public class GameController {

    private static int seed;
    private static int numberOfPlayers;
    private static String currentPlayer;
    private static boolean gameActive = true;
    private static boolean endTurn = false;
    private static int rangedNumberOfMoves = 2;
    private static int meleeNumberOfMoves = 4;
    private static int cavalryNumberOfMoves = 6;
    private static ArrayList<String> players = new ArrayList();
    private static ArrayList<Base> bases = new ArrayList();
    private static ArrayList<Unit> units = new ArrayList();

    public static void main(String[] args) {
        players.add("Jenna");
        players.add("Spike");
        players.add("Peter");
        numberOfPlayers = players.size();

        start(players.toArray(new String[0]),100);
    }

    public static void start(String[] players, int seed) {
        GameController.seed = seed;
        currentPlayer = GameController.players.get(0);
        GameBoard game = new GameBoard(seed);
        while (gameActive == true) {
            while (endTurn == false) {

            }
        }
    }

    public static void forfeit(String username) {
        players.remove(username);
        numberOfPlayers--;
    }

    public static void endTurn() {
        endTurn = true;
        int currentPlayerPostion = players.indexOf(currentPlayer);
        if (currentPlayerPostion == numberOfPlayers - 1) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(currentPlayerPostion++);
        }
    }

    public static void toArrayList(String[] players) {
        for (int i = 0; i < players.length; i++) {
            GameController.players.add(players[i]);
        }
        numberOfPlayers = GameController.players.size();
    }

    public static int getRangedNumberOfMoves() {
        return rangedNumberOfMoves;
    }

    public static int getMeleeNumberOfMoves() {
        return meleeNumberOfMoves;
    }

    public static int getCavalryNumberOfMoves() {
        return cavalryNumberOfMoves;
    }

    public static void setGameActive(boolean gameActive) {
        GameController.gameActive = gameActive;
    }

    //================API FUNCTIONS=====================================================================

    public static void upgrade(int baseID) {
        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getBaseID() == baseID) {
                bases.get(i).upgrade();
            }
        }
    }

    public static void create(int xCoordinate, int yCoordinate, UnitTypeEnum type, int baseID) {
        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getBaseID() == baseID) {
                bases.get(i).createUnit(xCoordinate, yCoordinate, type);
            }
        }
    }

    public static void move(int xCoordinate, int yCoordinate, int unitID){
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID) {
                units.get(i).move(xCoordinate,yCoordinate);
            }
        }
    }

    public static void attack(int xCoordinate, int yCoordinate, int unitID){
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID) {
                units.get(i).attack(xCoordinate,yCoordinate);
            }
        }
    }

    public static Position[] getMoves(int unitID){
        Position[] position = null;

        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID) {
                position = units.get(i).getMoves(true);
            }
        }
        return position;
    }

    public static Position[] getAttacks(int unitID){
        Position[] position = null;

        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID) {
                position = units.get(i).getMoves(false);
            }
        }
        return position;
    }

    public static Position[] getPlacement(int baseID){
        Position[] position = null;

        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getBaseID() == baseID) {
                position = bases.get(i).getPlacement();
            }
        }
        return position;
    }

    public static void getState(){
        Gson gson = new Gson();
    }
}
