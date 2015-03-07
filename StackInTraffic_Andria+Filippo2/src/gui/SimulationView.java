/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package gui;

import gui.RoadEditorView.ExitListener;
import gui.RoadEditorView.MainMenuListener;
import gui.RoadEditorView.OpenListener;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.Buffer;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


// TODO: Auto-generated Javadoc
/**
 * The Class SimulationView.
 */
public class SimulationView extends JPanel{
	private JMenuBar menuBarTop = new JMenuBar();
	
	/** The frame. */
	public ApplicationFrame frame;
	
	/**
	 * Instantiates a new simulation view.
	 *
	 * @param frame
	 *            the frame
	 */
	public SimulationView(ApplicationFrame frame){
		this.frame = frame;
		this.setLayout(new BorderLayout());
		JMenu fileMenu = new JMenu("File");
	    menuBarTop.add(fileMenu);
	    JMenu editMenu = new JMenu("Edit");
	    menuBarTop.add(editMenu);
	    
	    frame.setJMenuBar(menuBarTop);
	    
	    JMenuItem newMap = new JMenuItem("New");
	    JMenuItem openMap = new JMenuItem("Open");
	    openMap.addActionListener(new OpenListener());
	    JMenuItem exitMainMenu = new JMenuItem("Main Menu");
	    exitMainMenu.addActionListener(new MainMenuListener());
	    JMenuItem exit = new JMenuItem("Exit");
	    exit.addActionListener(new ExitListener());
		/*JButton mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(new MainMenuListener());
		menubar.add(mainMenu);*/
        JMenuItem saveMap = new JMenuItem("Save");
        JMenuItem deleteMap = new JMenuItem("Delete");
        JMenuItem clearMap = new JMenuItem("Clear");
        fileMenu.add(newMap);
        fileMenu.add(openMap);
        fileMenu.addSeparator();
        fileMenu.add(exitMainMenu);
        fileMenu.add(exit);
        editMenu.add(saveMap);
        editMenu.add(clearMap);
        editMenu.add(deleteMap);
        
        
		JMenuBar menubar = new JMenuBar();
	    this.add(menubar, BorderLayout.NORTH);
	    ImageIcon playImage = new ImageIcon("./images/play.png");
		ImageIcon stopImage = new ImageIcon("./images/stop.png");
		ImageIcon pauseImage = new ImageIcon("./images/pause.png");
		ImageIcon refreshImage = new ImageIcon("./images/images_refresh.png");
	    JButton play = new JButton(playImage);
		menubar.add(play);
		JButton pause = new JButton(pauseImage);
		menubar.add(pause);
		JButton stop = new JButton(stopImage);	
		menubar.add(stop);
		JButton refresh = new JButton(refreshImage);
		menubar.add(refresh);
		menubar.add(play);
		menubar.add(Box.createRigidArea(new Dimension(10,0)));
		menubar.add(pause); 
		menubar.add(Box.createRigidArea(new Dimension(10,0)));
		menubar.add(stop);
		menubar.add(Box.createRigidArea(new Dimension(10,0)));
		menubar.add(refresh); 
		
		/*JButton mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(new MainMenuListener());
		menubar.add(mainMenu);*/
		
		JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
		toolbar.setFloatable(false);
		this.add(toolbar, BorderLayout.EAST);
		//Images on buttons - TODO fix the size of the buttons
		/*ImageIcon horizontalImage = new ImageIcon("./images/horizontalRoad.png");
		ImageIcon verticalImage = new ImageIcon("./images/verticalRoad.png");
		ImageIcon intersectionImage = new ImageIcon("./images/Intersection.png");
		JButton horizontalButton = new JButton(horizontalImage);
		JButton verticalButton = new JButton(verticalImage);
		JButton intersectionButton = new JButton(intersectionImage);
		JButton horizontalButton = new JButton("Horizontal");
		JButton verticalButton = new JButton("Vertical");
		JButton intersectionButton = new JButton("Intersection");*/
		JButton selectMapButton = new JButton("Open Sets of Maps");
		selectMapButton.addActionListener(new OpenListener());
		selectMapButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		toolbar.add(selectMapButton);
		//selectMapButton.setBackground(new Color(100,100,204));
		Font font = new Font("Avenir Book", Font.BOLD, 14);
		selectMapButton.setFont(font);
		selectMapButton.setForeground(Color.BLUE);
		toolbar.setPreferredSize(new Dimension(200,200));
		toolbar.add(selectMapButton);
		toolbar.addSeparator(new Dimension(100,150));
		
		JLabel carLabel= new JLabel("Select Number of Cars");
		carLabel.setFont(font);
		carLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		toolbar.add(carLabel);
		
		// 0 to n. n will be automaticaly calculated
		//TODO SET TO MAX
		JScrollBar carScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 1, 10, 0, 100); 
		carScrollBar.setAlignmentY(Component.TOP_ALIGNMENT);
		carScrollBar.setMinimum(1);
		carScrollBar.setMaximum(100);
		JLabel limitsLabel = new JLabel();
		limitsLabel.setText("" +carScrollBar.getMinimum() + "                              " + carScrollBar.getMaximum());
		limitsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		toolbar.setPreferredSize(new Dimension(200,200));
		toolbar.add(carScrollBar);
		toolbar.add(limitsLabel);
		toolbar.addSeparator(new Dimension(100,150));
	
		
		JLabel motionLabel= new JLabel("Select Car Speed"); 
		motionLabel.setFont(font);
		motionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		toolbar.add(motionLabel);
		JScrollBar motionScrollBar = new JScrollBar(JScrollBar.HORIZONTAL,1, 1, 0, 10);
		motionScrollBar.setAlignmentY(Component.TOP_ALIGNMENT);
		motionScrollBar.setMinimum(1);
		motionScrollBar.setMaximum(10);
		JLabel mLabel = new JLabel();
		mLabel.setText("" +motionScrollBar.getMinimum() + "                               " + motionScrollBar.getMaximum());
		mLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		toolbar.setPreferredSize(new Dimension(200,200));
		toolbar.add(motionScrollBar);
		toolbar.add(mLabel);
		toolbar.addSeparator(new Dimension(100,150)); //(new JSeparator(SwingConstants.HORIZONTAL));
		
		
		//toolbar.add(carLabel);
		//toolbar.add(numberOfCars);
		//toolbar.add(speedLabel);
		//toolbar.add(speed);
		//toolbar.add(horizontalButton);
		//toolbar.add(verticalButton);
		//toolbar.add(intersectionButton);
		//JToolBar toolbar2 = new JToolBar(null, JToolBar.VERTICAL);
		//this.add(toolbar2, BorderLayout.WEST);
		//JButton b1 = new JButton("Test");
		//toolbar2.add(b1);
	}
	
	/**
	 * The listener interface for receiving mainMenu events. The class that is
	 * interested in processing a mainMenu event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addMainMenuListener<code> method. When
	 * the mainMenu event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see MainMenuEvent
	 */
	
	public class MainMenuListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			frame.removeView();
			frame.addView(new MainView(frame));
		}
	}
	
public class ExitListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			System.exit(0);
		}
	}
	
	public class OpenListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			MapChoiceView openMap = new MapChoiceView();
		}
	}
	
	public class MapListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			MapChoiceView selectMapButton = new MapChoiceView();
			//Load set of predefined maps
		}
	}
	
	public class RefreshListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			//TODO Implement listener for the refresh button
		}
	}

	public class PlayListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			//TODO Implement listener for the play button
		}
	}
	
	public class PauseListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			//TODO Implement listener for the pause button
		}
	}
	
	public class StopListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			//TODO Implement listener for the stop button
		}
	}
	
	public class IncreaseNumberOfCars implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			//TODO Implement listener for increase number of cars
		
	}
}
	public class IncreaseCarSpeed implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			//TODO Implement listener for increase car speed 
		}
	}
}
