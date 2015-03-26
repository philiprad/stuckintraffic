/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package util;

import java.io.File;

// TODO: Auto-generated Javadoc
/**
 * The Class DirBuilder.
 */
public class DirBuilder {
	
	/**
	 * Creates the dir.
	 *
	 * @param path
	 *            the path
	 */
	public static void createDir(String path){
		File file =  new File(path);
		if (!file.exists()){
			file.mkdir();
			System.out.println("Directory "+path+" has been created");
		}
		else {
			System.out.println("Directory "+path+" already exists");
		}
	}
}
