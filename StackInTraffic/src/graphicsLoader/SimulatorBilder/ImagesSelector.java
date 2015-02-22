/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader.SimulatorBilder;

import graphicsLoader.ImagesBuilder;

import java.awt.Image;

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
		
		case RoadConfig.INTERSECTION_BLOCK: return img = ib.getIntersection();
		
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
		
		if  (blockType == RoadConfig.HORIZONTAL_BLOCK ||blockType == RoadConfig.HORIZONTAL_ENTER_BLOCK || blockType == RoadConfig.HORIZONTAL_EXIT_BLOCK){
			if (direction == 1) return ib.getCarRight();
			else return ib.getCarLeft();
		}
		else if (blockType == RoadConfig.VERTICAL_BLOCK || blockType == RoadConfig.VERTICAL_ENTER_BLOCK || blockType == RoadConfig.VERTICAL_EXIT_BLOCK){
			if (direction == 1) return ib.getCarDown();
			else return ib.getCarUp();
		} else {
		
		return ib.getCarRight();
		
		}
	}
}
