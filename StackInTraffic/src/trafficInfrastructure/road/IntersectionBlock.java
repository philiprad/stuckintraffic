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
 * The Class IntersectionBlock.
 */
public class IntersectionBlock {
	
	/**
	 * Gets the left to top path.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param blockType
	 *            the block type
	 * @return the left to top path
	 */
	public static ArrayList<PathPoint> getLeftToTopPath(int x, int y, short blockType){

		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		
			float theta = 90;
			float radius = GraphicsConfig.CAR_POSITION;
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
				arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_TO_TOP_DIRECTION,(int) theta));
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
	public static ArrayList<PathPoint> getLeftToBottomPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		y+=GraphicsConfig.BLOCK_SIDE_SIZE;
		float theta = 270;
		int radius = GraphicsConfig.CAR_INVERSE_POSITION;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_TO_BOTTOM_DIRECTION ,(int) theta));
		}
	return arrPathPoints;
	}
	
	/**
	 * Gets the right to top path.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param blockType
	 *            the block type
	 * @return the right to top path
	 */
	public static ArrayList<PathPoint> getRightToTopPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		x+=GraphicsConfig.BLOCK_SIDE_SIZE;
		
		float theta = 90;
		
		int radius = GraphicsConfig.CAR_INVERSE_POSITION;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.RIGHT_TO_TOP_DIRECTION ,(int) theta));
		}
	return arrPathPoints;
	}
	
	/**
	 * Gets the right to bottom path.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param blockType
	 *            the block type
	 * @return the right to bottom path
	 */
	public static ArrayList<PathPoint> getRightToBottomPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		y+=GraphicsConfig.BLOCK_SIDE_SIZE;
		x+=GraphicsConfig.BLOCK_SIDE_SIZE;
		float theta = 270;
		int radius = GraphicsConfig.CAR_POSITION;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.RIGHT_TO_BOTTOM_DIRECTION ,(int) theta));
		}
	return arrPathPoints;
	}
	
	/**
	 * Gets the top to right path.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param blockType
	 *            the block type
	 * @return the top to right path
	 */
	public static ArrayList<PathPoint> getTopToRightPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		x+=GraphicsConfig.BLOCK_SIDE_SIZE;
		
		float theta = 180;
		
		int radius = GraphicsConfig.CAR_POSITION;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.TOP_TO_RIGHT_DIRECTION ,(int) theta));
		}
	return arrPathPoints;
	}
	
	/**
	 * Gets the top to left path.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param blockType
	 *            the block type
	 * @return the top to left path
	 */
	public static ArrayList<PathPoint> getTopToLeftPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		float theta = 0;
		int radius = GraphicsConfig.CAR_INVERSE_POSITION;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.TOP_TO_LEFT_DIRECTION ,(int) theta));
		}
	return arrPathPoints;
	}
	
	/**
	 * Gets the bottom to right path.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param blockType
	 *            the block type
	 * @return the bottom to right path
	 */
	public static ArrayList<PathPoint> getBottomToRightPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		x+=GraphicsConfig.BLOCK_SIDE_SIZE;
		y+=GraphicsConfig.BLOCK_SIDE_SIZE;
		float theta = 180;
		
		int radius = GraphicsConfig.CAR_INVERSE_POSITION;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.BOTTOM_TO_RIGHT_DIRECTION ,(int) theta));
		}
	return arrPathPoints;
	}
	
	/**
	 * Gets the bottom to left path.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param blockType
	 *            the block type
	 * @return the bottom to left path
	 */
	public static ArrayList<PathPoint> getBottomToLeftPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		y+=GraphicsConfig.BLOCK_SIDE_SIZE;
		float theta = 0;
		int radius = GraphicsConfig.CAR_POSITION;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.BOTTOM_TO_LEFT_DIRECTION ,(int) theta));
		}
	return arrPathPoints;
	}
}
