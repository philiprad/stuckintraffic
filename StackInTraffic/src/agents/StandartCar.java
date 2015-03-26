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

public class StandartCar {
	
		
		/** The car type. */
		private short driverType;
		
		private short carType;
		
		/** The co emission. */
		private double coEmission = 0;
		
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
		
		private int securityDistance = 0;
		
		private int securityZeroDistance = 10;
		
		private ArrayList <TrafficLight> trafficLights;
		
		private int directionAfterTrafficLight = 0;
		
		private boolean isArriving = false;
		
		private short carDirectionNextBlock = 0;
		
		private short carDirection2NextRoadBlock = 0;
		
		private int nextRoadBlockX = -1;
		
		private int nextRoadBlockY = -1;
		
		private RoadBlock currentRoadBlock;
		private RoadBlock nextRoadBlock ;
		
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
			
			if(this.driverType == AgentConfig.FAMILY_DRIVER){
				
					this.acceleration = AgentConfig.FAMILY_DRIVER_ACCELERATION;
			
			} else if (this.driverType == AgentConfig.FAST_DRIVER){
				
					this.acceleration = AgentConfig.FAST_DRIVER_ACCELERATION;
			} else {
				this.acceleration = AgentConfig.NORMAL_DRIVER_ACCELERATION;
			}
			
			
		}
		
		public void setIsArriving(boolean n){
			this.isArriving = n;
		}
		
		private int getSpeed(){
			return this.speed;
		}
		
		public void updateSecurityDistance(){
			if (this.speed == 0){
				this.securityDistance = this.securityZeroDistance;
			} else {
				this.securityDistance = this.speed*3 + GraphicsConfig.CAR_LENGTH;
			}
		}
		public void updateLaneChoise(){
			if (this.getPathRoadBlockType()==RoadConfig.INTERSECTION_DOUBLE_BLOCK || this.getPathRoadBlockType()==RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK || this.getPathRoadBlockType()==RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
				if(this.getPathDirection()>100 || this.getPathDirection()<-100){
					this.laneChoise = 2;
				} else {
					this.laneChoise = 1;
				}
			}
		}
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
		
		public void driverSpeedUpdate(){
			if(this.driverType == AgentConfig.FAMILY_DRIVER){
				if(this.speed>3){
					this.speed=this.speed*80/100;
				}
			} else if (this.driverType == AgentConfig.FAST_DRIVER){
				if(this.speed>3){
					//this.speed=this.speed*120/100;
				}
			}
		}
		
		
		
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
		
		public void acceleration(){
			if (((RoadBlock)this.roadBlock[this.carX/GraphicsConfig.BLOCK_SIDE_SIZE][this.carY/GraphicsConfig.BLOCK_SIDE_SIZE]).getSpeedLimit()>this.speed){
				this.accelerationCounter+= this.acceleration;
				if (accelerationCounter>1){
				
					this.speed++;
					this.accelerationCounter-=1;
				}
			}
		}
		
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
		
		public int getExit(){
			return this.path.getPathPoints().get(this.counter).getRoundAboutExit();
		}
		
		public int getCenterX(){
			return this.path.getPathPoints().get(this.counter).getRoundAboutCenterX();
		}
		
		public int getCenterY(){
			return this.path.getPathPoints().get(this.counter).getRoundAboutCenterY();
		}
		
		
		public int getCarXAfter(int n){
			
			if (this.counter + n > this.path.getPathPoints().size()){
				return -1;
			} else {
				return this.path.getPathPoints().get(this.counter+n).getX();
			}
			
			
		}
		
		
		
		
		public int getCarYAfter(int n){
			
			if (this.counter + n > this.path.getPathPoints().size()){
				return -1;
			} else {
				return this.path.getPathPoints().get(this.counter+n).getY();
			}
			
			
		}
		
		
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
		
		public void updateScanningBlocs(){
			currentRoadBlock = (RoadBlock)this.roadBlock[this.carX/GraphicsConfig.BLOCK_SIDE_SIZE][this.carY/GraphicsConfig.BLOCK_SIDE_SIZE];
			nextRoadBlock = this.nextRoadBlock();
		}
		
		
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
						} else {
							if(!this.equals(car) && distanceToNextBlock>car.getDistanceToObject(this.nextRoadBlockX, this.nextRoadBlockY)){
								if((this.getExit() == 1 || this.getExit() == 2) && (car.getExit() == 1 || car.getExit() == 2)){
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
								} else if((this.getExit() == 3 || this.getExit() == 4) && (car.getExit() == 3 || car.getExit() == 4)){
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
						}
					}
				}
			}  
			
			if(nextRoadBlock != null && !trafficLightOnPath ){
				 
				if (nextRoadBlock.isCarInside()){
					
					 carList = nextRoadBlock.getCarList();
					if(carList.size()>0){
						
						int minDistance = 10000;
						for(StandartCar car: carList){ //Except the roand about it could be only 2 cars with the same direction and the same lane

								if (!this.equals(car)){
									if (nextRoadBlock.getBlockType() != RoadConfig.ROUND_ABOUT_BLOCK){
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
								}
								} else {
									
										if((this.getExit() == 1 || this.getExit() == 2) && (car.getExit() == 1 || car.getExit() == 2)){
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
										} else if((this.getExit() == 3 || this.getExit() == 4) && (car.getExit() == 3 || car.getExit() == 4)){
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
							this.go(currentRoadBlock, nextRoadBlock);
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
			
		}
		
		
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
		
		public void stop(){
			this.speed = 0;
		}
		
		public int getBlockSpeedLimitForDriver(int speedLimit){
			int sLimit = speedLimit;
			if(this.driverType == AgentConfig.FAMILY_DRIVER){
				
					sLimit=speedLimit*80/100;
					
				
			} else if (this.driverType == AgentConfig.FAST_DRIVER){
				
					sLimit=speedLimit*110/100;
					
			}

			return sLimit;
		}
		
		
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
		
		public int getDistanceToObject(int x, int y){
			int distance = (int)Math.sqrt( Math.pow((double)(this.carX - x), 2) + Math.pow((double)(this.carY - y), 2));
			return distance;
		}
		
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
