public class Thing {
    protected Tile tile;
    protected int xCoordinate, yCoordinate;
    protected int health;

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void printThing(){}
}
