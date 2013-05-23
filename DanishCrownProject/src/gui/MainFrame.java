package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.xml.bind.annotation.XmlElement.DEFAULT;

import model.Load;
import model.LoadingDock;
import model.Order;
import model.Status;
import model.Suborder;
import model.Trailer;
import model.Type;
import service.DU;
import service.LoadTimeComparator;
import service.Service;
import dao.DataBase;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Comparator;
import java.awt.Toolkit;


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
	private ImageIcon imgDcLogo;
	private JLabel lblDclogo;
	private WareHouseUI wareHouseUI;
	private TruckerUIFrame truckerUI;
	private DCPlannerUI dcPlannerUI;
	private TruckerSignOutFrame truckerSignOutFrame;
	private Controller controller;
	private JButton btnSignOut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Service service=Service.getInstance();
	    LoadingDock loadingD1=service.createLoadingDock(1, Type.BOX, Status.OPEN);
	    LoadingDock loadingD2=service.createLoadingDock(2, Type.BOX, Status.OPEN);
	    LoadingDock loadingD3=service.createLoadingDock(3, Type.CHRISTMAS_TREE, Status.OPEN);
	    LoadingDock loadingD4=service.createLoadingDock(4, Type.CHRISTMAS_TREE, Status.OPEN);
	    LoadingDock loadingD5=service.createLoadingDock(5, Type.BIN, Status.OPEN);
	    LoadingDock loadingD6=service.createLoadingDock(6, Type.BIN, Status.OPEN);
		Order o1=service.createOrder(1, 2000, 10, Type.BOX);
		Order o2=service.createOrder(2, 2000, 10, Type.CHRISTMAS_TREE);
		Order o3=service.createOrder(3, 2000, 10, Type.BIN);
		Trailer t1=service.createTrailer("1", "David", "Fletcher", "310-808-5243", Type.BOX);
		Trailer t2=service.createTrailer("2", "Bob", "Michel", "415-846-1688", Type.BOX);
		Trailer t3=service.createTrailer("3", "Jim", "Bravo", "720-318-9049", Type.CHRISTMAS_TREE);
		Trailer t4=service.createTrailer("4", "Adam", "Hall", "323-216-2201", Type.CHRISTMAS_TREE);
		Trailer t5=service.createTrailer("5", "Steve", "Tailer", "305-245-1641", Type.BIN);
		Trailer t6=service.createTrailer("6", "Finn", "Peir", "365-245-1641", Type.BIN);
		Suborder s1=service.createSuborder(30, 1000, DU.createDate(), o1, t1);
		Suborder s2=service.createSuborder(25, 1000, DU.createDate(), o1, t1);
		Suborder s3=service.createSuborder(25, 1000, DU.createDate(), o2, t2);
		Suborder s4=service.createSuborder(25, 1000, DU.createDate(), o2, t2);
		Suborder s5=service.createSuborder(25, 2000, DU.createDate(), o3, t3);
		
		
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
		controller=new Controller();
		
		
		frmDanishCrownProject = new JFrame();
		frmDanishCrownProject.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/resources/DCLogo.jpeg")));
		frmDanishCrownProject.setTitle("Danish Crown Project");
		frmDanishCrownProject.setBounds(100, 100, 565, 366);
		frmDanishCrownProject.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDanishCrownProject.getContentPane().setLayout(null);
		
		btnDanishPlannerUi = new JButton("D.C. Planner UI");
		btnDanishPlannerUi.addActionListener(controller);
		btnDanishPlannerUi.setBounds(28, 242, 124, 37);
		frmDanishCrownProject.getContentPane().add(btnDanishPlannerUi);
		
		btnTruckerUi = new JButton("Trucker Sign In");
		btnTruckerUi.addActionListener(controller);
		btnTruckerUi.setBounds(200, 280, 130, 37);
		frmDanishCrownProject.getContentPane().add(btnTruckerUi);
		
		btnWarehouseUi = new JButton("Warehouse UI");
		btnWarehouseUi.addActionListener(controller);
		btnWarehouseUi.setBounds(373, 242, 110, 37);
		frmDanishCrownProject.getContentPane().add(btnWarehouseUi);
		
		btnSignOut = new JButton("Trucker Sign Out");
		btnSignOut.addActionListener(controller);
		btnSignOut.setBounds(200, 209, 130, 37);
		frmDanishCrownProject.getContentPane().add(btnSignOut);
		
		lblDclogo = new JLabel("DClogo");
		lblDclogo.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/DCLogo_2.jpg")));
		lblDclogo.setBounds(0, 0, 551, 327);
		frmDanishCrownProject.getContentPane().add(lblDclogo);
		
			
		 
	}
	/** 
	 *Custom Event Listener
	 */
 private class Controller implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnWarehouseUi)
			{
				 wareHouseUI=new WareHouseUI();
				wareHouseUI.setVisible(true);
			}
			
			
			if(e.getSource()==btnTruckerUi)
			{
				truckerUI=new TruckerUIFrame();
				truckerUI.setVisible(true);
			}
			
			if(e.getSource()==btnDanishPlannerUi)
			{
				dcPlannerUI=new DCPlannerUI();
				dcPlannerUI.setVisible(true);
			}
			if(e.getSource()==btnSignOut)
			{
				truckerSignOutFrame =new TruckerSignOutFrame();
				truckerSignOutFrame.setVisible(true);
			}
		}
		
	}
}
