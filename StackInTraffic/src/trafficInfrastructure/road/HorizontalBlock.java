/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package trafficInfrastructure.road;

import graphicsLoader.GraphicsConfig;

import java.util.ArrayList;

import trafficInfrastructure.roadPath.PathPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class HorizontalBlock.
 */
public class HorizontalBlock {
	
		

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
			int [] tab = {x1+GraphicsConfig.BLOCK_SIDE_SIZE, y1 , x2 + GraphicsConfig.BLOCK_SIDE_SIZE, y2};
			return tab;
		}
		
		/**
		 * Gets the path points.
		 *
		 * @param x
		 *            the x
		 * @param y
		 *            the y
		 * @param blockType
		 *            the block type
		 * @return the path points
		 */
		public static ArrayList<PathPoint> getPathPoints(int x, int y, short blockType){
			ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
			for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE; i++){
			arrPathPoints.add(new PathPoint(blockType, x+i, y+GraphicsConfig.CAR_POSITION, 1 , -1, -1));
			}
			return arrPathPoints;
		}
		
		/**
		 * Gets the inverse path points.
		 *
		 * @param x
		 *            the x
		 * @param y
		 *            the y
		 * @param blockType
		 *            the block type
		 * @return the inverse path points
		 */
		public static ArrayList<PathPoint> getInversePathPoints(int x, int y, short blockType){
			ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
			for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE; i++){
				arrPathPoints.add(new PathPoint(blockType, x+GraphicsConfig.BLOCK_SIDE_SIZE-i, y+GraphicsConfig.CAR_INVERSE_POSITION,-1, -1, -1));
			}
			return arrPathPoints;
		}
}
