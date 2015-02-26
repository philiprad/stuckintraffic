/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package guiTemplate;

import javax.swing.JFrame;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationFrame.
 */
public class ApplicationFrame extends JFrame{
		
	/**
	 * Instantiates a new application frame.
	 */
	public ApplicationFrame(){
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.setResizable(false);       
	}
	
	/**
	 * Gets the frame.
	 *
	 * @return the frame
	 */
	public void addView(JPanel panelView){
		this.getContentPane().add(panelView);
		this.setVisible(true);
	}
	
	public void removeView(){
		this.getContentPane().removeAll();
		this.getContentPane().invalidate();
		this.getContentPane().validate();
		this.getContentPane().repaint();
        
	}
}
