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
	
	private JMenuBar menuBar = new JMenuBar();
	
	private EditorState editorState;
	
	private GridButtonMouseListener gridButtonMouseListener;
	
	/**
	 * Instantiates a new road editor view.
	 *
	 * @param frame
	 *            the frame
	 */
	public RoadEditorView(ApplicationFrame frame){
		editorState = new EditorState();
		
		this.frame = frame;
		ImagesBuilder ib = new ImagesBuilder();
		this.setLayout(new BorderLayout());
		
		JMenu fileMenu = new JMenu("File");
	    menuBar.add(fileMenu);
	    JMenu editMenu = new JMenu("Edit");
	    menuBar.add(editMenu);
	    
	    frame.setJMenuBar(menuBar);
	    
	    JMenuItem newMap = new JMenuItem("New");
	    JMenuItem openMap = new JMenuItem("Open");
	    openMap.addActionListener(new OpenListener());
	    JMenuItem exitMainMenu = new JMenuItem("Main Menu");
	    JMenuItem validateMap = new JMenuItem("Validate Map");
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
        editMenu.add(validateMap);
    
        
		JPanel gridPanel = new JPanel( new GridBagLayout());
		gridPanel.setBackground(Color.GRAY);
		gridPanel.setSize(new Dimension(this.gridWidth*GraphicsConfig.BLOCK_SIDE_SIZE,this.gridHeight*GraphicsConfig.BLOCK_SIDE_SIZE));
		this.scrollPane = new JScrollPane(gridPanel);
		
		
		//****************  TEST use TODO user chose option 1. create new map 2. edit existing map
		
		GridBuilder gridBuilder = (GridBuilder) (FileRW.readObject(MainConfig.GRID_PATH + "/"+this.roadInfrastructureName+MainConfig.GRID_SUFFIX));
		
		//***************************
		validateMap.addActionListener(new ValidateMapListener());
		
		System.out.println("ismapvalid" +isMapValid(gridBuilder.getGrid()));
		
		Component [][] componentGrid = GridButtonsLoader.getGridButtons(gridBuilder, ib);
		this.gridButtonMouseListener = new GridButtonMouseListener(gridBuilder,componentGrid, ib, this.editorState, gridPanel);
		
		for(int i=0;i<componentGrid.length;i++){
			for (int j=0;j<componentGrid[0].length;j++){
				componentGrid[i][j].addMouseListener(gridButtonMouseListener);
				componentGrid[i][j].setCursor(CursorManager.customCursor(ib.getStandartCursor()));
			}
		}
		
		RoadEditorView.drawButtons(gridBuilder, gridPanel,componentGrid);
		
		JToolBar toolbar = new JToolBar(null, JToolBar.VERTICAL);
		toolbar.setFloatable(false);
		this.add(toolbar, BorderLayout.EAST);
		this.add(this.scrollPane, BorderLayout.CENTER);
		
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
			
}
	public boolean isMapValid(short[][] map){
		
		/**
		 * implement rules by reading the array gridBuilder.
		 *  if [i+1][j]!=0 and [i-1][j]!=0 then it is intersection
		 *  if [i+1][j]!=0 and (i!=0 and [i-1][j]==0) and [i][j]!=17 and [i][j]!=18 and [i][j]!= 4 then it is starting point
		 *  
		 */
		boolean isValid =true;
		int blockSize;
		try{
		for(int i=0; i<map.length;i++){
			//System.out.println("first i =" + i + " map length = " +map.length);
			for(int j=0;j<map[i].length;j++){
				if(map[i][j]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK || map[i][j]==RoadConfig.VERTICAL_DOUBLE_BLOCK)
					blockSize = 2;
				else
					blockSize = 1;
				
				//System.out.println("first j =" + j + " map length i= " + map[i].length + "  this block is: " + map[i][j]);
				//if(j==0  i-1>0 )
				if((i+blockSize!=map.length) && (map[i][j]!=-100 && map[i][j]!=0) && ((i==0 && j-1>0 && map[i][j-1]==0) || (j==0 && i-1>0 && map[i-1][j]==0))&& 
				(map[i][j]!=RoadConfig.INTERSECTION_BLOCK && map[i][j]!=RoadConfig.INTERSECTION_DOUBLE_BLOCK && 
				map[i][j]!=RoadConfig.INTERSECTION_MIXED_BLOCK && map[i][j]!=RoadConfig.INTERSECTION_BLOCK && 
				map[i][j]!=RoadConfig.ROUND_ABOUT_BLOCK)){
					System.out.println("i = " +i + " and j = " +j);
					System.out.println("Roadblock type before= " + map[i][j]);
				//Convert current block to an entry point.
					if(map[i][j]==RoadConfig.HORIZONTAL_BLOCK)
						map[i][j]=RoadConfig.HORIZONTAL_ENTER_BLOCK;
					else if(map[i][j]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK)
						map[i][j]=RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK;
					else if(map[i][j]==RoadConfig.VERTICAL_BLOCK)
						map[i][j]=RoadConfig.VERTICAL_ENTER_BLOCK;
					else if(map[i][j]==RoadConfig.VERTICAL_DOUBLE_BLOCK)
						map[i][j]=RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK;
					
					System.out.println("Roadblock type after= " + map[i][j]);
					
				}
				if((map[i][j]!=-100 && map[i][j]!=0) && ((i>1 ) && (j>1)) &&  //if statement to identify exit points. 
				//validations to ensure that the current block is not a special block that cannot be an entry or an exit point
				(map[i][j]!=RoadConfig.INTERSECTION_BLOCK && map[i][j]!=RoadConfig.INTERSECTION_DOUBLE_BLOCK && 
				map[i][j]!=RoadConfig.INTERSECTION_MIXED_BLOCK && map[i][j]!=RoadConfig.INTERSECTION_BLOCK && 
				map[i][j]!=RoadConfig.ROUND_ABOUT_BLOCK && 
				//validations to ensure that the current block is not an entry point
				map[i][j]!=RoadConfig.HORIZONTAL_ENTER_BLOCK && 
				map[i][j]!=RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK && map[i][j]!=RoadConfig.VERTICAL_ENTER_BLOCK &&
				map[i][j]!=RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK && 
				//validations to ensure that the previous block(both up or left of the current block is not an entry point
				map[i][j-blockSize]!=RoadConfig.HORIZONTAL_ENTER_BLOCK && 
				map[i][j-blockSize]!=RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK && map[i][j-blockSize]!=RoadConfig.VERTICAL_ENTER_BLOCK &&
				map[i][j-blockSize]!=RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK && map[i-blockSize][j]!=RoadConfig.HORIZONTAL_ENTER_BLOCK && 
				map[i-blockSize][j]!=RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK && map[i-blockSize][j]!=RoadConfig.VERTICAL_ENTER_BLOCK &&
				map[i-blockSize][j]!=RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK) &&
				//validations to ensure that this is the last block is not a street.
				((i+blockSize==map.length && (j+blockSize<=map.length && (map[i][j+blockSize]==0 || map[i][j]!=map[i][j+blockSize]))) || 
				  (j+blockSize==map.length && (i+blockSize<=map.length && (map[i+blockSize][j]==0 || map[i][j]!=map[i+blockSize][j]))))){
					
					//
				//convert current block to an exit point.
					System.out.println("i = " + i + " and j = " + j + " Roadblock type before= " + map[i][j]);

					if(map[i][j]==RoadConfig.HORIZONTAL_BLOCK)
						map[i][j]=RoadConfig.HORIZONTAL_EXIT_BLOCK;
					else if(map[i][j]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK)
						map[i][j]=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK;
					else if(map[i][j]==RoadConfig.VERTICAL_BLOCK)
						map[i][j]=RoadConfig.VERTICAL_EXIT_BLOCK;
					else if(map[i][j]==RoadConfig.VERTICAL_DOUBLE_BLOCK)
						map[i][j]=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK;
					
					System.out.println("Roadblock type after= " + map[i][j]);
					break;

				}
			}
			}
		}catch(Exception e){
			System.out.println("crashed\n\n" + e);
			e.printStackTrace();
			return false;
		}
		
		return isValid;
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
			frame.validate();
			JComponent component = frame.getJMenuBar();
			component.removeAll();
			frame.addView(new MainView(frame));
		}
		
		
	}
public class ValidateMapListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			
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
	
	public static void drawButtons(GridBuilder gridBuilder, JPanel gridPanel, Component [] [] componentGrid){
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		for (int i=0; i<gridBuilder.getGrid().length; i++){
			for (int j=0; j<gridBuilder.getGrid()[0].length; j++){
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
	}
}
