/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package agents;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import trafficInfrastructure.road.RoadConfig;
import trafficInfrastructure.roadPath.DoublePath;
import trafficInfrastructure.roadPath.Path;
import trafficInfrastructure.roadPath.PathPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class Car.
 */
public class Car {
	
	/** The car type. */
	private short carType;
	
	/** The co emission. */
	private double coEmission;
	
	/** The path. */
	private Path path;
	
	/** The counter. */
	private int counter;
	
	/** The speed. */
	private int speed = 5;
	
	/** The path end. */
	private short pathEnd = -1;
	
	private int laneChoise = -1;
	
	private int moveBuffer = 0;
	
	private int doublePathCounter = 0;
	
	private boolean isPrevBlockDouble = true;
	
	private int changeLaneCounter = 0;
	
	/**
	 * Instantiates a new car.
	 *
	 * @param path the path
	 */
	public Car (Path path){
		this.path = path;
		this.counter = 0;
		this.speed = 0;
	}
	
	/**
	 * Draw car.
	 *
	 * @param g2d the g2d
	 * @param ib the ib
	 */
	public void drawCar(Graphics2D g2d, ImagesBuilder ib){
		PathPoint pathP = this.path.getPathPoints().get(counter);
		if  (pathP.getBlockType() == RoadConfig.HORIZONTAL_BLOCK || pathP.getBlockType() == RoadConfig.HORIZONTAL_ENTER_BLOCK || pathP.getBlockType() == RoadConfig.HORIZONTAL_EXIT_BLOCK ){
			if (pathP.getDirection() == 1) g2d.drawImage(ib.getCarRight(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
			else g2d.drawImage(ib.getCarLeft(),pathP.getX() - GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
		} else if (pathP.getBlockType() == RoadConfig.HORIZONTAL_DOUBLE_BLOCK || pathP.getBlockType() == RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || pathP.getBlockType() == RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK){
			if (pathP.getDirection() == 1){
				if(this.laneChoise == 2){
					g2d.drawImage(ib.getCarRight(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2+GraphicsConfig.DISTANCE_BETWEEN_LANES, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
				} else if (this.laneChoise == 3) {
					this.changeLaneCounter+=this.speed/2;
					if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
						g2d.drawImage(ib.getCarRight(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2+this.changeLaneCounter, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
					} else {
						g2d.drawImage(ib.getCarRight(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2+GraphicsConfig.DISTANCE_BETWEEN_LANES, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
						this.changeLaneCounter = 0;
						this.laneChoise = 2;
					}
				}
					else {
					g2d.drawImage(ib.getCarRight(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
				}
			}
			else{
				
				if(this.laneChoise == 2){
					g2d.drawImage(ib.getCarLeft(),pathP.getX() - GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2 - GraphicsConfig.DISTANCE_BETWEEN_LANES, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
				}
				else{
					g2d.drawImage(ib.getCarLeft(),pathP.getX() - GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
				}
			}
		}
		else if (pathP.getBlockType() == RoadConfig.VERTICAL_BLOCK || pathP.getBlockType() == RoadConfig.VERTICAL_ENTER_BLOCK || pathP.getBlockType() == RoadConfig.VERTICAL_EXIT_BLOCK){
			if (pathP.getDirection() == 1) g2d.drawImage(ib.getCarDown(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
			else g2d.drawImage(ib.getCarUp(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
		}
		else if (pathP.getBlockType() == RoadConfig.VERTICAL_DOUBLE_BLOCK || pathP.getBlockType() == RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || pathP.getBlockType() == RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
			if (pathP.getDirection() == 1) {
				if(this.laneChoise == 2){
					g2d.drawImage(ib.getCarDown(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2- GraphicsConfig.DISTANCE_BETWEEN_LANES, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
				} else {
					g2d.drawImage(ib.getCarDown(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
				}
			}
			else {
				if(this.laneChoise == 2){
					g2d.drawImage(ib.getCarUp(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2+ GraphicsConfig.DISTANCE_BETWEEN_LANES, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
				} else {
					g2d.drawImage(ib.getCarUp(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
					
				}
			}
		} else if (pathP.getBlockType() == RoadConfig.INTERSECTION_BLOCK || pathP.getBlockType() == RoadConfig.INTERSECTION_UP_BLOCK || pathP.getBlockType() == RoadConfig.INTERSECTION_RIGHT_BLOCK || pathP.getBlockType() == RoadConfig.INTERSECTION_DOWN_BLOCK || pathP.getBlockType() == RoadConfig.INTERSECTION_LEFT_BLOCK || pathP.getBlockType() == RoadConfig.ROUND_ABOUT_BLOCK || pathP.getBlockType() == RoadConfig.INTERSECTION_DOUBLE_BLOCK || pathP.getBlockType() == RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK || pathP.getBlockType() == RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
			
			 AffineTransform origXform = g2d.getTransform();
		     AffineTransform newXform = (AffineTransform)(origXform.clone());
		     //center of rotation is center of the panel
		     int xRot = pathP.getX();
		     int yRot = pathP.getY();
		     newXform.rotate(Math.toRadians(pathP.getRotation()), xRot, yRot);
		     g2d.setTransform(newXform);
		     //draw image centered in panel
		     g2d.drawImage(ib.getCarUp(),pathP.getX() - GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null);
		    
		     g2d.setTransform(origXform);
		 
		}
		
	}
	
	/**
	 * Move.
	 */
	public void move(){
		
		
			if (this.path.getPathPoints().size()-speed > this.counter){		
				this.counter+= speed;
				
			} else {
				this.pathEnd = 1;
			}
	}
	
	/**
	 * If path end.
	 *
	 * @return true, if successful
	 */
	public int getLaneChoice(){
		return this.laneChoise;
	}
	
	public void setLane(int choise){
		this.laneChoise = choise;
	}
	public boolean ifPathEnd(){
		if (this.pathEnd == 1) return true;
		else return false;
	}
	
	/**
	 * Gets the car x.
	 *
	 * @return the car x
	 */
	public int getCarX(){
		return this.path.getPathPoints().get(this.counter).getX();
	}
	
	/**
	 * Gets the car x after.
	 *
	 * @param n
	 *            the n
	 * @return the car x after
	 */
	public int getCarXAfter(int n){
		return this.path.getPathPoints().get(this.counter+n).getX();
	}
	
	/**
	 * Gets the car y.
	 *
	 * @return the car y
	 */
	public int getCarY(){
		return this.path.getPathPoints().get(this.counter).getY();
	}
	
	/**
	 * Gets the car y after.
	 *
	 * @param n
	 *            the n
	 * @return the car y after
	 */
	public int getCarYAfter(int n){
		return this.path.getPathPoints().get(this.counter+n).getY();
	}
	
	/**
	 * Gets the road block type.
	 *
	 * @return the road block type
	 */
	public short getRoadBlockType(){
		return this.path.getPathPoints().get(this.counter).getBlockType();
	}
	
	/**
	 * Gets the next block.
	 *
	 * @param rdBlocks
	 *            the rd blocks
	 *            
	 * @return the next block
	 */
	public RoadBlock getNextBlock(Object [][] rdBlocks){
		int dist = 49;
		if (this.getRoadBlockType() == RoadConfig.INTERSECTION_BLOCK){
			dist = 54;
		}
		while(this.getCarXAfter(dist)/GraphicsConfig.BLOCK_SIDE_SIZE!=this.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE && this.getCarYAfter(dist)/GraphicsConfig.BLOCK_SIDE_SIZE!=this.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE){
			dist-=5;
		}
		return (RoadBlock)rdBlocks[this.getCarXAfter(dist)/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarYAfter(dist)/GraphicsConfig.BLOCK_SIDE_SIZE];
	}
	
	/**
	 * Gets the direction of the car.
	 *
	 * @return the direction
	 */
	
	public int getDirection(){
		return this.path.getPathPoints().get(this.counter).getDirection();
	}
	
	
	
	/**
	 * Speed management.
	 *
	 * @param rdBlocks
	 *            the rd blocks
	 * @param trafficLightList
	 *            the traffic light list
	 */
	public void speedManagement(Object [] [] rdBlocks /*, ArrayList<TrafficLight> trafficLightList*/){

		int currentI = this.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE;
		int currentJ = this.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE;
		 
		short roadBlockType = this.getRoadBlockType();
		int direction = this.getDirection();
		RoadBlock nextRoadBlock;
		RoadBlock currentRoadBlock = rdBlocks!=null?(RoadBlock)rdBlocks[currentI][currentJ]:null;
		 
			switch(roadBlockType){
			
				case RoadConfig.HORIZONTAL_BLOCK: {
					
					switch(direction){
						case RoadConfig.ORIGINAL_TRAFFIC_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI+1][currentJ];
							break;
						}
						case RoadConfig.INVERSE_TRAFFIC_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI-1][currentJ];
							if(((RoadBlock)rdBlocks[currentI][currentJ]).getBlockType() != roadBlockType)
							break;
						}
					}
					break;
				}
				case RoadConfig.HORIZONTAL_DOUBLE_BLOCK: {
					
					switch(direction){
						case RoadConfig.ORIGINAL_TRAFFIC_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI+2][currentJ];
							break;
						}
						case RoadConfig.INVERSE_TRAFFIC_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI-2][currentJ];
							if(((RoadBlock)rdBlocks[currentI][currentJ]).getBlockType() != roadBlockType)
							break;
						}
					}
				break;
				}
				case RoadConfig.VERTICAL_BLOCK: {
					
					switch(direction){
						case RoadConfig.ORIGINAL_TRAFFIC_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI][currentJ+1];
							break;
						}
						case RoadConfig.INVERSE_TRAFFIC_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI][currentJ-1];
							break;
						}						
					}
					break;
				}
				case RoadConfig.VERTICAL_DOUBLE_BLOCK: {
					
					switch(direction){
						case RoadConfig.ORIGINAL_TRAFFIC_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI][currentJ+2];
							break;
						}
						case RoadConfig.INVERSE_TRAFFIC_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI][currentJ-2];
							break;
						}						
					}
					break;
				}
				case RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK: {
					
					switch(direction){
						case RoadConfig.ORIGINAL_TRAFFIC_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI][currentJ];
							break;
						}
						case RoadConfig.INVERSE_TRAFFIC_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI][currentJ];
							break;
						}						
					}
					break;
				}
				case RoadConfig.ROUND_ABOUT_ENTER: {
					switch(direction){
						case RoadConfig.LEFT_TO_TOP_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI+1][currentJ-2];
							break;
						}
						case RoadConfig.LEFT_TO_RIGHT_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI+3][currentJ];
							break;
						}
						case RoadConfig.LEFT_TO_BOTTOM_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI+1][currentJ+2];
							break;
						}
						case RoadConfig.LEFT_TO_LEFT_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI-1][currentJ];
							break;
						}
						case RoadConfig.RIGHT_TO_BOTTOM_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI-1][currentJ+2];
							break;
						}
						case RoadConfig.RIGHT_TO_RIGHT_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI+1][currentJ];
							break;
						}
						case RoadConfig.RIGHT_TO_TOP_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI-1][currentJ-2];
							break;
						}
						case RoadConfig.RIGHT_TO_LEFT_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI-3][currentJ];
							break;
						}
						case RoadConfig.TOP_TO_RIGHT_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI+2][currentJ+1];
							break;
						}
						case RoadConfig.TOP_TO_BOTTOM_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI][currentJ+3];
							break;
						}
						case RoadConfig.TOP_TO_LEFT_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI-2][currentJ+1];
							break;
						}
						case RoadConfig.TOP_TO_TOP_DIRECTION: {
							nextRoadBlock= (RoadBlock)rdBlocks[currentI][currentJ-1];
							break;
						}
						case RoadConfig.BOTTOM_TO_LEFT_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI-2][currentJ-1];
							break;
						}
						case RoadConfig.BOTTOM_TO_TOP_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI][currentJ-1];
							break;
						}
						case RoadConfig.BOTTOM_TO_RIGHT_DIRECTION: {
							nextRoadBlock = (RoadBlock)rdBlocks[currentI+2][currentJ-1];
							break;
						}
						case RoadConfig.BOTTOM_TO_BOTTOM_DIRECTION: {
							nextRoadBlock= (RoadBlock)rdBlocks[currentI][currentJ+1];
							break;
						}
						
					}
					break;
				}
				
			}
		 
		//Traffic Light Rule 1
	/*	this.speed = 10;
		boolean trafficLightOnPath = false;
		short trafficLightIndex = -1;
		if(((RoadBlock)rdBlocks[this.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE]).isTrafficLightInside()){
			
			ArrayList<Short> trafficLightIndexList = ((RoadBlock)rdBlocks[this.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE]).getTrafficLightIndexList();
			for(Short index : trafficLightIndexList){
				if(trafficLightList.get(index).getDirection()==this.getDirection()){
					trafficLightOnPath = true;
					trafficLightIndex = index;
				}
			}
		}
		
		if (trafficLightOnPath == true){
			if (trafficLightList.get(trafficLightIndex).getState()!=AgentConfig.TRAFFIC_LIGHT_GREEN){
				if (trafficLightList.get(trafficLightIndex).getDistanceToTrafficLight(this.getCarX(), this.getCarY())<GraphicsConfig.BLOCK_SIDE_SIZE/5){
					this.speed=0;
				} else if(trafficLightList.get(trafficLightIndex).getDistanceToTrafficLight(this.getCarX(), this.getCarY())<GraphicsConfig.BLOCK_SIDE_SIZE/2){
					this.speed = 5;
				}
			} else {
				//TrafficLight Rule 1.1
				
				if (this.getNextBlock(rdBlocks).getBlockType() == RoadConfig.INTERSECTION_BLOCK){
					if (((RoadBlock) (rdBlocks[this.getCarXAfter(70)/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarYAfter(70)/GraphicsConfig.BLOCK_SIDE_SIZE])).isCarInside()){
						ArrayList <Car> carList = ((RoadBlock) (rdBlocks[this.getCarXAfter(70)/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarYAfter(70)/GraphicsConfig.BLOCK_SIDE_SIZE])).getCarList();
						short carCounter = 0;
						if(carList.size()>1){
							for(Car cr: carList){
								if ((this.path.getPathPoints().get(counter+70).getDirection() == cr.getDirection() ) && (cr.getCarSpeed() < 4)){
									carCounter++;
								}
							}
							
							if (carCounter>1){
								this.speed = 0;
							}
						}
					}
				}
			}
		}
		
		*/
		
		//Other Cars Rule 2
		acceleration(currentRoadBlock);
		int distance = 0;
		if (((RoadBlock)rdBlocks[this.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE]).isCarInside()){
			ArrayList<Car> carList = ((RoadBlock)rdBlocks[this.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE]).getCarList();
			for(Car carFromList : carList){
				if (!this.equals(carFromList)){
					if(this.getDirection()==carFromList.getDirection()){
						if ((carFromList.getCarX()-this.getCarX())*this.getDirection()>=0 && this.getDirection()*(carFromList.getCarY()-this.getCarY())>=0){
							distance = (int) (Math.sqrt(Math.pow(carFromList.getCarX()-this.getCarX(), 2.0)+Math.pow(carFromList.getCarY()-this.getCarY(), 2.0)));
							if(distance>0 && distance < 35){
								this.deceleration(distance,0);
							}
							else if (distance>0 && distance<80){
								this.deceleration(distance,3);
							}
						}
					}
				}
			}
			int end = 0;
			
			
			
			if (this.getDirection()==RoadConfig.ORIGINAL_TRAFFIC_DIRECTION){
				if (this.getRoadBlockType() == RoadConfig.HORIZONTAL_EXIT_BLOCK || this.getRoadBlockType() == RoadConfig.VERTICAL_EXIT_BLOCK || this.getRoadBlockType() == RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK || this.getRoadBlockType() == RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK ){
					end = 1;
					
				}
			} else {
				if (this.getRoadBlockType() == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.getRoadBlockType() == RoadConfig.VERTICAL_ENTER_BLOCK || this.getRoadBlockType() == RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || this.getRoadBlockType() == RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK ){
					end = 1;
				}
			}
			if (end == 0){
				carList = this.getNextBlock(rdBlocks).getCarList();
				for(Car carFromList : carList){
					if (!this.equals(carFromList)){
						if(this.getDirection()==carFromList.getDirection()){
							if ((carFromList.getCarX()-this.getCarX())*this.getDirection()>=0 && this.getDirection()*(carFromList.getCarY()-this.getCarY())>=0){
								distance = (int) (Math.sqrt(Math.pow(carFromList.getCarX()-this.getCarX(), 2.0)+Math.pow(carFromList.getCarY()-this.getCarY(), 2.0)));
								if(distance>0 && distance < 35){
									this.deceleration(distance,0);
								}
								else if (distance>0 && distance<80){
									this.deceleration(distance,3);
								}
							}
						}
					}
				}
			}
		}
