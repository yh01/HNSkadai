package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.LoginDTO;
import util.DBConnector;

public class LoginDAO {
	private String sql,
		dbUrl="jdbc:mysql://172.16.0.22/",
		dbUser = "user1",
		dbPass = "mysql",
		dbName = "HNS";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	LoginDTO dto = new LoginDTO();
	public boolean selectUser(String name,String pass){
		con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
		sql = "SELECT id FROM trn_user WHERE name=? AND pass=?";
		try{
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setId(rs.getInt("id"));
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

	public LoginDTO getDto() {
		return dto;
	}
}
