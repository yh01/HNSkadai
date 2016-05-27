package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.CodeTestDTO;
import util.DBConnector;

public class CodeTestDAO {
	CodeTestDTO dto = new CodeTestDTO();
	public CodeTestDTO getDto() {
		return dto;
	}

	private String sql,
		dbUrl = "jdbc:mysql://localhost/",//"jdbc:mysql://172.16.0.22/"
		dbUser = "root",//"user1"
		dbPass = "mysql",//"mysql"
		dbName = "HNS?useUnicode=true&characterEncoding=utf8";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private int count;

	public void getTest(){
		con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
		sql = "SELECT hex(name),hex(pass) FROM code_test WHERE name=?";
		try{
			ps = con.prepareStatement(sql);
			ps.setString(1, "あいう");
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setName(rs.getString("hex(name)"));
				dto.setPass(rs.getString("hex(pass)"));
				System.out.println(rs.getString("hex(name)"));
				System.out.println(rs.getString("hex(pass)"));
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
	}

	public int insertHex(String hName, String hPass) {
		count = 0;
		try{
			con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
			sql = "INSERT INTO hex_test (name,pass)VALUES(?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, hName);
			ps.setString(2, hPass);
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

	public void getUnHex(){
		con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
		sql = "SELECT unhex(name),unhex(pass) FROM hex_test WHERE name=?";
		try{
			ps = con.prepareStatement(sql);
			ps.setString(1, "E38182E38184E38186");
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setName(rs.getString("unhex(name)"));
				dto.setPass(rs.getString("unhex(pass)"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("pass"));
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
	}
}
