package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class MapChoiceView {
	private JPanel jPanel = new JPanel();
    JFrame f = new JFrame("Choose Map");
    
	public MapChoiceView(){
    
	jPanel.setSize(400, 300);
    JButton button = new JButton("Open");
    JButton button1 = new JButton("Cancel");
    
	String labels[] = { "A", "B", "C", "D","E", "F", "G", "H","I", "J" };
    
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    JList<String> list = new JList<String>(labels);

    JScrollPane scrollPane = new JScrollPane(list);
    Container contentPane = f.getContentPane();
    contentPane.add(scrollPane, BorderLayout.NORTH);
    // contentPane.setBounds(10, 10, 10, 10);//setBorder(new EmptyBorder(10,10,10,10));

    f.add(jPanel);
    f.setSize(350,250);
    f.setVisible(true);
    f.setLocationRelativeTo(null);
    
    button.setAlignmentY(Component.CENTER_ALIGNMENT);
    jPanel.add(button);
    button1.setAlignmentY(Component.CENTER_ALIGNMENT);
    jPanel.add(button1,BorderLayout.SOUTH);
    button.addActionListener(new OpenListener());
    button1.addActionListener(new CancelListener());
    
  }
	public class CancelListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			f.dispose();
		}
	}
    public class OpenListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			f.dispose();
		}
	}
}
