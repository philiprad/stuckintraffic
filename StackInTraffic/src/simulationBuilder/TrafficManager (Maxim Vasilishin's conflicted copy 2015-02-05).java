package simulationBuilder;

import graphicsLoader.GraphicsConfig;
import main.MainConfig;
import trafficInfrastructure.grid.GridBuilder;
import util.FileRW;
import agents.RoadBlock;

public class TrafficManager {
	
	RoadBlock [][] rdBlocks = new RoadBlock [GraphicsConfig.GRID_WIDTH] [GraphicsConfig.GRID_WIDTH];
	
	public TrafficManager(String infrastructureName){
		
		GridBuilder grid = (GridBuilder) FileRW.readObject(MainConfig.GRID_PATH + "/" + infrastructureName + MainConfig.GRID_SUFFIX);
	}
	
	public void buildRdBlockArray (GridBuilder grid){
		
	}

}
