package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DBConnector;

public class UpdateAddressDAO {
	private String sql,
		dbUrl = "jdbc:mysql://172.16.0.22/",
		dbUser = "user1",
		dbPass = "mysql",
		dbName = "HNS";
	private Connection con;
	private PreparedStatement ps;
	private int count;
	public int updateAddress(int id, String address) {
		count = 0;
		try{
			con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
			sql = "UPDATE trn_address SET address = ? WHERE id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, address);
			ps.setInt(2, id);
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
