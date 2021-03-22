import processing.core.PImage;

import java.awt.*;

public class AlienOne extends Entity {
    private int moveCount;
    private int id;

    public AlienOne(Point position, PImage image, WorldModel worldModel, int id) {
        super(position, image, worldModel);
        this.moveCount = 0;
        this.id = id;
    }

    public void moveTo(Point point){
        Point oldPos = position;
        moveCount++;
        if (alienWithinBounds() && moveCount >= 5){
            if (world.getOccupancyCell(point) == null){
                world.setOccupancyCell(oldPos, null);
                this.position = new Point(point.x * TILESIZE, point.y * TILESIZE);
                world.setOccupancyCell(this.position, this);
                moveCount = 0;
            }
        }
    }

    public boolean alienWithinBounds(){
        return this.position.x - TILESIZE >= 0 && this.position.x + TILESIZE < MainWorld.WIDTH;
    }

    public int getId() {
        return id;
    }
}
