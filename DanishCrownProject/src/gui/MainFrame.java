package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame {
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting look & feel: " + e.getMessage());
		}
	}


	private JFrame frmDanishCrownProject;
	private JButton btnDanishPlannerUi;
	private JButton btnTruckerUi;
	private JButton btnWarehouseUi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmDanishCrownProject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDanishCrownProject = new JFrame();
		frmDanishCrownProject.setTitle("Danish Crown Project");
		frmDanishCrownProject.setBounds(100, 100, 450, 300);
		frmDanishCrownProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDanishCrownProject.getContentPane().setLayout(null);
		
		btnDanishPlannerUi = new JButton("D.C. Planner UI");
		btnDanishPlannerUi.setBounds(20, 177, 124, 37);
		frmDanishCrownProject.getContentPane().add(btnDanishPlannerUi);
		
		btnTruckerUi = new JButton("Trucker UI");
		btnTruckerUi.setBounds(176, 177, 101, 37);
		frmDanishCrownProject.getContentPane().add(btnTruckerUi);
		
		btnWarehouseUi = new JButton("Warehouse UI");
		btnWarehouseUi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame wH=new WareHouseUI();
				wH.setVisible(true);
			   
			}
		});
		btnWarehouseUi.setBounds(303, 177, 110, 37);
		frmDanishCrownProject.getContentPane().add(btnWarehouseUi);
		
			
	}
}
