package trafficInfrastructure.road;

import graphicsLoader.GraphicsConfig;

import java.util.ArrayList;

import trafficInfrastructure.roadPath.PathPoint;

public class IntersectionMixedVerticalBlock {

	
	public static ArrayList<PathPoint> getLeftToTop1To1LanePath(int x, int y, short blockType){

		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		
			float theta = 90;
			float radius = GraphicsConfig.CAR_POSITION_DOUBLE_LANE;
			System.out.println("Radius" + radius);
			float stepsNb = (float) (radius*Math.PI/2) ;
			
			System.out.println("StepNB" + stepsNb);
			float step = 90/(stepsNb);
			System.out.println("Step" + step);
			for (int i = 0; i < stepsNb-1; i++){
				theta-=step;
				int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
				int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
				System.out.println(xp + " "+ yp);
				arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_1_TO_TOP_1_DIRECTION,(int) theta ));
			}
		return arrPathPoints;
	}
	
			
	/**
	 * Gets the left to top1 to2 lane path.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param blockType
	 *            the block type
	 * @return the left to top1 to2 lane path
	 */
	public static ArrayList<PathPoint> getLeftToTop1To2LanePath(int x, int y, short blockType){

		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		
			for (int i = 0; i < GraphicsConfig.DISTANCE_BETWEEN_LANES; i++){
				arrPathPoints.add(new PathPoint(blockType, x+i, y+GraphicsConfig.CAR_POSITION_DOUBLE_LANE, 1 , 90));
			}
			x+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
			float theta = 90;
			float radius = GraphicsConfig.CAR_POSITION_DOUBLE_LANE;
			System.out.println("Radius" + radius);
			float stepsNb = (float) (radius*Math.PI/2) ;
			
			System.out.println("StepNB" + stepsNb);
			float step = 90/(stepsNb);
			System.out.println("Step" + step);
			for (int i = 0; i < stepsNb-1; i++){
				theta-=step;
				int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
				int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
				System.out.println(xp + " "+ yp);
				arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_1_TO_TOP_2_DIRECTION,(int) theta ));
			}
		return arrPathPoints;
	}
	
	/**
	 * Gets the left to bottom path.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param blockType
	 *            the block type
	 * @return the left to bottom path
	 */

/**
 * Gets the left2 to1 bottom path.
 *
 * @param x
 *            the x
 * @param y
 *            the y
 * @param blockType
 *            the block type
 * @return the left2 to1 bottom path
 */
//TODO rescalculate radius
/*public static ArrayList<PathPoint> getLeft2To1BottomPath(int x, int y, short blockType){
	
	ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
	for (int i = 0; i < GraphicsConfig.DISTANCE_BETWEEN_LANES; i++){
		arrPathPoints.add(new PathPoint(blockType, x+i, y+GraphicsConfig.CAR_POSITION_DOUBLE_LANE+GraphicsConfig.DISTANCE_BETWEEN_LANES, 1 , 90));
	}
	x+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
	y+=GraphicsConfig.BLOCK_SIDE_SIZE*2;
	float theta = 270;
	int radius = GraphicsConfig.CAR_INVERSE_POSITION_DOUBLE_LANE-GraphicsConfig.DISTANCE_BETWEEN_LANES;
	System.out.println("Radius" + radius);
	float stepsNb = (float) (radius*Math.PI/2) ;
	
	System.out.println("StepNB" + stepsNb);
	float step = 90/(stepsNb);
	System.out.println("Step" + step);
	for (int i = 0; i < stepsNb-1; i++){
		theta+=step;
		int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
		int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
		System.out.println(xp + " "+ yp);
		arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_2_TO_BOTTOM_1_DIRECTION,(int) theta ));
	}
return arrPathPoints;
}*/



/**
 * Gets the right2 to1 top path.
 *
 * @param x
 *            the x
 * @param y
 *            the y
 * @param blockType
 *            the block type
 * @return the right2 to1 top path
 * 
 * 
 */

//TODO recalculate radius and path
/*public static ArrayList<PathPoint> getRight2To1TopPath(int x, int y, short blockType){
	ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
	for (int i = 0; i < GraphicsConfig.DISTANCE_BETWEEN_LANES; i++){
		arrPathPoints.add(new PathPoint(blockType, x+GraphicsConfig.BLOCK_SIDE_SIZE*2-i, y+GraphicsConfig.CAR_INVERSE_POSITION_DOUBLE_LANE - GraphicsConfig.DISTANCE_BETWEEN_LANES,-1, 270));
	}
	x+=GraphicsConfig.BLOCK_SIDE_SIZE*2-GraphicsConfig.DISTANCE_BETWEEN_LANES;
	
	float theta = 90;
	
	int radius = GraphicsConfig.CAR_INVERSE_POSITION_DOUBLE_LANE-GraphicsConfig.DISTANCE_BETWEEN_LANES;
	System.out.println("Radius" + radius);
	float stepsNb = (float) (radius*Math.PI/2) ;
	
	System.out.println("StepNB" + stepsNb);
	float step = 90/(stepsNb);
	System.out.println("Step" + step);
	for (int i = 0; i < stepsNb-1; i++){
		theta+=step;
		int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
		int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
		System.out.println(xp + " "+ yp);
		arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.RIGHT_2_TO_TOP_1_DIRECTION ,(int) theta ));
	}
return arrPathPoints;
}	*/

/**
 * Gets the right1 to1 bottom path.
 *
 * @param x
 *            the x
 * @param y
 *            the y
 * @param blockType
 *            the block type
 * @return the right1 to1 bottom path
 */
