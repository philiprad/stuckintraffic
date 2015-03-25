package simulationBuilder;

import java.util.ArrayList;

import agents.AgentConfig;
import agents.TrafficLight;

public class TrafficLightSetSingleIntersection {
	
	ArrayList<TrafficLight> arrTrafficLight;
	private int timer;
	private int nextTrafficLight = 0;
	private boolean isYellowState = false;
	private int greenTimeInterval = 100;
	private int yellowTimeInterval= 30;
	
	public TrafficLightSetSingleIntersection(ArrayList<TrafficLight> arrTrafficLight){
		this.timer = 0;
		this.arrTrafficLight = arrTrafficLight;
		this.setGreenColor();
	}
	
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
	
	public void setGreenTimeIntervel(int n){
		this.greenTimeInterval = n;
	}
	
	public void setYellowTimeInterval(int n ){
		this.yellowTimeInterval = n ;
	}
	
	public void setNextTrafficLight(int n, boolean restartTimer){
		if (restartTimer){
			this.timer = 0;
		}
		this.nextTrafficLight = n;
	}
}
