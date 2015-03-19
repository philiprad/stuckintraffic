package trafficInfrastructure.road;

import graphicsLoader.GraphicsConfig;

import java.util.ArrayList;

import trafficInfrastructure.roadPath.PathPoint;

public class TempDoubleBlock {
	public static ArrayList<PathPoint> getPathPoints(int x, int y, short blockType, int direction, int idDouble){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		int temp = 0;
		switch(blockType){
		case RoadConfig.HORIZONTAL_DOUBLE_BLOCK: temp = RoadConfig.HORIZONTAL_DOUBLE_BLOCK_TEMP; 
		case RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK: temp = RoadConfig.HORIZONTAL_DOUBLE_BLOCK_TEMP;
		case RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK: temp = RoadConfig.HORIZONTAL_DOUBLE_BLOCK_TEMP;
		case RoadConfig.VERTICAL_DOUBLE_BLOCK: temp = RoadConfig.VERTICAL_DOUBLE_BLOCK_TEMP; 
		case RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK: temp = RoadConfig.VERTICAL_DOUBLE_BLOCK_TEMP;
		case RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK: temp = RoadConfig.VERTICAL_DOUBLE_BLOCK_TEMP;
		case RoadConfig.INTERSECTION_DOUBLE_BLOCK: temp = RoadConfig.INTERSECTION_DOUBLE_TEMP; 
		case RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK: temp = RoadConfig.MIXED_HORIZONTAL_INTERSECTION_TEMP; 
		case RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK: temp = RoadConfig.MIXED_VERTICAL_INTERSECTION_TEMP; 
		}
		for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE*2; i++){
		arrPathPoints.add(new PathPoint(blockType, temp, temp, direction , -1, idDouble));
		}
		return arrPathPoints;
	}
}
