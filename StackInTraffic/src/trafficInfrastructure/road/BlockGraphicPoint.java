package trafficInfrastructure.road;

import java.io.Serializable;

public class BlockGraphicPoint implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1673665639861149678L;
	private int x;
	private int y;
	private short blockType;
	
	
	public BlockGraphicPoint(int x, int y, short blockType){
		this.x = x;
		this.y = y;
		this.blockType = blockType;
		
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
}
