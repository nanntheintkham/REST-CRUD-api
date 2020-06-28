package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector
{
	public static Connection Con;
	
	static 
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "root", "");
		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void closeDB()
	{
			try
			{
				Con.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

