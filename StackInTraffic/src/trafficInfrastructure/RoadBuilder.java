/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package trafficInfrastructure;

import graphicsLoader.GraphicsConfig;

import java.util.ArrayList;

import main.MainConfig;
import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.BlockGraphicPoint;
import trafficInfrastructure.road.HorizontalBlock;
import trafficInfrastructure.road.HorizontalDoubleBlock;
import trafficInfrastructure.road.IntersectionBlock;
import trafficInfrastructure.road.IntersectionDoubleBlock;
import trafficInfrastructure.road.IntersectionMixedHorizontalBlock;
import trafficInfrastructure.road.IntersectionMixedVerticalBlock;
import trafficInfrastructure.road.RoadConfig;
import trafficInfrastructure.road.RoundAboutBlockSingle;
import trafficInfrastructure.road.VerticalBlock;
import trafficInfrastructure.road.VerticalDoubleBlock;
import trafficInfrastructure.roadPath.DoublePath;
import trafficInfrastructure.roadPath.Path;
import trafficInfrastructure.roadPath.PathPoint;
import trafficInfrastructure.roadPath.PathSwitchingDifference;
import trafficInfrastructure.roadPath.VisitedIntersectionsOnPath;
import util.FileRW;

// TODO: Auto-generated Javadoc
/**
 * RoadBuilder is for to create and edit road infrastructure The result is saved
 * in Components directory.
 *
 * @author Maxim Vasilishin
 * @version 1.0
 */

public class RoadBuilder {
	
	/** The road inf name. */
	private String roadInfName;
	
	/** The road grid. */
	private GridBuilder roadGrid;
	
	/** The arr path. */
	private ArrayList<Path> arrPath;
	

	
	
	/**
	 * The current x1.
	 *
	 * @param roadInfName
	 *            the road inf name
	 * @param gridBuilder
	 *            the grid builder
	 */
	//private int currentX1;
	
	/** The current y1. */
	//private int currentY1;
	
	/** The current x2. */
	//private int currentX2 = 0; // for feature use
	
	/** The current y2. */
	//private int currentY2 = 0; // for feature use
	
	
	/**
	 * Instantiates a new road builder.
	 *
	 * @param roadInfName
	 *            the road inf name
	 * @param currentX
	 *            the current x
	 * @param currentY
	 *            the current y
	 * @param gridBuilder
	 *            the grid builder
	 */
	public RoadBuilder (String roadInfName, GridBuilder gridBuilder){
		this.roadInfName = roadInfName;
		//this.currentX1 = currentX;
		//this.currentY1 = currentY;
		this.roadGrid = gridBuilder;
		this.arrPath = new ArrayList<Path>();
	}
	
	/**
	 * Builds the road.
	 */
	public void buildRoad(){
		
		/*this.addRoadBlock(RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK);
		for (int i = 0; i < 6; i++){
			this.addRoadBlock(RoadConfig.VERTICAL_DOUBLE_BLOCK);
		}
		this.addRoadBlock(RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK);
		*/
		/*this.addRoadBlock(RoadConfig.VERTICAL_ENTER_BLOCK);
		for (int i = 0; i < 14; i++){
			this.addRoadBlock(RoadConfig.VERTICAL_BLOCK);
		}
		this.addRoadBlock(RoadConfig.VERTICAL_EXIT_BLOCK);
		
		this.currentX1 = 500;
		this.currentY1 = 0;
		
		this.addRoadBlock(RoadConfig.VERTICAL_ENTER_BLOCK);
		for (int i = 0; i < 14; i++){
			this.addRoadBlock(RoadConfig.VERTICAL_BLOCK);
		}
		this.addRoadBlock(RoadConfig.VERTICAL_EXIT_BLOCK);
		
		
		this.currentX1 = 0;
		this.currentY1 = 150;
		
		this.addRoadBlock(RoadConfig.HORIZONTAL_ENTER_BLOCK);
		for (int i = 0; i < 14; i++){
			this.addRoadBlock(RoadConfig.HORIZONTAL_BLOCK);
		}
		this.addRoadBlock(RoadConfig.HORIZONTAL_EXIT_BLOCK);
		
		this.currentX1 = 0;
		this.currentY1 = 300;
		
		this.addRoadBlock(RoadConfig.HORIZONTAL_ENTER_BLOCK);
		for (int i = 0; i < 14; i++){
			this.addRoadBlock(RoadConfig.HORIZONTAL_BLOCK);
		}
		this.addRoadBlock(RoadConfig.HORIZONTAL_EXIT_BLOCK);
		
		
		this.currentX1 = 0;
		this.currentY1 = 500;
		
		this.addRoadBlock(RoadConfig.HORIZONTAL_ENTER_BLOCK);
		for (int i = 0; i < 14; i++){
			this.addRoadBlock(RoadConfig.HORIZONTAL_BLOCK);
		}
		this.addRoadBlock(RoadConfig.HORIZONTAL_EXIT_BLOCK);
		/*this.currentX1 = 0;
		this.currentY1 = 500;
		
		this.addRoadBlock(RoadConfig.HORIZONTAL_ENTER_BLOCK);
		for (int i = 0; i < 14; i++){
			this.addRoadBlock(RoadConfig.HORIZONTAL_BLOCK);
		}
		this.addRoadBlock(RoadConfig.HORIZONTAL_EXIT_BLOCK);
		
		this.currentX1 = 0;
		this.currentY1 = 150;
		
		this.addRoadBlock(RoadConfig.HORIZONTAL_ENTER_BLOCK);
		for (int i = 0; i < 18; i++){
			this.addRoadBlock(RoadConfig.HORIZONTAL_BLOCK);
		}
		this.addRoadBlock(RoadConfig.HORIZONTAL_EXIT_BLOCK);
		*/
		
	for (int i = 0; i<this.roadGrid.getGrid().length; i++){
		for (int j = 0; j<this.roadGrid.getGrid()[0].length; j++){
			System.out.print(this.roadGrid.getGrid() [i] [j]);
		}
		System.out.print("\n");
	}
	//FileRW.writeObject(this.roadGrid, MainConfig.GRID_PATH + "/" + this.roadInfName + MainConfig.GRID_SUFFIX);
	this.roadGraphicBuilder();
	this.getPaths();	
	}
	
