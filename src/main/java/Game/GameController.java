package Game;

import com.google.gson.Gson;

import java.util.ArrayList;

public class GameController {

    private static GameController instance = null;

    private int seed;
    private int numberOfPlayers;
    private String currentPlayer;
    private boolean gameActive = true;
    private boolean endTurn = false;
    private int rangedNumberOfMoves = 2;
    private int meleeNumberOfMoves = 4;
    private int cavalryNumberOfMoves = 6;
    private ArrayList<String> players = new ArrayList();
    private ArrayList<Base> bases = new ArrayList();
    private ArrayList<Unit> units = new ArrayList();

    public GameController() {
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void start(String[] usernames, int seed) {
        GameController.getInstance().seed = seed;
        GameController.getInstance().numberOfPlayers = usernames.length;

        for (int i = 0; i < usernames.length; i++) {
            players.add(usernames[i]);
        }

        currentPlayer = GameController.getInstance().players.get(0);
        GameBoard game = new GameBoard(seed);

//=================GAME LOOP==================================================

//        while (gameActive == true) {
//            while (endTurn == false) {
//
//            }
//        }
    }

    public void forfeit(String username) {
        players.remove(username);
        numberOfPlayers--;
    }

    public void endTurn() {
        endTurn = true;
        int currentPlayerPostion = players.indexOf(currentPlayer);
        if (currentPlayerPostion == numberOfPlayers - 1) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(currentPlayerPostion++);
        }
    }

//    public void toArrayList(String[] players) {
//        for (int i = 0; i < players.length; i++) {
//            GameController.getInstance().players.add(players[i]);
//        }
//        numberOfPlayers = GameController.getInstance().players.size();
//    }

    public int getRangedNumberOfMoves() {
        return rangedNumberOfMoves;
    }

    public int getMeleeNumberOfMoves() {
        return meleeNumberOfMoves;
    }

    public int getCavalryNumberOfMoves() {
        return cavalryNumberOfMoves;
    }

    public void setGameActive(boolean gameActive) {
        GameController.getInstance().gameActive = gameActive;
    }

    //================API FUNCTIONS=====================================================================

    public void upgrade(int BaseID) {
        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getBaseID() == BaseID) {
                bases.get(i).upgrade();
            }
        }
    }

    public void createUnit(int xCoord, int yCoord, UnitTypeEnum type, int baseID) {
        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getBaseID() == baseID) {
                bases.get(i).createUnit(xCoord, yCoord, type);
            }
        }
    }

    public void move(int xCoord, int yCoord, int unitID) {
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID) {
                units.get(i).move(xCoord, yCoord);
            }
        }
    }

    public void attack(int xCoord, int yCoord, int unitID) {
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID) {
                units.get(i).attack(xCoord, yCoord);
            }
        }
    }

    public String getMoves(int unitID) {
        Position[] position = null;

        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID) {
                position = units.get(i).getMoves(true);
            }
        }

        Positions positions = new Positions(position);
        Gson gson = new Gson();
        String json = gson.toJson(positions);

        System.out.println(json);
        return json;
    }

    public String getAttacks(int unitID) {
        Position[] position = null;

        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID) {
                position = units.get(i).getMoves(false);
            }
        }

        Positions positions = new Positions(position);
        Gson gson = new Gson();
        String json = gson.toJson(positions);

        System.out.println(json);
        return json;
    }

    public String getPlacement(int baseID) {
        Position[] position = null;

        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getBaseID() == baseID) {
                position = bases.get(i).getPlacement();
            }
        }

        Positions positions = new Positions(position);
        Gson gson = new Gson();
        String json = gson.toJson(positions);

        System.out.println(json);
        return json;
    }

    public String getState() {
        GameState state = new GameState(players.toArray(new String[0]), bases.toArray(new Base[0]), units.toArray(new Unit[0]), GameBoard.getTileTypes());
        state.setBoardHeight(GameBoard.getBoardHeight());
        state.setBoardWidth(GameBoard.getBoardWidth());
        state.setCurrentPlayer(currentPlayer);

        Gson gson = new Gson();
        String json = gson.toJson(state);

        System.out.println(json);
        return json;
    }
}
