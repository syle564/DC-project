package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;

import model.Type;

public class CreateTrailerPane extends JPanel {
	private JLabel lblCompany;
	private JTextField txtCompany;
	private JTextField txtTrailerid;
	private JLabel lblTrailerid;
	private JTextField txtDriver;
	private JTextField txtSearchid;
	private JScrollPane scrollPane;
	private JList list;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSearchid;
	private JLabel lblTrucksInSystem;
	private JComboBox<Type> cmbxCargo;
	private DefaultComboBoxModel<Type> cmbxCargoModel;

	/**
	 * Create the panel.
	 */
	public CreateTrailerPane() {
		setLayout(null);
		
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
		btnSave.setBounds(10, 341, 89, 23);
		add(btnSave);
		
		btnSearchid = new JButton("SearchID");
		btnSearchid.setBounds(206, 39, 89, 23);
		add(btnSearchid);
		
		txtSearchid = new JTextField();
		txtSearchid.setText("SearchID");
		txtSearchid.setBounds(322, 40, 110, 20);
		add(txtSearchid);
		txtSearchid.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(206, 111, 89, 23);
		add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(206, 144, 89, 23);
		add(btnDelete);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(322, 115, 110, 260);
		add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		lblTrucksInSystem = new JLabel("Trucks in System :");
		lblTrucksInSystem.setBounds(327, 88, 105, 14);
		add(lblTrucksInSystem);
		
		cmbxCargo = new JComboBox();
		cmbxCargo.setBounds(10, 299, 117, 20);
		add(cmbxCargo);
		
		cmbxCargoModel =new DefaultComboBoxModel<Type>();
		cmbxCargoModel.addElement(Type.CHRISTMAS_TREE);
		cmbxCargoModel.addElement(Type.BIN);
		cmbxCargoModel.addElement(Type.BOX);
		cmbxCargo.setModel(cmbxCargoModel);

	}
}
