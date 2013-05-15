package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import service.Service;

import model.Order;
import model.Suborder;
import model.Trailer;
import model.Type;

public class HandleOrderPane extends JPanel {
	private JTextField txtOrderid;
	private JTextField txtTotalweight;
	private JTextField txtMargin;
	private JLabel lblOrderid;
	private JLabel lblTotalWeight;
	private JLabel lblMargin;
	private JLabel lblType;
	private JButton btnSave;
	private JButton btnModifysub;
	private JButton btnRemovesub;
	private JButton btnAddsub;
	private JButton btnSearchordid;
	private JButton btnUpdate;
	private JButton btnDeleteord;
	private JTextField txtSearchordid;
	private JScrollPane scrollPane_1;
	private JList<Order> lstOrders;
	private DefaultListModel<Order> ordersModel;
	private JLabel lblOrders;
	private JList<Suborder> lstSub;
	private DefaultListModel<Suborder> suborderModel;
	private JComboBox<Type> cmbxCargo;
	private DefaultComboBoxModel<Type> cmbxCargoModel;
	private Controller controller;
	private JFrame owner;

	/**
	 * Create the panel.
	 */
	public HandleOrderPane(JFrame owner) {
		setLayout(null);
		 
		this.owner=owner;
		controller=new Controller();
		lblOrderid = new JLabel("OrderID #");
		lblOrderid.setBounds(10, 28, 67, 14);
		add(lblOrderid);
		
		txtOrderid = new JTextField();
		txtOrderid.setText("OrderID");
		txtOrderid.setBounds(10, 53, 86, 20);
		add(txtOrderid);
		txtOrderid.setColumns(10);
		
		lblTotalWeight = new JLabel("Total Weight :");
		lblTotalWeight.setBounds(10, 95, 86, 14);
		add(lblTotalWeight);
		
		txtTotalweight = new JTextField();
		txtTotalweight.setText("TotalWeight");
		txtTotalweight.setBounds(10, 120, 86, 20);
		add(txtTotalweight);
		txtTotalweight.setColumns(10);
		
		lblMargin = new JLabel("Margin :");
		lblMargin.setBounds(10, 164, 67, 14);
		add(lblMargin);
		
		txtMargin = new JTextField();
		txtMargin.setText("Margin");
		txtMargin.setBounds(10, 189, 86, 20);
		add(txtMargin);
		txtMargin.setColumns(10);
		
		lblType = new JLabel("Type :");
		lblType.setBounds(10, 228, 46, 14);
		add(lblType);
		
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(controller);
		btnSave.setBounds(10, 301, 89, 23);
		add(btnSave);
		
//		btnModifysub = new JButton("ModifySub");
//		btnModifysub.setBounds(194, 188, 89, 23);
//		add(btnModifysub);
		
//		btnRemovesub = new JButton("RemoveSub");
//		btnRemovesub.addActionListener(controller);
//		btnRemovesub.setBounds(194, 219, 89, 23);
//		add(btnRemovesub);
		
		btnAddsub = new JButton("AddSub");
		btnAddsub.addActionListener(controller);
		btnAddsub.setBounds(194, 252, 89, 23);
		add(btnAddsub);
		
		btnSearchordid = new JButton("SearchOrdID");
		btnSearchordid.addActionListener(controller);
		btnSearchordid.setBounds(320, 24, 106, 23);
		add(btnSearchordid);
		
		btnUpdate = new JButton("UpdateOrd");
		btnUpdate.addActionListener(controller);
		btnUpdate.setBounds(320, 58, 106, 23);
		add(btnUpdate);
		
		btnDeleteord = new JButton("DeleteOrd");
		btnDeleteord.addActionListener(controller);
		btnDeleteord.setBounds(320, 95, 106, 23);
		add(btnDeleteord);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 186, 106, 152);
		add(scrollPane);
		
		lstSub = new JList<Suborder>();
		suborderModel =new DefaultListModel<Suborder>();
		lstSub.setModel(suborderModel);
		scrollPane.setViewportView(lstSub);
		
		JLabel lblSuborders = new JLabel("Suborders :");
		lblSuborders.setBounds(345, 164, 67, 14);
		add(lblSuborders);
		
		txtSearchordid = new JTextField();
		txtSearchordid.setText("SearchOrdID");
		txtSearchordid.setBounds(473, 25, 131, 20);
		add(txtSearchordid);
		txtSearchordid.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(473, 71, 131, 267);
		add(scrollPane_1);
		
