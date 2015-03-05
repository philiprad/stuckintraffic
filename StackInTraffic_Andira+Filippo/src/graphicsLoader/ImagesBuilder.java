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
	
	/** The verical double road. */
	private Image vericalDoubleRoad;
	
	/** The horizontal road. */
	private Image horizontalDoubleRoad;
	
	/** The intersection. */
	private Image intersection;
	
	private Image vericalRoadSc;
	
	/** The horizontal road. */
	private Image horizontalRoadSc;
	
	/** The verical double road. */
	private Image vericalDoubleRoadSc;
	
	/** The horizontal road. */
	private Image horizontalDoubleRoadSc;
	
	/** The intersection. */
	private Image intersectionSc;
	
	/** The verical double road. */
	private Image vericalDoubleRoadTb;
	
	/** The horizontal road. */
	private Image horizontalDoubleRoadTb;
	
	private Image vericalRoadCr;
	
	/** The horizontal road. */
	private Image horizontalRoadCr;
	
	/** The verical double road. */
	private Image vericalDoubleRoadCr;
	
	/** The horizontal road. */
	private Image horizontalDoubleRoadCr;
	
	/** The intersection. */
	private Image intersectionCr;
	
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
		this.vericalDoubleRoad = loadImage(GraphicsConfig.VERTICAL_DOUBLE_ROAD_IMG);
		this.horizontalDoubleRoad = loadImage(GraphicsConfig.HORIZONTAL_DOUBLE_ROAD_IMG);
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
		
		this.vericalRoadSc = loadImage(GraphicsConfig.VERTICAL_ROAD_IMG_SCALED);
		this.horizontalRoadSc = loadImage(GraphicsConfig.HORIZONTAL_ROAD_IMG_SCALED);
		this.vericalDoubleRoadSc = loadImage(GraphicsConfig.VERTICAL_DOUBLE_ROAD_IMG_SCALED);
		this.horizontalDoubleRoadSc = loadImage(GraphicsConfig.HORIZONTAL_DOUBLE_ROAD_IMG_SCALED);
		this.intersectionSc = loadImage(GraphicsConfig.INTERSECTION_IMG_SCALED);
		
		this.vericalRoadCr = loadImage(GraphicsConfig.VERTICAL_ROAD_IMG_CURSOR);
		this.horizontalRoadCr = loadImage(GraphicsConfig.HORIZONTAL_ROAD_IMG_CURSOR);
		this.vericalDoubleRoadCr = loadImage(GraphicsConfig.VERTICAL_DOUBLE_ROAD_IMG_CURSOR);
		this.horizontalDoubleRoadCr = loadImage(GraphicsConfig.HORIZONTAL_DOUBLE_ROAD_IMG_CURSOR);
		this.intersectionCr = loadImage(GraphicsConfig.INTERSECTION_IMG_CURSOR);
		
		this.horizontalDoubleRoadTb = loadImage(GraphicsConfig.HORIZONTAL_DOUBLE_ROAD_IMG_TOOLBAR);
		this.vericalDoubleRoadTb = loadImage(GraphicsConfig.VERTICAL_DOUBLE_ROAD_IMG_TOOLBAR);
		
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
	 * Gets the horizontal double rd.
	 *
	 * @return the horizontal double rd
	 */
	public Image getHorizontalDoubleRd(){
		return this.horizontalDoubleRoad;
	}
	
	/**
	 * Gets the vertical rd.
	 *
	 * @return the vertical rd
	 */
	public Image getVerticalDoubleRd(){
		return this.vericalDoubleRoad;
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
	
	
	public Image getHorizontalRdSc(){
		return this.horizontalRoadSc;
	}
	
	/**
	 * Gets the vertical rd.
	 *
	 * @return the vertical rd
	 */
	public Image getVerticalRdSc(){
		return this.vericalRoadSc;
	}
	
	/**
	 * Gets the horizontal double rd.
	 *
	 * @return the horizontal double rd
	 */
	public Image getHorizontalDoubleRdSc(){
		return this.horizontalDoubleRoadSc;
	}
	
	/**
	 * Gets the vertical rd.
	 *
	 * @return the vertical rd
	 */
	public Image getVerticalDoubleRdSc(){
		return this.vericalDoubleRoadSc;
	}
	
	/**
	 * Gets the intersection.
	 *
	 * @return the intersection
	 */
	public Image getIntersectionSc(){
		return this.intersectionSc;
	}
	
	
	public Image getHorizontalDoubleRdTb(){
		return this.horizontalDoubleRoadTb;
	}
	
	/**
	 * Gets the vertical rd.
	 *
	 * @return the vertical rd
	 */
	public Image getVerticalDoubleRdTb(){
		return this.vericalDoubleRoadTb;
	}
	
	public Image getHorizontalRdCr(){
		return this.horizontalRoadCr;
	}
	
	/**
	 * Gets the vertical rd.
	 *
	 * @return the vertical rd
	 */
	public Image getVerticalRdCr(){
		return this.vericalRoadCr;
	}
	
	/**
	 * Gets the horizontal double rd.
	 *
	 * @return the horizontal double rd
	 */
	public Image getHorizontalDoubleRdCr(){
		return this.horizontalDoubleRoadCr;
	}
	
	/**
	 * Gets the vertical rd.
	 *
	 * @return the vertical rd
	 */
	public Image getVerticalDoubleRdCr(){
		return this.vericalDoubleRoadCr;
	}
	
	/**
	 * Gets the intersection.
	 *
	 * @return the intersection
	 */
	public Image getIntersectionCr(){
		return this.intersectionCr;
	}
}

