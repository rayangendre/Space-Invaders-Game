import processing.core.PImage;

import java.awt.*;

public class SpaceShip extends Entity {

    public SpaceShip(Point position, PImage image, WorldModel worldModel) {
        super(position, image, worldModel);
    }

    public void moveLeft(){
        Point oldPos = position;
        if (withinBounds(this.x() - TILESIZE, this.y())){
            world.setOccupancyCell(oldPos, null);
            position.x -= TILESIZE;
            world.setOccupancyCell(this.position, this);
        }

    }

    public void moveRight(){
        Point oldPos = position;
        if (withinBounds(this.x() + TILESIZE, this.y())){
            world.setOccupancyCell(oldPos, null);
            position.x += TILESIZE;
            world.setOccupancyCell(this.position, this);
        }

    }

    public void moveUp(){
        Point oldPos = position;
        if (withinBounds(this.x(), this.y() - TILESIZE)){
            world.setOccupancyCell(oldPos, null);
            position.y -= TILESIZE;
            world.setOccupancyCell(this.position, this);
        }

    }

    public void moveDown() {
        Point oldPos = position;
        if (withinBounds(this.x(), this.y() + TILESIZE)) {
            world.setOccupancyCell(oldPos, null);
            position.y += TILESIZE;
            world.setOccupancyCell(this.position, this);
        }
    }


    public Laser shootLaser(PImage laserImage){
        Laser laser = new Laser(x(), y(), laserImage, -1, world);
        return laser;
    }

    public boolean withinBounds(int x, int y){
        return x >= 0 && x < MainWorld.WIDTH
                && y < MainWorld.HEIGHT && y >= (16 * TILESIZE);
    }
}
