package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;


public class DBUpdater
{
	public static int id = 1;
	//add to db
	public static boolean add(String name, String username, String email, String dob)
	{
		try
		{
			
			String sql = "INSERT INTO account (id, name, username, email, dob) VALUES('"
					+ id + "','"
					+ name + "','"
					+ username + "','"
					+ email + "','"
					+ dob + "')";
			
			PreparedStatement st = Connector.Con.prepareStatement(sql);
			
			st.executeUpdate();
			id++;
			
			return true;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
		
	}
	
	//Showing all data
	public DefaultTableModel getData()
	{
		DefaultTableModel model = new DefaultTableModel();
		
		
		
		Object[] field = {"ID", "Name", "Username", "email", "Date Of Birth", "Delete", "Update"};
		model.setColumnIdentifiers(field);
		
		String sql = "SELECT * from account";
		
		try
		{
			PreparedStatement st = Connector.Con.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String username = rs.getString(3);
				String email = rs.getString(4);
				String dob = rs.getString(5);
				
				model.addRow(new Object[] {id, name, username, email, dob, "Delete", "Updae"});
			}
			return model;
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Updating data
	public static boolean Update(String id, String name, String username, String email, String dob)
	{
		String sql = "UPDATE account SET name = '" + name + "',username = '" + username + "',email = '" + email + "',dob = '" + dob
				+ "'WHERE id = '" + id + "'";
		
		try
		{
			Statement st = Connector.Con.prepareStatement(sql);
			st.execute(sql);
			
			return true;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	//Delete data
	public static boolean Delete(String id)
	{
		String sql = "DELETE FROM account WHERE id = '" + id + "'";
		
		try
		{
			Statement st = Connector.Con.prepareStatement(sql);
			
			st.execute(sql);
			return true;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
