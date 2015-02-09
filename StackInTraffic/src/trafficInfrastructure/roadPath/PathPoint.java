package trafficInfrastructure.roadPath;

import java.io.Serializable;

public class PathPoint implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 814825524866788188L;
	private int x;
	private int y;
	private short blockType;
	private int rot;
	private int direction;
	
	public PathPoint (short blockType, int x, int y, int direction, int rot){
		this.rot = rot;
		this.x = x;
		this.y = y;
		this.blockType = blockType;
		this.direction = direction;
		
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public short getBlockType(){
		return this.blockType;
	}
	
	public int getRotation(){
		return this.rot;
	}
	
	public int getDirection(){
		return direction;
	}
	
}
