/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class RoadEditorPopUpView.
 */
public class RoadEditorPopUpView extends JDialog implements ActionListener {
	
	/** The frame. */
	JFrame frame;
	
	/** The parent panel. */
	JPanel parentPanel;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1618785619463832603L;
	
	/**
	 * Instantiates a new road editor pop up view.
	 *
	 * @param frame
	 *            the frame
	 * @param parentPanel
	 *            the parent panel
	 * @param title
	 *            the title
	 */
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
	  
  	/* (non-Javadoc)
  	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
  	 */
  	public void actionPerformed(ActionEvent e) {
	    this.setVisible(false); 
	    this.dispose(); 
	  }
	  
	  /**
	 * The listener interface for receiving backToMenu events. The class that is
	 * interested in processing a backToMenu event implements this interface,
	 * and the object created with that class is registered with a component
	 * using the component's <code>addBackToMenuListener<code> method. When
	 * the backToMenu event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BackToMenuEvent
	 */
  	public class BackToMenuListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	  
	  /**
	 * The listener interface for receiving loadMap events. The class that is
	 * interested in processing a loadMap event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addLoadMapListener<code> method. When
	 * the loadMap event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see LoadMapEvent
	 */
  	public class LoadMapListener implements ActionListener{

		
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) { 
				RoadEditorPopUpView.this.setVisible(false); 
				RoadEditorPopUpView.this.dispose();
				MapChoiceView openMap = new MapChoiceView(frame, parentPanel, ((RoadEditorView) parentPanel).getGridBuilder());
		  }
	}
	  
	  /**
	 * The listener interface for receiving createMap events. The class that is
	 * interested in processing a createMap event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addCreateMapListener<code> method. When
	 * the createMap event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see CreateMapEvent
	 */
  	public class CreateMapListener implements ActionListener{

			
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) { 
				RoadEditorPopUpView.this.setVisible(false); 
				RoadEditorPopUpView.this.dispose();
				MapCreateView createMap = new MapCreateView(frame, parentPanel, ((RoadEditorView) parentPanel).getGridBuilder());
		  }
	}
}

