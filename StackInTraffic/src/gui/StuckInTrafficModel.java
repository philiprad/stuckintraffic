package gui;
public class StuckInTrafficModel {
	//contains the data
	private int calculationValue;//final value
	//it performs the method that is needed 
	public void addTwoNumbers(int firstNumber, int secondNumber){//calculation to perform this data
	
			calculationValue = firstNumber + secondNumber; 
	}
	//provide a way to get my value-output-provide access to the data
	public int getCalculationValue(){
		return calculationValue;
	}
}