package simulationBuilder;

import java.util.ArrayList;

import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.RoadConfig;
import agents.AgentConfig;
import agents.RoadBlock;
import agents.TrafficLight;

public class TrafficLightsBuilder {
	
	private ArrayList<TrafficLight> arrTrafficLights = new ArrayList<TrafficLight>();
	private ArrayList<TrafficLightSetSingleIntersection> arrTrafficLightSetSingleIntersection = new ArrayList<TrafficLightSetSingleIntersection>();
	private GridBuilder grid;
	private Object [] [] rdBlocks;
	
	public TrafficLightsBuilder( GridBuilder grid, Object [][] rdBlocks){
		this.grid = grid;
		this.rdBlocks = rdBlocks;
	}
	
	public void buildTrafficLights (){
		short trafficLightCounter = 0;
		TrafficLight trafficLight;
		for (int i = 0; i < this.grid.getGrid().length; i++){
			for (int j = 0; j < this.grid.getGrid()[0].length; j++){
			if (this.rdBlocks[i][j]!=null){
				if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_BLOCK){
					ArrayList<TrafficLight> tempTrafficLightList = new ArrayList<TrafficLight>();
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-1], i, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1);
					tempTrafficLightList.add(trafficLight);
					this.arrTrafficLights.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight =new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 2);
					tempTrafficLightList.add(trafficLight);
					this.arrTrafficLights.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-1][j], i-1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 3);
					tempTrafficLightList.add(trafficLight);
					this.arrTrafficLights.add(trafficLight);
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 4);
					tempTrafficLightList.add(trafficLight);
					this.arrTrafficLights.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					this.arrTrafficLightSetSingleIntersection.add(new TrafficLightSetSingleIntersection(tempTrafficLightList));
					
				
				}  else if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_LEFT_BLOCK) {
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-1], i, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1));
					((RoadBlock) this.rdBlocks[i][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 2));
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-1][j], i-1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 3));
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					
				} else if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_RIGHT_BLOCK){
					
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-1], i, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1));
					((RoadBlock) this.rdBlocks[i][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 2));
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 4));
					((RoadBlock) this.rdBlocks[i+1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
				} else
				
				if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_UP_BLOCK){
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-1], i, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1));
					((RoadBlock) this.rdBlocks[i][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-1][j], i-1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 3));
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 4));
					((RoadBlock) this.rdBlocks[i+1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
				} else
				
				if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_DOWN_BLOCK){
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 2));
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-1][j], i-1, j, AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 3));
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 4));
					((RoadBlock) this.rdBlocks[i+1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
				} else 
				
				if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_DOUBLE_BLOCK){
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-2], i+1, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1));
					((RoadBlock) this.rdBlocks[i+1][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-2], i+1, j-1,AgentConfig.TRAFFIC_LIGHT_2_LANE, (short) 2));
					((RoadBlock) this.rdBlocks[i+1][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+2], i, j+2,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 3));
					((RoadBlock) this.rdBlocks[i][j+2]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+2], i, j+2,AgentConfig.TRAFFIC_LIGHT_2_LANE, (short) 4));
					((RoadBlock) this.rdBlocks[i][j+2]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-2][j], i-1, j, AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 5));
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-2][j], i-1, j, AgentConfig.TRAFFIC_LIGHT_2_LANE,(short) 6));
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+2][j], i+2, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 7));
					((RoadBlock) this.rdBlocks[i+2][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+2][j], i+2, j+1,AgentConfig.TRAFFIC_LIGHT_2_LANE,(short) 8));
					((RoadBlock) this.rdBlocks[i+2][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
				} 
			 else 
				
				if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-2], i+1, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1));
					((RoadBlock) this.rdBlocks[i+1][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-2], i+1, j-1,AgentConfig.TRAFFIC_LIGHT_2_LANE, (short) 2));
					((RoadBlock) this.rdBlocks[i+1][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 3));
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1,AgentConfig.TRAFFIC_LIGHT_2_LANE, (short) 4));
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-1][j], i-1, j, AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 5));
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+2][j], i+2, j,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 7));
					((RoadBlock) this.rdBlocks[i+2][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
				} else if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-1], i, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1));
					((RoadBlock) this.rdBlocks[i][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					
					
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+2], i, j+2,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 3));
					((RoadBlock) this.rdBlocks[i][j+2]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					
					
					
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-2][j], i-1, j, AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 5));
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-2][j], i-1, j, AgentConfig.TRAFFIC_LIGHT_2_LANE,(short) 6));
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 7));
					((RoadBlock) this.rdBlocks[i+1][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j+1,AgentConfig.TRAFFIC_LIGHT_2_LANE,(short) 8));
					((RoadBlock) this.rdBlocks[i+1][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
				} 
			}
		}
		
	}
		
		
			System.out.println(this.arrTrafficLights.size());
	}
	
	public ArrayList<TrafficLight> getTrafficLightList(){
		return this.arrTrafficLights;
	}
	
	public ArrayList<TrafficLightSetSingleIntersection> getTrafficLightSetSingleList(){
		return this.arrTrafficLightSetSingleIntersection;
	}

}
