package Game;

public class Tile {
    protected Thing thing;
    protected boolean hasThing;
    protected TileTypeEnum type;
    protected boolean isTarget = false;

    public void drawTile(){}

    public void setThing(Thing thing) {
        this.thing = thing;
        hasThing = true;
    }

    public void setHasThing(boolean hasThing) {
        this.hasThing = hasThing;
    }

    public Thing getThing() {
        return thing;
    }
}
