package Game;

public class Main {
    public static void main(String[] args) {

        System.out.println("=================================START=====================================================");
        //===================================================START======================================================

        String[] players = {"Bob", "Alice", "Peter"};
        int seed = 1000;

        String[] players2 = {"John", "Sarah", "Jenna"};
        int seed2 = 2000;

        GameController.getInstance().start(players, seed);
        GameController.getInstance().getState(players[0]);

        GameController.getInstance().start(players2, seed2);
        GameController.getInstance().getState(players2[0]);

        System.out.println("=================================GET PLACEMENT==============================================");
        //=============================================GET PLACEMENT====================================================

        GameController.getInstance().getPlacement(0, "Bob");
//        System.out.println(GameController.getInstance().getPlacement(0, "Alice"));
//        System.out.println(GameController.getInstance().getPlacement(2, "Peter"));

        System.out.println("================================CREATE UNIT=================================================");
        //===============================================CREATE UNIT====================================================

        GameController.getInstance().createUnit(1,0,UnitTypeEnum.RANGED, 0, "Bob");
        GameController.getInstance().getState(players[0]);

        System.out.println("====================================GET MOVES===============================================");
        //==============================================GET MOVES=======================================================

        GameController.getInstance().getMoves(6,"Bob");

        System.out.println("====================================MOVE===================================================");
        //===============================================MOVE===========================================================

        GameController.getInstance().move(1,1,6, "Bob");
        GameController.getInstance().getState(players[0]);

        System.out.println("===============================GET ATTACKS==================================================");
        //===============================================GET ATTACKS====================================================

        GameController.getInstance().getAttacks(6,"Bob");

        System.out.println("===============================END GAME AND RESET===========================================");
        //===========================================END GAME AND RESETS================================================

        GameController.getInstance().forfeit("Alice");
        //GameController.getInstance().forfeit("Peter");

        GameController.getInstance().getState(players[0]);
    }
}
