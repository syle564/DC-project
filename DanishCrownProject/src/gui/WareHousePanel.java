package gui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;

import model.LoadingDock;

import service.Service;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.JList;

public class WareHousePanel extends JPanel {
	private JScrollPane scrollPane;
	private JButton btnBeginLoad;
	private JButton btnLoadApproved;
	private JComboBox cmbSelectDock;

	private DefaultListModel model;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public WareHousePanel() {
		setLayout(null);
		
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 59, 420, 204);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		model =new DefaultListModel<>();
		
		cmbSelectDock = new JComboBox();
		cmbSelectDock.setBounds(311, 23, 105, 20);
		add(cmbSelectDock);
		
		btnBeginLoad = new JButton("Begin Load");
		btnBeginLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model= (DefaultTableModel)table.getModel();
				model.addRow(new Object[]{"dsd","dsds","sdd","sd","sd"});
			}
		});
		btnBeginLoad.setBounds(209, 292, 89, 23);
		this.add(btnBeginLoad);
		
		btnLoadApproved = new JButton("Load Approved");
		btnLoadApproved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model= (DefaultTableModel)table.getModel();
				
				    for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
				        model.removeRow(i);
				    }
				

				
			}
		});
		btnLoadApproved.setBounds(311, 292, 105, 23);
		add(btnLoadApproved);

	}
}
