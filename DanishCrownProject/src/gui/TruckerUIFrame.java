package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.print.attribute.standard.JobName;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import service.Service;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class TruckerUIFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTrailerid;
	private JLabel lblTrailerid;
	private JTextField txtPhone;
	private JLabel lblPhone;
	private JButton btnSignIn;
	private JTextField txtResttime;
	private JLabel lblRestTime;
	private Controller controller;
	private JLabel lblFound;
	private JTextField txtWeightIn;
	private JLabel lblWeghtIn;


	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public TruckerUIFrame() {
		controller=new Controller();
		
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TruckerUIFrame.class.getResource("/resources/DCLogo.jpeg")));
		setTitle("TruckerUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 300);
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
		btnSignIn.addActionListener(controller);
		btnSignIn.setBounds(34, 209, 89, 23);
		contentPane.add(btnSignIn);
		
		lblFound = new JLabel("");
		
		lblFound.setBounds(10, 243, 88, 14);
		contentPane.add(lblFound);
		
		txtWeightIn = new JTextField();
		txtWeightIn.setText("10000");
		txtWeightIn.setBounds(182, 161, 86, 20);
		contentPane.add(txtWeightIn);
		txtWeightIn.setColumns(10);
		
		lblWeghtIn = new JLabel("Weght In");
		lblWeghtIn.setBounds(182, 138, 46, 14);
		contentPane.add(lblWeghtIn);
	}
	
	private class Controller implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSignIn)
			{
				if(txtPhone.getText().length()>0 && txtResttime.getText().length()>0 && txtTrailerid.getText().length()>0)
				{
					String phone,trailerId;
					int restTime=Integer.parseInt(txtResttime.getText());
					phone=txtPhone.getText();
					int weightIN=Integer.parseInt(txtWeightIn.getText());
					trailerId=txtTrailerid.getText();
					
					if(Service.getInstance().registerIn(trailerId, weightIN, restTime,phone))
					{   lblFound.setForeground(Color.GREEN);
						lblFound.setText("Success");
						txtTrailerid.setText("");
						txtPhone.setText("");
						txtResttime.setText("");
					}
							
					else {	lblFound.setForeground(Color.RED);
							lblFound.setText("Invalid ID");
					}
				}
			}
			
		}

		
		
	}
}