	/**
	 * Adds the road block.
	 */
	/*public void addRoadBlock (short blockNb){ 
		
		if (blockNb == RoadConfig.HORIZONTAL_ENTER_BLOCK || blockNb == RoadConfig.HORIZONTAL_BLOCK || blockNb == RoadConfig.HORIZONTAL_EXIT_BLOCK){
			if (this.roadGrid.getGrid()[this.currentX1/GraphicsConfig.BLOCK_SIDE_SIZE][this.currentY1/GraphicsConfig.BLOCK_SIDE_SIZE] == RoadConfig.VERTICAL_BLOCK){
				this.roadGrid.addRoadBlock(RoadConfig.INTERSECTION_BLOCK, this.currentX1, this.currentY1);
			} else {
				this.roadGrid.addRoadBlock(blockNb, this.currentX1, this.currentY1);
			}
			int tab [] = HorizontalBlock.nextBlockLocation(this.currentX1, this.currentY1, this.currentX2, this.currentY2);
			this.currentX1 = tab[0];
			this.currentY1 = tab[1];
		}
		
		if (blockNb == RoadConfig.VERTICAL_BLOCK || blockNb == RoadConfig.VERTICAL_ENTER_BLOCK || blockNb == RoadConfig.VERTICAL_EXIT_BLOCK){
			if (this.roadGrid.getGrid()[this.currentX1/GraphicsConfig.BLOCK_SIDE_SIZE][this.currentY1/GraphicsConfig.BLOCK_SIDE_SIZE] == RoadConfig.HORIZONTAL_BLOCK){
				this.roadGrid.addRoadBlock(RoadConfig.INTERSECTION_BLOCK, this.currentX1, this.currentY1);
			} else {
				this.roadGrid.addRoadBlock(blockNb, this.currentX1, this.currentY1);
			}
			int tab [] = VerticalBlock.nextBlockLocation(this.currentX1, this.currentY1, this.currentX2, this.currentY2);
			this.currentX1 = tab[0];
			this.currentY1 = tab[1];
		}
		
		if (blockNb == RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || blockNb == RoadConfig.HORIZONTAL_DOUBLE_BLOCK || blockNb == RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK){
			/*if (this.roadGrid.getGrid()[this.currentX1/GraphicsConfig.BLOCK_SIDE_SIZE][this.currentY1/GraphicsConfig.BLOCK_SIDE_SIZE] == RoadConfig.VERTICAL_BLOCK){
				this.roadGrid.addRoadBlock(RoadConfig.INTERSECTION_BLOCK, this.currentX1, this.currentY1);
			} else {*/
	/*			this.roadGrid.addRoadBlock(blockNb, this.currentX1, this.currentY1);
				this.roadGrid.addRoadBlock((short) -100, this.currentX1+50, this.currentY1);
				this.roadGrid.addRoadBlock((short) -100, this.currentX1+50, this.currentY1+50);
				this.roadGrid.addRoadBlock((short) -100, this.currentX1, this.currentY1+50);
			//}
			int tab [] = HorizontalDoubleBlock.nextBlockLocation(this.currentX1, this.currentY1, this.currentX2, this.currentY2);
			this.currentX1 = tab[0];
			this.currentY1 = tab[1];
		}
		
		if (blockNb == RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || blockNb == RoadConfig.VERTICAL_DOUBLE_BLOCK || blockNb == RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
			/*if (this.roadGrid.getGrid()[this.currentX1/GraphicsConfig.BLOCK_SIDE_SIZE][this.currentY1/GraphicsConfig.BLOCK_SIDE_SIZE] == RoadConfig.VERTICAL_BLOCK){
				this.roadGrid.addRoadBlock(RoadConfig.INTERSECTION_BLOCK, this.currentX1, this.currentY1);
			} else {*/
			/*	this.roadGrid.addRoadBlock(blockNb, this.currentX1, this.currentY1);
				this.roadGrid.addRoadBlock((short) -100, (this.currentX1+50), this.currentY1);
				this.roadGrid.addRoadBlock((short) -100, (this.currentX1+50), (this.currentY1+50));
				this.roadGrid.addRoadBlock((short) -100, this.currentX1, (this.currentY1+50));
			//}
			/*int tab [] = VerticalDoubleBlock.nextBlockLocation(this.currentX1, this.currentY1, this.currentX2, this.currentY2);
			this.currentX1 = tab[0];
			this.currentY1 = tab[1];
		}
	}*/
	
	/**
	 * Road graphic builder.
	 */
	public void roadGraphicBuilder(){
		ArrayList<BlockGraphicPoint> roadGB = new ArrayList<BlockGraphicPoint>();
		for (int i = 0; i<this.roadGrid.getGrid().length; i++){
			for (int j = 0; j<this.roadGrid.getGrid()[0].length; j++){
				if (this.roadGrid.getGrid()[i][j]!=0 && this.roadGrid.getGrid()[i][j]!=-100 && this.roadGrid.getGrid()[i][j]!=-200 && this.roadGrid.getGrid()[i][j]!=-300 && this.roadGrid.getGrid()[i][j]!=-400 && this.roadGrid.getGrid()[i][j]!=-500){
					if(this.roadGrid.getGrid()[i][j]==RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
						roadGB.add(new BlockGraphicPoint(i*GraphicsConfig.BLOCK_SIDE_SIZE, j*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[i][j], GraphicsConfig.BLOCK_SIDE_SIZE*2, GraphicsConfig.BLOCK_SIDE_SIZE));
					} else if(this.roadGrid.getGrid()[i][j]==RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
						roadGB.add(new BlockGraphicPoint(i*GraphicsConfig.BLOCK_SIDE_SIZE, j*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[i][j], GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE*2));
						}
					else if(this.roadGrid.getGrid()[i][j]>30){
						roadGB.add(new BlockGraphicPoint(i*GraphicsConfig.BLOCK_SIDE_SIZE, j*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[i][j], GraphicsConfig.BLOCK_SIDE_SIZE*3, GraphicsConfig.BLOCK_SIDE_SIZE*3));
					} else if(this.roadGrid.getGrid()[i][j]>10) {
						roadGB.add(new BlockGraphicPoint(i*GraphicsConfig.BLOCK_SIDE_SIZE, j*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[i][j], GraphicsConfig.BLOCK_SIDE_SIZE*2, GraphicsConfig.BLOCK_SIDE_SIZE*2));
					} else {
						roadGB.add(new BlockGraphicPoint(i*GraphicsConfig.BLOCK_SIDE_SIZE, j*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[i][j], GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE));
					}
				}
			}
		}
		FileRW.writeObject(roadGB, MainConfig.ROADBLOCK_PATH + "/" + roadInfName + MainConfig.ROADBLOCK_GRAPHICS_SUFFIX);
		
	}
	
