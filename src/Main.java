import javax.swing.JFrame;
import javax.swing.JPanel;
//Main class
public class Main {
	static JPanel panel = new MenuMain(PollutionCombat.getWIDTH());

	// Constructor creates a Jframe and sets it's content pane to be the parameter that's passed to the constructor.
        public Main(JPanel p) {
            panel = p;
            JFrame frame = new JFrame("POLLUTION COMBAT");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setContentPane(p);
            frame.pack();
        }

    // Main method creates an instance of this class. As soon as an instance is created the Jframe is displayed and the whole program is fired up.
        public static void main(String[] args){
            Main l = new Main(panel);
        }
}
