package graphicsLoader.RoadEditorBuilder;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;
import graphicsLoader.ImagesSelector;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GridButtonsLoader {
	public static  Component [][] getGridButtons(short[][] roadGrid, ImagesBuilder ib){
		GridButtonMouseListener gridButtonMouseListener = new GridButtonMouseListener();
		
		//Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		Component [][] buttons = new Component [roadGrid.length] [roadGrid[0].length];
		//Border emptyBorder = BorderFactory.createEmptyBorder();
		JButton tempButton;
		for(int i=0;i<roadGrid.length;i++){
			
			for (int j=0;j<roadGrid[0].length;j++){
				if (roadGrid[i][j]!=0 && roadGrid[i][j]!=-100){
						if(roadGrid[i][j]>10){
							tempButton = new JButton();//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
							ImageIcon background = new ImageIcon( ImagesSelector.selectRoadImageSc(roadGrid[i][j], ib)); 
							tempButton.setIcon(background);
							tempButton.setLayout(null);
							tempButton.setSize(GraphicsConfig.BLOCK_SIDE_SIZE*2, GraphicsConfig.BLOCK_SIDE_SIZE*2);
							tempButton.setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE*2, GraphicsConfig.BLOCK_SIDE_SIZE*2));
						}else{
							tempButton = new JButton();//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
							ImageIcon background = new ImageIcon( ImagesSelector.selectRoadImageSc(roadGrid[i][j], ib)); 
							tempButton.setIcon(background);
							tempButton.setLayout(null);
							tempButton.setSize(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE);
							tempButton.setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));

						}
				} 
				else {
					tempButton = new JButton();//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
					tempButton.setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
				}
				
				tempButton.setRolloverEnabled(true);
				tempButton.addMouseListener(gridButtonMouseListener);
				tempButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				buttons[i][j] = tempButton;
			}
		}
		
		return buttons;
	}

}
