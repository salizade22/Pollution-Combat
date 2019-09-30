import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HelpPanelMouse implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated  method stub
		int mx = e.getX();
		int my = e.getY();
		if(mx >=MenuMain.windowSize/2-100 && mx<= MenuMain.windowSize ) {
			if(my >=450 && my <= 480) {
				Main l = new Main(new MenuMain(PollutionCombat.getWIDTH()));
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
