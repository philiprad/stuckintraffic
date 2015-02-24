package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Main;

public class StuckInTrafficController {

		private StuckInTrafficView theView;
		private StuckInTrafficModel theModel;
		
		public StuckInTrafficController(StuckInTrafficView theView, StuckInTrafficModel theModel)
		{
			this.theView = theView;
			this.theModel = theModel;
			
			this.theView.addCalculationListener(new CalculateListener());
			this.theView.addExitListener(new ExitListener());
			this.theView.addSimulationListener(new SimulationListener());
			this.theView.addMapBuilderListener(new MapBuilderListener());
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
		
		//Functionality of CalculateButton 
		public class CalculateListener implements ActionListener{
			
			public void actionPerformed(ActionEvent arg0){
				int firstNumber,secondNumber = 0;
				
				try{
					firstNumber = theView.getFirstNumber();
					secondNumber = theView.getSecondNumber();
					
					theModel.addTwoNumbers(firstNumber, secondNumber);
					
					theView.setCalcSolution(theModel.getCalculationValue());
					
				}	 	
			
				//exception if user do something else than entering integers
				catch(NumberFormatException ex){
					theView.displayErrorMessage("You need to enter 2 integers");
				}
			}
			
		}
			
}

