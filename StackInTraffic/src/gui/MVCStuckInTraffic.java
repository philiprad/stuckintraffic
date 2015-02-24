package gui;

public class MVCStuckInTraffic {

	public static void main(String[] args){
		
		/*ApplicationFrame frame = new ApplicationFrame();
		frame.setVisible(true);
		frame.getContentPane().add(new MainWindow());*/
		StuckInTrafficView theView = new StuckInTrafficView();
		
		StuckInTrafficModel theModel = new StuckInTrafficModel();
		//to know with what works with
		StuckInTrafficController theController = new StuckInTrafficController(theView, theModel);
		
		theView.setVisible(true);
	}
	
}
