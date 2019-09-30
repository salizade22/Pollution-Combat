import javax.swing.*;
import java.awt.*;
import java.util.Vector;
//Bullet class--bulet in our game
public class Bullet extends GameObject{
    private static Vector<Bullet> bullets=Tank.bullets;///we can refer to the vector of bullets in tank here and modify it in this class ro avoid clustering of code in Tank
    protected static  int currentBullet=0;//help with iterating this vector
    private Tank tank;//tank
    private boolean hitSomething;//has it a gameObject
    private Image bullet=new ImageIcon("bullet.png").getImage();//image to display

    //constructor
    public Bullet(int posX, int posY, Character ID,World world,Tank tank) {
        super(posX, posY, ID ,world);
        this.x=x;
        this.y=y;
        this.tank=tank;
        this.world=world;
        hitSomething=false;
    }

    @Override
    public void repaint(Graphics g) {
        g.drawImage(bullet,(int)x-10,(int)y,null);
    }

    @Override
    public void update() {
        if(y>PollutionCombat.getHEIGHT())
             x = tank.x;//we can change this--the bullets move with the tank

        y+=velY;

        if(y<=0||hitSomething) {
            currentBullet += 1;
            if(currentBullet>bullets.size()-1){//if index> the vector size go back to the first element
                currentBullet=0;
            }
            bullets.set(currentBullet,new Bullet(tank.x,PollutionCombat.getHEIGHT()+50,Character.BULLET,world,this.tank));
        }
    }

    //getter
    public void setHitSomething(boolean hitSomething) {
        this.hitSomething = hitSomething;
    }

}
