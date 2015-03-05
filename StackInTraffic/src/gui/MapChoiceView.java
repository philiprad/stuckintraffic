package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.MainConfig;
import util.FileRW;

public class MapChoiceView {
	private JPanel jPanel = new JPanel();
    JFrame f = new JFrame("Choose Map");
    String selection = "";
	
    @SuppressWarnings("unchecked")
	public MapChoiceView(){
    
	jPanel.setSize(400, 300);
    JButton button = new JButton("Cancel");
    JButton button1 = new JButton("Open");
    ArrayList<String> mapNames = (ArrayList<String>) FileRW.readObject(MainConfig.SAVES_FILE_PATH);
    String [] stringList = new String [mapNames.size()];
    for(int i = 0; i<mapNames.size();i++){
    	stringList[i] = mapNames.get(i);
    }
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    final JList<String> list = new JList<String>(stringList);
    
    
    list.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			 if (!e.getValueIsAdjusting()) {
				 selection = list.getSelectedValue();
               }			
		}
	});
    JScrollPane scrollPane = new JScrollPane(list);
    Container contentPane = f.getContentPane();
    contentPane.add(scrollPane, BorderLayout.NORTH);
    // contentPane.setBounds(10, 10, 10, 10);//setBorder(new EmptyBorder(10,10,10,10));
    f.add(jPanel);
    f.setSize(350,200);
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
