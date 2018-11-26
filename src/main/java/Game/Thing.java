package Game;

public class Thing {
    protected Tile tile;
    protected int xCoordinate, yCoordinate;
    protected int health;
    protected int id;
    protected static transient int counter = 0;

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getId() {
        return id;
    }
}
