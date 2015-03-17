package agentsLogic;

import java.util.ArrayList;

import trafficInfrastructure.roadPath.PathPoint;

public class DoubleIntersectionPossiblePath {

		private ArrayList <PathPoint> firstChoise;
		private ArrayList <PathPoint> secondChoise;
		
		public DoubleIntersectionPossiblePath(ArrayList <PathPoint> firstChoise, ArrayList <PathPoint> secondChoise){
			this.firstChoise = firstChoise;
			this.secondChoise = secondChoise;
		}
		public ArrayList <PathPoint> getFirstChoise(){
			return this.firstChoise;
		}
		public ArrayList <PathPoint> getSecondChoise(){
			return this.secondChoise;
		}
		public void addArrayPathToFirstChose(ArrayList <PathPoint> arrPathPoint){
			this.firstChoise.addAll(arrPathPoint);
		}
		public void addArrayPathToSecondChose(ArrayList <PathPoint> arrPathPoint){
			this.secondChoise.addAll(arrPathPoint);
		}
}
