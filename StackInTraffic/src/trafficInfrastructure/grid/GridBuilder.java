/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package trafficInfrastructure.grid;
import graphicsLoader.GraphicsConfig;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GridBuilder.
 */
public class GridBuilder implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5959035419524206558L;
	
	/** The grid tab. */
	private short [] [] gridTab ;
	
	/**
	 * Instantiates a new grid builder.
	 *
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public GridBuilder(int width, int height){
		this.gridTab = new short [width][height];
	}
	
	/**
	 * Adds the road block.
	 *
	 * @param blockType
	 *            the block type
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void addRoadBlock(short blockType, int x, int y){
		this.gridTab[x][y] = blockType;
	}
	
	/**
	 * Gets the grid.
	 *
	 * @return the grid
	 */
	public short [] [] getGrid(){
		return this.gridTab;
	}
	
}
