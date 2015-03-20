package trafficInfrastructure.road;

import graphicsLoader.GraphicsConfig;

import java.util.ArrayList;

import trafficInfrastructure.roadPath.PathPoint;

public class IntersectionMixedHorizontalBlock {
	
	
	
	
	public static ArrayList<PathPoint> getLeft1To1TopPath(int x, int y, short blockType){

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
				arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_1_TO_TOP_1_DIRECTION,(int) theta  ));
			}
		return arrPathPoints;
	}

	public static ArrayList<PathPoint> getLeft1To2TopPath(int x, int y, short blockType){

		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		
			for (int i = 0; i < GraphicsConfig.DISTANCE_BETWEEN_LANES; i++){
				arrPathPoints.add(new PathPoint(blockType, x+i, y+GraphicsConfig.CAR_POSITION_DOUBLE_LANE, RoadConfig.LEFT_1_TO_TOP_2_DIRECTION , 90 ));
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
				arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_1_TO_TOP_2_DIRECTION,(int) theta  ));
			}
		return arrPathPoints;
	}
	
	public static ArrayList<PathPoint> getLeft1To1BottomPath(int x, int y, short blockType){
		
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		
		for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE; i++){
			arrPathPoints.add(new PathPoint(blockType, x+i, y+GraphicsConfig.CAR_POSITION, RoadConfig.LEFT_1_TO_1_BOTTOM  , 90  ));
		}
		x+=GraphicsConfig.BLOCK_SIDE_SIZE;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_1_TO_1_BOTTOM ,(int) theta  ));
		}
	return arrPathPoints;
	}
	
public static ArrayList<PathPoint> getLeft1To2BottomPath(int x, int y, short blockType){
		
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		
		for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE - GraphicsConfig.DISTANCE_BETWEEN_LANES; i++){
			arrPathPoints.add(new PathPoint(blockType, x+i, y+GraphicsConfig.CAR_POSITION, RoadConfig.LEFT_1_TO_2_BOTTOM , 90 ));
		}
		x+=GraphicsConfig.BLOCK_SIDE_SIZE- GraphicsConfig.DISTANCE_BETWEEN_LANES;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.LEFT_1_TO_2_BOTTOM ,(int) theta  ));
		}
	return arrPathPoints;
	}
	
	// TODO
	public static ArrayList<PathPoint> getBottom2To1RightPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		
		x+=GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.DISTANCE_BETWEEN_LANES;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.BOTTOM_2_TO_RIGHT_1_DIRECTION ,(int) theta  ));
		}
		
		for (int i = 0; i < GraphicsConfig.CAR_INVERSE_POSITION; i++){
			arrPathPoints.add(new PathPoint(blockType, x+i, y-GraphicsConfig.CAR_INVERSE_POSITION, RoadConfig.BOTTOM_2_TO_RIGHT_1_DIRECTION, 90 ));
		}
	return arrPathPoints;
	}

	
	//TODO
	
	public static ArrayList<PathPoint> getTop2To1LeftPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		x+=GraphicsConfig.BLOCK_SIDE_SIZE - GraphicsConfig.DISTANCE_BETWEEN_LANES;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.TOP_2_TO_LEFT_1_DIRECTION ,(int) theta  ));
		}
		
		for (int i = 0; i < GraphicsConfig.CAR_INVERSE_POSITION; i++){
			arrPathPoints.add(new PathPoint(blockType, x-i, y+GraphicsConfig.CAR_INVERSE_POSITION, RoadConfig.TOP_2_TO_LEFT_1_DIRECTION, 270 ));
		}
		
	return arrPathPoints;
	}
	
	public static ArrayList<PathPoint> getRight1To1BottomPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		y+=GraphicsConfig.BLOCK_SIDE_SIZE;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.RIGHT_1_TO_BOTTOM_1_DIRECTION ,(int) theta  ));
		}
	return arrPathPoints;
	}
	
	public static ArrayList<PathPoint> getRight1To2BottomPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		for (int i = 0; i < GraphicsConfig.DISTANCE_BETWEEN_LANES; i++){
			arrPathPoints.add(new PathPoint(blockType, x+GraphicsConfig.BLOCK_SIDE_SIZE*2-i, y+GraphicsConfig.CAR_INVERSE_POSITION,RoadConfig.RIGHT_1_TO_BOTTOM_2_DIRECTION, 270 ));
		}
		y+=GraphicsConfig.BLOCK_SIDE_SIZE;
		x+=GraphicsConfig.BLOCK_SIDE_SIZE*2-GraphicsConfig.DISTANCE_BETWEEN_LANES;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.RIGHT_1_TO_BOTTOM_2_DIRECTION ,(int) theta  ));
		}
	return arrPathPoints;
	}
	
	public static ArrayList<PathPoint> getRight1To1TopPath(int x, int y, short blockType){
		
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		
		for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE; i++){
			arrPathPoints.add(new PathPoint(blockType, x+GraphicsConfig.BLOCK_SIDE_SIZE*2-i, y+GraphicsConfig.CAR_INVERSE_POSITION, RoadConfig.RIGHT_1_TO_1_TOP , 270 ));
		}
		
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.RIGHT_1_TO_1_TOP ,(int) theta  ));
		}
	return arrPathPoints;
	}
	
	public static ArrayList<PathPoint> getRight1To2TopPath(int x, int y, short blockType){
		
		
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		for (int i = 0; i < GraphicsConfig.BLOCK_SIDE_SIZE - GraphicsConfig.DISTANCE_BETWEEN_LANES; i++){
			arrPathPoints.add(new PathPoint(blockType, x+GraphicsConfig.BLOCK_SIDE_SIZE*2-i, y+GraphicsConfig.CAR_INVERSE_POSITION,  RoadConfig.RIGHT_1_TO_2_TOP, 270 ));
		}
		x+=GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.DISTANCE_BETWEEN_LANES;
		
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.RIGHT_1_TO_2_TOP ,(int) theta  ));
		}
	return arrPathPoints;
	}
	
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.TOP_1_TO_RIGHT_1_DIRECTION ,(int) theta  ));
		}
	return arrPathPoints;
	}
	
	public static ArrayList<PathPoint> getBottom1To1LeftPath(int x, int y, short blockType){
		ArrayList<PathPoint> arrPathPoints = new ArrayList<PathPoint>();
		y+=GraphicsConfig.BLOCK_SIDE_SIZE;
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
			arrPathPoints.add(new PathPoint(blockType, xp, yp, RoadConfig.BOTTOM_1_TO_LEFT_1_DIRECTION ,(int) theta  ));
		}
	return arrPathPoints;
	}
	
	
	
	
}
