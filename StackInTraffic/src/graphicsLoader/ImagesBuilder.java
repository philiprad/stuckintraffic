package graphicsLoader;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImagesBuilder {
	
	private Image vericalRoad;
	private Image horizontalRoad;
	private Image intersection;
	private Image carUp;
	private Image carDown;
	private Image carLeft;
	private Image carRight;
	
	public ImagesBuilder(){
		this.vericalRoad = loadImage(GraphicsConfig.VERTICAL_ROAD_IMG);
		this.horizontalRoad = loadImage(GraphicsConfig.HORIZONTAL_ROAD_IMG);
		this.intersection = loadImage(GraphicsConfig.INTERSECTION_IMG);
		this.carDown = loadImage(GraphicsConfig.CAR_DOWN_IMG);
		this.carLeft= loadImage(GraphicsConfig.CAR_LEFT_IMG);
		this.carRight = loadImage(GraphicsConfig.CAR_RIGHT_IMG);
		this.carUp = loadImage(GraphicsConfig.CAR_UP_IMG);
	}
	
	public static Image loadImage(String path){
		Image image = null;
		try{
			InputStream is = new BufferedInputStream(new FileInputStream(path));
		    image = ImageIO.read(is);
		}
		catch(Exception e){
			System.err.println("Error occured while picture reading");
			e.printStackTrace();
		}
		
		return image;
	}

	public Image getHorizontalRd(){
		return this.horizontalRoad;
	}
	
	public Image getVerticalRd(){
		return this.vericalRoad;
	}
	
	public Image getIntersection(){
		return this.intersection;
	}
	
	public Image getCarUp(){
		return this.carUp;
	}

	public Image getCarDown(){
		return this.carDown;
		
	}
	
	public Image getCarRight(){
		return this.carRight;
		
	}
	
	public Image getCarLeft(){
		return this.carLeft;
	}
	
}

