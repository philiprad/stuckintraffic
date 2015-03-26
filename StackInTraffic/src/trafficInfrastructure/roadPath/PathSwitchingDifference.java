/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package trafficInfrastructure.roadPath;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class PathSwitchingDifference.
 */
public class PathSwitchingDifference implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4692456359308290062L;
	
	/** The index. */
	private int index;
	
	/** The length difference. */
	private int lengthDifference;
	
	/**
	 * Instantiates a new path switching difference.
	 *
	 * @param index the index
	 * @param length the length
	 */
	public PathSwitchingDifference( int index, int length){
		 this.index = index;
		 this.lengthDifference = length;
	}
	
	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength(){
		return this.lengthDifference;
	}
	
	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex(){
		return this.index;
	}
	
}
