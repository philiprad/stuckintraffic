package agents;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class RoadBlock.
 */
public class RoadBlock {
	
	/** The ArrayList with cars */
	private ArrayList<Car> arrCar;
	
	/** The traffic light index. */
	private ArrayList <Short> trafficLightIndex;
	
	/** The block type. */
	private short blockType;
	
	/** The speed limit. */
	private int speedLimit = AgentConfig.DEFAULT_SPEED_LIMIT;
	
	/**
	 * Instantiates a new road block.
	 *
	 * @param blockType the block type
	 */
	public RoadBlock(short blockType){
		this.blockType = blockType;
	}
	
	/**
	 * Adds the car to the block container
	 *
	 * @param car the car
	 */
	public void addCar(Car car){
		this.arrCar.add(car);
	}
	
	/**
	 * Delete car
	 *
	 * @param car the car
	 */
	public void deleteCar(Car car){
		int k = -1;
		
		for (int i = 0; i < this.arrCar.size()-1; i++){
			if (car.equals(this.arrCar.get(i))){
				k = i;
			}
		}
		
		if (k!=-1){
			this.arrCar.remove(k);
		}
	}
	
	/**
	 * Gets the block type.
	 *
	 * @return the block type
	 */
	public short getBlockType(){
		return this.blockType;
	}
	
	public void addTrafficLightIndex (short index){ //Verify !!!
		this.trafficLightIndex.add(index);
	}
	
	public int nbTrafficLights(){
		return this.trafficLightIndex.size();
	}
}
