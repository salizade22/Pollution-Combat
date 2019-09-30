import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Vector;

public class Submarine extends Ship{
    private Image submarine=new ImageIcon("submarine.png").getImage();
    private static Vector<GameObject> sub=GameSequence.getSub();
    private int indexSub=0;

    public Submarine(int posX, int posY,int index, Character ID, Tank tank,World world) {
        super(posX, posY,index, ID,tank,world);
        this.ID=Character.SUBMARINE;
        indexSub=index;
    }

    @Override
    public void repaint(Graphics g) {
        for(Bullet b: Tank.bullets){
            if(b.y>this.y-50&&b.y<this.y+50&b.x>this.x-70&&b.x<this.x+70){//means we've been hit
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

    @Override
    public void update() {
        velX= -4;
        x+=velX;


        if(this.x<=-100||isDestroyed()&&counter1==0){
            sub.set(indexSub,new Submarine(originalX, PollutionCombat.getHEIGHT() - 200,indexSub,Character.SUBMARINE,this.tank,this.world));
            counter1+=1;
        }
        //toxins
        for(Toxin t:toxins) {
            t.update();
        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

}

