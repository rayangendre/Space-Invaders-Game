import processing.core.PImage;

import java.awt.*;

public class DontBreak extends Entity{
    public DontBreak(Point position, PImage image, WorldModel world) {
        super(position, image, world);
//        world.setOccupancyCell(position, this);
    }
}
