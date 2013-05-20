package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

import service.DU;
import service.Service;

import model.Order;
import model.Suborder;
import model.Trailer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuborderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtLoadingweight;
	private JTextField txtLoadingtime;
	private JTextField txtLoadingdate;
	private JLabel lblLoaingWeight;
	private JLabel lblLoadingTime;
	private JLabel lblLoadingDate;
	private JLabel lblListOfAvailable;
	private Suborder suborder;
	private JList<Trailer> lstTrailers;
	private DefaultListModel<Trailer> trailerModel;
	private JButton okButton;
	private JButton cancelButton;
	private Controller controller;
	private Order order;



	/**
	 * Create the dialog.
	 */
	public SuborderDialog(Order order,Suborder suborder, JFrame owner, ModalityType modality) {
		
		
		
		super(owner, modality);
		this.suborder=suborder;
		controller= new Controller();
		this.order=order;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(SuborderDialog.class.getResource("/resources/DCLogo.jpeg")));
		setTitle("Suborder Dialog");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblLoaingWeight = new JLabel("Loading Weight :");
			lblLoaingWeight.setBounds(10, 24, 86, 14);
			contentPanel.add(lblLoaingWeight);
		}
		{
			txtLoadingweight = new JTextField();
			txtLoadingweight.setText("LoadingWeight");
			txtLoadingweight.setBounds(10, 49, 86, 20);
			contentPanel.add(txtLoadingweight);
			txtLoadingweight.setColumns(10);
		}
		{
			lblLoadingTime = new JLabel("Loading Time :");
			lblLoadingTime.setBounds(10, 80, 75, 14);
			contentPanel.add(lblLoadingTime);
		}
		{
			txtLoadingtime = new JTextField();
			txtLoadingtime.setText("LoadingTime");
			txtLoadingtime.setBounds(10, 105, 86, 20);
			contentPanel.add(txtLoadingtime);
			txtLoadingtime.setColumns(10);
		}
		{
			lblLoadingDate = new JLabel("Loading Date :");
			lblLoadingDate.setBounds(10, 136, 75, 14);
			contentPanel.add(lblLoadingDate);
		}
		{
			txtLoadingdate = new JTextField();
			txtLoadingdate.setText("yyyy-mm-dd");
			txtLoadingdate.setBounds(10, 161, 86, 20);
			contentPanel.add(txtLoadingdate);
			txtLoadingdate.setColumns(10);
		}
		{
			lblListOfAvailable = new JLabel(" Available Trucks :");
			lblListOfAvailable.setBounds(261, 24, 109, 14);
			contentPanel.add(lblListOfAvailable);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(261, 49, 86, 169);
			contentPanel.add(scrollPane);
			{
				lstTrailers = new JList<Trailer>();
				trailerModel= new DefaultListModel<Trailer>();
				lstTrailers.setModel(trailerModel);
				scrollPane.setViewportView(lstTrailers);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(controller);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(controller);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			fillTrailers();
		}
	}
	
	private void fillTrailers()
	{
		trailerModel.clear();
		for(Trailer t:Service.getInstance().getAvailbleTrailers())
		{
			trailerModel.addElement(t);
		}
	}
	
	public Suborder getSuborder()
	{
		return suborder;
	}
	
	private  SuborderDialog getThis()
	{
		return this;
	}
	class Controller implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==okButton)
			{
				if(lstTrailers.getSelectedIndex()!=-1)
				{
				Service.getInstance().createSuborder(Integer.parseInt(txtLoadingtime.getText()),Integer.parseInt(txtLoadingweight.getText()),
						DU.createDate(txtLoadingdate.getText()),order,lstTrailers.getSelectedValue());
				getThis().setVisible(false);
				}
			}
				
			
			if(e.getSource()==cancelButton)
			{
				getThis().dispose();
			}
		}
		
	}


}
