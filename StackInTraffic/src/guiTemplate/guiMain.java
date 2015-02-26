package guiTemplate;

public class guiMain {
	public static void main(String[] args){
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	ApplicationFrame frame = new ApplicationFrame();
	        		frame.setVisible(true);
	        		frame.addView(new MainView(frame));
	            }
	        });
	}
}
