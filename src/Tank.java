import java.awt.*;
import java.util.Vector;

//Tank
public class Tank extends GameObject{
    static Vector<Bullet> bullets=new Vector<>();//all the bullets in out tank--the use of static is the same concept used in GameSequence

    public Tank(int posX, int posY,Character ID, World world) {
        super(posX, posY ,Character.TANK ,world); //takes to parent constructor--creates a new GameObject
        this.world=world;
        addBullets(bullets); //start with an initial number of bullets
    }

    private void addBullets(Vector<Bullet> bullets){
        int mody=0;
        for(int i=0; i<10; i++){//have 10 recyclable bullets
            bullets.add(new Bullet(this.x ,PollutionCombat.getHEIGHT()+50,Character.BULLET, world ,this));
        }
    }

    @Override
    public void repaint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x,y,20,20);
        //bullet vector
        for(Bullet b:bullets) {
            b.repaint(g);
        }
    }

    @Override
    public void update(){//update
        x+=velX;
        y+=velY;

        //movement for every update
        if(world.isRightPressed()){
            if(x>=PollutionCombat.getWIDTH()-20){ velX=0; }//to keep the player within boundaries
            else velX=10;
        }
        else if(!world.isLeftPressed()) velX=0;


        if(world.isLeftPressed()){ if(x==0){ velX=0;}//to keep player within the boundaries
            else velX=-10;
        }
        else if(!world.isRightPressed()) velX=0;

        //bullet vector
        for(Bullet b:bullets) {
            b.update();
        }
    }

}
