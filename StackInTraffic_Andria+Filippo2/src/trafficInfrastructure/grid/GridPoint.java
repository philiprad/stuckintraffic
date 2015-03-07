/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package trafficInfrastructure.grid;

// TODO: Auto-generated Javadoc
/**
 * The Class GridPoint.
 */
public class GridPoint {

		/** The x. */
		private int x;
		
		/** The y. */
		private int y;
		
		/**
		 * Instantiates a new grid point.
		 *
		 * @param x
		 *            the x
		 * @param y
		 *            the y
		 */
		public GridPoint (int x, int y){
			this.x = x;
			this.y = y;
		}
		
		/**
		 * Gets the x.
		 *
		 * @return the x
		 */
		public int getX(){
			return this.x;
		}
		
		/**
		 * Gets the y.
		 *
		 * @return the y
		 */
		public int getY(){
			return this.y;
		}
}
