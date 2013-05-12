package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

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
	private Controller controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Service service=Service.getInstance();
		LoadingDock loadingD=service.createLoadingDock(1, Type.BOX, Status.OPEN);
		Order o1=service.createOrder(1, 2000, 10, Type.BOX);
		Trailer t1=service.createTrailer("1", "Cock", "Douche", "23123", Type.BOX);
		Suborder s1=service.createSuborder(30, 200, DU.createDate(), o1, t1);
		Suborder s2=service.createSuborder(25, 200, DU.createDate(), o1, t1);
		service.registerIn("1", 100, 30);
		Trailer t2=service.createTrailer("5", "Cock", "Douche", "23123", Type.CHRISTMAS_TREE);
		Trailer t3=service.createTrailer("2", "Cock", "Douche", "23123", Type.BIN);
		service.beginLoad(s1.getlLoad());
		service.beginLoad(s2.getlLoad());
		for(LoadingDock l:DataBase.getInstance().getAllLoadingDocks() )
			
			//System.out.println(l.getlLoad());

		service.completeLoad(s1);
		//System.out.println(s1.getlLoad());
		Comparator c=new LoadTimeComparator();
		
		loadingD.addLoad(new Load(DU.createDate(), DU.createDate()));
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
		frmDanishCrownProject.setBounds(100, 100, 450, 300);
		frmDanishCrownProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDanishCrownProject.getContentPane().setLayout(null);
		
		btnDanishPlannerUi = new JButton("D.C. Planner UI");
		btnDanishPlannerUi.addActionListener(controller);
		btnDanishPlannerUi.setBounds(20, 177, 124, 37);
		frmDanishCrownProject.getContentPane().add(btnDanishPlannerUi);
		
		btnTruckerUi = new JButton("Trucker UI");
		btnTruckerUi.addActionListener(controller);
		btnTruckerUi.setBounds(176, 177, 101, 37);
		frmDanishCrownProject.getContentPane().add(btnTruckerUi);
		
		btnWarehouseUi = new JButton("Warehouse UI");
		btnWarehouseUi.addActionListener(controller);
		btnWarehouseUi.setBounds(303, 177, 110, 37);
		frmDanishCrownProject.getContentPane().add(btnWarehouseUi);
		
		lblDclogo = new JLabel("DClogo");
		lblDclogo.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/DCLogo.jpeg")));
		lblDclogo.setBounds(73, 41, 295, 125);
		frmDanishCrownProject.getContentPane().add(lblDclogo);
		
			
		 
	}
	/**
	 * @author Momo
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
		}
		
	}
}
