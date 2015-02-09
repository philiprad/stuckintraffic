package main;

import util.OSDirectoryFinder;

public class MainConfig {
	
	public static final String HOME_DIR = "StuckInTraffic";
	public static final String COMPONENTS_DIR = "Components";
	public static final String SAVES_DIR = "Saves";
	public static final String PATHS_DIR = "Paths";
	public static final String GRID_DIR = "Grids";
	public static final String ROADBLOCK_DIR = "RoadBlocksGraphics";
	
	public static final String HOME_PATH = OSDirectoryFinder.APP_HOME_DIR + "/" + HOME_DIR;
	public static final String COMPONENTS_PATH = HOME_PATH + "/" + COMPONENTS_DIR ;
	public static final String SAVES_PATH = COMPONENTS_PATH + "/" + SAVES_DIR ;
	public static final String PATHS_PATH = SAVES_PATH + "/" + PATHS_DIR ;
	public static final String GRID_PATH = SAVES_PATH + "/" + GRID_DIR ;
	public static final String ROADBLOCK_PATH = SAVES_PATH + "/" + ROADBLOCK_DIR ;
	
	public static final String SAVES_FILE_NAME = "TrafficInfrastructures";
	public static final String SAVES_FILE_PATH = SAVES_PATH + "/" + SAVES_FILE_NAME;
	
	public static final String GRID_SUFFIX = "Grid";
	public static final String ROADBLOCK_GRAPHICS_SUFFIX = "Graphics";
	public static final String PATH_SUFFIX = "Path";
	
}
