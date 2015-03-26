/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package simulationBuilder;

import java.util.ArrayList;

import agents.AgentConfig;
import agents.TrafficLight;

// TODO: Auto-generated Javadoc
/**
 * The Class TrafficLightSetSingleIntersection.
 */
public class TrafficLightSetSingleIntersection {
	
	/** The arr traffic light. */
	ArrayList<TrafficLight> arrTrafficLight;
	
	/** The timer. */
	private int timer;
	
	/** The next traffic light. */
	private int nextTrafficLight = 0;
	
	/** The is yellow state. */
	private boolean isYellowState = false;
	
	/** The green time interval. */
	private int greenTimeInterval = 100;
	
	/** The yellow time interval. */
	private int yellowTimeInterval= 30;
	
	/**
	 * Instantiates a new traffic light set single intersection.
	 *
	 * @param arrTrafficLight the arr traffic light
	 */
	public TrafficLightSetSingleIntersection(ArrayList<TrafficLight> arrTrafficLight){
		this.timer = 0;
		this.arrTrafficLight = arrTrafficLight;
		this.setGreenColor();
	}
	
	/**
	 * Update state.
	 */
	public void updateState(){
		this.timer++;
		if (isYellowState){
			if(this.timer == yellowTimeInterval){
				this.isYellowState = false;
				this.timer = 0;
				this.setGreenColor();
			}
			
		} else {
			if(this.timer == greenTimeInterval){
				this.isYellowState = true;
				this.timer = 0;
				this.setYellowColor();
			}
		}
		
		
	}
	
	/**
	 * Sets the green color.
	 */
	public void setGreenColor(){
		for(int i=0; i<arrTrafficLight.size(); i++){
			if(i == nextTrafficLight){
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_GREEN);
			}
			else{
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
			}
		}
	}
	
	/**
	 * Sets the yellow color.
	 */
	public void setYellowColor(){
		int currentTrafficLight = nextTrafficLight;
		if (nextTrafficLight == arrTrafficLight.size()-1){
			nextTrafficLight=0;
		}
		else {
			nextTrafficLight++;
		}
		
		for(int i=0; i<arrTrafficLight.size(); i++){
			if( i==currentTrafficLight || i==nextTrafficLight){
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_YELLOW);
			}
			else {
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
			}
		}
		
	}
	
	/**
	 * Sets the green time intervel.
	 *
	 * @param n the new green time intervel
	 */
	public void setGreenTimeIntervel(int n){
		this.greenTimeInterval = n;
	}
	
	/**
	 * Sets the yellow time interval.
	 *
	 * @param n the new yellow time interval
	 */
	public void setYellowTimeInterval(int n ){
		this.yellowTimeInterval = n ;
	}
	
	/**
	 * Sets the next traffic light.
	 *
	 * @param n the n
	 * @param restartTimer the restart timer
	 */
	public void setNextTrafficLight(int n, boolean restartTimer){
		if (restartTimer){
			this.timer = 0;
		}
		this.nextTrafficLight = n;
	}
}
