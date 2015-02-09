package util;

import java.io.File;

public class DirBuilder {
	
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
