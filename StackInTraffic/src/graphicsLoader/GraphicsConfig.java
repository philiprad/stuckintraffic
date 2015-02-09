package graphicsLoader;

public class GraphicsConfig {
	
	public static final int MAIN_HEIGHT = 800;
	public static final int MAIN_WIDTH = 800;
	public static final int BLOCK_SIDE_SIZE = 50;
	public static final int GRID_HEIGHT = MAIN_HEIGHT/BLOCK_SIDE_SIZE;
	public static final int GRID_WIDTH = MAIN_WIDTH/BLOCK_SIDE_SIZE;
	public static final String VERTICAL_ROAD_IMG = "./images/verticalRoad.png";
	public static final String HORIZONTAL_ROAD_IMG = "./images/horizontalRoad.png";
	public static final String INTERSECTION_IMG = "./images/intersection.png";
	public static final String CAR_UP_IMG = "./images/carUp.png";
	public static final String CAR_DOWN_IMG = "./images/carDown.png";
	public static final String CAR_RIGHT_IMG = "./images/carRight.png";
	public static final String CAR_LEFT_IMG = "./images/carLeft.png";
	public static final int CAR_POSITION = BLOCK_SIDE_SIZE*30/100;
	public static final int CAR_INVERSE_POSITION = BLOCK_SIDE_SIZE-CAR_POSITION;
	public static final int CAR_LENGTH = BLOCK_SIDE_SIZE*40/100;
	public static final int CAR_WIDTH = BLOCK_SIDE_SIZE*20/100;
	
}
