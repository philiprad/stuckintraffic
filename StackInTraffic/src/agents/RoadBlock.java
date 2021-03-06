/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package agents;

import java.util.ArrayList;

import trafficInfrastructure.road.RoadConfig;

// TODO: Auto-generated Javadoc
/**
 * The Class RoadBlock.
 */
public class RoadBlock {
	
	/** The ArrayList with cars. */
	private ArrayList<StandartCar> arrCar = new ArrayList<StandartCar>();
	
	/** The traffic light index. */
	private ArrayList <Short> trafficLightIndex = new ArrayList <Short>();
	
	/** The block type. */
	private short blockType;
	
	/** The speed limit. */
	private int speedLimit = AgentConfig.DEFAULT_SPEED_LIMIT;
	
	/** The arriving cars. */
	private ArrayList<StandartCar> arrivingCars = new ArrayList<StandartCar>();
	
	/**
	 * Instantiates a new road block.
	 *
	 * @param blockType the block type
	 */
	public RoadBlock(short blockType){
		this.blockType = blockType;
		switch(blockType){
		case RoadConfig.INTERSECTION_BLOCK:{
			this.speedLimit=4;
			break;
		}
		case RoadConfig.ROUND_ABOUT_BLOCK:{
			this.speedLimit=3;
			break;
		}
		case RoadConfig.INTERSECTION_DOUBLE_BLOCK:{
			this.speedLimit=5;
			break;
		}
		case RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK:{
			this.speedLimit=4;
			break;
		}
		case RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK:{
			this.speedLimit=4;
			break;
		}
		case RoadConfig.INTERSECTION_RIGHT_BLOCK:{
			this.speedLimit=4;
			break;
		}
		case RoadConfig.INTERSECTION_UP_BLOCK:{
			this.speedLimit=4;
			break;
		}
		case RoadConfig.INTERSECTION_DOWN_BLOCK:{
			this.speedLimit=4;
			break;
		}
		case RoadConfig.INTERSECTION_LEFT_BLOCK:{
			this.speedLimit=4;
			break;
		}
		case RoadConfig.HORIZONTAL_DOUBLE_BLOCK:{
			this.speedLimit=7;
			break;
		}
		case RoadConfig.VERTICAL_DOUBLE_BLOCK:{
			this.speedLimit=7;
			break;
		} case RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK:{
			this.speedLimit=7;
			break;
		}
		case RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK:{
			this.speedLimit=7;
			break;
		}
		
		case RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK:{
			this.speedLimit=7;
			break;
		}
		case RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK:{
			this.speedLimit=7;
			break;
		}
		default:{
			this.speedLimit=5;
		}
		
		
		}
	}
	
	/**
	 * Gets the arriving car list.
	 *
	 * @return the arriving car list
	 */
	public ArrayList<StandartCar> getArrivingCarList(){
		return this.arrivingCars;
	}
	
	/**
	 * Adds the arriving car.
	 *
	 * @param car the car
	 */
	public void addArrivingCar(StandartCar car){
		this.arrivingCars.add(car);
	}
	
	/**
	 * Delete arriving car.
	 */
	public void deleteArrivingCar(){
		if (!this.arrivingCars.isEmpty()){
				for (StandartCar car : this.arrCar){
					int k = -1;
			
					for (int i = 0; i < this.arrivingCars.size(); i++){
						if (car.equals(this.arrivingCars.get(i))){
							k = i;
						}
					}
			
					if (k!=-1){
						this.arrivingCars.get(k).setIsArriving(false);
						this.arrivingCars.remove(k);
					}
				}
		}
	}
	
	/**
	 * Adds the car to the block container.
	 *
	 * @param car
	 *            the car
	 */
	public void addCar(StandartCar car){
		this.arrCar.add(car);
	}
	
	/**
	 * Checks if is car inside.
	 *
	 * @return true, if is car inside
	 */
	public boolean isCarInside(){
		if (this.arrCar.isEmpty()){
			return false;
		}
		else return true;
	}
	/**
	 * Delete car.
	 *
	 * @param car
	 *            the car
	 */
	public void deleteCar(StandartCar car){
		int k = -1;
		
		for (int i = 0; i < this.arrCar.size(); i++){
			if (car.equals(this.arrCar.get(i))){
				k = i;
			}
		}
		
		if (k!=-1){
			this.arrCar.remove(k);
		}
	}
	
	/**
	 * Clear car list.
	 */
	public void clearCarList(){
		this.arrCar.clear();
	}
	
	/**
	 * Gets the car list.
	 *
	 * @return the car list
	 */
	public ArrayList<StandartCar> getCarList(){
		return this.arrCar;
	}
	
	/**
	 * Gets the block type.
	 *
	 * @return the block type
	 */
	
	public short getBlockType(){
		return this.blockType;
	}
	
	/**
	 * Adds the traffic light index.
	 *
	 * @param index
	 *            the index
	 */
	public void addTrafficLightIndex (short index){ //Verify !!!
		this.trafficLightIndex.add(index);
	}
	
	/**
	 * Gets the traffic light index list.
	 *
	 * @return the traffic light index list
	 */
	public ArrayList <Short> getTrafficLightIndexList(){
		return this.trafficLightIndex;
	}
	
	/**
	 * Checks if is traffic light inside.
	 *
	 * @return true, if is traffic light inside
	 */
	public boolean isTrafficLightInside(){
		if (this.trafficLightIndex.isEmpty()) return false;
		else return true;
	}
	/**
	 * Nb traffic lights.
	 *
	 * @return the int
	 */
	public int nbTrafficLights(){
		return this.trafficLightIndex.size();
	}
	
	/**
	 * Gets the speed limit.
	 *
	 * @return the speed limit
	 */
	public int getSpeedLimit(){
		return this.speedLimit;
	}
}
