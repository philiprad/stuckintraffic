//interface
package gui;
import java.awt.event.ActionListener;

import javax.swing.*;


public class StuckInTrafficView extends JFrame{
	
	private JTextField  firstNumber = new JTextField(10);
	private JLabel additionLabel = new JLabel("+");
	private JTextField  secondNumber = new JTextField(10);
	private JButton calculateButton = new JButton("Calculate");
	private JTextField calcSolution = new JTextField(10);
	private JButton simulationButton = new JButton("Simulation");
	private JButton mapBuilderButton = new JButton("Map Builder");
	private JButton exitButton = new JButton("Exit");
	JMenuBar menubar = new JMenuBar();
	
	StuckInTrafficView(){
		
		JPanel calcPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setJMenuBar(menubar);
		
		//Menu options - FILE(Open,Save,Exit), HELP(About)
		JMenu file = new JMenu("File");
		menubar.add(file);
		JMenuItem open = new JMenuItem ("Open");
		file.add(open);
		JMenuItem save = new JMenuItem ("Save");
		file.add(save);
		JMenuItem exit = new JMenuItem ("Exit");
		file.add(exit);
	
		JMenu help = new JMenu("Help");
		menubar.add(help);
		JMenuItem about = new JMenuItem("About");
		help.add(about);
		
		//Add boxes to the panel
		calcPanel.add(firstNumber);
		calcPanel.add(additionLabel);
		calcPanel.add(secondNumber);
		calcPanel.add(calculateButton);
		calcPanel.add(calcSolution);
		calcPanel.add(simulationButton);
		calcPanel.add(mapBuilderButton);
		calcPanel.add(exitButton);
		
		this.add(calcPanel);
		
	}
	
	public int getFirstNumber(){
		return Integer.parseInt(firstNumber.getText());
	}
	
	public int getSecondNumber(){
		return Integer.parseInt(secondNumber.getText());
	}
	
	public int getCalcSolution(){
		return Integer.parseInt(calcSolution.getText());
	}
	
	public void setCalcSolution(int solution){
		calcSolution.setText(Integer.toString(solution));
	}
	
	void addCalculationListener(ActionListener listenerForCalcButton){
		calculateButton.addActionListener(listenerForCalcButton);
	}
	
	void addSimulationListener(ActionListener listenerForSimButton){
		simulationButton.addActionListener(listenerForSimButton);
		}
	void addMapBuilderListener(ActionListener listenerForMapBuildButton){
		mapBuilderButton.addActionListener(listenerForMapBuildButton);
	}
	void addExitListener(ActionListener listenerForExitButton){
		exitButton.addActionListener(listenerForExitButton);
	}
	
	void displayErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
}