	/**
	 * Gets the paths.
	 *
	 * @return the paths
	 */
	public void getPaths (){
		 ArrayList <BlockGraphicPoint> endPoints = new ArrayList <BlockGraphicPoint>();
		 for (int i = 0; i<this.roadGrid.getGrid().length; i++){
				for (int j = 0; j<this.roadGrid.getGrid()[0].length; j++){
					if (this.roadGrid.getGrid()[i][j]==RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadGrid.getGrid()[i][j]==RoadConfig.HORIZONTAL_EXIT_BLOCK || this.roadGrid.getGrid()[i][j]==RoadConfig.VERTICAL_ENTER_BLOCK || this.roadGrid.getGrid()[i][j]==RoadConfig.VERTICAL_EXIT_BLOCK ||
							this.roadGrid.getGrid()[i][j]==RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || this.roadGrid.getGrid()[i][j]==RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK || this.roadGrid.getGrid()[i][j]==RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || this.roadGrid.getGrid()[i][j]==RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
						endPoints.add(new BlockGraphicPoint(i, j, this.roadGrid.getGrid()[i][j],GraphicsConfig.BLOCK_SIDE_SIZE,GraphicsConfig.BLOCK_SIDE_SIZE));
					}
				}
		 }
		 
		 for (BlockGraphicPoint blockGP : endPoints){
			 
			 ArrayList<PathPoint> arrPathPoint = new ArrayList<PathPoint>();
			 ArrayList<PathPoint> arrDoublePathPoint = new ArrayList<PathPoint>();
			 ArrayList<PathSwitchingDifference> arrPathSwitch = new ArrayList<PathSwitchingDifference>(); 
			 short direction = 0;
			 int x = blockGP.getX();
			 int y = blockGP.getY();
			 
			 int previousX = x;
			 int previousY = y; 
				
				if ( this.roadGrid.getGrid() [x] [y]== RoadConfig.HORIZONTAL_ENTER_BLOCK ){
					direction = 1;
					arrPathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
					
					x++;
				}
				
				if (this.roadGrid.getGrid() [x] [y] == RoadConfig.VERTICAL_ENTER_BLOCK){
					direction = 1;
					arrPathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
					
					y++;
				}
				
				if ( this.roadGrid.getGrid() [x] [y]== RoadConfig.HORIZONTAL_EXIT_BLOCK) {
					direction = -1;
					arrPathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],(short) -1));
					
