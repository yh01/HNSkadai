package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DTO.MasterAddressDTO;
import util.DBConnector;

public class MasterAddressDAO {
	MasterAddressDTO dto = new MasterAddressDTO();
	private String sql,
	dbUrl = "jdbc:mysql://localhost/",//jdbc:mysql://172.16.0.22/
	dbUser = "root",//user1
	dbPass = "mysql",//
	dbName = "HNS";//
	private Connection con;
	private PreparedStatement ps;
	private int count;

	public boolean checkAddress(String address){
		System.out.println(address);
		String zip=" ",kenOrCapital=" ",cityName=" ",ward=" ",townNumAndName=" ";
		int zipStart=0,zipEnd=7,startKenOrCapital=7,endKen = address.indexOf("県")+1,endCapital=address.indexOf("都")+1,
				startCityForKen = address.indexOf("県")+1,startCityForCapital = address.indexOf("都")+1,endCity = address.indexOf("市")+1,
				startWard = address.indexOf("市")+1,endWard = address.indexOf("区")+1,
				startTown=address.indexOf("区")+1,endTown=address.length();
		Pattern patternAddress = Pattern.compile("^\\d{7}+.*(県|都)+.*(市|区)+.*$");
		Pattern patternKen = Pattern.compile("県");
		Pattern patternCapital = Pattern.compile("都");
		Pattern patternCity = Pattern.compile("市");
		Pattern patternWard = Pattern.compile("区");
		Matcher matcherKen = patternKen.matcher(address);
		Matcher matcherAddress = patternAddress.matcher(address);
		Matcher matcherCapital = patternCapital.matcher(address);
		Matcher matcherCity = patternCity.matcher(address);
		Matcher matcherWard = patternWard.matcher(address);
		if(matcherAddress.find()){
			System.out.println("true");
			zip = address.substring(zipStart,zipEnd);
			dto.setZip(zip);
			if(matcherKen.find()){
				kenOrCapital = address.substring(startKenOrCapital,endKen);
				dto.setKenOrCapital(kenOrCapital);
				if(matcherCity.find()){
					cityName=address.substring(startCityForKen,endCity);
					dto.setCityName(cityName);
					if(matcherWard.find()){
						ward = address.substring(startWard,endWard);
						dto.setWard(ward);
						townNumAndName = address.substring(startTown,endTown);
						dto.setTownNum(townNumAndName);
						return true;
					}else if(!matcherWard.find()){
						ward = " ";
						dto.setWard(ward);
						townNumAndName = address.substring(endCity,endTown);
						dto.setTownNum(townNumAndName);
						return true;
					}
				}else if(!matcherCity.find()){
					cityName=" ";
					ward=address.substring(endKen,endWard);
					dto.setWard(ward);
					townNumAndName = address.substring(startTown,endTown);
					dto.setTownNum(townNumAndName);
					return true;
				}

			}else if(matcherCapital.find()){
				kenOrCapital = address.substring(startKenOrCapital,endCapital);
				dto.setKenOrCapital(kenOrCapital);
				if(matcherCity.find()){
					cityName=address.substring(startCityForCapital,endCity);
					dto.setCityName(cityName);
					if(matcherWard.find()){
						ward = address.substring(startWard,endWard);
						dto.setWard(ward);
						townNumAndName = address.substring(startTown,endTown);
						dto.setTownNum(townNumAndName);
						return true;
					}else if(!matcherWard.find()){
						ward = " ";
						dto.setWard(ward);
						townNumAndName = address.substring(endCity,endTown);
						dto.setTownNum(townNumAndName);
						return true;
					}
				}else if(!matcherCity.find()){
					ward=address.substring(endCapital,endWard);
					dto.setWard(ward);
					townNumAndName = address.substring(startTown,endTown);
					dto.setTownNum(townNumAndName);
					return true;
				}
			}
		}else{
			System.out.println("false");
			return false;
		}
		return false;
	}
	public int insertMaster(String zip, String kenOrCapital,String cityName,String ward,String townNum){
		count = 0;
		try{
			con = DBConnector.connectDB(dbUrl, dbName, dbUser, dbPass);
			sql = "INSERT INTO address (zip,ken_or_capital,city,ward,town)VALUES(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, zip);
			ps.setString(2, kenOrCapital);
			ps.setString(3, cityName);
			ps.setString(4, ward);
			ps.setString(5, townNum);
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

	public MasterAddressDTO getDto() {
		return dto;
	}
}
