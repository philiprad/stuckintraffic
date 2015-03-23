package agents;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import trafficInfrastructure.road.RoadConfig;
import trafficInfrastructure.roadPath.Path;
import trafficInfrastructure.roadPath.PathPoint;

public class StandartCar {
	
		
		/** The car type. */
		private short driverType;
		
		/** The co emission. */
		private double coEmission;
		
		/** The path. */
		private Path path;
		
		/** The counter. */
		private int counter;
		
		/** The speed. */
		private int speed;
		
		/** The path end. */
		private short pathEnd = -1;
		
		private int laneChoise = -1;
		
		private int changeLaneCounter = 0;
		
		private int carX;
		
		private int carY;
		
		private boolean canMove;
		
		private Object [] [] roadBlock;
		
		private float acceleration;
		
		private float accelerationCounter = 0;
		
		private float decelerationCounter = 0;
		
		public StandartCar(Path path, short driverType, Object [] [] roadBlock,int laneChoise){
			this.driverType = driverType;
			this.path = path;
			this.roadBlock = roadBlock;
			this.laneChoise = laneChoise;
			this.updateCarCoordinates();
			this.speed =((RoadBlock)roadBlock[this.carX/GraphicsConfig.BLOCK_SIDE_SIZE][this.carY/GraphicsConfig.BLOCK_SIDE_SIZE]).getSpeedLimit();
			this.driverSpeedUpdate();
			
			if(this.driverType == AgentConfig.FAMILY_DRIVER){
				
					this.acceleration = AgentConfig.FAMILY_DRIVER_ACCELERATION;
			
			} else if (this.driverType == AgentConfig.FAST_DRIVER){
				
					this.acceleration = AgentConfig.FAST_DRIVER_ACCELERATION;
			} else {
				this.acceleration = AgentConfig.FAST_DRIVER_ACCELERATION;
			}
			
			
		}
		
		public void updateCarCoordinates(){
			if (this.laneChoise == 1){
				this.carX = this.getCarPathX();
				this.carY = this.getCarPathY();
			} else if (this.laneChoise == 2){
				this.carX = this.getCarPathX();
				this.carY = this.getCarPathY();
				
				if (this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_DOUBLE_BLOCK){
					if (this.getPathDirection() == 1){
						this.carY+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
					}
					else {
						this.carY-=GraphicsConfig.DISTANCE_BETWEEN_LANES;
					}
				} else if(this.getPathRoadBlockType()== RoadConfig.VERTICAL_DOUBLE_BLOCK) {
					if (this.getPathDirection() == 1){
						this.carX-=GraphicsConfig.DISTANCE_BETWEEN_LANES;
					}
					else {
						this.carX+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
					}
				}
			} else if (this.laneChoise == 3){
				if (this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_DOUBLE_BLOCK){
					if (this.getPathDirection() == 1){
						this.carY+=this.changeLaneCounter;
					}
					else {
						this.carY-=this.changeLaneCounter;
					}
				} else if(this.getPathRoadBlockType()== RoadConfig.VERTICAL_DOUBLE_BLOCK) {
					if (this.getPathDirection() == 1){
						this.carX-=this.changeLaneCounter;
					}
					else {
						this.carX+=this.changeLaneCounter;
					}
				}
			} else if (this.laneChoise == 4){
				if (this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_DOUBLE_BLOCK){
					if (this.getPathDirection() == 1){
						this.carY-=this.changeLaneCounter;
					}
					else {
						this.carY+=this.changeLaneCounter;
					}
				} else if(this.getPathRoadBlockType()== RoadConfig.VERTICAL_DOUBLE_BLOCK) {
					if (this.getPathDirection() == 1){
						this.carX+=this.changeLaneCounter;
					}
					else {
						this.carX-=this.changeLaneCounter;
					}
				}
			}
		}
		
		public void driverSpeedUpdate(){
			if(this.driverType == AgentConfig.FAMILY_DRIVER){
				if(this.speed>3){
					this.speed=this.speed*80/100;
				}
			} else if (this.driverType == AgentConfig.FAST_DRIVER);
				if(this.speed>3){
					this.speed=this.speed*120/100;
				}
		}
		
		public void move(){
			
			
			if (this.path.getPathPoints().size()-speed > this.counter){		
				this.counter+= speed;
				this.updateCarCoordinates();
			} else {
				this.pathEnd = 1;
			}
		}
		
		public void acceleration(){
			this.accelerationCounter+= this.acceleration;
			if (accelerationCounter>1){
				this.speed++;
				this.accelerationCounter-=1;
			}
		}
		
		public void deceleration(){
			
		}
		
		public int getCarX(){
			return this.carX;
		}
		
		public int getCarY(){
			return this.carY;
		}
		
		public int getLaneChoice(){
			return this.laneChoise;
		}
		
		public void setLane(int choise){
			this.laneChoise = choise;
		}
		
		public boolean isPathEnd(){
			if (this.pathEnd == 1) return true;
			else return false;
		}
		
		public int getCarPathX(){
			return this.path.getPathPoints().get(this.counter).getX();
		}
		
		public int getCarPathY(){
			return this.path.getPathPoints().get(this.counter).getY();
		}
		
		public short getPathRoadBlockType(){
			return this.path.getPathPoints().get(this.counter).getBlockType();
		}
		
		public int getPathDirection(){
			return this.path.getPathPoints().get(this.counter).getDirection();
		}
		
		public int getCarSpeed(){
			return this.speed;
		}
		
		
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
}
