import processing.core.PApplet;

import java.awt.*;

public class MainWorld extends PApplet {
    protected static final int WIDTH = 1120;
    protected static final int HEIGHT = 640;
    protected static final int TILESIZE = 32;
    protected static final int COLUMNS = WIDTH / TILESIZE;
    protected static final int ROWS = HEIGHT / TILESIZE;

    private ImageStore imageStore;
    private WorldModel worldModel;
    private WorldView view;

    private long nextTime;
    private final int TIMER_PERIOD = 100;

    public void settings(){
        size(WIDTH, HEIGHT);
    }

    public void setup(){
        nextTime = System.currentTimeMillis() + TIMER_PERIOD;
        imageStore = new ImageStore();
        createImageStore();
        worldModel = new WorldModel(imageStore, ROWS, COLUMNS);
        view = new WorldView(this, COLUMNS, ROWS, worldModel, new Background(imageStore.getImage("background")));
    }

    /**
     * Creates the imageStore by reading in the imagelist file
     */
    public void createImageStore(){
        String[] lines = loadStrings("imagelist");
        for (int i = 0; i< lines.length; i++){
            if (lines[i].length() > 0 ){
                String[] words = lines[i].split(",");
                imageStore.addImage(words[0], loadImage(words[1]));
            }
        }

    }



    /**
     * the draw function to repeat the drawing to the image box
     */
    public void draw(){
        //gets the current time of the program
        long currentTime = System.currentTimeMillis();
        view.drawWorld();

        // if the tick speed has been reached, go through events
        if (currentTime >= nextTime) {
            worldModel.action();
            //increment the next time to be reached
            nextTime = currentTime + TIMER_PERIOD;
        }
        fill(255, 255, 255);
        textSize(13);
        text("Aliens killed: " + worldModel.getAliensKilled(), 950, 580);
        text("Arrow keys to move", 50, 580);
        text("Space Bar to Shoot", 50, 600);
        text("Click to call rocket strike", 50, 620);
    }


    public void keyPressed() {
        //pass the world object to each call to move
            SpaceShip spaceShip = worldModel.getSpaceShip();
            if (key == CODED){
                if (keyCode == LEFT) {
                    spaceShip.moveLeft();
                } else if (keyCode == RIGHT) {
                    spaceShip.moveRight();
                } else if (keyCode == UP){
                    spaceShip.moveUp();
                } else if (keyCode == DOWN){
                    spaceShip.moveDown();
                }
            }
             else if (key == ' '){
                 if (worldModel.getOccupancyCell(new Point(spaceShip.x()/TILESIZE, (spaceShip.y()- TILESIZE)/TILESIZE)) != null){

                 }else {
                     worldModel.getLaserList().add(spaceShip.shootLaser(imageStore.getImage("laser")));
                 }
            }

    }

    public void mousePressed(){
        worldModel.createRocket(mouseX/TILESIZE, mouseY/TILESIZE);
    }


    public static void main(String[] args) {
        PApplet.main("MainWorld");
    }


}
