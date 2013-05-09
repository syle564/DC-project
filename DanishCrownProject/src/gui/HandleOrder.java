package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class HandleOrder extends JPanel {
	private JTextField txtOrderid;
	private JTextField txtTotalweight;
	private JTextField txtMargin;
	private JTextField txtType;
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
	private JList list_1;
	private JLabel lblOrders;
	private JList list;

	/**
	 * Create the panel.
	 */
	public HandleOrder() {
		setLayout(null);
		
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
		
		txtType = new JTextField();
		txtType.setText("Type");
		txtType.setBounds(10, 253, 86, 20);
		add(txtType);
		txtType.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(10, 301, 89, 23);
		add(btnSave);
		
		btnModifysub = new JButton("ModifySub");
		btnModifysub.setBounds(194, 188, 89, 23);
		add(btnModifysub);
		
		btnRemovesub = new JButton("RemoveSub");
		btnRemovesub.setBounds(194, 219, 89, 23);
		add(btnRemovesub);
		
		btnAddsub = new JButton("AddSub");
		btnAddsub.setBounds(194, 252, 89, 23);
		add(btnAddsub);
		
		btnSearchordid = new JButton("SearchOrdID");
		btnSearchordid.setBounds(320, 24, 106, 23);
		add(btnSearchordid);
		
		btnUpdate = new JButton("UpdateOrd");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUpdate.setBounds(320, 58, 106, 23);
		add(btnUpdate);
		
		btnDeleteord = new JButton("DeleteOrd");
		btnDeleteord.setBounds(320, 95, 106, 23);
		add(btnDeleteord);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 186, 106, 152);
		add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
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
		
		list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		
		lblOrders = new JLabel("Orders :");
		lblOrders.setBounds(506, 56, 98, 14);
		add(lblOrders);

	}

}
