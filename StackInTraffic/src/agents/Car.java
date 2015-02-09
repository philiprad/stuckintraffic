package agents;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import trafficInfrastructure.road.RoadConfig;
import trafficInfrastructure.roadPath.Path;
import trafficInfrastructure.roadPath.PathPoint;

public class Car {
	
	private Path path;
	private int counter;
	private int speed = 10;
	private short pathEnd = -1;
	
	public Car (Path path){
		this.path = path;
		this.counter = 0;
	}
	
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

			System.out.println("Rotete");
		/*	g2d.rotate(Math.toRadians(Math.toRadians(45)), pathP.getX(), pathP.getY());
			g2d.drawImage(ib.getCarRight(),pathP.getX()-GraphicsConfig.CAR_LENGTH/2, pathP.getY() - GraphicsConfig.CAR_WIDTH/2, GraphicsConfig.CAR_LENGTH, GraphicsConfig.CAR_WIDTH, null);
			g2d.rotate(-Math.toRadians(Math.toRadians(45)), pathP.getX(), pathP.getY());*/
			
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
	
	public void move(){
		if (this.path.getPathPoints().size()-speed>this.counter){		
			this.counter+= speed;
		} else {
			this.pathEnd = 1;
		}
	}
	
	public boolean ifPathEnd(){
		if (this.pathEnd == 1) return true;
		else return false;
	}
	
	
}
