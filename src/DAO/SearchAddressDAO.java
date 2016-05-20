package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.SearchAddressDTO;
import util.DBConnector;

public class SearchAddressDAO {
	private String sql,
		dbUrl="jdbc:mysql://172.16.0.22/",
		dbUser = "user1",
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
		sql = "SELECT ken_name,city_name,ward,town_name FROM address WHERE zip = ? ";
		try{
			ps = con.prepareStatement(sql);
			ps.setString(1, zip);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setKen_name(rs.getString("ken_name"));
				dto.setCity_name(rs.getString("city_name"));
				dto.setWard(rs.getString("ward"));
				dto.setTown_name(rs.getString("town_name"));
				result = true;
				System.out.println("aaa");
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

	public SearchAddressDTO getDto() {
		return dto;
	}
}
