import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.awt.*;

public class WorldModel {

    private final ImageStore imageStore;

    public SpaceShip spaceShip;

    public PImage background;

    private Entity occupancy[][];

    private PathingStrategy ASTAR = new AStarPathingStrategy();
    private FindPath findPathAStar;

    private PathingStrategy SINGLESTEP = new SingleStepPathingStrategy();
    private FindPath findPathSingleStep;

    private PathingStrategy Dijisktra = new DijkstraPathingStrategy();
    private FindPath findPathDij;

    private int aliensKilled = 0;

    private List<Laser> laserList;
    private List<Asteroid> asteroids;
    private AlienOne[] alienOnes;
    private final List<Point> goals;
    private final List<Point> returnGoals;
    private final List<A_Spawn> spawns;
    private Boolean[] found;
    private Yaeger[] yaegerList;
    private List<Rocket> rocketList;

    private long nextTime;
    private A_Spawn yaegerSpawn;

    private final int TILESIZE = 32;
    private final String SPACESHIP_KEY = "spaceship";
    private final String ASTEROID_KEY = "asteroid";

    public WorldModel(ImageStore imageStore, int numRow, int numCol) {
        this.occupancy = new Entity[numRow][numCol];
        for (int row = 0; row < MainWorld.ROWS; row++) {
            Arrays.fill(this.occupancy[row], null);
        }
        this.imageStore = imageStore;
        this.spaceShip = new SpaceShip(new Point(12 * TILESIZE, 17 * TILESIZE), imageStore.getImage(SPACESHIP_KEY), this);
        setOccupancyCell(spaceShip.getPosition(), spaceShip);
        this.background = this.imageStore.getImage("background");
        this.laserList = new ArrayList<>();
        this.asteroids = new ArrayList<>();
        this.alienOnes = new AlienOne[4];
        this.goals = new ArrayList<>();
        this.spawns = new ArrayList<>();
        this.returnGoals = new ArrayList<>();
        this.rocketList = new ArrayList<>();
        this.found = new Boolean[4];
        this.yaegerList = new Yaeger[1];
        createAsteroids();
        createAliensOne();
        createGold();
        findPathAStar = new FindPath(ASTAR, occupancy);
        findPathSingleStep = new FindPath(SINGLESTEP, occupancy);
        findPathDij = new FindPath(Dijisktra, occupancy);
        nextTime = System.currentTimeMillis();

    }

    public void setOccupancyCell(Point pos, Entity entity) {
        this.occupancy[pos.y/TILESIZE][pos.x/TILESIZE] = entity;
    }

    public Entity getOccupancyCell(Point pos) {
        return this.occupancy[pos.y][pos.x];
    }

    public void action(){
        laserAction();
//        alienAction();
        moveAlienA(0);
        moveAlienA(1);
        moveAlienD(2);
        moveAlienD(3);
        rocketAction();
        moveYaeger();
        reSpawn();




    }


    private void rocketAction(){
        for (Iterator<Rocket> rocketIterator = rocketList.iterator(); rocketIterator.hasNext();){
            Rocket rocket = rocketIterator.next();
            rocket.move();
            if (rocket.position.equals(rocket.getGoal())){
                if (!(occupancy[rocket.y()/TILESIZE][rocket.x()/TILESIZE] instanceof DontBreakRocket)){
                    if (occupancy[rocket.y() / 32][rocket.x() / 32] instanceof Asteroid){
                        asteroids.remove((Asteroid)occupancy[rocket.y() / 32][rocket.x() / 32]);
                    }
                    if (occupancy[rocket.y() / 32][rocket.x() / 32] instanceof AlienOne){
                        AlienOne alien = (AlienOne) occupancy[rocket.y() / 32][rocket.x() / 32];
                        alienOnes[alien.getId()] = null;
                        aliensKilled++;
                    }
                    if (occupancy[rocket.y() / 32][rocket.x() / 32] instanceof Yaeger){
                        yaegerList[0] = null;
                        aliensKilled++;
                    }
                    occupancy[rocket.y() / 32][rocket.x() / 32] = null;
                    occupancy[rocket.y() / 32][rocket.x() / 32] = new Explosion(rocket.getPosition(), imageStore.getImage("explosion"), this);
                }
                rocketIterator.remove();
            }
        }
    }

