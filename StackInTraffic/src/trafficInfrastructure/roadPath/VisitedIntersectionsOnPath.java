package trafficInfrastructure.roadPath;

import java.util.ArrayList;

import trafficInfrastructure.grid.GridPoint;

public class VisitedIntersectionsOnPath {
	 
	private ArrayList <GridPoint> arrGridPoint;
	
	public VisitedIntersectionsOnPath(){
		this.arrGridPoint = new ArrayList<GridPoint>();
	}
	
	public void addIntersection(int x, int y){
		this.arrGridPoint.add(new GridPoint(x,y));
	}
	
	public boolean isVisited(int x, int y){
		for (GridPoint gp : this.arrGridPoint){
			if (gp.getX()==x && gp.getY()==y){
				return true;
			}
		}
		return false;
	}
}
