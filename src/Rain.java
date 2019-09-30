import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Random;

//adapted from ashakirov of stackoverflow.com in:https://stackoverflow.com/questions/11295986/simulating-rain
class Rain {
    private int mWind = 0;//facts-wind speed
    private int mGravity = 1;//facts-gravity

    private int x;//random position
    private int y;
    private int prevX;//keeps place of previous drop
    private int prevY;

    public Rain(int y) {
        Random r = new Random();
        x = r.nextInt(PollutionCombat.getWIDTH());
        this.y = y ;
    }

    public void update() {//just add wind and gravity
        prevX = x;
        prevY = y;

        x += mWind;
        y += mGravity;
    }

    public void repaint(Graphics2D g2) {
        if(y<PollutionCombat.getHEIGHT()-300){//should be ideally linked to the oceans y ##night mode
            Line2D line = new Line2D.Double(x, y, prevX, prevY);
            g2.draw(line);
        }
    }


}
