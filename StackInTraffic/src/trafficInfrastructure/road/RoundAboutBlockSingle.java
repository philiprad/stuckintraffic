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
 * The Class RoundAboutBlockSingle.
 */
public class RoundAboutBlockSingle {
	
	/**
	 * Gets the path.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param exitNumber
	 *            the exit number
	 * @param previousBlockType
	 *            the previous block type
	 * @param direction
	 *            the direction
	 * @return the path
	 */
	public static ArrayList<PathPoint> getPath(int x, int y,short exitNumber,int previousBlockType, int direction){
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		int initialAngel = 0;
		short roundAboutDirection= 0;
		int initialAngel1 = 0;
		int initialAngel2 = 0;
		ArrayList<PathPoint> arrList = new ArrayList<PathPoint>();
		if (previousBlockType == RoadConfig.VERTICAL_BLOCK || previousBlockType == RoadConfig.VERTICAL_ENTER_BLOCK || previousBlockType == RoadConfig.VERTICAL_EXIT_BLOCK){
			if (direction==1){
				initialAngel = 270;
				
				switch (exitNumber){
				case 1: 
					y1 = y;
					x1 = x+GraphicsConfig.BLOCK_SIDE_SIZE;
					x2 = x+2*GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y+GraphicsConfig.BLOCK_SIDE_SIZE;
					initialAngel1 = 180;
					initialAngel2 = 168;
					x = x + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.TOP_TO_RIGHT_DIRECTION;
					arrList.addAll(getPathToFirstExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1, initialAngel2, roundAboutDirection));break;
				case 2: 
					y1 = y;
					x1 = x+GraphicsConfig.BLOCK_SIDE_SIZE;
					x2 = x+GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y+3*GraphicsConfig.BLOCK_SIDE_SIZE;
					initialAngel1 = 180;
					initialAngel2 = 258;
					x = x + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.TOP_TO_BOTTOM_DIRECTION;
					arrList.addAll(getPathToSecondExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1, initialAngel2, roundAboutDirection));break;
				case 3: 
					initialAngel1 = 180;
					initialAngel2 = 300;
					y1 = y;
					x1 = x+3*GraphicsConfig.BLOCK_SIDE_SIZE;
					x2 = x-GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y+4*GraphicsConfig.BLOCK_SIDE_SIZE;
					x = x + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.TOP_TO_LEFT_DIRECTION;
					arrList.addAll(getPathToThirdExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1,initialAngel2, roundAboutDirection));break;
				case 4: 
					initialAngel1 = 180;
					initialAngel2 = 30;
					y1 = y;
					x1 = x+3*GraphicsConfig.BLOCK_SIDE_SIZE;
					x2 = x-2*GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y;
					x = x + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.TOP_TO_TOP_DIRECTION;
					arrList.addAll(getPathToSameExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1,initialAngel2, roundAboutDirection));break;
				}
				
				
			} else {
				initialAngel = 90;
				
				
				switch (exitNumber){
				case 1: 
					y1 = y+GraphicsConfig.BLOCK_SIDE_SIZE;
					x1 = x;
					x2 = x-GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y;
					initialAngel1 = 0;
					initialAngel2 = 348;
					x = x + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y - GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.BOTTOM_TO_LEFT_DIRECTION;
					arrList.addAll(getPathToFirstExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1, initialAngel2,roundAboutDirection));break;
				case 2: 
					y1 = y+GraphicsConfig.BLOCK_SIDE_SIZE;
					x1 = x;
					x2 = x;
					y2 = y-2*GraphicsConfig.BLOCK_SIDE_SIZE;
					initialAngel1 = 0;
					initialAngel2 = 78;
					x = x + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y - GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.BOTTOM_TO_TOP_DIRECTION;
					arrList.addAll(getPathToSecondExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1, initialAngel2,roundAboutDirection));break;
				case 3: 
					initialAngel1 = 0;
					initialAngel2 = 120;
					y1 = y+GraphicsConfig.BLOCK_SIDE_SIZE;
					x1 = x-2*GraphicsConfig.BLOCK_SIDE_SIZE;
					x2 = x+2*GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y-3*GraphicsConfig.BLOCK_SIDE_SIZE;
					x = x + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y - GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.BOTTOM_TO_RIGHT_DIRECTION;
					arrList.addAll(getPathToThirdExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1,initialAngel2,roundAboutDirection));break;
				case 4: 
					initialAngel1 = 0;
					initialAngel2 = 210;
					y1 = y+GraphicsConfig.BLOCK_SIDE_SIZE;
					x1 = x-2*GraphicsConfig.BLOCK_SIDE_SIZE;
					x2 = x+3*GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y+GraphicsConfig.BLOCK_SIDE_SIZE;
					x = x + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y - GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.BOTTOM_TO_BOTTOM_DIRECTION;
					arrList.addAll(getPathToSameExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1,initialAngel2,roundAboutDirection));break;
				}
				
			} 
		}else if (previousBlockType == RoadConfig.HORIZONTAL_BLOCK || previousBlockType == RoadConfig.HORIZONTAL_ENTER_BLOCK || previousBlockType == RoadConfig.HORIZONTAL_EXIT_BLOCK){
			if (direction==1){
				
				initialAngel = 180;
				
				
				switch (exitNumber){
				case 1: 
					y1 = y;
					x1 = x;
					x2 = x+GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y-GraphicsConfig.BLOCK_SIDE_SIZE;
					initialAngel1 = 90;
					initialAngel2 = 78;
					x = x +GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.LEFT_TO_TOP_DIRECTION;
					arrList.addAll(getPathToFirstExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1, initialAngel2,roundAboutDirection));break;
				case 2: 
					y1 = y;
					x1 = x;
					x2 = x+3*GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y;
					initialAngel1 = 90;
					initialAngel2 = 168;
					x = x +GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.LEFT_TO_RIGHT_DIRECTION;
					arrList.addAll(getPathToSecondExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1, initialAngel2,roundAboutDirection));break;
				case 3: 
					initialAngel1 = 90;
					initialAngel2 = 210;
					y1 = y-2*GraphicsConfig.BLOCK_SIDE_SIZE;
					x1 = x;
					x2 = x+4*GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y+2*GraphicsConfig.BLOCK_SIDE_SIZE;
					x = x +GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.LEFT_TO_BOTTOM_DIRECTION;
					arrList.addAll(getPathToThirdExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1,initialAngel2,roundAboutDirection));break;
				case 4: 
					initialAngel1 = 90;
					initialAngel2 = 300;
					y1 = y-2*GraphicsConfig.BLOCK_SIDE_SIZE;
					x1 = x;
					x2 = x;
					y2 = y+3*GraphicsConfig.BLOCK_SIDE_SIZE;
					x = x +GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.LEFT_TO_LEFT_DIRECTION;
					arrList.addAll(getPathToSameExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1,initialAngel2,roundAboutDirection));break;
				}
				
			}
			else {
				initialAngel = 0;
				
				
				switch (exitNumber){
				case 1: 
					initialAngel1 = 270;
					initialAngel2 = 258;
					y1 = y+GraphicsConfig.BLOCK_SIDE_SIZE;
					x1 = x+GraphicsConfig.BLOCK_SIDE_SIZE;
					x2 = x;
					y2 = y+2*GraphicsConfig.BLOCK_SIDE_SIZE;
					x = x - GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.RIGHT_TO_BOTTOM_DIRECTION;
					arrList.addAll(getPathToFirstExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1,initialAngel2,roundAboutDirection));break;
				case 2: 
					initialAngel1 = 270;
					initialAngel2 = 348;
					y1 = y+GraphicsConfig.BLOCK_SIDE_SIZE;
					x1 = x+GraphicsConfig.BLOCK_SIDE_SIZE;
					x2 = x-2*GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y+GraphicsConfig.BLOCK_SIDE_SIZE;
					x = x - GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.RIGHT_TO_LEFT_DIRECTION;
					arrList.addAll(getPathToSecondExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1,initialAngel2,roundAboutDirection));break;
				case 3: 
					initialAngel1 = 270;
					initialAngel2 = 30;
					y1 = y+3*GraphicsConfig.BLOCK_SIDE_SIZE;
					x1 = x+GraphicsConfig.BLOCK_SIDE_SIZE;
					x2 = x-3*GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y-GraphicsConfig.BLOCK_SIDE_SIZE;
					x = x - GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.RIGHT_TO_TOP_DIRECTION;
					arrList.addAll(getPathToThirdExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1,initialAngel2,roundAboutDirection));break;
				case 4: 
					initialAngel1 = 270;
					initialAngel2 = 120;
					y1 = y+3*GraphicsConfig.BLOCK_SIDE_SIZE;
					x1 = x+GraphicsConfig.BLOCK_SIDE_SIZE;
					x2 = x+GraphicsConfig.BLOCK_SIDE_SIZE;
					y2 = y-2*GraphicsConfig.BLOCK_SIDE_SIZE;
					x = x - GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
					y = y + GraphicsConfig.BLOCK_SIDE_SIZE/2;
					roundAboutDirection = RoadConfig.RIGHT_TO_RIGHT_DIRECTION;
					arrList.addAll(getPathToSameExit(x,y,x1,y1,x2,y2,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel,initialAngel1,initialAngel2,roundAboutDirection));break;
				}
			}
		}
		
		return arrList;
	}
	
	/**
	 * Gets the path to first exit.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param x1
	 *            the x1
	 * @param y1
	 *            the y1
	 * @param x2
	 *            the x2
	 * @param y2
	 *            the y2
	 * @param blockType
	 *            the block type
	 * @param initialAngel
	 *            the initial angel
	 * @param initialAngel1
	 *            the initial angel1
	 * @param initialAngel2
	 *            the initial angel2
	 * @param direction
	 *            the direction
	 * @return the path to first exit
	 */
	public static ArrayList<PathPoint> getPathToFirstExit(int x, int y, int x1,int y1,int x2, int y2, short blockType, int initialAngel, int initialAngel1, int initialAngel2, short direction){
		ArrayList<PathPoint> arrList = new ArrayList<PathPoint>();
		
		float theta = initialAngel1;
		float radius = 16;
		System.out.println("Radius" + radius);
		float stepsNb = (float) (radius*Math.PI*44/100) ;
		
		System.out.println("StepNB" + stepsNb);
		float step = 78/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta-=step;
			int xp = (int) (x1+radius*Math.cos(theta*Math.PI / 180));
			int yp = (int) (y1+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, direction,(int) theta, -1 ));
		}
		
	    theta = (float)initialAngel;
	    theta+=18;
		radius = GraphicsConfig.CAR_ROUND_ABOUT_POSITION_1;
		System.out.println("Radius" + radius);
		stepsNb = (float) (radius*Math.PI*30/100) ;
		System.out.println("StepNB" + stepsNb);
		step = 54/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta+=step;
			int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
			int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, direction,(int) theta, -1 ));
		}
		
		theta = initialAngel2;
		radius = 16;
		System.out.println("Radius" + radius);
		stepsNb = (float) (radius*Math.PI*44/100) ;
		
		System.out.println("StepNB" + stepsNb);
		step = 78/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta-=step;
			int xr = (int) (x2+radius*Math.cos(theta*Math.PI / 180));
			int yr = (int) (y2+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xr + " "+ yr);
			arrList.add(new PathPoint(blockType, xr, yr, direction,(int) theta, -1 ));
		}
		
		return arrList;
	}
	
	/**
	 * Gets the path to second exit.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param x1
	 *            the x1
	 * @param y1
	 *            the y1
	 * @param x2
	 *            the x2
	 * @param y2
	 *            the y2
	 * @param blockType
	 *            the block type
	 * @param initialAngel
	 *            the initial angel
	 * @param initialAngel1
	 *            the initial angel1
	 * @param initialAngel2
	 *            the initial angel2
	 * @param direction
	 *            the direction
	 * @return the path to second exit
	 */
	public static ArrayList<PathPoint> getPathToSecondExit(int x, int y, int x1, int  y1, int x2, int y2, short blockType, int initialAngel, int initialAngel1, int initialAngel2, short direction){
		
		
		ArrayList<PathPoint> arrList = new ArrayList<PathPoint>();
		
		float theta = initialAngel1;
		float radius = 16;
		System.out.println("Radius" + radius);
		float stepsNb = (float) (radius*Math.PI*44/100) ;
		
		System.out.println("StepNB" + stepsNb);
		float step = 78/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta-=step;
			int xp = (int) (x1+radius*Math.cos(theta*Math.PI / 180));
			int yp = (int) (y1+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, direction,(int) theta, -1 ));
		}
		
		theta = (float)initialAngel;
		theta+=18;
		radius = GraphicsConfig.CAR_ROUND_ABOUT_POSITION_1;
		System.out.println("Radius" + radius);
		stepsNb = (float) (radius*Math.PI*80/100) ;
		
		System.out.println("StepNB" + stepsNb);
		step = 144/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta+=step;
			int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
			int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, direction,(int) theta, -1 ));
		}
		
		theta = initialAngel2;
		radius = 16;
		System.out.println("Radius" + radius);
		stepsNb = (float) (radius*Math.PI*44/100) ;
		
		System.out.println("StepNB" + stepsNb);
		step = 78/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta-=step;
			int xr = (int) (x2+radius*Math.cos(theta*Math.PI / 180));
			int yr = (int) (y2+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xr + " "+ yr);
			arrList.add(new PathPoint(blockType, xr, yr, direction,(int) theta, -1 ));
		}
		
		return arrList;
	}
	
	/**
	 * Gets the path to third exit.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param x1
	 *            the x1
	 * @param y1
	 *            the y1
	 * @param x2
	 *            the x2
	 * @param y2
	 *            the y2
	 * @param blockType
	 *            the block type
	 * @param initialAngel
	 *            the initial angel
	 * @param initialAngel1
	 *            the initial angel1
	 * @param initialAngel2
	 *            the initial angel2
	 * @param direction
	 *            the direction
	 * @return the path to third exit
	 */
	public static ArrayList<PathPoint> getPathToThirdExit(int x, int y,int x1, int y1, int x2, int y2, short blockType, int initialAngel, int initialAngel1, int initialAngel2, short direction){
		ArrayList<PathPoint> arrList = new ArrayList<PathPoint>();
		
		float theta = initialAngel1;
		float radius = 115;
		System.out.println("Radius" + radius);
		float stepsNb = (float) (radius*Math.PI*17/100) ;
		
		System.out.println("StepNB" + stepsNb);
		float step = 30/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta-=step;
			int xp = (int) (x1+radius*Math.cos(theta*Math.PI / 180));
			int yp = (int) (y1+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, direction,(int) theta, -1 ));
		}
		
		theta = (float)initialAngel;
		theta+=47;
		radius = GraphicsConfig.CAR_ROUND_ABOUT_POSITION_2;
		System.out.println("Radius" + radius);
		stepsNb = (float) (radius*(Math.PI*96/100)) ;
		
		System.out.println("StepNB" + stepsNb);
		step = 172/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta+=step;
			int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
			int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, direction,(int) theta, -1 ));
		}
		
		theta = initialAngel2;
		radius = 115;
		System.out.println("Radius" + radius);
		stepsNb = (float) (radius*Math.PI*17/100) ;
		
		System.out.println("StepNB" + stepsNb);
		step = 30/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta-=step;
			int xp = (int) (x2+radius*Math.cos(theta*Math.PI / 180));
			int yp = (int) (y2+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, direction,(int) theta, -1 ));
		}
		return arrList;
	}
	
	/**
	 * Gets the path to same exit.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param x1
	 *            the x1
	 * @param y1
	 *            the y1
	 * @param x2
	 *            the x2
	 * @param y2
	 *            the y2
	 * @param blockType
	 *            the block type
	 * @param initialAngel
	 *            the initial angel
	 * @param initialAngel1
	 *            the initial angel1
	 * @param initialAngel2
	 *            the initial angel2
	 * @param direction
	 *            the direction
	 * @return the path to same exit
	 */
	public static ArrayList<PathPoint> getPathToSameExit(int x, int y, int x1, int y1, int x2, int y2, short blockType, int initialAngel, int initialAngel1, int initialAngel2, short direction){
ArrayList<PathPoint> arrList = new ArrayList<PathPoint>();
		
		float theta = initialAngel1;
		float radius = 115;
		System.out.println("Radius" + radius);
		float stepsNb = (float) (radius*Math.PI*17/100) ;
		
		System.out.println("StepNB" + stepsNb);
		float step = 30/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta-=step;
			int xp = (int) (x1+radius*Math.cos(theta*Math.PI / 180));
			int yp = (int) (y1+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, direction,(int) theta, -1 ));
		}
		
		theta = (float)initialAngel;
		theta+=47;
		radius = GraphicsConfig.CAR_ROUND_ABOUT_POSITION_2;
		System.out.println("Radius" + radius);
		stepsNb = (float) (radius*(Math.PI*96/100+Math.PI/2)) ;
		
		System.out.println("StepNB" + stepsNb);
		step = 262/(stepsNb);
		System.out.println("Step" + step);
		int xp = 0;
		int yp = 0;
		for (int i = 0; i < stepsNb-1; i++){
			theta+=step;
			xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
			yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, direction,(int) theta, -1 ));
		}
		
		
		theta = initialAngel2;
		radius = 115;
		System.out.println("Radius" + radius);
		stepsNb = (float) (radius*Math.PI*17/100) ;
		
		System.out.println("StepNB" + stepsNb);
		step = 30/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta-=step;
			xp = (int) (x2+radius*Math.cos(theta*Math.PI / 180));
			yp = (int) (y2+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, direction,(int) theta, -1 ));
		}
		return arrList;
	}
}
