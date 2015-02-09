package agents;


public class TrafficLight {

	private short direction;
	private short roadBlock;
	private int gridX;
	private int gridY;
	//private int linePositionX;
	//private int linePositionY;
	private int trafficLightState = -1;
	
	public TrafficLight(short direction, short roadBlock , int x , int y){
		
		this.direction = direction;
		//this.linePositionX = linePositionX;
		//this.linePositionY = linePositionY;
		this.gridX = x;
		this.gridY = y;
		this.roadBlock = roadBlock;
		
	}
	
	/*public int getDistanceToTrafficLight(int x, int y){
		if (this.roadBlock == RoadConfig.HORIZONTAL_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadBlock == RoadConfig.HORIZONTAL_EXIT_BLOCK){
			return Math.abs(this.linePositionX - x);
		}
		else {
			return Math.abs(this.linePositionY - y);
		}
	}*/
}
