/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package util;

// TODO: Auto-generated Javadoc
/**
 * The Class OSDirectoryFinder.
 */
public class OSDirectoryFinder {
	
	/** The Constant APP_HOME_DIR. */
	public static final String APP_HOME_DIR = getHomeDir();
	
	/**
	 * Gets the home dir.
	 *
	 * @return the home dir
	 */
	public static String getHomeDir(){
		String os = System.getProperty("os.name").toLowerCase();
		//TODO OS FOLDERS
		if (os.indexOf("win")>=0){
			return "./";
		}
		if (os.indexOf("mac")>=0){
			return "/Applications";
		}
		if (os.indexOf("nux")>=0){
			return "./";
		}
		else {
			return "./";
		}
		
	}
}
