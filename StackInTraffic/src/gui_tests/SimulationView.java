/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package gui_tests;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
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
	    JButton play = new JButton("Play");
		menubar.add(play);
		JButton pause = new JButton("Pause");
		menubar.add(pause);
		JButton stop = new JButton("Stop");	
		menubar.add(stop);
		JButton mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(new MainMenuListener());
		menubar.add(mainMenu);
		
		JToolBar toolbar = new JToolBar();
		this.add(toolbar, BorderLayout.EAST);
		//Images on buttons - TODO fix the size of the buttons
		/*ImageIcon horizontalImage = new ImageIcon("./images/horizontalRoad.png");
		ImageIcon verticalImage = new ImageIcon("./images/verticalRoad.png");
		ImageIcon intersectionImage = new ImageIcon("./images/Intersection.png");
		JButton horizontalButton = new JButton(horizontalImage);
		JButton verticalButton = new JButton(verticalImage);
		JButton intersectionButton = new JButton(intersectionImage);*/
		JButton horizontalButton = new JButton("Horizontal");
		JButton verticalButton = new JButton("Vertical");
		JButton intersectionButton = new JButton("Intersection");
	

		toolbar.add(horizontalButton);
		toolbar.add(verticalButton);
		toolbar.add(intersectionButton);
		
		
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
