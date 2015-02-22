/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader.SimulatorBilder;

import graphicsLoader.GraphicsConfig;
import graphicsLoader.ImagesBuilder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.MainConfig;
import simulationBuilder.TrafficManager;
import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.BlockGraphicPoint;
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
	
	/** The car list. */
	private ArrayList<Car> carList;
	
	/** The traffic light list. */
	private ArrayList<TrafficLight> trafficLightList;
	
	private Object [][] roadBlockGrid;
	
	/** The path counter. */
	private short pathCounter = 0;
	
	/** The traffic light counter. */
	private short trafficLightCounter = 100;
	
	/** The traffic light number. */
	private short trafficLightNumber = 1;
	
	/** The is next yellow. */
	private short isNextYellow = 0;
	
	/**
	 * Instantiates a new graphics drawer.
	 *
	 * @param delay the delay
	 * @param fileName the file name
	 * @param arrBG the arr bg
	 * @param ib the ib
	 */
	@SuppressWarnings("unchecked")
	public GraphicsDrawer(int delay , String fileName , ArrayList<BlockGraphicPoint> arrBG , ImagesBuilder ib){
		this.delay =  delay;
		this.timer = new Timer (this.delay, this);
		this.ib = ib;
		this.arrBG = arrBG;
		this.arrPath = (ArrayList<Path>)FileRW.readObject(MainConfig.PATHS_PATH + "/" + fileName + MainConfig.PATH_SUFFIX);
		TrafficManager tm = new TrafficManager(fileName);
		this.trafficLightList = tm.getTrafficLightList();
		this.roadBlockGrid = tm.getRoadBlockArray();
		this.carList = new ArrayList<Car>();
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
        for (TrafficLight tl: this.trafficLightList){
        	tl.drawTrafficLights(g, ib);
        }
        if (!this.carList.isEmpty()){
        	Graphics2D g2d=(Graphics2D)g;
        	carList.get(0).drawCar(g2d, ib);
        }
        timer.start();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.trafficLightCounter++;
		if (isNextYellow==1){
			if (this.trafficLightCounter>=80){
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
		}
		if (this.carList.isEmpty()){
			this.carList.add(new Car(this.arrPath.get(pathCounter)));
		} else {
			if (carList.get(0).ifPathEnd()){
				this.carList.clear();
				if (this.pathCounter < this.arrPath.size()-1){
					this.pathCounter ++;
				}
				else {
					this.pathCounter = 0;
				}
			} else {
				carList.get(0).move();
			}
		}
		
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
			g.drawImage(ImagesSelector.selectRoadImage(blockGP.getBlockType(), ib), blockGP.getX(),blockGP.getY(),GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE, null);
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
	
	private void carGridPositionUpdate(){
		for(int i = 0; i<GraphicsConfig.GRID_WIDTH; i++){
			for(int j = 0; j<GraphicsConfig.GRID_HEIGHT; j++){
				if(this.roadBlockGrid[i][j]!=null){
					((RoadBlock) this.roadBlockGrid[i][j]).clearCarList();
				}
			}
		}
		for (Car car : this.carList){
				int i = car.getCarX()/GraphicsConfig.BLOCK_SIDE_SIZE;
				int j = car.getCarY()/GraphicsConfig.BLOCK_SIDE_SIZE;
				((RoadBlock)this.roadBlockGrid[i][j]).addCar(car);
		}
		
		for(int i = 0; i<GraphicsConfig.GRID_WIDTH; i++){
			for(int j = 0; j<GraphicsConfig.GRID_HEIGHT; j++){
				if(this.roadBlockGrid[i][j]!=null){
					if(((RoadBlock) this.roadBlockGrid[i][j]).carInside()){
						System.out.print(1);
					}
					else {
						System.out.print(0);
					}
				}
				else {
					System.out.print(0);
				}
				
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}
