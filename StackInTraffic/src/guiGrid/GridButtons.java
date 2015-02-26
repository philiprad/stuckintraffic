package guiGrid;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class GridButtons {
	public static List<Component> gridButtons(Dimension dimension){
		MyMouseListener myMouseListener = new MyMouseListener();
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Image image = toolkit.getImage("./images/car.png");
		Point hotSpot = new Point(0,0);  
		Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "car");  
		//Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		List<Component> buttons = new ArrayList<Component>();
		int buttons_no = dimension.height / 50;
		//Border emptyBorder = BorderFactory.createEmptyBorder();

		for(int i =0;i<buttons_no;i++){
			JButton tempButton = new JButton("RoadBlock"+i);
			tempButton.setPreferredSize(new Dimension(50, 50));
			tempButton.setBorder(null);
			tempButton.setCursor(cursor);
			tempButton.setRolloverEnabled(true);
			//tempButton.setRolloverIcon(rolloverIcon);
			//tempButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GREEN,Color.GREEN));//(Color.GREEN, 10));//(BevelBorder.RAISED,Color.GREEN,Color.BLACK));
			tempButton.addMouseListener(myMouseListener);
			buttons.add(tempButton);
		}
		
		return buttons;
	}
}
