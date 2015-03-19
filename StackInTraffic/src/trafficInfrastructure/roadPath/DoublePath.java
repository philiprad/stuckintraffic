package trafficInfrastructure.roadPath;

import java.io.Serializable;
import java.util.ArrayList;

public class DoublePath implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8886635786819099366L;

	/** The first choise. */
	private ArrayList <PathPoint> firstChoise;
	
	/** The second choise. */
	private ArrayList <PathPoint> secondChoise;
	
	/**
	 * Instantiates a new double intersection possible path.
	 *
	 * @param firstChoise
	 *            the first choise
	 * @param secondChoise
	 *            the second choise
	 */
	public DoublePath(ArrayList <PathPoint> firstChoise, ArrayList <PathPoint> secondChoise){
		this.firstChoise = firstChoise;
		this.secondChoise = secondChoise;
	}
	
	/**
	 * Gets the first choise.
	 *
	 * @return the first choise
	 */
	public ArrayList <PathPoint> getFirstChoise(){
		return this.firstChoise;
	}
	
	/**
	 * Gets the second choise.
	 *
	 * @return the second choise
	 */
	public ArrayList <PathPoint> getSecondChoise(){
		return this.secondChoise;
	}
	
	/**
	 * Adds the array path to first chose.
	 *
	 * @param arrPathPoint
	 *            the arr path point
	 */
	public void addArrayPathToFirstChose(ArrayList <PathPoint> arrPathPoint){
		this.firstChoise.addAll(arrPathPoint);
	}
	
	/**
	 * Adds the array path to second chose.
	 *
	 * @param arrPathPoint
	 *            the arr path point
	 */
	public void addArrayPathToSecondChose(ArrayList <PathPoint> arrPathPoint){
		this.secondChoise.addAll(arrPathPoint);
	}
}
