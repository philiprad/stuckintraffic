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
import trafficInfrastructure.roadPath.Path;
import trafficInfrastructure.roadPath.PathPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class Car.
 */
public class Car {
	
	private short carType;
	private double coEmission;
	
	/** The path. */
	private Path path;
	
	/** The counter. */
	private int counter;
	
	/** The speed. */
	private int speed = 10;
	
	/** The path end. */
	private short pathEnd = -1;
	
	private short remove = 0;
	
	/**
	 * Instantiates a new car.
	 *
	 * @param path the path
	 */
	public Car (Path path){
		this.path = path;
		this.counter = 0;
	}
	
	/**
	 * Draw car.
	 *
	 * @param g2d the g2d
	 * @param ib the ib
	 */
	public void drawCar(Graphics2D g2d, ImagesBuilder ib){
		PathPoint pathP = this.path.getPathPoints().get(counter);
		if  (pathP.getBlockType() == RoadConfig.HORIZONTAL_BLOCK || pathP.getBlockType() == RoadConfig.HORIZONTAL_ENTER_BLOCK || pathP.getBlockType() == RoadConfig.HORIZONTAL_EXIT_BLOCK){
			if (pathP.getDirection() == 1) g2d.drawImage(ib.getCarRight(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
			else g2d.drawImage(ib.getCarLeft(),pathP.getX() - GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
		}
		else if (pathP.getBlockType() == RoadConfig.VERTICAL_BLOCK || pathP.getBlockType() == RoadConfig.VERTICAL_ENTER_BLOCK || pathP.getBlockType() == RoadConfig.VERTICAL_EXIT_BLOCK){
			if (pathP.getDirection() == 1) g2d.drawImage(ib.getCarDown(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
			else g2d.drawImage(ib.getCarUp(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
		} else if (pathP.getBlockType() == RoadConfig.INTERSECTION_BLOCK){
			
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
		if (this.path.getPathPoints().size()-speed>this.counter){		
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
	
	public int getCarYAfter(int n){
		return this.path.getPathPoints().get(this.counter+n).getY();
	}
	
	public short getRoadBlockType(){
		return this.path.getPathPoints().get(this.counter).getBlockType();
	}
	
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
	 * Gets the direction.
	 * @param rdBlocks 
	 *
	 * @return the direction
	 */
	
	public int getDirection(){
		return this.path.getPathPoints().get(this.counter).getDirection();
	}
	
	/**
	 * Speed management.
	 *
	 * @param rdBlocks the rd blocks
	 */
	public void speedManagement(Object [] [] rdBlocks , ArrayList<TrafficLight> trafficLightList){
		//Traffic Light Rule 1
		this.speed = 10;
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
			}
		}
		
		//Other Cars Rule 2
		int distance = 0;
		if (((RoadBlock)rdBlocks[this.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE]).isCarInside()){
			ArrayList<Car> carList = ((RoadBlock)rdBlocks[this.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE]).getCarList();
			for(Car carFromList : carList){
				if (!this.equals(carFromList)){
					if(this.getDirection()==carFromList.getDirection()){
						if ((carFromList.getCarX()-this.getCarX())*this.getDirection()>=0 && this.getDirection()*(carFromList.getCarY()-this.getCarY())>=0){
							distance = (int) (Math.sqrt(Math.pow(carFromList.getCarX()-this.getCarX(), 2.0)+Math.pow(carFromList.getCarY()-this.getCarY(), 2.0)));
							if(distance>0 && distance < 35){
								this.speed = 0;
							}
							else if (distance>0 && distance<40){
								this.speed=5;
							}
						}
					}
				}
			}
			int end = 0;
			/*if (this.getDirection()==RoadConfig.ORIGINAL_TRAFFIC_DIRECTION){
				if(this.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE < (GraphicsConfig.GRID_WIDTH-1) || this.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE < (GraphicsConfig.GRID_HEIGHT-1)){
					end = 1; 
				}
			} else if (this.getDirection()==RoadConfig.INVERSE_TRAFFIC_DIRECTION){
				if(this.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE < 2 || this.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE < 2){
					end = 1; 
				}
			}*/
			if (this.getDirection()==RoadConfig.ORIGINAL_TRAFFIC_DIRECTION){
				if (this.getRoadBlockType() == RoadConfig.HORIZONTAL_EXIT_BLOCK || this.getRoadBlockType() == RoadConfig.VERTICAL_EXIT_BLOCK ){
					end = 1;
				}
			} else {
				if (this.getRoadBlockType() == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.getRoadBlockType() == RoadConfig.VERTICAL_ENTER_BLOCK ){
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
									speed = 0;
								}
								else if (distance>0 && distance<40){
									this.deceleration(35);
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * Acceleration.
	 *
	 * @param dist the dist
	 */
	public void acceleration(){
		this.speed++;
	}
	
	/**
	 * Deceleration.
	 *
	 * @param dist the dist
	 */
	public void deceleration(int dist){
		if (this.speed>2){
			float sp = (float)speed/(float)dist;
			this.speed += speed/dist;
			if(sp-(float)(speed/dist)>0.5){
				this.speed--;
			}
		}
	}
	
	/**
	 * Stop.
	 */
	public void stop(){
		this.speed = 0;
	}
	
	public void setRemove(){
		this.remove = 1;
	}
	
	
}
