package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DBUtils {

		public static Connection getConnection() throws ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); 
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useSSL=false","root","Qwerty@123");
				System.out.println("Database connected successfully");
		return con;
		}
		
	}

