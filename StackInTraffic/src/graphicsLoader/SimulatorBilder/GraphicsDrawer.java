/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package graphicsLoader.SimulatorBilder;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;
import graphicsLoader.ImagesSelector;
import gui.SimulationView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.MainConfig;
import simulationBuilder.RoadBlocksBuffer;
import simulationBuilder.TrafficLightSetDoubleIntersection;
import simulationBuilder.TrafficLightSetMixedIntersection;
import simulationBuilder.TrafficLightSetSingleIntersection;
import simulationBuilder.TrafficLightsBuilder;
import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.BlockGraphicPoint;
import trafficInfrastructure.road.RoadConfig;
import trafficInfrastructure.roadPath.DoublePath;
import trafficInfrastructure.roadPath.Path;
import util.FileRW;
import agents.RoadBlock;
import agents.StandartCar;
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
	private GridBuilder gridBuilder;
	
	/** The ib. */
	private ImagesBuilder ib;
	
	/** The arr bg. */
	private ArrayList<BlockGraphicPoint> arrBG;
	
	/** The arr path. */
	private ArrayList<Path> arrPath;
	
	/** The arr double path. */
	private ArrayList<DoublePath> arrDoublePath;
	
	/** The car list. */
	private ArrayList<StandartCar> carList ;
	
	/** The traffic light list. */
	private ArrayList<TrafficLight> trafficLightList;
	
	/** The arr traffic light set single. */
	private ArrayList<TrafficLightSetSingleIntersection> arrTrafficLightSetSingle;
	
	/** The arr traffic light set double. */
	private ArrayList<TrafficLightSetDoubleIntersection> arrTrafficLightSetDouble;
	
	/** The arr traffic light set mixed. */
	private ArrayList<TrafficLightSetMixedIntersection> arrTrafficLightSetMixed;
	
	/** The road block grid. */
	private Object [][] roadBlockGrid;
	
	/** The path counter. */
	private short pathCounter = 0;
	
	
	/** The car add counter. */
	private int carAddCounter = 0;
	
	/** The maximum cars. */
	private int maximumCars = 0;
	
	/** The view. */
	private SimulationView view;
	
	/** The petrol consumption. */
	private double petrolConsumption;
	
	/** The co emission. */
	private double coEmission;
	
	/** The maximum map capacity. */
	private int maximumMapCapacity;
	
	/**
	 * Instantiates a new graphics drawer.
	 *
	 * @param view the view
	 * @param delay the delay
	 * @param fileName the file name
	 * @param arrBG the arr bg
	 * @param ib the ib
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public GraphicsDrawer(SimulationView view, int delay , String fileName , ArrayList<BlockGraphicPoint> arrBG , ImagesBuilder ib){
		gridBuilder = (GridBuilder)FileRW.readObject(MainConfig.GRID_PATH + "/" + fileName + MainConfig.GRID_SUFFIX);
		
		//this.setSize(gridBuilder.getGrid()[0].length*GraphicsConfig.BLOCK_SIDE_SIZE, gridBuilder.getGrid().length*GraphicsConfig.BLOCK_SIDE_SIZE);
		this.setPreferredSize(new Dimension(gridBuilder.getGrid()[0].length*GraphicsConfig.BLOCK_SIDE_SIZE, gridBuilder.getGrid().length*GraphicsConfig.BLOCK_SIDE_SIZE));
		this.delay =  delay;
		this.view = view;
		this.ib = ib;
		this.arrBG = arrBG;
		this.arrPath = (ArrayList<Path>)FileRW.readObject(MainConfig.PATHS_PATH + "/" + fileName + MainConfig.PATH_SUFFIX);
		
		RoadBlocksBuffer roadBlockBuffer = new RoadBlocksBuffer(fileName);
		this.roadBlockGrid = roadBlockBuffer.getRoadBlockBufferArray();
		this.maximumCars = this.getMaximumNumberOfCars()/2;
		this.maximumMapCapacity = this.getMaximumNumberOfCars();
		TrafficLightsBuilder trafficLightBuilder = new TrafficLightsBuilder(gridBuilder, this.roadBlockGrid);
		trafficLightBuilder.buildTrafficLights();
		this.trafficLightList =trafficLightBuilder.getTrafficLightList();
		this.arrTrafficLightSetSingle = trafficLightBuilder.getTrafficLightSetSingleList();
		this.arrTrafficLightSetDouble = trafficLightBuilder.getTrafficLightSetDoubleList();
		this.arrTrafficLightSetMixed = trafficLightBuilder.getTrafficLightSetMixedList();
		this.carList = new ArrayList<StandartCar>();
		
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
	
	/**
	 * Gets the avg co emission.
	 *
	 * @return the avg co emission
	 */
	public double getAvgCoEmission(){
		return this.coEmission;
	}
	
	/**
	 * Gets the avg petrol consumption.
	 *
	 * @return the avg petrol consumption
	 */
	public double getAvgPetrolConsumption(){
		return this.petrolConsumption;
	}
	
	/**
	 * Pause.
	 */
	public void pause(){
		this.timer.stop();
	}
	
	/**
	 * Gets the number of cars.
	 *
	 * @return the number of cars
	 */
	public int getNumberOfCars(){
		return this.maximumCars;
	}
	
	/**
	 * Gets the utilisation of roads.
	 *
	 * @return the utilisation of roads
	 */
	public double getUtilisationOfRoads(){
		return this.maximumMapCapacity/100*this.carList.size();
	}
	
	/**
	 * Sets the delay.
	 *
	 * @param newDelay the new delay
	 */
	public void setDelay(int newDelay){
		this.stop();
		this.timer.setDelay(newDelay);
		this.start();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint (Graphics g){
		super.paintComponent(g);     // paint parent's background
        setBackground(Color.GRAY);
        drawRoads(g);
        for (TrafficLight tl: this.trafficLightList){
        	tl.drawTrafficLights(g, ib);
        }
        
        if (!this.carList.isEmpty()){
        	Graphics2D g2d=(Graphics2D)g;
        	for(StandartCar cr :this.carList){
        		cr.drawCar(g2d, ib);
        	}
        }
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		stepUpdate();
	}
	
	/**
	 * Step update.
	 */
	public void stepUpdate(){
		this.carAddCounter++;
		
		for (TrafficLightSetSingleIntersection tlSet : this.arrTrafficLightSetSingle){
        	tlSet.updateState();
        }
        for (TrafficLightSetDoubleIntersection tlSet : this.arrTrafficLightSetDouble){
        	tlSet.updateState();
        }
        for (TrafficLightSetMixedIntersection tlSet : this.arrTrafficLightSetMixed){
        	tlSet.updateState();
        }
		
		if (this.carList.isEmpty()){
			
				this.putCarOnEveryPath();
				
				
		} else {
			
			this.carAddCounter++;
			if(this.carAddCounter>20 && this.carList.size()<=this.maximumCars){
				this.putCarOnEveryPath();
				this.carAddCounter = 0;
			}
			
			ArrayList <Integer> arr = new ArrayList <Integer>();
			for (int i=0; i<this.carList.size(); i++){
				if (this.carList.get(i).isPathEnd()){
					arr.add((Integer)i);
					
					
				} else {
					
					this.carList.get(i).updateSpeed();
					this.carList.get(i).move();
					
				}
				
					
			}
			for(int i=0; i<arr.size(); i++){
				this.carList.remove((int)arr.get(i));
			}
		
		}
		
		view.updateStatistics();
		this.carGridPositionUpdate();
		
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
	
	/**
	 * Car grid position update.
	 */
	private void carGridPositionUpdate(){
		for(int i = 0; i<this.roadBlockGrid.length; i++){
			for(int j = 0; j<this.roadBlockGrid[0].length; j++){
				if(this.roadBlockGrid[i][j]!=null){
					((RoadBlock) this.roadBlockGrid[i][j]).clearCarList();
					((RoadBlock)this.roadBlockGrid[i][j]).deleteArrivingCar();
				}
			}
		}
		if (!this.carList.isEmpty()){
			
			for (StandartCar car : this.carList){
				int i = car.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE;
				int j = car.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE;
				
				((RoadBlock)this.roadBlockGrid[i][j]).addCar(car);
				
			}
		}
	}
	
	/**
	 * Avg speed.
	 *
	 * @return the double
	 */
	public double avgSpeed(){
		double speed = 0;
		double coEmission = 0;
		double petrolConsumption = 0;
		if (!this.carList.isEmpty()){
			for (StandartCar cr : this.carList)	{
				speed+=cr.getCarSpeed();
				coEmission+=cr.getCoEmission();
				petrolConsumption+=cr.getPetrolEmission();
			}
			
			
			Double avgRounded=new BigDecimal(coEmission/this.carList.size()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			this.coEmission = avgRounded;
			avgRounded=new BigDecimal(petrolConsumption/this.carList.size()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			this.petrolConsumption = avgRounded;
			double avgSpeed = speed/carList.size()*0.18/40*1000*2.236936;
		    avgRounded=new BigDecimal(avgSpeed ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			speed = avgRounded;
		}
		return speed;
	}
	
	/**
	 * Update cars.
	 *
	 * @param n the n
	 */
	public void updateCars(int n){
		int difference = this.carList.size() - n;
		if (difference>0){
			this.deleteRandomCars(difference);
			this.maximumCars = n;
		} else {
			this.maximumCars = n;
		}
		
	}
	
	/**
	 * Gets the maximum number of cars.
	 *
	 * @return the maximum number of cars
	 */
	public int getMaximumNumberOfCars(){
		int counter = 0;
		for (int i = 0 ;i<this.gridBuilder.getGrid().length; i++){
			for (int j = 0 ;j<this.gridBuilder.getGrid()[0].length; j++){
				if(this.gridBuilder.getGrid()[i][j]==RoadConfig.VERTICAL_BLOCK || this.gridBuilder.getGrid()[i][j]==RoadConfig.HORIZONTAL_BLOCK){
					counter+=2;
				} 
				if(this.gridBuilder.getGrid()[i][j]==RoadConfig.VERTICAL_DOUBLE_BLOCK || this.gridBuilder.getGrid()[i][j]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK){
					counter+=8;
				} 
			}
		}
		return counter;
	}
	
	/**
	 * Gets the car list size.
	 *
	 * @return the car list size
	 */
	public int getCarListSize(){
		return this.carList.size();
	}
	
	/**
	 * Delete random cars.
	 *
	 * @param n the n
	 */
	public void deleteRandomCars(int n){
		
		for(int i = 0; i<=n ; i++){
			Random rand = new Random();
			int x = rand.nextInt(this.carList.size());
			this.carList.remove(x);
		}
		
	}
	
	/**
	 * Put car on every path.
	 */
	public void putCarOnEveryPath(){
		Random rand = new Random();
		int x = rand.nextInt(this.arrPath.size());
		int driver = rand.nextInt(3);
		int carType = rand.nextInt(3);
		driver++;
		carType++;
		int roadBkX = this.arrPath.get(x).getPathPoints().get(0).getX()/GraphicsConfig.BLOCK_SIDE_SIZE;
		int roadBkY = this.arrPath.get(x).getPathPoints().get(0).getY()/GraphicsConfig.BLOCK_SIDE_SIZE;
		RoadBlock roadBk =(RoadBlock)this.roadBlockGrid[roadBkX][roadBkY];
		
		RoadBlock nextBk = (RoadBlock) this.roadBlockGrid[this.arrPath.get(x).getPathPoints().get(55).getX()/GraphicsConfig.BLOCK_SIDE_SIZE][this.arrPath.get(x).getPathPoints().get(55).getY()/GraphicsConfig.BLOCK_SIDE_SIZE];
		if(!roadBk.isCarInside()){
			
				if(roadBk.getBlockType() == RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK || roadBk.getBlockType()==RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK || roadBk.getBlockType() == RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK || roadBk.getBlockType()==RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK){
					if(!nextBk.isCarInside()){
						StandartCar car = new StandartCar(this.arrPath.get(x), (short) driver, this.roadBlockGrid, 2 , this.trafficLightList, carType);
						this.carList.add(car);
					}
				}
			 else {
				StandartCar car = new StandartCar(this.arrPath.get(x), (short) driver, this.roadBlockGrid, 1 , this.trafficLightList,carType);
				this.carList.add(car);
			}
		}
	}
}
