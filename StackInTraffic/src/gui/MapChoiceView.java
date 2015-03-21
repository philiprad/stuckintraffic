/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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
public class MapChoiceView extends JFrame implements ActionListener{
	
	/** The j panel. */
	private JPanel jPanel = new JPanel();
    
    /** The this. */
    
    GridBuilder gridBuilder;
    
    /** The selection. */
    String selection = "";
	
	/** The panel. */
	JPanel panel;
    
    /**
	 * Instantiates a new map choice view.
	 *
	 * @param parentFrame
	 *            the parent frame
	 * @param panel
	 *            the panel
	 * @param gridBuilder
	 *            the grid builder
	 */
    @SuppressWarnings("unchecked")
	public MapChoiceView(JFrame parentFrame, JPanel panel, GridBuilder gridBuilder){
    this.panel = panel;
    this.gridBuilder = gridBuilder;
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        	JButton button = new JButton("Cancel");
        	JButton button1 = new JButton("Open");
        	ArrayList<String> mapNames = (ArrayList<String>) FileRW.readObject(MainConfig.SAVES_FILE_PATH);
        	String [] stringList = new String [mapNames.size()];
        	for(int i = 0; i<mapNames.size();i++){
        		stringList[i] = mapNames.get(i);
        	}
        	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
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
        	Container contentPane = getContentPane();
        	contentPane.add(scrollPane, BorderLayout.NORTH);
   
        	button.setAlignmentY(Component.CENTER_ALIGNMENT);
        	jPanel.add(button);
        	button1.setAlignmentY(Component.CENTER_ALIGNMENT);
        	jPanel.add(button1,BorderLayout.SOUTH);
        	button.addActionListener(new CancelListener());
        	button1.addActionListener(new OpenListener());
        	add(jPanel);
        	setLocationRelativeTo(null);
        	setPreferredSize(new Dimension(350,200));
        	pack(); 
        	setLocationRelativeTo(null);
        	setVisible(true);
        	setAlwaysOnTop (true);
        	}
    
        });
   
    
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
			MapChoiceView.this.dispose();
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
					gridBuilder = (GridBuilder) (FileRW.readObject(MainConfig.GRID_PATH + "/"+selection+MainConfig.GRID_SUFFIX));
					MapValidator.deleteEndPoints(gridBuilder);
					((RoadEditorView) panel).setGridBuilder(gridBuilder);
					((RoadEditorView) panel).buildGrid();//(gridBuilder);
					((RoadEditorView) panel).setMapName(selection);
					MapChoiceView.this.setVisible(false);
					MapChoiceView.this.dispose();
				}
			}else{
					if(selection!=""){
						//panel.removeAll();
						((SimulationView) panel).loadMap(selection);
						MapChoiceView.this.setVisible(false);
						MapChoiceView.this.dispose();
					}
			}
				
			
			
			
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
