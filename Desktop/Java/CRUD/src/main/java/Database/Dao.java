package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao
{
	public static void prepareDB()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306";
			
			Connection con = DriverManager.getConnection(url,"root","");
			Statement st = con.createStatement();
			
			String sql = "create database if not exists crud";
			st.execute(sql);
		} catch (SQLException | ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void prepareLoginTable()
	{
		String user = "Nann";
		String password = "130613";
		try
		{
			Statement st = Connector.Con.createStatement();
			
			String sql = "create table if not exists `admin`("
					+ "username varchar(50) primary key NOT NULL,"
					+ "password varchar(150) NOT NULL"
					+ ")";
			
			st.executeUpdate(sql);
			
			if(checkAdminData() == false)
			{
				String sql1 = "INSERT INTO admin VALUES (?,?)";
				
				PreparedStatement stm = Connector.Con.prepareStatement(sql1);
				stm.setString(1, user);
				stm.setString(2, password);
				
				stm.executeUpdate();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void prepareAccountTable()
	{
		try
		{
			Statement st = Connector.Con.createStatement();
			
			String sql1 = "CREATE TABLE if not exists `account` (\r\n" +  
					"  `name` varchar(150) NOT NULL,\r\n" + 
					"  `username` varchar(100) PRIMARY KEY NOT NULL,\r\n" +
					"  `email` varchar(100) UNIQUE NOT NULL,\r\n" + 
					"  `dob` date DEFAULT NULL,\r\n" + 
					"  `verified` tinyint(1) NOT NULL DEFAULT 0\r\n" 
					+")";
			
			st.execute(sql1);
					
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean checkLogin(String user, String pwd)
	{
		String sql = "SELECT * FROM `admin` WHERE username = ? and password = ?";
		
		try
		{
			PreparedStatement st = Connector.Con.prepareStatement(sql);
			st.setString(1, user);
			st.setString(2, pwd);
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
				return true;
			else
				return false;
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	public static boolean checkAdminData()
	{
		String sql = "select * from `admin`";
		
		
		try {
			Statement stm = Connector.Con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			if(rs.next()) return true;
			else return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}