/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphicsConfig.
 */
public class GraphicsConfig {
	
	/** The Constant MAIN_HEIGHT. */
	public static final int MAIN_HEIGHT = 800;
	
	/** The Constant MAIN_WIDTH. */
	public static final int MAIN_WIDTH = 800;
	
	/** The Constant BLOCK_SIDE_SIZE. */
	public static final int BLOCK_SIDE_SIZE = 50;
	
	/** The Constant DOUBLE_BLOCK_SIZE. */
	public static final int DOUBLE_BLOCK_SIZE = 100;
	
	public static final int TRIPLE_BLOCK_SIZE = 150;
	
	/** The Constant GRID_HEIGHT. */
	public static final int GRID_HEIGHT = MAIN_HEIGHT/BLOCK_SIDE_SIZE;
	
	/** The Constant GRID_WIDTH. */
	public static final int GRID_WIDTH = MAIN_WIDTH/BLOCK_SIDE_SIZE;
	
	/** The Constant VERTICAL_ROAD_IMG. */
	public static final String VERTICAL_ROAD_IMG = "./images/verticalRoad.png";
	
	/** The Constant HORIZONTAL_ROAD_IMG. */
	public static final String HORIZONTAL_ROAD_IMG = "./images/horizontalRoad.png";
	
	/** The Constant VERTICAL_DOUBLE_ROAD_IMG. */
	public static final String VERTICAL_DOUBLE_ROAD_IMG = "./images/verticalDoubleRoad.png";
	
	/** The Constant HORIZONTAL_ROAD_IMG. */
	public static final String HORIZONTAL_DOUBLE_ROAD_IMG = "./images/horizontalDoubleRoad.png";
	
	/** The Constant VERTICAL_GREEN_LIGHT_IMG. */
	public static final String VERTICAL_GREEN_LIGHT_IMG = "./images/verticalGreenLight.png";
	
	/** The Constant HORIZONTAL_GREEN_LIGHT_IMG. */
	public static final String HORIZONTAL_GREEN_LIGHT_IMG = "./images/horizontalGreenLight.png";
	
	/** The Constant VERTICAL_RED_LIGHT_IMG. */
	public static final String VERTICAL_RED_LIGHT_IMG = "./images/verticalRedLight.png";
	
	/** The Constant HORIZONTAL_RED_LIGHT_IMG. */
	public static final String HORIZONTAL_RED_LIGHT_IMG = "./images/horizontalRedLight.png";
	
	/** The Constant VERTICAL_YELLOW_LIGHT_IMG. */
	public static final String VERTICAL_YELLOW_LIGHT_IMG = "./images/verticalYellowLight.png";
	
	/** The Constant HORIZONTAL_YELLOW_LIGHT_IMG. */
	public static final String HORIZONTAL_YELLOW_LIGHT_IMG = "./images/horizontalYellowLight.png";
	
	/** The Constant INTERSECTION_IMG. */
	public static final String INTERSECTION_IMG = "./images/intersection.png";
	
	
	/** The Constant VERTICAL_ROAD_IMG_SCALED. */
	public static final String VERTICAL_ROAD_IMG_SCALED = "./images/scaledPictures/verticalRoad.png";
	
	/** The Constant HORIZONTAL_ROAD_IMG. */
	public static final String HORIZONTAL_ROAD_IMG_SCALED = "./images/scaledPictures/horizontalRoad.png";
	
	/** The Constant INTERSECTION_IMG_SCALED. */
	public static final String INTERSECTION_IMG_SCALED = "./images/scaledPictures/intersection.png";
	
	/** The Constant VERTICAL_DOUBLE_ROAD_IMG. */
	public static final String VERTICAL_DOUBLE_ROAD_IMG_SCALED = "./images/scaledPictures/verticalDoubleRoad.png";
	
	/** The Constant HORIZONTAL_ROAD_IMG. */
	public static final String HORIZONTAL_DOUBLE_ROAD_IMG_SCALED = "./images/scaledPictures/horizontalDoubleRoad.png";
	
