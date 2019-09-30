import java.awt.*;
import javax.swing.*;

public class MenuMain extends JPanel{
	static int windowSize;
	private Image background=new ImageIcon("background.png").getImage();
	public MenuMain(int size) {
		windowSize=size;
        this.setPreferredSize(new Dimension(PollutionCombat.getWIDTH(), PollutionCombat.getHEIGHT())); //display size
        this.addMouseListener(new MouseInput());
	}
	 
	@Override
	public void paintComponent(Graphics g) {
		 g.drawImage(background, 0, 0, null);
		// Draws welcome text on screen.
		Font font = new Font("arial",Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("Welcome!! Get Ready to fight Pollution!",windowSize/2-250,100);
		
		// Creates three rectangles which represent buttons.
		Font def = new Font("arial",Font.PLAIN, 20);
		g.setFont(def);
		g.setColor(Color.GRAY);
		g.fillRect(windowSize/2-100, 150, 100, 30);
		g.fillRect(windowSize/2-100, 250, 100, 30);
		g.fillRect(windowSize/2-100, 350, 100, 30);

		g.setColor(Color.WHITE);
		g.drawString("Play", windowSize/2-80, 170);
		g.drawString("Help", windowSize/2-80, 270);
		g.drawString("Quit", windowSize/2-80, 370);
       
		
	}
}
