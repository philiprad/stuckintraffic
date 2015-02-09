package trafficInfrastructure.roadPath;

import java.io.Serializable;
import java.util.ArrayList;

public class Path implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8405738204268016862L;
	private ArrayList<PathPoint> arrPathPoint;
	
	public Path (ArrayList<PathPoint> arrPathPoint){
		this.arrPathPoint = arrPathPoint;
	}
	
	public ArrayList<PathPoint> getPathPoints(){
		return this.arrPathPoint;
	}
	
}
