/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package agents;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;
import graphicsLoader.ImagesSelector;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import trafficInfrastructure.road.RoadConfig;
import trafficInfrastructure.roadPath.Path;
import trafficInfrastructure.roadPath.PathPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class StandartCar.
 */
public class StandartCar {
	
		
		/** The car type. */
		private short driverType;
		
		/** The car type. */
		private short carType;
		
		/** The co emission. */
		private double coEmission = 0;
		
		/** The petro consumption. */
		private double petroConsumption = 0;
		
		/** The path. */
		private Path path;
		
		/** The counter. */
		private int counter;
		
		/** The speed. */
		private int speed;
		
		/** The path end. */
		private short pathEnd = -1;
		
		/** The lane choise. */
		private int laneChoise = -1;
		
		/** The change lane counter. */
		private int changeLaneCounter = 0;
		
		/** The car x. */
		private int carX;
		
		/** The car y. */
		private int carY;
				
		/** The road block. */
		private Object [] [] roadBlock;
		
		/** The acceleration. */
		private float acceleration;
		
		/** The acceleration counter. */
		private float accelerationCounter = 0;
		
		/** The deceleration counter. */
		private float decelerationCounter = 0;
		
		/** The security distance. */
		private int securityDistance = 0;
		
		/** The security zero distance. */
		private int securityZeroDistance = 10;
		
		/** The traffic lights. */
		private ArrayList <TrafficLight> trafficLights;
		
		/** The direction after traffic light. */
		private int directionAfterTrafficLight = 0;
		
		/** The is arriving. */
		private boolean isArriving = false;
		
		/** The car direction next block. */
		private short carDirectionNextBlock = 0;
		
		/** The car direction2 next road block. */
		private short carDirection2NextRoadBlock = 0;
		
		/** The next road block x. */
		private int nextRoadBlockX = -1;
		
		/** The next road block y. */
		private int nextRoadBlockY = -1;
		
		/** The current road block. */
		private RoadBlock currentRoadBlock;
		
		/** The next road block. */
		private RoadBlock nextRoadBlock ;
		
		/**
		 * Instantiates a new standart car.
		 *
		 * @param path the path
		 * @param driverType the driver type
		 * @param roadBlock the road block
		 * @param laneChoise the lane choise
		 * @param trafficLights the traffic lights
		 * @param carType the car type
		 */
		public StandartCar(Path path, short driverType, Object [] [] roadBlock,int laneChoise,  ArrayList <TrafficLight> trafficLights, int carType){
			this.carType = (short) carType;
			this.trafficLights = trafficLights;
			this.driverType = driverType;
			this.path = path;
			this.roadBlock = roadBlock;
			this.laneChoise = laneChoise;
			this.updateCarCoordinates();
			this.speed =((RoadBlock)roadBlock[this.carX/GraphicsConfig.BLOCK_SIDE_SIZE][this.carY/GraphicsConfig.BLOCK_SIDE_SIZE]).getSpeedLimit();
			this.driverSpeedUpdate();
			this.updateSecurityDistance();
			switch(this.carType){
			case AgentConfig.ELECTRIC_CAR :
				this.coEmission = AgentConfig.ELECTRIC_CAR_EMISSION;
				this.petroConsumption = AgentConfig.ELECTRIC_CAR_CONSUMPTION;
				break;
			case AgentConfig.HYBRID_CAR :
				this.coEmission = AgentConfig.HYBRID_CAR_EMISSION;
				this.petroConsumption = AgentConfig.HYBRID_CAR_CONSUMPTION;
				break;
			case AgentConfig.PETROL_CAR :
				this.coEmission = AgentConfig.PETROL_CAR_EMISSION;
				this.petroConsumption = AgentConfig.PETROL_CAR_CONSUMPTION;
				break;
			}
			if(this.driverType == AgentConfig.FAMILY_DRIVER){
				
					this.acceleration = AgentConfig.FAMILY_DRIVER_ACCELERATION;
					this.coEmission = this.coEmission*110/100;
					this.petroConsumption = this.petroConsumption*110/100;
			
			} else if (this.driverType == AgentConfig.FAST_DRIVER){
				
					this.acceleration = AgentConfig.FAST_DRIVER_ACCELERATION;
					this.coEmission = this.coEmission*120/100;
					this.petroConsumption = this.petroConsumption*120/100;
			} else {
				this.acceleration = AgentConfig.NORMAL_DRIVER_ACCELERATION;
			}
			
			
		}
		
		/**
		 * Sets the checks if is arriving.
		 *
		 * @param n the new checks if is arriving
		 */
		public void setIsArriving(boolean n){
			this.isArriving = n;
		}
		
		/**
		 * Gets the speed.
		 *
		 * @return the speed
		 */
		public int getSpeed(){
			return this.speed;
		}
		
		/**
		 * Gets the co emission.
		 *
		 * @return the co emission
		 */
		public double getCoEmission(){
			return this.coEmission;
		}
		
		/**
		 * Gets the petrol emission.
		 *
		 * @return the petrol emission
		 */
		public double getPetrolEmission(){
			return this.petroConsumption;
		}
		
