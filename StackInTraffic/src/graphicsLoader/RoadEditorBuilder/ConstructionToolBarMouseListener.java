/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader.RoadEditorBuilder;

import graphicsLoader.ImagesBuilder;
import graphicsLoader.ImagesSelector;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving constructionToolBarMouse events. The
 * class that is interested in processing a constructionToolBarMouse event
 * implements this interface, and the object created with that class is
 * registered with a component using the component's
 * <code>addConstructionToolBarMouseListener<code> method. When
 * the constructionToolBarMouse event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ConstructionToolBarMouseEvent
 */
public class ConstructionToolBarMouseListener extends JPanel implements MouseListener{
	
	/** The block type. */
	private short blockType;
	
	/** The buttons. */
	private Component [][] buttons;
	
	/** The ib. */
	private ImagesBuilder ib;
	
	/** The editor state. */
	private EditorState editorState;
	
	/**
	 * Instantiates a new construction tool bar mouse listener.
	 *
	 * @param blockType
	 *            the block type
	 * @param ib
	 *            the ib
	 * @param buttons
	 *            the buttons
	 * @param editorState
	 *            the editor state
	 */
	public ConstructionToolBarMouseListener(short blockType, ImagesBuilder ib, Component [][] buttons, EditorState editorState ){
		this.blockType = blockType;
		this.buttons = buttons;
		this.ib = ib;
		this.editorState = editorState;
	}


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.editorState.setState(RoadEditorConfig.BUILD_STATE);
		this.editorState.setCurrentBlockType(blockType);
		for (int i=0; i<this.buttons.length; i++){
			for (int j=0; j<this.buttons[0].length; j++){
				buttons[i][j].setCursor(CursorManager.customCursor(ImagesSelector.selectRoadImageCr(this.blockType, this.ib)));
			}
		}
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

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton)e.getSource();
		//button.setBorder(BorderFactory.createEmptyBorder(5, 1, 5, 10));
		button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
		button.repaint();
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton)e.getSource();
		button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
		button.repaint();
	}

}
