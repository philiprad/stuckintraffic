package guiGrid;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class MyMouseListener extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -574114849359770825L;

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//if (e.getSource() != this) return;
		JButton button= (JButton)e.getSource() ;
	   // setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.GREEN,Color.BLACK));
		button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GREEN,Color.GREEN));//(Color.GREEN, 10));//(BevelBorder.RAISED,Color.GREEN,Color.BLACK));
		button.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//if (e.getSource() != this) return;
		   // setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.GREEN,Color.BLACK));
		JButton button = (JButton)e.getSource();
		button.setBorder(BorderFactory.createEmptyBorder());
		button.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton button= (JButton)e.getSource() ;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