		/**
		 * Update security distance.
		 */
		public void updateSecurityDistance(){
			if (this.speed == 0){
				this.securityDistance = this.securityZeroDistance;
			} else {
				this.securityDistance = this.speed*3 + GraphicsConfig.CAR_LENGTH;
			}
		}
		
		/**
		 * Update lane choise.
		 */
		public void updateLaneChoise(){
			if (this.getPathRoadBlockType()==RoadConfig.INTERSECTION_DOUBLE_BLOCK || this.getPathRoadBlockType()==RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK || this.getPathRoadBlockType()==RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
				if(this.getPathDirection()>100 || this.getPathDirection()<-100){
					this.laneChoise = 2;
				} else {
					this.laneChoise = 1;
				}
			}
		}
		
		/**
		 * Update car coordinates.
		 */
		public void updateCarCoordinates(){
			if (this.laneChoise == 1){
				this.carX = this.getCarPathX();
				this.carY = this.getCarPathY();
			} else if (this.laneChoise == 2){
				this.carX = this.getCarPathX();
				this.carY = this.getCarPathY();
				
				if (this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK){
					if (this.getPathDirection() == 1){
						this.carY+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
					}
					else {
						this.carY-=GraphicsConfig.DISTANCE_BETWEEN_LANES;
					}
				} else if(this.getPathRoadBlockType()== RoadConfig.VERTICAL_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK) {
					if (this.getPathDirection() == 1){
						this.carX-=GraphicsConfig.DISTANCE_BETWEEN_LANES;
					}
					else {
						this.carX+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
					}
				}
			} else if (this.laneChoise == 3){
				this.carX = this.getCarPathX();
				this.carY = this.getCarPathY();
				if (this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK){
					if (this.getPathDirection() == 1){
						this.carY+=this.changeLaneCounter;
					}
					else {
						this.carY-=this.changeLaneCounter;
					}
				} else if(this.getPathRoadBlockType()== RoadConfig.VERTICAL_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK) {
					if (this.getPathDirection() == 1){
						this.carX-=this.changeLaneCounter;
					}
					else {
						this.carX+= this.changeLaneCounter;
					}
				}
			} else if (this.laneChoise == 4){
				this.carX = this.getCarPathX();
				this.carY = this.getCarPathY();
				if (this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK){
					if (this.getPathDirection() == 1){
						this.carY+=GraphicsConfig.DISTANCE_BETWEEN_LANES-this.changeLaneCounter;
					}
					else {
						this.carY-=GraphicsConfig.DISTANCE_BETWEEN_LANES+this.changeLaneCounter;
					}
				} else if(this.getPathRoadBlockType()== RoadConfig.VERTICAL_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || this.getPathRoadBlockType()== RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK) {
					if (this.getPathDirection() == 1){
						this.carX-=GraphicsConfig.DISTANCE_BETWEEN_LANES+this.changeLaneCounter;
					}
					else {
						this.carX+=GraphicsConfig.DISTANCE_BETWEEN_LANES-this.changeLaneCounter;
					}
				}
			}
		}
		
		/**
		 * Driver speed update.
		 */
		public void driverSpeedUpdate(){
			if(this.driverType == AgentConfig.FAMILY_DRIVER){
				if(this.speed>3){
					this.speed=this.speed*80/100;
				}
			} else if (this.driverType == AgentConfig.FAST_DRIVER){
				if(this.speed>3){
					this.speed=this.speed*110/100;
				}
			}
		}
		
		
		
		/**
		 * Move.
		 */
		public void move(){
			
			
			if (this.path.getPathPoints().size()-speed > this.counter){	
				this.counter+= speed;
				this.updateCarCoordinates();
				
				int change = this.needChange();
				if (change>-1){
					laneChoise=change;
				}
				this.updateLaneChoise();
			} else {
				this.pathEnd = 1;
			}
		}
		
		/**
		 * Acceleration.
		 */
		public void acceleration(){
			if (((RoadBlock)this.roadBlock[this.carX/GraphicsConfig.BLOCK_SIDE_SIZE][this.carY/GraphicsConfig.BLOCK_SIDE_SIZE]).getSpeedLimit()>this.speed){
				this.accelerationCounter+= this.acceleration;
				if (accelerationCounter>1){
				
					this.speed++;
					this.accelerationCounter-=1;
				}
			}
		}
		
		/**
		 * Deceleration.
		 *
		 * @param distance the distance
		 * @param finalDeceleration the final deceleration
		 */
		public void deceleration(int distance, int finalDeceleration){
			double decelerator = ((double)speed - finalDeceleration)/(double)distance;
			this.decelerationCounter+= decelerator;
			if(this.speed>this.decelerationCounter){
					if(decelerationCounter>=1){
						speed-=decelerationCounter;
						decelerationCounter-=(int)decelerationCounter;
					}
				} else {
					speed = 0;
				}
		}
		
		
		
		/**
		 * Gets the car x.
		 *
		 * @return the car x
		 */
		public int getCarX(){
			return this.carX;
		}
		
		/**
		 * Gets the car y.
		 *
		 * @return the car y
		 */
		public int getCarY(){
			return this.carY;
		}
		
