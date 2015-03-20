package pathFiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import main.MainConfig;
import trafficInfrastructure.roadPath.Path;
import trafficInfrastructure.roadPath.PathPoint;
import util.FileRW;

public class PathFilter {
	
	private static ArrayList<Path> arrPath;
	
	public static void main (String[] args){
	
	String fileName = "Map1";
	int Comparator;
	//Collections.sort(arrPath, new (Comparator)PathComparator();
	//Comparator<Path> c = Comparator<>arrPath.get(0).getPathPoints().size();
	arrPath = (ArrayList<Path>)FileRW.readObject(MainConfig.PATHS_PATH + "/" + fileName + MainConfig.PATH_SUFFIX);
	//arrPath.sort(c);
	arrPath.sort(new PathComparator());
	//Collections.sort(arrPath);
	//0 - 115 (381)  : 185 - 349 (381)
	//0 - 115 (550)  : 164 - 0 (550)
	//0 - 115 (273)  : 164 - 0 (273)
	//Collections.sort(arrPath, new PathComparator());
		for(Path path : arrPath){
			
			System.out.println( path.getPathPoints().get(0).getX() + " - " + path.getPathPoints().get(0).getY() + " ("+ path.getPathPoints().size() + ") "+ " : " + path.getPathPoints().get(path.getPathPoints().size()-1).getX() + " - " + path.getPathPoints().get(path.getPathPoints().size()-1).getY()+ " ("+ path.getPathPoints().size() + ")");
//			System.out.println("-" + path.getPathPoints().get(1).getX() + " - " + path.getPathPoints().get(0).getY() + " ("+ path.getPathPoints().size() + ") "+ " : " + path.getPathPoints().get(path.getPathPoints().size()-2).getX() + " - " + path.getPathPoints().get(path.getPathPoints().size()-2).getY()+ " ("+ path.getPathPoints().size() + ")");

			if(path.getPathPoints().get(0).getX()==0 && path.getPathPoints().get(0).getY()==115 && path.getPathPoints().get(path.getPathPoints().size()-1).getX()==499 && path.getPathPoints().get(path.getPathPoints().size()-1).getY()==265){
			
				for (int i =0; i< path.getPathPoints().size();i++ ){//PathPoint pp :path.getPathPoints()){
				System.out.print(path.getPathPoints().get(i).getX()+"    ");
			}
			System.out.print("\n");
			for (int i =0; i< path.getPathPoints().size();i++ ){//PathPoint pp :path.getPathPoints()){
				System.out.print(path.getPathPoints().get(i).getY()+"    ");
			}
			}
		}
	}
}
