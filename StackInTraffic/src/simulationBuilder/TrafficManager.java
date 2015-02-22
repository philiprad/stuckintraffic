/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package simulationBuilder;

import graphicsLoader.GraphicsConfig;

import java.util.ArrayList;

import main.MainConfig;
import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.RoadConfig;
import util.FileRW;
import agents.AgentConfig;
import agents.RoadBlock;
import agents.TrafficLight;

// TODO: Auto-generated Javadoc
/**
 * The Class TrafficManager.
 */
public class TrafficManager {
	
	/** The rd blocks. */
	private Object [][] rdBlocks = new Object [GraphicsConfig.GRID_WIDTH] [GraphicsConfig.GRID_WIDTH];
	
	/** The traffic infrastructure name. */
	private String trafficInfrastructureName;
	
	/** The arr traffic lights. */
	private ArrayList<TrafficLight> arrTrafficLights = new ArrayList<TrafficLight>();
	
	/** The grid. */
	private GridBuilder grid;
	
	/**
	 * Instantiates a new traffic manager.
	 *
	 * @param infrastructureName
	 *            the infrastructure name
	 */
	public TrafficManager(String infrastructureName){
		
		this.trafficInfrastructureName = infrastructureName;
		this.grid = (GridBuilder) FileRW.readObject(MainConfig.GRID_PATH + "/" + trafficInfrastructureName + MainConfig.GRID_SUFFIX);
		this.buildRoaddBlockArray();
		this.buildTrafficLights();
	}
	
	/**
	 * Builds the roadd block array.
	 */
	public void buildRoaddBlockArray (){
		
		for (int i = 0; i < GraphicsConfig.GRID_WIDTH; i++){
			for (int j = 0; j < GraphicsConfig.GRID_HEIGHT; j++){
				if (grid.getGrid()[i][j]!=0){
					this.rdBlocks [i][j] = new RoadBlock(grid.getGrid()[i][j]);
					System.out.println(((RoadBlock) this.rdBlocks[i][j]).getBlockType());
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
			if (this.rdBlocks[i][j]!=null){
				if (((RoadBlock) this.rdBlocks[i][j]).getBlockType() == RoadConfig.INTERSECTION_BLOCK){
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i][j-1], i, j-1, (short) 1));
					((RoadBlock) this.rdBlocks[i][j-1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i][j+1], i, j+1, (short) 2));
					((RoadBlock) this.rdBlocks[i][j+1]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.ORIGINAL_TRAFFIC_DIRECTION, grid.getGrid()[i-1][j], i-1, j, (short) 3));
					((RoadBlock) this.rdBlocks[i-1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
					
					this.arrTrafficLights.add(new TrafficLight(RoadConfig.INVERSE_TRAFFIC_DIRECTION, grid.getGrid()[i+1][j], i+1, j,(short) 4));
					((RoadBlock) this.rdBlocks[i+1][j]).addTrafficLightIndex(trafficLightCounter);
					trafficLightCounter++;
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
	 * Gets the road block array.
	 *
	 * @return the road block array
	 */
	public Object [][] getRoadBlockArray(){
		return this.rdBlocks;
	}
	
	/**
	 * Intersection1 green light.
	 *
	 * @param number
	 *            the number
	 * @param tl
	 *            the tl
	 */
	public static void intersection1GreenLight(short number, ArrayList<TrafficLight> tl){
		for(int i=0; i<tl.size(); i++){
			if((i+1)%4==number || (number == 4 && (i+1)%4==0)){
				tl.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_GREEN);
			}
			else{
				tl.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
			}
		}
	}
	
	/**
	 * Intersection1 yellow light.
	 *
	 * @param number
	 *            the number
	 * @param tl
	 *            the tl
	 */
	public static void intersection1YellowLight(short number, ArrayList<TrafficLight> tl){
		short nextTrafficLight;
		if (number == 4){
			nextTrafficLight=1;
		}
		else {
			nextTrafficLight=(short) (number+1);
		}
		
		for(int i=0; i<tl.size(); i++){
			if((i+1)%4==number || (number == 4 && (i+1)%4==0)){
				tl.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_YELLOW);
			}
			else if ((i+1)%4==nextTrafficLight || (nextTrafficLight == 4 && (i+1)%4==0)){
				tl.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_YELLOW);
			} else {
				tl.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
			}
		}
		
	}
}
