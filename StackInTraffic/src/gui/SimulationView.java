/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import javax.swing.Timer;

import trafficInfrastructure.grid.GridBuilder;
//import gui.RoadEditorView.OpenListener;


// TODO: Auto-generated Javadoc
/**
 * The Class SimulationView.
 */
public class SimulationView extends JPanel{

	private GridBuilder gridBuilder;
    /** The play button. */
    private JButton playButton;
	
	/** The pause button. */
	private JButton pauseButton;
	
	/** The stop button. */
	private JButton stopButton;	
	
	/** The refresh button. */
	private JButton refreshButton;
	
	/** The display timer. */
	private JLabel displayTimer;

    /** The centiseconds. */
    private byte centiseconds = 0;
    
    /** The seconds. */
    private byte seconds = 0;
    
    /** The minutes. */
    private short minutes = 0;
    
    /** The time formatter. */
    private DecimalFormat timeFormatter;//timer display format
    
    /** The simulation timer. */
    private Timer simulationTimer;
    
	
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
		this.loadMainContent();
		MapChoiceView mapChoiceView = new MapChoiceView(frame, this, this.gridBuilder);
	}
	
	public void loadMainContent(){
		this.setLayout(new BorderLayout());
		JMenuBar menuBarTop = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
	    menuBarTop.add(fileMenu);
	    JMenu editMenu = new JMenu("Edit");
	    menuBarTop.add(editMenu);
	    
	    frame.setJMenuBar(menuBarTop);
	    
	    JMenuItem newMap = new JMenuItem("New");
	    JMenuItem openMap = new JMenuItem("Open");
	    //openMap.addActionListener(new OpenListener());
	    JMenuItem exitMainMenu = new JMenuItem("Main Menu");
	    exitMainMenu.addActionListener(new MainMenuListener());
	    JMenuItem exit = new JMenuItem("Exit");
	    exit.addActionListener(new ExitListener());
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
     
        //-----------------------------timer implementation
		JMenuBar menubar = new JMenuBar();
	    this.add(menubar, BorderLayout.NORTH);
	    
	    /**
	     * TODO resize images
	     */
	    ImageIcon playImage = new ImageIcon("./images/play.png");
		ImageIcon stopImage = new ImageIcon("./images/stop.png");
		ImageIcon pauseImage = new ImageIcon("./images/pause.png");
		
		/**
		 * TODO get refresh image from Max 
		 */
		ImageIcon refreshImage = new ImageIcon("./images/refresh.jpg");
		
		playButton = new JButton(playImage);
		pauseButton = new JButton(pauseImage);
		stopButton = new JButton(stopImage);	
		refreshButton = new JButton(refreshImage);
		displayTimer = new JLabel("Display Timer");
	    JLabel timerLabel = new JLabel("Timer: ");
	   // simulationTimer = new Timer();
	    timeFormatter = new DecimalFormat("00");
	    
        
	playButton.addActionListener(new PlayListener());
	pauseButton.addActionListener(new PauseListener());
	stopButton.addActionListener(new StopListener());
	refreshButton.addActionListener(new RefreshListener());
	simulationTimer = new Timer(10, new ActionListener() {
        
		@Override
      public void actionPerformed(ActionEvent e) {
         	if(centiseconds<99){	
         		centiseconds++;
         	} else {
         		if(centiseconds==99){
         			seconds++;
         			centiseconds=0; 
         		} if(seconds==60){
         				minutes++;
         				seconds=0; 
         	}
         }
         	displayTimer.setText(timeFormatter.format(minutes) + ":"
                     + timeFormatter.format(seconds) + "."
                     + timeFormatter.format(centiseconds));
         }
     });

	displayTimer.setText(timeFormatter.format(minutes) + ":"
             + timeFormatter.format(seconds) + "."
             + timeFormatter.format(centiseconds));

		
		//space between buttons
		menubar.add(timerLabel);
		menubar.add(Box.createRigidArea(new Dimension(10,0)));
		menubar.add(displayTimer);
		menubar.add(Box.createRigidArea(new Dimension(20,0)));
		menubar.add(playButton);
		menubar.add(Box.createRigidArea(new Dimension(10,0)));
		menubar.add(pauseButton); 
		menubar.add(Box.createRigidArea(new Dimension(10,0)));
		menubar.add(stopButton);
		menubar.add(Box.createRigidArea(new Dimension(10,0)));
		menubar.add(refreshButton);  
		
		
	
		/*JButton mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(new MainMenuListener());
		menubar.add(mainMenu);*/
		
		JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
		toolbar.setFloatable(false);
		this.add(toolbar, BorderLayout.EAST);
		
		//selectMapButton.addActionListener(new OpenListener());
		//selectMapButton.setBackground(new Color(100,100,204));
		Font font = new Font("Avenir Book", Font.BOLD, 14);
		toolbar.setPreferredSize(new Dimension(200,200));
		toolbar.addSeparator(new Dimension(100,150));
		
		JLabel carLabel= new JLabel("Set Number of Cars");
		carLabel.setFont(font);
		carLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		toolbar.add(carLabel);
		
		// 0 to n. n will be automatically calculated
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
	
		
		JLabel motionLabel= new JLabel("Set Simulation Speed"); 
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
	
	//listener for exit
	/**
	 * The listener interface for receiving exit events. The class that is
	 * interested in processing a exit event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addExitListener<code> method. When
	 * the exit event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ExitEvent
	 */
	public class ExitListener implements ActionListener{	
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			System.exit(0);
		}
	}
	
	/**
	 * The listener interface for receiving play events. The class that is
	 * interested in processing a play event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addPlayListener<code> method. When
	 * the play event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see PlayEvent
	 */
	public class PlayListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			simulationTimer.start();
		}
	}
	
	/**
	 * The listener interface for receiving pause events. The class that is
	 * interested in processing a pause event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addPauseListener<code> method. When
	 * the pause event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see PauseEvent
	 */
	public class PauseListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			simulationTimer.stop();
			
		}
	}
	
	/**
	 * The listener interface for receiving stop events. The class that is
	 * interested in processing a stop event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addStopListener<code> method. When
	 * the stop event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see StopEvent
	 */
	public class StopListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			simulationTimer.stop();
		}
	}
	
	/**
	 * The listener interface for receiving refresh events. The class that is
	 * interested in processing a refresh event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addRefreshListener<code> method. When
	 * the refresh event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see RefreshEvent
	 */
	public class RefreshListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			 simulationTimer.stop();

             centiseconds = 0;
             seconds = 0;
             minutes = 0;

             displayTimer.setText(timeFormatter.format(minutes) + ":"
                     + timeFormatter.format(seconds) + "."
                     + timeFormatter.format(centiseconds));
		}
	}
	
	/*public class TimerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        	if(centiseconds<99){	
        		centiseconds++;
        	} else {
        		if(centiseconds==99){
        			seconds++;
        			centiseconds=0; 
        		}else if(seconds<60){
        				seconds++;
        			} else {
        				if(seconds==60){
        				minutes++;
        				seconds=0;
        			}
        		}
        	}
        	displayTimer.setText(timeFormatter.format(minutes) + ":"
                    + timeFormatter.format(seconds) + "."
                    + timeFormatter.format(centiseconds));
        }
    }*/
	
	/**
	 * The Class IncreaseNumberOfCars.
	 */
	public class IncreaseNumberOfCars implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			//TODO Implement listener for increase number of cars
		
	}
}
	
	/**
	 * The Class IncreaseCarSpeed.
	 */
	public class IncreaseCarSpeed implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			//TODO Implement listener for increase car speed 
		}
	}
}

