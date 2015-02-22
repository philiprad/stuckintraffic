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
	
	/** The path. */
	private Path path;
	
	/** The counter. */
	private int counter;
	
	/** The speed. */
	private int speed = 10;
	
	/** The path end. */
	private short pathEnd = -1;
	
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
	
	/**
	 * Gets the car y.
	 *
	 * @return the car y
	 */
	public int getCarY(){
		return this.path.getPathPoints().get(this.counter).getY();
	}
	
	/**
	 * Gets the direction.
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
		
		float sp = (float)speed/(float)dist;
		this.speed += speed/dist;
		if(sp-(float)(speed/dist)>0.5){
			this.speed--;
		}
	}
	
	/**
	 * Stop.
	 */
	public void stop(){
		this.speed = 0;
	}
	
	
}
