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
import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.BlockGraphicPoint;
import trafficInfrastructure.roadPath.Path;
import util.FileRW;
import agents.Car;

public class GraphicsDrawer extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = -5229368678300119736L;
	
	private int delay;
	private Timer timer;
	private GridBuilder gridRoad;
	private ImagesBuilder ib;
	private ArrayList<BlockGraphicPoint> arrBG;
	private ArrayList<Path> arrPath;
	private ArrayList<Car> carList;
	private short pathCounter = 0;
	
	@SuppressWarnings("unchecked")
	public GraphicsDrawer(int delay , String fileName , ArrayList<BlockGraphicPoint> arrBG , ImagesBuilder ib){
		this.delay =  delay;
		this.timer = new Timer (this.delay, this);
		this.ib = ib;
		this.arrBG = arrBG;
		this.arrPath = (ArrayList<Path>)FileRW.readObject(MainConfig.PATHS_PATH + "/" + fileName + MainConfig.PATH_SUFFIX);
		this.carList = new ArrayList<Car>();
	}
	
	public void start(){
		this.start();
	}
	
	public void stop(){
		this.stop();
	}
	
	public void setDelay(int newDelay){
		this.timer.setInitialDelay(newDelay);
	}
	
	public void paint (Graphics g){
		super.paintComponent(g);     // paint parent's background
        setBackground(Color.GRAY);
        drawRoads(g);
        if (!this.carList.isEmpty()){
        	Graphics2D g2d=(Graphics2D)g;
        	carList.get(0).drawCar(g2d, ib);
        }
        timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
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
		// TODO Auto-generated method stub
		repaint();
	}
	
	public void drawRoads(Graphics g){
		for (BlockGraphicPoint blockGP : arrBG){
			g.drawImage(ImagesSelector.selectRoadImage(blockGP.getBlockType(), ib), blockGP.getX(),blockGP.getY(),GraphicsConfig.BLOCK_SIDE_SIZE, GraphicsConfig.BLOCK_SIDE_SIZE, null);
		}
	}
	//public void
	
}
