package gui;

import gui.MapChoiceView.CancelListener;
import gui.MapChoiceView.OpenListener;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RoadEditorPopUpView {


    
    /** The f. */
    private JFrame f = new JFrame("Choose Map");
    
    public RoadEditorPopUpView (){
    	JPanel jPanel = new JPanel();
    	jPanel.setSize(400, 400);
        JButton button = new JButton("Back to Main Menu");
        JButton button1 = new JButton("Load Map");
        JButton button2 = new JButton("Create New Map");
        JLabel welcomeLabel = new JLabel("Welcome to the StackInTraffic MapEditor");
        JLabel optionLabel = new JLabel("Please choise the option below");
        jPanel.add(welcomeLabel);
        jPanel.add(optionLabel);
        Container contentPane = f.getContentPane();
        contentPane.add(jPanel, BorderLayout.NORTH);
        JPanel jPanelButtons = new JPanel();
    	jPanelButtons.setSize(400, 300);
        // contentPane.setBounds(10, 10, 10, 10);//setBorder(new EmptyBorder(10,10,10,10));
        f.add(jPanel);
        f.setSize(350,200);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        jPanel.add(button);
        button1.setAlignmentY(Component.CENTER_ALIGNMENT);
        jPanel.add(button1,BorderLayout.SOUTH);
       // button.addActionListener(new CancelListener());
       // button1.addActionListener(new OpenListener());
    }
}
