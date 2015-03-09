/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class MainWindow.
 */
public class MainView extends JPanel {
	
	/**
	 * Instantiates a new main window.
	 */
	
	private ApplicationFrame frame;
	
	/**
	 * Instantiates a new main view.
	 *
	 * @param frame
	 *            the frame
	 */
	public MainView (ApplicationFrame frame){
		this.frame = frame;
		 BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		 this.setLayout(layout);
		 JButton button = new JButton("Simulation");
	     button.setAlignmentX(Component.CENTER_ALIGNMENT);
	     this.add(button);
	     JButton button1 = new JButton("Map Builder");
	     button1.setAlignmentX(Component.CENTER_ALIGNMENT);
	     this.add(button1);
	     JButton button2 = new JButton("Exit");
	     button2.setAlignmentX(Component.CENTER_ALIGNMENT);
	     this.add(button2);
	     
	     button2.addActionListener(new ExitListener());
		 button.addActionListener(new SimulationListener());
		 button1.addActionListener(new MapBuilderListener());
		 
		
	}
	
	//Functionality of SimulationButton-New Window (Main.java)
			/**
	 * The listener interface for receiving simulation events. The class that is
	 * interested in processing a simulation event implements this interface,
	 * and the object created with that class is registered with a component
	 * using the component's <code>addSimulationListener<code> method. When
	 * the simulation event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SimulationEvent
	 */
	public class SimulationListener implements ActionListener{
				
				/* (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent arg0){
					frame.removeView();
					frame.addView(new SimulationView(frame));
				}
			}
			
			//Functionality of MapBuilderButton-New Window (SystemPrint.java)
			/**
			 * The listener interface for receiving mapBuilder events. The class
			 * that is interested in processing a mapBuilder event implements
			 * this interface, and the object created with that class is
			 * registered with a component using the component's
			 * <code>addMapBuilderListener<code> method. When
			 * the mapBuilder event occurs, that object's appropriate
			 * method is invoked.
			 *
			 * @see MapBuilderEvent
			 */
			public class MapBuilderListener implements ActionListener{
				
				/* (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent arg0){
					frame.removeView();
					frame.addView(new RoadEditorView(frame));
					
				}
			}
			
			//Functionality of ExitButton
			/**
			 * The listener interface for receiving exit events. The class that
			 * is interested in processing a exit event implements this
			 * interface, and the object created with that class is registered
			 * with a component using the component's
			 * <code>addExitListener<code> method. When
			 * the exit event occurs, that object's appropriate
			 * method is invoked.
			 *
			 * @see ExitEvent
			 */
			public class ExitListener implements ActionListener{
				
				/* (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent arg0){
					System.exit(0);
				}
			}
}
