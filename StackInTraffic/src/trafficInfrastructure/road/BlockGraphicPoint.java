/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package trafficInfrastructure.road;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockGraphicPoint.
 */
public class BlockGraphicPoint implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1673665639861149678L;
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The block type. */
	private short blockType;
	
	/** The block size. */
	private int blockHorizontalSize;
	
	/** The block vertical size. */
	private int blockVerticalSize;
	
	
	/**
	 * Instantiates a new block graphic point.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param blockType
	 *            the block type
	 * @param blockHorizontalSize
	 *            the block horizontal size
	 * @param blockVerticalSize
	 *            the block vertical size
	 */
	public BlockGraphicPoint(int x, int y, short blockType, int blockHorizontalSize, int blockVerticalSize){
		this.x = x;
		this.y = y;
		this.blockType = blockType;
		this.blockHorizontalSize = blockHorizontalSize;
		this.blockVerticalSize = blockVerticalSize;
		
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY(){
		return this.y;
	}
	
	/**
	 * Gets the block type.
	 *
	 * @return the block type
	 */
	public short getBlockType(){
		return this.blockType;
	}
	
	/**
	 * Gets the block size.
	 *
	 * @return the block size
	 */
	public int getBlockHorizontalSize(){
		return this.blockHorizontalSize;
	}
	
	/**
	 * Gets the block vertical size.
	 *
	 * @return the block vertical size
	 */
	public int getBlockVerticalSize(){
		return this.blockVerticalSize;
	}
}
