import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();
		/*
		 * g.drawRect(windowSize/2-100, 150, 100, 30);
		g.drawRect(windowSize/2-100, 250, 100, 30);
		g.drawRect(windowSize/2-100, 350, 100, 30);
		 */
		//Pressing Play button fires up the game.
		if(mx >=MenuMain.windowSize/2-100 && mx<= MenuMain.windowSize+50 ) {
			
			if(my>=150 && my <= 180) {
			
				Main l = new Main(new PollutionCombat());
			}
					
		}
		// Pressing help button fires up the help panel.
		if(mx >=MenuMain.windowSize/2-100 && mx<= MenuMain.windowSize+50 ) {
			if(my >=250 && my <= 280) {
				Main l = new Main(new HelpPanel());
			}
		}
		// Pressing quit exits the program.
		if(mx >=MenuMain.windowSize/2-100 && mx<= MenuMain.windowSize+50 ) {
			if(my >=350 && my <= 380) {
				System.exit(0);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
