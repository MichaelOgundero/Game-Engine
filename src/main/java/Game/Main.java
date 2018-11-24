package Game;

public class Main {
    public static void main(String[] args) {

        //==============================================================================================================
        String[] players = {"Bob", "Alice", "Peter"};
        int seed = 1000;
        GameController.getInstance().start(players, seed);
        GameController.getInstance().getState();
        //==============================================================================================================

        GameController.getInstance().getPlacement(0, "Bob");
//        System.out.println(GameController.getInstance().getPlacement(0, "Alice"));
//        System.out.println(GameController.getInstance().getPlacement(2, "Peter"));

        //==============================================================================================================

//        GameController.getInstance().createUnit(5,5,UnitTypeEnum.RANGED, 0, "Bob");
//        GameController.getInstance().getState();
    }
}
