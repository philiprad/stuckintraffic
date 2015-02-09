package simulationBuilder;

import graphicsLoader.GraphicsConfig;

import java.util.ArrayList;

import main.MainConfig;
import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.RoadConfig;
import util.FileRW;
import agents.RoadBlock;
import agents.TrafficLight;

public class TrafficManager {
	
	private RoadBlock [][] rdBlocks = new RoadBlock [GraphicsConfig.GRID_WIDTH] [GraphicsConfig.GRID_WIDTH];
	private String trafficInfrastructureName;
	private ArrayList<TrafficLight> arrTrafficLights = new ArrayList<TrafficLight>();
	private GridBuilder grid;
	
	public TrafficManager(String infrastructureName){
		
		this.trafficInfrastructureName = infrastructureName;
		this.grid = (GridBuilder) FileRW.readObject(MainConfig.GRID_PATH + "/" + trafficInfrastructureName + MainConfig.GRID_SUFFIX);
		this.buildRoaddBlockArray();
		//this.buildTrafficLights();
	}
	
	public void buildRoaddBlockArray (){
		
		for (int i = 0; i < GraphicsConfig.GRID_WIDTH; i++){
			for (int j = 0; j < GraphicsConfig.GRID_HEIGHT; j++){
				if (grid.getGrid()[i][j]!=0){
					this.rdBlocks [i][j] = new RoadBlock(grid.getGrid()[i][j]);
					System.out.println(this.rdBlocks[i][j].getBlockType());
				}
				
				else {
					this.rdBlocks [i][j] = null; 
				}
			}
		}
	}
	
	/**
	 * Builds the traffic lights.
	 */
	public void buildTrafficLights (){
		short trafficLightCounter = 0;
		for (int i = 0; i < GraphicsConfig.GRID_WIDTH; i++){
			for (int j = 0; j < GraphicsConfig.GRID_HEIGHT; j++){
			if (this.rdBlocks[i][j] != null){
				if (this.rdBlocks[i][j].getBlockType() == RoadConfig.INTERSECTION_BLOCK){
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-1], i, j-1));
					this.rdBlocks[i][j-1].addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION_DIRECTION, grid.getGrid()[i][j+1], i, j+1));
					this.rdBlocks[i][j+1].addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-1][j], i-1, j));
					this.rdBlocks[i-1][j].addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION_DIRECTION, grid.getGrid()[i+1][j], i+1, j));
					this.rdBlocks[i-1][j].addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
				}
			}
		}
		}
		
		
			System.out.println(this.arrTrafficLights.size());
	}

}
