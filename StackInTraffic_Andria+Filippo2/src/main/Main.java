/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package main;

import graphicsLoader.ImagesBuilder;
import graphicsLoader.SimulatorBilder.GraphicsDrawer;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;

import trafficInfrastructure.RoadBuilder;
import trafficInfrastructure.road.BlockGraphicPoint;
import util.FileRW;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {
	
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	@SuppressWarnings("unchecked")
	public static void main (String[] args){
		String rdName = "test";
		AppDirBuilder.build();
		//RoadBuilder rb = new RoadBuilder("test", 150 , 0);
	//	rb.buildRoad();
		
		JFrame frame = new JFrame();
		//frame.setSize(GraphicsConfig.MAIN_WIDTH, GraphicsConfig.MAIN_HEIGHT+20);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//menu bar options
	    JMenuBar menubar = new JMenuBar();
	    frame.setJMenuBar(menubar);
	    JButton play = new JButton("Play");
		menubar.add(play);
		JButton pause = new JButton("Pause");
		menubar.add(pause);
		JButton stop = new JButton("Stop");	
		menubar.add(stop);
		JButton mainMenu = new JButton("Main Menu");
		menubar.add(mainMenu);
		JToolBar toolbar = new JToolBar();
		frame.add(toolbar, BorderLayout.BEFORE_FIRST_LINE);
		JButton loadMapsButton = new JButton("Load Maps");
		JScrollBar motionScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar carScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		
		ImagesBuilder ib = new ImagesBuilder();
		ArrayList<BlockGraphicPoint> arrBG =(ArrayList<BlockGraphicPoint>) FileRW.readObject(MainConfig.ROADBLOCK_PATH+"/"+rdName+MainConfig.ROADBLOCK_GRAPHICS_SUFFIX);
		frame.getContentPane().add(new GraphicsDrawer(50 , "test" , arrBG , ib ));
	    frame.setVisible(true);
	}
}
