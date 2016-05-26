package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.SearchAddressDTO;
import util.DBConnector;

public class SearchAddressDAO {
	private String sql,
		dbUrl="jdbc:mysql://localhost/",//jdbc:mysql://172.16.0.22/
		dbUser = "root",//user1
		dbPass = "mysql",
		dbName = "HNS";
	private boolean result;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	SearchAddressDTO dto = new SearchAddressDTO();
	public boolean searchAddress(String zip){
		result = false;
		con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
		sql = "SELECT zip,ken_or_capital,city,ward,town FROM address WHERE zip = ? ";
		try{
			ps = con.prepareStatement(sql);
			ps.setString(1, zip);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setZip(rs.getString("zip"));
				dto.setKenOrCapital(rs.getString("ken_or_capital"));
				dto.setCity(rs.getString("city"));
				dto.setWard(rs.getString("ward"));
				dto.setTown(rs.getString("town"));
				result = true;
			}else{
				result = false;
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
		System.out.println(result);
		return result;
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

	public SearchAddressDTO getDto() {
		return dto;
	}
}
