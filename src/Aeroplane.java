import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.Vector;

//Controls all of our Aeroplanes
public class Aeroplane extends GameObject {
    private Image aero=new ImageIcon("aeroplane.png").getImage();
    private Vector<GameObject> aeroplane=GameSequence.getAeroplane();//we can refer to the vector in GameSequence here and modify it in this class ro avoid clustering of code in GameSequence
    protected boolean destroyed=false;//destroyed?
    protected Vector<Toxin> toxins=new Vector<Toxin>();
    private int indexPlane=0;//help with iterating this vector
    protected Tank tank;
    private int counter=0;
    private int counter2=0;
    private final int originalX;


    public Aeroplane(int posX, int posY, int currentPlane,Character ID, World world,Tank tank) {
        super(posX, posY, ID,world);
        this.world=world;
        addToxins(toxins);
        this.indexPlane=currentPlane;
        this.tank=tank;
        originalX=posX;
    }

	//add toxins to our game
    protected void addToxins(Vector<Toxin> toxins) { //recharge the ammo
        if(ID==Character.AEROPLANE)
            for(int i=0; i<2 ; i++){ //start with a 100 bullets
                toxins.add(new Toxin(x ,this.y+100 , i,Character.AEROPLANE, world ,this));
            }
        if(ID==Character.ALIENSHIP)
            for(int i=0; i<2 ; i++){ //start with a 100 bullets
                toxins.add(new Toxin(x ,this.y+100 , i,Character.ALIENSHIP, world ,this));
            }
    }

	//update and repaint
    @Override
    public void repaint(Graphics g) {
        for(Bullet b: Tank.bullets){
            if(b.y>this.y-100&&b.y<this.y+100&b.x>this.x&&b.x<this.x+200){//means we've been hit
                destroyed=true;
                b.setHitSomething(true);
            }
        }

        if(destroyed==false) {
            g.drawImage(aero, x, y, null);
            //bullet vector
            try{
                for (Toxin t : toxins) {
                    t.repaint(g);
                }
            }
            catch (ConcurrentModificationException e){
                //do nothing
            }
        }
        else {
            if(destroyed&&counter==0){//the counter is to make sure there is no double counting because the game repaints super fast
                PollutionCombat.setCurrentScore(PollutionCombat.getCurrentScore()+1);
                counter++;
            }
        }
    }

    Random random=new Random(1342);
    @Override
    public void update() {
       velX= 2;
       x+=velX;

        if(this.x>=PollutionCombat.getWIDTH()||isDestroyed()&&counter2==0){//to avoid double counting
           aeroplane.set(indexPlane,new Aeroplane(originalX, 100-random.nextInt(60) ,indexPlane,Character.AEROPLANE,world,this.tank));
           counter2+=1;
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


