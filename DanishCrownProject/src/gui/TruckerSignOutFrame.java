package gui;


import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import service.Service;

public class TruckerSignOutFrame extends JDialog {

	private JPanel contentPane;
	private JTextField txtTrailerID;
	private JTextField txtWeightOut;
	private JButton btnSignOut;
	private JLabel lblSignOut;
	private Controller controller;

	
	/**
	 * Create the frame.
	 */
	public TruckerSignOutFrame() {
		controller=new Controller();
		setTitle("Sign Out");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TruckerSignOutFrame.class.getResource("/resources/DCLogo.jpeg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 205, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTrailerID = new JTextField();
		txtTrailerID.setBounds(10, 47, 141, 20);
		contentPane.add(txtTrailerID);
		txtTrailerID.setColumns(10);
		
		JLabel lblTrailerId = new JLabel("Trailer ID:");
		lblTrailerId.setBounds(10, 22, 79, 14);
		contentPane.add(lblTrailerId);
		
		txtWeightOut = new JTextField();
		txtWeightOut.setBounds(10, 113, 141, 20);
		contentPane.add(txtWeightOut);
		txtWeightOut.setColumns(10);
		
		JLabel lblWeightOut = new JLabel("Weight Out:(INT)");
		lblWeightOut.setBounds(10, 93, 95, 14);
		contentPane.add(lblWeightOut);
		
		btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(controller);
		btnSignOut.setBounds(10, 201, 89, 23);
		contentPane.add(btnSignOut);
		
		lblSignOut = new JLabel("");
		lblSignOut.setBounds(20, 235, 106, 14);
		contentPane.add(lblSignOut);
	}
	
	
	
	class Controller implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSignOut)
			{
				if(Service.getInstance().signOut(txtTrailerID.getText(), Integer.parseInt(txtWeightOut.getText())))
				{lblSignOut.setForeground(Color.GREEN);
				lblSignOut.setText("Success!");}
				else{
					lblSignOut.setForeground(Color.RED);
				lblSignOut.setText("Failure");}
			}
		}
		
	}

}
