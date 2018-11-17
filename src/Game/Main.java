package Game;

public class Main {
    public static void main(String[] args) {
        String[] players = {"Peter", "Jason", "Abernafy"};
        GameController.getInstance().start(players,5);
    }
}