    private void laserAction(){
        for (Iterator<Laser> laserIterator = laserList.iterator(); laserIterator.hasNext();) {
            Laser laser = laserIterator.next();
            if (laser.y() < TILESIZE || laser.y() > MainWorld.HEIGHT){
                laserIterator.remove();
                setOccupancyCell(laser.getPosition(), null);
            }
            for (Iterator<Asteroid> asteroidIterator = asteroids.iterator(); asteroidIterator.hasNext();){
                Asteroid asteroid = asteroidIterator.next();
                if ((laser.x() == asteroid.x() && (laser.y()-TILESIZE) == asteroid.y())
                || (laser.x() == asteroid.x() && laser.y() == asteroid.y())){
                    laserIterator.remove();
                }
            }
            //if alien position is equal to laser position, then delete both
            for (int i = 0; i < alienOnes.length; i++){
                AlienOne alienOne = alienOnes[i];
                if (alienOne != null) {
                    if ((laser.x() == alienOne.x() && laser.y()-TILESIZE == alienOne.y())
                    || (laser.x() == alienOne.x() && laser.y() == alienOne.y())) {
                        aliensKilled++;
                        setOccupancyCell(laser.getPosition(), null);
                        setOccupancyCell(alienOne.getPosition(), null);
                        occupancy[alienOne.y() / 32][alienOne.x() / 32] = new Explosion(alienOne.getPosition(), imageStore.getImage("explosion"), this);
                        laserIterator.remove();
                        alienOnes[i] = null;
                    }
                }
            }
            if (yaegerList[0] != null) {
                if ((laser.x() == yaegerList[0].x() && laser.y()-TILESIZE == yaegerList[0].y())
                || (laser.x() == yaegerList[0].x() && laser.y() == yaegerList[0].y())) {
                    setOccupancyCell(yaegerList[0].getPosition(), null);
                    occupancy[yaegerList[0].y() / 32][yaegerList[0].x() / 32] = new Explosion(yaegerList[0].getPosition(), imageStore.getImage("explosion"), this);
                    yaegerList[0] = null;
                    laserIterator.remove();
                    aliensKilled++;
                }
            }
            laser.fire();
        }
    }


    private void createAsteroids(){
        asteroids = Factory.createAsteroids(imageStore, this);
    }
    private void createGold(){
        Gold one = Factory.createGold(3, 11, imageStore, this);
        Gold two = Factory.createGold(10,10, imageStore, this);
        Gold three = Factory.createGold(25, 10, imageStore, this);
        Gold four = Factory.createGold(31, 10, imageStore, this);

        goals.add(new Point(3, 12));
        goals.add(new Point(9,10));
        goals.add(new Point(24, 10));
        goals.add(new Point(31, 11));

        returnGoals.add(new Point(4,3));
        returnGoals.add(new Point(5,3));
        returnGoals.add(new Point(29,3));
        returnGoals.add(new Point(30,3));

        found[0] = false;
        found[1] = false;
        found[2] = false;
        found[3] = false;

    }

    private void createAliensOne(){
        alienOnes[0] = Factory.createAlien(4, 3, 0, imageStore, this);
        alienOnes[1] = Factory.createAlien(6, 3, 1, imageStore, this);
        alienOnes[2] = Factory.createAlien(28, 3, 2, imageStore, this);
        alienOnes[3] = Factory.createAlien(31, 3, 3, imageStore, this);

        spawns.add(Factory.createSpawnLeft(4, 2, imageStore, this));
        spawns.add(Factory.createSpawnRight(5, 2, imageStore, this));
        spawns.add(Factory.createSpawnLeft(29, 2, imageStore, this));
        spawns.add(Factory.createSpawnRight(30, 2, imageStore, this));
        yaegerSpawn = Factory.createSpawnYaeger(16, 2, imageStore, this);
        yaegerList[0] = Factory.createYaeger(16, 3, imageStore, this);

    }

    public void createRocket(int x, int y){
        if (withinBoundsRocket(x*TILESIZE,y*TILESIZE)){
            rocketList.add(Factory.createRocket(y, x, y, imageStore, this));
        }
    }

    public boolean withinBoundsRocket(int x, int y){
        return x >= 0 && x < MainWorld.WIDTH && y < (16 * TILESIZE);
    }

    private void moveAlienA(int index){
        if(!found[index] && alienOnes[index] != null) {
            List<APoint> path = alienPath(new APoint(alienOnes[index].getPosition().x / TILESIZE, alienOnes[index].getPosition().y / TILESIZE), new APoint(goals.get(index).x, goals.get(index).y));
            if (!path.isEmpty()) {
                alienOnes[index].moveTo(new Point(path.get(1).x, path.get(1).y));
            }
            if ((alienOnes[index].x()) <= ((goals.get(index).x * TILESIZE) + TILESIZE)
                    && (alienOnes[index].x()) >= ((goals.get(index).x * TILESIZE) - TILESIZE)
                    && (alienOnes[index].y()) <= (goals.get(index).y * TILESIZE)+TILESIZE
            && (alienOnes[index].y()) >= (goals.get(index).y * TILESIZE)-TILESIZE){

                found[index] = true;
            }
        }else if (found[index] && alienOnes[index] != null){
            List<APoint> path = alienPath(new APoint(alienOnes[index].getPosition().x / TILESIZE, alienOnes[index].getPosition().y / TILESIZE), new APoint((returnGoals.get(index).x), (returnGoals.get(index).y)));

            if (!path.isEmpty()){
                alienOnes[index].moveTo(new Point(path.get(1).x, path.get(1).y));
            }
            if ((alienOnes[index].x()) <= (returnGoals.get(index).x * TILESIZE) + TILESIZE
                    && (alienOnes[index].x()) >= (returnGoals.get(index).x * TILESIZE) - TILESIZE
                    && (alienOnes[index].y()) <= (returnGoals.get(index).y * TILESIZE) + TILESIZE
                    &&(alienOnes[index].y()) >= (returnGoals.get(index).y * TILESIZE) - TILESIZE
            ){
                found[index] = false;
            }
        }
    }

