package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

public class DCPlannerUI extends JDialog {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private CreateTrailerPane createTrailerPane; 
	private HandleOrderPane handleOrderPane;




	/**
	 * Create the frame.
	 */
	public DCPlannerUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DCPlannerUI.class.getResource("/resources/DCLogo.jpeg")));
		setTitle("DanishCrownUI");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 661, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		createTrailerPane =new CreateTrailerPane();
		tabbedPane.add("Manage trailers",createTrailerPane);
		
		handleOrderPane= new HandleOrderPane(this);
		tabbedPane.add("Orders",handleOrderPane);
	}


}
