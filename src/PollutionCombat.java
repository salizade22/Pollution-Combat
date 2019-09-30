import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.util.ConcurrentModificationException;

//this is the main class game
/*
ordered in linear fashion- from top to bottom sequence in the game
*/

public class PollutionCombat extends JPanel implements Runnable{
    private static int currentScore=0; //current score
    private static final int WIDTH=1460; //Width
    private static final int HEIGHT=900;//Height
    private static final int FPS = 60;//Frames Per Second of Execution
    private Thread mainThread;//main thread
    private World world;//everything gameObject in this game is contained here
    private static int level=1;//current level
    private static GameSequence currentLevel; //story line for our game is written here
    private boolean isRunning=false; //is the thread running
    private static long day=0;//determines the day times
    private Image img2=new ImageIcon("img2.png").getImage();
    private static PollutionCombat combat;

    //main
    public static void main(String[] args){ //sets the frame where the game is played //where the program starts
        JFrame frame = new JFrame("POLLUTION COMBAT");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        combat = new PollutionCombat(); //start instance of this program
        frame.setContentPane(combat);
        frame.pack();
        frame.setVisible(true); //set it as visible
        frame.setMaximumSize(panel.getPreferredSize());//cannot resize;
    }

    public PollutionCombat(){ //constructor
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //display size
        world=new World(); //create a new world--the world is almost like a level
        this.addKeyListener(new KeyInput(world)); //create a new Listener for our world
        start();//start the Program

        //start our story--This is where the game is made and objects are added to our world
        currentLevel=new GameSequence(world);
        currentLevel.start();
    }

    public void start() {//start the game
        isRunning=true;
        mainThread = new Thread(this);//start a new thread
        mainThread.start(); //tells the program to go to the run method for the thread to start
    }

    @Override
    public void run() {//what happens when the game is running
        //most of the code here apart from update and repaint is borrowed from:
		//https://www.youtube.com/playlist?list=PLWms45O3n--6KCNAEETGiVTEFvnqA7qCi-Real Tuts Java Game Development
        //the only self written methods are the update && repaint
        setFocusable(true); //enables focus for our keyboard input
        setRequestFocusEnabled(true);
        requestFocus();

        //game loop
        long lastTime = System.nanoTime();
        double ns = 1000000000 / FPS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            if(PollutionCombat.getWaterColor()>35)
                 day++;
            else {
                day += 500000;//dramatic effect
            }

            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while(delta >= 1) {
                    update(); //update the world------------------------------
                delta--;
            }
                repaint(); //create new image of the world---------------------------------


            frames++;

            if (System.currentTimeMillis() - timer > 500) {
                timer += 500;
                frames = 0;
            }
        }
        stop();
    }

    public void stop() {//stop the game
        isRunning=false;
    }


    public void update(){//what happens when we update
        checkLevel();
        world.update();
    }

    private int counterLevel1=0;
    private void checkLevel() {//updates the levels as the scores increase
        if(PollutionCombat.getCurrentScore()>3&&counterLevel1==0) {
            world.addObject(GameSequence.getShip());
            counterLevel1++;
            level+=1;
        }
        if(PollutionCombat.getCurrentScore()>10&&counterLevel1==1) {
            world.addObject(GameSequence.getAeroplane());
            counterLevel1++;
            level+=1;
        }
        if(PollutionCombat.getCurrentScore()>20&&counterLevel1==2) {
            world.addObject(GameSequence.getAlienShip());
            counterLevel1++;
            level+=1;
        }
    }

    //facilitate change in WaterColor--which is basically the score
    private static int waterColor=250; //basically determines the score of the game
    private int night=0;

    @Override
    public void paintComponent(Graphics gOri){//draws everything in this program
        Graphics2D g = (Graphics2D) gOri;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

		//logic
        if(getWaterColor()<=5){
            g.drawImage(img2,0,0,1024,768,null);
            g.drawString("SCORE:   "+ currentScore, 400, 700);
        }


        else {
            if (day < 400000000) {
                night = 1;
            } else if (day > 400000000 && day < 800000000) {
                night = 2;
            } else {
                night = 3;
            }

            //new Color(55, 60,  100) 25 30 //new Color(200,250,150)--dayMode or nightMode
            if (day > 1200000000) {
                day = 0;
            }

            if (night == 1) {
                GradientPaint sunSet = new GradientPaint(0, 0, new Color(55, 60, 100), 0, HEIGHT, new Color(10, 140, 150));
                g.setPaint(sunSet);
                g.fill(new Rectangle2D.Double(0, 0, WIDTH, HEIGHT));
            } else if (night == 2) {
                GradientPaint sunSet = new GradientPaint(0, 0, new Color(100, 150, 170), 0, HEIGHT, new Color(10, 140, 150));
                g.setPaint(sunSet);
                g.fill(new Rectangle2D.Double(0, 0, WIDTH, HEIGHT));
            } else {
                GradientPaint sunSet = new GradientPaint(0, 0, new Color(200, 250, 150), 0, HEIGHT, new Color(10, 140, 150));
                g.setPaint(sunSet);
                g.fill(new Rectangle2D.Double(0, 0, WIDTH, HEIGHT));
            }

            if (night == 1) {
                g.setColor(Color.CYAN);
            } else {
                g.setColor(Color.BLUE);
            }

            //SCORES
            g.drawString("LEVEL:      " + getLevel(), PollutionCombat.getWIDTH() - 100, 10);
            g.drawString("SCORE:    " + currentScore, PollutionCombat.getWIDTH() - 100, 25);
            g.drawString("LIFE:     " + waterColor, PollutionCombat.getWIDTH() - 100, 40);

            try {
                //Ocean
                Color ocean = new Color(51, 180, waterColor);
                g.setColor(ocean);
                g.fillRect(0, HEIGHT - 300, WIDTH, 300);
            } catch (IllegalArgumentException e) {
                //do nothing
            }

            //repaint every object in our world
            try {
                world.repaint(gOri);
            } catch (ConcurrentModificationException e) {
                //do nothing
            }
        }
    }

    //getters and setters
    public static int getWaterColor() { return waterColor; }//waterColor

    public static void setWaterColor(int waterColor) { PollutionCombat.waterColor = waterColor; }

    public static int getWIDTH() { return WIDTH; }//width of screen

    public static int getHEIGHT() { return HEIGHT; }//Height of screen

    public static int getLevel() { return level; }//level

    public static int getCurrentScore() { return currentScore; }//currentscore

    public static void setCurrentScore(int currentScore) { PollutionCombat.currentScore = currentScore; }//currentscore

}
