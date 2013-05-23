package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import model.Load;
import model.LoadingDock;
import model.Trailer;
import service.DU;
import service.Service;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.EventListener;
import java.util.Timer;
import java.util.TimerTask;


public class WareHousePanel extends JPanel {
	
	private JScrollPane scrollPane;
	private JButton btnBeginLoad;
	private JButton btnLoadApproved;
	private JComboBox<LoadingDock> cmbSelectDock;

	private DefaultComboBoxModel<LoadingDock> model;
	private JTable table;
	private Controller controller = new Controller(); 

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
				"EstimatedStart", "EstimtedEnd", "ActualStart", "ActualEnd", "Completed","TrailerID","Load",
			}
		));
		scrollPane.setViewportView(table);
		model =new DefaultComboBoxModel<LoadingDock>();
		
		cmbSelectDock = new JComboBox<LoadingDock>();
		cmbSelectDock.addActionListener(controller);
		cmbSelectDock.setBounds(570, 28, 105, 20);
		cmbSelectDock.setModel(model);
		add(cmbSelectDock);
		
		btnBeginLoad = new JButton("Begin Load");
		btnBeginLoad.addActionListener(controller);
		btnBeginLoad.setBounds(471, 292, 89, 23);
		this.add(btnBeginLoad);
		
		btnLoadApproved = new JButton("Load Approved");
		btnLoadApproved.addActionListener(controller);
		btnLoadApproved.setBounds(570, 292, 105, 23);
		add(btnLoadApproved);
		fillDocks();
		
		updateTableView();
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
					l.getActtualEndTime(),l.isCompleted(),l.getlSuborder().getlTrailer().getTrailerID(),l});
		}
	}
	
	private Component getthis()
	{
		return this;
	}
	
	private class Controller implements ActionListener, EventListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== btnBeginLoad){
			Load load=(Load)table.getValueAt(table.getSelectedRow(), 6);
			service.beginLoad(load, (LoadingDock)cmbSelectDock.getSelectedItem());
			updateTableView();
	
			}
			
			if(e.getSource()==btnLoadApproved)
			{
			String trailerID=(String)table.getValueAt(table.getSelectedRow(), 5);
			Load load=(Load)table.getValueAt(table.getSelectedRow(), 6);
			if(service.completeLoad(load, (LoadingDock)cmbSelectDock.getSelectedItem(), trailerID))
				{updateTableView();
				Trailer t=load.getlSuborder().getlTrailer();
				new Reminder(DU.createDatePlusMinuts(t.getArrivalTime(), t.getRestTime()));
				}
			}
			if(e.getSource()==cmbSelectDock)
			{
				updateTableView();
			}			
		}	
}
	
	
	public class Reminder {
	    Timer timer;

	    public Reminder(Date timeOfExecution) {
	        timer = new Timer();
	        timer.schedule(new RemindTask(), timeOfExecution );		
	    }

	    class RemindTask extends TimerTask {
	        public void run() {
	        	JOptionPane.showMessageDialog(getthis(), "Trailer ready for pickup!");
	            timer.cancel(); 
	        }
	    }
	}
}
