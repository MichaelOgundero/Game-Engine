package Game;

import com.google.gson.Gson;

import java.util.ArrayList;

public class GameController {

    private static GameController instance = null;

    private int seed;
    private int numberOfPlayers;
    private String currentPlayer;

    private int rangedNumberOfMoves = 2;
    private int meleeNumberOfMoves = 3;
    private int cavalryNumberOfMoves = 5;

    public ArrayList<String> players = new ArrayList();
    public ArrayList<Base> bases = new ArrayList();
    public ArrayList<Unit> units = new ArrayList();

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

    }

    public void forfeit(String username) {
        players.remove(username);
        numberOfPlayers--;
    }

    public void endTurn(String username) {
        int currentPlayerPostion = players.indexOf(currentPlayer);
        if (currentPlayerPostion == numberOfPlayers - 1) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(currentPlayerPostion++);
        }
    }

    public int getRangedNumberOfMoves() {
        return rangedNumberOfMoves;
    }

    public int getMeleeNumberOfMoves() {
        return meleeNumberOfMoves;
    }

    public int getCavalryNumberOfMoves() {
        return cavalryNumberOfMoves;
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
