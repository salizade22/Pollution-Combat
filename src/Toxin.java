import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Random;

//Deals with every gameobject that is a villain and assigns it chemicals
public class Toxin extends GameObject {
    private int index;
    private Aeroplane aeroplane;
    private Submarine sub;
    private Ship ship;
    private AlienShip alienShip;

    private Image toxin=new ImageIcon("toxin.png").getImage();
    private Image toxin2=new ImageIcon("toxin2.png").getImage();

    public Toxin(int posX, int posY, int index,Character ID,World world,GameObject enemy) {//many objects utilizing the same constructor
        super(posX, posY, ID ,world);
        this.x=posX-100;
        this.y=posY;

        switch(ID){
            case AEROPLANE:
                this.aeroplane = (Aeroplane)enemy;
                break;

            case SUBMARINE:
                this.sub=(Submarine)enemy;
                break;

            case SHIP:
                this.ship=(Ship) enemy;
                break;

            case ALIENSHIP:
                this.alienShip=(AlienShip)enemy;
                break;
        }

        this.world=world;
        this.index=index;
    }

    @Override
    public void repaint(Graphics g) {
        switch (ID){
            case AEROPLANE:
                if (!this.aeroplane.isDestroyed()) {
                    try {
                        g.drawImage(toxin, (int) x, (int) y, null);
                    } catch (ConcurrentModificationException e) {
                        //do nothing
                    }
                }
                break;
            case SHIP:
                if(!this.ship.isDestroyed()) {
                    try {
                        g.drawImage(toxin2, (int) x, (int) y, null);
                    } catch (ConcurrentModificationException e) {
                        //do nothing
                    }
                }
                break;

            case SUBMARINE:
                if(!this.sub.isDestroyed()) {
                    try {
                        g.drawImage(toxin2, (int) x, (int) y, null);
                    } catch (ConcurrentModificationException e) {
                        //do nothing
                    }
                }
                break;

            case ALIENSHIP:
                if(!this.alienShip.isDestroyed()) {
                    try {
                        g.drawImage(toxin, (int) x, (int) y, null);
                    } catch (ConcurrentModificationException e) {
                        //do nothing
                    }
                }
                break;
        }
    }

    Random random=new Random();
    @Override
    public void update(){
        switch (ID){
            case AEROPLANE:
                if (!this.aeroplane.isDestroyed()) {
                    y += velY;

                    x = aeroplane.x + 100;


                    if (this.x >= random.nextInt(PollutionCombat.getWIDTH())) {
                        velY = 4;
                    }

                    if(y==PollutionCombat.getHEIGHT()-150&&x>0&&x<PollutionCombat.getWIDTH()) {
                        PollutionCombat.setWaterColor(PollutionCombat.getWaterColor() - 10);
                    }

                }
                break;
			case SUBMARINE:
                if (!this.sub.isDestroyed()) {
                    //System.out.println(x+" "+y);
                    x=this.sub.x+20;
                    y += velY;

                    if (this.x <= 800) {
                        velY = 2;
                    }

                    if(y==PollutionCombat.getHEIGHT()&&x>0&&x<PollutionCombat.getWIDTH()) {
                        PollutionCombat.setWaterColor(PollutionCombat.getWaterColor() - 5);
                    }
                }
                break;
				
            case SHIP:
                    if (!this.ship.isDestroyed()) {
                        x=this.ship.x+20;
                        y += velY;

                        if (x >= random.nextInt(PollutionCombat.getWIDTH())) {
                            velY = 2;
                        }

                        if(y==PollutionCombat.getHEIGHT()&&x>0&&x<PollutionCombat.getWIDTH()) {
                            PollutionCombat.setWaterColor(PollutionCombat.getWaterColor() - 7);

                        }
                    }
				break;

            
            case ALIENSHIP:
                if (!this.alienShip.isDestroyed()) {
                    y += velY;

                    if (this.y <= random.nextInt(300)) {
                        x = alienShip.x + 50;
                        y=alienShip.y+50;
                    }

                    if (y >= random.nextInt(100)) {
                        velY = 15;
                    }

                    if(y==PollutionCombat.getHEIGHT()-250&&x>0&&x<PollutionCombat.getWIDTH()) {
                        PollutionCombat.setWaterColor(PollutionCombat.getWaterColor() - 20);

                    }
                }
                break;

        }
    }


}
