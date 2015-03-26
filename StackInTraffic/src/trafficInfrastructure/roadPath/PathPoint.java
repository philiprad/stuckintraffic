/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package trafficInfrastructure.roadPath;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class PathPoint.
 */
public class PathPoint implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 814825524866788188L;
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The block type. */
	private short blockType;
	
	/** The rot. */
	private int rot;
	
	/** The direction. */
	private int direction;
	
	/** The round about center x. */
	private int roundAboutCenterX = 0;
	
	/** The round about center y. */
	private int roundAboutCenterY = 0;
	
	/** The exit. */
	private int exit = 0;
	
	/**
	 * Instantiates a new path point.
	 *
	 * @param blockType
	 *            the block type
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param direction
	 *            the direction
	 * @param rot
	 *            the rot
	 */
	public PathPoint (short blockType, int x, int y, int direction, int rot){
		this.rot = rot;
		this.x = x;
		this.y = y;
		this.blockType = blockType;
		this.direction = direction;
		
		
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
	 * Gets the rotation.
	 *
	 * @return the rotation
	 */
	public int getRotation(){
		return this.rot;
	}
	
	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public int getDirection(){
		return direction;
	}
	
	/**
	 * Sets the round about center x.
	 *
	 * @param x the new round about center x
	 */
	public void setRoundAboutCenterX( int x){
		this.roundAboutCenterX = x;
	}
	
	/**
	 * Sets the round about center y.
	 *
	 * @param y the new round about center y
	 */
	public void setRoundAboutCenterY(int y){
		this.roundAboutCenterY = y;
	}
	
	/**
	 * Gets the round about center x.
	 *
	 * @return the round about center x
	 */
	public int getRoundAboutCenterX(){
		return this.roundAboutCenterX;
	}
	
	/**
	 * Gets the round about center y.
	 *
	 * @return the round about center y
	 */
	public int getRoundAboutCenterY(){
		return this.roundAboutCenterY;
	}
	
	/**
	 * Sets the round about exit.
	 *
	 * @param i the new round about exit
	 */
	public void setRoundAboutExit(int i){
		this.exit = i;
	}
	
	/**
	 * Gets the round about exit.
	 *
	 * @return the round about exit
	 */
	public int getRoundAboutExit(){
		return this.exit;
	}
	
}
