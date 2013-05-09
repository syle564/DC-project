package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TruckerUIFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTrailerid;
	private JLabel lblTrailerid;
	private JTextField txtPhone;
	private JLabel lblPhone;
	private JButton btnSignIn;
	private JTextField txtResttime;
	private JLabel lblRestTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TruckerUIFrame frame = new TruckerUIFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TruckerUIFrame() {
		setTitle("TruckerUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 240, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTrailerid = new JLabel("TrailerID #");
		lblTrailerid.setBounds(34, 24, 95, 14);
		contentPane.add(lblTrailerid);
		
		txtTrailerid = new JTextField();
		txtTrailerid.setText("TrailerID");
		txtTrailerid.setBounds(34, 49, 86, 20);
		contentPane.add(txtTrailerid);
		txtTrailerid.setColumns(10);
		
		lblPhone = new JLabel("Phone #");
		lblPhone.setBounds(34, 80, 46, 14);
		contentPane.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setText("Phone");
		txtPhone.setBounds(34, 105, 86, 20);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		lblRestTime = new JLabel("Rest time :");
		lblRestTime.setBounds(34, 138, 64, 14);
		contentPane.add(lblRestTime);
		
		txtResttime = new JTextField();
		txtResttime.setText("RestTime");
		txtResttime.setBounds(34, 161, 86, 20);
		contentPane.add(txtResttime);
		txtResttime.setColumns(10);
		
		btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(34, 209, 89, 23);
		contentPane.add(btnSignIn);
	}
}
