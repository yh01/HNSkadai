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

	public int insertAddress(int id, String name, String phoneNumber, String zip, String address) {
		count = 0;
		try{
			con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
			sql = "INSERT INTO trn_address (id,name,phone_number,zip,address)VALUES(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, phoneNumber);
			ps.setString(4, zip);
			ps.setString(5, address);
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

	public int updateAddress(int id, String name,String phoneNumber,String zip,String address) {
		count = 0;
		try{
			con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
			sql = "UPDATE trn_address SET name = ? , phone_number = ? , zip = ? , address = ? WHERE id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phoneNumber);
			ps.setString(3, zip);
			ps.setString(4, address);
			ps.setInt(5, id);
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
		sql = "SELECT zip,address FROM trn_address WHERE id = ? ";
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setZip(rs.getString("zip"));
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

	public boolean getPhoneNum(int id){
		con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
		sql = "SELECT phone_number FROM trn_address WHERE id = ? ";
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setPhoneNumber(rs.getString("phone_number"));
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

	public boolean checkName(int id){
		con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
		sql = "SELECT name FROM trn_address WHERE id=?";
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}else if(!rs.next()){
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

	public boolean getName(int id){
		con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
		sql = "SELECT name FROM trn_address WHERE id = ? ";
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setName(rs.getString("name"));
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
