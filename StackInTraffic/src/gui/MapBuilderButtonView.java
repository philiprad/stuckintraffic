package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;


		public class MapBuilderButtonView{
			public static void main (String[] args){
				JFrame frame = new JFrame();
				//frame.setSize(GraphicsConfig.MAIN_WIDTH, GraphicsConfig.MAIN_HEIGHT+20);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				//this.setExtendedState(this.MAXIMIZED_BOTH);
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
				frame.add(toolbar, BorderLayout.EAST);
				//Images on buttons - TODO fix the size of the buttons
				/*ImageIcon horizontalImage = new ImageIcon("./images/horizontalRoad.png");
				ImageIcon verticalImage = new ImageIcon("./images/verticalRoad.png");
				ImageIcon intersectionImage = new ImageIcon("./images/Intersection.png");
				JButton horizontalButton = new JButton(horizontalImage);
				JButton verticalButton = new JButton(verticalImage);
				JButton intersectionButton = new JButton(intersectionImage);*/
				JButton horizontalButton = new JButton("Horizontal");
				JButton verticalButton = new JButton("Vertical");
				JButton intersectionButton = new JButton("Intersection");
			

				toolbar.add(horizontalButton);
				toolbar.add(verticalButton);
				toolbar.add(intersectionButton);
				
				frame.setVisible(true);
									
	}
			
}
