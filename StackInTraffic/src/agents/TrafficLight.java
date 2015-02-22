/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package agents;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;

import java.awt.Graphics;
import java.awt.Image;

import trafficInfrastructure.road.RoadConfig;


// TODO: Auto-generated Javadoc
/**
 * The Class TrafficLight.
 */
public class TrafficLight {

	/** The direction. */
	private short direction;
	
	/** The road block. */
	private short roadBlock;
	
	/** The grid x. */
	private int gridX;
	
	/** The grid y. */
	private int gridY;
	//private int linePositionX;
	//private int linePositionY;
	/** The traffic light state. */
	private short trafficLightState = AgentConfig.TRAFFIC_LIGHT_RED;
	
	/** The number. */
	private short number;
	
	/**
	 * Instantiates a new traffic light.
	 *
	 * @param direction the direction
	 * @param roadBlock the road block
	 * @param x the x
	 * @param y the y
	 * @param number the number
	 */
	public TrafficLight(short direction, short roadBlock , int x , int y, short number){
		this.number = number;
		this.direction = direction;
		//this.linePositionX = linePositionX;
		//this.linePositionY = linePositionY;
		this.gridX = x;
		this.gridY = y;
		this.roadBlock = roadBlock;
		
	}
	
	/*public int getDistanceToTrafficLight(int x, int y){
		if (this.roadBlock == RoadConfig.HORIZONTAL_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_EXIT_BLOCK){
			return Math.abs(this.linePositionX - x);
		}
		else {
			return Math.abs(this.linePositionY - y);
		}
	}*/
	
	/**
	 * Draw traffic lights.
	 *
	 * @param g the g
	 * @param ib the ib
	 */
	public void drawTrafficLights(Graphics g, ImagesBuilder ib){
		
		Image trafficLight;
		
		if  (this.roadBlock == RoadConfig.HORIZONTAL_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_EXIT_BLOCK){
			if(this.trafficLightState == AgentConfig.TRAFFIC_LIGHT_RED){
				trafficLight = ib.getRedLightV();
			} else if(this.trafficLightState == AgentConfig.TRAFFIC_LIGHT_GREEN){
				trafficLight = ib.getGreenLightV();
			}
			else {
				trafficLight = ib.getYellowLightV();
			}
			if (this.direction == RoadConfig.ORIGINAL_TRAFFIC_DIRECTION){
				g.drawImage(trafficLight,this.gridX*50+GraphicsConfig.TRAFFIC_LIGHT_POSITION_WIDTH_DISTANCE,this.gridY*50, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH,null );
			}
			else {
				g.drawImage(trafficLight,this.gridX*50,this.gridY*50+GraphicsConfig.TRAFFIC_LIGHT_POSITION_LENGTH_DISTANCE, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH,null );
			}
		}
		
		if  (this.roadBlock == RoadConfig.VERTICAL_BLOCK || this.roadBlock == RoadConfig.VERTICAL_ENTER_BLOCK || this.roadBlock == RoadConfig.VERTICAL_EXIT_BLOCK){
			if(this.trafficLightState == AgentConfig.TRAFFIC_LIGHT_RED){
				trafficLight = ib.getRedLightH();
			} else if(this.trafficLightState == AgentConfig.TRAFFIC_LIGHT_GREEN){
				trafficLight = ib.getGreenLightH();
			}
			else {
				trafficLight = ib.getYellowLightH();
			}
			if (this.direction == RoadConfig.ORIGINAL_TRAFFIC_DIRECTION){
				g.drawImage(trafficLight,this.gridX*50+GraphicsConfig.TRAFFIC_LIGHT_POSITION_LENGTH_DISTANCE,this.gridY*50+GraphicsConfig.TRAFFIC_LIGHT_POSITION_WIDTH_DISTANCE, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH,null );
			}
			else {
				g.drawImage(trafficLight,this.gridX*50,this.gridY*50, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH,null );
			}
		}
	}
	
	/**
	 * Change traffic light state.
	 *
	 * @param state the state
	 */
	public void changeTrafficLightState(short state){
		this.trafficLightState = state;
	}
}
