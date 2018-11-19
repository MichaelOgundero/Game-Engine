package Game;

public class Obstacle extends Tile {

    public Obstacle() {
        this.type = TileTypeEnum.OBSTACLE;
    }

    @Override
    public void drawTile(){
        System.out.print(" O ");
    }
}
