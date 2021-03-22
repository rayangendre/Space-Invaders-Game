import processing.core.PImage;

import java.awt.*;

public class Entity {
    protected Point position;
    protected PImage image;
    protected final int TILESIZE = 32;
    protected WorldModel world;

    public Entity(Point position, PImage image, WorldModel world) {
        this.position = position;
        this.image = image;
        this.world = world;
        world.setOccupancyCell(new Point(position.x, position.y), this);
    }

    public PImage getImage() {
        return image;
    }

    public int x(){
        return position.x;
    }

    public int y(){
        return position.y;
    }

    public Point getPosition() {
        return position;
    }
}
