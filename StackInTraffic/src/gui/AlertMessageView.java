/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


// TODO: Auto-generated Javadoc
/**
 * The Class AlertMessageView.
 */
public class AlertMessageView implements ActionListener{
	
	/** The frame. */
	private JFrame frame =  new JFrame();
	
	
	/**
	 * Instantiates a new alert message view.
	 *
	 * @param message
	 *            the message
	 */
	public AlertMessageView(String message){
		
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(2,1);
    	panel.setLayout(gridLayout);
    	JLabel alertLabel = new JLabel(message,SwingConstants.CENTER);
    	panel.add(alertLabel);
    	JButton button = new JButton("Ok");
    	panel.add(button);
    	button.addActionListener(this);
    	frame.getContentPane().add(panel);
    	frame.setLocationRelativeTo(null);
    	
    	frame.setPreferredSize(new Dimension(400,120));
    	frame.pack(); 
    	frame.setLocationRelativeTo(null);
    	frame.setAlwaysOnTop (true);
    	frame.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		
	}

}
