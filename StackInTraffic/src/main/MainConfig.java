/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package main;

import util.OSDirectoryFinder;

// TODO: Auto-generated Javadoc
/**
 * The Class MainConfig.
 */
public class MainConfig {
	
	/** The Constant HOME_DIR. */
	public static final String HOME_DIR = "StuckInTraffic";
	
	/** The Constant COMPONENTS_DIR. */
	public static final String COMPONENTS_DIR = "Components";
	
	/** The Constant SAVES_DIR. */
	public static final String SAVES_DIR = "Saves";
	
	/** The Constant PATHS_DIR. */
	public static final String PATHS_DIR = "Paths";
	
	public static final String DOUBLE_PATHS_DIR = "DoublePaths" ;
	
	/** The Constant GRID_DIR. */
	public static final String GRID_DIR = "Grids";
	
	/** The Constant ROADBLOCK_DIR. */
	public static final String ROADBLOCK_DIR = "RoadBlocksGraphics";
	
	/** The Constant HOME_PATH. */
	public static final String HOME_PATH = OSDirectoryFinder.APP_HOME_DIR + "/" + HOME_DIR;
	
	/** The Constant COMPONENTS_PATH. */
	public static final String COMPONENTS_PATH = HOME_PATH + "/" + COMPONENTS_DIR ;
	
	/** The Constant SAVES_PATH. */
	public static final String SAVES_PATH = COMPONENTS_PATH + "/" + SAVES_DIR ;
	
	/** The Constant PATHS_PATH. */
	public static final String PATHS_PATH = SAVES_PATH + "/" + PATHS_DIR ;
	
	public static final String DOUBLE_PATHS_PATH = SAVES_PATH + "/" + DOUBLE_PATHS_DIR;
	
	/** The Constant GRID_PATH. */
	public static final String GRID_PATH = SAVES_PATH + "/" + GRID_DIR ;
	
	/** The Constant ROADBLOCK_PATH. */
	public static final String ROADBLOCK_PATH = SAVES_PATH + "/" + ROADBLOCK_DIR ;
	
	/** The Constant SAVES_FILE_NAME. */
	public static final String SAVES_FILE_NAME = "TrafficInfrastructures";
	
	/** The Constant SAVES_FILE_PATH. */
	public static final String SAVES_FILE_PATH = SAVES_PATH + "/" + SAVES_FILE_NAME;
	
	/** The Constant GRID_SUFFIX. */
	public static final String GRID_SUFFIX = "Grid";
	
	/** The Constant ROADBLOCK_GRAPHICS_SUFFIX. */
	public static final String ROADBLOCK_GRAPHICS_SUFFIX = "Graphics";
	
	/** The Constant PATH_SUFFIX. */
	public static final String PATH_SUFFIX = "Path";
	
	public static final String DOUBLE_PATH_SUFFIX = "DoublePath";
	
}
