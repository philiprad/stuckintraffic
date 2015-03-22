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
	
	/** The line position x. */
	private int linePositionX;
	
	/** The line position y. */
	private int linePositionY;
	/** The traffic light state. */
	private short trafficLightState = AgentConfig.TRAFFIC_LIGHT_RED;
	
	/** The number. */
	private short number;
	
	private short type;
	
	/**
	 * Instantiates a new traffic light.
	 *
	 * @param direction the direction
	 * @param roadBlock the road block
	 * @param x the x
	 * @param y the y
	 * @param number the number
	 */
	public TrafficLight(short direction, short roadBlock , int x , int y,short type, short number){
		this.type = type;
		this.number = number;
		this.direction = direction;
		this.gridX = x;
		this.gridY = y;
		this.roadBlock = roadBlock;
		
	}
	
	/**
	 * Gets the distance to traffic light.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return the distance to traffic light
	 */
	public int getDistanceToTrafficLight(int x, int y){
		if (this.roadBlock == RoadConfig.HORIZONTAL_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_EXIT_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_DOUBLE_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK ){
			if (this.direction==RoadConfig.ORIGINAL_TRAFFIC_DIRECTION){
				return Math.abs(this.gridX*GraphicsConfig.BLOCK_SIDE_SIZE+49 - x)-GraphicsConfig.CAR_LENGTH/2;
			}
			else {
				return Math.abs(this.gridX*GraphicsConfig.BLOCK_SIDE_SIZE - x)-GraphicsConfig.CAR_LENGTH/2;
			}
		}
		else if (this.roadBlock == RoadConfig.VERTICAL_BLOCK || this.roadBlock == RoadConfig.VERTICAL_ENTER_BLOCK || this.roadBlock == RoadConfig.VERTICAL_EXIT_BLOCK || this.roadBlock == RoadConfig.VERTICAL_DOUBLE_BLOCK || this.roadBlock == RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || this.roadBlock == RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
			if (this.direction==RoadConfig.ORIGINAL_TRAFFIC_DIRECTION){
				return Math.abs(this.gridY*GraphicsConfig.BLOCK_SIDE_SIZE+49 - y)-GraphicsConfig.CAR_LENGTH/2;
			}
			else {
				return Math.abs(this.gridY*GraphicsConfig.BLOCK_SIDE_SIZE - y)-GraphicsConfig.CAR_LENGTH/2;
			}
		}
		
		return 0;
	}
	
	/**
	 * Draw traffic lights.
	 *
	 * @param g the g
	 * @param ib the ib
	 */
	public void drawTrafficLights(Graphics g, ImagesBuilder ib){
		
		Image trafficLight;
		
		if  (this.roadBlock == RoadConfig.HORIZONTAL_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_EXIT_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_DOUBLE_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK){
			if(this.trafficLightState == AgentConfig.TRAFFIC_LIGHT_RED){
				trafficLight = ib.getRedLightV();
			} else if(this.trafficLightState == AgentConfig.TRAFFIC_LIGHT_GREEN){
				trafficLight = ib.getGreenLightV();
			}
			else {
				trafficLight = ib.getYellowLightV();
			}
			if (this.direction == RoadConfig.ORIGINAL_TRAFFIC_DIRECTION){
				if (this.type == AgentConfig.TRAFFIC_LIGHT_1_LANE){
					g.drawImage(trafficLight,this.gridX*50+GraphicsConfig.TRAFFIC_LIGHT_POSITION_WIDTH_DISTANCE,this.gridY*50, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH,null );
				} else {
					g.drawImage(trafficLight,this.gridX*50+GraphicsConfig.TRAFFIC_LIGHT_POSITION_WIDTH_DISTANCE,this.gridY*50+GraphicsConfig.BLOCK_SIDE_SIZE/2, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH,null );
				}
			}
			else {
				if (this.type == AgentConfig.TRAFFIC_LIGHT_1_LANE){
					g.drawImage(trafficLight,this.gridX*50,this.gridY*50+GraphicsConfig.TRAFFIC_LIGHT_POSITION_LENGTH_DISTANCE, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH,null );
				} else {
					g.drawImage(trafficLight,this.gridX*50,this.gridY*50, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH,null );
					
				}
			}
		}
		
		if  (this.roadBlock == RoadConfig.VERTICAL_BLOCK || this.roadBlock == RoadConfig.VERTICAL_ENTER_BLOCK || this.roadBlock == RoadConfig.VERTICAL_EXIT_BLOCK || this.roadBlock == RoadConfig.VERTICAL_DOUBLE_BLOCK || this.roadBlock == RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || this.roadBlock == RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
			if(this.trafficLightState == AgentConfig.TRAFFIC_LIGHT_RED){
				trafficLight = ib.getRedLightH();
			} else if(this.trafficLightState == AgentConfig.TRAFFIC_LIGHT_GREEN){
				trafficLight = ib.getGreenLightH();
			}
			else {
				trafficLight = ib.getYellowLightH();
			}
			if (this.direction == RoadConfig.ORIGINAL_TRAFFIC_DIRECTION){
				if (this.type == AgentConfig.TRAFFIC_LIGHT_1_LANE){
					g.drawImage(trafficLight,this.gridX*50+GraphicsConfig.TRAFFIC_LIGHT_POSITION_LENGTH_DISTANCE,this.gridY*50+GraphicsConfig.TRAFFIC_LIGHT_POSITION_WIDTH_DISTANCE, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH,null );
				} else {
					g.drawImage(trafficLight,this.gridX*50,this.gridY*50+GraphicsConfig.TRAFFIC_LIGHT_POSITION_WIDTH_DISTANCE, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH,null );
				}
			} else {
				if (this.type == AgentConfig.TRAFFIC_LIGHT_1_LANE){
					g.drawImage(trafficLight,this.gridX*50,this.gridY*50, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH,null );
				} else {
					g.drawImage(trafficLight,this.gridX*50+GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH,this.gridY*50, GraphicsConfig.TRAFFIC_LIGHT_LINE_LENGTH, GraphicsConfig.TRAFFIC_LIGHT_LINE_WIDTH,null );
				}
				
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
	
	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public short getDirection(){
		return  this.direction;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public short getState(){
		return this.trafficLightState;
	}
}
