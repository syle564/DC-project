package gui;

import javax.swing.DefaultComboBoxModel;
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

import model.Load;
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
	private JComboBox<LoadingDock> cmbSelectDock;

	private DefaultComboBoxModel<LoadingDock> model;
	private JTable table;

	private Service  service=Service.getInstance();
	/**
	 * Create the panel.
	 */
	public WareHousePanel() {
		setLayout(null);
		
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 59, 645, 204);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"EstimatedStart", "EstimtedEnd", "ActualStart", "ActualEnd", "TrailerID"
			}
		));
		scrollPane.setViewportView(table);
		model =new DefaultComboBoxModel<LoadingDock>();
		
		cmbSelectDock = new JComboBox<LoadingDock>();
		cmbSelectDock.setBounds(570, 28, 105, 20);
		cmbSelectDock.setModel(model);
		add(cmbSelectDock);
		
		btnBeginLoad = new JButton("Begin Load");
		btnBeginLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateTableView();
			}
		});
		btnBeginLoad.setBounds(471, 292, 89, 23);
		this.add(btnBeginLoad);
		
		btnLoadApproved = new JButton("Load Approved");
		btnLoadApproved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	table.get
			clearTable();		
			}
		});
		btnLoadApproved.setBounds(570, 292, 105, 23);
		add(btnLoadApproved);
		fillDocks();
	}
	
	private void fillDocks()
	{
		for(LoadingDock lD:service.getAvailableDocks())
		model.addElement(lD);
	}
	private void clearTable()
	{
		DefaultTableModel model= (DefaultTableModel)table.getModel();
		
	    for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
	        model.removeRow(i);
	    }
	}
	private void updateTableView()
	{   clearTable();
		DefaultTableModel model= (DefaultTableModel)table.getModel();
		for(Load l:service.getLoadsFrom((LoadingDock) cmbSelectDock.getSelectedItem()))
		{
			model.addRow(new Object[]{l.getEstStartTime(),l.getEstEndTime(),l.getActualBegTime(),
					l.getActtualEndTime(),l.getlSuborder().getlTrailer().getTrailerID()});
		}
	}
}
