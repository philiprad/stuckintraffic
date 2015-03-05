/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;

// TODO: Auto-generated Javadoc
/**
 * The Class SimulationView.
 */
public class SimulationView extends JPanel{
	
	/** The frame. */
	public ApplicationFrame frame;
	
	/**
	 * Instantiates a new simulation view.
	 *
	 * @param frame
	 *            the frame
	 */
	public SimulationView(ApplicationFrame frame){
		this.frame = frame;
		this.setLayout(new BorderLayout());
		JMenuBar menubar = new JMenuBar();
	    this.add(menubar, BorderLayout.NORTH);
		//Images on buttons - TODO fix the size of the buttons
		ImageIcon playImage = new ImageIcon("./images/play_resize.png");
		ImageIcon pauseImage = new ImageIcon("./images/pause_resize.png");
		ImageIcon stopImage = new ImageIcon("./images/stop_resize.png");
	    JButton play = new JButton(playImage);
	    //play.setBounds(5,5,5,10);
		menubar.add(play);
		JButton pause = new JButton(pauseImage);
		//pause.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
		//play.setBounds(5,5,5,10);
		menubar.add(pause);
		JButton stop = new JButton(stopImage);
		//stop.setBounds(5,5,5,10);
		menubar.add(stop);
		JButton mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(new MainMenuListener());
		menubar.add(mainMenu);
		
		JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
		this.add(toolbar, BorderLayout.EAST);
		/*JButton horizontalButton = new JButton(playImage);
		JButton verticalButton = new JButton(pauseImage);
		JButton intersectionButton = new JButton(stopImage);
		JButton horizontalButton = new JButton("Horizontal");
		JButton verticalButton = new JButton("Vertical");
		JButton intersectionButton = new JButton("Intersection");
		toolbar.add(horizontalButton);
		toolbar.add(verticalButton);
		toolbar.add(intersectionButton);*/
		JLabel carLabel= new JLabel("Select number of Cars");
		toolbar.add(carLabel);
		JScrollBar carScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		carScrollBar.setAlignmentY(Component.TOP_ALIGNMENT);
		toolbar.add(carScrollBar);
		
		JLabel motionLabel= new JLabel("Select car speed");
		toolbar.add(motionLabel);
		JScrollBar motionScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		motionScrollBar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		toolbar.add(motionScrollBar);
		
		
	}
	
	/**
	 * The listener interface for receiving mainMenu events. The class that is
	 * interested in processing a mainMenu event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addMainMenuListener<code> method. When
	 * the mainMenu event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see MainMenuEvent
	 */
	public class MainMenuListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			frame.removeView();
			frame.addView(new MainView(frame));
		}
	}
	 
}
