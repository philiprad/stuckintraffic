package pathFiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import main.MainConfig;
import trafficInfrastructure.roadPath.Path;
import util.FileRW;

public class PathFilter {
	
	private static ArrayList<Path> arrPath;
	
	public static void main (String[] args){
	
	String fileName = "pathFilter2";
	int Comparator;
	//Collections.sort(arrPath, new (Comparator)PathComparator();
	//Comparator<Path> c = Comparator<>arrPath.get(0).getPathPoints().size();
	arrPath = (ArrayList<Path>)FileRW.readObject(MainConfig.PATHS_PATH + "/" + fileName + MainConfig.PATH_SUFFIX);
	//arrPath.sort(c);
	
		for(Path path : arrPath){
			System.out.println(path.getPathPoints().get(0).getX() + " - " + path.getPathPoints().get(0).getY() + " ("+ path.getPathPoints().size() + ") "+ " : " + path.getPathPoints().get(path.getPathPoints().size()-1).getX() + " - " + path.getPathPoints().get(path.getPathPoints().size()-1).getY()+ " ("+ path.getPathPoints().size() + ")");
	
		}
	}
}