public static ArrayList<PathPoint> getRight1To1BottomPath(int x, int y, short blockType){
	ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
	y+=GraphicsConfig.BLOCK_SIDE_SIZE*2;
	x+=GraphicsConfig.BLOCK_SIDE_SIZE*2;
	float theta = 270;
	int radius = GraphicsConfig.CAR_POSITION_DOUBLE_LANE;
	System.out.println("Radius" + radius);
	float stepsNb = (float) (radius*Math.PI/2) ;
	
	System.out.println("StepNB" + stepsNb);
	float step = 90/(stepsNb);
	System.out.println("Step" + step);
	for (int i = 0; i < stepsNb-1; i++){
		theta-=step;
		int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
		int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
		System.out.println(xp + " "+ yp);
		arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.RIGHT_1_TO_BOTTOM_1_DIRECTION ,(int) theta ));
	}
return arrPathPoints;
}

/**
 * Gets the top1 to1 right path.
 *
 * @param x
 *            the x
 * @param y
 *            the y
 * @param blockType
 *            the block type
 * @return the top1 to1 right path
 */
public static ArrayList<PathPoint> getTop1To1RightPath(int x, int y, short blockType){
	ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
	x+=GraphicsConfig.BLOCK_SIDE_SIZE*2;
	
	float theta = 180;
	
	int radius = GraphicsConfig.CAR_POSITION_DOUBLE_LANE;
	System.out.println("Radius" + radius);
	float stepsNb = (float) (radius*Math.PI/2) ;
	
	System.out.println("StepNB" + stepsNb);
	float step = 90/(stepsNb);
	System.out.println("Step" + step);
	for (int i = 0; i < stepsNb-1; i++){
		theta-=step;
		int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
		int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
		System.out.println(xp + " "+ yp);
		arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.TOP_1_TO_RIGHT_1_DIRECTION ,(int) theta ));
	}
return arrPathPoints;
}

/**
 * Gets the top1 to2 right path.
 *
 * @param x
 *            the x
 * @param y
 *            the y
 * @param blockType
 *            the block type
 * @return the top1 to2 right path
 */
public static ArrayList<PathPoint> getTop1To2RightPath(int x, int y, short blockType){
	ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
	for (int i = 0; i < GraphicsConfig.DISTANCE_BETWEEN_LANES; i++){
		arrPathPoints.add(new PathPoint(blockType,x+GraphicsConfig.CAR_INVERSE_POSITION_DOUBLE_LANE , y+i,1, -1));
	}
	x+=GraphicsConfig.BLOCK_SIDE_SIZE*2;
	y+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
	float theta = 180;
	
	int radius = GraphicsConfig.CAR_POSITION_DOUBLE_LANE;
	System.out.println("Radius" + radius);
	float stepsNb = (float) (radius*Math.PI/2) ;
	
	System.out.println("StepNB" + stepsNb);
	float step = 90/(stepsNb);
	System.out.println("Step" + step);
	for (int i = 0; i < stepsNb-1; i++){
		theta-=step;
		int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
		int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
		System.out.println(xp + " "+ yp);
		arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.TOP_1_TO_RIGHT_2_DIRECTION ,(int) theta ));
	}
return arrPathPoints;
}



/**
 * Gets the bottom1 to1 left path.
 *
 * @param x
 *            the x
 * @param y
 *            the y
 * @param blockType
 *            the block type
 * @return the bottom1 to1 left path
 */
public static ArrayList<PathPoint> getBottom1To1LeftPath(int x, int y, short blockType){
	ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
	y+=GraphicsConfig.BLOCK_SIDE_SIZE*2;
	float theta = 0;
	int radius = GraphicsConfig.CAR_POSITION_DOUBLE_LANE;
	System.out.println("Radius" + radius);
	float stepsNb = (float) (radius*Math.PI/2) ;
	
	System.out.println("StepNB" + stepsNb);
	float step = 90/(stepsNb);
	System.out.println("Step" + step);
	for (int i = 0; i < stepsNb-1; i++){
		theta-=step;
		int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
		int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
		System.out.println(xp + " "+ yp);
		arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.BOTTOM_1_TO_LEFT_1_DIRECTION ,(int) theta ));
	}
return arrPathPoints;
}

/**
 * Gets the bottom1 to2 left path.
 *
 * @param x
 *            the x
 * @param y
 *            the y
 * @param blockType
 *            the block type
 * @return the bottom1 to2 left path
 */
public static ArrayList<PathPoint> getBottom1To2LeftPath(int x, int y, short blockType){
	ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
	for (int i = 0; i < GraphicsConfig.DISTANCE_BETWEEN_LANES; i++){
		arrPathPoints.add(new PathPoint(blockType,x+GraphicsConfig.CAR_POSITION_DOUBLE_LANE , y+GraphicsConfig.BLOCK_SIDE_SIZE*2-i, -1 ,-1));
	}
	y+=GraphicsConfig.BLOCK_SIDE_SIZE*2-GraphicsConfig.DISTANCE_BETWEEN_LANES;
	float theta = 0;
	int radius = GraphicsConfig.CAR_POSITION_DOUBLE_LANE;
	System.out.println("Radius" + radius);
	float stepsNb = (float) (radius*Math.PI/2) ;
	
	System.out.println("StepNB" + stepsNb);
	float step = 90/(stepsNb);
	System.out.println("Step" + step);
	for (int i = 0; i < stepsNb-1; i++){
		theta-=step;
		int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
		int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
		System.out.println(xp + " "+ yp);
		arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.BOTTOM_1_TO_LEFT_2_DIRECTION ,(int) theta ));
	}
return arrPathPoints;
}
}