	public static final String VERTICAL_DOUBLE_ROAD_IMG_TOOLBAR = "./images/toolBarPictures/verticalDoubleRoad.png";
	
	/** The Constant HORIZONTAL_ROAD_IMG. */
	public static final String HORIZONTAL_DOUBLE_ROAD_IMG_TOOLBAR = "./images/toolBarPictures/horizontalDoubleRoad.png";
	
	public static final String VERTICAL_ROAD_IMG_CURSOR = "./images/cursorPictures/verticalRoad.png";
	
	/** The Constant HORIZONTAL_ROAD_IMG. */
	public static final String HORIZONTAL_ROAD_IMG_CURSOR = "./images/cursorPictures/horizontalRoad.png";
	
	/** The Constant INTERSECTION_IMG_SCALED. */
	public static final String INTERSECTION_IMG_CURSOR = "./images/cursorPictures/intersection.png";
	
	/** The Constant VERTICAL_DOUBLE_ROAD_IMG. */
	public static final String VERTICAL_DOUBLE_ROAD_IMG_CURSOR = "./images/cursorPictures/verticalDoubleRoad.png";
	
	/** The Constant HORIZONTAL_ROAD_IMG. */
	public static final String HORIZONTAL_DOUBLE_ROAD_IMG_CURSOR = "./images/cursorPictures/horizontalDoubleRoad.png";
	
	
	
	/** The Constant CAR_UP_IMG. */
	public static final String CAR_UP_IMG = "./images/carUp.png";
	
	/** The Constant CAR_DOWN_IMG. */
	public static final String CAR_DOWN_IMG = "./images/carDown.png";
	
	/** The Constant CAR_RIGHT_IMG. */
	public static final String CAR_RIGHT_IMG = "./images/carRight.png";
	
	/** The Constant CAR_LEFT_IMG. */
	public static final String CAR_LEFT_IMG = "./images/carLeft.png";
	
	/** The Constant CAR_POSITION. */
	public static final int CAR_POSITION = BLOCK_SIDE_SIZE*30/100;
	
	/** The Constant CAR_INVERSE_POSITION. */
	public static final int CAR_INVERSE_POSITION = BLOCK_SIDE_SIZE-CAR_POSITION;
	
	/** The Constant CAR_LENGTH. */
	public static final int CAR_LENGTH = BLOCK_SIDE_SIZE*40/100;
	
	/** The Constant CAR_WIDTH. */
	public static final int CAR_WIDTH = BLOCK_SIDE_SIZE*20/100;
	
	/** The Constant TRAFFIC_LIGHT_LINE_LENGTH. */
	public static final int TRAFFIC_LIGHT_LINE_LENGTH = BLOCK_SIDE_SIZE/2;
	
	/** The Constant TRAFFIC_LIGHT_LINE_WIDTH. */
	public static final int TRAFFIC_LIGHT_LINE_WIDTH = BLOCK_SIDE_SIZE/5;
	
	/** The Constant TRAFFIC_LIGHT_POSITION_LENGTH_DISTANCE. */
	public static final int TRAFFIC_LIGHT_POSITION_LENGTH_DISTANCE = BLOCK_SIDE_SIZE-TRAFFIC_LIGHT_LINE_LENGTH;
	
	/** The Constant TRAFFIC_LIGHT_POSITION_WIDTH_DISTANCE. */
	public static final int TRAFFIC_LIGHT_POSITION_WIDTH_DISTANCE = BLOCK_SIDE_SIZE-TRAFFIC_LIGHT_LINE_WIDTH;
	
	/** The Constant CAR_POSITION_DOUBLE_LANE. */
	public static final int CAR_POSITION_DOUBLE_LANE = BLOCK_SIDE_SIZE*40/100;
	
	/** The Constant CAR_INVERSE_POSITION. */
	public static final int CAR_INVERSE_POSITION_DOUBLE_LANE = BLOCK_SIDE_SIZE*2-(CAR_POSITION+BLOCK_SIDE_SIZE*2*5/100);
	
}
