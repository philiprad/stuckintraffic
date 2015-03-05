package graphicsLoader.RoadEditorBuilder;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class CursorManager {
	public static Cursor customCursor(Image image){
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Point hotSpot = new Point(0,0);  
		Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "custom"); 
		return cursor;
	}
	
	public static Cursor handCursor(){
		return new Cursor(Cursor.HAND_CURSOR);
	}
}
