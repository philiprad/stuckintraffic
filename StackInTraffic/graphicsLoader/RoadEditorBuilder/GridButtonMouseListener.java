/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader.RoadEditorBuilder;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving myMouse events. The class that is
 * interested in processing a myMouse event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addMyMouseListener<code> method. When
 * the myMouse event occurs, that object's appropriate
 * method is invoked.
 *
 * @see MyMouseEvent
 */
public class GridButtonMouseListener extends JPanel implements MouseListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -574114849359770825L;

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//if (e.getSource() != this) return;
		JButton button= (JButton)e.getSource() ;
	   // setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.GREEN,Color.BLACK));
		button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GREEN,Color.GREEN));//(Color.GREEN, 10));//(BevelBorder.RAISED,Color.GREEN,Color.BLACK));
		button.repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//if (e.getSource() != this) return;
		   // setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.GREEN,Color.BLACK));
		JButton button = (JButton)e.getSource();
		button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		button.repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton button= (JButton)e.getSource() ;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
