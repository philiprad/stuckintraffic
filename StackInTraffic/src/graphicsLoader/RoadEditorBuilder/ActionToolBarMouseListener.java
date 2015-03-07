/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader.RoadEditorBuilder;

import graphicsLoader.ImagesBuilder;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving actionToolBarMouse events. The class
 * that is interested in processing a actionToolBarMouse event implements this
 * interface, and the object created with that class is registered with a
 * component using the component's
 * <code>addActionToolBarMouseListener<code> method. When
 * the actionToolBarMouse event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ActionToolBarMouseEvent
 */
public class ActionToolBarMouseListener extends JPanel implements MouseListener {
	
	/** The ib. */
	private ImagesBuilder ib;
	
	/** The buttons. */
	private Component [][] buttons ;
	
	/** The editor state. */
	private EditorState editorState;
	
	/** The action type. */
	private short actionType;
	
	/**
	 * Instantiates a new action tool bar mouse listener.
	 *
	 * @param actionType
	 *            the action type
	 * @param ib
	 *            the ib
	 * @param buttons
	 *            the buttons
	 * @param editorState
	 *            the editor state
	 */
	public ActionToolBarMouseListener (short actionType, ImagesBuilder ib, Component [][] buttons, EditorState editorState ){
		this.ib = ib;
		this.buttons = buttons;
		this.editorState = editorState;
		this.actionType = actionType;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		// TODO Auto-generated method stub
		switch(this.actionType){
			
		
		case RoadEditorConfig.ACTION_NULL :
			this.editorState.setState(RoadEditorConfig.EMPTY_STATE);
			this.editorState.setCurrentBlockType((short) 0);
			for (int i=0; i<this.buttons.length; i++){
				for (int j=0; j<this.buttons[0].length; j++){
					buttons[i][j].setCursor(CursorManager.customCursor(ib.getStandartCursor()));
				}
			}break;
		case RoadEditorConfig.ACTION_MOVE :
			this.editorState.setState(RoadEditorConfig.HANDLE_STATE);
			System.out.println("\n handle state \n");
			this.editorState.setCurrentBlockType((short) 0);
			for (int i=0; i<this.buttons.length; i++){
				for (int j=0; j<this.buttons[0].length; j++){
					buttons[i][j].setCursor(CursorManager.customCursor(ib.getHandCursor()));
				}
			}break;
		
		case RoadEditorConfig.ACTION_DELETE :
			this.editorState.setState(RoadEditorConfig.DELETE_STATE);
			
			this.editorState.setCurrentBlockType((short) 0);
			System.out.println("work");
				for (int i=0; i<this.buttons.length; i++){
					for (int j=0; j<this.buttons[0].length; j++){
						this.buttons[i][j].setCursor(CursorManager.customCursor(ib.getDeleteCursor()));
						System.out.println("work");
					}
				}break;
	
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
		
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	
}
