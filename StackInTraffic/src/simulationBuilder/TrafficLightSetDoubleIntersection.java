package simulationBuilder;

import java.util.ArrayList;

import agents.AgentConfig;
import agents.TrafficLight;

public class TrafficLightSetDoubleIntersection {

	ArrayList<TrafficLight> arrTrafficLight;
	private int timer;
	private int nextTrafficLight1 = 0;
	private int nextTrafficLight2 ;
	private int parallelTrafficLight1;
	private boolean isYellowState = false;
	private int greenTimeInterval = 50;
	private int yellowTimeInterval= 30;
	private boolean isWholeLane = false;
	
	public TrafficLightSetDoubleIntersection(ArrayList<TrafficLight> arrTrafficLight){
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
			
		}
	}
	
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
		parallelTrafficLight1 = nextTrafficLight1 + 4;
		if(parallelTrafficLight1>7){
			parallelTrafficLight1-=8;
		}
	}
}
