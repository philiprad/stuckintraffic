/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader.RoadEditorBuilder;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;
import graphicsLoader.ImagesSelector;
import gui.RoadEditorView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import trafficInfrastructure.grid.GridBuilder;

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
	
	private GridBuilder gridBuilder;
	private Component [][] buttons;
	private ImagesBuilder ib;
	private EditorState editorState;
	private JPanel panel;
	
	public GridButtonMouseListener(GridBuilder gridBuilder, Component [][] buttons, ImagesBuilder ib, EditorState editorState, JPanel panel){
		this.gridBuilder = gridBuilder;
		this.buttons = buttons;
		this.ib = ib;
		this.editorState = editorState;
		this.panel = panel;
	}

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
		this.editorState.setCanBuild(true);
		
		if (this.editorState.getState()==RoadEditorConfig.BUILD_STATE || (this.editorState.getState()==RoadEditorConfig.HANDLE_STATE && this.editorState.getHandled()==true)){
			
			
			short [][] roadGrid = this.gridBuilder.getGrid();
			for(int i=0;i<buttons.length;i++){
				for (int j=0;j<buttons[0].length;j++){
					if (button == buttons[i][j] && roadGrid[i][j]!=0){
						button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.RED,Color.RED));//(Color.GREEN, 10));//(BevelBorder.RAISED,Color.GREEN,Color.BLACK));
						button.repaint();
						this.editorState.setCanBuild(true);
						break;
					}
					
						
					
				}
			}
		}
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
		
		Component button= (Component)e.getSource() ;
		
		short [][] roadGrid = this.gridBuilder.getGrid();
		for(int i=0;i<buttons.length;i++){
			for (int j=0;j<buttons.length;j++){
				
				if (this.editorState.getState()==RoadEditorConfig.HANDLE_STATE && !this.editorState.getHandled()){
					
					if (roadGrid[i][j]!=0 && roadGrid[i][j]!=-100){
						this.editorState.setHandled(true);
						this.editorState.setCurrentBlockType(roadGrid[i][j]);
						((JButton)buttons[i][j]).setIcon(null);//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
						((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
						System.out.println("\n handled \n");
					}
					
				}
				
				if (button == buttons[i][j] && roadGrid[i][j]==0){
					
				panel.invalidate();
				 if ((this.editorState.getState()==RoadEditorConfig.BUILD_STATE && this.editorState.getCanBuild()) || (this.editorState.getState()==RoadEditorConfig.HANDLE_STATE && this.editorState.getCanBuild() && this.editorState.getHandled())){
						this.editorState.setHandled(false);
					 	this.gridBuilder.addRoadBlock(this.editorState.getCurrentBlockType(), i*50, j*50);
						
						if (roadGrid[i][j]!=0 && roadGrid[i][j]!=-100){
							System.out.println("\n builded \n");
							if(roadGrid[i][j]>10){
								
								ImageIcon background = new ImageIcon( ImagesSelector.selectRoadImageSc(roadGrid[i][j], ib)); 
								((JButton)((JButton)buttons[i][j])).setIcon(background);
								((JButton)buttons[i][j]).setLayout(null);
								((JButton)buttons[i][j]).setSize(GraphicsConfig.BLOCK_SIDE_SIZE*2, GraphicsConfig.BLOCK_SIDE_SIZE*2);
								((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE*2, GraphicsConfig.BLOCK_SIDE_SIZE*2));
							}else{
								//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
								ImageIcon background = new ImageIcon( ImagesSelector.selectRoadImageSc(roadGrid[i][j], ib)); 
								((JButton)buttons[i][j]).setIcon(background);
								((JButton)buttons[i][j]).setLayout(null);
								((JButton)buttons[i][j]).setSize(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE);
								((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));

							}
					} 
					else {
						((JButton)buttons[i][j]).setIcon(null);//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
						((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
					}
					
					((JButton)buttons[i][j]).setRolloverEnabled(true);
					//((JButton)buttons[i][j]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
						
					RoadEditorView.drawButtons(this.gridBuilder, this.panel, this.buttons);
					((JButton)button).setBorder(BorderFactory.createLineBorder(Color.WHITE));
					button.repaint();
					panel.repaint();
						
						
					}
					this.panel.validate();
				}
				
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

}
