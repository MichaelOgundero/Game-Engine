package Game;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GameController {
    private static GameController instance = null;
    public ArrayList<Base> bases = new ArrayList();
    public ArrayList<Unit> units = new ArrayList();
    public ArrayList<GameBoard> games = new ArrayList();

    public GameController() {
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void start(String[] usernames, int seed) {
        games.add(new GameBoard(seed, usernames));
        games.get(games.size() - 1).initializeBases();

        getState(GameBoard.gameBoardHolder.get(usernames[0]).currentPlayer);
    }

    public void checkIfGameOver(String username) throws IOException {
        if (GameBoard.gameBoardHolder.get(username).numberOfPlayers == 1) {

            //================================Call Lobby================================================================
            String FinishURL = "https://lobbyservice-dot-training-project-lab.appspot.com/FinishGame?username=";

            URL obj = new URL(FinishURL + username);
            HttpURLConnection httpCon = (HttpURLConnection) obj.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty("Content-Type", "json");
            httpCon.setRequestMethod("DELETE");

            System.out.println("Response code: " + httpCon.getResponseCode());
            //==========================================================================================================

            forfeit(username);
            GameBoard.gameBoardHolder.get(username).currentPlayer = null;
        }
    }

    public void forfeit(String username) {

        if (GameBoard.gameBoardHolder.get(username).currentPlayer.equals(username)) {

            endTurn(username);

            GameBoard.gameBoardHolder.get(username).players.remove(username);
            GameBoard.gameBoardHolder.get(username).numberOfPlayers--;
            for (int i = 0; i < bases.size(); i++) {
                if (bases.get(i).getPlayerBelongsTo().equals(username)) {
                    int xCoord = bases.get(i).xCoordinate;
                    int yCoord = bases.get(i).yCoordinate;

                    GameBoard.gameBoardHolder.get(username).gameTiles[xCoord][yCoord].setThing(null);
                    GameBoard.gameBoardHolder.get(username).gameTiles[xCoord][yCoord].setHasThing(false);

                    bases.remove(i);
                }
            }

            for (int i = 0; i < units.size(); i++) {
                if (units.get(i).getPlayerBelongsTo().equals(username)) {
                    int xCoord = units.get(i).xCoordinate;
                    int yCoord = units.get(i).yCoordinate;

                    GameBoard.gameBoardHolder.get(username).gameTiles[xCoord][yCoord].setThing(null);
                    GameBoard.gameBoardHolder.get(username).gameTiles[xCoord][yCoord].setHasThing(false);

                    units.remove(units.get(i));
                    i--;
                }
            }
            try {
                checkIfGameOver(GameBoard.gameBoardHolder.get(username).currentPlayer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getState(GameBoard.gameBoardHolder.get(username).currentPlayer);
    }


    public void endTurn(String username) {
        if (GameBoard.gameBoardHolder.get(username).currentPlayer.equals(username)) {
            int currentPlayerPostion = GameBoard.gameBoardHolder.get(username).players.indexOf(GameBoard.gameBoardHolder.get(username).currentPlayer);
            if (currentPlayerPostion == GameBoard.gameBoardHolder.get(username).numberOfPlayers - 1) {
                GameBoard.gameBoardHolder.get(username).currentPlayer = GameBoard.gameBoardHolder.get(username).players.get(0);
            } else {
                currentPlayerPostion++;
                GameBoard.gameBoardHolder.get(username).currentPlayer = GameBoard.gameBoardHolder.get(username).players.get(currentPlayerPostion);
            }
            for (int i = 0; i < units.size(); i++) {
                if (units.get(i).playerBelongsTo.equals(username)) {
                    units.get(i).resetMoves();
                    units.get(i).resetNumberOfAttacks();
                }
            }
            for (int i = 0; i < bases.size(); i++) {
                if (bases.get(i).getPlayerBelongsTo().equals(username)) {
                    bases.get(i).resetCreatableUnits();
                }
            }
        }
        getState(GameBoard.gameBoardHolder.get(username).currentPlayer);
    }

    //================API FUNCTIONS=====================================================================

    public void upgrade(int BaseID, String username) {
        if (GameBoard.gameBoardHolder.get(username).currentPlayer.equals(username)) {
            for (int i = 0; i < bases.size(); i++) {
                if (bases.get(i).getId() == BaseID && bases.get(i).getPlayerBelongsTo().equals(username)) {
                    bases.get(i).upgrade();
                }
            }
        }
        getState(GameBoard.gameBoardHolder.get(username).currentPlayer);
    }

    public void createUnit(int xCoord, int yCoord, UnitTypeEnum type, int baseID, String username) {
        if (GameBoard.gameBoardHolder.get(username).currentPlayer.equals(username)) {
            for (int i = 0; i < bases.size(); i++) {
                if (bases.get(i).getId() == baseID && bases.get(i).getPlayerBelongsTo().equals(username)) {
                    bases.get(i).createUnit(xCoord, yCoord, type, username);
                }
            }
        }
        getState(GameBoard.gameBoardHolder.get(username).currentPlayer);
    }

    public void move(int xCoord, int yCoord, int unitID, String username) {
        if (GameBoard.gameBoardHolder.get(username).currentPlayer.equals(username)) {
            for (int i = 0; i < units.size(); i++) {
                if (units.get(i).getId() == unitID && units.get(i).getPlayerBelongsTo().equals(username)) {
                    units.get(i).move(xCoord, yCoord, username);
                }
            }
        }
        getState(GameBoard.gameBoardHolder.get(username).currentPlayer);
    }

    public void attack(int xCoord, int yCoord, int unitID, String username) {
        if (GameBoard.gameBoardHolder.get(username).currentPlayer.equals(username)) {
            for (int i = 0; i < units.size(); i++) {
                if (units.get(i).getId() == unitID && units.get(i).getPlayerBelongsTo().equals(username)) {
                    units.get(i).attack(xCoord, yCoord, username);
                    try {
                        checkIfGameOver(username);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            getState(GameBoard.gameBoardHolder.get(username).currentPlayer);
        } catch (Exception e) {
            System.out.println("Cant't find the Game");
        }

    }

    public String getMoves(int unitID, String username) {
        if (GameBoard.gameBoardHolder.get(username).currentPlayer.equals(username)) {
            Position[] position = null;

            for (int i = 0; i < units.size(); i++) {
                if (units.get(i).getId() == unitID && units.get(i).getPlayerBelongsTo().equals(username)) {
                    position = units.get(i).getMoves(true, username);
                }
            }

            Positions positions = new Positions(position);
            Gson gson = new Gson();
            String json = gson.toJson(positions);

            System.out.println(json);
            return json;
        } else {
            return null;
        }
    }

    public String getAttacks(int unitID, String username) {
        if (GameBoard.gameBoardHolder.get(username).currentPlayer.equals(username)) {
            Position[] position = null;

            for (int i = 0; i < units.size(); i++) {
                if (units.get(i).getId() == unitID && units.get(i).getPlayerBelongsTo().equals(username)) {
                    position = units.get(i).getAttacks(username);
                }
            }

            Positions positions = new Positions(position);
            Gson gson = new Gson();
            String json = gson.toJson(positions);

            System.out.println(json);
            return json;
        } else {
            return null;
        }
    }

    public String getPlacement(int baseID, String username) {
        if (GameBoard.gameBoardHolder.get(username).currentPlayer.equals(username)) {
            Position[] position = null;

            for (int i = 0; i < bases.size(); i++) {
                if (bases.get(i).getId() == baseID && bases.get(i).getPlayerBelongsTo().equals(username)) {
                    position = bases.get(i).getPlacement();
                }
            }

            Positions positions = new Positions(position);
            Gson gson = new Gson();
            String json = gson.toJson(positions);

            System.out.println(json);
            return json;
        } else {
            return null;
        }
    }

    public String getState(String username) {
        if (GameBoard.gameBoardHolder.get(username).currentPlayer.equals(username)) {
            ArrayList<Base> filteredBases = new ArrayList<>();
            ArrayList<Unit> filteredUnits = new ArrayList<>();
            ArrayList<String> tempPlayers = GameBoard.gameBoardHolder.get(username).players;

            for (int i = 0; i < bases.size(); i++) {
                for (int j = 0; j < tempPlayers.size(); j++) {
                    if (bases.get(i).getPlayerBelongsTo().equals(tempPlayers.get(j))) {
                        filteredBases.add(bases.get(i));
                    }
                }
            }

            for (int i = 0; i < units.size(); i++) {
                for (int j = 0; j < tempPlayers.size(); j++) {
                    if (units.get(i).getPlayerBelongsTo().equals(tempPlayers.get(j))) {
                        filteredUnits.add(units.get(i));
                    }
                }
            }

            GameState state = new GameState(GameBoard.gameBoardHolder.get(username).players.toArray(new String[0]), filteredBases.toArray(new Base[0]), filteredUnits.toArray(new Unit[0]), GameBoard.gameBoardHolder.get(username).getTileTypes());
            state.setBoardHeight(GameBoard.getBoardHeight());
            state.setBoardWidth(GameBoard.getBoardWidth());
            state.setCurrentPlayer(GameBoard.gameBoardHolder.get(username).currentPlayer);

            Gson gson = new Gson();
            String json = gson.toJson(state);

            System.out.println(json);
            return json;
        } else {
            return null;
        }
    }
}
