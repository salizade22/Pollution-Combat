import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//all the input is read here
public class KeyInput extends KeyAdapter implements KeyListener{
    private World world;
    private int counterReset=0;

    public KeyInput(World world) {
        this.world=world;
    }
    private static int counter=0;//helps with our pause
    private static int counterLeft=0;
    private static int counterRight=0;

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();

        for(Vector<GameObject> characters:world.getGameObjects()) {
            for (GameObject character : characters) {
                if (character.getID() == Character.TANK) {
                    if (code == KeyEvent.VK_LEFT&&counterLeft==0) {
                        world.setLeftPressed(true);
                        counterLeft+=1;
                    }
                    if (code == KeyEvent.VK_RIGHT&&counterRight==0){
                        world.setRightPressed(true);
                        counterRight+=1;
                    }
                    if (code == KeyEvent.VK_SPACE) {
                        Tank tank = (Tank) character; //get the tank
                        //do something to the tank
                        tank.bullets.elementAt(Bullet.currentBullet).velY = -40;
                    }
                }
            }
        }


        if (code == KeyEvent.VK_P){
            if(counter%2==0)
                World.setPause(true);
            else
                World.setPause(false);
            counter+=1;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        for (Vector<GameObject> characters : world.getGameObjects()) {
            for (GameObject character : characters) {
                if (character.getID() == Character.TANK) {
                    if (code == KeyEvent.VK_LEFT) {
                        world.setLeftPressed(false);
                        counterLeft=0;
                    }
                    if (code == KeyEvent.VK_RIGHT) {
                        world.setRightPressed(false);
                        counterRight=0;
                    }
                }
            }
        }
    }

}
