package Game;

import com.google.gson.Gson;

import java.util.ArrayList;

public class GameController {
    private static GameController instance = null;
    public ArrayList<String> players = new ArrayList();
    public ArrayList<Base> bases = new ArrayList();
    public ArrayList<Unit> units = new ArrayList();
    private int numberOfPlayers;
    private String currentPlayer;

    public GameController() {
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void start(String[] usernames, int seed) {
        GameController.getInstance().numberOfPlayers = usernames.length;

        for (int i = 0; i < usernames.length; i++) {
            players.add(usernames[i]);
        }

        currentPlayer = GameController.getInstance().players.get(0);
        GameBoard game = new GameBoard(seed);

        game.initializeBases(players.get(0), 0, 0);
        game.initializeBases(players.get(1), GameBoard.getBoardWidth() - 1, 0);

        if (numberOfPlayers == 3) {
            game.initializeBases(players.get(1), 0, GameBoard.getBoardHeight() - 1);
        } else if (numberOfPlayers == 4) {
            game.initializeBases(players.get(1), 0, GameBoard.getBoardHeight() - 1);
            game.initializeBases(players.get(1), GameBoard.getBoardWidth() - 1, GameBoard.getBoardHeight() - 1);
        }
    }

    public void forfeit(String username) {
        players.remove(username);
        numberOfPlayers--;
    }

    public void endTurn(String username) {
        if (username == currentPlayer) {
            int currentPlayerPostion = players.indexOf(currentPlayer);
            if (currentPlayerPostion == numberOfPlayers - 1) {
                currentPlayer = players.get(0);
            } else {
                currentPlayer = players.get(currentPlayerPostion++);
            }
            for (int i = 0; i < units.size(); i++) {
                units.get(i).resetMoves();
            }
        }
    }

    //================API FUNCTIONS=====================================================================

    public void upgrade(int BaseID, String username) {
        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getBaseID() == BaseID && bases.get(i).getPlayerBelongsTo() == username) {
                bases.get(i).upgrade();
            }
        }
    }

    public void createUnit(int xCoord, int yCoord, UnitTypeEnum type, int baseID, String username) {
        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getBaseID() == baseID && bases.get(i).getPlayerBelongsTo() == username) {
                bases.get(i).createUnit(xCoord, yCoord, type, username);
            }
        }
    }

    public void move(int xCoord, int yCoord, int unitID, String username) {
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID && units.get(i).getPlayerBelongsTo() == username) {
                units.get(i).move(xCoord, yCoord);
            }
        }
    }

    public void attack(int xCoord, int yCoord, int unitID, String username) {
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID && units.get(i).getPlayerBelongsTo() == username) {
                units.get(i).attack(xCoord, yCoord);
            }
        }
    }

    public String getMoves(int unitID, String username) {
        Position[] position = null;

        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID && units.get(i).getPlayerBelongsTo() == username) {
                position = units.get(i).getMoves(true);
            }
        }

        Positions positions = new Positions(position);
        Gson gson = new Gson();
        String json = gson.toJson(positions);

        System.out.println(json);
        return json;
    }

    public String getAttacks(int unitID, String username) {
        Position[] position = null;

        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitID() == unitID && units.get(i).getPlayerBelongsTo() == username) {
                position = units.get(i).getMoves(false);
            }
        }

        Positions positions = new Positions(position);
        Gson gson = new Gson();
        String json = gson.toJson(positions);

        System.out.println(json);
        return json;
    }

    public String getPlacement(int baseID, String username) {
        Position[] position = null;

        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getBaseID() == baseID && bases.get(i).getPlayerBelongsTo() == username) {
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
