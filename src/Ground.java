public class Ground extends Tile {

    public Ground() {
        this.type = TileTypeEnum.GROUND;
    }

    @Override
    public void drawTile(){
        if(this.thing == null){
            System.out.print(" G ");
        }else{
            this.thing.printThing();
        }
    }
}