					if (this.roadGrid.getGrid() [x-1] [y] == -400){
						x-=2;
					} else {
						x-=1;
					}
				}
				if (this.roadGrid.getGrid() [x] [y] == RoadConfig.VERTICAL_EXIT_BLOCK){
					direction = -1;
					arrPathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],(short) -1));
					
					if (this.roadGrid.getGrid() [x] [y-1] == -500){
						y-=2;
					} else {
						y-=1;
					}
				}
				
				if ( this.roadGrid.getGrid() [x] [y]== RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK ){
					direction = 1;
					arrPathPoint.addAll(HorizontalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
					
					x+=2;
				}
				
				if (this.roadGrid.getGrid() [x] [y] == RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK){
					direction = 1;
					arrPathPoint.addAll(VerticalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));

					y+=2;
				}
				
				if ( this.roadGrid.getGrid() [x] [y]== RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK) {
					direction = -1;
					
					arrPathPoint.addAll(HorizontalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) -1));
					
					if (this.roadGrid.getGrid() [x-1] [y] == RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
						x-=1;
					} else {
						x-=2;
					}
					
				}
				if (this.roadGrid.getGrid() [x] [y] == RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
					direction = -1;
					arrPathPoint.addAll(VerticalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],(short) -1));
					
					if (this.roadGrid.getGrid() [x] [y-1] == RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
						y-=1;
					} else {
						y-=2;
					}
				}
				arrDoublePathPoint.addAll(arrPathPoint);
				this.discoverPaths(arrPathPoint, arrDoublePathPoint, arrPathSwitch, new VisitedIntersectionsOnPath(), direction, x, y, previousX , previousY);
				
		 }
		 
		 FileRW.writeObject(this.arrPath, MainConfig.PATHS_PATH + "/" + this.roadInfName + MainConfig.PATH_SUFFIX);
	}
	
	/**
	 * Discover paths.
	 *
	 * @param arrPathPoint            the arr path point
	 * @param arrDoublePathPoint the arr double path point
	 * @param arrPathSwitch the arr path switch
	 * @param viop            the viop
	 * @param direction            the direction
	 * @param x            the x
	 * @param y            the y
	 * @param prevX            the prev x
	 * @param prevY            the prev y
	 */
	public void discoverPaths(ArrayList<PathPoint> arrPathPoint, ArrayList<PathPoint> arrDoublePathPoint, ArrayList<PathSwitchingDifference> arrPathSwitch,  VisitedIntersectionsOnPath viop, int direction, int x, int y ,int prevX,int prevY){
		int previousX = prevX;
		int previousY = prevY;
		while (this.roadGrid.getGrid() [x] [y]!= RoadConfig.HORIZONTAL_ENTER_BLOCK && this.roadGrid.getGrid() [x] [y] != RoadConfig.VERTICAL_ENTER_BLOCK && this.roadGrid.getGrid() [x] [y]!= RoadConfig.HORIZONTAL_EXIT_BLOCK && this.roadGrid.getGrid() [x] [y] != RoadConfig.VERTICAL_EXIT_BLOCK &&
				this.roadGrid.getGrid() [x] [y]!= RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK && this.roadGrid.getGrid() [x] [y] != RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK && this.roadGrid.getGrid() [x] [y]!= RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK && this.roadGrid.getGrid() [x] [y] != RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
			System.out.println(x + " " + y +" "+ this.roadGrid.getGrid() [x] [y]);
			
			
			if ( this.roadGrid.getGrid() [x] [y] == RoadConfig.HORIZONTAL_BLOCK){
				previousX = x;
				previousY = y;
				if (direction == 1){
					arrPathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
					arrDoublePathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
					x++;
					
					
	
				} else {
					arrPathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],(short) -1));
					arrDoublePathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],(short) -1));
					
					if(this.roadGrid.getGrid() [x-1] [y] == -400){
						x-=2;
					} else {
						x--;
					}
					
				}
			} else if (this.roadGrid.getGrid() [x] [y] == RoadConfig.VERTICAL_BLOCK){
				
				previousX = x;
				previousY = y;
				
				if (direction == 1){
					arrPathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
					arrDoublePathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
					y++;
					
					
					
					
				} else {
					arrPathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],(short) -1));
					arrDoublePathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],(short) -1));
					
					if(this.roadGrid.getGrid() [x] [y-1] == -500){
						y-=2;
					} else {
						y--;
					}
					
					
					
				}
			} 
			
			if ( this.roadGrid.getGrid() [x] [y] == RoadConfig.HORIZONTAL_DOUBLE_BLOCK){
				previousX = x;
				previousY = y;
				if (direction == 1){
					arrPathPoint.addAll(HorizontalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
					arrDoublePathPoint.addAll(HorizontalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
					
					x+=2;
					
					
	
				} else {
					arrPathPoint.addAll(HorizontalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) -1));
					arrDoublePathPoint.addAll(HorizontalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) -1));
					
					if(this.roadGrid.getGrid() [x-1] [y] == RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
						x-=1;
					} else {
						x-=2;
					}
					
				}
			} else if (this.roadGrid.getGrid() [x] [y] == RoadConfig.VERTICAL_DOUBLE_BLOCK){
				
				previousX = x;
				previousY = y;
				
				if (direction == 1){
					arrPathPoint.addAll(VerticalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
					arrDoublePathPoint.addAll(VerticalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
					
					y+=2;
					
					
					
					
				} else {
					arrPathPoint.addAll(VerticalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],(short) -1));
					arrDoublePathPoint.addAll(VerticalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],(short) -1));
					
					if(this.roadGrid.getGrid() [x] [y-1] == RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
						y-=1;
						
					} else {
						y-=2;
						
					}
					
					
					
				}
			} 
			
			else if (this.roadGrid.getGrid() [x][y] == RoadConfig.INTERSECTION_BLOCK){
				if (viop.isVisited(x, y)){
					break;
				}
				else {
					viop.addIntersection(x, y);
				}
				System.out.println("intersection detected");
				if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_EXIT_BLOCK){
					
					previousX = x;
					previousY = y;
					
					if (direction == 1){
						ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
						arr1.addAll(arrPathPoint);
						arr1.addAll(IntersectionBlock.getLeftToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
						ArrayList<PathPoint> arr2 = new ArrayList<PathPoint>();
						arr2.addAll(arrPathPoint);
						arr2.addAll(IntersectionBlock.getLeftToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
						
						ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
						arrDouble1.addAll(arrDoublePathPoint);
						arrDouble1.addAll(IntersectionBlock.getLeftToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
						ArrayList<PathPoint> arrDouble2 = new ArrayList<PathPoint>();
						arrDouble2.addAll(arrDoublePathPoint);
						arrDouble2.addAll(IntersectionBlock.getLeftToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
						
						
						VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
						viop1.addAllIntersections(viop.getIntersections());
						
						VisitedIntersectionsOnPath viop2 = new VisitedIntersectionsOnPath();
						viop2.addAllIntersections(viop.getIntersections());
						
						this.discoverPaths(arr1,arrDouble1,arrPathSwitch,viop1, 1, x, y+1, previousX , previousY);
						this.discoverPaths(arr2,arrDouble2,arrPathSwitch,viop2, -1, x, y-1, previousX , previousY);
						arrPathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
						arrDoublePathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
						
						x++;
						
						
						
					}
					
					else {
						ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
						arr1.addAll(arrPathPoint);
						arr1.addAll(IntersectionBlock.getRightToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						ArrayList<PathPoint> arr2 = new ArrayList<PathPoint>();
						arr2.addAll(arrPathPoint);
						arr2.addAll(IntersectionBlock.getRightToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						
						ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
						arrDouble1.addAll(arrDoublePathPoint);
						arrDouble1.addAll(IntersectionBlock.getRightToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						ArrayList<PathPoint> arrDouble2 = new ArrayList<PathPoint>();
						arrDouble2.addAll(arrDoublePathPoint);
						arrDouble2.addAll(IntersectionBlock.getRightToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						
						
						VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
						viop1.addAllIntersections(viop.getIntersections());
						
						VisitedIntersectionsOnPath viop2 = new VisitedIntersectionsOnPath();
						viop2.addAllIntersections(viop.getIntersections());
						
						
						this.discoverPaths(arr1,arrDouble1,arrPathSwitch,viop1, 1, x, y+1, previousX , previousY);
						this.discoverPaths(arr2,arrDouble2,arrPathSwitch,viop2, -1, x, y-1, previousX , previousY);
						arrPathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],RoadConfig.RIGHT_TO_LEFT_DIRECTION));
						arrDoublePathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], RoadConfig.RIGHT_TO_LEFT_DIRECTION));
						
						x--;
						
						
					}
					
					
					
				} else
				
				if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_EXIT_BLOCK){
					
					previousX = x;
					previousY = y;
					
					if (direction == 1){
						ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
						arr1.addAll(arrPathPoint);
						arr1.addAll(IntersectionBlock.getTopToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						ArrayList<PathPoint> arr2 = new ArrayList<PathPoint>();
						arr2.addAll(arrPathPoint);
						arr2.addAll(IntersectionBlock.getTopToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						
						ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
						arrDouble1.addAll(arrDoublePathPoint);
						arrDouble1.addAll(IntersectionBlock.getTopToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						ArrayList<PathPoint> arrDouble2 = new ArrayList<PathPoint>();
						arrDouble2.addAll(arrDoublePathPoint);
						arrDouble2.addAll(IntersectionBlock.getTopToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						
						VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
						viop1.addAllIntersections(viop.getIntersections());
						
						VisitedIntersectionsOnPath viop2 = new VisitedIntersectionsOnPath();
						viop2.addAllIntersections(viop.getIntersections());
						
						
						
						this.discoverPaths(arr1,arrDouble1, arrPathSwitch, viop1, 1, x+1, y, previousX , previousY);
						this.discoverPaths(arr2,arrDouble2, arrPathSwitch, viop2, -1, x-1, y, previousX , previousY);
						arrPathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
						arrDoublePathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
						y++;
						
						
						
					} else {
						ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
						arr1.addAll(arrPathPoint);
						arr1.addAll(IntersectionBlock.getBottomToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						ArrayList<PathPoint> arr2 = new ArrayList<PathPoint>();
						arr2.addAll(arrPathPoint);
						arr2.addAll(IntersectionBlock.getBottomToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						
						ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
						arrDouble1.addAll(arrDoublePathPoint);
						arrDouble1.addAll(IntersectionBlock.getBottomToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						ArrayList<PathPoint> arrDouble2 = new ArrayList<PathPoint>();
						arrDouble2.addAll(arrDoublePathPoint);
						arrDouble2.addAll(IntersectionBlock.getBottomToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						
						
						VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
						viop1.addAllIntersections(viop.getIntersections());
						
						VisitedIntersectionsOnPath viop2 = new VisitedIntersectionsOnPath();
						viop2.addAllIntersections(viop.getIntersections());
						
						
						this.discoverPaths(arr1,arrDouble1,arrPathSwitch, viop1, 1, x+1, y, previousX , previousY);
						this.discoverPaths(arr2,arrDouble2,arrPathSwitch, viop2, -1, x-1, y, previousX , previousY);
						arrPathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], RoadConfig.BOTTOM_TO_TOP_DIRECTION));
						arrDoublePathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],RoadConfig.BOTTOM_TO_TOP_DIRECTION));
						y--;
						
					}
					
				}
			} else if (this.roadGrid.getGrid() [x][y] == RoadConfig.INTERSECTION_LEFT_BLOCK){
			
				if (viop.isVisited(x, y)){
					break;
				}
				else {
					viop.addIntersection(x, y);
				}
				
				System.out.println("intersection left block detected");
				if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_EXIT_BLOCK){
					
					previousX = x;
					previousY = y;
					
					if (direction == 1){
						ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
						arr1.addAll(arrPathPoint);
						arr1.addAll(IntersectionBlock.getLeftToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
						ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
						arrDouble1.addAll(arrDoublePathPoint);
						arrDouble1.addAll(IntersectionBlock.getLeftToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
						
						VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

						viop1.addAllIntersections(viop.getIntersections());
						
						this.discoverPaths(arr1, arrDouble1, arrPathSwitch, viop1, 1, x, y+1, previousX , previousY);
						arrPathPoint.addAll(IntersectionBlock.getLeftToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
						arrDoublePathPoint.addAll(IntersectionBlock.getLeftToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
						y--;
						direction=-1;
					}	
				} if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_EXIT_BLOCK){
					
					previousX = x;
					previousY = y;
					
					if (direction == 1){
						
						ArrayList<PathPoint> arr = new ArrayList<PathPoint>();
						arr.addAll(arrPathPoint);
						arr.addAll(IntersectionBlock.getTopToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						ArrayList<PathPoint> arrDouble = new ArrayList<PathPoint>();
						arrDouble.addAll(arrDoublePathPoint);
						arrDouble.addAll(IntersectionBlock.getTopToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						
						VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

						viop1.addAllIntersections(viop.getIntersections());
						
						this.discoverPaths(arr,arrDouble, arrPathSwitch, viop1, -1, x-1, y, previousX , previousY);
						arrPathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
						arrDoublePathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE,this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
						
						y++;
						
						
						
					} else {
						
						ArrayList<PathPoint> arr = new ArrayList<PathPoint>();
						arr.addAll(arrPathPoint);
						arr.addAll(IntersectionBlock.getBottomToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						ArrayList<PathPoint> arrDouble = new ArrayList<PathPoint>();
						arrDouble.addAll(arrDoublePathPoint);
						arrDouble.addAll(IntersectionBlock.getBottomToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
						VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

						viop1.addAllIntersections(viop.getIntersections());
						this.discoverPaths(arr,arrDouble, arrPathSwitch, viop1, -1, x-1, y, previousX , previousY);
						arrPathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], RoadConfig.BOTTOM_TO_TOP_DIRECTION));
						arrDoublePathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1,this.roadGrid.getGrid() [x] [y], RoadConfig.BOTTOM_TO_TOP_DIRECTION));
						y--;
						
					}
					
				}
			
			}
			
			
			 else if (this.roadGrid.getGrid() [x][y] == RoadConfig.INTERSECTION_RIGHT_BLOCK){
					
					if (viop.isVisited(x, y)){
						break;
					}
					else {
						viop.addIntersection(x, y);
					}
					
					System.out.println("intersection right block detected");
					if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_EXIT_BLOCK){
						
						previousX = x;
						previousY = y;
						
						if (direction == -1){
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionBlock.getRightToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionBlock.getRightToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr1,arrDouble1, arrPathSwitch, viop1, 1, x, y+1, previousX , previousY);
							arrPathPoint.addAll(IntersectionBlock.getRightToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDoublePathPoint.addAll(IntersectionBlock.getRightToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							y--;
							direction=-1;
						}	
					} if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_EXIT_BLOCK){
						
						previousX = x;
						previousY = y;
						
						if (direction == 1){
							
							ArrayList<PathPoint> arr = new ArrayList<PathPoint>();
							arr.addAll(arrPathPoint);
							arr.addAll(IntersectionBlock.getTopToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							ArrayList<PathPoint> arrDouble = new ArrayList<PathPoint>();
							arrDouble.addAll(arrDoublePathPoint);
							arrDouble.addAll(IntersectionBlock.getTopToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr,arrDouble,arrPathSwitch, viop1, 1, x+1, y, previousX , previousY);
							arrPathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
							arrDoublePathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
							
							y++;
							
							
							
						} else {
							
							ArrayList<PathPoint> arr = new ArrayList<PathPoint>();
							arr.addAll(arrPathPoint);
							arr.addAll(IntersectionBlock.getBottomToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							ArrayList<PathPoint> arrDouble = new ArrayList<PathPoint>();
							arrDouble.addAll(arrDoublePathPoint);
							arrDouble.addAll(IntersectionBlock.getBottomToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr,arrDouble, arrPathSwitch, viop1, 1, x+1, y, previousX , previousY);
							arrPathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], RoadConfig.BOTTOM_TO_TOP_DIRECTION));
							arrDoublePathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], RoadConfig.BOTTOM_TO_TOP_DIRECTION));
							y--;
							
						}
						
					}
				
				}
			 	else if (this.roadGrid.getGrid() [x][y] == RoadConfig.INTERSECTION_UP_BLOCK){
					if (viop.isVisited(x, y)){
						break;
					}
					else {
						viop.addIntersection(x, y);
					}
					System.out.println("intersection up detected");
					if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_EXIT_BLOCK){
						
						previousX = x;
						previousY = y;
						
						if (direction == 1){
							
							ArrayList<PathPoint> arr = new ArrayList<PathPoint>();
							arr.addAll(arrPathPoint);
							arr.addAll(IntersectionBlock.getLeftToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble = new ArrayList<PathPoint>();
							arrDouble.addAll(arrDoublePathPoint);
							arrDouble.addAll(IntersectionBlock.getLeftToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr, arrDouble,arrPathSwitch,viop1, -1, x, y-1, previousX , previousY);
							arrPathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE,this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							
							x++;
							
							
							
						}
						
						else {
							
							ArrayList<PathPoint> arr = new ArrayList<PathPoint>();
							arr.addAll(arrPathPoint);
							arr.addAll(IntersectionBlock.getRightToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							ArrayList<PathPoint> arrDouble = new ArrayList<PathPoint>();
							arrDouble.addAll(arrDoublePathPoint);
							arrDouble.addAll(IntersectionBlock.getRightToTopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr, arrDouble, arrPathSwitch, viop1, -1, x, y-1, previousX , previousY);
							arrPathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							x--;
							
							
						}
						
						
						
					} else
					
					if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_EXIT_BLOCK){
						
						previousX = x;
						previousY = y;
						
						if (direction == 1){
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionBlock.getTopToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionBlock.getTopToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch, viop1, 1, x+1, y, previousX , previousY);
							arrPathPoint.addAll(IntersectionBlock.getTopToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							arrDoublePathPoint.addAll(IntersectionBlock.getTopToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							x--;
					}
			 	}
			 }
			 	else if (this.roadGrid.getGrid() [x][y] == RoadConfig.INTERSECTION_DOWN_BLOCK){
					if (viop.isVisited(x, y)){
						break;
					}
					else {
						viop.addIntersection(x, y);
					}
					System.out.println("intersection down detected");
					if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_EXIT_BLOCK){
						
						previousX = x;
						previousY = y;
						
						if (direction == 1){
							
							ArrayList<PathPoint> arr = new ArrayList<PathPoint>();
							arr.addAll(arrPathPoint);
							arr.addAll(IntersectionBlock.getLeftToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble = new ArrayList<PathPoint>();
							arrDouble.addAll(arrDoublePathPoint);
							arrDouble.addAll(IntersectionBlock.getLeftToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr,arrDouble,arrPathSwitch,viop1, 1, x, y+1, previousX , previousY);
							arrPathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							x++;
							
							
							
						}
						
						else {
							
							ArrayList<PathPoint> arr = new ArrayList<PathPoint>();
							arr.addAll(arrPathPoint);
							arr.addAll(IntersectionBlock.getRightToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							ArrayList<PathPoint> arrDouble = new ArrayList<PathPoint>();
							arrDouble.addAll(arrDoublePathPoint);
							arrDouble.addAll(IntersectionBlock.getRightToBottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr,arrDouble, arrPathSwitch,viop1, 1, x, y+1, previousX , previousY);
							arrPathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, RoadConfig.HORIZONTAL_BLOCK, RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, RoadConfig.HORIZONTAL_BLOCK, RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							x--;
							
							
						}
						
						
						
					} else
					
					if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_EXIT_BLOCK){
						
						previousX = x;
						previousY = y;
						
						if (direction == -1){
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionBlock.getBottomToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionBlock.getBottomToRightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();

							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr1,arrDouble1, arrPathSwitch, viop1, 1, x+1, y, previousX , previousY);
							arrPathPoint.addAll(IntersectionBlock.getBottomToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							arrDoublePathPoint.addAll(IntersectionBlock.getBottomToLeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid()[x] [y]));
							x--;
					}
			 	}
			 }
			
			 	else if (this.roadGrid.getGrid() [x][y] == RoadConfig.INTERSECTION_DOUBLE_BLOCK){
					
			 		if (viop.isVisited(x, y)){
						break;
					}
					else {
						viop.addIntersection(x, y);
					}
					System.out.println("Double intersection detected");
					if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_DOUBLE_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK){
						previousX = x;
						previousY = y;
						
						if (direction == 1){
							
							
						
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionDoubleBlock.getLeft1To1TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionDoubleBlock.getLeft1To2TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x, y-2, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionDoubleBlock.getLeft2To2BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionDoubleBlock.getLeft2To1BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x, y+2, previousX , previousY);
							
							arrPathPoint.addAll(HorizontalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							x+=2;
							direction=1;
							
						}
						
						else {
				
								
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionDoubleBlock.getRight1To1BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionDoubleBlock.getRight1To2BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x, y+2, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionDoubleBlock.getRight2To2TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionDoubleBlock.getRight2To1TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x, y-2, previousX , previousY);
							
							arrPathPoint.addAll(HorizontalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							x-=2;
							direction=-1;
							
							
						}
						
						
						
					} else
					
					if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_DOUBLE_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
						if (direction==1){
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionDoubleBlock.getTop1To1RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionDoubleBlock.getTop1To2RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x+2, y, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionDoubleBlock.getTop2To2LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionDoubleBlock.getTop2To1LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x-2, y, previousX , previousY);
							
							arrPathPoint.addAll(VerticalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
							arrDoublePathPoint.addAll(VerticalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
							y+=2;
							direction=1;
							
							
							
						} else {
							
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionDoubleBlock.getBottom1To1LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionDoubleBlock.getBottom1To2LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x-2, y, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionDoubleBlock.getBottom2To2RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionDoubleBlock.getTop2To1LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x+2, y, previousX , previousY);
							
							arrPathPoint.addAll(VerticalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], RoadConfig.BOTTOM_TO_TOP_DIRECTION));
							arrDoublePathPoint.addAll(VerticalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],RoadConfig.BOTTOM_TO_TOP_DIRECTION));
							y-=2;
							direction=-1;
							
						}
						
			 	}
			 }	
			
			 	else if (this.roadGrid.getGrid() [x][y] == RoadConfig.INTERSECTION_MIXED_HORIZONTAL_BLOCK){
					//Always come at the same point, do not change
			 		if (viop.isVisited(x, y)){
						break;
					}
					else {
						viop.addIntersection(x, y);
					}
					System.out.println("Mixed horizontal intersection detected");
					
					if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_EXIT_BLOCK){
						previousX = x;
						previousY = y;
						
						if (direction == 1){
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedHorizontalBlock.getLeft1To1TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedHorizontalBlock.getLeft1To2TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x, y-2, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedHorizontalBlock.getLeft1To2BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedHorizontalBlock.getLeft1To1BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x, y+1, previousX , previousY);
							
							arrPathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							arrPathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							
							x+=2;
							direction=1;
							
						}
						
						else {
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedHorizontalBlock.getRight1To1BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedHorizontalBlock.getRight1To2BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x, y+1, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedHorizontalBlock.getRight1To2TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedHorizontalBlock.getRight1To1TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x, y-2, previousX , previousY);
							
							arrPathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.BLOCK_SIDE_SIZE -1, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.BLOCK_SIDE_SIZE -1, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							arrPathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE -1 , y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE -1, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							
							x-=1;
							direction=-1;
								
						
						}
						
						
						
					} else
					
					if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_DOUBLE_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
						if (direction==1){
							
							/*arrPathPoint.addAll(IntersectionMixedHorizontalBlock.getTop1To1RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							x+=2;
							direction=1;*/
							/*arrPathPoint.addAll(IntersectionMixedHorizontalBlock.getTop2To1LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							x-=1;
							direction=-1;*/
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedHorizontalBlock.getTop1To1RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedHorizontalBlock.getTop1To1RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x+2, y, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedHorizontalBlock.getTop2To1LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedHorizontalBlock.getTop2To1LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x-1, y, previousX , previousY);
							
							arrPathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE+GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
							arrDoublePathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE+GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
							
							y+=1;
							direction=1;
							
						} else {
							
							/*arrPathPoint.addAll(IntersectionMixedHorizontalBlock.getBottom1To1LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							x-=1;
							direction=-1;*/
							/*arrPathPoint.addAll(IntersectionMixedHorizontalBlock.getBottom2To1RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							x+=2;
							direction=1;*/
							
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedHorizontalBlock.getBottom1To1LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedHorizontalBlock.getBottom1To1LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x-1, y, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedHorizontalBlock.getBottom2To1RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedHorizontalBlock.getBottom2To1RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x+2, y, previousX , previousY);
							
							arrPathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], RoadConfig.BOTTOM_TO_TOP_DIRECTION));
							arrDoublePathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],RoadConfig.BOTTOM_TO_TOP_DIRECTION ));
							
							y-=2;
							direction=-1;
						
						}
						
			 	}
					
			 }
			
			
			 	else if (this.roadGrid.getGrid() [x][y] == RoadConfig.INTERSECTION_MIXED_VERTICAL_BLOCK){
					//TODO Fix visited points
			 		if (viop.isVisited(x, y)){
						break;
					}
					else {
						viop.addIntersection(x, y);
					}
					System.out.println("Mixed vertical intersection detected");
					
					if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_DOUBLE_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK){
						
						
						previousX = x;
						previousY = y;
						
						if (direction == 1){
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedVerticalBlock.getLeft1To1TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedHorizontalBlock.getLeft1To1TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x, y-1, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedVerticalBlock.getLeft2To1BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedVerticalBlock.getLeft2To1BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x, y+2, previousX , previousY);
							
							arrPathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.LEFT_TO_RIGHT_DIRECTION));
							
							x+=1;
							direction=1;
							
						}
						
						else {
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedVerticalBlock.getRight1To1BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedHorizontalBlock.getRight1To1BottomPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x, y+2, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedVerticalBlock.getRight2To1TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedVerticalBlock.getRight2To1TopPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x, y-1, previousX , previousY);
							
							arrPathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE - 1, y*GraphicsConfig.BLOCK_SIDE_SIZE+GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							arrDoublePathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE - 1, y*GraphicsConfig.BLOCK_SIDE_SIZE+GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.RIGHT_TO_LEFT_DIRECTION));
							
							x-=2;
							direction=-1;
							
						}
						
						
					
					} else
					
					if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_EXIT_BLOCK){
						if (direction==1){

							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedVerticalBlock.getTop1To1RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedVerticalBlock.getTop1To2RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x+1,y, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedVerticalBlock.getTop1To2LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedVerticalBlock.getTop1To1LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x-2, y, previousX , previousY);
							
							arrPathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
							arrDoublePathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],  RoadConfig.TOP_TO_BOTTOM_DIRECTION));
							arrPathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE , y*GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
							arrDoublePathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE + GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], RoadConfig.TOP_TO_BOTTOM_DIRECTION));
							
							y+=2;
							direction=1;
							
						} else {
							
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedVerticalBlock.getBottom1To1LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedVerticalBlock.getBottom1To2LeftPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							VisitedIntersectionsOnPath viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, -1, x-2,y, previousX , previousY);
							
							arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(IntersectionMixedVerticalBlock.getBottom1To2RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(IntersectionMixedVerticalBlock.getBottom1To1RightPath(x*GraphicsConfig.BLOCK_SIDE_SIZE,y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y]));
							arrPathSwitch.add(new PathSwitchingDifference(arrDouble1.size()-1, arrDouble1.size() - arr1.size()));
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							this.discoverPaths(arr1, arrDouble1, arrPathSwitch1, viop1, 1, x+1, y, previousX , previousY);
							
							arrPathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],  RoadConfig.BOTTOM_TO_TOP_DIRECTION ));
							arrDoublePathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE+ GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],  RoadConfig.BOTTOM_TO_TOP_DIRECTION));
							arrPathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE , y*GraphicsConfig.BLOCK_SIDE_SIZE -1, this.roadGrid.getGrid() [x] [y],  RoadConfig.BOTTOM_TO_TOP_DIRECTION));
							arrDoublePathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE -1, this.roadGrid.getGrid() [x] [y],  RoadConfig.BOTTOM_TO_TOP_DIRECTION));
							
							y-=1;
							direction=-1;
						}
						
			 	}
					
			 }
			
			 	else if (this.roadGrid.getGrid() [x][y] == RoadConfig.ROUND_ABOUT_ENTER){
			 		
			 		VisitedIntersectionsOnPath viop1;
			 		
			 		if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.HORIZONTAL_EXIT_BLOCK){
						
						
						
						if (direction == 1){
							if (viop.isVisited(x+1, y)){
								break;
							}
							else {
								viop.addIntersection(x+1, y);
							}
							previousX = x;
							previousY = y;
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 1, RoadConfig.HORIZONTAL_BLOCK, 1));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 1, RoadConfig.HORIZONTAL_BLOCK, 1));
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							this.discoverPaths(arr1,arrDouble1,arrPathSwitch,viop1, -1, x+1, y-2, previousX , previousY);
							
							ArrayList<PathPoint> arr2 = new ArrayList<PathPoint>();
							arr2.addAll(arrPathPoint);
							arr2.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 2, RoadConfig.HORIZONTAL_BLOCK, 1));
							ArrayList<PathPoint> arrDouble2 = new ArrayList<PathPoint>();
							arrDouble2.addAll(arrDoublePathPoint);
							arrDouble2.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 2, RoadConfig.HORIZONTAL_BLOCK, 1));
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							this.discoverPaths(arr2,arrDouble2,arrPathSwitch,viop1, 1, x+3, y, previousX , previousY);
							
							ArrayList<PathPoint> arr3 = new ArrayList<PathPoint>();
							arr3.addAll(arrPathPoint);
							arr3.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 3, RoadConfig.HORIZONTAL_BLOCK, 1));
							
							ArrayList<PathPoint> arrDouble3 = new ArrayList<PathPoint>();
							arrDouble3.addAll(arrDoublePathPoint);
							arrDouble3.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 3, RoadConfig.HORIZONTAL_BLOCK, 1));
							
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							this.discoverPaths(arr3,arrDouble3,arrPathSwitch,viop1, 1, x+1, y+2, previousX , previousY);
							
							arrPathPoint.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 4, RoadConfig.HORIZONTAL_BLOCK, 1));
							arrDoublePathPoint.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 4, RoadConfig.HORIZONTAL_BLOCK, 1));
							x-=1;
							direction=-1;
							
						}
						
						else {
							
							if (viop.isVisited(x-1, y)){
								break;
							}
							else {
								viop.addIntersection(x-1, y);
							}
							previousX = x;
							previousY = y;
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 1, RoadConfig.HORIZONTAL_BLOCK, -1));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 1, RoadConfig.HORIZONTAL_BLOCK, -1));
	
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr1,arrDouble1,arrPathSwitch,viop1, 1, x-1, y+2, previousX , previousY);
							
							ArrayList<PathPoint> arr2 = new ArrayList<PathPoint>();
							arr2.addAll(arrPathPoint);
							arr2.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 2, RoadConfig.HORIZONTAL_BLOCK, -1));
							ArrayList<PathPoint> arrDouble2 = new ArrayList<PathPoint>();
							arrDouble2.addAll(arrDoublePathPoint);
							arrDouble2.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 2, RoadConfig.HORIZONTAL_BLOCK, -1));
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr2,arrDouble2, arrPathSwitch,viop1, -1, x-3, y, previousX , previousY);
							
							ArrayList<PathPoint> arr3 = new ArrayList<PathPoint>();
							arr3.addAll(arrPathPoint);
							arr3.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 3, RoadConfig.HORIZONTAL_BLOCK, -1));
							ArrayList<PathPoint> arrDouble3 = new ArrayList<PathPoint>();
							arrDouble3.addAll(arrDoublePathPoint);
							arrDouble3.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 3, RoadConfig.HORIZONTAL_BLOCK, -1));
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr3,arrDouble3,arrPathSwitch,viop1, -1, x-1, y-2, previousX , previousY);
							
							arrPathPoint.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 4, RoadConfig.HORIZONTAL_BLOCK, -1));
							arrDoublePathPoint.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 4, RoadConfig.HORIZONTAL_BLOCK, -1));
							
							x+=1;
							direction=1;
							
						}
						
						
						
					} else if (this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_ENTER_BLOCK || this.roadGrid.getGrid()[previousX][previousY] == RoadConfig.VERTICAL_EXIT_BLOCK){
						
						
						
						if (direction == 1){
							
							if (viop.isVisited(x, y+1)){
								break;
							}
							else {
								viop.addIntersection(x, y+1);
							}
							previousX = x;
							previousY = y;
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 1, RoadConfig.VERTICAL_BLOCK, 1));
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 1, RoadConfig.VERTICAL_BLOCK, 1));						
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr1,arrDouble1, arrPathSwitch,viop1, 1, x+2, y+1, previousX , previousY);
							
							ArrayList<PathPoint> arr2 = new ArrayList<PathPoint>();
							arr2.addAll(arrPathPoint);
							arr2.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 2, RoadConfig.VERTICAL_BLOCK, 1));
							ArrayList<PathPoint> arrDouble2 = new ArrayList<PathPoint>();
							arrDouble2.addAll(arrDoublePathPoint);
							arrDouble2.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 2, RoadConfig.VERTICAL_BLOCK, 1));
							
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr2,arrDouble2, arrPathSwitch, viop1, 1, x, y+3, previousX , previousY);
							
							ArrayList<PathPoint> arr3 = new ArrayList<PathPoint>();
							arr3.addAll(arrPathPoint);
							arr3.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 3, RoadConfig.VERTICAL_BLOCK, 1));
							
							ArrayList<PathPoint> arrDouble3 = new ArrayList<PathPoint>();
							arrDouble3.addAll(arrDoublePathPoint);
							arrDouble3.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 3, RoadConfig.VERTICAL_BLOCK, 1));
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr3,arrDouble3,arrPathSwitch,viop1, -1, x-2, y+1, previousX , previousY);
							
							arrPathPoint.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 4, RoadConfig.VERTICAL_BLOCK, 1));
							arrDoublePathPoint.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 4, RoadConfig.VERTICAL_BLOCK,1));
							y-=1;
							direction=-1;
						}
						else {
							if (viop.isVisited(x, y-1)){
								break;
							}
							else {
								viop.addIntersection(x, y-1);
							}
							previousX = x;
							previousY = y;
							
							ArrayList<PathPoint> arr1 = new ArrayList<PathPoint>();
							arr1.addAll(arrPathPoint);
							arr1.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 1, RoadConfig.VERTICAL_BLOCK, -1));
							
							ArrayList<PathPoint> arrDouble1 = new ArrayList<PathPoint>();
							arrDouble1.addAll(arrDoublePathPoint);
							arrDouble1.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 1, RoadConfig.VERTICAL_BLOCK, -1));
							
							
							ArrayList <PathSwitchingDifference> arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							this.discoverPaths(arr1,arrDouble1,arrPathSwitch,viop1, -1, x-2, y-1, previousX , previousY);
							
							ArrayList<PathPoint> arr2 = new ArrayList<PathPoint>();
							arr2.addAll(arrPathPoint);
							arr2.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 2, RoadConfig.VERTICAL_BLOCK, -1));
							ArrayList<PathPoint> arrDouble2 = new ArrayList<PathPoint>();
							arrDouble2.addAll(arrDoublePathPoint);
							arrDouble2.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 2, RoadConfig.VERTICAL_BLOCK, -1));
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							
							this.discoverPaths(arr2,arrDouble2, arrPathSwitch,viop1, -1, x, y-3, previousX , previousY);
							
							ArrayList<PathPoint> arr3 = new ArrayList<PathPoint>();
							arr3.addAll(arrPathPoint);
							arr3.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 3, RoadConfig.VERTICAL_BLOCK, -1));
							ArrayList<PathPoint> arrDouble3 = new ArrayList<PathPoint>();
							arrDouble3.addAll(arrDoublePathPoint);
							arrDouble3.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 3, RoadConfig.VERTICAL_BLOCK, -1));
							arrPathSwitch1 = new ArrayList <PathSwitchingDifference>();
							arrPathSwitch1.addAll(arrPathSwitch);
							
							viop1 = new VisitedIntersectionsOnPath();
							viop1.addAllIntersections(viop.getIntersections());
							this.discoverPaths(arr3,arrDouble3, arrPathSwitch,viop1, 1, x+2, y-1, previousX , previousY);
					
							arrPathPoint.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 4, RoadConfig.VERTICAL_BLOCK, -1));
							arrDoublePathPoint.addAll(RoundAboutBlockSingle.getPath(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, (short) 4, RoadConfig.VERTICAL_BLOCK, -1));
							
							y+=1;
							direction=1;
						}
					}
			 	}
		}
		
		
		if ( this.roadGrid.getGrid() [x] [y]== RoadConfig.HORIZONTAL_ENTER_BLOCK || this.roadGrid.getGrid() [x] [y]== RoadConfig.HORIZONTAL_EXIT_BLOCK){
			if (direction == 1){
				arrPathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
				arrDoublePathPoint.addAll(HorizontalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
				
			} else {
				arrPathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],(short) -1));
				arrDoublePathPoint.addAll(HorizontalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y],(short) -1));
				
			}
			
			this.arrPath.add(new Path(arrPathPoint, new DoublePath(arrPathPoint,arrPathSwitch)));
		}
		if ( this.roadGrid.getGrid() [x] [y]== RoadConfig.VERTICAL_ENTER_BLOCK ||this.roadGrid.getGrid() [x] [y]== RoadConfig.VERTICAL_EXIT_BLOCK){
			if (direction == 1){
				arrPathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
				arrDoublePathPoint.addAll(VerticalBlock.getPathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
				
			} else {
				arrPathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], (short) -1));
				arrDoublePathPoint.addAll(VerticalBlock.getInversePathPoints(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], (short) -1));
				}
			
			this.arrPath.add(new Path(arrPathPoint, new DoublePath(arrPathPoint,arrPathSwitch)));
		}
		
		if ( this.roadGrid.getGrid() [x] [y]== RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || this.roadGrid.getGrid() [x] [y]== RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK){
			if (direction == 1){
				arrPathPoint.addAll(HorizontalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
				arrDoublePathPoint.addAll(HorizontalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) 1));
				} else {
					arrPathPoint.addAll(HorizontalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], (short) -1));
					arrDoublePathPoint.addAll(HorizontalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE-1, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y],(short) -1));
					
			}
			
			this.arrPath.add(new Path(arrPathPoint, new DoublePath(arrPathPoint,arrPathSwitch)));
		}
		if ( this.roadGrid.getGrid() [x] [y]== RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK ||this.roadGrid.getGrid() [x] [y]== RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
			if (direction == 1){
				arrPathPoint.addAll(VerticalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], (short) 1));
				arrDoublePathPoint.addAll(VerticalDoubleBlock.getPath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE, this.roadGrid.getGrid() [x] [y], (short) 1));
				} else {
				arrPathPoint.addAll(VerticalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], (short) -1));
				arrDoublePathPoint.addAll(VerticalDoubleBlock.getInversePath1Points(x*GraphicsConfig.BLOCK_SIDE_SIZE, y*GraphicsConfig.BLOCK_SIDE_SIZE-1, this.roadGrid.getGrid() [x] [y], (short) -1));
				}
			
			this.arrPath.add(new Path(arrPathPoint, new DoublePath(arrPathPoint,arrPathSwitch)));
		}
		
		
		for (PathPoint p : arrPathPoint){
			System.out.print(p.getX()+" ");
		}
		System.out.print("\n");
		
		for (PathPoint p : arrPathPoint){
			System.out.print(p.getY()+" ");
		}
		System.out.print("\n");
		System.out.print("Path\n");
		
		
	}
	

}
