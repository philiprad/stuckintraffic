package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoadEditorPopUpView extends JDialog implements ActionListener {
	
	JFrame frame;
	JPanel parentPanel;
	private static final long serialVersionUID = -1618785619463832603L;
	public RoadEditorPopUpView(JFrame frame,JPanel parentPanel, String title) {
		
		super(frame, title, true);
	    this.frame = frame;
	    this.parentPanel = parentPanel;
	    JPanel jPanel = new JPanel();
    	//jPanel.setSize(400, 100);
    	JPanel container = new JPanel();
        JButton backButton = new JButton("Back to Main Menu");
        JButton loadButton = new JButton("Load Map");
        JButton createButton = new JButton("Create New Map");
        JLabel welcomeLabel = new JLabel("Welcome to the StackInTraffic MapEditor!!!");
        JLabel optionLabel = new JLabel("Please choise the option below");
        jPanel.add(welcomeLabel);
        jPanel.add(optionLabel);
        JPanel jPanelButtons = new JPanel();
    	GridLayout buttonsLayout = new GridLayout(0,3);
    	jPanelButtons.setLayout(buttonsLayout);
    	jPanelButtons.add(createButton);
        jPanelButtons.add(loadButton);
        jPanelButtons.add(backButton);
        backButton.addActionListener(new BackToMenuListener());
        loadButton.addActionListener(new LoadMapListener());
        createButton.addActionListener(new CreateMapListener());
        container.setLayout(new GridLayout(2,1));
        container.add(jPanel);
        container.add(jPanelButtons);
        
        this.add(container);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,130);
        this.pack(); 
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);
        
        
        
	  }
	  public void actionPerformed(ActionEvent e) {
	    this.setVisible(false); 
	    this.dispose(); 
	  }
	  
	  public class BackToMenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			RoadEditorPopUpView.this.setVisible(false); 
			RoadEditorPopUpView.this.dispose();
			((ApplicationFrame) frame).removeView();
			frame.validate();
			JComponent component = frame.getJMenuBar();
			component.removeAll();
			((ApplicationFrame) frame).addView(new MainView((ApplicationFrame) frame));
		}
	  
	  }
	  
	  public class LoadMapListener implements ActionListener{

		
			@Override
			public void actionPerformed(ActionEvent e) { 
				RoadEditorPopUpView.this.setVisible(false); 
				RoadEditorPopUpView.this.dispose();
				MapChoiceView openMap = new MapChoiceView(frame, parentPanel, ((RoadEditorView) parentPanel).getGridBuilder());
		  }
	}
	  
	  public class CreateMapListener implements ActionListener{

			
			@Override
			public void actionPerformed(ActionEvent e) { 
				RoadEditorPopUpView.this.setVisible(false); 
				RoadEditorPopUpView.this.dispose();
				MapCreateView createMap = new MapCreateView(frame, parentPanel, ((RoadEditorView) parentPanel).getGridBuilder());
		  }
	}
}

