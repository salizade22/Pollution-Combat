import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Fish {
    private Image fish1=new ImageIcon("fish.png").getImage();//image to display
    private int mUp = 0;//facts-wind speed
    private int mLeft =-1;//facts-gravity
    private int fish;
    private int x;//random position
    private int y;
    private int prevX;//keeps place of previous drop
    private int prevY;

    public Fish(int x,int fish) {
        Random r = new Random();
        y = PollutionCombat.getHEIGHT()-30 -r.nextInt(250);
        this.x = x ;
        this.fish=fish;
    }

    public void update() {//just add wind and gravity
        prevX = x;
        prevY = y;

        x += mLeft;
        y += mUp;


            if (x == 0) {
                x = PollutionCombat.getWIDTH() + 100;
            }

    }

    public void repaint(Graphics2D g2) {
        Graphics2D g = (Graphics2D) g2;

        if(PollutionCombat.getWaterColor()<200&&PollutionCombat.getWaterColor()>100) {
            if(fish%3==0){
                g.drawImage(fish1, (int) x  , (int) y , null);
            }
        }
        else if(PollutionCombat.getWaterColor()<100&&PollutionCombat.getWaterColor()>50) {
            if(fish%4==0){
                g.drawImage(fish1, (int) x  , (int) y , null);
            }
        }
        else if(PollutionCombat.getWaterColor()<50&&PollutionCombat.getWaterColor()>=-20) {
          //do nothing
        }
        else{
            g.drawImage(fish1, (int) x  , (int) y , null);
        }

    }



}
