/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package trafficInfrastructure.roadPath;

import java.util.ArrayList;

import trafficInfrastructure.grid.GridPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class VisitedIntersectionsOnPath.
 */
public class VisitedIntersectionsOnPath {
	 
	/** The arr grid point. */
	private ArrayList <GridPoint> arrGridPoint;
	
	/**
	 * Instantiates a new visited intersections on path.
	 */
	public VisitedIntersectionsOnPath(){
		this.arrGridPoint = new ArrayList<GridPoint>();
	}
	
	/**
	 * Adds the intersection.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void addIntersection(int x, int y){
		this.arrGridPoint.add(new GridPoint(x,y));
	}
	
	/**
	 * Checks if is visited.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return true, if is visited
	 */
	public boolean isVisited(int x, int y){
		for (GridPoint gp : this.arrGridPoint){
			if (gp.getX()==x && gp.getY()==y){
				return true;
			}
		}
		return false;
	}
}
