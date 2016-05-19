package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.ShowAddressDTO;
import util.DBConnector;

public class ShowAddressDAO {
	private String sql,
		dbUrl = "jdbc:mysql://172.16.0.22/",
		dbUser = "user1",
		dbPass = "mysql",
		dbName = "HNS";
	private boolean result;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	ShowAddressDTO dto = new ShowAddressDTO();

	public boolean selectAddress(int id){
		result = true;
		try{
			con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
			sql = "SELECT address FROM trn_address WHERE id = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setAddress(rs.getString("address"));
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ShowAddressDTO getDto() {
		return dto;
	}
}
