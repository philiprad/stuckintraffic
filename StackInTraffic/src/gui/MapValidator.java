/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package gui;

import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.RoadConfig;

// TODO: Auto-generated Javadoc
/**
 * The Class MapValidator.
 */
public class MapValidator {
	
	/**
	 * Checks if is map valid.
	 *
	 * @param map
	 *            the map
	 * @return true, if is map valid
	 */
	public static boolean isMapValid(short[][] map){
		
		/**
		 * implement rules by reading the array gridBuilder.
		 *  if [i+1][j]!=0 and [i-1][j]!=0 then it is intersection
		 *  if [i+1][j]!=0 and (i!=0 and [i-1][j]==0) and [i][j]!=17 and [i][j]!=18 and [i][j]!= 4 then it is starting point
		 *  
		 */
		boolean isValid =true;
		int blockSize;
		
		try{
			for(int i=0; i<map.length;i++){
				//System.out.println("first i =" + i + " map length = " +map.length);
				for(int j=0;j<map[0].length;j++){
					switch(map[i][j]){
						case RoadConfig.HORIZONTAL_BLOCK: {
							System.out.println("horizontal block");
							if((i>0 && i+2< map.length && map[i-1][j]==0 && ((map[i+1][j]!=0 && map[i+2][j]!=0) || map[i+1][j]==-200) || (i==0 && ((map[i+1][j]!=0 && map[i+2][j]!=0) || map[i+1][j]==-200)))){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.HORIZONTAL_ENTER_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if(((i+1<map.length && map[i+1][j]==0 && (map[i-1][j]!=0 || map[i-1][j]==-200)) || 
								((i+1==map.length) && (i>0 && i-2 >= 0) && ((map[i-1][j]!=0 && map[i-2][j]!=0) || map[i-1][j]==-200)))){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.HORIZONTAL_EXIT_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if((i==0 && (map[i+1][j]==0 || (map[i+1][j]==RoadConfig.VERTICAL_BLOCK || map[i+1][j]==RoadConfig.VERTICAL_ENTER_BLOCK || map[i+1][j]==RoadConfig.VERTICAL_EXIT_BLOCK ||
									map[i+1][j]==RoadConfig.INTERSECTION_RIGHT_BLOCK || map[i+1][j]==RoadConfig.VERTICAL_DOUBLE_BLOCK || map[i+1][j]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK ||
									map[i+1][j]==-100) || map[i+2][j]==0)) || 
									(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_BLOCK && (i>0 && i+2< map.length && (map[i+1][j]==0 || (map[i+1][j]==RoadConfig.VERTICAL_BLOCK || map[i+1][j]==RoadConfig.VERTICAL_ENTER_BLOCK || map[i+1][j]==RoadConfig.VERTICAL_EXIT_BLOCK ||
											map[i+1][j]==RoadConfig.INTERSECTION_RIGHT_BLOCK || map[i+1][j]==RoadConfig.VERTICAL_DOUBLE_BLOCK || map[i+1][j]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK ||
											map[i+1][j]==-100)) && ((map[i-1][j]==0 || (map[i-1][j]==RoadConfig.VERTICAL_BLOCK || map[i-1][j]==RoadConfig.VERTICAL_ENTER_BLOCK || map[i-1][j]==RoadConfig.VERTICAL_EXIT_BLOCK ||
													map[i-1][j]==RoadConfig.INTERSECTION_LEFT_BLOCK || map[i-1][j]==RoadConfig.VERTICAL_DOUBLE_BLOCK || map[i-1][j]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK ||
													map[i-1][j]==-100)) || map[i+2][j]==0))) || 
									(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_BLOCK && i>0 && i+2==map.length && 
									(((map[i-1][j]==0) || (map[i-1][j]==RoadConfig.VERTICAL_BLOCK || map[i-1][j]==RoadConfig.VERTICAL_ENTER_BLOCK || map[i-1][j]==RoadConfig.VERTICAL_EXIT_BLOCK ||
									map[i-1][j]==RoadConfig.INTERSECTION_LEFT_BLOCK || map[i-1][j]==RoadConfig.VERTICAL_DOUBLE_BLOCK || map[i-1][j]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK ||
									map[i-1][j]==-100)) && 
									(map[i+1][j]==0 || (map[i+1][j]==RoadConfig.VERTICAL_BLOCK || map[i+1][j]==RoadConfig.VERTICAL_ENTER_BLOCK || map[i+1][j]==RoadConfig.VERTICAL_EXIT_BLOCK ||
									map[i+1][j]==RoadConfig.INTERSECTION_RIGHT_BLOCK || map[i+1][j]==RoadConfig.VERTICAL_DOUBLE_BLOCK || map[i+1][j]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK ||
									map[i+1][j]==-100))))){
								System.out.println("Horizontal Block failed map Validations"+ map.length + " and " + map[0].length);
								return false;
							}
							break;
						}
						case RoadConfig.HORIZONTAL_DOUBLE_BLOCK : {
							System.out.println("horizontal double block");
	
							if((i>0 && i+5< map.length) && (map[i-1][j]==0 && map[i+2][j]!=0 && map[i+4][j]!=0) || (i==0 && map[i+2][j]!=0 && map[i+4][j]!=0)){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if(((i+2<map.length && map[i+2][j]==0) || (i+2==map.length)) && (i>0 && i-4 >= 0) && map[i-2][j]!=0 && map[i-4][j]!=0){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if((i==0 && (map[i+2][j]==0 || map[i+4][j]==0)) || 
									(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK && (i>0 && i+4< map.length && map[i+2][j]==0 && (map[i-2][j]==0 || map[i+4][j]==0))) || 
									//((i>0 && i+4< map.length && map[i+2][j]==0 && (map[i-2][j]==0 || map[i+4][j]==0))) || 
									(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK && i>0 && i+4==map.length && (map[i-2][j]==0 || map[i+2][j]==0)) ||
									(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK && i>0 && i+5==map.length && (map[i-2][j]==0 || map[i+2][j]==0)) 
									){
								System.out.println("Horizontal Double Block failed Map Validations");
								return false;
							}
//							if((i==0 && (map[i+1][j]==0 || map[i+2][j]==0)) || ((i>0 && i+2< map.length && map[i+1][j]==0 && (map[i-1][j]==0 || map[i+2][j]==0))) || (map[i][j]!=RoadConfig.HORIZONTAL_EXIT_BLOCK && i>0 && i+2==map.length && (map[i-1][j]==0 || map[i+1][j]==0))){
//								return false;
//							}
							break;
						}
						case RoadConfig.VERTICAL_BLOCK: {
							System.out.println("vertical block");
	
							if((j>0 && j+2< map[0].length && map[i][j-1]==0 && map[i][j+1]!=0 && map[i][j+2]!=0) || (j==0 && map[i][j+1]!=0 && map[i][j+2]!=0)){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.VERTICAL_ENTER_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if(((j+1<map[0].length && map[i][j+1]==0) || (j+1==map[0].length)) && (j>0 && j-2 >= 0) && map[i][j-1]!=0 && map[i][j-2]!=0){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.VERTICAL_EXIT_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if(//map[i][j]!=RoadConfig.VERTICAL_EXIT_BLOCK && 
							(j==0 && ((map[i][j+1]==0 ||
									(map[i][j+1]==RoadConfig.HORIZONTAL_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_ENTER_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_EXIT_BLOCK ||
									map[i][j+1]==RoadConfig.INTERSECTION_DOWN_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK ||
									map[i][j+1]==-100)) || map[i][j+2]==0)) || 
									(map[i][j]!=RoadConfig.VERTICAL_EXIT_BLOCK && 
									(j>0 && j+2< map[0].length && (map[i][j+1]==0 || 
											(map[i][j+1]==RoadConfig.HORIZONTAL_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_ENTER_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_EXIT_BLOCK ||
											map[i][j+1]==RoadConfig.INTERSECTION_DOWN_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK ||
											map[i][j+1]==-100)) && ((map[i][j-1]==0 ||
													(map[i][j-1]==RoadConfig.HORIZONTAL_BLOCK || map[i][j-1]==RoadConfig.HORIZONTAL_ENTER_BLOCK || map[i][j-1]==RoadConfig.HORIZONTAL_EXIT_BLOCK ||
													map[i][j-1]==RoadConfig.INTERSECTION_UP_BLOCK || map[i][j-1]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK || map[i][j-1]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK ||
													map[i][j-1]==-100)) || map[i][j+2]==0))) || 
									(//map[i][j]!=RoadConfig.VERTICAL_EXIT_BLOCK && 
									(j>0 && j+2== map[0].length && ((map[i][j-1]==0 ||
											(map[i][j-1]==RoadConfig.HORIZONTAL_BLOCK || map[i][j-1]==RoadConfig.HORIZONTAL_ENTER_BLOCK || map[i][j-1]==RoadConfig.HORIZONTAL_EXIT_BLOCK ||
											map[i][j-1]==RoadConfig.INTERSECTION_UP_BLOCK || map[i][j-1]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK || map[i][j-1]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK ||
											map[i][j-1]==-100)) && (map[i][j+1]==0 || 
											(map[i][j+1]==RoadConfig.HORIZONTAL_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_ENTER_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_EXIT_BLOCK ||
											map[i][j+1]==RoadConfig.INTERSECTION_DOWN_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK || map[i][j+1]==RoadConfig.HORIZONTAL_DOUBLE_BLOCK ||
											map[i][j+1]==-100)))))){
								System.out.println("Vertical Block failed map validations" + map.length + " and " + map[0].length);
								return false;
							}
							break;
						}
						case RoadConfig.VERTICAL_DOUBLE_BLOCK: {
							System.out.println("vertical double block");
							if((j>0 && j+5< map[0].length) && (map[i][j-1]==0 && map[i][j+2]!=0 && map[i][j+4]!=0) || (j==0 && map[i][j+2]!=0 && map[i][j+4]!=0)){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK;	
								System.out.println(" to: " + map[i][j]);
							}
							if(((j+2<map[0].length && map[i][j+2]==0) || (j+2==map[0].length)) && (j>0 && j-4 >= 0) && map[i][j-2]!=0 && map[i][j-4]!=0){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if((j==0 && (map[i][j+2]==0 || map[i][j+4]==0)) ||
									(map[i][j]!=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK && (i>0 && j+4< map[0].length && map[i][j+2]==0 && (map[i][j-2]==0 || map[i][j+4]==0))) ||
									(map[i][j]!=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK && (j>0 && j+4== map[0].length && map[i][j-2]==0 && map[i][j+2]==0)) || 
									(map[i][j]!=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK && (j>0 && j+5== map[0].length && (map[i][j-2]==0 || map[i][j+2]==0)))){
								System.out.println("Vertical Double Block failed Map Validations");
								return false;
							}
//							(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK && (i>0 && i+4< map.length && map[i+2][j]==0 && (map[i-2][j]==0 || map[i+4][j]==0))) || 
//							//((i>0 && i+4< map.length && map[i+2][j]==0 && (map[i-2][j]==0 || map[i+4][j]==0))) || 
//							(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK && i>0 && i+4==map.length && (map[i-2][j]==0 || map[i+2][j]==0)) ||
//							(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK && i>0 && i+5==map.length && (map[i-2][j]==0 || map[i+2][j]==0)) 
							break;
						}
						case RoadConfig.INTERSECTION_BLOCK: {
							if((map[i-1][j]!=RoadConfig.HORIZONTAL_BLOCK && map[i-1][j]!=RoadConfig.HORIZONTAL_ENTER_BLOCK) || (map[i+1][j]!=RoadConfig.HORIZONTAL_BLOCK && map[i+1][j]!=RoadConfig.HORIZONTAL_EXIT_BLOCK) || (map[i][j+1]!=RoadConfig.VERTICAL_BLOCK && map[i][j+1]!=RoadConfig.VERTICAL_EXIT_BLOCK) || (map[i][j-1]!=RoadConfig.VERTICAL_BLOCK && map[i][j-1]!=RoadConfig.VERTICAL_ENTER_BLOCK)){
								return false;
							}
							break;
						}
						case RoadConfig.INTERSECTION_UP_BLOCK: {
							if((map[i-1][j]!=RoadConfig.HORIZONTAL_BLOCK && map[i-1][j]!=RoadConfig.HORIZONTAL_ENTER_BLOCK) || (map[i+1][j]!=RoadConfig.HORIZONTAL_BLOCK && map[i+1][j]!=RoadConfig.HORIZONTAL_EXIT_BLOCK) || (map[i][j-1]!=RoadConfig.VERTICAL_BLOCK && map[i][j-1]!=RoadConfig.VERTICAL_ENTER_BLOCK) || 
									(map[i][j+1]!=RoadConfig.INTERSECTION_DOWN_BLOCK && map[i][j+1]!=0)){//&& map[i][j-1]!=RoadConfig.HORIZONTAL_BLOCK && map[i][j-1]!=RoadConfig.HORIZONTAL_ENTER_BLOCK && map[i][j-1]!=RoadConfig.HORIZONTAL_EXIT_BLOCK && map[i][j-1]!=RoadConfig.HORIZONTAL_DOUBLE_BLOCK)){
								System.out.println("Intersection Up Block failed Map Validations");
								return false;
							}
							break;
						}
						
						case RoadConfig.INTERSECTION_DOWN_BLOCK: {
							if((map[i-1][j]!=RoadConfig.HORIZONTAL_BLOCK && map[i-1][j]!=RoadConfig.HORIZONTAL_ENTER_BLOCK) || (map[i+1][j]!=RoadConfig.HORIZONTAL_BLOCK && map[i+1][j]!=RoadConfig.HORIZONTAL_EXIT_BLOCK)|| (map[i][j+1]!=RoadConfig.VERTICAL_BLOCK && map[i][j+1]!=RoadConfig.VERTICAL_EXIT_BLOCK) ||
									(map[i][j-1]!=RoadConfig.INTERSECTION_UP_BLOCK && map[i][j-1]!=0)){
								System.out.println("Intersection Down Block failed Map Validations");
								return false;
							}
							break;
						}
						
						case RoadConfig.INTERSECTION_LEFT_BLOCK: {
							if((map[i-1][j]!=RoadConfig.HORIZONTAL_BLOCK && map[i-1][j]!=RoadConfig.HORIZONTAL_ENTER_BLOCK) || (map[i][j+1]!=RoadConfig.VERTICAL_BLOCK && map[i][j+1]!=RoadConfig.VERTICAL_EXIT_BLOCK) || (map[i][j-1]!=RoadConfig.VERTICAL_BLOCK && map[i][j-1]!=RoadConfig.VERTICAL_ENTER_BLOCK) ||
									(map[i+1][j]!=RoadConfig.INTERSECTION_RIGHT_BLOCK && map[i+1][j]!=0)){
								System.out.println("Intersection Left Block failed Map Validations");
								return false;
							}
							break;
						}
						
						case RoadConfig.INTERSECTION_RIGHT_BLOCK: {
							if((map[i+1][j]!=RoadConfig.HORIZONTAL_BLOCK && map[i+1][j]!=RoadConfig.HORIZONTAL_EXIT_BLOCK) || (map[i][j+1]!=RoadConfig.VERTICAL_BLOCK && map[i][j+1]!=RoadConfig.VERTICAL_EXIT_BLOCK) || (map[i][j-1]!=RoadConfig.VERTICAL_BLOCK && map[i][j-1]!=RoadConfig.VERTICAL_ENTER_BLOCK) ||
									(map[i-1][j]!=RoadConfig.INTERSECTION_LEFT_BLOCK && map[i-1][j]!=0)){
								System.out.println("Intersection Right Block failed Map Validations");
								return false;
							}
							break;
						}
						
						case RoadConfig.INTERSECTION_DOUBLE_BLOCK: {
							if((map[i-2][j]!=RoadConfig.HORIZONTAL_DOUBLE_BLOCK && map[i-2][j]!=RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK) || (map[i+2][j]!=RoadConfig.HORIZONTAL_DOUBLE_BLOCK && map[i+2][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK) || (map[i][j+2]!=RoadConfig.VERTICAL_DOUBLE_BLOCK && map[i][j+2]!=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK) || (map[i][j-2]!=RoadConfig.VERTICAL_DOUBLE_BLOCK && map[i][j-2]!=RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK)){
								System.out.println("Intersection Double Block failed Map Validations");
								return false;
							}
							break;
						}
						/**
						 * TODO intersection mixed block map validation
						 */
						case RoadConfig.INTERSECTION_MIXED_BLOCK: {
							
							break;
						}
						/**
						 * TODO round about block map validation
						 */
						case RoadConfig.ROUND_ABOUT_BLOCK:{
							if((i<1 && j<1 && i+1<=map.length ) ||(map[i-1][j+1]!=RoadConfig.HORIZONTAL_BLOCK && map[i-1][j+1]!=RoadConfig.HORIZONTAL_ENTER_BLOCK) || (map[i+3][j+1]!=RoadConfig.HORIZONTAL_BLOCK && map[i+3][j+1]!=RoadConfig.HORIZONTAL_EXIT_BLOCK) || (map[i+1][j-1]!=RoadConfig.VERTICAL_BLOCK && map[i+1][j-1]!=RoadConfig.VERTICAL_ENTER_BLOCK) || (map[i+1][j+3]!=RoadConfig.VERTICAL_BLOCK && map[i+1][j+3]!=RoadConfig.VERTICAL_EXIT_BLOCK) || 
									(map[i-1][j]!=0 && map[i-1][j-1]!=0 && map[i][j-1]!=0 && map[i+3][j]!=0 && map[i+3][j-1]!=0 && map[i+2][j-1]!=0 && map[i+3][j+2]!=0 && map[i+3][j+3]!=0 && map[i+2][j+3]!=0 && map[i][j+3]!=0 && map[i-1][j+3]!=0 && map[i-1][j+2]!=0)){
								System.out.println("Round About Block failed Map Validations");
								return false;
							}
//							if((map[i-2][j]!=RoadConfig.HORIZONTAL_DOUBLE_BLOCK && map[i-2][j]!=RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK) || (map[i+2][j]!=RoadConfig.HORIZONTAL_DOUBLE_BLOCK && map[i+2][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK) || (map[i][j+2]!=RoadConfig.VERTICAL_DOUBLE_BLOCK && map[i][j+2]!=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK) || (map[i][j-2]!=RoadConfig.VERTICAL_DOUBLE_BLOCK && map[i][j-2]!=RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK)){
//								System.out.println("map invalid on intersection double block");
//								return false;
//							}
							break;
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("crashed\n\n" + e.toString());
			e.printStackTrace();
			return false;
		}
		
		return isValid;
	}
	
	/**
	 * Delete end points.
	 *
	 * @param gridBuilder
	 *            the grid builder
	 */
	public static void deleteEndPoints(GridBuilder gridBuilder){
		for(int i=0; i < gridBuilder.getGrid().length; i++){
			for(int j=0; j<gridBuilder.getGrid()[0].length;j++){
				switch(gridBuilder.getGrid()[i][j]){
				case RoadConfig.HORIZONTAL_ENTER_BLOCK:
					gridBuilder.getGrid()[i][j] = RoadConfig.HORIZONTAL_BLOCK;
					break;
				case RoadConfig.HORIZONTAL_EXIT_BLOCK:
					gridBuilder.getGrid()[i][j] = RoadConfig.HORIZONTAL_BLOCK;
					break;
				case RoadConfig.VERTICAL_EXIT_BLOCK:
					gridBuilder.getGrid()[i][j] = RoadConfig.VERTICAL_BLOCK;
					break;
				case RoadConfig.VERTICAL_ENTER_BLOCK:
					gridBuilder.getGrid()[i][j] = RoadConfig.VERTICAL_BLOCK;
					break;
				case RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK:
					gridBuilder.getGrid()[i][j] = RoadConfig.HORIZONTAL_DOUBLE_BLOCK;
					break;
				case RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK:
					gridBuilder.getGrid()[i][j] = RoadConfig.HORIZONTAL_DOUBLE_BLOCK;
					break;
				case RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK:
					gridBuilder.getGrid()[i][j] = RoadConfig.VERTICAL_DOUBLE_BLOCK;
					break;
				case RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK:
					gridBuilder.getGrid()[i][j] = RoadConfig.VERTICAL_DOUBLE_BLOCK;
					break;
				}
			
			}
		}
	}
}