		lstOrders = new JList<Order>();
		scrollPane_1.setViewportView(lstOrders);
		ordersModel = new DefaultListModel<Order>();
		lstOrders.setModel(ordersModel);
		lstOrders.addListSelectionListener(controller);
		
		lblOrders = new JLabel("Orders :");
		lblOrders.setBounds(506, 56, 98, 14);
		add(lblOrders);
		
		cmbxCargo = new JComboBox<Type>();
		cmbxCargo.setBounds(10, 253, 86, 20);
		add(cmbxCargo);
		
		cmbxCargoModel =new DefaultComboBoxModel<Type>();
		cmbxCargoModel.addElement(Type.CHRISTMAS_TREE);
		cmbxCargoModel.addElement(Type.BIN);
		cmbxCargoModel.addElement(Type.BOX);
		cmbxCargo.setModel(cmbxCargoModel);
		
		fillOrders();

	}
	
	private void fillOrders()
	{
		ordersModel.clear();
		for(Order o:Service.getInstance().getAllOrders())
		{
			ordersModel.addElement(o);
		}
		
	}
	
	private void fillSubOrders()
	{
		if(lstOrders.getSelectedIndex()!=-1)
		{
			suborderModel.clear();
			for(Suborder s: lstOrders.getSelectedValue().getlSuborder())
			{
				suborderModel.addElement(s);
			}
		}
	}
	private JFrame getFrame()
	{
		return this.owner;
	}
	private class Controller implements ActionListener, ListSelectionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
		        if(e.getSource()==btnAddsub)
		        {
		        	
		        	SuborderDialog subDioalog=new SuborderDialog(lstSub.getSelectedValue(),getFrame(), ModalityType.DOCUMENT_MODAL);
		        	subDioalog.setVisible(true);
		        	if(subDioalog.getSuborder()!=null)
		        	lstOrders.getSelectedValue().addSuborder(subDioalog.getSuborder());
		        	fillSubOrders();
		        	
		        }
		        
		      
		        
		        if(e.getSource()==btnUpdate)
		        {
		        	Order order=lstOrders.getSelectedValue();
		        	txtOrderid.setText(order.getOrderID()+"");
		        	txtMargin.setText(order.getMargin()+"");
		        	txtTotalweight.setText(order.getTotalWeight()+"");
		        	
		        	int i=0;
					if(order.getlType().equals(Type.CHRISTMAS_TREE))
						i=0;
					if(order.getlType().equals(Type.BIN))
						i=1;
					if(order.getlType().equals(Type.BOX))
						i=2;
					cmbxCargo.setSelectedIndex(i);
		        	
		        }
		        
		        if(e.getSource()==btnDeleteord)
		        {
		        	Service.getInstance().removeOrder(lstOrders.getSelectedValue());
		        	fillOrders();
		        }
		        
		        if(e.getSource()==btnSave)
		        {
		        	if(lstOrders.getSelectedIndex()!=-1)
		        	{
		        		Service.getInstance().updateOrder(lstOrders.getSelectedValue(),
			        			Integer.parseInt(txtTotalweight.getText()) , Integer.parseInt(txtMargin.getText())
			        			, cmbxCargo.getPrototypeDisplayValue());
		        	}
		        	else
		        	Service.getInstance().createOrder(Integer.parseInt(txtOrderid.getText()),
		        			Integer.parseInt(txtTotalweight.getText()) , Integer.parseInt(txtMargin.getText())
		        			, cmbxCargo.getPrototypeDisplayValue());
		        	fillOrders();
		        }
		        
		       
		        if(e.getSource()==btnSearchordid)
		        {

					Order found = null;

					String target= txtSearchordid.getText();
					
					int left = 0;
					int right = ordersModel.getSize() - 1;
					int middle = 0;
					while (found == null && left <= right) {
						middle = (right + left) / 2;
						if (ordersModel.get(middle).getOrderID()== Integer.parseInt(target)) {
							found = ordersModel.get(middle);
						} else if (ordersModel.get(middle).getOrderID()>Integer.parseInt(target))
							{right = middle - 1;
							
							}
							
						else
							left = middle + 1;
					}

					if(found!=null)
					{lstOrders.setSelectedValue(found, true);}
		        }
			
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource()==lstOrders)
			{
				fillSubOrders();
			}
		}
		
	}
	

}
