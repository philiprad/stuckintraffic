/*
 * @author  Maxim Vasilishin, Fillipos
 * @version 1.0
 */
package graphicsLoader.RoadEditorBuilder;

import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class GridButtons.
 */
public class GridButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -353987734691087375L;
	private int x;
	private int y;
	private int roadBlockSize;
	
	public GridButton(int x, int y, int blockSideSize) {
		super();
		this.setName("");
		this.x = x;
		this.y = y;
		this.roadBlockSize = blockSideSize;
	}
	
	public int getRoadBlockSize(){
		return this.roadBlockSize;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
}
