package trafficInfrastructure.grid;
import graphicsLoader.GraphicsConfig;

import java.io.Serializable;

public class GridBuilder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5959035419524206558L;
	private short [] [] gridTab ;
	
	public GridBuilder(){
		this.gridTab = new short [GraphicsConfig.MAIN_WIDTH/GraphicsConfig.BLOCK_SIDE_SIZE][GraphicsConfig.MAIN_HEIGHT/GraphicsConfig.BLOCK_SIDE_SIZE];
	}
	
	public void addRoadBlock(short blockType, int x, int y){
		this.gridTab[x/GraphicsConfig.BLOCK_SIDE_SIZE][y/GraphicsConfig.BLOCK_SIDE_SIZE] = blockType;
	}
	
	public short [] [] getGrid(){
		return this.gridTab;
	}
	
}
