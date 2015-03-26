/*
 * @author  Maxim Vasilishin
 * @version 4.0
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
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.RoadConfig;

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
	
	/** The grid builder. */
	private GridBuilder gridBuilder;
	
	/** The buttons. */
	private Component [][] buttons;
	
	/** The ib. */
	private ImagesBuilder ib;
	
	/** The editor state. */
	private EditorState editorState;
	
	/** The panel. */
	private JPanel panel;
	
	/**
	 * Instantiates a new grid button mouse listener.
	 *
	 * @param gridBuilder
	 *            the grid builder
	 * @param buttons
	 *            the buttons
	 * @param ib
	 *            the ib
	 * @param editorState
	 *            the editor state
	 * @param panel
	 *            the panel
	 */
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
		
		
		
		if (this.editorState.getState()==RoadEditorConfig.BUILD_STATE || (this.editorState.getState()==RoadEditorConfig.HANDLE_STATE && this.editorState.getHandled()==true)){
			
			
			short [][] roadGrid = this.gridBuilder.getGrid();
			for(int i=0;i<buttons.length;i++){
				for (int j=0;j<buttons[0].length;j++){
					if (button == buttons[i][j]){
						if (this.editorState.getCurrentBlockType()==RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
							if(i+1<buttons.length){
								if (roadGrid[i][j]!=0 || this.gridBuilder.getGrid()[i+1][j] != 0){
									button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.RED,Color.RED));
									button.repaint();
									this.editorState.setCanBuild(false);
								}
								else {
									button.setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i+1][j]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									button.repaint();
									this.editorState.setCanBuild(true);
								}
							}
							else {
								button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.RED,Color.RED));
								button.repaint();
								this.editorState.setCanBuild(false);
							}
						} else
						
						if (this.editorState.getCurrentBlockType()==RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
							if(j+1<buttons[0].length){
								if (roadGrid[i][j]!=0 || this.gridBuilder.getGrid()[i][j+1] != 0){
									button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.RED,Color.RED));
									button.repaint();
									this.editorState.setCanBuild(false);
								}
								else {
									button.setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i][j+1]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									button.repaint();
									this.editorState.setCanBuild(true);
								}
							}
							else {
								button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.RED,Color.RED));
								button.repaint();
								this.editorState.setCanBuild(false);
							}
						} else
						if (this.editorState.getCurrentBlockType()>30){
							if (i+2<buttons.length && j+2<buttons[0].length){
								if (roadGrid[i][j]!=0 || this.gridBuilder.getGrid()[i+1][j] != 0 ||
									this.gridBuilder.getGrid()[i][j+1] != 0 ||
									this.gridBuilder.getGrid()[i+1][j+1] != 0 ||
									this.gridBuilder.getGrid()[i+2][j] != 0 ||
									this.gridBuilder.getGrid()[i+2][j+1] != 0 ||
									this.gridBuilder.getGrid()[i][j+2] != 0 ||
									this.gridBuilder.getGrid()[i+1][j+2] != 0 ||
									this.gridBuilder.getGrid()[i+2][j+2] != 0 ){
									button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.RED,Color.RED));
									button.repaint();
									this.editorState.setCanBuild(false);
								}
								else{
									button.setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i+1][j]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i][j+1]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i+1][j+1]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i+2][j]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i+2][j+1]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i][j+2]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i+1][j+2]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i+2][j+2]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									button.repaint();
									this.editorState.setCanBuild(true);
								}
							}
							else {
								button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.RED,Color.RED));
								button.repaint();
								this.editorState.setCanBuild(false);
							}
						
						}else if (this.editorState.getCurrentBlockType()>10){
							if (i+1<buttons.length && j+1<buttons[0].length){	
								if (roadGrid[i][j]!=0 || this.gridBuilder.getGrid()[i+1][j] != 0 ||
										this.gridBuilder.getGrid()[i][j+1] != 0 ||
										this.gridBuilder.getGrid()[i+1][j+1] != 0 ){
										button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.RED,Color.RED));
										button.repaint();
										this.editorState.setCanBuild(false);
								} else {
									button.setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i+1][j]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i][j+1]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									((JComponent) this.buttons[i+1][j+1]).setBorder(BorderFactory.createLineBorder(Color.GREEN));
									this.editorState.setCanBuild(true);
								}
							} else {
								button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.RED,Color.RED));
								button.repaint();
								this.editorState.setCanBuild(false);
							}
						}
					
					else if (roadGrid[i][j]!=0){
							button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.RED,Color.RED));
							button.repaint();
							this.editorState.setCanBuild(false);
							break;
						} else {
							button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GREEN,Color.GREEN));
							button.repaint();
							this.editorState.setCanBuild(true);
						}
					//}
					
						
					}
				}
			}
		} else {
			button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GREEN,Color.GREEN));//(Color.GREEN, 10));//(BevelBorder.RAISED,Color.GREEN,Color.BLACK));
			button.repaint();
			this.editorState.setCanBuild(true);
			
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
		for(int i=0;i<buttons.length;i++){
			for (int j=0;j<buttons[0].length;j++){
				if (button == buttons[i][j]){
					
					if (this.editorState.getCurrentBlockType()==RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
						if(i+1<buttons.length){
							((JComponent) this.buttons[i+1][j]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
						}
					} else
					
					if (this.editorState.getCurrentBlockType()==RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
						if(j+1<buttons[0].length){
							((JComponent) this.buttons[i][j+1]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
						}
					} else
					if (this.editorState.getCurrentBlockType()>30){
						if (i+2<buttons.length && j+2<buttons[0].length){
							((JComponent) this.buttons[i+1][j]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
							((JComponent) this.buttons[i][j+1]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
							((JComponent) this.buttons[i+1][j+1]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
							((JComponent) this.buttons[i+2][j]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
							((JComponent) this.buttons[i+2][j+1]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
							((JComponent) this.buttons[i][j+2]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
							((JComponent) this.buttons[i+1][j+2]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
							((JComponent) this.buttons[i+2][j+2]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
						}
					}
					
					if (this.editorState.getCurrentBlockType()>10){
						if (i+1<buttons.length && j+1<buttons[0].length){
							((JComponent) this.buttons[i+1][j]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
							((JComponent) this.buttons[i][j+1]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
							((JComponent) this.buttons[i+1][j+1]).setBorder(BorderFactory.createLineBorder(Color.WHITE));
						}
					}
				}
			}
		}
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
			for (int j=0;j<buttons[0].length;j++){
				if (button == buttons[i][j] && this.editorState.getState()==RoadEditorConfig.DELETE_STATE){
					if (roadGrid[i][j]!=0 && roadGrid[i][j]!=-100 && roadGrid[i][j]!=-200 && roadGrid[i][j]!=-300 && roadGrid[i][j]!=-400 && roadGrid[i][j]!=-500){
						
						((JButton)buttons[i][j]).setIcon(null);//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
						((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
						if (this.gridBuilder.getGrid()[i][j]==RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
							this.gridBuilder.getGrid()[i][j] = 0;
							this.gridBuilder.getGrid()[i+1][j] = 0;
						} else
						if (this.gridBuilder.getGrid()[i][j]==RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
							this.gridBuilder.getGrid()[i][j] = 0;
							this.gridBuilder.getGrid()[i][j+1] = 0;
						} else
						if (this.gridBuilder.getGrid()[i][j]>30){
							this.gridBuilder.getGrid()[i][j] = 0;
							this.gridBuilder.getGrid()[i+1][j] = 0;
							this.gridBuilder.getGrid()[i+2][j] = 0;
							this.gridBuilder.getGrid()[i][j+1] = 0;
							this.gridBuilder.getGrid()[i+1][j+1] = 0;
							this.gridBuilder.getGrid()[i+2][j+1] = 0;
							this.gridBuilder.getGrid()[i][j+2] = 0;
							this.gridBuilder.getGrid()[i+1][j+2] = 0;
							this.gridBuilder.getGrid()[i+2][j+2] = 0;
						} else
						if (this.gridBuilder.getGrid()[i][j]>10){
							this.gridBuilder.getGrid()[i][j] = 0;
							this.gridBuilder.getGrid()[i+1][j] = 0;
							this.gridBuilder.getGrid()[i+1][j+1] = 0;
							this.gridBuilder.getGrid()[i][j+1] = 0;
						}
						else {
							this.gridBuilder.getGrid()[i][j] = 0;
						}
						RoadEditorView.drawButtons(this.gridBuilder, this.panel, this.buttons);
						((JButton)button).setBorder(BorderFactory.createLineBorder(Color.WHITE));
						panel.repaint();
						panel.validate();
						System.out.println("\n deleted \n");
					}
					
				
				} else
				if (button == buttons[i][j] && this.editorState.getState()==RoadEditorConfig.HANDLE_STATE && !this.editorState.getHandled()){
					
					if (roadGrid[i][j]!=0 && roadGrid[i][j]!=-100 && roadGrid[i][j]!=-200 && roadGrid[i][j]!=-300 && roadGrid[i][j]!=-400 &&  roadGrid[i][j]!=-500){
						this.editorState.setHandled(true);
						this.editorState.setCurrentBlockType(roadGrid[i][j]);
						((JButton)buttons[i][j]).setIcon(null);//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
						((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
						if (this.gridBuilder.getGrid()[i][j]==RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
							this.gridBuilder.getGrid()[i][j] = 0;
							this.gridBuilder.getGrid()[i+1][j] = 0;
						} else
						if (this.gridBuilder.getGrid()[i][j]==RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
							this.gridBuilder.getGrid()[i][j] = 0;
							this.gridBuilder.getGrid()[i][j+1] = 0;
						} else
						if (this.gridBuilder.getGrid()[i][j]>30){
							this.gridBuilder.getGrid()[i][j] = 0;
							this.gridBuilder.getGrid()[i+1][j] = 0;
							this.gridBuilder.getGrid()[i+2][j] = 0;
							this.gridBuilder.getGrid()[i][j+1] = 0;
							this.gridBuilder.getGrid()[i+1][j+1] = 0;
							this.gridBuilder.getGrid()[i+2][j+1] = 0;
							this.gridBuilder.getGrid()[i][j+2] = 0;
							this.gridBuilder.getGrid()[i+1][j+2] = 0;
							this.gridBuilder.getGrid()[i+2][j+2] = 0;
						} else
						if (this.gridBuilder.getGrid()[i][j]>10){
							this.gridBuilder.getGrid()[i][j] = 0;
							this.gridBuilder.getGrid()[i+1][j] = 0;
							this.gridBuilder.getGrid()[i+1][j+1] = 0;
							this.gridBuilder.getGrid()[i][j+1] = 0;
						}
						else {
							this.gridBuilder.getGrid()[i][j] = 0;
						}
						RoadEditorView.drawButtons(this.gridBuilder, this.panel, this.buttons);
						((JButton)button).setBorder(BorderFactory.createLineBorder(Color.WHITE));
						panel.repaint();
						panel.validate();
						System.out.println("\n handled \n");
					}
					
				} else
				
				if (button == buttons[i][j] && roadGrid[i][j]==0){
					
				panel.invalidate();
				 if ((this.editorState.getState()==RoadEditorConfig.BUILD_STATE && this.editorState.getCanBuild()) || (this.editorState.getState()==RoadEditorConfig.HANDLE_STATE && this.editorState.getCanBuild() && this.editorState.getHandled())){
						this.editorState.setHandled(false);
					 	this.gridBuilder.addRoadBlock(this.editorState.getCurrentBlockType(), i, j);
						
						if (roadGrid[i][j]!=0 && roadGrid[i][j]!=-100 && roadGrid[i][j]!=-200 && roadGrid[i][j]!=-300 && roadGrid[i][j]!=-400 && roadGrid[i][j]!=-500){
							System.out.println("\n builded \n");
							if (this.gridBuilder.getGrid()[i][j]==RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
								ImageIcon background = new ImageIcon( ImagesSelector.selectRoadImageSc(roadGrid[i][j], ib)); 
								((JButton)((JButton)buttons[i][j])).setIcon(background);
								((JButton)buttons[i][j]).setLayout(null);
								((JButton)buttons[i][j]).setSize(GraphicsConfig.BLOCK_SIDE_SIZE*2, GraphicsConfig.BLOCK_SIDE_SIZE);
								((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE*2, GraphicsConfig.BLOCK_SIDE_SIZE));
								this.gridBuilder.getGrid()[i][j] = this.editorState.getCurrentBlockType();
								this.gridBuilder.getGrid()[i+1][j] = -400;
							} else
							if (this.gridBuilder.getGrid()[i][j]==RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
								ImageIcon background = new ImageIcon( ImagesSelector.selectRoadImageSc(roadGrid[i][j], ib)); 
								((JButton)((JButton)buttons[i][j])).setIcon(background);
								((JButton)buttons[i][j]).setLayout(null);
								((JButton)buttons[i][j]).setSize(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE*2);
								((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE*2));
								this.gridBuilder.getGrid()[i][j] = this.editorState.getCurrentBlockType();
								this.gridBuilder.getGrid()[i][j+1] = -500;
							} else
							if(roadGrid[i][j]>30){
								
								ImageIcon background = new ImageIcon( ImagesSelector.selectRoadImageSc(roadGrid[i][j], ib)); 
								((JButton)((JButton)buttons[i][j])).setIcon(background);
								((JButton)buttons[i][j]).setLayout(null);
								((JButton)buttons[i][j]).setSize(GraphicsConfig.BLOCK_SIDE_SIZE*3, GraphicsConfig.BLOCK_SIDE_SIZE*3);
								((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE*3, GraphicsConfig.BLOCK_SIDE_SIZE*3));
								this.gridBuilder.getGrid()[i][j] = this.editorState.getCurrentBlockType();
								this.gridBuilder.getGrid()[i+1][j] = -200;
								this.gridBuilder.getGrid()[i+2][j] = -300;
								this.gridBuilder.getGrid()[i][j+1] = -200;
								this.gridBuilder.getGrid()[i+1][j+1] = -300;
								this.gridBuilder.getGrid()[i+2][j+1] = -200;
								this.gridBuilder.getGrid()[i][j+2] = -300;
								this.gridBuilder.getGrid()[i+1][j+2] = -200;
								this.gridBuilder.getGrid()[i+2][j+2] = -300;
							}
								
								
							else if(roadGrid[i][j]>10){
								
								ImageIcon background = new ImageIcon( ImagesSelector.selectRoadImageSc(roadGrid[i][j], ib)); 
								((JButton)((JButton)buttons[i][j])).setIcon(background);
								((JButton)buttons[i][j]).setLayout(null);
								((JButton)buttons[i][j]).setSize(GraphicsConfig.BLOCK_SIDE_SIZE*2, GraphicsConfig.BLOCK_SIDE_SIZE*2);
								((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE*2, GraphicsConfig.BLOCK_SIDE_SIZE*2));
								this.gridBuilder.getGrid()[i][j] = this.editorState.getCurrentBlockType();
								this.gridBuilder.getGrid()[i+1][j] = -100;
								this.gridBuilder.getGrid()[i+1][j+1] = -100;
								this.gridBuilder.getGrid()[i][j+1] = -100;
								
							}else{
								//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
								ImageIcon background = new ImageIcon( ImagesSelector.selectRoadImageSc(roadGrid[i][j], ib)); 
								((JButton)buttons[i][j]).setIcon(background);
								((JButton)buttons[i][j]).setLayout(null);
								((JButton)buttons[i][j]).setSize(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE);
								((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
								this.gridBuilder.getGrid()[i][j] = this.editorState.getCurrentBlockType();
							}
					} 
					/*else {
						((JButton)buttons[i][j]).setIcon(null);//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
						((JButton)buttons[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
					}*/
					
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
		
		for (int j=0; j<this.gridBuilder.getGrid().length; j++){
			for (int i=0; i<this.gridBuilder.getGrid()[0].length; i++){
				System.out.print(gridBuilder.getGrid()[j][i]+"\t");
			}
			System.out.print("\n");
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
	
	/*
	 * Triple block verification
	 * if (i+2<buttons.length && j+2<buttons[0].length){
							if(
							this.gridBuilder.getGrid()[i+1][j] == 0 &&
							this.gridBuilder.getGrid()[i][j+1] == 0 &&
							this.gridBuilder.getGrid()[i+1][j+1] == 0 &&
							this.gridBuilder.getGrid()[i+2][j] == 0 &&
							this.gridBuilder.getGrid()[i+2][j+1] == 0 &&
							this.gridBuilder.getGrid()[i][j+2] == 0 &&
							this.gridBuilder.getGrid()[i+1][j+2] == 0 &&
							this.gridBuilder.getGrid()[i+2][j+2] == 0 ){}
							}*/

}
