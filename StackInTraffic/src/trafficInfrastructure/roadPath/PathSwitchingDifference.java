package trafficInfrastructure.roadPath;

import java.io.Serializable;

public class PathSwitchingDifference implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4692456359308290062L;
	private int index;
	private int lengthDifference;
	
	public PathSwitchingDifference( int index, int length){
		 this.index = index;
		 this.lengthDifference = length;
	}
	
	public int getLength(){
		return this.lengthDifference;
	}
	
	public int getIndex(){
		return this.index;
	}
	
}
