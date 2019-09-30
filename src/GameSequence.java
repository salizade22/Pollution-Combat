import java.util.Random;
import java.util.Vector;

//Everything that appears in the world is here
public class GameSequence{
    private World world;
    private static Vector<GameObject> tank=new Vector<>(); //static to enable us refer to them in various classes with ease instead of creating new instances of this class
    private static Vector<GameObject> cloud=new Vector<>();//well demonstrated in other classes i.e Cloud.java
    private static Vector<GameObject> aeroplane=new Vector<>();
    private static Vector<GameObject> ship=new Vector<>();
    private static Vector<GameObject> sub=new Vector<>();
    private static Vector<GameObject> alienShip=new Vector<>();

    public GameSequence(World world){
        this.world=world;
        giveMeVector();//creates all game objects to be used in the game
    }


    public void start() {//the first command run in this class called from PollutionCombat
        //level1
        world.addObject(tank);//add a vector of tank objects to our world
        world.addObject(cloud);//add a vector of cloud objects to our world

        if (PollutionCombat.getLevel()==1) //other levels are added in the main class
            world.addObject(sub);

    }

    private void giveMeVector(){//creates the vectors of various gameObjects to be used
        //we can only have one tank in the current mode
        tank.add(new Tank(100, PollutionCombat.getHEIGHT() -20,Character.TANK, world));

        //Clouds--we only need two clouds in our vector which will be reset every single time their function is achieved i.e once it moves from screen to screen
        int cloudMod=0;
        Random random=new Random();
        for(int i=0; i<5 ; i++){ //start with a 100 bullets
            cloud.add(new Cloud(0+ cloudMod ,85,Character.CLOUD, world));
            cloudMod-=500;
        }

        int aeroMod=0;
        //Aeroplane
        for(int i=0; i<3; i++){ //start with a 100 bullets
            aeroplane.add(new Aeroplane(-700+aeroMod,200,i,Character.AEROPLANE,world,(Tank)tank.get(0)));
            aeroMod-=PollutionCombat.getWIDTH()+200;
        }

        int shipMod=0;
        //Ship
        for(int i=0; i<5; i++){ //start with a 100 bullets
            ship.add(new Ship(-100+shipMod, PollutionCombat.getWIDTH()-400,i,Character.SHIP,(Tank)tank.get(0),world));
            shipMod-=PollutionCombat.getWIDTH()-700;
        }

        int subMod=0;
        //Submarine
        for(int i=0; i<6; i++){ //start with a 100 bullets
            sub.add(new Submarine(PollutionCombat.getWIDTH()+100+subMod, PollutionCombat.getHEIGHT() - 200,i,Character.SUBMARINE,(Tank)tank.get(0),world));
            subMod+=PollutionCombat.getWIDTH();
        }

        int alien=0;
        //Submarine
        for(int i=0; i<9; i++){ //start with a 100 bullets
            alienShip.add(new AlienShip(400, -200+alien,i,Character.ALIENSHIP,world,(Tank)tank.get(0)));
            alien-=400;
        }
    }

    //getters and setters
    public static Vector<GameObject> getCloud() { return cloud; }

    public static Vector<GameObject> getAeroplane() {return aeroplane;}

    public static Vector<GameObject> getShip() { return ship; }

    public static Vector<GameObject> getSub() { return sub; }

    public static Vector<GameObject> getAlienShip() { return alienShip; }

}
