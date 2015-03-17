/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package agents.logic;

import java.util.ArrayList;

import trafficInfrastructure.roadPath.PathPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class DoubleIntersectionPossiblePath.
 */
public class DoubleIntersectionPossiblePath {

		/** The first choise. */
		private ArrayList <PathPoint> firstChoise;
		
		/** The second choise. */
		private ArrayList <PathPoint> secondChoise;
		
		/**
		 * Instantiates a new double intersection possible path.
		 *
		 * @param firstChoise
		 *            the first choise
		 * @param secondChoise
		 *            the second choise
		 */
		public DoubleIntersectionPossiblePath(ArrayList <PathPoint> firstChoise, ArrayList <PathPoint> secondChoise){
			this.firstChoise = firstChoise;
			this.secondChoise = secondChoise;
		}
		
		/**
		 * Gets the first choise.
		 *
		 * @return the first choise
		 */
		public ArrayList <PathPoint> getFirstChoise(){
			return this.firstChoise;
		}
		
		/**
		 * Gets the second choise.
		 *
		 * @return the second choise
		 */
		public ArrayList <PathPoint> getSecondChoise(){
			return this.secondChoise;
		}
		
		/**
		 * Adds the array path to first chose.
		 *
		 * @param arrPathPoint
		 *            the arr path point
		 */
		public void addArrayPathToFirstChose(ArrayList <PathPoint> arrPathPoint){
			this.firstChoise.addAll(arrPathPoint);
		}
		
		/**
		 * Adds the array path to second chose.
		 *
		 * @param arrPathPoint
		 *            the arr path point
		 */
		public void addArrayPathToSecondChose(ArrayList <PathPoint> arrPathPoint){
			this.secondChoise.addAll(arrPathPoint);
		}
}
