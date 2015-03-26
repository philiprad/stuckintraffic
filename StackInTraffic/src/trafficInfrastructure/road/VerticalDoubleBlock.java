/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package trafficInfrastructure.road;

import graphicsLoader.GraphicsConfig;

import java.util.ArrayList;

import trafficInfrastructure.roadPath.PathPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class VerticalDoubleBlock.
 */
public class VerticalDoubleBlock {
	
	/**
	 * Next block location.
	 *
	 * @param x1
	 *            the x1
	 * @param y1
	 *            the y1
	 * @param x2
	 *            the x2
	 * @param y2
	 *            the y2
	 * @return the int[]
	 */
	public static int [] nextBlockLocation(int x1,int y1, int x2, int y2){
		int [] tab = {x1, y1+GraphicsConfig.BLOCK_SIDE_SIZE*2, x2 , y2 +GraphicsConfig.BLOCK_SIDE_SIZE*2};
		return tab;
	}
	
	
	/**
	 * Gets the path points.
	 *
	 * @param x            the x
	 * @param y            the y
	 * @param blockType            the block type
	 * @param direction the direction
	 * @return the path points
	 */
	public static ArrayList<PathPoint> getPath1Points(int x, int y, short blockType, short direction){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE*2; i++){
			arrPathPoints.add(new PathPoint(blockType,x+GraphicsConfig.CAR_INVERSE_POSITION_DOUBLE_LANE, y+i,direction, -1));
		}
		return arrPathPoints;
	}
	
	/**
	 * Gets the path2 points.
	 *
	 * @param x the x
	 * @param y the y
	 * @param blockType the block type
	 * @param direction the direction
	 * @return the path2 points
	 */
	public static ArrayList<PathPoint> getPath2Points(int x, int y, short blockType,  short direction){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE*2; i++){
			arrPathPoints.add(new PathPoint(blockType,x+GraphicsConfig.CAR_INVERSE_POSITION_DOUBLE_LANE - GraphicsConfig.DISTANCE_BETWEEN_LANES, y+i,direction, -1));
		}
		return arrPathPoints;
	}
	
	
	/**
	 * Gets the inverse path points.
	 *
	 * @param x            the x
	 * @param y            the y
	 * @param blockType            the block type
	 * @param direction the direction
	 * @return the inverse path points
	 */
	public static ArrayList<PathPoint> getInversePath1Points(int x, int y, short blockType, short direction){
		
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE*2; i++){
			arrPathPoints.add(new PathPoint(blockType,x+GraphicsConfig.CAR_POSITION_DOUBLE_LANE, y+GraphicsConfig.BLOCK_SIDE_SIZE*2-i, direction ,-1));
		}
		return arrPathPoints;
	}
	
	/**
	 * Gets the inverse path2 points.
	 *
	 * @param x the x
	 * @param y the y
	 * @param blockType the block type
	 * @param direction the direction
	 * @return the inverse path2 points
	 */
	public static ArrayList<PathPoint> getInversePath2Points(int x, int y, short blockType,short direction){
		
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE*2; i++){
			arrPathPoints.add(new PathPoint(blockType,x+GraphicsConfig.CAR_POSITION_DOUBLE_LANE + GraphicsConfig.DISTANCE_BETWEEN_LANES, y+GraphicsConfig.BLOCK_SIDE_SIZE*2-i, direction ,-1));
		}
		return arrPathPoints;
	}

}
