import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Vector;


public class Ship extends GameObject{
        private Image shipp=new ImageIcon("ship.png").getImage();
        protected World world;
        protected boolean destroyed=false;
        private static Vector<GameObject> ship=GameSequence.getShip();//we can refer to the vector in GameSequence here and modify it in this class ro avoid clustering of code in GameSequence
        protected Vector<Toxin> toxins=new Vector<Toxin>();//all the bullets in out tank
        private int indexShip=0;//help with iterating this vector
        protected Tank tank;
        protected int counter=0;
        protected int counter1=0;
        protected final int originalX;

        public Ship(int posX, int posY, int currentShip,Character ID, Tank tank,World world) {
            super(posX, posY, ID,world);
            this.world=world;
            addToxins(toxins);
            this.indexShip=currentShip;
            this.tank=tank;
            originalX=posX;
        }

         private void addToxins(Vector<Toxin> toxins) { //recharge the ammo
            if(ID==Character.SHIP) {
                for (int i = 0; i < 3; i++) { //start with a 100 bullets
                    toxins.add(new Toxin(x + 25, this.y + 50, i, Character.SHIP, world, this));
                }
            }

             if(ID==Character.SUBMARINE) {
                 for (int i = 0; i < 3; i++) { //start with a 100 bullets
                     toxins.add(new Toxin(x + 25, this.y + 50, i, Character.SUBMARINE, world, this));
                 }
             }


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
                g.drawImage(shipp, x, y, null);

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
            velX= 2;
            x+=velX;


            if(x>PollutionCombat.getWIDTH()&&counter1==0) {
                ship.set(indexShip, new Ship(originalX, PollutionCombat.getHEIGHT() - 350, indexShip, Character.SHIP, this.tank, this.world));
                counter1+=1;
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


