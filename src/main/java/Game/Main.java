package Game;

public class Main {
    public static void main(String[] args) {

        System.out.println("=================================START=====================================================");
        //===================================================START======================================================

        String[] players = {"Bob", "Alice"};
        int seed = 1000;

//        String[] players2 = {"John", "Sarah", "Jenna"};
//        int seed2 = 2000;

        GameController.getInstance().start(players, seed);

//        GameController.getInstance().start(players2, seed2);
//        GameController.getInstance().getState(players2[0]);

        System.out.println("===========================GET PLACEMENT====================================================");
        //=============================================GET PLACEMENT====================================================

        GameController.getInstance().getPlacement(0, "Bob");
//        System.out.println(GameController.getInstance().getPlacement(0, "Alice"));
//        System.out.println(GameController.getInstance().getPlacement(2, "Peter"));

        System.out.println("=============================CREATE UNIT====================================================");
        //===============================================CREATE UNIT====================================================

        GameController.getInstance().createUnit(0,1,UnitTypeEnum.RANGED, 0, "Bob");

        System.out.println("============================GET MOVES=======================================================");
        //==============================================GET MOVES=======================================================

        GameController.getInstance().getMoves(2,"Bob");

        System.out.println("=============================MOVE===========================================================");
        //===============================================MOVE===========================================================

        GameController.getInstance().move(1,1,2, "Bob");

        System.out.println("=============================UPGRADE========================================================");
        //===============================================UPGRADE========================================================

        GameController.getInstance().upgrade(0,"Bob");

        System.out.println("=============================GET ATTACKS====================================================");
        //===============================================GET ATTACKS====================================================

        GameController.getInstance().getAttacks(2,"Bob");

        System.out.println("=========================END TURN===========================================================");
        //===========================================END TURN===========================================================

        GameController.getInstance().endTurn("Bob");

        System.out.println("=========================PLAYER 2 GET PLACEMENT=============================================");
        //===========================================PLAYER 2 GET PLACEMENT=============================================

        GameController.getInstance().getPlacement(1,"Alice");

        System.out.println("=========================PLAYER 2 CREATE UNIT===============================================");
        //===========================================PLAYER 2 CREATE UNIT===============================================

        GameController.getInstance().createUnit(2,1,UnitTypeEnum.MELEE, 1, "Alice");

        System.out.println("=============================GET ATTACKS====================================================");
        //===============================================GET ATTACKS====================================================

        GameController.getInstance().getAttacks(3,"Alice");

        System.out.println("=============================ATTACK=========================================================");
        //===============================================ATTACK=========================================================

        GameController.getInstance().attack(2,0,3,"Alice");

        System.out.println("=========================END TURN===========================================================");
        //===========================================END TURN===========================================================

        GameController.getInstance().endTurn("Alice");

        System.out.println("=============================GET ATTACKS====================================================");
        //===============================================GET ATTACKS====================================================

        GameController.getInstance().getAttacks(2,"Bob");

        System.out.println("=============================ATTACK=========================================================");
        //===============================================ATTACK=========================================================

        GameController.getInstance().attack(2,0,2,"Bob");


    }
}
