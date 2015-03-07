/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package gui;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;
import graphicsLoader.ImagesSelector;
import graphicsLoader.RoadEditorBuilder.ActionToolBarMouseListener;
import graphicsLoader.RoadEditorBuilder.ConstructionToolBarMouseListener;
import graphicsLoader.RoadEditorBuilder.CursorManager;
import graphicsLoader.RoadEditorBuilder.EditorState;
import graphicsLoader.RoadEditorBuilder.GridButtonMouseListener;
import graphicsLoader.RoadEditorBuilder.GridButtonsLoader;
import graphicsLoader.RoadEditorBuilder.RoadEditorConfig;

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
import javax.swing.JComponent;
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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7107451858595215658L;

	/** The frame. */
	private ApplicationFrame frame;
	
	/** The road infrastructure name. */
	//private String roadInfrastructureName = "test";
	
	/** The scroll pane. */
	private JScrollPane scrollPane ;
	
	//TODO Input parametres of GRID size 1. Existing grid 2. Define parameters for new grid
	/** The grid height. */
	private	int gridHeight =GraphicsConfig.GRID_HEIGHT;
	
	/** The grid width. */
	private int gridWidth = GraphicsConfig.GRID_WIDTH;
	
	/** The menu bar. */
	private JMenuBar menuBar = new JMenuBar();
	
	/** The editor state. */
	private EditorState editorState;
	
	/** The grid button mouse listener. */
	private GridButtonMouseListener gridButtonMouseListener;
	
	/** The ib. */
	private ImagesBuilder ib;
	
	/** The component grid. */
	private Component [][] componentGrid;
	
	/**
	 * Instantiates a new road editor view.
	 *
	 * @param frame
	 *            the frame
	 */
	public RoadEditorView(ApplicationFrame frame){
		
		
		this.frame = frame;
		this.ib = new ImagesBuilder();
		this.setLayout(new BorderLayout());
		
		JMenu fileMenu = new JMenu("File");
	    menuBar.add(fileMenu);
	    JMenu editMenu = new JMenu("Edit");
	    menuBar.add(editMenu);
	    
	    this. frame.setJMenuBar(menuBar);
	    
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
    
        
	}	
		
		
		//****************  TEST use TODO user chose option 1. create new map 2. edit existing map
		
		
		
		
		
		
		
	
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
			frame.validate();
			JComponent component = frame.getJMenuBar();
			component.removeAll();
			frame.addView(new MainView(frame));
		}
		
		
	}
	
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
	 * The listener interface for receiving open events. The class that is
	 * interested in processing a open event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addOpenListener<code> method. When
	 * the open event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see OpenEvent
	 */
	public class OpenListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			MapChoiceView openMap = new MapChoiceView(RoadEditorView.this);
		}
	}
	
	/**
	 * Draw buttons.
	 *
	 * @param gridBuilder
	 *            the grid builder
	 * @param gridPanel
	 *            the grid panel
	 * @param componentGrid
	 *            the component grid
	 */
	public static void drawButtons(GridBuilder gridBuilder, JPanel gridPanel, Component [] [] componentGrid){
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		for (int i=0; i<gridBuilder.getGrid().length; i++){
			for (int j=0; j<gridBuilder.getGrid()[0].length; j++){
				if (gridBuilder.getGrid()[j][i]>30){
					gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
					gridBagConstraints.gridx = j;
					gridBagConstraints.gridy = i;
					gridBagConstraints.gridheight = 3;
					gridBagConstraints.gridwidth = 3;
					gridPanel.add(componentGrid[j][i],gridBagConstraints);
					
				} 
				else if (gridBuilder.getGrid()[j][i]>10){
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
	}
	
	/**
	 * Builds the grid.
	 *
	 * @param gridBuilder
	 *            the grid builder
	 */
	public void buildGrid(GridBuilder gridBuilder){
		JPanel gridPanel = new JPanel( new GridBagLayout());
		gridPanel.setBackground(Color.GRAY);
		gridPanel.setSize(new Dimension(this.gridWidth*GraphicsConfig.BLOCK_SIDE_SIZE,this.gridHeight*GraphicsConfig.BLOCK_SIDE_SIZE));
		this.scrollPane = new JScrollPane(gridPanel);
		this.componentGrid = GridButtonsLoader.getGridButtons(gridBuilder, ib);
		this.gridButtonMouseListener = new GridButtonMouseListener(gridBuilder,componentGrid, ib, this.editorState, gridPanel);
		
		for(int i=0;i<componentGrid.length;i++){
			for (int j=0;j<componentGrid[0].length;j++){
				componentGrid[i][j].addMouseListener(gridButtonMouseListener);
				componentGrid[i][j].setCursor(CursorManager.customCursor(ib.getStandartCursor()));
			}
		}
		
		RoadEditorView.drawButtons(gridBuilder, gridPanel,componentGrid);
		this.add(this.scrollPane, BorderLayout.CENTER);
		JToolBar toolbar = new JToolBar(null, JToolBar.VERTICAL);
		toolbar.setFloatable(false);
		this.add(toolbar, BorderLayout.EAST);
		
		
		JButton button = null;
		
			button= new JButton("Horizontal Road Block", new ImageIcon(ImagesSelector.selectRoadImageSc(RoadConfig.HORIZONTAL_BLOCK, ib)));
			ConstructionToolBarMouseListener toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.HORIZONTAL_BLOCK, ib, componentGrid, this.editorState);
			button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
		
	        button= new JButton("Vertical Road Block", new ImageIcon(ImagesSelector.selectRoadImageSc(RoadConfig.VERTICAL_BLOCK, ib)));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.VERTICAL_BLOCK, ib, componentGrid, this.editorState);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
        
	        button= new JButton("Intersection Road Block", new ImageIcon(ImagesSelector.selectRoadImageSc(RoadConfig.INTERSECTION_BLOCK, ib)));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.INTERSECTION_BLOCK, ib, componentGrid,this.editorState);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        button= new JButton("Intersection T Block Up",new ImageIcon(ib.getIntersectionUpSc()));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.INTERSECTION_UP_BLOCK, ib, componentGrid,this.editorState);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        button= new JButton("Intersection T Block Down",new ImageIcon(ib.getIntersectionDownSc()));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.INTERSECTION_DOWN_BLOCK, ib, componentGrid,this.editorState);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        button= new JButton("Intersection T Block Left",new ImageIcon(ib.getIntersectionLeftSc()));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.INTERSECTION_LEFT_BLOCK, ib, componentGrid,this.editorState);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        button= new JButton("Intersection T Block Right",new ImageIcon(ib.getIntersectionRightSc()));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.INTERSECTION_RIGHT_BLOCK, ib, componentGrid,this.editorState);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        button= new JButton("Horizontal 2xlane Block", new ImageIcon(ImagesSelector.selectRoadImageTb(RoadConfig.HORIZONTAL_DOUBLE_BLOCK, ib)));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.HORIZONTAL_DOUBLE_BLOCK, ib, componentGrid, this.editorState);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        button= new JButton("Vertical 2xlane Block", new ImageIcon(ImagesSelector.selectRoadImageTb(RoadConfig.VERTICAL_DOUBLE_BLOCK, ib)));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.VERTICAL_DOUBLE_BLOCK, ib, componentGrid,this.editorState);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        button= new JButton("Intersection Double Block",new ImageIcon(ib.getIntersectionDoubleTb()));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.INTERSECTION_DOUBLE_BLOCK, ib, componentGrid,this.editorState);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        button= new JButton("RoundAbout",new ImageIcon(ib.getRoundAboutTb()));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.ROUND_ABOUT_BLOCK, ib, componentGrid,this.editorState);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        JToolBar actionToolBar = new JToolBar(null, JToolBar.VERTICAL);
	        actionToolBar.setFloatable(false);
			this.add(actionToolBar, BorderLayout.WEST);
			JButton actionButton = null;
			
			
			actionButton = new JButton("");//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
			ImageIcon background = new ImageIcon( ib.getStandartCursor()); 
			actionButton.setIcon(background);
			actionButton.setLayout(null);
			actionButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5,5));
			actionButton.setCursor(CursorManager.handCursor());
			actionButton.setSize(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE);
			actionButton.setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
			ActionToolBarMouseListener actionToolBarML = new ActionToolBarMouseListener(RoadEditorConfig.ACTION_NULL, ib ,componentGrid, this.editorState);
			actionButton.addMouseListener(actionToolBarML);
			actionToolBar.add(actionButton);
			
			actionButton = new JButton();//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
			background = new ImageIcon( ib.getHandCursor()); 
			actionButton.setIcon(background);
			actionButton.setLayout(null);
			actionButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5,5));
			actionButton.setCursor(CursorManager.handCursor());
			actionButton.setSize(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE);
			actionButton.setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
			actionToolBarML = new ActionToolBarMouseListener(RoadEditorConfig.ACTION_MOVE, ib ,componentGrid, this.editorState);
			actionButton.addMouseListener(actionToolBarML);
			actionToolBar.add(actionButton);
			
			actionButton = new JButton();//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
			background = new ImageIcon( ib.getDeleteCursor()); 
			actionButton.setIcon(background);
			actionButton.setLayout(null);
			actionButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5,5));
			actionButton.setCursor(CursorManager.handCursor());
			actionButton.setSize(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE);
			actionButton.setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
			actionToolBarML = new ActionToolBarMouseListener(RoadEditorConfig.ACTION_DELETE, ib ,componentGrid, this.editorState);
			actionButton.addMouseListener(actionToolBarML);
			actionToolBar.add(actionButton);
			
		frame.validate();
		frame.repaint();
	}
	
	/**
	 * Update editor state.
	 */
	public void updateEditorState(){
		this.editorState = new EditorState();
	}
}
