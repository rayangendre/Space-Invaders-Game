import processing.core.PApplet;

import java.util.Arrays;

public class WorldView {
    private final PApplet screen;
    private final int COLUMNS;
    private final int ROWS;
    private final WorldModel world;
    private final int TILESIZE = 32;
    private Background background[][];

    public WorldView(PApplet screen, int column, int rows, WorldModel world, Background defaultBackground) {
        this.screen = screen;
        this.COLUMNS = column;
        this.ROWS = rows;
        this.world = world;
        this.background = new Background[ROWS][COLUMNS];

        for (int row = 0; row < ROWS; row++) {
            Arrays.fill(this.background[row], defaultBackground);
        }
    }

    public void drawWorld(){
        drawBackground();
        drawEntities();
    }

    public void drawBackground(){
        this.screen.background(0);
        this.screen.fill(150);
        drawGridBackGround();
        this.screen.fill(64);

    }

    public void drawEntities(){
        drawFromGrid();
        drawLasers();
        drawRockets();


    }

    private void drawAsteroids(){
        for (Asteroid asteroid: world.getAsteroids()){
            this.screen.image(asteroid.getImage(), asteroid.x(), asteroid.y());
        }
    }

    private void drawAliensOne(){
        for (AlienOne alienOne: world.getAlienOnes()){
            this.screen.image(alienOne.getImage(), alienOne.x(), alienOne.y());
        }
    }

    private void drawLasers(){
        for (Laser laser : world.getLaserList()) {
            this.screen.image(laser.getLaserImage(), laser.x(), laser.y());
        }
    }

    private void drawRockets(){
        for (Rocket rocket: world.getRocketList()){
            this.screen.image(rocket.getImage(), rocket.x(), rocket.y());
        }
    }

    public void drawFromGrid(){
        Entity occ [][] = world.getOccupancy();
        for (int row = 0; row < (MainWorld.ROWS); row ++){
            for(int col = 0; col < (MainWorld.COLUMNS); col++) {
                if (occ[row][col] != null) {
                    if (occ[row][col] instanceof Laser){

                    }else {
                        this.screen.image(occ[row][col].getImage(), col * TILESIZE, row * TILESIZE);
                    }
                }
                //if it is an explosion, it drew the image then removes it
                if (occ[row][col] instanceof Explosion){
                    occ[row][col] = null;
                }
            }
        }
    }

    private void drawGridBackGround(){
        for (int row = 0; row < (ROWS); row ++){
            for(int col = 0; col < (COLUMNS); col++) {
                this.screen.image(background[row][col].getImage(), col*32, row*32);

            }
        }
    }


}
