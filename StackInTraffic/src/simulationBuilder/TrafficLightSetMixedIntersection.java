package simulationBuilder;

import java.util.ArrayList;

import agents.AgentConfig;
import agents.TrafficLight;

public class TrafficLightSetMixedIntersection {
	ArrayList<TrafficLight> arrTrafficLight;
	private int timer;
	private int nextTrafficLight1 = 0;
	private int nextTrafficLight2 ;
	private int parallelTrafficLight1;
	private boolean isYellowState = false;
	private int greenTimeInterval = 50;
	private int yellowTimeInterval= 30;
	private boolean isWholeLane = false;
	private boolean isSingleLane = false;
	private int nextTrafficLight;
	
	public TrafficLightSetMixedIntersection(ArrayList<TrafficLight> arrTrafficLight){
		this.timer = 0;
		this.calculateIndexOfTrafficLigth();
		this.arrTrafficLight = arrTrafficLight;
		this.setGreenColor();
	}
	
	public void updateState(){
		this.timer++;
		if (isYellowState){
			if(this.timer == yellowTimeInterval){
				this.isYellowState = false;
				this.timer = 0;
				
				if (this.isSingleLane){
					this.setGreenColorSingleLane();
				} else {
					if (this.isWholeLane){
						this.setGreenColorWholeLaneTurn();
					} else {
						this.setGreenColor();
					}
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
	
	public void setGreenColorWholeLaneTurn(){
		for(int i=0; i<arrTrafficLight.size(); i++){
			if(i == nextTrafficLight1 || i == nextTrafficLight2 ){
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_GREEN);
			}
			else{
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
			}
			
			this.isWholeLane = false;
			this.isSingleLane = true;
			
		}
	}
	
	public void setGreenColorSingleLane(){
		for(int i=0; i<arrTrafficLight.size(); i++){
			if(i == nextTrafficLight){
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_GREEN);
			} else{
				arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
			}
		} 
		this.isSingleLane = false;
	}
	
	public void setYellowColor(){
		
		if(this.isSingleLane){
			for(int i=0; i<arrTrafficLight.size(); i++){
				if( i==nextTrafficLight1 || i == nextTrafficLight2 || i == nextTrafficLight){
					arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_YELLOW);
				}
				else {
					arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
				}
			 }
		} else {
		
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
			
			int currentTrafficLight = nextTrafficLight;
			if (nextTrafficLight1 == arrTrafficLight.size()-3){
				nextTrafficLight1=0;
			}	
			else {
				nextTrafficLight1+=3;
			}
			this.calculateIndexOfTrafficLigth();
		
			for(int i=0; i<arrTrafficLight.size(); i++){
				if( i==nextTrafficLight1 || i==parallelTrafficLight1 || i==currentTrafficLight){
					arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_YELLOW);
				}
				else {
					arrTrafficLight.get(i).changeTrafficLightState(AgentConfig.TRAFFIC_LIGHT_RED);
				}
			}
			
			
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
		this.nextTrafficLight1 = n;
	}
	
	public void calculateIndexOfTrafficLigth(){
		nextTrafficLight2 = nextTrafficLight1+1;
		parallelTrafficLight1 = nextTrafficLight1 + 3;
		nextTrafficLight = nextTrafficLight1+2;
		if(parallelTrafficLight1>5){
			parallelTrafficLight1-=6;
		}
	}
}