		/**
		 * Gets the lane choice.
		 *
		 * @return the lane choice
		 */
		public int getLaneChoice(){
			return this.laneChoise;
		}
		
		/**
		 * Sets the lane.
		 *
		 * @param choise the new lane
		 */
		public void setLane(int choise){
			this.laneChoise = choise;
		}
		
		/**
		 * Checks if is path end.
		 *
		 * @return true, if is path end
		 */
		public boolean isPathEnd(){
			if (this.pathEnd == 1) return true;
			else return false;
		}
		
		/**
		 * Gets the car path x.
		 *
		 * @return the car path x
		 */
		public int getCarPathX(){
			return this.path.getPathPoints().get(this.counter).getX();
		}
		
		/**
		 * Gets the car path y.
		 *
		 * @return the car path y
		 */
		public int getCarPathY(){
			return this.path.getPathPoints().get(this.counter).getY();
		}
		
		/**
		 * Gets the path road block type.
		 *
		 * @return the path road block type
		 */
		public short getPathRoadBlockType(){
			return this.path.getPathPoints().get(this.counter).getBlockType();
		}
		
		/**
		 * Gets the path direction.
		 *
		 * @return the path direction
		 */
		public int getPathDirection(){
			return this.path.getPathPoints().get(this.counter).getDirection();
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
		 * Gets the exit.
		 *
		 * @return the exit
		 */
		public int getExit(){
			return this.path.getPathPoints().get(this.counter).getRoundAboutExit();
		}
		
		/**
		 * Gets the center x.
		 *
		 * @return the center x
		 */
		public int getCenterX(){
			return this.path.getPathPoints().get(this.counter).getRoundAboutCenterX();
		}
		
		/**
		 * Gets the center y.
		 *
		 * @return the center y
		 */
		public int getCenterY(){
			return this.path.getPathPoints().get(this.counter).getRoundAboutCenterY();
		}
		
		
		/**
		 * Gets the car x after.
		 *
		 * @param n the n
		 * @return the car x after
		 */
		public int getCarXAfter(int n){
			
			if (this.counter + n > this.path.getPathPoints().size()){
				return -1;
			} else {
				return this.path.getPathPoints().get(this.counter+n).getX();
			}
			
			
		}
		
		
		
		
		/**
		 * Gets the car y after.
		 *
		 * @param n the n
		 * @return the car y after
		 */
		public int getCarYAfter(int n){
			
			if (this.counter + n > this.path.getPathPoints().size()){
				return -1;
			} else {
				return this.path.getPathPoints().get(this.counter+n).getY();
			}
			
			
		}
		
		
		/**
		 * Next road block.
		 *
		 * @return the road block
		 */
		public RoadBlock nextRoadBlock(){
			RoadBlock roadBlock = null;
			for(int i = 10 ; i<=50 ;i+=10){
				if(counter+i<this.path.getPathPoints().size()){
					
					if(!this.roadBlock[this.carX/GraphicsConfig.BLOCK_SIDE_SIZE][this.carY/GraphicsConfig.BLOCK_SIDE_SIZE].equals(this.roadBlock[this.getCarXAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarYAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE])){
						
						roadBlock = (RoadBlock)this.roadBlock[this.getCarXAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarYAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE];
						carDirectionNextBlock = (short) this.path.getPathPoints().get(counter+i).getDirection();
						this.nextRoadBlockX = (this.getCarXAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE)*GraphicsConfig.BLOCK_SIDE_SIZE;
						this.nextRoadBlockY = (this.getCarYAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE)*GraphicsConfig.BLOCK_SIDE_SIZE;
						
						break;
					} else {
						this.nextRoadBlockX = this.path.getPathPoints().get(this.path.getPathPoints().size()-1).getX();
						this.nextRoadBlockY = this.path.getPathPoints().get(this.path.getPathPoints().size()-1).getY();
						
					}
				} 
			}
			
			return roadBlock;
			
		}
		
		/*public RoadBlock next2RoadBlock(){
			RoadBlock next2roadBlock = null;
			RoadBlock nextRoadBlock = this.nextRoadBlock();
			for(int i = 50 ; i<=100 ;i+=10){
				if(counter+i<this.path.getPathPoints().size()){
					
					if(!this.roadBlock[this.carX/GraphicsConfig.BLOCK_SIDE_SIZE][this.carY/GraphicsConfig.BLOCK_SIDE_SIZE].equals(this.roadBlock[this.getCarXAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarYAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE]) && !nextRoadBlock.equals(this.roadBlock[this.getCarXAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarYAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE])){
						
						next2roadBlock = (RoadBlock)this.roadBlock[this.getCarXAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarYAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE];
						this.carDirection2NextRoadBlock = (short) this.path.getPathPoints().get(counter+i).getDirection();
						this.nextRoadBlockX = (this.getCarXAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE)*GraphicsConfig.BLOCK_SIDE_SIZE;
						this.nextRoadBlockY = (this.getCarYAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE)*GraphicsConfig.BLOCK_SIDE_SIZE;
						break;
					}
				} 
			}
			
			return next2roadBlock;
		}*/
		
		
		/**
		 * Gets the block after intersection.
		 *
		 * @return the block after intersection
		 */
		public RoadBlock getBlockAfterIntersection(){
			RoadBlock roadBlock = null;
			
			
			for(int i = 70 ; i<=200 ;i+=10){
				if(counter+i<this.path.getPathPoints().size()){
					RoadBlock tempRoadBlock = (RoadBlock)this.roadBlock[this.getCarXAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarYAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE];
					
					if((tempRoadBlock.getBlockType()<4 || tempRoadBlock.getBlockType()>8) && tempRoadBlock.getBlockType()!=RoadConfig.INTERSECTION_DOUBLE_BLOCK && tempRoadBlock.getBlockType()!=RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK && tempRoadBlock.getBlockType()!= RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
						
						roadBlock = tempRoadBlock;
						this.directionAfterTrafficLight = this.path.getPathPoints().get(counter+i).getDirection();
						break;
						
					}
				} 			
			}
			return roadBlock;
		}
		
		/**
		 * Need change.
		 *
		 * @return the int
		 */
		public int needChange(){
			if(this.getPathRoadBlockType()>10 && this.getPathRoadBlockType()<17){
				for(int i = 10 ; i<=200 ;i+=10){
					if(counter+i<this.path.getPathPoints().size()){
						short blockType =  this.path.getPathPoints().get(counter+i).getBlockType();
						if(blockType == RoadConfig.INTERSECTION_DOUBLE_BLOCK || blockType == RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK || blockType == RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
							if(this.path.getPathPoints().get(counter+i).getDirection()<0 && this.laneChoise == 2 ){
								
								if (this.couldChange(1)){
									return 4;
								} else {
									return -1;
								}
							} else if (this.path.getPathPoints().get(counter+i).getDirection()>0 && this.laneChoise == 1 ){
								if (this.couldChange(2)){
									return 3;
								} else {
									return -1;
								}
							}
							
						}
					} else {
						return -1;	
					}
				}
				return -1;
			} else {
				return -1;
			}
		}
		
		/**
		 * Could change.
		 *
		 * @param way the way
		 * @return true, if successful
		 */
		public boolean couldChange(int way){
			this.updateScanningBlocs();
			boolean desicion = true;
			if(this.currentRoadBlock.getCarList().size() > 1){
				for(StandartCar car : this.currentRoadBlock.getCarList()){
					if(car.getLaneChoice()==way){
						desicion = false;
					}
				}
			}
			
			if(this.nextRoadBlock.getCarList().size() > 0){
				for(StandartCar car : this.currentRoadBlock.getCarList()){
					if(car.getLaneChoice()==way){
						desicion = false;
					}
				}
			}
			
			return desicion;
		}
		
		/**
		 * Update scanning blocs.
		 */
		public void updateScanningBlocs(){
			currentRoadBlock = (RoadBlock)this.roadBlock[this.carX/GraphicsConfig.BLOCK_SIDE_SIZE][this.carY/GraphicsConfig.BLOCK_SIDE_SIZE];
			nextRoadBlock = this.nextRoadBlock();
		}
		
		
		/**
		 * Update speed.
		 */
		public void updateSpeed(){
			
			this.updateScanningBlocs();
			//RoadBlock next2RoadBlock = this.next2RoadBlock();
			boolean trafficLightOnPath = false;
			short trafficLightIndex = -1;
			int distanceToNextObject = -1;
			int nextObjectSpeed = -1;
			int decelerationDistance = 0;
			
			int distanceToNextObjectNextBlock = -1;
			int nextObjectSpeedNextBlock = -1;
			int decelerationDistanceNextBlock = 0;
			
			int distanceToNextObjectNext2Block = -1;
			int nextObjectSpeedNext2Block = -1;
			int decelerationDistanceNext2Block = 0;
			
			int speedLimitBlock = 0;
			boolean isCarNextBlock = false;
			boolean isCarNext2Block = false;
			boolean isCarCurrentBlock = false;
			
			
			
			ArrayList <StandartCar> carList = currentRoadBlock.getCarList();

			if (currentRoadBlock.isTrafficLightInside()){
				
				
				ArrayList<Short> trafficLightIndexList = currentRoadBlock.getTrafficLightIndexList();
				
				for(Short index : trafficLightIndexList){
					
					if(trafficLights.get(index).getDirection()==this.getPathDirection()){
						short type = trafficLights.get(index).getType();
						if((type == 2 && this.laneChoise == 2) || (type == 2 && this.laneChoise == 3) || (type == 1 && this.laneChoise == 1) || (type == 1 && this.laneChoise == 4)){
							
							trafficLightOnPath = true;

							trafficLightIndex = index;
							
						}

					}
				}
			}

			if(carList.size()>1){
				int minDistance = 10000;
				//int index = this.getCarListIndex(carList ,this);
				int distanceToNextBlock = this.getDistanceToObject(this.nextRoadBlockX, this.nextRoadBlockY);
				for(StandartCar car: carList){ //Except the roand about it could be only 2 cars with the same direction and the same lane
						
						if (currentRoadBlock.getBlockType()!=RoadConfig.ROUND_ABOUT_BLOCK){
							if (!this.equals(car) && distanceToNextBlock>car.getDistanceToObject(this.nextRoadBlockX, this.nextRoadBlockY)){
							
								if(this.getPathDirection()==car.getPathDirection() && (this.getLaneChoice() == car.getLaneChoice() || car.getLaneChoice() == 3 || car.getLaneChoice() == 4) ){
									int distance = this.getDistanceToObject(car.getCarX(), car.getCarY());
									if (distance<minDistance){
										nextObjectSpeed = car.getSpeed();
										minDistance = distance-GraphicsConfig.CAR_LENGTH;
										distanceToNextObject = minDistance;
										this.updateSecurityDistance();
										if (nextObjectSpeed>0){
											decelerationDistance = minDistance - nextObjectSpeed*3;
										} else {
											decelerationDistance = minDistance - this.securityZeroDistance;
										}
										isCarCurrentBlock = true;
									//System.out.println("there is a car in the next block");
									}
								}
							}
						} /*else {
							if(!this.equals(car) && distanceToNextBlock>car.getDistanceToObject(this.nextRoadBlockX, this.nextRoadBlockY)){
								if((this.getExit() == 1 || this.getExit() == 2) && (car.getExit() == 1 || car.getExit() == 2 || car.getExit() == 3 || car.getExit() == 4) && (car.getDistanceToObject(car.getCenterX(), car.getCenterY())<GraphicsConfig.CAR_ROUND_ABOUT_POSITION_1 + 2) && (car.getDistanceToObject(this.getCenterX(), this.getCenterY())>GraphicsConfig.CAR_ROUND_ABOUT_POSITION_1 + 2)){
									int distance = this.getDistanceToObject(car.getCarX(), car.getCarY());
									if (distance<minDistance){
										nextObjectSpeed = car.getSpeed();
										minDistance = distance-GraphicsConfig.CAR_LENGTH;
										distanceToNextObject = minDistance;
										this.updateSecurityDistance();
										if (nextObjectSpeed>0){
											decelerationDistance = minDistance - nextObjectSpeed*3;
										} else {
											decelerationDistance = minDistance - this.securityZeroDistance;
										}
										isCarCurrentBlock = true;
									//System.out.println("there is a car in the next block");
									}
								} else if((this.getExit() == 3 || this.getExit() == 4) && (car.getExit() == 3 || car.getExit() == 4 || this.getExit() == 1 || this.getExit() == 2) && (car.getDistanceToObject(car.getCenterX(), car.getCenterY())<GraphicsConfig.CAR_ROUND_ABOUT_POSITION_2 + 2) && (car.getDistanceToObject(this.getCenterX(), this.getCenterY())>GraphicsConfig.CAR_ROUND_ABOUT_POSITION_2 + 2)){
									int distance = this.getDistanceToObject(car.getCarX(), car.getCarY());
									if (distance<minDistance){
										nextObjectSpeed = car.getSpeed();
										minDistance = distance-GraphicsConfig.CAR_LENGTH;
										distanceToNextObject = minDistance;
										this.updateSecurityDistance();
										if (nextObjectSpeed>0){
											decelerationDistance = minDistance - nextObjectSpeed*3;
										} else {
											decelerationDistance = minDistance - this.securityZeroDistance;
										}
										isCarCurrentBlock = true;
									//System.out.println("there is a car in the next block");
									
								}
							}
						}
					}*/
				}
			}  
			
			if(nextRoadBlock != null && !trafficLightOnPath ){
				 
				if (nextRoadBlock.isCarInside()){
					
					 carList = nextRoadBlock.getCarList();
					if(carList.size()>0){
						
						int minDistance = 10000;
						for(StandartCar car: carList){ //Except the roand about it could be only 2 cars with the same direction and the same lane

								if (!this.equals(car)){
									//if (nextRoadBlock.getBlockType() != RoadConfig.ROUND_ABOUT_BLOCK){
									if(carDirectionNextBlock==car.getPathDirection() && (this.getLaneChoice() == car.getLaneChoice() || car.getLaneChoice() == 3 || car.getLaneChoice() == 4) ){
										int distance = this.getDistanceToObject(car.getCarX(), car.getCarY());
										
										if (distance<minDistance){
											
											nextObjectSpeedNextBlock = car.getSpeed();
											minDistance = distance - GraphicsConfig.CAR_LENGTH;
											distanceToNextObjectNextBlock = minDistance;
											this.updateSecurityDistance();
											if (nextObjectSpeedNextBlock>0){
												decelerationDistanceNextBlock = minDistance - nextObjectSpeedNextBlock*3;
											} else {
												decelerationDistanceNextBlock = minDistance - this.securityZeroDistance;
											}
											
											isCarNextBlock = true;
										}
									}
								
								} /*else {
									
									if((this.getExit() == 1 || this.getExit() == 2) && (car.getExit() == 1 || car.getExit() == 2 || car.getExit() == 3 || car.getExit() == 4) && (car.getDistanceToObject(car.getCenterX(), car.getCenterY())<GraphicsConfig.CAR_ROUND_ABOUT_POSITION_1 + 2) && (car.getDistanceToObject(this.getCenterX(), this.getCenterY())>GraphicsConfig.CAR_ROUND_ABOUT_POSITION_1 + 2)){
											int distance = this.getDistanceToObject(car.getCarX(), car.getCarY());
											if (distance<minDistance){
												nextObjectSpeedNextBlock = car.getSpeed();
												minDistance = distance-GraphicsConfig.CAR_LENGTH;
												distanceToNextObjectNextBlock = minDistance;
												this.updateSecurityDistance();
												if (nextObjectSpeedNextBlock>0){
													decelerationDistanceNextBlock = minDistance - nextObjectSpeedNextBlock*3;
												} else {
													decelerationDistanceNextBlock = minDistance - this.securityZeroDistance;
												}
												isCarNextBlock = true;
											//System.out.println("there is a car in the next block");
											}
										} else if((this.getExit() == 3 || this.getExit() == 4) && (car.getExit() == 3 || car.getExit() == 4 || this.getExit() == 1 || this.getExit() == 2) || (car.getDistanceToObject(car.getCenterX(), car.getCenterY())<GraphicsConfig.CAR_ROUND_ABOUT_POSITION_2 + 2) && (car.getDistanceToObject(this.getCenterX(), this.getCenterY())>GraphicsConfig.CAR_ROUND_ABOUT_POSITION_2 + 2)){
											int distance = this.getDistanceToObject(car.getCarX(), car.getCarY());
											if (distance<minDistance){
												nextObjectSpeedNextBlock = car.getSpeed();
												minDistance = distance-GraphicsConfig.CAR_LENGTH;
												distanceToNextObject = minDistance;
												this.updateSecurityDistance();
												if (nextObjectSpeedNextBlock>0){
													decelerationDistanceNextBlock = minDistance - nextObjectSpeedNextBlock*3;
												} else {
													decelerationDistanceNextBlock = minDistance - this.securityZeroDistance;
												}
												isCarNextBlock = true;
											//System.out.println("there is a car in the next block");
											}
										}
									}
								}*/
							}
						}
					} 
				}  
			
			/*if(next2RoadBlock != null && !trafficLightOnPath ){
				 
				if (next2RoadBlock.isCarInside()){
					
					 carList = next2RoadBlock.getCarList();
					if(carList.size()>0){
						
						int minDistance = 10000;
						for(StandartCar car: carList){ //Except the roand about it could be only 2 cars with the same direction and the same lane

								if (!this.equals(car)){
									
									if(carDirection2NextRoadBlock==car.getPathDirection() && (this.getLaneChoice() == car.getLaneChoice() || car.getLaneChoice() == 3 || car.getLaneChoice() == 4) ){
										int distance = this.getDistanceToObject(car.getCarX(), car.getCarY());
										
										if (distance<minDistance){
										
											nextObjectSpeedNext2Block = car.getSpeed();
											minDistance = distance - GraphicsConfig.CAR_LENGTH;
											distanceToNextObjectNext2Block = minDistance;
											this.updateSecurityDistance();
											if (nextObjectSpeedNext2Block>0){
												decelerationDistanceNext2Block = minDistance - nextObjectSpeedNext2Block*3;
											} else {
												decelerationDistanceNext2Block = minDistance - this.securityZeroDistance;
											}
											isCarNext2Block = true;
											
										}
									}
								}
							}
						}
					} 
				}  */

			
			
			
			
			if (this.speed>currentRoadBlock.getSpeedLimit()){
				this.speed--;
			}
			
			
			
			
			if(isCarCurrentBlock && distanceToNextObject<this.securityDistance){
				/*if(nextRoadBlock.getBlockType() == RoadConfig.ROUND_ABOUT_BLOCK && nextRoadBlock.isCarInside()){
					this.speed--;
				} else*/
				
				if (distanceToNextObject<=this.securityZeroDistance){
					this.speed = 0;
				} else {
					this.deceleration(decelerationDistance, nextObjectSpeed);
				}
				
			} else
			if (trafficLightOnPath == true ){

				if (trafficLights.get(trafficLightIndex).getState()!=AgentConfig.TRAFFIC_LIGHT_GREEN){
					
					
					if(trafficLights.get(trafficLightIndex).getState()==AgentConfig.TRAFFIC_LIGHT_YELLOW && this.speed>4){
						this.acceleration();
					} else {
					
					if (trafficLights.get(trafficLightIndex).getDistanceToTrafficLight(this.getCarX(), this.getCarY())<GraphicsConfig.BLOCK_SIDE_SIZE/10){
						this.accelerationCounter =0;
						this.decelerationCounter = 0;
						this.speed=0;
						

					} else {
						 if(this.speed>3){
							 this.accelerationCounter = 0;
							 this.deceleration(trafficLights.get(trafficLightIndex).getDistanceToTrafficLight(this.getCarX(), this.getCarY()), this.speed);
						 }
						 else {
							 this.speed++;
						 }
						
					}
				}
	
				} else {
					
						boolean canGo = true;
						if (nextRoadBlock!=null){
							if ((nextRoadBlock.getBlockType()>3 && nextRoadBlock.getBlockType()<9) && nextRoadBlock.isCarInside()){
								canGo = false;
								System.out.println("CanGO");
							}
						}
					
					/*for (StandartCar car : nextRoadBlock.getCarList()){
						if (car.getPathDirection() == this.directionAfterTrafficLight && car.getLaneChoice() == 1){
							n++;
							System.out.println("there is a car");
						}
					}*/
					RoadBlock afterIntersectionBlock = this.getBlockAfterIntersection();
					if (afterIntersectionBlock!=null){
						int n = 0;
						for (StandartCar car : afterIntersectionBlock.getCarList()){
							if (car.getPathDirection() == this.directionAfterTrafficLight && car.getLaneChoice() == 1){
								n++;
								System.out.println("there is a car");
							}
						}
						
						if (n<1 && canGo){
							if (isCarNextBlock && distanceToNextObjectNextBlock<this.securityDistance){
								if (distanceToNextObjectNextBlock<=this.securityZeroDistance){
									this.speed = 0;
								} else {
									this.deceleration(decelerationDistanceNextBlock, nextObjectSpeedNextBlock);
								}
								
							} else {
								this.go(currentRoadBlock, nextRoadBlock);//this.acceleration(); 
							}
							//this.go(currentRoadBlock, nextRoadBlock);
							System.out.println("there is NO  car");
						} else {
							if (trafficLights.get(trafficLightIndex).getDistanceToTrafficLight(this.getCarX(), this.getCarY())<GraphicsConfig.BLOCK_SIDE_SIZE/10){
								this.accelerationCounter =0;
								this.decelerationCounter = 0;
								this.speed=0;
								

							} else {
								 if(this.speed>3){
									 this.accelerationCounter = 0;
									 this.deceleration(trafficLights.get(trafficLightIndex).getDistanceToTrafficLight(this.getCarX(), this.getCarY()), this.speed);
								 }
								 else {
									 this.speed++;
								 }
								
							}
						}
					} else {
						System.out.println("Null afterIntersectionBlock");
					}
					//if(nextRoadBlock!=null){
						//if (!nextRoadBlock.isCarInside()){
							
							/*if(!this.isArriving){
								afterIntersectionBlock.addArrivingCar(this);
								this.isArriving = true;
							}
							this.directionAfterTrafficLight = 0;
						}*/
					//}
				//} 
					
					
					/*RoadBlock afterIntersectionBlock = this.getBlockAfterIntersection();
					if (afterIntersectionBlock!=null){
						int n = 0;
						for (StandartCar car : afterIntersectionBlock.getCarList()){
							if (car.getPathDirection() == this.directionAfterTrafficLight && car.getLaneChoice() == 1){
								n++;
								System.out.println("there is a car");
							}
						}
						
						System.out.println("car arriving "+afterIntersectionBlock.getArrivingCarList().size());
						*/
						//if (n+afterIntersectionBlock.getArrivingCarList().size()<2){
							
					
					
						
					
				}
				
			} else if (isCarNextBlock && distanceToNextObjectNextBlock<this.securityDistance){
				
				if (distanceToNextObjectNextBlock<=this.securityZeroDistance){
					this.speed = 0;
				} else {
					this.deceleration(decelerationDistanceNextBlock, nextObjectSpeedNextBlock);
				}
				
			} else {
				this.go(currentRoadBlock, nextRoadBlock);//this.acceleration(); 
			} 
			
			//Round about Rule
			/*if (nextRoadBlock!=null){
				if (nextRoadBlock.getBlockType() == RoadConfig.ROUND_ABOUT_BLOCK && currentRoadBlock.getBlockType()!=RoadConfig.ROUND_ABOUT_BLOCK){
					
					int distanceToNextBlock = this.getDistanceToObject(this.nextRoadBlockX, this.nextRoadBlockY);
					if(nextRoadBlock.isCarInside()){
						if (distanceToNextBlock<GraphicsConfig.BLOCK_SIDE_SIZE/10){
							this.accelerationCounter =0;
							this.decelerationCounter = 0;
							this.speed=0;
						
						}	
						else {
							if(this.speed>3){
								this.accelerationCounter = 0;
								this.deceleration(trafficLights.get(trafficLightIndex).getDistanceToTrafficLight(this.getCarX(), this.getCarY()), this.speed);
							}
							else {
								this.speed++;
							}
						
						}
					}
				}
			} */
			
		}
		
		
		/**
		 * Go.
		 *
		 * @param currentRoadBlock the current road block
		 * @param nextRoadBlock the next road block
		 */
		public void go(RoadBlock currentRoadBlock , RoadBlock nextRoadBlock){
			if(this.speed<this.getBlockSpeedLimitForDriver(currentRoadBlock.getSpeedLimit())){
				if(nextRoadBlock!=null){
					if(this.speed<this.getBlockSpeedLimitForDriver(nextRoadBlock.getSpeedLimit())){
						//this.acceleration();
						this.speed++;
						
					} else {
						this.accelerationCounter = 0;
						
					}
				}
				else {
				//	this.acceleration();
					this.speed++;
				}
			}
			else {
				this.accelerationCounter = 0;
			}
		}
		
		/**
		 * Stop.
		 */
		public void stop(){
			this.speed = 0;
		}
		
		/**
		 * Gets the block speed limit for driver.
		 *
		 * @param speedLimit the speed limit
		 * @return the block speed limit for driver
		 */
		public int getBlockSpeedLimitForDriver(int speedLimit){
			int sLimit = speedLimit;
			if(this.driverType == AgentConfig.FAMILY_DRIVER){
				
					sLimit=speedLimit*80/100;
					
				
			} else if (this.driverType == AgentConfig.FAST_DRIVER){
				
					sLimit=speedLimit*110/100;
					
			}

			return sLimit;
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
				if (pathP.getDirection() == 1) g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.RIGHT, ib),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
				else g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.LEFT, ib),pathP.getX() - GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
			} else if (pathP.getBlockType() == RoadConfig.HORIZONTAL_DOUBLE_BLOCK || pathP.getBlockType() == RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || pathP.getBlockType() == RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK){
				if (pathP.getDirection() == 1){
					if(this.laneChoise == 2){
						g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.RIGHT, ib),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2+GraphicsConfig.DISTANCE_BETWEEN_LANES, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
					} else if (this.laneChoise == 3) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.RIGHT, ib),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2+this.changeLaneCounter, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
						} else {
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.RIGHT, ib),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2+GraphicsConfig.DISTANCE_BETWEEN_LANES, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 2;
						}
					} else if (this.laneChoise == 4) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.RIGHT, ib),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2+GraphicsConfig.DISTANCE_BETWEEN_LANES-this.changeLaneCounter, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
						} else {
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.RIGHT, ib),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 1;
						}
					}
						else {
						g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.RIGHT, ib),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
					}
				}
				else{
					
					if(this.laneChoise == 2){
						g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.LEFT, ib),pathP.getX() - GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2 - GraphicsConfig.DISTANCE_BETWEEN_LANES, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
					} else if (this.laneChoise == 3) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.LEFT, ib),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2-this.changeLaneCounter, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
						} else {
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.LEFT, ib),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2-GraphicsConfig.DISTANCE_BETWEEN_LANES, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 2;
						}
					} else if (this.laneChoise == 4) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.LEFT, ib),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2-GraphicsConfig.DISTANCE_BETWEEN_LANES+this.changeLaneCounter, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
						} else {
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.LEFT, ib),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 1;
						}
					}
					else{
						g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.LEFT, ib),pathP.getX() - GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
					} 
				}
			}
			else if (pathP.getBlockType() == RoadConfig.VERTICAL_BLOCK || pathP.getBlockType() == RoadConfig.VERTICAL_ENTER_BLOCK || pathP.getBlockType() == RoadConfig.VERTICAL_EXIT_BLOCK){
				if (pathP.getDirection() == 1) g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.DOWN, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
				else g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.UP, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
			}
			else if (pathP.getBlockType() == RoadConfig.VERTICAL_DOUBLE_BLOCK || pathP.getBlockType() == RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || pathP.getBlockType() == RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
				if (pathP.getDirection() == 1) {
					if(this.laneChoise == 2){
						g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.DOWN, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2- GraphicsConfig.DISTANCE_BETWEEN_LANES, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
					} else if (this.laneChoise == 3) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.DOWN, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2-this.changeLaneCounter, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
						} else {
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.DOWN, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2 - GraphicsConfig.DISTANCE_BETWEEN_LANES, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 2;
						}
					} else if (this.laneChoise == 4) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.DOWN, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2 - GraphicsConfig.DISTANCE_BETWEEN_LANES + this.changeLaneCounter, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
						} else {
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.DOWN, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 1;
						}
					} else {
						g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.DOWN, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
					}
				}
				else {
					if(this.laneChoise == 2){
						g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.UP, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2+ GraphicsConfig.DISTANCE_BETWEEN_LANES, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
					} else if (this.laneChoise == 3) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.UP, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2+this.changeLaneCounter, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
						} else {
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.UP, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2 + GraphicsConfig.DISTANCE_BETWEEN_LANES, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 2;
						}
					} else if (this.laneChoise == 4) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.UP, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2 + GraphicsConfig.DISTANCE_BETWEEN_LANES - this.changeLaneCounter, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
						} else {
							g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.UP, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 1;
						}
					} else {
						g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.UP, ib),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
						
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
			     g2d.drawImage(ImagesSelector.carSelector(carType, driverType, AgentConfig.UP, ib),pathP.getX() - GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null);
			    
			     g2d.setTransform(origXform);
			 
			}
			
			
			
		}
		
		/**
		 * Gets the distance to object.
		 *
		 * @param x the x
		 * @param y the y
		 * @return the distance to object
		 */
		public int getDistanceToObject(int x, int y){
			int distance = (int)Math.sqrt( Math.pow((double)(this.carX - x), 2) + Math.pow((double)(this.carY - y), 2));
			return distance;
		}
		
		/**
		 * Gets the car list index.
		 *
		 * @param list the list
		 * @param car the car
		 * @return the car list index
		 */
		public int getCarListIndex(ArrayList<StandartCar>list, StandartCar car){
			int index = 0;
			for (int i=0; i<list.size(); i++){
				if(car.equals(list.get(i))){
					index = i;
					break;
				}
			}
			
			return index;
		}
		
		
}
