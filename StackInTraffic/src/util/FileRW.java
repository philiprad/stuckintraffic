/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class FileRW.
 */
public class FileRW {
	
	/**
	 * Read object.
	 *
	 * @param path
	 *            the path
	 * @return the object
	 */
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
	
	/**
	 * Write object.
	 *
	 * @param object
	 *            the object
	 * @param path
	 *            the path
	 */
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
