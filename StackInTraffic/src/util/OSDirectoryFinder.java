package util;

public class OSDirectoryFinder {
	
	public static final String APP_HOME_DIR = getHomeDir();
	
	public static String getHomeDir(){
		String os = System.getProperty("os.name").toLowerCase();
		
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
