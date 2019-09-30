import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.Vector;

//a model of the Aeroplane with different motion sequence
public class AlienShip extends Aeroplane{
    private Image submarine=new ImageIcon("ufo.png").getImage();
    private static Vector<GameObject> alienShip=GameSequence.getAlienShip();
    private int indexSub=0;
    private int counter=0;


    public AlienShip(int posX, int posY, int currentPlane, Character ID, World world, Tank tank){
        super(posX, posY, currentPlane, ID, world,tank);
        indexSub=currentPlane;
    }

    @Override
    public void repaint(Graphics g) {
        for(Bullet b: Tank.bullets){
            if(b.y>this.y-50&&b.y<this.y+50&b.x>this.x-90&&b.x<this.x+90){//means we've been hit
                destroyed=true;
                b.setHitSomething(true);
            }
        }

        if(!destroyed) {
            g.drawImage(submarine, x, y, null);

            try {
                for (Toxin t : toxins) {
                    t.repaint(g);
                }
            } catch (ConcurrentModificationException e) {
                //do nothing
            }
        }
        else {
            if(destroyed&&counter==0){
                PollutionCombat.setCurrentScore(PollutionCombat.getCurrentScore()+1);
                counter++;
            }
        }


    }

    Random random=new Random(120);

    @Override
    public void update() {
        int leftorRight=random.nextInt(2);
        if(y==random.nextInt(150)) {
            velY=0;
            if(leftorRight==1) velX=random.nextInt(5);
            else velX=-random.nextInt(5);
        }
        else{
            velY= 2;
        }
        x+=velX;
        y+=velY;

        if(this.y>=PollutionCombat.getHEIGHT()-350||isDestroyed()&&this.counter==0){
            this.alienShip.set(indexSub,new AlienShip(random.nextInt(PollutionCombat.getWIDTH()), -random.nextInt(500),indexSub,Character.ALIENSHIP,this.world,this.tank));
            counter+=1;
        }

        //toxins
        for(Toxin t:toxins) {
            t.update();
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }


}
