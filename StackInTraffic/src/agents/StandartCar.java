package agents;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

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
		
		private int securityDistance = 0;
		
		private int securityZeroDistance = 5+GraphicsConfig.CAR_LENGTH;
		
		private ArrayList <TrafficLight> trafficLights;
		
		public StandartCar(Path path, short driverType, Object [] [] roadBlock,int laneChoise,  ArrayList <TrafficLight> trafficLights){
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
				this.acceleration = AgentConfig.FAST_DRIVER_ACCELERATION;
			}
			
			
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
				this.updateSpeed();
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
		
		public int getCarXAfter(int n){
			
			if (this.counter + n > this.path.getPathPoints().size()){
				return -1;
			}
			else {
				int x = 0;
				if (this.laneChoise == 1){
					x = this.path.getPathPoints().get(this.counter+n).getX();
				} else if (this.laneChoise == 2){
				
					x = this.path.getPathPoints().get(this.counter+n).getX();
					
					if(this.getPathRoadBlockType()== RoadConfig.VERTICAL_DOUBLE_BLOCK) {
						if (this.getPathDirection() == 1){
							x-=GraphicsConfig.DISTANCE_BETWEEN_LANES;
						}
						else {
							x+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
						}
					}
				} else if (this.laneChoise == 3){ //NOT Precise, Need to create new algorithm
					
					x = this.path.getPathPoints().get(this.counter+n).getX();
					 if(this.getPathRoadBlockType()== RoadConfig.VERTICAL_DOUBLE_BLOCK) {
						 if (this.getPathDirection() == 1){ 
							 if(this.speed/2*10>GraphicsConfig.DISTANCE_BETWEEN_LANES){
								 x-=GraphicsConfig.DISTANCE_BETWEEN_LANES;
							 } else {
								 x-=this.speed/2*10;
							 }
						 } else {
						
							 if(this.speed/2*10>GraphicsConfig.DISTANCE_BETWEEN_LANES){
								 x+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
							 } else {
								 x-=this.speed/2*10;
							 }
						 }
					}
				
			} else if (this.laneChoise == 4){
				x = this.path.getPathPoints().get(this.counter+n).getX();
				 if(this.getPathRoadBlockType()== RoadConfig.VERTICAL_DOUBLE_BLOCK) {
					 if (this.getPathDirection() == 1){ 
						 if(this.speed/2*10>GraphicsConfig.DISTANCE_BETWEEN_LANES){
							 x+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
						 } else {
							 x+=this.speed/2*10;
						 }
					 } else {
					
						 if(this.speed/2*10>GraphicsConfig.DISTANCE_BETWEEN_LANES){
							 x-=GraphicsConfig.DISTANCE_BETWEEN_LANES;
						 } else {
							 x+=this.speed/2*10;
						 }
					 }
				}
			}
				
				return x;
				
			}
			
		}
		
		
		
		
		public int getCarYAfter(int n){
			
			if (this.counter + n > this.path.getPathPoints().size()){
				return -1;
			}
			else {
				int y = 0;
				if (this.laneChoise == 1){
					y = this.path.getPathPoints().get(this.counter+n).getY();
				} else if (this.laneChoise == 2){
				
					y = this.path.getPathPoints().get(this.counter+n).getY();
					
					if(this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_DOUBLE_BLOCK) {
						if (this.getPathDirection() == 1){
							y+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
						}
						else {
							y-=GraphicsConfig.DISTANCE_BETWEEN_LANES;
						}
					}
				} else if (this.laneChoise == 3){ //NOT Precise, Need to create new algorithm
					
					y = this.path.getPathPoints().get(this.counter+n).getY();
					 if(this.getPathRoadBlockType()== RoadConfig.HORIZONTAL_DOUBLE_BLOCK) {
						 if (this.getPathDirection() == 1){ 
							 if(this.speed/2*10>GraphicsConfig.DISTANCE_BETWEEN_LANES){
								 y+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
							 } else {
								 y+=this.speed/2*10;
							 }
						 } else {
						
							 if(this.speed/2*10>GraphicsConfig.DISTANCE_BETWEEN_LANES){
								 y-=GraphicsConfig.DISTANCE_BETWEEN_LANES;
							 } else {
								 y+=this.speed/2*10;
							 }
						 }
					}
				
			} else if (this.laneChoise == 4){
				y = this.path.getPathPoints().get(this.counter+n).getY();
				 if(this.getPathRoadBlockType()== RoadConfig.VERTICAL_DOUBLE_BLOCK) {
					 if (this.getPathDirection() == 1){ 
						 if(this.speed/2*10>GraphicsConfig.DISTANCE_BETWEEN_LANES){
							 y-=GraphicsConfig.DISTANCE_BETWEEN_LANES;
						 } else {
							 y-=this.speed/2*10;
						 }
					 } else {
					
						 if(this.speed/2*10>GraphicsConfig.DISTANCE_BETWEEN_LANES){
							 y+=GraphicsConfig.DISTANCE_BETWEEN_LANES;
						 } else {
							 y+=this.speed/2*10;
						 }
					 }
				}
			}
				
				return y;
			}
			
		}
		
		public RoadBlock nextRoadBlock(){
			RoadBlock roadBlock = null;
			for(int i = 10 ; i<=50 ;i+=10){
				if(counter+i<this.path.getPathPoints().size()){
					
					if(this.roadBlock[this.carX/GraphicsConfig.BLOCK_SIDE_SIZE][this.carY/GraphicsConfig.BLOCK_SIDE_SIZE] != this.roadBlock[this.getCarXAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarYAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE]){
						
						roadBlock = (RoadBlock)this.roadBlock[this.getCarXAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE][this.getCarYAfter(i)/GraphicsConfig.BLOCK_SIDE_SIZE];
						break;
					}
				} else {
					 
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
								
								return 4;
							} else if (this.path.getPathPoints().get(counter+i).getDirection()>0 && this.laneChoise == 1 ){
								
								return 3;
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
		
		/*public void updateSpeed(){
			RoadBlock currentRoadBlock = (RoadBlock)this.roadBlock[this.carX/GraphicsConfig.BLOCK_SIDE_SIZE][this.carY/GraphicsConfig.BLOCK_SIDE_SIZE];
			RoadBlock nextRoadBlock = this.nextRoadBlock();
			int distanceToNextObject = -1;
			int nextObjectSpeed = 0;
			int decelerationDistance = 0;
			int speedLimitBlock = 0;
			boolean trafficLightOnPath = false;

			short trafficLightIndex = -1;
			
			if (currentRoadBlock.isTrafficLightInside()){
				
				
				ArrayList<Short> trafficLightIndexList = currentRoadBlock.getTrafficLightIndexList();
				
				for(Short index : trafficLightIndexList){
					
					if(trafficLights.get(index).getDirection()==this.getPathDirection()){
						short type = trafficLights.get(index).getType();
						if((type == 2 && this.laneChoise == 2) || (type == 2 && this.laneChoise == 3) || (type == 1 && this.laneChoise == 1) || (type == 1 && this.laneChoise == 4)){
							System.out.println("TrafficLight");
							trafficLightOnPath = true;

							trafficLightIndex = index;
							
						}

					}
				}
			}
			
				ArrayList <StandartCar> carList = currentRoadBlock.getCarList();

				

				if(carList.size()>1){
					int minDistance = 10000;
					int index = this.getCarListIndex(carList ,this);
					for(StandartCar car: carList){ //Except the roand about it could be only 2 cars with the same direction and the same lane
							
							if (!this.equals(car) && index>this.getCarListIndex(carList,car)){
								
								if(this.getPathDirection()==car.getPathDirection() && (this.getLaneChoice() == car.getLaneChoice() || car.getLaneChoice() == 3 || car.getLaneChoice() == 4) ){
									int distance = this.getDistanceToObject(car.getCarX(), car.getCarY());
									if (distance<minDistance){
										nextObjectSpeed = car.getSpeed();
										minDistance = distance-GraphicsConfig.CAR_LENGTH;
										distanceToNextObject = minDistance;
										this.updateSecurityDistance();
										decelerationDistance = minDistance - nextObjectSpeed*3;
										//System.out.println("there is a car in the next block");
									}
								}
							}
					
					}
				} 
				
			 else if(nextRoadBlock != null && !trafficLightOnPath ){
				 
				if (nextRoadBlock.isCarInside()){
					
					 carList = nextRoadBlock.getCarList();
					if(carList.size()>0){
						
						int minDistance = 10000;
						for(StandartCar car: carList){ //Except the roand about it could be only 2 cars with the same direction and the same lane

								if (!this.equals(car)){
									
									if(this.getPathDirection()==car.getPathDirection() && (this.getLaneChoice() == car.getLaneChoice() || car.getLaneChoice() == 3 || car.getLaneChoice() == 4) ){
										int distance = this.getDistanceToObject(car.getCarX(), car.getCarY());
										
										if (distance<minDistance){
											
											nextObjectSpeed = car.getSpeed();
											minDistance = distance - GraphicsConfig.CAR_LENGTH;
											distanceToNextObject = minDistance;
											this.updateSecurityDistance();
											decelerationDistance = minDistance - nextObjectSpeed*3;
											
										}
									}
								}
							}
						}
					} 
				}  

			
			if (trafficLightOnPath == true){

				if (trafficLights.get(trafficLightIndex).getState()!=AgentConfig.TRAFFIC_LIGHT_GREEN){

					if (trafficLights.get(trafficLightIndex).getDistanceToTrafficLight(this.getCarX(), this.getCarY())<GraphicsConfig.BLOCK_SIDE_SIZE/5){

						this.speed=0;

					} else {

						this.deceleration(trafficLights.get(trafficLightIndex).getDistanceToTrafficLight(this.getCarX(), this.getCarY()), 1);
					}
				}
			}
			
			if (distanceToNextObject != -1){
				
				if(nextObjectSpeed == 0){
					if (distanceToNextObject<=this.securityZeroDistance){
						this.speed = 0;
					} else {
						this.deceleration(this.securityZeroDistance, nextObjectSpeed);
					}
				}
					
				 else {
					
					if(distanceToNextObject<(nextObjectSpeed)*3){
						this.speed = nextObjectSpeed;
					} else {
						if(this.speed > nextObjectSpeed){
							this.accelerationCounter = 0;
							this.deceleration(decelerationDistance, nextObjectSpeed);
						} else {
							this.accelerationCounter = 0;
							this.decelerationCounter = 0;
						}
					}
				 }	
				
				
			
		}
				
			
			this.updateSecurityDistance();
			
			
		}*/
		
		public void updateSpeed(){
			RoadBlock currentRoadBlock = (RoadBlock)this.roadBlock[this.carX/GraphicsConfig.BLOCK_SIDE_SIZE][this.carY/GraphicsConfig.BLOCK_SIDE_SIZE];
			//RoadBlock nextRoadBlock = this.nextRoadBlock();
			boolean trafficLightOnPath = false;
			short trafficLightIndex = -1;
			
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
			
			
			if (trafficLightOnPath == true){

				if (trafficLights.get(trafficLightIndex).getState()!=AgentConfig.TRAFFIC_LIGHT_GREEN){
					System.out.println("NotGreen");
					if (trafficLights.get(trafficLightIndex).getDistanceToTrafficLight(this.getCarX(), this.getCarY())<GraphicsConfig.BLOCK_SIDE_SIZE/5){
						this.accelerationCounter =0;
						this.decelerationCounter = 0;
						this.speed=0;
						System.out.println("stop");

					} else {
						this.accelerationCounter =0;
						this.deceleration(trafficLights.get(trafficLightIndex).getDistanceToTrafficLight(this.getCarX(), this.getCarY()), 1);
						System.out.println("deceleration");
					}
				} else {
					System.out.println("Green Go");
					//this.go(currentRoadBlock);
					this.acceleration();
				}
				
			}
			System.out.println("Nothing");
		}
		
		public void go(RoadBlock currentRoadBlock){
			if(this.speed<this.getBlockSpeedLimitForDriver(currentRoadBlock.getSpeedLimit())){
				this.acceleration();
				System.out.println("acceleration");
			}
			else {
				this.accelerationCounter =0;
			}
		}
		
		public void stop(){
			this.speed = 0;
		}
		
		public int getBlockSpeedLimitForDriver(int speedLimit){
			if(this.driverType == AgentConfig.FAMILY_DRIVER){
				
					speedLimit=this.speed*80/100;
				
			} else if (this.driverType == AgentConfig.FAST_DRIVER){
				
					//speedLimit=this.speed*120/100;
			}

			return speedLimit;
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
					} else if (this.laneChoise == 4) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ib.getCarRight(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2+GraphicsConfig.DISTANCE_BETWEEN_LANES-this.changeLaneCounter, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
						} else {
							g2d.drawImage(ib.getCarRight(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 1;
						}
					}
						else {
						g2d.drawImage(ib.getCarRight(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
					}
				}
				else{
					
					if(this.laneChoise == 2){
						g2d.drawImage(ib.getCarLeft(),pathP.getX() - GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2 - GraphicsConfig.DISTANCE_BETWEEN_LANES, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
					} else if (this.laneChoise == 3) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ib.getCarLeft(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2-this.changeLaneCounter, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
						} else {
							g2d.drawImage(ib.getCarLeft(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2-GraphicsConfig.DISTANCE_BETWEEN_LANES, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 2;
						}
					} else if (this.laneChoise == 4) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ib.getCarLeft(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2-GraphicsConfig.DISTANCE_BETWEEN_LANES+this.changeLaneCounter, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
						} else {
							g2d.drawImage(ib.getCarLeft(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 1;
						}
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
					} else if (this.laneChoise == 3) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ib.getCarDown(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2-this.changeLaneCounter, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
						} else {
							g2d.drawImage(ib.getCarDown(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2 - GraphicsConfig.DISTANCE_BETWEEN_LANES, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 2;
						}
					} else if (this.laneChoise == 4) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ib.getCarDown(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2 - GraphicsConfig.DISTANCE_BETWEEN_LANES + this.changeLaneCounter, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
						} else {
							g2d.drawImage(ib.getCarDown(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 1;
						}
					} else {
						g2d.drawImage(ib.getCarDown(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
					}
				}
				else {
					if(this.laneChoise == 2){
						g2d.drawImage(ib.getCarUp(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2+ GraphicsConfig.DISTANCE_BETWEEN_LANES, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
					} else if (this.laneChoise == 3) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ib.getCarUp(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2+this.changeLaneCounter, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
						} else {
							g2d.drawImage(ib.getCarUp(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2 + GraphicsConfig.DISTANCE_BETWEEN_LANES, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 2;
						}
					} else if (this.laneChoise == 4) {
						this.changeLaneCounter+=this.speed/2;
						if(this.changeLaneCounter<GraphicsConfig.DISTANCE_BETWEEN_LANES){
							g2d.drawImage(ib.getCarUp(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2 + GraphicsConfig.DISTANCE_BETWEEN_LANES - this.changeLaneCounter, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
						} else {
							g2d.drawImage(ib.getCarUp(),pathP.getX()-GraphicsConfig.CAR_WIDTH/2, pathP.getY() - GraphicsConfig.CAR_LENGTH/2, GraphicsConfig.CAR_WIDTH, GraphicsConfig.CAR_LENGTH, null );
							this.changeLaneCounter = 0;
							this.laneChoise = 1;
						}
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
