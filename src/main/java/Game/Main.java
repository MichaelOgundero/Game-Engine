package Game;

public class Main {
    public static void main(String[] args) {

        //===================================================START======================================================
        String[] players = {"Bob", "Alice", "Peter"};
        int seed = 1000;
        GameController.getInstance().start(players, seed);
        GameController.getInstance().getState();

        System.out.println("=================================GET PLACEMENT==============================================");
        //=============================================GET PLACEMENT====================================================

        GameController.getInstance().getPlacement(0, "Bob");
//        System.out.println(GameController.getInstance().getPlacement(0, "Alice"));
//        System.out.println(GameController.getInstance().getPlacement(2, "Peter"));

        System.out.println("================================CREATE UNIT=================================================");
        //===============================================CREATE UNIT====================================================

        GameController.getInstance().createUnit(1,0,UnitTypeEnum.RANGED, 0, "Bob");
        GameController.getInstance().getState();

        System.out.println("====================================GET MOVES===============================================");
        //==============================================GET MOVES=======================================================

        GameController.getInstance().getMoves(3,"Bob");

        System.out.println("====================================MOVE===================================================");
        //===============================================MOVE===========================================================

        GameController.getInstance().move(1,1,3, "Bob");
        GameController.getInstance().getState();

        System.out.println("===============================GET ATTACKS==================================================");
        //===============================================GET ATTACKS====================================================

        GameController.getInstance().getAttacks(3,"Bob");

        GameController.getInstance().endTurn("Bob");
        GameController.getInstance().getState();
    }
}
