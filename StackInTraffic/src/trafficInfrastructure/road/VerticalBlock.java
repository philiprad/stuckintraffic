package trafficInfrastructure.road;

import graphicsLoader.GraphicsConfig;

import java.util.ArrayList;

import trafficInfrastructure.roadPath.PathPoint;

public class VerticalBlock {

		public static int [] nextBlockLocation(int x1,int y1, int x2, int y2){
			int [] tab = {x1, y1+GraphicsConfig.BLOCK_SIDE_SIZE, x2 , y2 +GraphicsConfig.BLOCK_SIDE_SIZE};
			return tab;
		}
		
		public static ArrayList<PathPoint> getPathPoints(int x, int y, short blockType){
			ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
			for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE; i++){
				arrPathPoints.add(new PathPoint(blockType,x+GraphicsConfig.CAR_INVERSE_POSITION , y+i,1, -1));
			}
			return arrPathPoints;
		}
		
		public static ArrayList<PathPoint> getInversePathPoints(int x, int y, short blockType){
			
			ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
			for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE; i++){
				arrPathPoints.add(new PathPoint(blockType,x+GraphicsConfig.CAR_POSITION , y+GraphicsConfig.BLOCK_SIDE_SIZE-i, -1 ,-1));
			}
			return arrPathPoints;
		}

}
