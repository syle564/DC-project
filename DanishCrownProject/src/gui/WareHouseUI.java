package gui;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

public class WareHouseUI extends JDialog {

	private JPanel contentPane;
	private WareHousePanel wareHousePanel;
	private JTabbedPane tabbedPane;

	/**
	 * Create the frame.
	 */
	public WareHouseUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WareHouseUI.class.getResource("/resources/DCLogo.jpeg")));
		setTitle("WareHouseUI");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 734, 397);
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
