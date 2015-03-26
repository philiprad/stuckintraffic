/*
 * @author  Maxim Vasilishin
 * @version 4.0
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
	
	/** The intersection double. */
	private Image intersectionDouble;
	
	/** The round about. */
	private Image roundAbout;
	
	/** The intersection up. */
	private Image intersectionUp;
	
	/** The intersection down. */
	private Image intersectionDown;
	
	/** The intersection right. */
	private Image intersectionRight;
	
	/** The intersection left. */
	private Image intersectionLeft;
	
	/** The mixed intersection horizontal. */
	private Image mixedIntersectionHorizontal;
	
	/** The mixed intersection vertical. */
	private Image mixedIntersectionVertical;
	
	/** The verical road sc. */
	private Image vericalRoadSc;
	
	/** The horizontal road. */
	private Image horizontalRoadSc;
	
	/** The verical double road. */
	private Image vericalDoubleRoadSc;
	
	/** The horizontal road. */
	private Image horizontalDoubleRoadSc;
	
	/** The intersection. */
	private Image intersectionSc;
	
	/** The intersection double sc. */
	private Image intersectionDoubleSc;
	
	/** The round about sc. */
	private Image roundAboutSc;
	
	/** The intersection up sc. */
	private Image intersectionUpSc;
	
	/** The intersection down sc. */
	private Image intersectionDownSc;
	
	/** The intersection right sc. */
	private Image intersectionRightSc;
	
	/** The intersection left sc. */
	private Image intersectionLeftSc;
	
	/** The mixed intersection horizontal sc. */
	private Image mixedIntersectionHorizontalSc;
	
	/** The mixed intersection vertical sc. */
	private Image mixedIntersectionVerticalSc;
	
	/** The verical double road. */
	private Image vericalDoubleRoadTb;
	
	/** The horizontal road. */
	private Image horizontalDoubleRoadTb;
	
	/** The intersection double tb. */
	private Image intersectionDoubleTb;
	
	/** The round about tb. */
	private Image roundAboutTb;
	
	/** The mixed intersection horizontal tb. */
	private Image mixedIntersectionHorizontalTb;
	
	/** The mixed intersection vertical tb. */
	private Image mixedIntersectionVerticalTb;
	
	/** The verical road cr. */
	private Image vericalRoadCr;
	
	/** The horizontal road. */
	private Image horizontalRoadCr;
	
	/** The verical double road. */
	private Image vericalDoubleRoadCr;
	
	/** The horizontal road. */
	private Image horizontalDoubleRoadCr;
	
	/** The intersection. */
	private Image intersectionCr;
	
	/** The intersection double cr. */
	private Image intersectionDoubleCr;
	
	/** The round about cr. */
	private Image roundAboutCr;
	
	/** The intersection up cr. */
	private Image intersectionUpCr;
	
	/** The intersection down cr. */
	private Image intersectionDownCr;
	
	/** The intersection right cr. */
	private Image intersectionRightCr;
	
	/** The intersection left cr. */
	private Image intersectionLeftCr;
	
	/** The mixed intersection horizontal cr. */
	private Image mixedIntersectionHorizontalCr;
	
	/** The mixed intersection vertical cr. */
	private Image mixedIntersectionVerticalCr;
	
	/** The play button. */
	private Image playButton;
	
	/** The stop button. */
	private Image stopButton;
	
	/** The pause button. */
	private Image pauseButton;
	
	/** The step button. */
	private Image stepButton;
	
	
	
	/** The car up. */
	public Image carUp;
	
	/** The car down. */
	public Image carDown;
	
	/** The car left. */
	public Image carLeft;
	
	/** The car right. */
	public Image carRight;
	
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
	
	/** The cursor standart. */
	private Image cursorStandart;
	
	/** The cursor hand. */
	private Image cursorHand;
	
	/** The cursor delete. */
	private Image cursorDelete;
	
	/** The car bh up. */
	public Image carBHUp;
	
	/** The car down. */
	public Image carBHDown;
	
	/** The car left. */
	public Image carBHLeft;
	
	/** The car right. */
	public Image carBHRight;
	
	/** The car be up. */
	public Image carBEUp;
	
	/** The car down. */
	public Image carBEDown;
	
	/** The car left. */
	public Image carBELeft;
	
	/** The car right. */
	public Image carBERight;
	
	/** The car bp up. */
	public Image carBPUp;
	
	/** The car down. */
	public Image carBPDown;
	
	/** The car left. */
	public Image carBPLeft;
	
	/** The car right. */
	public Image carBPRight;
	
	/** The car yh up. */
	public Image carYHUp;
	
	/** The car down. */
	public Image carYHDown;
	
	/** The car left. */
	public Image carYHLeft;
	
	/** The car right. */
	public Image carYHRight;
	
	/** The car ye up. */
	public Image carYEUp;
	
	/** The car down. */
	public Image carYEDown;
	
	/** The car left. */
	public Image carYELeft;
	
	/** The car right. */
	public Image carYERight;
	
	/** The car yp up. */
	public Image carYPUp;
	
	/** The car down. */
	public Image carYPDown;
	
	/** The car left. */
	public Image carYPLeft;
	
	/** The car right. */
	public Image carYPRight;
	
	/** The car rh up. */
	public Image carRHUp;
	
	/** The car down. */
	public Image carRHDown;
	
	/** The car left. */
	public Image carRHLeft;
	
	/** The car right. */
	public Image carRHRight;
	
	/** The car re up. */
	public Image carREUp;
	
	/** The car down. */
	public Image carREDown;
	
	/** The car left. */
	public Image carRELeft;
	
	/** The car right. */
	public Image carRERight;
	
	/** The car rp up. */
	public Image carRPUp;
	
	/** The car down. */
	public Image carRPDown;
	
	/** The car left. */
	public Image carRPLeft;
	
	/** The car right. */
	public Image carRPRight;
	
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
		
		this.cursorStandart = loadImage(GraphicsConfig.CURSOR_STANDART);
		this.cursorHand = loadImage(GraphicsConfig.CURSOR_HAND);
		this.cursorDelete = loadImage(GraphicsConfig.CURSOR_DELETE);
		this.intersectionDoubleCr = loadImage(GraphicsConfig.INTERSECTION_DOUBLE_IMG_CURSOR);
		this.roundAboutCr = loadImage(GraphicsConfig.ROUND_ABOUT_IMG_CURSOR);
		this.intersectionUpCr = loadImage(GraphicsConfig.INTERSECTION_UP_IMG_CURSOR);
		this.intersectionDownCr = loadImage(GraphicsConfig.INTERSECTION_DOWN_IMG_CURSOR);
		this.intersectionRightCr = loadImage(GraphicsConfig.INTERSECTION_RIGHT_IMG_CURSOR);
		this.intersectionLeftCr = loadImage(GraphicsConfig.INTERSECTION_LEFT_IMG_CURSOR);
		this.mixedIntersectionHorizontalCr = loadImage(GraphicsConfig.MIXED_INTERSECTION_HORIZONTAL_IMG_CURSOR);
		this.mixedIntersectionVerticalCr = loadImage(GraphicsConfig.MIXED_INTERSECTION_VERTICAL_IMG_CURSOR);
		
		this.intersectionDouble = loadImage(GraphicsConfig.INTERSECTION_DOUBLE_IMG);
		this.roundAbout = loadImage(GraphicsConfig.ROUND_ABOUT);
		this.intersectionUp = loadImage(GraphicsConfig.INTERSECTION_UP_IMG);
		this.intersectionDown = loadImage(GraphicsConfig.INTERSECTION_DOWN_IMG);
		this.intersectionRight = loadImage(GraphicsConfig.INTERSECTION_RIGHT_IMG);
		this.intersectionLeft = loadImage(GraphicsConfig.INTERSECTION_LEFT_IMG);
		this.mixedIntersectionHorizontal = loadImage(GraphicsConfig.MIXED_INTERSECTION_HORIZONTAL_IMG);
		this.mixedIntersectionVertical = loadImage(GraphicsConfig.MIXED_INTERSECTION_VERTICAL_IMG);
		
		this.intersectionDoubleSc = loadImage(GraphicsConfig.INTERSECTION_DOUBLE_IMG_SCALED);
		this.roundAboutSc = loadImage(GraphicsConfig.ROUND_ABOUT_SCALED);
		this.intersectionUpSc = loadImage(GraphicsConfig.INTERSECTION_UP_IMG_SCALED);
		this.intersectionDownSc = loadImage(GraphicsConfig.INTERSECTION_DOWN_IMG_SCALED);
		this.intersectionRightSc = loadImage(GraphicsConfig.INTERSECTION_RIGHT_IMG_SCALED);
		this.intersectionLeftSc = loadImage(GraphicsConfig.INTERSECTION_LEFT_IMG_SCALED);
		this.mixedIntersectionHorizontalSc = loadImage(GraphicsConfig.MIXED_INTERSECTION_HORIZONTAL_IMG_SCALED);
		this.mixedIntersectionVerticalSc = loadImage(GraphicsConfig.MIXED_INTERSECTION_VERTICAL_IMG_SCALED);
		
		this.intersectionDoubleTb = loadImage(GraphicsConfig.INTERSECTION_DOUBLE_IMG_TOOLBAR);
		this.roundAboutTb = loadImage(GraphicsConfig.ROUND_ABOUT_IMG_TOOLBAR);
		this.mixedIntersectionHorizontalTb = loadImage(GraphicsConfig.MIXED_INTERSECTION_HORIZONTAL_IMG_TOOLBAR);
		this.mixedIntersectionVerticalTb = loadImage(GraphicsConfig.MIXED_INTERSECTION_VERTICAL_IMG_TOOLBAR);
		
		this.pauseButton =  loadImage(GraphicsConfig.PAUSE_BUTTON);
		this.stepButton =  loadImage(GraphicsConfig.STEP_BUTTON);
		this.playButton =  loadImage(GraphicsConfig.PLAY_BUTTON);
		this.stopButton =  loadImage(GraphicsConfig.STOP_BUTTON);
		
		this.carBEUp = loadImage(GraphicsConfig.CAR_BLUEE_UP_IMG);
		this.carBEDown = loadImage(GraphicsConfig.CAR_BLUEE_DOWN_IMG);
		this.carBERight = loadImage(GraphicsConfig.CAR_BLUEE_RIGHT_IMG);
		this.carBELeft = loadImage(GraphicsConfig.CAR_BLUEE_LEFT_IMG);
		
		this.carBHUp = loadImage(GraphicsConfig.CAR_BLUEH_UP_IMG);
		this.carBHDown = loadImage(GraphicsConfig.CAR_BLUEH_DOWN_IMG);
		this.carBHRight = loadImage(GraphicsConfig.CAR_BLUEH_RIGHT_IMG);
		this.carBHLeft = loadImage(GraphicsConfig.CAR_BLUEH_LEFT_IMG);
		
		this.carBPUp = loadImage(GraphicsConfig.CAR_BLUEP_UP_IMG);
		this.carBPDown = loadImage(GraphicsConfig.CAR_BLUEP_DOWN_IMG);
		this.carBPRight = loadImage(GraphicsConfig.CAR_BLUEP_RIGHT_IMG);
		this.carBPLeft = loadImage(GraphicsConfig.CAR_BLUEP_LEFT_IMG);
		
		this.carREUp = loadImage(GraphicsConfig.CAR_REDE_UP_IMG);
		this.carREDown = loadImage(GraphicsConfig.CAR_REDE_DOWN_IMG);
		this.carRERight = loadImage(GraphicsConfig.CAR_REDE_RIGHT_IMG);
		this.carRELeft = loadImage(GraphicsConfig.CAR_REDE_LEFT_IMG);
		
		this.carRPUp = loadImage(GraphicsConfig.CAR_REDP_UP_IMG);
		this.carRPDown = loadImage(GraphicsConfig.CAR_REDP_DOWN_IMG);
		this.carRPRight = loadImage(GraphicsConfig.CAR_REDP_RIGHT_IMG);
		this.carRPLeft = loadImage(GraphicsConfig.CAR_REDP_LEFT_IMG);
		
		this.carRHUp = loadImage(GraphicsConfig.CAR_REDE_UP_IMG);
		this.carRHDown = loadImage(GraphicsConfig.CAR_REDE_DOWN_IMG);
		this.carRHRight = loadImage(GraphicsConfig.CAR_REDE_RIGHT_IMG);
		this.carRHLeft = loadImage(GraphicsConfig.CAR_REDE_LEFT_IMG);
		
		this.carYEUp = loadImage(GraphicsConfig.CAR_YELLOWE_UP_IMG);
		this.carYEDown = loadImage(GraphicsConfig.CAR_YELLOWE_DOWN_IMG);
		this.carYERight = loadImage(GraphicsConfig.CAR_YELLOWE_RIGHT_IMG);
		this.carYELeft = loadImage(GraphicsConfig.CAR_YELLOWE_LEFT_IMG);
		
		this.carYPUp = loadImage(GraphicsConfig.CAR_YELLOWP_UP_IMG);
		this.carYPDown = loadImage(GraphicsConfig.CAR_YELLOWP_DOWN_IMG);
		this.carYPRight = loadImage(GraphicsConfig.CAR_YELLOWP_RIGHT_IMG);
		this.carYPLeft = loadImage(GraphicsConfig.CAR_YELLOWP_LEFT_IMG);
		
		this.carYHUp = loadImage(GraphicsConfig.CAR_YELLOWH_UP_IMG);
		this.carYHDown = loadImage(GraphicsConfig.CAR_YELLOWH_DOWN_IMG);
		this.carYHRight = loadImage(GraphicsConfig.CAR_YELLOWH_RIGHT_IMG);
		this.carYHLeft = loadImage(GraphicsConfig.CAR_YELLOWH_LEFT_IMG);
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
	
	
	/**
	 * Gets the horizontal rd sc.
	 *
	 * @return the horizontal rd sc
	 */
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
	
	
	/**
	 * Gets the horizontal double rd tb.
	 *
	 * @return the horizontal double rd tb
	 */
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
	
	/**
	 * Gets the horizontal rd cr.
	 *
	 * @return the horizontal rd cr
	 */
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
	
	/**
	 * Gets the standart cursor.
	 *
	 * @return the standart cursor
	 */
	public Image getStandartCursor(){
		return this.cursorStandart;
	}
	
	/**
	 * Gets the hand cursor.
	 *
	 * @return the hand cursor
	 */
	public Image getHandCursor(){
		return this.cursorHand;
	}
	
	/**
	 * Gets the delete cursor.
	 *
	 * @return the delete cursor
	 */
	public Image getDeleteCursor(){
		return this.cursorDelete;
	}
	
	/**
	 * Gets the round about.
	 *
	 * @return the round about
	 */
	public Image getRoundAbout(){
		return this.roundAbout;
	}
	
	/**
	 * Gets the round about sc.
	 *
	 * @return the round about sc
	 */
	public Image getRoundAboutSc(){
		return this.roundAboutSc;
	}
	
	/**
	 * Gets the round about cr.
	 *
	 * @return the round about cr
	 */
	public Image getRoundAboutCr(){
		return this.roundAboutCr;
	}
	
	/**
	 * Gets the round about tb.
	 *
	 * @return the round about tb
	 */
	public Image getRoundAboutTb(){
		return this.roundAboutTb;
	}
	
	/**
	 * Gets the intersection double.
	 *
	 * @return the intersection double
	 */
	public Image getIntersectionDouble(){
		return this.intersectionDouble;
	}
	
	/**
	 * Gets the intersection double cr.
	 *
	 * @return the intersection double cr
	 */
	public Image getIntersectionDoubleCr(){
		return this.intersectionDoubleCr;
	}
	
	/**
	 * Gets the intersection double sc.
	 *
	 * @return the intersection double sc
	 */
	public Image getIntersectionDoubleSc(){
		return this.intersectionDoubleSc;
	}
	
	/**
	 * Gets the intersection double tb.
	 *
	 * @return the intersection double tb
	 */
	public Image getIntersectionDoubleTb(){
		return this.intersectionDoubleTb;
	}
	
	/**
	 * Gets the intersection up.
	 *
	 * @return the intersection up
	 */
	public Image getIntersectionUp(){
		return this.intersectionUp;
	}
	
	/**
	 * Gets the intersection up sc.
	 *
	 * @return the intersection up sc
	 */
	public Image getIntersectionUpSc(){
		return this.intersectionUpSc;
	}
	
	/**
	 * Gets the intersection up cr.
	 *
	 * @return the intersection up cr
	 */
	public Image getIntersectionUpCr(){
		return this.intersectionUpCr;
	}
	
	/**
	 * Gets the intersection down.
	 *
	 * @return the intersection down
	 */
	public Image getIntersectionDown(){
		return this.intersectionDown;
	}
	
	/**
	 * Gets the intersection down sc.
	 *
	 * @return the intersection down sc
	 */
	public Image getIntersectionDownSc(){
		return this.intersectionDownSc;
	}
	
	/**
	 * Gets the intersection down cr.
	 *
	 * @return the intersection down cr
	 */
	public Image getIntersectionDownCr(){
		return this.intersectionDownCr;
	}
	
	/**
	 * Gets the intersection left.
	 *
	 * @return the intersection left
	 */
	public Image getIntersectionLeft(){
		return this.intersectionLeft;
	}
	
	/**
	 * Gets the intersection left sc.
	 *
	 * @return the intersection left sc
	 */
	public Image getIntersectionLeftSc(){
		return this.intersectionLeftSc;
	}
	
	/**
	 * Gets the intersection left cr.
	 *
	 * @return the intersection left cr
	 */
	public Image getIntersectionLeftCr(){
		return this.intersectionLeftCr;
	}
	
	/**
	 * Gets the intersection right.
	 *
	 * @return the intersection right
	 */
	public Image getIntersectionRight(){
		return this.intersectionRight;
	}
	
	/**
	 * Gets the intersection right sc.
	 *
	 * @return the intersection right sc
	 */
	public Image getIntersectionRightSc(){
		return this.intersectionRightSc;
	}
	
	/**
	 * Gets the intersection right cr.
	 *
	 * @return the intersection right cr
	 */
	public Image getIntersectionRightCr(){
		return this.intersectionRightCr;
	}
	
	/**
	 * Gets the mixed intersection h.
	 *
	 * @return the mixed intersection h
	 */
	public Image getMixedIntersectionH(){
		return this.mixedIntersectionHorizontal;
	}
	
	/**
	 * Gets the mixed intersection h sc.
	 *
	 * @return the mixed intersection h sc
	 */
	public Image getMixedIntersectionHSc(){
		return this.mixedIntersectionHorizontalSc;
	}
	
	/**
	 * Gets the mixed intersection h cr.
	 *
	 * @return the mixed intersection h cr
	 */
	public Image getMixedIntersectionHCr(){
		return this.mixedIntersectionHorizontalCr;
	}
	
	/**
	 * Gets the mixed intersection h tb.
	 *
	 * @return the mixed intersection h tb
	 */
	public Image getMixedIntersectionHTb(){
		return this.mixedIntersectionHorizontalTb;
	}
	
	/**
	 * Gets the mixed intersection v.
	 *
	 * @return the mixed intersection v
	 */
	public Image getMixedIntersectionV(){
		return this.mixedIntersectionVertical;
	}
	
	/**
	 * Gets the mixed intersection v sc.
	 *
	 * @return the mixed intersection v sc
	 */
	public Image getMixedIntersectionVSc(){
		return this.mixedIntersectionVerticalSc;
	}
	
	/**
	 * Gets the mixed intersection v cr.
	 *
	 * @return the mixed intersection v cr
	 */
	public Image getMixedIntersectionVCr(){
		return this.mixedIntersectionVerticalCr;
	}
	
	/**
	 * Gets the mixed intersection v tb.
	 *
	 * @return the mixed intersection v tb
	 */
	public Image getMixedIntersectionVTb(){
		return this.mixedIntersectionVerticalTb;
	}
	
	/**
	 * Gets the play button.
	 *
	 * @return the play button
	 */
	public Image getPlayButton(){
		return this.playButton;
	}
	
	/**
	 * Gets the pause button.
	 *
	 * @return the pause button
	 */
	public Image getPauseButton(){
		return this.pauseButton;
	}
	
	/**
	 * Gets the stop button.
	 *
	 * @return the stop button
	 */
	public Image getStopButton(){
		return this.stopButton;
	}
	
	/**
	 * Gets the step button.
	 *
	 * @return the step button
	 */
	public Image getStepButton(){
		return this.stepButton;
	}
}

