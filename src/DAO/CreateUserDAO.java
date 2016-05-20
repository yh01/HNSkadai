package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnector;

public class CreateUserDAO {
	private String sql,
	dbUrl = "jdbc:mysql://172.16.0.22/",
	dbUser = "user1",
	dbPass = "mysql",
	dbName = "HNS";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private int count;

	public int insertUser(String name, String pass) {
		count = 0;
		try{
			con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
			sql = "INSERT INTO trn_user (name,pass)VALUES(?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pass);
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

	public boolean checkUserName(String name){
		con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
		sql = "SELECT id FROM trn_user WHERE name=?";
		try{
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()){
				rs.getInt("id");
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