//		
	}
	
	/**
	 * Gets the car speed.
	 *
	 * @return the car speed
	 */
	public int getCarSpeed(){
		return this.speed;
	}
	
	/**
	 * Acceleration.
	 */
	public void acceleration(RoadBlock currentRoadBlock){
		if(currentRoadBlock.getSpeedLimit() < this.speed){
			this.deceleration(getDistanceToNextBlock(this.getCarX(), this.getCarY(),currentRoadBlock),currentRoadBlock.getSpeedLimit());
		}else{
			while(this.speed<currentRoadBlock.getSpeedLimit()){
				this.speed++;
			}
		}
	}
	
	/**
	 * Deceleration.
	 *
	 * @param dist the dist
	 */
	public void deceleration(int dist, int finalSpeed){
		
//		if (this.speed>2){
//			float sp = (float)speed/(float)dist;
//			this.speed += speed/dist;
//			if(sp-(float)(speed/dist)>0.5){
//				this.speed--;
//			}
//		}
		System.out.println("distance: " + dist);
		if (dist>0){
			this.speed =  ((finalSpeed * finalSpeed) - (this.speed * this.speed)) / (2*dist);
			System.out.println("speed: " + this.speed);
		}
	}
	/**
	 * Gets the distance to traffic light.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return the distance to traffic light
	 */
	public int getDistanceToNextBlock(int x, int y, RoadBlock currentRoadBlock){
		System.out.println("current block in getDistanceFunction: " + currentRoadBlock.getBlockType());
		int distance =0;
		if (currentRoadBlock.getBlockType() == RoadConfig.HORIZONTAL_BLOCK || currentRoadBlock.getBlockType() == RoadConfig.HORIZONTAL_ENTER_BLOCK || currentRoadBlock.getBlockType() == RoadConfig.HORIZONTAL_EXIT_BLOCK || currentRoadBlock.getBlockType() == RoadConfig.HORIZONTAL_DOUBLE_BLOCK || currentRoadBlock.getBlockType() == RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || currentRoadBlock.getBlockType() == RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK ){
			if (getDirection()==RoadConfig.ORIGINAL_TRAFFIC_DIRECTION){
				distance = Math.abs(this.getCurrentGridX()+49 - x)-GraphicsConfig.CAR_LENGTH/2;
			}
			else {
				distance = Math.abs(this.getCurrentGridX() - x)-GraphicsConfig.CAR_LENGTH/2;
			}
		}
		else if (currentRoadBlock.getBlockType() == RoadConfig.VERTICAL_BLOCK || currentRoadBlock.getBlockType() == RoadConfig.VERTICAL_ENTER_BLOCK || currentRoadBlock.getBlockType() == RoadConfig.VERTICAL_EXIT_BLOCK || currentRoadBlock.getBlockType() == RoadConfig.VERTICAL_DOUBLE_BLOCK || currentRoadBlock.getBlockType() == RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || currentRoadBlock.getBlockType() == RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
			if (getDirection()==RoadConfig.ORIGINAL_TRAFFIC_DIRECTION){
				distance = Math.abs(this.getCurrentGridY()+49 - y)-GraphicsConfig.CAR_LENGTH/2;
			}
			else {
				distance = Math.abs(this.getCurrentGridY() - y)-GraphicsConfig.CAR_LENGTH/2;
			}
		}
		System.out.println("getDistanceToNextBlock: " + distance);
		return distance;
	}
	/**
	 * gets the current X coordinate in the grid in pixels
	 * @return
	 */
	public int getCurrentGridX() {
		return this.path.getPathPoints().get(this.counter).getX();
	}
	/**
	 * gets the current Y coordinate in the grid in pixels
	 * @return
	 */
	public int getCurrentGridY() {
		return this.path.getPathPoints().get(this.counter).getY();
	}
	/**
	 * Stop.
	 */
	public void stop(){
		this.speed = 0;
	}

	
}
