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
 * The Class TrafficLightSetDoubleIntersection.
 */
public class TrafficLightSetDoubleIntersection {

	/** The arr traffic light. */
	ArrayList<TrafficLight> arrTrafficLight;
	
	/** The timer. */
	private int timer;
	
	/** The next traffic light1. */
	private int nextTrafficLight1 = 0;
	
	/** The next traffic light2. */
	private int nextTrafficLight2 ;
	
	/** The parallel traffic light1. */
	private int parallelTrafficLight1;
	
	/** The is yellow state. */
	private boolean isYellowState = false;
	
	/** The green time interval. */
	private int greenTimeInterval = 50;
	
	/** The yellow time interval. */
	private int yellowTimeInterval= 30;
	
	/** The is whole lane. */
	private boolean isWholeLane = false;
	
	/**
	 * Instantiates a new traffic light set double intersection.
	 *
	 * @param arrTrafficLight the arr traffic light
	 */
	public TrafficLightSetDoubleIntersection(ArrayList<TrafficLight> arrTrafficLight){
		this.timer = 0;
		this.calculateIndexOfTrafficLigth();
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
				if (this.isWholeLane){
					this.setGreenColorWholeLaneTurn();
				} else {
					this.setGreenColor();
				}
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
			if(i == nextTrafficLight1 || i == parallelTrafficLight1 ){
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_GREEN);
			}
			else{
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
			}
			this.isWholeLane = true;
			
		}
	}
	
	/**
	 * Sets the green color whole lane turn.
	 */
	public void setGreenColorWholeLaneTurn(){
		for(int i=0; i<arrTrafficLight.size(); i++){
			if(i == nextTrafficLight1 || i == nextTrafficLight2 ){
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_GREEN);
			}
			else{
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
			}
			
			this.isWholeLane = false;
			
		}
	}
	
	/**
	 * Sets the yellow color.
	 */
	public void setYellowColor(){
		
		if (this.isWholeLane){
			
			for(int i=0; i<arrTrafficLight.size(); i++){
				if( i==nextTrafficLight1 || i==nextTrafficLight2 || i == parallelTrafficLight1){
					arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_YELLOW);
				}
				else {
					arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
				}
			 }
			
			
			
		} else {
			
			int currentTrafficLight1 = nextTrafficLight1;
			int currentTrafficLight2 = nextTrafficLight2;
			if (nextTrafficLight1 == arrTrafficLight.size()-2){
				nextTrafficLight1=0;
			}	
			else {
				nextTrafficLight1+=2;
			}
			this.calculateIndexOfTrafficLigth();
		
			for(int i=0; i<arrTrafficLight.size(); i++){
				if( i==currentTrafficLight1 || i==currentTrafficLight2 || i==nextTrafficLight1 || i==parallelTrafficLight1){
					arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_YELLOW);
				}
				else {
					arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
				}
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
		this.nextTrafficLight1 = n;
	}
	
	/**
	 * Calculate index of traffic ligth.
	 */
	public void calculateIndexOfTrafficLigth(){
		nextTrafficLight2 = nextTrafficLight1+1;
		parallelTrafficLight1 = nextTrafficLight1 + 4;
		if(parallelTrafficLight1>7){
			parallelTrafficLight1-=8;
		}
	}
}
