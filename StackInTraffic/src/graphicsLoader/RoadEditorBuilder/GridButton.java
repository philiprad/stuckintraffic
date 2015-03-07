/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader.RoadEditorBuilder;

import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class GridButtons.
 */
public class GridButton extends JButton{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -353987734691087375L;
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The road block size. */
	private int roadBlockSize;
	
	/**
	 * Instantiates a new grid button.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param blockSideSize
	 *            the block side size
	 */
	public GridButton(int x, int y, int blockSideSize) {
		super();
		this.setName("");
		this.x = x;
		this.y = y;
		this.roadBlockSize = blockSideSize;
	}
	
	/**
	 * Gets the road block size.
	 *
	 * @return the road block size
	 */
	public int getRoadBlockSize(){
		return this.roadBlockSize;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getX()
	 */
	public int getX(){
		return this.x;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getY()
	 */
	public int getY(){
		return this.y;
	}
	
}
