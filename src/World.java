import java.awt.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

//This class just takes all gameObjects in the game, updates and repaints them---that's it
//this class deals with creating the actual world---THE objects
public class World{
    private Vector<Vector<GameObject>> gameObjects;//all the gameObjects Vectors in our world
    private boolean rightPressed=false;//is right presse
    private boolean leftPressed=false; //helps to detect buttonPress
    private static boolean pause=false;

    public World(){//what will a typical world have?
        gameObjects=new Vector<Vector<GameObject>>();//all the gameObjects

        //just add fish--the easiest place to add a GameObject that does not need its own class and is always in the game
        Random random=new Random();
        int fishmod=0;
        for(int i=0; i<70;i++){
            fishV.add(new Fish(PollutionCombat.getWIDTH()+200+fishmod,i));
            fishmod+=random.nextInt(30);
        }
    }

    public void update(){ //what happens after each update
        if(!pause) {
            for (Vector<GameObject> game : gameObjects) {
                for (GameObject obj : game) {
                    obj.update();
                }
            }
        }
    }


    public void repaint(Graphics g){//repaint
        for (Vector<GameObject> game : gameObjects) {
            for (GameObject obj : game) {
                obj.repaint(g); //render every object in the game
            }
        }
        drawRain(g); //rain
        drawFish(g); //fish
    }

    //draws fish
    private Vector<Fish> fishV = new Vector<>();//a vector of Fish to be used in our game
    private void drawFish(Graphics gOri) {
        Graphics2D g = (Graphics2D) gOri;
        //DRAW FISH
        Iterator<Fish> iterator = fishV.iterator();
        while (iterator.hasNext()) {
            Fish fish = iterator.next();
            if(!pause)
                fish.update();
            fish.repaint(g);
        }
    }

    //draws rain
    private Vector<Rain> rainV = new Vector<>();//a vector of Rain
    private static boolean raining=true;
    private void drawRain(Graphics g0) {
        //Rain
        Graphics2D g = (Graphics2D) g0;
        float mRainWidth=2;//width of the rain
        g.setStroke(new BasicStroke(mRainWidth));//sets the width of the rain
        g.setColor(Color.white);

        //DRAW RAIN
        Iterator<Rain> iterator = rainV.iterator();
        while (iterator.hasNext()) {
            Rain rain = iterator.next();
            if(!pause)
                rain.update();
            rain.repaint(g);
        }

        //CREATE NEW RAIN
        if (raining) {//if its still raining
            rainV.add(new Rain(0));
        }

    }

    //when do we add certain objects
    public void addObject(Vector<GameObject> event){ gameObjects.add(event); }
    //getters and setters-- check and set the status of buttons pressed in the game
    public boolean isRightPressed() { return rightPressed; }

    public void setRightPressed(boolean rightPressed) { this.rightPressed = rightPressed; }

    public boolean isLeftPressed() { return leftPressed; }

    public void setLeftPressed(boolean leftPressed) { this.leftPressed = leftPressed; }

    public Vector<Vector<GameObject>> getGameObjects() { return gameObjects; }

    public static void setPause(boolean pause) { World.pause = pause;}
}
