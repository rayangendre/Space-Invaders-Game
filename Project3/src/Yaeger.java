import processing.core.PImage;

import java.awt.*;

public class Yaeger extends Entity{
    private int moveCount;
    public Yaeger(Point position, PImage image, WorldModel world) {
        super(position, image, world);
    }

    public void moveTo(Point point) {
        Point oldPos = position;
        moveCount++;
        if (moveCount >= 5){
            if (world.getOccupancyCell(point) == null){
                world.setOccupancyCell(oldPos, null);
                this.position = new Point(point.x * TILESIZE, point.y * TILESIZE);
                world.setOccupancyCell(this.position, this);
                moveCount = 0;
            }
        }
    }
}
