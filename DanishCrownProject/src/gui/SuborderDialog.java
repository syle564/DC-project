package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class SuborderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtLoadingweight;
	private JTextField txtLoadingtime;
	private JTextField txtLoadingdate;
	private JLabel lblLoaingWeight;
	private JLabel lblLoadingTime;
	private JLabel lblLoadingDate;
	private JLabel lblListOfAvailable;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SuborderDialog dialog = new SuborderDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SuborderDialog() {
		setTitle("Suborder Dialog");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblLoaingWeight = new JLabel("Loading Weight :");
			lblLoaingWeight.setBounds(10, 24, 86, 14);
			contentPanel.add(lblLoaingWeight);
		}
		{
			txtLoadingweight = new JTextField();
			txtLoadingweight.setText("LoadingWeight");
			txtLoadingweight.setBounds(10, 49, 86, 20);
			contentPanel.add(txtLoadingweight);
			txtLoadingweight.setColumns(10);
		}
		{
			lblLoadingTime = new JLabel("Loading Time :");
			lblLoadingTime.setBounds(10, 80, 75, 14);
			contentPanel.add(lblLoadingTime);
		}
		{
			txtLoadingtime = new JTextField();
			txtLoadingtime.setText("LoadingTime");
			txtLoadingtime.setBounds(10, 105, 86, 20);
			contentPanel.add(txtLoadingtime);
			txtLoadingtime.setColumns(10);
		}
		{
			lblLoadingDate = new JLabel("Loading Date :");
			lblLoadingDate.setBounds(10, 136, 75, 14);
			contentPanel.add(lblLoadingDate);
		}
		{
			txtLoadingdate = new JTextField();
			txtLoadingdate.setText("LoadingDate");
			txtLoadingdate.setBounds(10, 161, 86, 20);
			contentPanel.add(txtLoadingdate);
			txtLoadingdate.setColumns(10);
		}
		{
			lblListOfAvailable = new JLabel(" Available Trucks :");
			lblListOfAvailable.setBounds(261, 24, 109, 14);
			contentPanel.add(lblListOfAvailable);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(261, 49, 86, 169);
			contentPanel.add(scrollPane);
			{
				list = new JList();
				scrollPane.setViewportView(list);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
