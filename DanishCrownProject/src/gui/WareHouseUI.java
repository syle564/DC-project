package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class WareHouseUI extends JFrame {

	private JPanel contentPane;
	private WareHousePanel wareHousePanel;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public WareHouseUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		wareHousePanel=new WareHousePanel();
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
	    tabbedPane.add("Loading Times",wareHousePanel);
	}

}
