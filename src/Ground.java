public class Ground extends Tile {

    String printS;

    public Ground() {
        this.type = TileTypeEnum.GROUND;
    }

    @Override
    public void drawTile() {
        if (this.thing == null) {
            printS = " G ";
        } else if (this.isTarget == true) {
            printS = " T ";
        } else {
            this.thing.printThing();
        }

        if(printS != null){
            System.out.print(printS);
        }
    }
}
