/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader.RoadEditorBuilder;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;
import graphicsLoader.ImagesSelector;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class GridButtons.
 */
public class GridButtons {
	
	/**
	 * Grid buttons.
	 *
	 * @param s
	 *            the s
	 * @param ib
	 *            the ib
	 * @return the component[][]
	 */
	public static  Component [][] gridButtons(short[][] s, ImagesBuilder ib){
		GridButtonMouseListener gridButtonMouseListener = new GridButtonMouseListener();
		
		//Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		Component [][] buttons = new Component [s.length] [s[0].length];
		//Border emptyBorder = BorderFactory.createEmptyBorder();

		for(int i=0;i<s.length;i++){
			JButton tempButton;
			for (int j=0;j<s[0].length;j++){
				if (s[i][j]!=0 && s[i][j]!=100){
					
					tempButton = new JButton();
					ImageIcon background = new ImageIcon( ImagesSelector.selectRoadImageSc(s[i][j], ib)); //Is this how you set a background image for a button?
			        tempButton.setIcon(background);
			        tempButton.setLayout(null); //This hasn't made a difference
			        tempButton.setSize(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE);
				}
				else {
					tempButton = new JButton();
				}
				tempButton.setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
				tempButton.setBorder(null);
				tempButton.setRolloverEnabled(true);
			//tempButton.setRolloverIcon(rolloverIcon);
			//tempButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GREEN,Color.GREEN));//(Color.GREEN, 10));//(BevelBorder.RAISED,Color.GREEN,Color.BLACK));
			tempButton.addMouseListener(gridButtonMouseListener);
			buttons[i][j] = tempButton;
			}
		}
		
		return buttons;
	}
}
