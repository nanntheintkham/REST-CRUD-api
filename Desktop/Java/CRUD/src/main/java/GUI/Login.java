package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Login extends JFrame
{

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

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
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login()
	{
		Dao.prepareDB();
		Dao.prepareLoginTable();
		Dao.prepareAccountTable();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Account CRUD");
		lblNewLabel.setBackground(new Color(176, 196, 222));
		lblNewLabel.setForeground(new Color(245, 245, 245));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(25, 25, 112));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(new Color(245, 245, 245));
		lblNewLabel_1.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(67, 35, 93, 18);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setForeground(new Color(245, 245, 245));
		lblNewLabel_1_1.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(67, 81, 93, 26);
		panel_1.add(lblNewLabel_1_1);

		textField = new JTextField();
		textField.setBackground(new Color(176, 196, 222));
		textField.setBounds(185, 23, 122, 32);
		panel_1.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(176, 196, 222));
		passwordField.setBounds(185, 75, 122, 32);
		panel_1.add(passwordField);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(221, 160, 221));
		btnNewButton.addActionListener(new ActionListener()
		{
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
			{
				 String user = textField.getText();
	             String pwd = passwordField.getText();
				if(Dao.checkLogin(user, pwd))
	             {
					JOptionPane.showMessageDialog(null, "Successfully Login");
					dispose();
					
					Account acc = new Account();
					acc.setVisible(true);
				}
				
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tekton Pro Ext", Font.BOLD, 14));
		btnNewButton.setBounds(299, 149, 98, 32);
		panel_1.add(btnNewButton);
	}
}
