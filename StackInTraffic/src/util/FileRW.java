package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileRW {
	
	public static Object readObject (String path){
		
		Object obj = new Object();
		try{
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = ois.readObject();
			ois.close();
		}
		catch (Exception e) {
	         e.printStackTrace();
	    }
		
		return obj;
	}
	
	public static void writeObject(Object object, String path){
	
		try{
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.close();
		}
		catch (Exception e) {
	         e.printStackTrace();
	    }
	}
}
