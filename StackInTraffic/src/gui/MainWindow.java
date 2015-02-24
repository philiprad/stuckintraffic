package gui;

import gui.StuckInTrafficController.ExitListener;
import gui.StuckInTrafficController.MapBuilderListener;
import gui.StuckInTrafficController.SimulationListener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;

public class MainWindow extends JPanel {
	
	public MainWindow (){
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
		 button2.addActionListener(new MapBuilderListener());
	}
	
	//Functionality of SimulationButton-New Window (Main.java)
			public class SimulationListener implements ActionListener{
				public void actionPerformed(ActionEvent arg0){
					Main.main(null);
							
				}
			}
			
			//Functionality of MapBuilderButton-New Window (SystemPrint.java)
			public class MapBuilderListener implements ActionListener{
				public void actionPerformed(ActionEvent arg0){
					MapBuilderButtonView.main(null); 
				}
			}
			
			//Functionality of ExitButton
			public class ExitListener implements ActionListener{
				public void actionPerformed(ActionEvent arg0){
					System.exit(0);
				}
			}
}
