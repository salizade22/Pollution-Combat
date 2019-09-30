import java.awt.*;
//this abstract class defines the architectural design of our GameObjects and stipulates some rules all objects should have

public abstract class GameObject{ //defines the objects in the game
    protected int x,y; //position on the screen
    protected double velX=0, velY=0; //defines the motion
    protected Character ID;
    World world;

    public GameObject(int posX,int posY , Character ID,World world){ //velocity
        this.x=posX;
        this.y=posY;
        this.ID=ID;
        this.world=world;
    }

    public abstract void repaint(Graphics g);//draws everything//every object draws something new after 60 FPS
    public abstract void update(); //every object updates


    //getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }


    public Character getID() {
        return ID;
    }

    public void setID(Character ID) {
        this.ID = ID;
    }
}
