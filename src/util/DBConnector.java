package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static String driverName="com.mysql.jdbc.Driver";
	private static Connection con=null;
	public static Connection connectDB(String dbUrl,String dbName, String dbUser,String dbPass){
		dbUrl += dbName;
		try{
			Class.forName(driverName);
			con = DriverManager.getConnection(dbUrl,dbUser,dbPass);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return con;
	}
}
