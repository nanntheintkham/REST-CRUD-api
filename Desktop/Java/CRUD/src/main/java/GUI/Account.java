package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.nann.CRUD.App;
import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class Account extends JFrame
{

	private JPanel contentPane;
	private JTextField nametf;
	private JLabel lblNewLabel_1;
	private JTextField usertf;
	private JLabel lblNewLabel_2;
	private JTextField emailtf;
	private JLabel lblNewLabel_3;
	private JButton Createbtn;
	private JTable table;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable tableInfo;
	private JDateChooser dob;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ignored)
		{
		}
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Account frame = new Account();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	// For Retrieving Data
	public void Retrieve()
	{
		DefaultTableModel model = App.processRead();
		tableInfo.setModel(model);
	}

	/**
	 * Create the frame.
	 */
	public Account()
	{
		// Create Database and Tables

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setPreferredSize(new Dimension(10, 100));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setForeground(new Color(245, 245, 245));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 16, 48, 14);
		panel.add(lblNewLabel);

		nametf = new JTextField();
		nametf.setBackground(new Color(176, 196, 222));
		nametf.setMinimumSize(new Dimension(7, 31));
		nametf.setPreferredSize(new Dimension(7, 30));
		nametf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nametf.setBounds(68, 9, 96, 31);
		panel.add(nametf);
		nametf.setColumns(10);

		lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(new Color(245, 245, 245));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(178, 17, 85, 14);
		panel.add(lblNewLabel_1);

		usertf = new JTextField();
		usertf.setBackground(new Color(176, 196, 222));
		usertf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		usertf.setColumns(10);
		usertf.setBounds(273, 9, 96, 31);
		panel.add(usertf);

		lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setForeground(new Color(245, 245, 245));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 58, 48, 14);
		panel.add(lblNewLabel_2);

		emailtf = new JTextField();
		emailtf.setBackground(new Color(176, 196, 222));
		emailtf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		emailtf.setColumns(10);
		emailtf.setBounds(68, 51, 96, 31);
		panel.add(emailtf);

		lblNewLabel_3 = new JLabel("Birthdate:");
		lblNewLabel_3.setForeground(new Color(245, 245, 245));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_3.setBounds(178, 50, 85, 31);
		panel.add(lblNewLabel_3);
	
		Createbtn = new JButton("Create");
		Createbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//when the button shows create
				if (Createbtn.getText() == "Create")
				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

					String d = df.format(dob.getDate());
					if (App.processCreate(usertf.getText(), nametf.getText(), emailtf.getText(), d))
					{
						JOptionPane.showMessageDialog(null, "Successfully created");

						nametf.setText("");
						usertf.setText("");
						emailtf.setText("");
						((JTextField) dob.getDateEditor().getUiComponent()).setText("");

						Retrieve();
					} else
					{
						JOptionPane.showMessageDialog(null, "Not Saved!");
					}
				}
				
				//when the button shows update
				else
				{
					String date = tableInfo.getValueAt(tableInfo.getSelectedRow(), 4).toString();
					
					//Update Data
					if (App.processUpdate(usertf.getText(), nametf.getText(), emailtf.getText(), date))
					{
						JOptionPane.showMessageDialog(null, "Successfully Updated");

						// CLEAR TXT
						nametf.setText("");
						usertf.setText("");
						emailtf.setText("");
						((JTextField) dob.getDateEditor().getUiComponent()).setText("");
						
						//change the button back to create
						Createbtn.setText("Create");

						// Refresh the table
						Retrieve();
					} else
					{
						JOptionPane.showMessageDialog(null, "Not Updated");
					}

				}

			}
		});
		Createbtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		Createbtn.setBounds(425, 64, 91, 25);
		panel.add(Createbtn);

		dob = new JDateChooser();
		dob.setBounds(273, 51, 125, 31);
		panel.add(dob);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(25, 25, 112));
		panel_1.setPreferredSize(new Dimension(10, 245));
		contentPane.add(panel_1, BorderLayout.SOUTH);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setAutoscrolls(true);
		scrollPane_1.setBackground(new Color(25, 25, 112));
		scrollPane_1.setPreferredSize(new Dimension(515, 236));
		panel_1.add(scrollPane_1);
		
		tableInfo = new JTable();
		tableInfo.setForeground(new Color(25, 25, 112));
		tableInfo.setBackground(UIManager.getColor("TabbedPane.background"));
		Retrieve();
		
		tableInfo.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				TableClick();
			}
		});
		tableInfo.setRowSelectionAllowed(false);
		tableInfo.setPreferredSize(new Dimension(500, 150));

		scrollPane_1.setViewportView(tableInfo);

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		table = new JTable();
		panel_1.add(table);
	}

	//action when the table is clicked
	public void TableClick()
	{
		String name = tableInfo.getValueAt(tableInfo.getSelectedRow(), 0).toString();
		String username = tableInfo.getValueAt(tableInfo.getSelectedRow(), 1).toString();
		String email = tableInfo.getValueAt(tableInfo.getSelectedRow(), 2).toString();
		String date = tableInfo.getValueAt(tableInfo.getSelectedRow(), 3).toString();
		nametf.setText(name);
		usertf.setText(username);
		emailtf.setText(email);
		((JTextField) dob.getDateEditor().getUiComponent()).setText(date);

		if (tableInfo.getSelectedColumn() == 5)
		{
			
			Createbtn.setText("Update");
		}
		
		if(tableInfo.getSelectedColumn() == 4)
		{
			Createbtn.setText("Create");
			String[] options = {"Yes", "No"};
			int ans = JOptionPane.showOptionDialog(null, "Are you sure to delete this row?", "Delete Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			
			if(ans == 0)
			{
				new App();
				if(App.processDelete(usertf.getText()))
				{
					JOptionPane.showMessageDialog(null, "Row Deleted");
					
					// CLEAR TXT
					nametf.setText("");
					usertf.setText("");
					emailtf.setText("");
					((JTextField) dob.getDateEditor().getUiComponent()).setText("");
					
					Retrieve();
				}
			}
		}

	}
}
