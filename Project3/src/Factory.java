import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Factory {
    private static final String ASTEROID_KEY = "asteroid";
    private static final int TILESIZE = 32;

    public static Rocket createRocket(int y, int xG, int yG, ImageStore imageStore, WorldModel worldModel){
       return new Rocket(new Point(0, y*TILESIZE), new Point(xG*TILESIZE, yG*TILESIZE), imageStore.getImage("rocket"), worldModel);
    }

    public static Yaeger createYaeger(int x, int y, ImageStore imageStore, WorldModel worldModel){
        return new Yaeger(new Point(x*TILESIZE, y*TILESIZE), imageStore.getImage("yaeger"), worldModel);
    }

    public static A_Spawn createSpawnLeft(int x, int y, ImageStore imageStore, WorldModel worldModel){
        return new A_Spawn(new Point(x*TILESIZE, y*TILESIZE), imageStore.getImage("ASpawnLeft"), worldModel);
    }

    public static A_Spawn createSpawnRight(int x, int y, ImageStore imageStore, WorldModel worldModel){
        return new A_Spawn(new Point(x*TILESIZE, y*TILESIZE), imageStore.getImage("ASpawnRight"), worldModel);
    }

    public static A_Spawn createSpawnYaeger(int x, int y, ImageStore imageStore, WorldModel worldModel){
        return new A_Spawn(new Point(x*TILESIZE, y*TILESIZE), imageStore.getImage("yaegerSpawn"), worldModel);
    }

    public static AlienOne createAlien(int x, int y, int id, ImageStore imageStore, WorldModel worldModel){
        String ALIENONE_KEY = "alienOne";
        return new AlienOne(new Point(x * TILESIZE, y * TILESIZE), imageStore.getImage(ALIENONE_KEY), worldModel, id);
    }
    public static Gold createGold(int x, int y, ImageStore imageStore, WorldModel worldModel){
        return new Gold(new Point(x*TILESIZE, y*TILESIZE), imageStore.getImage("goal"), worldModel);
    }

    public static List<Asteroid> createAsteroids(ImageStore imageStore, WorldModel worldModel){
        List<Asteroid> asteroids = new ArrayList<>();
        //Level One
        asteroids.add(new Asteroid(new Point( TILESIZE, 5 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(TILESIZE, 8 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(2 * TILESIZE, 12 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(2 * TILESIZE, 11 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(3 * TILESIZE, 7 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(3 * TILESIZE, 10 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(4 * TILESIZE, 8 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(4 * TILESIZE, 11 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(4 * TILESIZE, 12 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(4 * TILESIZE, 13 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(5 * TILESIZE, 4 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(5 * TILESIZE, 5 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(5 * TILESIZE, 8 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(5 * TILESIZE, 10 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(6 * TILESIZE, 6 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(6 * TILESIZE, 10 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(6 * TILESIZE, 13 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(7 * TILESIZE, 13 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(8 * TILESIZE, 7 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(8 * TILESIZE, 13 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(9 * TILESIZE, 4 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(9 * TILESIZE, 9 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(9 * TILESIZE, 11 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(9 * TILESIZE, 13 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(10 * TILESIZE, 6 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(10 * TILESIZE, 9 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(10 * TILESIZE, 11 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(11 * TILESIZE, 10 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(13 * TILESIZE, 5 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(13 * TILESIZE, 8 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(13 * TILESIZE, 12 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(14 * TILESIZE, 10 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(16 * TILESIZE, 8 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(18 * TILESIZE, 5 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(18 * TILESIZE, 12 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));


        //right side
        asteroids.add(new Asteroid(new Point(20 * TILESIZE, 6 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(21 * TILESIZE, 11 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(22 * TILESIZE, 8 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(23 * TILESIZE, 5 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(24 * TILESIZE, 7 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(24 * TILESIZE, 9 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(24 * TILESIZE, 11 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(25 * TILESIZE, 9 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(25 * TILESIZE, 11 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(26 * TILESIZE, 4 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(26 * TILESIZE, 10 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(27 * TILESIZE, 7 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(27 * TILESIZE, 11 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(28 * TILESIZE, 5 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(28 * TILESIZE, 8 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(28 * TILESIZE, 12 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(29 * TILESIZE, 11 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(29 * TILESIZE, 4 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(30 * TILESIZE, 4 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(30 * TILESIZE, 5 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(30 * TILESIZE, 7 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(30 * TILESIZE, 10 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(30 * TILESIZE, 11 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(31 * TILESIZE, 7 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(31 * TILESIZE, 9 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(32 * TILESIZE, 10 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(32 * TILESIZE, 11 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(33 * TILESIZE, 5 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        asteroids.add(new Asteroid(new Point(33 * TILESIZE, 8 * TILESIZE), imageStore.getImage(ASTEROID_KEY), worldModel));
        return asteroids;

    }


}
