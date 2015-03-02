package graphicsLoader.RoadEditorBuilder;

import graphicsLoader.ImagesBuilder;
import graphicsLoader.ImagesSelector;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolBarButtonMouseListener extends JPanel implements MouseListener{
	
	private short blockType;
	private Component [][] buttons;
	private ImagesBuilder ib;
	private EditorState editorState;
	
	public ToolBarButtonMouseListener(short blockType, ImagesBuilder ib, Component [][] buttons, EditorState editorState ){
		this.blockType = blockType;
		this.buttons = buttons;
		this.ib = ib;
		this.editorState = editorState;
	}


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
		JButton button = (JButton)e.getSource();
		//button.setBorder(BorderFactory.createEmptyBorder(5, 1, 5, 10));
		button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
		button.repaint();
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton)e.getSource();
		button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
		button.repaint();
	}

}
