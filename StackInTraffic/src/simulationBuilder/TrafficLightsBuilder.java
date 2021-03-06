/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package simulationBuilder;

import java.util.ArrayList;

import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.RoadConfig;
import agents.AgentConfig;
import agents.RoadBlock;
import agents.TrafficLight;

// TODO: Auto-generated Javadoc
/**
 * The Class TrafficLightsBuilder.
 */
public class TrafficLightsBuilder {
	
	/** The arr traffic lights. */
	private ArrayList<TrafficLight> arrTrafficLights = new ArrayList<TrafficLight>();
	
	/** The arr traffic light set single intersection. */
	private ArrayList<TrafficLightSetSingleIntersection> arrTrafficLightSetSingleIntersection = new ArrayList<TrafficLightSetSingleIntersection>();
	
	/** The arr traffic light set double intersection. */
	private ArrayList<TrafficLightSetDoubleIntersection> arrTrafficLightSetDoubleIntersection = new ArrayList<TrafficLightSetDoubleIntersection>();
	
	/** The arr traffic light set mixed intersection. */
	private ArrayList<TrafficLightSetMixedIntersection> arrTrafficLightSetMixedIntersection = new ArrayList<TrafficLightSetMixedIntersection>();
	
	/** The grid. */
	private GridBuilder grid;
	
	/** The rd blocks. */
	private Object [] [] rdBlocks;
	
	/**
	 * Instantiates a new traffic lights builder.
	 *
	 * @param grid the grid
	 * @param rdBlocks the rd blocks
	 */
	public TrafficLightsBuilder( GridBuilder grid, Object [][] rdBlocks){
		this.grid = grid;
		this.rdBlocks = rdBlocks;
	}
	
	/**
	 * Builds the traffic lights.
	 */
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
					
					ArrayList<TrafficLight> tempTrafficLightList = new ArrayList<TrafficLight>();
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-1], i, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1);
					tempTrafficLightList.add(trafficLight);
					this.arrTrafficLights.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 2);
					tempTrafficLightList.add(trafficLight);
					this.arrTrafficLights.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-1][j], i-1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 3);
					tempTrafficLightList.add(trafficLight);
					this.arrTrafficLights.add(trafficLight);
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLightSetSingleIntersection.add(new TrafficLightSetSingleIntersection(tempTrafficLightList));
					
					
				} else if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_RIGHT_BLOCK){
					
					ArrayList<TrafficLight> tempTrafficLightList = new ArrayList<TrafficLight>();
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-1], i, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1);
					tempTrafficLightList.add(trafficLight);
					this.arrTrafficLights.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 2);
					tempTrafficLightList.add(trafficLight);
					this.arrTrafficLights.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 4);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLightSetSingleIntersection.add(new TrafficLightSetSingleIntersection(tempTrafficLightList));
					
				} else
				
				if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_UP_BLOCK){
					
					ArrayList<TrafficLight> tempTrafficLightList = new ArrayList<TrafficLight>();
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-1], i, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-1][j], i-1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 3);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 4);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLightSetSingleIntersection.add(new TrafficLightSetSingleIntersection(tempTrafficLightList));
					
					
				} else
				
				if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_DOWN_BLOCK){
					
					ArrayList<TrafficLight> tempTrafficLightList = new ArrayList<TrafficLight>();
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 2); 
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-1][j], i-1, j, AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 3);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 4);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLightSetSingleIntersection.add(new TrafficLightSetSingleIntersection(tempTrafficLightList));
					
				} else 
				
				if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_DOUBLE_BLOCK){
					
					ArrayList<TrafficLight> tempTrafficLightList = new ArrayList<TrafficLight>();
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-2], i+1, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+1][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight= new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-2], i+1, j-1,AgentConfig.TRAFFIC_LIGHT_2_LANE, (short) 2);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+1][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-2][j], i-1, j,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 2);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-2][j], i-1, j, AgentConfig.TRAFFIC_LIGHT_2_LANE,(short) 6);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+2], i, j+2,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 3);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j+2]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+2], i, j+2,AgentConfig.TRAFFIC_LIGHT_2_LANE, (short) 4);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j+2]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+2][j], i+2, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 7);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+2][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+2][j], i+2, j+1,AgentConfig.TRAFFIC_LIGHT_2_LANE,(short) 8);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+2][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
				
					this.arrTrafficLightSetDoubleIntersection.add(new TrafficLightSetDoubleIntersection(tempTrafficLightList));
					
				
				} 
			 else 
				
				if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
					
					ArrayList<TrafficLight> tempTrafficLightList = new ArrayList<TrafficLight>();
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-2], i+1, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+1][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-2], i+1, j-1,AgentConfig.TRAFFIC_LIGHT_2_LANE, (short) 2);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+1][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-1][j], i-1, j, AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 5);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 3);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1,AgentConfig.TRAFFIC_LIGHT_2_LANE, (short) 4);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					
				
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+2][j], i+2, j,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 7);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+2][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLightSetMixedIntersection.add(new TrafficLightSetMixedIntersection(tempTrafficLightList));
					
					
				} else if (this.grid.getGrid()[i][j] == RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
					
					ArrayList<TrafficLight> tempTrafficLightList = new ArrayList<TrafficLight>();
					
					
			
							
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-2][j], i-1, j, AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 5);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-2][j], i-1, j, AgentConfig.TRAFFIC_LIGHT_2_LANE,(short) 6);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+2], i, j+2,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 3);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j+2]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j+1,AgentConfig.TRAFFIC_LIGHT_1_LANE,(short) 7);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+1][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j+1,AgentConfig.TRAFFIC_LIGHT_2_LANE,(short) 8);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i+1][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					trafficLight = new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-1], i, j-1,AgentConfig.TRAFFIC_LIGHT_1_LANE, (short) 1);
					this.arrTrafficLights.add(trafficLight);
					tempTrafficLightList.add(trafficLight);
					((RoadBlock) this.rdBlocks[i][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLightSetMixedIntersection.add(new TrafficLightSetMixedIntersection(tempTrafficLightList));
					
				} 
			}
		}
		
	}
		
		
			System.out.println(this.arrTrafficLights.size());
	}
	
	/**
	 * Gets the traffic light list.
	 *
	 * @return the traffic light list
	 */
	public ArrayList<TrafficLight> getTrafficLightList(){
		return this.arrTrafficLights;
	}
	
	/**
	 * Gets the traffic light set single list.
	 *
	 * @return the traffic light set single list
	 */
	public ArrayList<TrafficLightSetSingleIntersection> getTrafficLightSetSingleList(){
		return this.arrTrafficLightSetSingleIntersection;
	}
	
	/**
	 * Gets the traffic light set double list.
	 *
	 * @return the traffic light set double list
	 */
	public ArrayList<TrafficLightSetDoubleIntersection> getTrafficLightSetDoubleList(){
		return this.arrTrafficLightSetDoubleIntersection;
	}
	
	/**
	 * Gets the traffic light set mixed list.
	 *
	 * @return the traffic light set mixed list
	 */
	public ArrayList<TrafficLightSetMixedIntersection> getTrafficLightSetMixedList(){
		return this.arrTrafficLightSetMixedIntersection;
	}

}
