/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package graphicsLoader;

import java.awt.Image;

import agents.AgentConfig;
import trafficInfrastructure.road.RoadConfig;

// TODO: Auto-generated Javadoc
/**
 * The Class ImagesSelector.
 */
public class ImagesSelector {
	
	/**
	 * Select road image.
	 *
	 * @param blockType the block type
	 * @param ib the ib
	 * @return the image
	 */
	public static Image selectRoadImage(int blockType, ImagesBuilder ib){
		
		Image img = null;
		
		switch (blockType){
			
		case RoadConfig.HORIZONTAL_BLOCK: return img = ib.getHorizontalRd();
		case RoadConfig.HORIZONTAL_ENTER_BLOCK: return img = ib.getHorizontalRd();
		case RoadConfig.HORIZONTAL_EXIT_BLOCK: return img = ib.getHorizontalRd();
		
		case RoadConfig.VERTICAL_BLOCK: return img = ib.getVerticalRd();
		case RoadConfig.VERTICAL_ENTER_BLOCK: return img = ib.getVerticalRd();
		case RoadConfig.VERTICAL_EXIT_BLOCK: return img = ib.getVerticalRd();
		
		case RoadConfig.HORIZONTAL_DOUBLE_BLOCK: return img = ib.getHorizontalDoubleRd();
		case RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK: return img = ib.getHorizontalDoubleRd();
		case RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK: return img = ib.getHorizontalDoubleRd();
		
		case RoadConfig.VERTICAL_DOUBLE_BLOCK: return img = ib.getVerticalDoubleRd();
		case RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK: return img = ib.getVerticalDoubleRd();
		case RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK: return img = ib.getVerticalDoubleRd();
		
		case RoadConfig.INTERSECTION_BLOCK: return img = ib.getIntersection();
		case RoadConfig.INTERSECTION_DOWN_BLOCK: return img = ib.getIntersectionDown();
		case RoadConfig.INTERSECTION_UP_BLOCK: return img = ib.getIntersectionUp();
		case RoadConfig.INTERSECTION_RIGHT_BLOCK: return img = ib.getIntersectionRight();
		case RoadConfig.INTERSECTION_LEFT_BLOCK: return img = ib.getIntersectionLeft();
		case RoadConfig.INTERSECTION_DOUBLE_BLOCK: return img = ib.getIntersectionDouble();
		case RoadConfig.ROUND_ABOUT_BLOCK: return img = ib.getRoundAbout();
		
		case RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK: return img = ib.getMixedIntersectionH();
		case RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK: return img = ib.getMixedIntersectionV();
		
		}
		
		return img;
	}
	
/**
 * Select road image sc.
 *
 * @param blockType
 *            the block type
 * @param ib
 *            the ib
 * @return the image
 */
public static Image selectRoadImageSc(int blockType, ImagesBuilder ib){
		
		Image img = null;
		
		switch (blockType){
			
		case RoadConfig.HORIZONTAL_BLOCK: return img = ib.getHorizontalRdSc();
		case RoadConfig.HORIZONTAL_ENTER_BLOCK: return img = ib.getHorizontalRdSc();
		case RoadConfig.HORIZONTAL_EXIT_BLOCK: return img = ib.getHorizontalRdSc();
		
		case RoadConfig.VERTICAL_BLOCK: return img = ib.getVerticalRdSc();
		case RoadConfig.VERTICAL_ENTER_BLOCK: return img = ib.getVerticalRdSc();
		case RoadConfig.VERTICAL_EXIT_BLOCK: return img = ib.getVerticalRdSc();
		
		case RoadConfig.HORIZONTAL_DOUBLE_BLOCK: return img = ib.getHorizontalDoubleRdSc();
		case RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK: return img = ib.getHorizontalDoubleRdSc();
		case RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK: return img = ib.getHorizontalDoubleRdSc();
		
		case RoadConfig.VERTICAL_DOUBLE_BLOCK: return img = ib.getVerticalDoubleRdSc();
		case RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK: return img = ib.getVerticalDoubleRdSc();
		case RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK: return img = ib.getVerticalDoubleRdSc();
		
		case RoadConfig.INTERSECTION_BLOCK: return img = ib.getIntersectionSc();
		case RoadConfig.INTERSECTION_DOWN_BLOCK: return img = ib.getIntersectionDownSc();
		case RoadConfig.INTERSECTION_UP_BLOCK: return img = ib.getIntersectionUpSc();
		case RoadConfig.INTERSECTION_RIGHT_BLOCK: return img = ib.getIntersectionRightSc();
		case RoadConfig.INTERSECTION_LEFT_BLOCK: return img = ib.getIntersectionLeftSc();
		case RoadConfig.INTERSECTION_DOUBLE_BLOCK: return img = ib.getIntersectionDoubleSc();
		case RoadConfig.ROUND_ABOUT_BLOCK: return img = ib.getRoundAboutSc();
		
		case RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK: return img = ib.getMixedIntersectionHSc();
		case RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK: return img = ib.getMixedIntersectionVSc();
		}
		
		return img;
	}
	


/**
 * Select road image tb.
 *
 * @param blockType
 *            the block type
 * @param ib
 *            the ib
 * @return the image
 */
public static Image selectRoadImageTb(int blockType, ImagesBuilder ib){
	
	Image img = null;
	
	switch (blockType){
	
		case RoadConfig.HORIZONTAL_DOUBLE_BLOCK: return img = ib.getHorizontalDoubleRdTb();
		case RoadConfig.VERTICAL_DOUBLE_BLOCK: return img = ib.getVerticalDoubleRdTb();
		case RoadConfig.INTERSECTION_DOUBLE_BLOCK: return img = ib.getIntersectionDoubleTb();
		case RoadConfig.ROUND_ABOUT_BLOCK: return img = ib.getRoundAboutTb();
		case RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK: return img = ib.getMixedIntersectionHTb();
		case RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK: return img = ib.getMixedIntersectionVTb();
	}
	
	return img;
}

/**
 * Select road image cr.
 *
 * @param blockType
 *            the block type
 * @param ib
 *            the ib
 * @return the image
 */
public static Image selectRoadImageCr(int blockType, ImagesBuilder ib){
	
	Image img = null;
	
	switch (blockType){
	
		case RoadConfig.HORIZONTAL_BLOCK: return img = ib.getHorizontalRdCr();
		case RoadConfig.VERTICAL_BLOCK: return img = ib.getVerticalRdCr();
		case RoadConfig.HORIZONTAL_DOUBLE_BLOCK: return img = ib.getHorizontalDoubleRdCr();
		case RoadConfig.VERTICAL_DOUBLE_BLOCK: return img = ib.getVerticalDoubleRdCr();
		case RoadConfig.INTERSECTION_BLOCK: return img = ib.getIntersectionCr();
		case RoadConfig.INTERSECTION_DOWN_BLOCK: return img = ib.getIntersectionDownCr();
		case RoadConfig.INTERSECTION_UP_BLOCK: return img = ib.getIntersectionUpCr();
		case RoadConfig.INTERSECTION_RIGHT_BLOCK: return img = ib.getIntersectionRightCr();
		case RoadConfig.INTERSECTION_LEFT_BLOCK: return img = ib.getIntersectionLeftCr();
		case RoadConfig.INTERSECTION_DOUBLE_BLOCK: return img = ib.getIntersectionDoubleCr();
		case RoadConfig.ROUND_ABOUT_BLOCK: return img = ib.getRoundAboutCr();
		case RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK: return img = ib.getMixedIntersectionHCr();
		case RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK: return img = ib.getMixedIntersectionVCr();
	}
	
	return img;
}
/**
 * Select car image.
 *
 * @param blockType the block type
 * @param direction the direction
 * @param ib the ib
 * @return the image
 */
public static Image selectCarImage(int blockType, int direction, ImagesBuilder ib){
		
		if  (blockType == RoadConfig.HORIZONTAL_BLOCK ||blockType == RoadConfig.HORIZONTAL_ENTER_BLOCK || blockType == RoadConfig.HORIZONTAL_EXIT_BLOCK ||
			blockType == RoadConfig.HORIZONTAL_DOUBLE_BLOCK ||blockType == RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || blockType == RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK){
			if (direction == 1) return ib.getCarRight();
			else return ib.getCarLeft();
		}
		else if (blockType == RoadConfig.VERTICAL_BLOCK || blockType == RoadConfig.VERTICAL_ENTER_BLOCK || blockType == RoadConfig.VERTICAL_EXIT_BLOCK ||
				blockType == RoadConfig.VERTICAL_DOUBLE_BLOCK || blockType == RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || blockType == RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
			if (direction == 1) return ib.getCarDown();
			else return ib.getCarUp();
		} else {
		
		return ib.getCarRight();
		
		}
	}



/**
 * Car selector.
 *
 * @param carType the car type
 * @param driverType the driver type
 * @param direction the direction
 * @param ib the ib
 * @return the image
 */
public static Image carSelector(int carType,int driverType, int direction, ImagesBuilder ib){
	
	Image img = null;
	switch(driverType){
	case AgentConfig.NORMAL_DRIVER :
		switch(carType){
		case AgentConfig.PETROL_CAR:
			switch(direction){
			case AgentConfig.RIGHT:
				img = ib.carBPRight;
				break;
			case AgentConfig.LEFT:
				img = ib.carBPLeft;
				break;
			case AgentConfig.UP:
				img = ib.carBPUp;
				break;
			case AgentConfig.DOWN:
				img = ib.carBPDown;
				break;
			}break;
		case AgentConfig.ELECTRIC_CAR :
			switch(direction){
			case AgentConfig.RIGHT:
				img = ib.carBERight;
				break;
			case AgentConfig.LEFT:
				img = ib.carBELeft;
				break;
			case AgentConfig.UP:
				img = ib.carBEUp;
				break;
			case AgentConfig.DOWN:
				img = ib.carBEDown;
				break;
			}break;
		case AgentConfig.HYBRID_CAR :
			switch(direction){
			case AgentConfig.RIGHT:
				img = ib.carBHRight;
				break;
			case AgentConfig.LEFT:
				img = ib.carBHLeft;
				break;
			case AgentConfig.UP:
				img = ib.carBHUp;
				break;
			case AgentConfig.DOWN:
				img = ib.carBHDown;
				break;
			}break;
	
		}break;
		
		
	
	case AgentConfig.FAST_DRIVER :
		switch(carType){
		case AgentConfig.PETROL_CAR:
			switch(direction){
			case AgentConfig.RIGHT:
				img = ib.carRPRight;
				break;
			case AgentConfig.LEFT:
				img = ib.carRPLeft;
				break;
			case AgentConfig.UP:
				img = ib.carRPUp;
				break;
			case AgentConfig.DOWN:
				img = ib.carRPDown;
				break;
			} break;
		case AgentConfig.ELECTRIC_CAR :
			switch(direction){
			case AgentConfig.RIGHT:
				img = ib.carRERight;
				break;
			case AgentConfig.LEFT:
				img = ib.carRELeft;
				break;
			case AgentConfig.UP:
				img = ib.carREUp;
				break;
			case AgentConfig.DOWN:
				img = ib.carREDown;
				break;
			}break;
		case AgentConfig.HYBRID_CAR :
			switch(direction){
			case AgentConfig.RIGHT:
				img = ib.carRHRight;
				break;
			case AgentConfig.LEFT:
				img = ib.carRHLeft;
				break;
			case AgentConfig.UP:
				img = ib.carRHUp;
				break;
			case AgentConfig.DOWN:
				img = ib.carRHDown;
				break;
			} break;
	
		}break;
		
	case AgentConfig.FAMILY_DRIVER :
		switch(carType){
		case AgentConfig.PETROL_CAR:
			switch(direction){
			case AgentConfig.RIGHT:
				img = ib.carYPRight;
				break;
			case AgentConfig.LEFT:
				img = ib.carYPLeft;
				break;
			case AgentConfig.UP:
				img = ib.carYPUp;
				break;
			case AgentConfig.DOWN:
				img = ib.carYPDown;
				break;
			} break;
		case AgentConfig.ELECTRIC_CAR :
			switch(direction){
			case AgentConfig.RIGHT:
				img = ib.carYERight;
				break;
			case AgentConfig.LEFT:
				img = ib.carYELeft;
				break;
			case AgentConfig.UP:
				img = ib.carYEUp;
				break;
			case AgentConfig.DOWN:
				img = ib.carYEDown;
				break;
			}break;
		case AgentConfig.HYBRID_CAR :
			switch(direction){
			case AgentConfig.RIGHT:
				img = ib.carYHRight;
				break;
			case AgentConfig.LEFT:
				img = ib.carYHLeft;
				break;
			case AgentConfig.UP:
				img = ib.carYHUp;
				break;
			case AgentConfig.DOWN:
				img = ib.carYHDown;
				break;
			}break;
	
		}break;
		
		
	
		
		
	}
	return img;
}

}



