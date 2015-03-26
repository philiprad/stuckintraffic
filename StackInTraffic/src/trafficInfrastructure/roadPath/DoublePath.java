/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package trafficInfrastructure.roadPath;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DoublePath.
 */
public class DoublePath implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2425214183718352807L;
	/** The arr path point. */
	
	private ArrayList<PathSwitchingDifference> arrPathSwitch;
	
	/** The arr path point. */
	private ArrayList<PathPoint> arrPathPoint;
	
	/**
	 * Instantiates a new path.
	 *
	 * @param arrPathPoint            the arr path point
	 * @param arrPathSwitch the arr path switch
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
	
	/**
	 * Gets the path switch.
	 *
	 * @return the path switch
	 */
	public ArrayList<PathSwitchingDifference> getPathSwitch(){
		return this.arrPathSwitch;
	}
	
}
