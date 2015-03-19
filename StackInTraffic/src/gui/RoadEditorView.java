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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import main.Main;
import main.MainConfig;
import trafficInfrastructure.RoadBuilder;
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
	//private	int gridHeight =GraphicsConfig.GRID_HEIGHT;
	
	/** The grid width. */
	//private int gridWidth = GraphicsConfig.GRID_WIDTH;
	
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
	
	/** The grid builder. */
	private GridBuilder gridBuilder;
	
	/** The map name. */
	private String mapName;
	
	/** The grid panel. */
	private JPanel gridPanel;
	
	/** The map name label. */
	private JLabel mapNameLabel = new JLabel("", SwingConstants.CENTER);
	
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
	    menuBar.add(Box.createRigidArea(new Dimension(50,0)));
	    menuBar.add(this.mapNameLabel);
	    
	    this. frame.setJMenuBar(menuBar);
	    
	    JMenuItem newMap = new JMenuItem("New");
	    newMap.addActionListener(new CreateListener());
	    JMenuItem openMap = new JMenuItem("Open");
	    openMap.addActionListener(new OpenListener());
	    JMenuItem exitMainMenu = new JMenuItem("Main Menu");
	    exitMainMenu.addActionListener(new MainMenuListener());
	    JMenuItem openSimulation = new JMenuItem("Simulation");
	    openSimulation.addActionListener(new SimulationListener());
	    JMenuItem exit = new JMenuItem("Exit");
	    exit.addActionListener(new ExitListener());
        JMenuItem saveMap = new JMenuItem("Save");
        saveMap.addActionListener(new SaveListener());
        JMenuItem deleteMap = new JMenuItem("Delete");
        deleteMap.addActionListener(new DeleteListener());
        JMenuItem clearMap = new JMenuItem("Clear");
        clearMap.addActionListener(new ClearListener());
        
        fileMenu.add(newMap);
        fileMenu.add(openMap);
        fileMenu.addSeparator();
        fileMenu.add(exitMainMenu);
        fileMenu.add(openSimulation);
        fileMenu.add(exit);
        editMenu.add(saveMap);
        editMenu.add(clearMap);
        editMenu.add(deleteMap);
    
        RoadEditorPopUpView launchPopUp = new RoadEditorPopUpView(this.frame, this ,"Road Editor");
	}	
	
	/**
	 * Sets the grid builder.
	 *
	 * @param gridBuilder
	 *            the new grid builder
	 */
	public void setGridBuilder(GridBuilder gridBuilder){
		this.gridBuilder = gridBuilder;
	}
	
	/**
	 * Gets the grid builder.
	 *
	 * @return the grid builder
	 */
	public GridBuilder getGridBuilder(){
		return this.gridBuilder;
	}
	
	/**
	 * Sets the map name.
	 *
	 * @param mapName
	 *            the new map name
	 */
	public void setMapName(String mapName){
		this.mapName = mapName;
		this.mapNameLabel.setText("Map Name: "+mapName);
		this.mapNameLabel.setForeground(Color.DARK_GRAY);
	}
	
	/**
	 * Gets the map name.
	 *
	 * @return the map name
	 */
	public String getMapName(){
		return this.mapName;
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
			MapChoiceView openMap = new MapChoiceView(frame,RoadEditorView.this, RoadEditorView.this.gridBuilder);
		}
	}
	
	/**
	 * The listener interface for receiving create events. The class that is
	 * interested in processing a create event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addCreateListener<code> method. When
	 * the create event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see CreateEvent
	 */
	public class CreateListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			MapCreateView createMap = new MapCreateView(frame, RoadEditorView.this,RoadEditorView.this.getGridBuilder());
			
		}
		
	}
	
	/**
	 * The listener interface for receiving simulation events. The class that is
	 * interested in processing a simulation event implements this interface,
	 * and the object created with that class is registered with a component
	 * using the component's <code>addSimulationListener<code> method. When
	 * the simulation event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SimulationEvent
	 */
	public class SimulationListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.main(null);
		}
		
	}
	
	/**
	 * The listener interface for receiving clear events. The class that is
	 * interested in processing a clear event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addClearListener<code> method. When
	 * the clear event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ClearEvent
	 */
	public class ClearListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			editorState = new EditorState();
			for (int i=0; i<gridBuilder.getGrid().length;i++){
				for (int j=0; j<gridBuilder.getGrid()[0].length;j++){
					gridBuilder.getGrid()[i][j]=0;
					((JButton)componentGrid[i][j]).setIcon(null);//new GridButton(i,j,GraphicsConfig.BLOCK_SIDE_SIZE);
					((JButton)componentGrid[i][j]).setPreferredSize(new Dimension(GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
				}
			}
			RoadEditorView.drawButtons(gridBuilder, gridPanel, componentGrid);
			RoadEditorView.this.repaint();
			RoadEditorView.this.validate();
		}
		
		
	}
	
	/**
	 * The listener interface for receiving delete events. The class that is
	 * interested in processing a delete event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addDeleteListener<code> method. When
	 * the delete event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see DeleteEvent
	 */
	public class DeleteListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList <String> mapNameList = (ArrayList<String>) FileRW.readObject(MainConfig.SAVES_FILE_PATH);
			if(!RoadEditorView.this.mapName.isEmpty() && !mapNameList.isEmpty()){
				int temp = -1;
				for (int i = 0 ; i < mapNameList.size(); i++){
					if (mapNameList.get(i).equals(mapName)){
						temp = i;
					}
					
				}
				if (temp>-1){
					mapNameList.remove(temp);
					FileRW.writeObject(mapNameList, MainConfig.SAVES_FILE_PATH);
					System.out.println("Road Editor: Delete operation. "+mapName+" has been deleted successfully");
					
					}
				else{
					System.out.println("Road Editor: Delete operation. Unsuccessfull, map doesn't exist in saves");
				}
				
				
			}else {
				System.out.println("Road Editor: Delete operation. Unsuccessfull, map is not loaded or saves list is empty");
			}
			mapName=null;
			frame.removeView();
			JComponent component = frame.getJMenuBar();
			component.removeAll();
			frame.validate();
			frame.addView(new RoadEditorView(frame));
		}	
	}
	
	/**
	 * The listener interface for receiving save events. The class that is
	 * interested in processing a save event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addSaveListener<code> method. When
	 * the save event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SaveEvent
	 */
	public class SaveListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean isValid = MapValidator.isMapValid(gridBuilder.getGrid());
			
			if (isValid){
				System.out.println("Map is Valid");
				ArrayList <String> mapNameList = (ArrayList<String>) FileRW.readObject(MainConfig.SAVES_FILE_PATH);
				if (!mapName.isEmpty()){
					int isEdition = -1;
					for(String mapNameTemp: mapNameList){
						if(mapNameTemp.equals(mapName)){
							isEdition = 1;
						}
					}
					if (isEdition<0){
						mapNameList.add(mapName);
						FileRW.writeObject(mapNameList, MainConfig.SAVES_FILE_PATH);
					}
				
				
					FileRW.writeObject(gridBuilder, MainConfig.GRID_PATH+"/"+mapName+MainConfig.GRID_SUFFIX);
					RoadBuilder roadBuilder = new RoadBuilder(mapName, gridBuilder);
					roadBuilder.buildRoad();
					new AlertMessageView("Map passed validation and was saved");
					for (int j=0; j<gridBuilder.getGrid().length; j++){
						for (int i=0; i<gridBuilder.getGrid()[0].length; i++){
							System.out.print(gridBuilder.getGrid()[j][i]+"\t");
						}
						System.out.print("\n");
					}
					MapValidator.deleteEndPoints(gridBuilder);
					for (int j=0; j<gridBuilder.getGrid().length; j++){
						for (int i=0; i<gridBuilder.getGrid()[0].length; i++){
							System.out.print(gridBuilder.getGrid()[j][i]+"\t");
						}
						System.out.print("\n");
					}
				}
			} else {
				System.out.println("Map is Not Valid");
				for (int j=0; j<gridBuilder.getGrid().length; j++){
					for (int i=0; i<gridBuilder.getGrid()[0].length; i++){
						System.out.print(gridBuilder.getGrid()[j][i]+"\t");
					}
					System.out.print("\n");
				}
				new AlertMessageView("Map didn't pass validation and wasn't saved");
				MapValidator.deleteEndPoints(gridBuilder);
			}
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
		
			for (int j=0; j<componentGrid.length; j++){
				for (int i=0; i<componentGrid[0].length; i++){
				
				if (gridBuilder.getGrid()[j][i]==RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
					gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
					gridBagConstraints.gridx = j;
					gridBagConstraints.gridy = i;
					gridBagConstraints.gridheight = 1;
					gridBagConstraints.gridwidth = 2;
					gridPanel.add(componentGrid[j][i],gridBagConstraints);
						
				} else
				
				if (gridBuilder.getGrid()[j][i]==RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
					gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
					gridBagConstraints.gridx = j;
					gridBagConstraints.gridy = i;
					gridBagConstraints.gridheight = 2;
					gridBagConstraints.gridwidth = 1;
					gridPanel.add(componentGrid[j][i],gridBagConstraints);
							
				} else
					
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
	 */
	public void buildGrid(){
		gridPanel = new JPanel( new GridBagLayout());
		gridPanel.setBackground(Color.GRAY);
		gridPanel.setSize(new Dimension(gridBuilder.getGrid().length*GraphicsConfig.BLOCK_SIDE_SIZE,gridBuilder.getGrid()[0].length*GraphicsConfig.BLOCK_SIDE_SIZE));
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
		
		JScrollPane toolBarScroll = new JScrollPane(toolbar);
		this.add(toolBarScroll, BorderLayout.EAST);
		
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
	        
	        button= new JButton("Mixed Horizontal Intersection",new ImageIcon(ib.getMixedIntersectionHTb()));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK, ib, componentGrid,this.editorState);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        button.setIconTextGap(30);
	        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
	        button.addMouseListener(toolBarButtonMouseListener);
	        button.setCursor(CursorManager.handCursor());
	        toolbar.add(button);
	        
	        button= new JButton("Mixed Vertical Intersection",new ImageIcon(ib.getMixedIntersectionVTb()));
	        toolBarButtonMouseListener = new ConstructionToolBarMouseListener(RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK, ib, componentGrid,this.editorState);
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
