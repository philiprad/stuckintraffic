package trafficInfrastructure.roadPath;

public class PathSwitchingDifference {

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
