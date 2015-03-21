/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader.SimulatorBilder;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;
import graphicsLoader.ImagesSelector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.MainConfig;
import simulationBuilder.TrafficManager;
import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.BlockGraphicPoint;
import trafficInfrastructure.roadPath.DoublePath;
import trafficInfrastructure.roadPath.Path;
import util.FileRW;
import agents.Car;
import agents.RoadBlock;
import agents.TrafficLight;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphicsDrawer.
 */
public class GraphicsDrawer extends JPanel implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5229368678300119736L;
	
	/** The delay. */
	private int delay;
	
	/** The timer. */
	private Timer timer;
	
	/** The grid road. */
	private GridBuilder gridRoad;
	
	/** The ib. */
	private ImagesBuilder ib;
	
	/** The arr bg. */
	private ArrayList<BlockGraphicPoint> arrBG;
	
	/** The arr path. */
	private ArrayList<Path> arrPath;
	
	private ArrayList<DoublePath> arrDoublePath;
	
	/** The car list. */
	private ArrayList<Car> carList ;
	
	/** The traffic light list. */
	private ArrayList<TrafficLight> trafficLightList;
	
	/** The road block grid. */
	private Object [][] roadBlockGrid;
	
	/** The path counter. */
	private short pathCounter = 0;
	
	/** The traffic light counter. */
	private short trafficLightCounter = 100;
	
	/** The traffic light number. */
	private short trafficLightNumber = 1;
	
	/** The is next yellow. */
	private short isNextYellow = 0;
	
	/** The car add counter. */
	private int carAddCounter = 0;
	
	/**
	 * Instantiates a new graphics drawer.
	 *
	 * @param delay the delay
	 * @param fileName the file name
	 * @param arrBG the arr bg
	 * @param ib the ib
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public GraphicsDrawer(int delay , String fileName , ArrayList<BlockGraphicPoint> arrBG , ImagesBuilder ib){
		GridBuilder gridBuilder = (GridBuilder)FileRW.readObject(MainConfig.GRID_PATH + "/" + fileName + MainConfig.GRID_SUFFIX);
		
		//this.setSize(gridBuilder.getGrid()[0].length*GraphicsConfig.BLOCK_SIDE_SIZE, gridBuilder.getGrid().length*GraphicsConfig.BLOCK_SIDE_SIZE);
		this.setPreferredSize(new Dimension(gridBuilder.getGrid()[0].length*GraphicsConfig.BLOCK_SIDE_SIZE, gridBuilder.getGrid().length*GraphicsConfig.BLOCK_SIDE_SIZE));
		this.delay =  delay;
		
		this.ib = ib;
		this.arrBG = arrBG;
		this.arrPath = (ArrayList<Path>)FileRW.readObject(MainConfig.PATHS_PATH + "/" + fileName + MainConfig.PATH_SUFFIX);
		
		TrafficManager tm = new TrafficManager(fileName);
		//this.trafficLightList = tm.getTrafficLightList();
		this.roadBlockGrid = tm.getRoadBlockArray();
		this.carList = new ArrayList<Car>();
		this.timer = new Timer (this.delay, this);
	}
	
	/**
	 * Start.
	 */
	public void start(){
		this.timer.start();
	}
	
	/**
	 * Stop.
	 */
	public void stop(){
		this.timer.restart();
		this.timer.stop();
	}
	
	public void pause(){
		this.timer.stop();
	}
	
	/**
	 * Sets the delay.
	 *
	 * @param newDelay the new delay
	 */
	public void setDelay(int newDelay){
		this.timer.setInitialDelay(newDelay);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint (Graphics g){
		super.paintComponent(g);     // paint parent's background
        setBackground(Color.GRAY);
        drawRoads(g);
        /*for (TrafficLight tl: this.trafficLightList){
        	tl.drawTrafficLights(g, ib);
        }*/
        if (!this.carList.isEmpty()){
        	Graphics2D g2d=(Graphics2D)g;
        	for(Car cr :this.carList){
        		cr.drawCar(g2d, ib);
        	}
        }
        timer.start();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.carAddCounter++;
		this.trafficLightCounter++;
		/*if (isNextYellow==1){
			if (this.trafficLightCounter>=50){
				TrafficManager.intersection1YellowLight(this.trafficLightNumber, this.trafficLightList);
				this.incTrafficLightNumber();
				this.trafficLightCounter=0;
				this.isNextYellow = 0;
			}
		}
		else {
			if (this.trafficLightCounter>=30){
				TrafficManager.intersection1GreenLight(this.trafficLightNumber, this.trafficLightList);
				this.trafficLightCounter=0;
				this.isNextYellow = 1;
			}
		}*/
		if (this.carList.isEmpty()){
			
				this.putCarOnEveryPath();
				
				
		} else {
			this.carAddCounter++;
			if(this.carAddCounter>40){
				this.putCarOnEveryPath();
				this.carAddCounter = 0;
			}
			ArrayList <Integer> arr = new ArrayList <Integer>();
			for (int i=0; i<this.carList.size(); i++){
				if (this.carList.get(i).ifPathEnd()){
					arr.add((Integer)i);
					
					
				} else {
					
					//this.carList.get(i).speedManagement(this.roadBlockGrid, this.trafficLightList);
					this.carList.get(i).move();
					
				}
				
					
			}
			for(int i=0; i<arr.size(); i++){
				this.carList.remove((int)arr.get(i));
			}
		
		}
		//this.carGridPositionUpdate();
		
		repaint();
	}
	
	/**
	 * Draw roads.
	 *
	 * @param g the g
	 */
	public void drawRoads(Graphics g){
		for (BlockGraphicPoint blockGP : arrBG){
				g.drawImage(ImagesSelector.selectRoadImage(blockGP.getBlockType(), ib), blockGP.getX(),blockGP.getY(),blockGP.getBlockHorizontalSize(), blockGP.getBlockVerticalSize(), null);
		}
	}
	//public void
	/**
	 * Inc traffic light number.
	 */
	private void incTrafficLightNumber(){
		if (this.trafficLightNumber==4){
			this.trafficLightNumber=1;
		}
		else {
			this.trafficLightNumber++;
		}
	}
	
	/**
	 * Car grid position update.
	 */
	private void carGridPositionUpdate(){
		for(int i = 0; i<this.roadBlockGrid.length; i++){
			for(int j = 0; j<this.roadBlockGrid[0].length; j++){
				if(this.roadBlockGrid[i][j]!=null){
					((RoadBlock) this.roadBlockGrid[i][j]).clearCarList();
				}
			}
		}
		if (!this.carList.isEmpty()){
			for (Car car : this.carList){
				int i = car.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE;
				int j = car.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE;
				((RoadBlock)this.roadBlockGrid[i][j]).addCar(car);
			}
		}
	}
	
	/**
	 * Put car on every path.
	 */
	public void putCarOnEveryPath(){
		Random rand = new Random();
		int x = rand.nextInt(this.arrPath.size());
		Car car = new Car(this.arrPath.get(x));
		car.setLane(3);
		this.carList.add(car);
	}
}
