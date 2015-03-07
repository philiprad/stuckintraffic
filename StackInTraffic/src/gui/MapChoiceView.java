/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
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
import trafficInfrastructure.grid.GridBuilder;
import util.FileRW;

// TODO: Auto-generated Javadoc
/**
 * The Class MapChoiceView.
 */
public class MapChoiceView {
	
	/** The j panel. */
	private JPanel jPanel = new JPanel();
    
    /** The f. */
    JFrame f = new JFrame("Choose Map");
    
    /** The selection. */
    String selection = "";
	
	/** The panel. */
	JPanel panel;
    
    /**
	 * Instantiates a new map choice view.
	 *
	 * @param panel
	 *            the panel
	 */
    @SuppressWarnings("unchecked")
	public MapChoiceView(JPanel panel){
    this.panel = panel;
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
    button.addActionListener(new CancelListener());
    button1.addActionListener(new OpenListener());
    
  }
	
	/**
	 * The listener interface for receiving cancel events. The class that is
	 * interested in processing a cancel event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addCancelListener<code> method. When
	 * the cancel event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see CancelEvent
	 */
	public class CancelListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			f.dispose();
		}
	}
    
    /**
	 * The listener interface for receiving open events. The class that is
	 * interested in processing a open event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addOpenListener<code> method. When
	 * the open event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see OpenEvent
	 */
    public class OpenListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0){
			if (panel instanceof RoadEditorView){
				System.out.println("Open existing Map");
				if(selection!=""){
					panel.removeAll();
					((RoadEditorView) panel).updateEditorState();
					GridBuilder gridBuilder = (GridBuilder) (FileRW.readObject(MainConfig.GRID_PATH + "/"+selection+MainConfig.GRID_SUFFIX));
					((RoadEditorView) panel).buildGrid(gridBuilder);
					f.dispose();	
				}
				
			}
			
			
		}
	}
}
