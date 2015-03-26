/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package gui;

import graphicsLoader.ImagesBuilder;
import graphicsLoader.SimulatorBilder.GraphicsDrawer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.Timer;

import main.MainConfig;
import trafficInfrastructure.grid.GridBuilder;
//import gui.RoadEditorView.OpenListener;
import trafficInfrastructure.road.BlockGraphicPoint;
import trafficInfrastructure.road.RoadConfig;
import util.FileRW;


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
    
    private ImagesBuilder ib = new ImagesBuilder(); 
	
    private JScrollPane scrollPane;
	/** The frame. */
	public ApplicationFrame frame;
	
	private String mapName = "";
	
	private JScrollBar carScrollBar;
	
	private JScrollBar motionScrollBar;
	
	private GraphicsDrawer gDrawer;
	

	
	private JLabel numberLabel;
	
	private JLabel speedLabel;
	
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
	
	
	
	public SimulationView(ApplicationFrame frame, String mapName){
		this.mapName = mapName;
		this.gridBuilder = (GridBuilder) (FileRW.readObject(MainConfig.GRID_PATH + "/"+mapName+MainConfig.GRID_SUFFIX));
		
		this.frame = frame;
		this.loadMainContent();
		this.loadMap(mapName);
		
		//MapChoiceView mapChoiceView = new MapChoiceView(frame, this, this.gridBuilder);
	}
	
	
	public void setMapName(String mapName){
		this.mapName = mapName;
	}
	
	public void setGridBuilder(GridBuilder gridBuilder){
		this.gridBuilder = gridBuilder;
	}
	
	public void loadMainContent(){
		this.setLayout(new BorderLayout());
		JMenuBar menuBarTop = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
	    menuBarTop.add(fileMenu);
	    JMenu editMenu = new JMenu("Edit");
	    menuBarTop.add(editMenu);
	    
	    frame.setJMenuBar(menuBarTop);
	    
	    JMenuItem openMap = new JMenuItem("Open");
	    //openMap.addActionListener(new OpenListener());
	    JMenuItem exitMainMenu = new JMenuItem("Main Menu");
	    exitMainMenu.addActionListener(new MainMenuListener());
	    JMenuItem exit = new JMenuItem("Exit");
	    exit.addActionListener(new ExitListener());
        JMenuItem editMap = new JMenuItem("Map Editor");
        
        fileMenu.add(openMap);
        fileMenu.addSeparator();
        fileMenu.add(exitMainMenu);
        fileMenu.add(exit);
        editMenu.add(editMap);
        
        
        
		this.frame.add(this, BorderLayout.CENTER);
     
        //-----------------------------timer implementation
		JMenuBar menubar = new JMenuBar();
	    this.add(menubar, BorderLayout.NORTH);
	    
	    /**
	     * TODO resize images
	     */
	    ImageIcon playImage = new ImageIcon(ib.getPlayButton());
		ImageIcon stopImage = new ImageIcon(ib.getStopButton());
		ImageIcon pauseImage = new ImageIcon(ib.getPauseButton());
		
		/**
		 * TODO get refresh image from Max 
		 */
		ImageIcon refreshImage = new ImageIcon(ib.getStepButton());
		
		playButton = new JButton(playImage);
		pauseButton = new JButton(pauseImage);
		stopButton = new JButton(stopImage);	
		refreshButton = new JButton(refreshImage);
		displayTimer = new JLabel("Display Timer");
	    JLabel timerLabel = new JLabel("Timer: ");
	   // simulationTimer = new Timer();
	    timeFormatter = new DecimalFormat("00");
	    
        
	    
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
		toolbar.addSeparator(new Dimension(100,30));
		
		JLabel carLabel= new JLabel("Number of Cars");
		carLabel.setFont(font);
		carLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		toolbar.add(carLabel);
		
		// 0 to n. n will be automatically calculated
		//TODO SET TO MAX
		carScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 1, 10, 0, 100); 
		carScrollBar.setAlignmentY(Component.TOP_ALIGNMENT);
		carScrollBar.setMinimum(1);
		carScrollBar.setMaximum(100);
		JLabel limitsLabel = new JLabel();
		limitsLabel.setText("Min" + "                              " + "Max");
		limitsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		toolbar.setPreferredSize(new Dimension(200,200));
		toolbar.add(carScrollBar);
		toolbar.add(limitsLabel);
		toolbar.addSeparator(new Dimension(100,30));
	
		
		JLabel motionLabel= new JLabel("Simulation Speed"); 
		motionLabel.setFont(font);
		motionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		toolbar.add(motionLabel);
		motionScrollBar = new JScrollBar(JScrollBar.HORIZONTAL,1, 1, 0, 10);
		motionScrollBar.setAlignmentY(Component.TOP_ALIGNMENT);
		
		
		
		
		JLabel mLabel = new JLabel();
		mLabel.setText("Fast" + "                              " + "Slow");
		mLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		Font font1 = new Font("Avenir Book", Font.BOLD, 20);
		toolbar.setPreferredSize(new Dimension(200,200));
		toolbar.add(motionScrollBar);
		toolbar.add(mLabel);
		toolbar.addSeparator(new Dimension(100,30)); //(new JSeparator(SwingConstants.HORIZONTAL));
		JLabel carsLabel = new JLabel();
		carsLabel.setText("Cars current number: ");
		carsLabel.setFont(font);
		carsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		numberLabel = new JLabel();
		numberLabel.setText("");
		numberLabel.setFont(font1);
		numberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		toolbar.add(carsLabel);
		toolbar.add(numberLabel);
		
		JLabel carsAvgLabel = new JLabel();
		carsAvgLabel.setText("Avg car speed: ");
		carsAvgLabel.setFont(font);
		carsAvgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		speedLabel = new JLabel();
		speedLabel.setText("");
		speedLabel.setFont(font1);
		speedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		toolbar.add(carsAvgLabel);
		toolbar.add(speedLabel);

	}
	
	@SuppressWarnings("unchecked")
	public void loadMap(String mapName){
		ArrayList<BlockGraphicPoint> arrBG =(ArrayList<BlockGraphicPoint>) FileRW.readObject(MainConfig.ROADBLOCK_PATH+"/"+mapName+MainConfig.ROADBLOCK_GRAPHICS_SUFFIX);
		gDrawer = new GraphicsDrawer(this, 50 ,mapName, arrBG , ib );
		motionScrollBar.setMinimum(20);
		motionScrollBar.setMaximum(100);
		motionScrollBar.setValue(50);
		carScrollBar.setMinimum(1);
		int numberOfCars = gDrawer.getMaximumNumberOfCars();
		carScrollBar.setMaximum(numberOfCars);
		carScrollBar.setValue(numberOfCars/2);
		this.numberLabel.setText(""+gDrawer.getCarListSize());
		this.updateAvgSpeedOfCars();
		carScrollBar.addAdjustmentListener(new AdjustmentListener() {
		
			public void adjustmentValueChanged(AdjustmentEvent e) {
				
				SimulationView.this.gDrawer.updateCars(carScrollBar.getValue());
				
			}
		});
		
		motionScrollBar.addAdjustmentListener(new AdjustmentListener() {
			
			public void adjustmentValueChanged(AdjustmentEvent e) {
				
				gDrawer.setDelay(motionScrollBar.getValue());
			}
		});
		playButton.addActionListener(new PlayListener(gDrawer));
		pauseButton.addActionListener(new PauseListener(gDrawer));
		stopButton.addActionListener(new StopListener(gDrawer));
		refreshButton.addActionListener(new StepListener(gDrawer));
		this.scrollPane = new JScrollPane(gDrawer);
		this.add(this.scrollPane, BorderLayout.CENTER);
		this.revalidate();
		this.validate();
	
	}
	
		public void updateNumberOfCars(){
			SimulationView.this.numberLabel.setText(""+gDrawer.getCarListSize());
		}
		
		public void updateAvgSpeedOfCars(){
			SimulationView.this.speedLabel.setText(""+gDrawer.avgSpeed()+" M/S");
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
		
		private GraphicsDrawer gDrawer;
		
		public PlayListener (GraphicsDrawer gDrawer){
			this.gDrawer = gDrawer;
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			if(!mapName.equals("")){
				simulationTimer.start();
				gDrawer.start();
			}
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
		
		private GraphicsDrawer gDrawer;
		
		public PauseListener(GraphicsDrawer gDrawer){
			this.gDrawer = gDrawer;
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			if(!mapName.equals("")){
				simulationTimer.stop();
				gDrawer.pause();
			}
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
		
		private GraphicsDrawer gDrawer;
		
		public StopListener(GraphicsDrawer gDrawer){
			this.gDrawer = gDrawer;
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			if(!mapName.equals("")){
				simulationTimer.stop();
				this.gDrawer.stop();
				simulationTimer.stop();

				centiseconds = 0;
            seconds = 0;
            minutes = 0;

            displayTimer.setText(timeFormatter.format(minutes) + ":"
                    + timeFormatter.format(seconds) + "."
                    + timeFormatter.format(centiseconds));
            loadMap(mapName);
			}
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
	public class StepListener implements ActionListener{
		
		private GraphicsDrawer gDrawer;
		
		public StepListener(GraphicsDrawer gDrawer){
			this.gDrawer = gDrawer;
		}
		
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			if(!mapName.equals("")){
				this.gDrawer.stepUpdate();
			}
		}
	}
	
	
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

