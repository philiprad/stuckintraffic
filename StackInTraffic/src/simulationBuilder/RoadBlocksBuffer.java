/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package simulationBuilder;

import main.MainConfig;
import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.RoadConfig;
import util.FileRW;
import agents.RoadBlock;

// TODO: Auto-generated Javadoc
/**
 * The Class RoadBlocksBuffer.
 */
public class RoadBlocksBuffer {
	/** The rd blocks. */
	private Object [][] rdBlocks;
	
	/** The traffic infrastructure name. */
	private String trafficInfrastructureName;
	
	/** The grid. */
	private GridBuilder grid;
	
	/**
	 * Instantiates a new traffic manager.
	 *
	 * @param infrastructureName
	 *            the infrastructure name
	 */
	public RoadBlocksBuffer(String infrastructureName){
		
		this.trafficInfrastructureName = infrastructureName;
		this.grid = (GridBuilder) FileRW.readObject(MainConfig.GRID_PATH + "/" + trafficInfrastructureName + MainConfig.GRID_SUFFIX);
		rdBlocks = new Object [this.grid.getGrid().length] [this.grid.getGrid()[0].length];
		this.buildRoadBlockArray();
	}
	
	/**
	 * Builds the road block array.
	 */
	public void buildRoadBlockArray (){
		
		for (int i = 0; i < this.grid.getGrid().length; i++){
			for (int j = 0; j < this.grid.getGrid()[0].length; j++){
				if (grid.getGrid()[i][j]!=0 && grid.getGrid()[i][j]!=-100 && grid.getGrid()[i][j]!=-200 && grid.getGrid()[i][j]!=-300 && grid.getGrid()[i][j]!=-400 && grid.getGrid()[i][j]!=-500){
					this.rdBlocks [i][j] = new RoadBlock(grid.getGrid()[i][j]);
					if (grid.getGrid()[i][j]== RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
						this.rdBlocks [i+1][j] = new RoadBlock(grid.getGrid()[i][j]);
					} else if (grid.getGrid()[i][j]== RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK) {
						this.rdBlocks [i][j+1] = new RoadBlock(grid.getGrid()[i][j]);
					} else if (grid.getGrid()[i][j] > 30) {
						this.rdBlocks [i+1][j] = new RoadBlock(grid.getGrid()[i][j]);
						this.rdBlocks [i+2][j] = new RoadBlock(grid.getGrid()[i][j]);
						this.rdBlocks [i][j+1] = new RoadBlock(grid.getGrid()[i][j]);
						this.rdBlocks [i+1][j+1] = new RoadBlock(grid.getGrid()[i][j]);
						this.rdBlocks [i+2][j+1] = new RoadBlock(grid.getGrid()[i][j]);
						this.rdBlocks [i][j+2] = new RoadBlock(grid.getGrid()[i][j]);
						this.rdBlocks [i+1][j+2] = new RoadBlock(grid.getGrid()[i][j]);
						this.rdBlocks [i+2][j+2] = new RoadBlock(grid.getGrid()[i][j]);
					} else if (grid.getGrid()[i][j] > 10) {
						this.rdBlocks [i+1][j] = new RoadBlock(grid.getGrid()[i][j]);
						this.rdBlocks [i][j+1] = new RoadBlock(grid.getGrid()[i][j]);
						this.rdBlocks [i+1][j+1] = new RoadBlock(grid.getGrid()[i][j]);
					}
					System.out.println(((RoadBlock) this.rdBlocks[i][j]).getBlockType());
				} 
				
				else {
					if(grid.getGrid()[i][j]!=-100 && grid.getGrid()[i][j]!=-200 && grid.getGrid()[i][j]!=-300 && grid.getGrid()[i][j]!=-400 && grid.getGrid()[i][j]!=-500)
					this.rdBlocks [i][j] = null; 
				}
			}
		}
	}
	
	/**
	 * Gets the road block buffer array.
	 *
	 * @return the road block buffer array
	 */
	public Object [][] getRoadBlockBufferArray(){
		return this.rdBlocks;
	}
	
}