    private void moveAlienD(int index){
        if(!found[index] && alienOnes[index] != null) {
            List<APoint> path = dijPath(new APoint(alienOnes[index].getPosition().x / TILESIZE, alienOnes[index].getPosition().y / TILESIZE), new APoint(goals.get(index).x, goals.get(index).y));
            if (!path.isEmpty()) {
                alienOnes[index].moveTo(new Point(path.get(1).x, path.get(1).y));
            }
            if ((alienOnes[index].x()) <= ((goals.get(index).x * TILESIZE) + TILESIZE)
                    && (alienOnes[index].x()) >= ((goals.get(index).x * TILESIZE) - TILESIZE)
                    && (alienOnes[index].y()) <= (goals.get(index).y * TILESIZE)+TILESIZE
                    && (alienOnes[index].y()) >= (goals.get(index).y * TILESIZE)-TILESIZE){

                found[index] = true;
            }
        }else if (found[index] && alienOnes[index] != null){
            List<APoint> path = dijPath(new APoint(alienOnes[index].getPosition().x / TILESIZE, alienOnes[index].getPosition().y / TILESIZE), new APoint((returnGoals.get(index).x), (returnGoals.get(index).y)));

            if (!path.isEmpty()){
                alienOnes[index].moveTo(new Point(path.get(1).x, path.get(1).y));
            }
            if ((alienOnes[index].x()) <= (returnGoals.get(index).x * TILESIZE) + TILESIZE
                    && (alienOnes[index].x()) >= (returnGoals.get(index).x * TILESIZE) - TILESIZE
                    && (alienOnes[index].y()) <= (returnGoals.get(index).y * TILESIZE) + TILESIZE
                    &&(alienOnes[index].y()) >= (returnGoals.get(index).y * TILESIZE) - TILESIZE
            ){
                found[index] = false;
            }
        }
    }

    private void moveYaeger(){
        if (yaegerList[0] != null) {
            List<APoint> path = new ArrayList<>();
            APoint goal = new APoint(spaceShip.x() / TILESIZE, spaceShip.y() / TILESIZE);
            APoint start = new APoint(yaegerList[0].x() / TILESIZE, yaegerList[0].y() / TILESIZE);
            if (findPathSingleStep.findGoalSingleStep(start, goal, path)) {
                Point moveTo = new Point(path.get(0).x, path.get(0).y);
                yaegerList[0].moveTo(moveTo);
            }
        }
    }

    private void reSpawn(){
        String ALIENONE_KEY = "alienOne";
        if (alienOnes[0] == null){
            alienOnes[0] = Factory.createAlien(4, 3, 0, imageStore, this);
            found[0] = false;
        }
        if (alienOnes[1] == null){
            alienOnes[1] = Factory.createAlien(6, 3, 1, imageStore, this);
            found[1] = false;
        }
        if (alienOnes[2] == null){
            alienOnes[2] = Factory.createAlien(28, 3, 2, imageStore, this);
            found[2] = false;
        }
        if (alienOnes[3] == null){
            alienOnes[3] = Factory.createAlien(31, 3, 3, imageStore, this);
            found[3] = false;
        }
        if (yaegerList[0] == null){
            yaegerList[0] = Factory.createYaeger(16, 3, imageStore, this);
        }
    }

    private List<APoint> alienPath(APoint start, APoint goal){

        List<APoint> path = new ArrayList<>();
        findPathAStar.findGoalAStar(start, goal, path);
        return path;
    }

    private List<APoint> dijPath(APoint start, APoint goal){
        List<APoint> path = new ArrayList<>();
        findPathDij.findGoalAStar(start, goal, path);
        return path;
    }

    public SpaceShip getSpaceShip() {
        return spaceShip;
    }

    public List<Laser> getLaserList() {
        return laserList;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public AlienOne[] getAlienOnes() {
        return alienOnes;
    }

    public Entity[][] getOccupancy() {
        return occupancy;
    }

    public List<Rocket> getRocketList() {
        return rocketList;
    }

    public int getAliensKilled() {
        return aliensKilled;
    }

    public ImageStore getImageStore() {
        return imageStore;
    }
}
