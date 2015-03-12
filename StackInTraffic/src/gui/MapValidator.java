package gui;

import trafficInfrastructure.grid.GridBuilder;
import trafficInfrastructure.road.RoadConfig;

public class MapValidator {
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
				for(int j=0;j<map[i].length;j++){
					switch(map[i][j]){
						case RoadConfig.HORIZONTAL_BLOCK: {
							System.out.println("horizontal block");
							if((i>0 && i+2< map.length && map[i-1][j]==0 && map[i+1][j]!=0 && map[i+2][j]!=0) || (i==0 && map[i+1][j]!=0 && map[i+2][j]!=0)){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.HORIZONTAL_ENTER_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if(((i+1<map.length && map[i+1][j]==0) || (i+1==map.length)) && (i>0 && i-2 >= 0) && map[i-1][j]!=0 && map[i-2][j]!=0){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.HORIZONTAL_EXIT_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if((i==0 && (map[i+1][j]==0 || map[i+2][j]==0)) || 
									(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_BLOCK && (i>0 && i+2< map.length && map[i+1][j]==0 && (map[i-1][j]==0 || map[i+2][j]==0))) || 
									(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_BLOCK && i>0 && i+2==map.length && (map[i-1][j]==0 || map[i+1][j]==0))){
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
								
								boolean test1 = i>0 && i+4< map.length;
								boolean test2 = map[i+2][j]==0;
								boolean test3 = map[i-2][j]==0;
								//boolean test4 = map[i+4][j]==0;
								boolean test6 =map[i][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK && i>0 && i+3<= map.length && (map[i-2][j]==0);
								boolean test5= (i>0 && i+4< map.length && map[i+2][j]==0 && (map[i-2][j]==0 || map[i+4][j]==0));
								System.out.println("here double faulting: on "+map[i][j] + " tests: " + test1 + test2 + test3 + test5 + test6);
								return false;
							}
//							if((i==0 && (map[i+1][j]==0 || map[i+2][j]==0)) || ((i>0 && i+2< map.length && map[i+1][j]==0 && (map[i-1][j]==0 || map[i+2][j]==0))) || (map[i][j]!=RoadConfig.HORIZONTAL_EXIT_BLOCK && i>0 && i+2==map.length && (map[i-1][j]==0 || map[i+1][j]==0))){
//								return false;
//							}
							break;
						}
						case RoadConfig.VERTICAL_BLOCK: {
							System.out.println("vertical block");
	
							if((j>0 && j+2< map.length && map[i][j-1]==0 && map[i][j+1]!=0 && map[i][j+2]!=0) || (j==0 && map[i][j+1]!=0 && map[i][j+2]!=0)){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.VERTICAL_ENTER_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if(((j+1<map.length && map[i][j+1]==0) || (j+1==map.length)) && (j>0 && j-2 >= 0) && map[i][j-1]!=0 && map[i][j-2]!=0){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.VERTICAL_EXIT_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if(map[i][j]!=RoadConfig.VERTICAL_EXIT_BLOCK && (j==0 && (map[i][j+1]==0 || map[i][j+2]==0)) || 
									(map[i][j]!=RoadConfig.VERTICAL_EXIT_BLOCK && (j>0 && j+2< map.length && map[i][j+1]==0 && (map[i][j-1]==0 || map[i][j+2]==0))) || 
									(map[i][j]!=RoadConfig.VERTICAL_EXIT_BLOCK && (j>0 && j+2== map.length && (map[i][j-1]==0 || map[i][j+1]==0)))){
								return false;
							}
							break;
						}
						case RoadConfig.VERTICAL_DOUBLE_BLOCK: {
							System.out.println("vertical double block");
							if((j>0 && j+5< map.length) && (map[i][j-1]==0 && map[i][j+2]!=0 && map[i][j+4]!=0) || (j==0 && map[i][j+2]!=0 && map[i][j+4]!=0)){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK;	
								System.out.println(" to: " + map[i][j]);
							}
							if(((j+2<map.length && map[i][j+2]==0) || (j+2==map.length)) && (j>0 && j-4 >= 0) && map[i][j-2]!=0 && map[i][j-4]!=0){
								System.out.print("\n block i: " + i + " j:" + j + " converted from: " + map[i][j]);
								map[i][j]=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK;
								System.out.println(" to: " + map[i][j]);
							}
							if((j==0 && (map[i][j+2]==0 || map[i][j+4]==0)) ||
									(map[i][j]!=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK && (i>0 && j+4< map.length && map[i][j+2]==0 && (map[i][j-2]==0 || map[i][j+4]==0))) ||
									(map[i][j]!=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK && (j>0 && j+4== map.length && map[i][j-2]==0 && map[i][j+2]==0)) || 
									(map[i][j]!=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK && (j>0 && j+5== map.length && (map[i][j-2]==0 || map[i][j+2]==0)))){
								return false;
							}
//							(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK && (i>0 && i+4< map.length && map[i+2][j]==0 && (map[i-2][j]==0 || map[i+4][j]==0))) || 
//							//((i>0 && i+4< map.length && map[i+2][j]==0 && (map[i-2][j]==0 || map[i+4][j]==0))) || 
//							(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK && i>0 && i+4==map.length && (map[i-2][j]==0 || map[i+2][j]==0)) ||
//							(map[i][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK && i>0 && i+5==map.length && (map[i-2][j]==0 || map[i+2][j]==0)) 
							break;
						}
						case RoadConfig.INTERSECTION_BLOCK: {
							if(map[i-1][j]!=RoadConfig.HORIZONTAL_BLOCK || map[i+1][j]!=RoadConfig.HORIZONTAL_BLOCK || map[i][j+1]!=RoadConfig.VERTICAL_BLOCK || map[i][j-1]!=RoadConfig.VERTICAL_BLOCK){
								return false;
							}
							break;
						}
						case RoadConfig.INTERSECTION_UP_BLOCK: {
							if(map[i-1][j]!=RoadConfig.HORIZONTAL_BLOCK || map[i+1][j]!=RoadConfig.HORIZONTAL_BLOCK || map[i][j-1]!=RoadConfig.VERTICAL_BLOCK ){
								return false;
							}
							break;
						}
						
						case RoadConfig.INTERSECTION_DOWN_BLOCK: {
							if(map[i-1][j]!=RoadConfig.HORIZONTAL_BLOCK || map[i+1][j]!=RoadConfig.HORIZONTAL_BLOCK || map[i][j+1]!=RoadConfig.VERTICAL_BLOCK ){
								return false;
							}
							break;
						}
						
						case RoadConfig.INTERSECTION_LEFT_BLOCK: {
							if(map[i-1][j]!=RoadConfig.HORIZONTAL_BLOCK || map[i][j+1]!=RoadConfig.VERTICAL_BLOCK || map[i][j-1]!=RoadConfig.VERTICAL_BLOCK){
								return false;
							}
							break;
						}
						
						case RoadConfig.INTERSECTION_RIGHT_BLOCK: {
							if(map[i+1][j]!=RoadConfig.HORIZONTAL_BLOCK || map[i][j+1]!=RoadConfig.VERTICAL_BLOCK || map[i][j-1]!=RoadConfig.VERTICAL_BLOCK){
								return false;
							}
							break;
						}
						
						case RoadConfig.INTERSECTION_DOUBLE_BLOCK: {
							if((map[i-2][j]!=RoadConfig.HORIZONTAL_DOUBLE_BLOCK && map[i-2][j]!=RoadConfig.HORIZONTAL_ENTER_DOUBLE_BLOCK) || (map[i+2][j]!=RoadConfig.HORIZONTAL_DOUBLE_BLOCK && map[i+2][j]!=RoadConfig.HORIZONTAL_EXIT_DOUBLE_BLOCK) || (map[i][j+2]!=RoadConfig.VERTICAL_DOUBLE_BLOCK && map[i][j+2]!=RoadConfig.VERTICAL_EXIT_DOUBLE_BLOCK) || (map[i][j-2]!=RoadConfig.VERTICAL_DOUBLE_BLOCK && map[i][j-2]!=RoadConfig.VERTICAL_ENTER_DOUBLE_BLOCK)){
								System.out.println("map invalid on intersection double block");
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
