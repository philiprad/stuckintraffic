package trafficInfrastructure.road;

import graphicsLoader.GraphicsConfig;

import java.util.ArrayList;

import trafficInfrastructure.roadPath.PathPoint;

public class VerticalDoubleBlock {
	public static int [] nextBlockLocation(int x1,int y1, int x2, int y2){
		int [] tab = {x1, y1+GraphicsConfig.BLOCK_SIDE_SIZE*2, x2 , y2 +GraphicsConfig.BLOCK_SIDE_SIZE*2};
		return tab;
	}
	
	
	public static ArrayList<PathPoint> getPathPoints(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE*2; i++){
			arrPathPoints.add(new PathPoint(blockType,x+GraphicsConfig.CAR_INVERSE_POSITION_DOUBLE_LANE, y+i,1, -1));
		}
		return arrPathPoints;
	}
	
	
	public static ArrayList<PathPoint> getInversePathPoints(int x, int y, short blockType){
		
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE*2; i++){
			arrPathPoints.add(new PathPoint(blockType,x+GraphicsConfig.CAR_POSITION_DOUBLE_LANE , y+GraphicsConfig.BLOCK_SIDE_SIZE*2-i, -1 ,-1));
		}
		return arrPathPoints;
	}

}
