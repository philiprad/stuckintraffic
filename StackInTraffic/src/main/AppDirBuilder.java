package main;

import java.io.File;
import java.util.ArrayList;

import util.DirBuilder;
import util.FileRW;

public class AppDirBuilder {
	public static void build(){
		DirBuilder.createDir(MainConfig.HOME_PATH);
		DirBuilder.createDir(MainConfig.COMPONENTS_PATH);
		DirBuilder.createDir(MainConfig.SAVES_PATH);
		DirBuilder.createDir(MainConfig.GRID_PATH);
		DirBuilder.createDir(MainConfig.PATHS_PATH);
		DirBuilder.createDir(MainConfig.ROADBLOCK_PATH);
		
		File file = new File(MainConfig.SAVES_FILE_PATH);
		if (!file.exists()){
			ArrayList<String> trafficInfrastructures = new ArrayList<String>();
			FileRW.writeObject(trafficInfrastructures, MainConfig.SAVES_FILE_PATH);
		}
	}
}
