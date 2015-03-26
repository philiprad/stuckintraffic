/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package gui;

import main.AppDirBuilder;


// TODO: Auto-generated Javadoc
/**
 * The Class guiMain.
 */
public class guiMain {
	
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args){
		 AppDirBuilder.build();
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	ApplicationFrame frame = new ApplicationFrame();
	        		frame.setVisible(true);
	        		frame.addView(new MainView(frame));
	            }
	        });
	}
}
