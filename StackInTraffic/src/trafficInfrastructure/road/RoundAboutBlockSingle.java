package trafficInfrastructure.road;

import graphicsLoader.GraphicsConfig;

import java.util.ArrayList;

import trafficInfrastructure.roadPath.PathPoint;

public class RoundAboutBlockSingle {
	
	public static ArrayList<PathPoint> getPath(int x, int y,short exitNumber,int previousBlockType, int direction){
		
		int initialAngel = 0;
		int roundAboutDirection= 0;
		ArrayList<PathPoint> arrList = new ArrayList<PathPoint>();
		if (previousBlockType == RoadConfig.VERTICAL_BLOCK || previousBlockType == RoadConfig.VERTICAL_ENTER_BLOCK || previousBlockType == RoadConfig.VERTICAL_EXIT_BLOCK){
			if (direction==1){
				initialAngel = 270;
				x = x + GraphicsConfig.BLOCK_SIDE_SIZE/2;
				y = y + GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
			} else {
				initialAngel = 90;
				x = x + GraphicsConfig.BLOCK_SIDE_SIZE/2;
				y = y - GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.BLOCK_SIDE_SIZE/2;
			} 
		}else if (previousBlockType == RoadConfig.HORIZONTAL_BLOCK || previousBlockType == RoadConfig.HORIZONTAL_ENTER_BLOCK || previousBlockType == RoadConfig.HORIZONTAL_EXIT_BLOCK){
			if (direction==1){
				initialAngel = 180;
				x = x +GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE/2;
				y = y + GraphicsConfig.BLOCK_SIDE_SIZE/2;
			}
			else {
				initialAngel = 0;
				x = x - GraphicsConfig.BLOCK_SIDE_SIZE+GraphicsConfig.BLOCK_SIDE_SIZE/2;
				y = y + GraphicsConfig.BLOCK_SIDE_SIZE/2;
			}
		}
		switch (exitNumber){
			case 1: arrList.addAll(getPathToFirstExit(x,y,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel));break;
			case 2: arrList.addAll(getPathToSecondExit(x,y,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel));break;
			case 3: arrList.addAll(getPathToThirdExit(x,y,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel));break;
			case 4: arrList.addAll(getPathToSameExit(x,y,RoadConfig.ROUND_ABOUT_BLOCK, initialAngel));break;
		}
		return arrList;
	}
	
	public static ArrayList<PathPoint> getPathToFirstExit(int x, int y, short blockType, int initialAngel){
		ArrayList<PathPoint> arrList = new ArrayList<PathPoint>();
		float theta = (float)initialAngel;
		float radius = GraphicsConfig.CAR_ROUND_ABOUT_POSITION_1;
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
			arrList.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_TO_TOP_DIRECTION,(int) theta ));
		}
		return arrList;
	}
	public static ArrayList<PathPoint> getPathToSecondExit(int x, int y, short blockType, int initialAngel){
		ArrayList<PathPoint> arrList = new ArrayList<PathPoint>();
		float theta = (float)initialAngel;
		float radius = GraphicsConfig.CAR_ROUND_ABOUT_POSITION_1;
		System.out.println("Radius" + radius);
		float stepsNb = (float) (radius*Math.PI) ;
		
		System.out.println("StepNB" + stepsNb);
		float step = 180/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta+=step;
			int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
			int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_TO_TOP_DIRECTION,(int) theta ));
		}
		return arrList;
	}
	public static ArrayList<PathPoint> getPathToThirdExit(int x, int y, short blockType, int initialAngel){
		ArrayList<PathPoint> arrList = new ArrayList<PathPoint>();
		float theta = (float)initialAngel;
		float radius = GraphicsConfig.CAR_ROUND_ABOUT_POSITION_1;
		System.out.println("Radius" + radius);
		float stepsNb = (float) (radius*Math.PI+radius*Math.PI/2) ;
		
		System.out.println("StepNB" + stepsNb);
		float step = 270/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta+=step;
			int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
			int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_TO_TOP_DIRECTION,(int) theta ));
		}
		return arrList;
	}
	
	public static ArrayList<PathPoint> getPathToSameExit(int x, int y, short blockType, int initialAngel){
		ArrayList<PathPoint> arrList = new ArrayList<PathPoint>();
		float theta = (float)initialAngel;
		float radius = GraphicsConfig.CAR_ROUND_ABOUT_POSITION_1;
		System.out.println("Radius" + radius);
		float stepsNb = (float) (radius*Math.PI+2) ;
		
		System.out.println("StepNB" + stepsNb);
		float step = 360/(stepsNb);
		System.out.println("Step" + step);
		for (int i = 0; i < stepsNb-1; i++){
			theta+=step;
			int xp = (int) (x+radius*Math.cos(theta*Math.PI / 180));
			int yp = (int) (y+radius*Math.sin(theta*Math.PI / 180));
			System.out.println(xp + " "+ yp);
			arrList.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_TO_TOP_DIRECTION,(int) theta ));
		}
		return arrList;
	}
}
