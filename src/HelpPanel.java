import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

//Help menu
public class HelpPanel extends JPanel{
    
	String s = new StringBuilder().append("Use Left and Right arrows to move the tank.\n")
			.append("Use spacebar to shoot.\n").append("As more toxins drop your life span reduces.").toString();
	
	public HelpPanel() {
        this.setPreferredSize(new Dimension(PollutionCombat.getWIDTH(), PollutionCombat.getHEIGHT())); //display size 
        this.addMouseListener(new HelpPanelMouse());
        
	}
	
	@Override
    public void paintComponent(Graphics g){
		g.drawString(s, PollutionCombat.getWIDTH()/2-300, 270);
		
		
		
		g.setColor(Color.GRAY);
		g.fillRect(PollutionCombat.getWIDTH()/2 -100, 450, 100, 30);
		g.setColor(Color.WHITE);
		g.drawString("Back", PollutionCombat.getWIDTH()/2 -80, 470);


    }
}
