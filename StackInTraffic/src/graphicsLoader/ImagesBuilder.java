/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

// TODO: Auto-generated Javadoc
/**
 * The Class ImagesBuilder.
 */
public class ImagesBuilder {
	
	/** The verical road. */
	private Image vericalRoad;
	
	/** The horizontal road. */
	private Image horizontalRoad;
	
	/** The intersection. */
	private Image intersection;
	
	/** The car up. */
	private Image carUp;
	
	/** The car down. */
	private Image carDown;
	
	/** The car left. */
	private Image carLeft;
	
	/** The car right. */
	private Image carRight;
	
	/** The vertical red light. */
	private Image verticalRedLight;
	
	/** The horizontal red light. */
	private Image horizontalRedLight;
	
	/** The vertical green light. */
	private Image verticalGreenLight;
	
	/** The horizontal green light. */
	private Image horizontalGreenLight;
	
	/** The vertical yellow light. */
	private Image verticalYellowLight;
	
	/** The horizontal yellow light. */
	private Image horizontalYellowLight;
	
	/**
	 * Instantiates a new images builder.
	 */
	public ImagesBuilder(){
		this.vericalRoad = loadImage(GraphicsConfig.VERTICAL_ROAD_IMG);
		this.horizontalRoad = loadImage(GraphicsConfig.HORIZONTAL_ROAD_IMG);
		this.intersection = loadImage(GraphicsConfig.INTERSECTION_IMG);
		this.carDown = loadImage(GraphicsConfig.CAR_DOWN_IMG);
		this.carLeft= loadImage(GraphicsConfig.CAR_LEFT_IMG);
		this.carRight = loadImage(GraphicsConfig.CAR_RIGHT_IMG);
		this.carUp = loadImage(GraphicsConfig.CAR_UP_IMG);
		this.horizontalGreenLight = loadImage(GraphicsConfig.HORIZONTAL_GREEN_LIGHT_IMG);
		this.verticalGreenLight = loadImage(GraphicsConfig.VERTICAL_GREEN_LIGHT_IMG);
		this.horizontalRedLight = loadImage(GraphicsConfig.HORIZONTAL_RED_LIGHT_IMG);
		this.verticalRedLight = loadImage(GraphicsConfig.VERTICAL_RED_LIGHT_IMG);
		this.horizontalYellowLight = loadImage(GraphicsConfig.HORIZONTAL_YELLOW_LIGHT_IMG);
		this.verticalYellowLight = loadImage(GraphicsConfig.VERTICAL_YELLOW_LIGHT_IMG);
	}
	
	/**
	 * Load image.
	 *
	 * @param path the path
	 * @return the image
	 */
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

	/**
	 * Gets the horizontal rd.
	 *
	 * @return the horizontal rd
	 */
	public Image getHorizontalRd(){
		return this.horizontalRoad;
	}
	
	/**
	 * Gets the vertical rd.
	 *
	 * @return the vertical rd
	 */
	public Image getVerticalRd(){
		return this.vericalRoad;
	}
	
	/**
	 * Gets the intersection.
	 *
	 * @return the intersection
	 */
	public Image getIntersection(){
		return this.intersection;
	}
	
	/**
	 * Gets the car up.
	 *
	 * @return the car up
	 */
	public Image getCarUp(){
		return this.carUp;
	}

	/**
	 * Gets the car down.
	 *
	 * @return the car down
	 */
	public Image getCarDown(){
		return this.carDown;
		
	}
	
	/**
	 * Gets the car right.
	 *
	 * @return the car right
	 */
	public Image getCarRight(){
		return this.carRight;
		
	}
	
	/**
	 * Gets the car left.
	 *
	 * @return the car left
	 */
	public Image getCarLeft(){
		return this.carLeft;
	}
	
	/**
	 * Gets the green light h.
	 *
	 * @return the green light h
	 */
	public Image getGreenLightH(){
		return this.horizontalGreenLight;
	}
	
	/**
	 * Gets the green light v.
	 *
	 * @return the green light v
	 */
	public Image getGreenLightV(){
		return this.verticalGreenLight;
	}
	
	/**
	 * Gets the red light h.
	 *
	 * @return the red light h
	 */
	public Image getRedLightH(){
		return this.horizontalRedLight;
	}
	
	/**
	 * Gets the red light v.
	 *
	 * @return the red light v
	 */
	public Image getRedLightV(){
		return this.verticalRedLight;
	}
	
	/**
	 * Gets the yellow light h.
	 *
	 * @return the yellow light h
	 */
	public Image getYellowLightH(){
		return this.horizontalYellowLight;
	}
	
	/**
	 * Gets the yellow light v.
	 *
	 * @return the yellow light v
	 */
	public Image getYellowLightV(){
		return this.verticalYellowLight;
	}
	
}

