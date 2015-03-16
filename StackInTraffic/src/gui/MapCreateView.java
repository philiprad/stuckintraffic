/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import main.MainConfig;
import trafficInfrastructure.grid.GridBuilder;
import util.FileRW;

// TODO: Auto-generated Javadoc
/**
 * The Class MapCreateView.
 */
public class MapCreateView extends JFrame implements ActionListener{
	
	/** The j panel. */
	private JPanel jPanel = new JPanel();
    
    /** The this. */
    
	private GridBuilder gridBuilder;
    
    /** The selection. */
   
	
	/** The panel. */
    private JPanel panel;
	
	/** The list1. */
	private JComboBox<Integer> list1 ;
	
	/** The list2. */
	private JComboBox<Integer> list2;
	
	/** The name area. */
	private JTextArea nameArea = new JTextArea("");
	
	
    
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
	public MapCreateView(JFrame parentFrame, JPanel panel, GridBuilder gridBuilder){
    this.panel = panel;
    this.gridBuilder = gridBuilder;
    
	//jPanel.setSize(400, 300);
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        	JButton button = new JButton("Cancel");
        	JButton button1 = new JButton("Create");
        	JPanel comboBoxPanel = new JPanel();
        	GridLayout comboBoxLayout = new GridLayout(2,2);
        	comboBoxPanel.setLayout(comboBoxLayout);
        	comboBoxPanel.setLayout(comboBoxLayout);
        	
        	JLabel gridCreator = new JLabel("Choice the name and the grid size for you road map.",SwingConstants.CENTER);
        	JLabel gridName = new JLabel("New road name:",SwingConstants.CENTER);
        	
        	JPanel labelPanel = new JPanel();
        	GridLayout labelLayout = new GridLayout(3,1);
        	labelPanel.setLayout(labelLayout);
        	labelPanel.add(gridCreator);
        	labelPanel.add(gridName);
        	labelPanel.add(nameArea);
        	
        	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        	Integer [] intList = {10,12,14,16,18,20,22,24};
        	JLabel gridWidth = new JLabel("Grid Width",SwingConstants.CENTER);
        	JLabel gridHeight = new JLabel("Grid Height",SwingConstants.CENTER);
        	list1 = new JComboBox<Integer>(intList);
        	list2 = new JComboBox<Integer>(intList);
        	
        	/*list.addActionListener(new ListSelectionListener() {
		
        		@Override
        		public void valueChanged(ListSelectionEvent e) {
        			if (!e.getValueIsAdjusting()) {
        				selection = list.getSelectedValue();
        			}			
        		}
        	});*/
        	JScrollPane scrollPane1 = new JScrollPane(list1);
        	JScrollPane scrollPane2 = new JScrollPane(list2);
        	comboBoxPanel.add(gridWidth);
        	comboBoxPanel.add(gridHeight);
        	comboBoxPanel.add(scrollPane1);
        	comboBoxPanel.add(scrollPane2);
        	Container contentPane = getContentPane();

        	JButton cancelButton = new JButton("Cancel");
            JButton createButton = new JButton("Create");
        	
        	JPanel jPanelButtons = new JPanel();
         	GridLayout buttonsLayout = new GridLayout(0,2);
         	jPanelButtons.setLayout(buttonsLayout);
         	jPanelButtons.add(createButton);
            jPanelButtons.add(cancelButton);
            createButton.addActionListener(new CreateListener());
            cancelButton.addActionListener(new CancelListener());
            contentPane.add(labelPanel, BorderLayout.NORTH);
        	contentPane.add(comboBoxPanel,BorderLayout.CENTER);
        	contentPane.add(jPanelButtons,BorderLayout.SOUTH);
            
        	setLocationRelativeTo(null);
        	
        	setPreferredSize(new Dimension(350,300));
        	pack(); 
        	setLocationRelativeTo(null);
        	setAlwaysOnTop (true);
        	setVisible(true);
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
			MapCreateView.this.dispose();
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
    public class CreateListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			
			if (!nameArea.getText().equals("")){
				boolean isExist = false;
				ArrayList <String> nameList = (ArrayList<String>) FileRW.readObject(MainConfig.SAVES_FILE_PATH);
				if(!nameList.isEmpty()){
					for(int i=0; i<nameList.size();i++){
						if(nameArea.getText().equals(nameList.get(i))){
							isExist=true;
						}
					}
				}
				if(!isExist){
					int gridWidth = (int) list1.getSelectedItem();
					int gridHeight = (int) list2.getSelectedItem();
					gridBuilder = new GridBuilder(gridWidth, gridHeight);
					((RoadEditorView) panel).setGridBuilder(gridBuilder);
					System.out.println("grid width" + gridBuilder.getGrid().length);
					System.out.println("grid heigth"+ gridBuilder.getGrid()[0].length);
					panel.removeAll();
					((RoadEditorView) panel).updateEditorState();
					((RoadEditorView) panel).buildGrid();//(gridBuilder);
					((RoadEditorView) panel).setMapName(nameArea.getText());
					MapCreateView.this.setVisible(false);
					MapCreateView.this.dispose();
				}
				else {
					@SuppressWarnings("unused")
					AlertMessageView alert = new AlertMessageView("The map with this name exists already");
				}
			} else {
				@SuppressWarnings("unused")
				AlertMessageView alert = new AlertMessageView("Please enter the name of your new map");
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