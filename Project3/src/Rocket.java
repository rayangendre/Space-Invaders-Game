import processing.core.PImage;

import java.awt.*;

public class Rocket extends Entity{
    private Point goal;
    public Rocket(Point position, Point goal, PImage image, WorldModel world) {
        super(position, image, world);
        world.setOccupancyCell(this.position, null);
        this.goal = goal;
    }

    public void move(){
        this.position.x += TILESIZE;
    }

    public Point getGoal() {
        return goal;
    }
}
