//Cloud begins here
import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Cloud extends GameObject{
    private double cloudX ;
    private double cloudY ;
    private final int originalY;
    private World world;
    private Vector<GameObject> clouds=GameSequence.getCloud();//we can refer to the vector in GameSequence here and modify it in this class ro avoid clustering of code in GameSequence
    private static int cloudIndex=0;//just helps in the reiteration of clouds --in update()

    public Cloud(int posX, int posY,Character ID, World world) {
        super(posX, posY ,Character.CLOUD ,world); //takes to parent constructor--creates a new GameObject
        this.world=world;
        cloudX=posX;
        cloudY=posY;
        originalY=posY;
    }

    //cloud position
    private int pos=0;
    @Override
    public void repaint(Graphics g) {
        //the actual combination for cloud
        g.setColor(Color.white);
        pos= (int)(cloudX+velX);
        g.fillOval(pos, (int) cloudY, 50, 60);
        g.fillOval(pos+15, (int)(cloudY-25), 70, 80);
        g.fillOval(pos+30, (int) (cloudY+30), 70, 50);
        g.fillOval(pos+60, (int) cloudY, 80, 60);
        g.fillOval(pos+50, (int) (cloudY-30), 60, 40);
        g.fillOval(pos +80, (int) (cloudY-20), 70, 60);
        g.fillOval(pos+80, (int) (cloudY+20), 70, 60);
        g.fillOval(pos+100, (int) cloudY, 70, 60);
    }

    Random random=new Random(1234);
    @Override
    public void update() {
        velX+=5;

        if(this.pos>PollutionCombat.getWIDTH()+60){
            clouds.set(cloudIndex,new Cloud(-60,originalY,Character.CLOUD,world));
            cloudIndex+=1;//move on to the next element
            if(cloudIndex>clouds.size()-1) {//if index> the vector size go back to the first element
                cloudIndex = 0;
            }
        }
    }


    //getters and setters
    private int getPos() {
        return pos;
    }

}
