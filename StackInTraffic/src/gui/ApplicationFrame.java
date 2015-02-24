package gui;

import javax.swing.JFrame;

public class ApplicationFrame extends JFrame{
		
	public ApplicationFrame(){
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.setResizable(false);       
	}
	
	public JFrame getFrame(){
		return this;
	}
}
