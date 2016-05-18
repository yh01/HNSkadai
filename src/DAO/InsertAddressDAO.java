package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DBConnector;

public class InsertAddressDAO {
	private String sql,
		dbUrl = "jdbc:mysql://localhost/",
		dbUser = "root",
		dbPass = "mysql",
		dbName = "HNS";
	private Connection con;
	private PreparedStatement ps;
	private int count;
	public int insertAddress(int id, String address) {
		count = 0;
		try{
			con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
			sql = "INSERT INTO trn_address (id,address)VALUES(?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, address);
			count = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}
}
