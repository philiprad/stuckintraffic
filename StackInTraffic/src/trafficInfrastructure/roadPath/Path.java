/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package trafficInfrastructure.roadPath;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Path.
 */
public class Path implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8405738204268016862L;
	
	/** The arr path point. */
	private ArrayList<PathPoint> arrPathPoint;
	
	/**
	 * Instantiates a new path.
	 *
	 * @param arrPathPoint
	 *            the arr path point
	 */
	public Path (ArrayList<PathPoint> arrPathPoint){
		this.arrPathPoint = arrPathPoint;
	}
	
	/**
	 * Gets the path points.
	 *
	 * @return the path points
	 */
	public ArrayList<PathPoint> getPathPoints(){
		return this.arrPathPoint;
	}
	
}
