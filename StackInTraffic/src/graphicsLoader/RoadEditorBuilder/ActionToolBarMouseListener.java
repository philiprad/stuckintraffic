package graphicsLoader.RoadEditorBuilder;

import graphicsLoader.ImagesBuilder;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionToolBarMouseListener extends JPanel implements MouseListener {
	
	private ImagesBuilder ib;
	
	private Component [][] buttons ;
	
	private EditorState editorState;
	
	private short actionType;
	
	public ActionToolBarMouseListener (short actionType, ImagesBuilder ib, Component [][] buttons, EditorState editorState ){
		this.ib = ib;
		this.buttons = buttons;
		this.editorState = editorState;
		this.actionType = actionType;
	}

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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
