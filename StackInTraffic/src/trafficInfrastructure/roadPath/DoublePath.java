package trafficInfrastructure.roadPath;

import java.io.Serializable;
import java.util.ArrayList;

public class DoublePath implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2425214183718352807L;
	/** The arr path point. */
	
	private ArrayList<PathSwitchingDifference> arrPathSwitch;
	
	private ArrayList<PathPoint> arrPathPoint;
	
	/**
	 * Instantiates a new path.
	 *
	 * @param arrPathPoint
	 *            the arr path point
	 */
	public DoublePath (ArrayList<PathPoint> arrPathPoint,ArrayList<PathSwitchingDifference> arrPathSwitch ){
		this.arrPathPoint = arrPathPoint;
		this.arrPathSwitch = arrPathSwitch;
	}
	
	/**
	 * Gets the path points.
	 *
	 * @return the path points
	 */
	public ArrayList<PathPoint> getPathPoints(){
		return this.arrPathPoint;
	}
	
	public ArrayList<PathSwitchingDifference> getPathSwitch(){
		return this.arrPathSwitch;
	}
	
}
