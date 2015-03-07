/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader.RoadEditorBuilder;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

// TODO: Auto-generated Javadoc
/**
 * The Class CursorManager.
 */
public class CursorManager {
	
	/**
	 * Custom cursor.
	 *
	 * @param image
	 *            the image
	 * @return the cursor
	 */
	public static Cursor customCursor(Image image){
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Point hotSpot = new Point(0,0);  
		Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "custom"); 
		return cursor;
	}
	
	/**
	 * Hand cursor.
	 *
	 * @return the cursor
	 */
	public static Cursor handCursor(){
		return new Cursor(Cursor.HAND_CURSOR);
	}
}
