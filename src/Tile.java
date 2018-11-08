public class Tile {
    protected Thing thing;
    protected TileTypeEnum type;

    public void drawTile(){}

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public Thing getThing() {
        return thing;
    }
}
