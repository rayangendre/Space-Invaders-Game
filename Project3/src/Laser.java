

import processing.core.PImage;

import java.awt.*;

public class Laser extends Entity{
    private final int direction;

    public Laser(int x, int y, PImage laserImage, int direction, WorldModel worldModel) {
        super(new Point(x,y + (direction * 32)), laserImage, worldModel);
        this.direction = direction;
        world.setOccupancyCell(this.position, null);
    }

    public void fire(){
            this.position.y += (direction * TILESIZE);
    }


    public PImage getLaserImage() {
        return image;
    }
}
