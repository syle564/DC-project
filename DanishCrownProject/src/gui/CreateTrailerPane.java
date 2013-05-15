package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;

import service.Service;

import model.Trailer;
import model.Type;

public class CreateTrailerPane extends JPanel {
	private JLabel lblCompany;
	private JTextField txtCompany;
	private JTextField txtTrailerid;
	private JLabel lblTrailerid;
	private JTextField txtDriver;
	private JTextField txtSearchid;
	private JScrollPane scrollPane;
	private JList<Trailer> listTrailer;
	private DefaultListModel<Trailer> trailerModel;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSearchid;
	private JLabel lblTrucksInSystem;
	private JComboBox<Type> cmbxCargo;
	private DefaultComboBoxModel<Type> cmbxCargoModel;
	private Controller controller;

	/**
	 * Create the panel.
	 */
	public CreateTrailerPane() {
		setLayout(null);
		
		controller= new Controller();
		
		lblCompany = new JLabel("Company : ");
		lblCompany.setBounds(10, 43, 65, 14);
		add(lblCompany);
		
		txtCompany = new JTextField();
		txtCompany.setText("Company");
		txtCompany.setBounds(10, 68, 117, 20);
		add(txtCompany);
		txtCompany.setColumns(10);
		
		lblTrailerid = new JLabel("TrailerID #");
		lblTrailerid.setBounds(10, 115, 65, 14);
		add(lblTrailerid);
		
		txtTrailerid = new JTextField();
		txtTrailerid.setText("TrailerID");
		txtTrailerid.setBounds(10, 145, 117, 20);
		add(txtTrailerid);
		txtTrailerid.setColumns(10);
		
		JLabel lblDriver = new JLabel("Driver :");
		lblDriver.setBounds(10, 189, 46, 14);
		add(lblDriver);
		
		txtDriver = new JTextField();
		txtDriver.setText("Driver");
		txtDriver.setBounds(10, 224, 117, 20);
		add(txtDriver);
		txtDriver.setColumns(10);
		
		JLabel lblCargoType = new JLabel("Cargo Type :");
		lblCargoType.setBounds(10, 274, 65, 14);
		add(lblCargoType);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(controller);
		btnSave.setBounds(10, 341, 89, 23);
		add(btnSave);
		
		btnSearchid = new JButton("SearchID");
		btnSearchid.addActionListener(controller);
		btnSearchid.setBounds(206, 39, 89, 23);
		add(btnSearchid);
		
		txtSearchid = new JTextField();
		txtSearchid.setText("SearchID");
		txtSearchid.setBounds(322, 40, 110, 20);
		add(txtSearchid);
		txtSearchid.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(controller);
		btnUpdate.setBounds(206, 111, 89, 23);
		add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(controller);
		btnDelete.setBounds(206, 144, 89, 23);
		add(btnDelete);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(322, 115, 110, 260);
		add(scrollPane);
		
		listTrailer = new JList<Trailer>();
		scrollPane.setViewportView(listTrailer);
		
		trailerModel= new DefaultListModel<Trailer>();
		listTrailer.setModel(trailerModel);
		
		
		
		lblTrucksInSystem = new JLabel("Trucks in System :");
		lblTrucksInSystem.setBounds(327, 88, 105, 14);
		add(lblTrucksInSystem);
		
		cmbxCargo = new JComboBox<Type>();
		cmbxCargo.setBounds(10, 299, 117, 20);
		add(cmbxCargo);
		
		cmbxCargoModel =new DefaultComboBoxModel<Type>();
		cmbxCargoModel.addElement(Type.CHRISTMAS_TREE);
		cmbxCargoModel.addElement(Type.BIN);
		cmbxCargoModel.addElement(Type.BOX);
		cmbxCargo.setModel(cmbxCargoModel);
		
		fillTrailers();

	}
	
	private void fillTrailers()
	{
		trailerModel.clear();
		for(Trailer t: Service.getInstance().getAvailbleTrailers())
		{
			trailerModel.addElement(t);
		}
		System.out.println( Service.getInstance().getAvailbleTrailers());
	}
	
	
	class Controller implements ActionListener
 	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==btnSave)
			{
				if(listTrailer.getSelectedIndex()!=-1)
				{Service.getInstance().updateTrailer(listTrailer.getSelectedValue(), 
				txtTrailerid.getText(), txtCompany.getText(), txtDriver.getText(), "", (Type)cmbxCargo.getSelectedItem());}
				else{
				Service.getInstance().createTrailer(txtTrailerid.getText(), 
				txtCompany.getText(), txtDriver.getText(), "", (Type)cmbxCargo.getSelectedItem());}
				fillTrailers();
			}
			
			if(e.getSource()==btnDelete)
			{
				Service.getInstance().removeTrailer(listTrailer.getSelectedValue());
				fillTrailers();
			}
			if(e.getSource()==btnUpdate)
			{
				if(listTrailer.getSelectedIndex()!=-1)
				{
					Trailer t= listTrailer.getSelectedValue();
					txtCompany.setText(t.getCompany());
					txtDriver.setText(t.getDriver());
					txtTrailerid.setText(t.getTrailerID());
					
					int i=0;
					if(t.getlType().equals(Type.CHRISTMAS_TREE))
						i=0;
					if(t.getlType().equals(Type.BIN))
						i=1;
					if(t.getlType().equals(Type.BOX))
						i=2;
					cmbxCargo.setSelectedIndex(i);
				}
				
				
			}
			
			if(e.getSource()==btnSearchid)
			{
				Trailer found = null;

				String target= txtSearchid.getText();
				
				int left = 0;
				int right = trailerModel.getSize() - 1;
				int middle = 0;
				while (found == null && left <= right) {
					middle = (right + left) / 2;
					if (trailerModel.get(middle).getTrailerID().equals(target)) {
						found = trailerModel.get(middle);
					} else if (trailerModel.get(middle).compareTo(target)>0)
						{right = middle - 1;
						
						}
						
					else
						left = middle + 1;
				}

				if(found!=null)
				{listTrailer.setSelectedValue(found, true);}
			}
		}
		
	}
}
