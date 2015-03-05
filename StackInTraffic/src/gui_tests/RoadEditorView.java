/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package gui_tests;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;
import graphicsLoader.ImagesSelector;
import graphicsLoader.RoadEditorBuilder.CursorManager;
import graphicsLoader.RoadEditorBuilder.GridButtonsLoader;
import graphicsLoader.RoadEditorBuilder.ToolBarButtonMouseListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import main.MainConfig;
import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.RoadConfig;
import util.FileRW;

// TODO: Auto-generated Javadoc
/**
 * The Class RoadEditorView.
 */
public class RoadEditorView extends JPanel {
	
	/** The frame. */
	private ApplicationFrame frame;
	
	/** The road infrastructure name. */
	private String roadInfrastructureName = "test";
	
	/** The scroll pane. */
	private JScrollPane scrollPane ;
	
	//TODO Input parametres of GRID size 1. Existing grid 2. Define parameters for new grid
	/** The grid height. */
	private	int gridHeight =GraphicsConfig.GRID_HEIGHT;
	
	/** The grid width. */
	private int gridWidth = GraphicsConfig.GRID_WIDTH;
	
	
	/**
	 * Instantiates a new road editor view.
	 *
	 * @param frame
	 *            the frame
	 */
	public RoadEditorView(ApplicationFrame frame){
		this.frame = frame;
		ImagesBuilder ib = new ImagesBuilder();
		this.setLayout(new BorderLayout());
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
	    menuBar.add(fileMenu);
	    JMenu editMenu = new JMenu("Edit");
	    menuBar.add(editMenu);
	    
	    JMenuItem newMap = new JMenuItem("New");
	    JMenuItem openMap = new JMenuItem("Open");
	    JMenuItem exitMainMenu = new JMenuItem("Main Menu");
	    JMenuItem exit = new JMenuItem("Exit");
		/*JButton mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(new MainMenuListener());
		menubar.add(mainMenu);*/
		
		
		JPanel gridPanel = new JPanel( new GridBagLayout());
		gridPanel.setBackground(Color.GRAY);
		gridPanel.setSize(new Dimension(this.gridWidth*GraphicsConfig.BLOCK_SIDE_SIZE,this.gridHeight*GraphicsConfig.BLOCK_SIDE_SIZE));
		this.scrollPane = new JScrollPane(gridPanel);
		GridBuilder gridBuilder = (GridBuilder) (FileRW.readObject(MainConfig.GRID_PATH + "/"+this.roadInfrastructureName+MainConfig.GRID_SUFFIX));
		Component [][] componentGrid = GridButtonsLoader.getGridButtons(gridBuilder.getGrid(), ib);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		for (int i=0; i<this.gridWidth; i++){
			for (int j=0; j<this.gridHeight; j++){
				if (gridBuilder.getGrid()[j][i]>10){
					gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
					gridBagConstraints.gridx = j;
					gridBagConstraints.gridy = i;
					gridBagConstraints.gridheight = 2;
					gridBagConstraints.gridwidth = 2;
					gridPanel.add(componentGrid[j][i],gridBagConstraints);
					
				} 
				else {
					gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
					gridBagConstraints.gridx = j;
					gridBagConstraints.gridy = i;
					gridBagConstraints.gridheight = 1;
					gridBagConstraints.gridwidth = 1;
					gridPanel.add(componentGrid[j][i],gridBagConstraints);
				}
			}
		}
		JToolBar toolbar = new JToolBar(null, JToolBar.VERTICAL);
		toolbar.setFloatable(false);
		this.add(toolbar, BorderLayout.EAST);
		this.add(this.scrollPane, BorderLayout.CENTER);
		
		JButton button = null;
		
			button= new JButton("Horizontal Road Block", new ImageIcon(ImagesSelector.selectRoadImageSc(RoadConfig.HORIZONTAL_BLOCK, ib)));
			ToolBarButtonMouseListener toolBarButtonMouseListener = new ToolBarButtonMouseListener(RoadConfig.HORIZONTAL_BLOCK, ib, componentGrid);
			button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
		
	        button= new JButton("Vertical Road Block", new ImageIcon(ImagesSelector.selectRoadImageSc(RoadConfig.VERTICAL_BLOCK, ib)));
	        toolBarButtonMouseListener = new ToolBarButtonMouseListener(RoadConfig.VERTICAL_BLOCK, ib, componentGrid);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
        
	        button= new JButton("Intersection Road Block", new ImageIcon(ImagesSelector.selectRoadImageSc(RoadConfig.INTERSECTION_BLOCK, ib)));
	        toolBarButtonMouseListener = new ToolBarButtonMouseListener(RoadConfig.INTERSECTION_BLOCK, ib, componentGrid);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        button= new JButton("Horizontal 2xlane Block", new ImageIcon(ImagesSelector.selectRoadImageTb(RoadConfig.HORIZONTAL_DOUBLE_BLOCK, ib)));
	        toolBarButtonMouseListener = new ToolBarButtonMouseListener(RoadConfig.HORIZONTAL_DOUBLE_BLOCK, ib, componentGrid);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        button= new JButton("Vertical 2xlane Block", new ImageIcon(ImagesSelector.selectRoadImageTb(RoadConfig.VERTICAL_DOUBLE_BLOCK, ib)));
	        toolBarButtonMouseListener = new ToolBarButtonMouseListener(RoadConfig.VERTICAL_DOUBLE_BLOCK, ib, componentGrid);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	
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
}
