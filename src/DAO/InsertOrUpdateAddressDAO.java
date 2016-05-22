package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.InsertOrUpdateAddressDTO;
import util.DBConnector;

public class InsertOrUpdateAddressDAO {
	private String sql,
	dbUrl = "jdbc:mysql://localhost/",//jdbc:mysql://172.16.0.22/
	dbUser = "root",//user1
	dbPass = "mysql",//
	dbName = "HNS";//
	private Connection con;
	private PreparedStatement ps;
	private int count;
	private ResultSet rs;
	InsertOrUpdateAddressDTO dto = new InsertOrUpdateAddressDTO();

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

	public boolean checkAddress(int id){
		con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
		sql = "SELECT id FROM trn_address WHERE id=?";
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
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

	public boolean getAddress(int id){
		con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
		sql = "SELECT address FROM trn_address WHERE id = ? ";
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setAddress(rs.getString("address"));
				return true;
			}else{
				return false;
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


	public InsertOrUpdateAddressDTO getDto() {
		return dto;
	}
}
