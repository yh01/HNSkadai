package Action;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import DAO.InsertOrUpdateAddressDAO;
import DAO.MasterAddressDAO;
import DAO.ShowAddressDAO;
import DTO.InsertOrUpdateAddressDTO;
import DTO.MasterAddressDTO;
import DTO.ShowAddressDTO;

/**
 * Servlet implementation class InsertOrUpdateAddressAction
 */
@WebServlet("/InsertOrUpdateAddressAction")
public class InsertOrUpdateAddressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int count,id,insertMaster;
	String address,add,name,phoneNumber,nameMessage,phoneMessage,addressMessage,managementAddressMessage,masterMessage,catchAddress,showAddress,showName,showPhoneNumber,
		zip="",kenOrCapital="",cityName="",ward="",townNumAndName="";
	boolean check,checkName,checkPhoneNum,getName,getPhoneNum,getAddress,result,masterCheck;
	HttpSession session;
	RequestDispatcher rD;
	InsertOrUpdateAddressDAO dao = new InsertOrUpdateAddressDAO();
	InsertOrUpdateAddressDTO dto = new InsertOrUpdateAddressDTO();
	Pattern patternAddress = Pattern.compile("^\\d{7}+.*(県|都)+.*(市|区)+.*$");
	Pattern patternPhoneNumber = Pattern.compile("^\\d{3,4}-\\d{3,4}-\\d{4}$");
	Matcher matcherAddress,matcherPhoneNumber;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		rD = request.getRequestDispatcher("management_address.jsp");
		rD.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		if(session.getAttribute("id") != null){
			id = (int)session.getAttribute("id");
			address = request.getParameter("address");
			name = request.getParameter("name");
			phoneNumber = request.getParameter("phoneNumber");
			dto = dao.getDto();
			matcherAddress = patternAddress.matcher(address);
			matcherPhoneNumber = patternPhoneNumber.matcher(phoneNumber);
			if(!name.isEmpty()||!address.isEmpty()||!phoneNumber.isEmpty()){
				if(name.isEmpty()){
					checkName = dao.checkName(id);
					if(checkName){
						getName = dao.getName(id);
						name = dto.getName();
						name=StringUtils.defaultString(name);
						System.out.println(name);
						if(!name.isEmpty()){
							nameMessage = "変更なし("+name+"さん"+"のままです)";
							request.setAttribute("nameMessage", nameMessage);
						}else if(name.isEmpty()){
							name = null;
							showName = "まだ登録されていません。";
							request.setAttribute("showName", showName);
						}
					}else if(!checkName){
						showName = "まだ登録されていません。";
						request.setAttribute("showName", showName);
					}
				}
				if(phoneNumber.isEmpty()){
					checkPhoneNum = dao.getPhoneNum(id);
					if(checkPhoneNum){
						getPhoneNum = dao.getPhoneNum(id);
						phoneNumber = dto.getPhoneNumber();
						phoneNumber=StringUtils.defaultString(phoneNumber);
						System.out.println(phoneNumber);
						if(!phoneNumber.isEmpty()){
							phoneMessage = "変更なし("+phoneNumber+"のままです)";
							request.setAttribute("phoneMessage", phoneMessage);
						}else if(phoneNumber.isEmpty()){
							phoneNumber = null;
							showPhoneNumber = "まだ登録されていません。";
							request.setAttribute("showPhoneNumber", showPhoneNumber);
						}
					}else if(!checkPhoneNum){
						showPhoneNumber = "まだ登録されていません。";
						request.setAttribute("showPhoneNumber", showPhoneNumber);
					}
				}else if(!phoneNumber.isEmpty()&&!matcherPhoneNumber.find()){
					getPhoneNum = dao.getPhoneNum(id);
					phoneNumber = dto.getPhoneNumber();
					phoneMessage = "000-000-0000の形式で入力してください。";
					request.setAttribute("phoneMessage", phoneMessage);
				}
				if(address.isEmpty()){
					check = dao.checkAddress(id);
					if(check){
						getAddress = dao.getAddress(id);
						zip = dto.getZip();
						address = dto.getAddress();
						address = StringUtils.defaultString(address);
						System.out.println(address);
						if(!address.isEmpty()){
							addressMessage = "変更なし("+zip+address+"のままです)";
							request.setAttribute("addressMessage", addressMessage);
						}else if(address.isEmpty()){
							address = null;
							showAddress = "まだ登録されていません。";
							request.setAttribute("showAddress", showAddress);
						}
					}else if(!check){
						showAddress = "まだ登録されていません。";
						request.setAttribute("showAddress", showAddress);
					}
				}else if(!address.isEmpty()){
					if(matcherAddress.find()){
						MasterAddressDAO dao2 = new MasterAddressDAO();
						MasterAddressDTO dto2 = new MasterAddressDTO();
						masterCheck = dao2.checkAddress(address);
						dto2 = dao2.getDto();
						zip = dto2.getZip();
						kenOrCapital = dto2.getKenOrCapital();
						cityName = dto2.getCityName();
						ward = dto2.getWard();
						townNumAndName = dto2.getTownNum();
						if(masterCheck){
							insertMaster = dao2.insertMaster(zip, kenOrCapital, cityName, ward, townNumAndName);
							if(insertMaster > 0){
								masterMessage="マスタ化成功";
								request.setAttribute("masterMessage", masterMessage);
								kenOrCapital = StringUtils.defaultString(kenOrCapital);
								cityName = StringUtils.defaultString(cityName);
								ward = StringUtils.defaultString(ward);
								townNumAndName = StringUtils.defaultString(townNumAndName);
								address = kenOrCapital+cityName+ward+townNumAndName;
							}else if(insertMaster == 0){
								masterMessage="マスタ化失敗";
								request.setAttribute("masterMessage", masterMessage);
							}
						}else if(!masterCheck){
							masterMessage="チェック、マスタ化失敗";
							request.setAttribute("masterMessage", masterMessage);
						}
					}else if(!matcherAddress.find()){
						managementAddressMessage = "住所を郵便番号（ハイフンなし）から入力してください。";
						request.setAttribute("managementAddressMessage", managementAddressMessage);
						ShowAddressDAO dao1 = new ShowAddressDAO();
						ShowAddressDTO dto1 = new ShowAddressDTO();
						dto1 = dao1.getDto();
						dao1.selectAddress(id);
						showAddress = dto1.getAddress();
						showName = dto1.getName();
						showPhoneNumber = dto1.getPhoneNumber();
						showAddress = StringUtils.defaultString(showAddress);
						showName = StringUtils.defaultString(showName);
						showPhoneNumber = StringUtils.defaultString(showPhoneNumber);
						if(showAddress.isEmpty()){
							showAddress = "まだ登録されていません";
						}
						if(showName.isEmpty()){
							showName = "まだ登録されていません";
						}
						if(showPhoneNumber.isEmpty()){
							showPhoneNumber = "まだ登録されていません";
						}
						request.setAttribute("showAddress", showAddress);
						request.setAttribute("showName", showName);
						request.setAttribute("showPhoneNumber", showPhoneNumber);
						rD = request.getRequestDispatcher("management_address.jsp");
						rD.forward(request, response);
					}
				}

				check = dao.checkAddress(id);
				if(check){
					matcherAddress = patternAddress.matcher(zip + address);
					if(!matcherAddress.find()){
						managementAddressMessage = "住所を郵便番号（ハイフンなし）から入力してください。";
						request.setAttribute("managementAddressMessage", managementAddressMessage);
						ShowAddressDAO dao1 = new ShowAddressDAO();
						ShowAddressDTO dto1 = new ShowAddressDTO();
						dto1 = dao1.getDto();
						dao1.selectAddress(id);
						showAddress = dto1.getAddress();
						showName = dto1.getName();
						showPhoneNumber = dto1.getPhoneNumber();
						showAddress = StringUtils.defaultString(showAddress);
						showName = StringUtils.defaultString(showName);
						showPhoneNumber = StringUtils.defaultString(showPhoneNumber);
						if(showAddress.isEmpty()){
							showAddress = "まだ登録されていません";
						}
						if(showName.isEmpty()){
							showName = "まだ登録されていません";
						}
						if(showPhoneNumber.isEmpty()){
							showPhoneNumber = "まだ登録されていません";
						}
						request.setAttribute("showAddress", showAddress);
						request.setAttribute("showName", showName);
						request.setAttribute("showPhoneNumber", showPhoneNumber);
						rD = request.getRequestDispatcher("management_address.jsp");
						rD.forward(request, response);
					}
					count = dao.updateAddress(id,name,phoneNumber, zip,address);
					if(count > 0){
						getAddress = dao.getAddress(id);
						if(getAddress){
							managementAddressMessage = "住所情報の更新に成功しました。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							ShowAddressDAO dao1 = new ShowAddressDAO();
							ShowAddressDTO dto1 = new ShowAddressDTO();
							dto1 = dao1.getDto();
							dao1.selectAddress(id);
							showAddress = dto1.getAddress();
							showName = dto1.getName();
							showPhoneNumber = dto1.getPhoneNumber();
							showAddress = StringUtils.defaultString(showAddress);
							showName = StringUtils.defaultString(showName);
							showPhoneNumber = StringUtils.defaultString(showPhoneNumber);
							if(showAddress.isEmpty()){
								showAddress = "まだ登録されていません";
							}
							if(showName.isEmpty()){
								showName = "まだ登録されていません";
							}
							if(showPhoneNumber.isEmpty()){
								showPhoneNumber = "まだ登録されていません";
							}
							request.setAttribute("showAddress", showAddress);
							request.setAttribute("showName", showName);
							request.setAttribute("showPhoneNumber", showPhoneNumber);
							rD = request.getRequestDispatcher("management_address.jsp");
							rD.forward(request, response);
						}else if(!getAddress){
							managementAddressMessage = "住所情報の更新に成功しました。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							rD = request.getRequestDispatcher("management_address.jsp");
							rD.forward(request, response);
						}
					}else if(count == 0){
						managementAddressMessage = "住所情報の更新に失敗しました。";
						request.setAttribute("managementAddressMessage", managementAddressMessage);
						rD = request.getRequestDispatcher("management_address.jsp");
						rD.forward(request, response);
					}
				}else if(!check){
					matcherAddress = patternAddress.matcher(zip + address);
					if(!matcherAddress.find()){
						if(!matcherAddress.find()){
							managementAddressMessage = "住所を郵便番号（ハイフンなし）から入力してください。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							ShowAddressDAO dao1 = new ShowAddressDAO();
							ShowAddressDTO dto1 = new ShowAddressDTO();
							dto1 = dao1.getDto();
							dao1.selectAddress(id);
							showAddress = dto1.getAddress();
							showName = dto1.getName();
							showPhoneNumber = dto1.getPhoneNumber();
							showAddress = StringUtils.defaultString(showAddress);
							showName = StringUtils.defaultString(showName);
							showPhoneNumber = StringUtils.defaultString(showPhoneNumber);
							if(showAddress.isEmpty()){
								showAddress = "まだ登録されていません";
							}
							if(showName.isEmpty()){
								showName = "まだ登録されていません";
							}
							if(showPhoneNumber.isEmpty()){
								showPhoneNumber = "まだ登録されていません";
							}
							request.setAttribute("showAddress", showAddress);
							request.setAttribute("showName", showName);
							request.setAttribute("showPhoneNumber", showPhoneNumber);
							rD = request.getRequestDispatcher("management_address.jsp");
							rD.forward(request, response);
						}
					}
					count = dao.insertAddress(id,name,phoneNumber,zip,address);
					if(count > 0){
						getAddress = dao.getAddress(id);
						if(getAddress){
							managementAddressMessage = "住所情報の新規登録に成功しました。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							ShowAddressDAO dao1 = new ShowAddressDAO();
							ShowAddressDTO dto1 = new ShowAddressDTO();
							dto1 = dao1.getDto();
							dao1.selectAddress(id);
							showAddress = dto1.getAddress();
							showName = dto1.getName();
							showPhoneNumber = dto1.getPhoneNumber();
							showAddress = StringUtils.defaultString(showAddress);
							showName = StringUtils.defaultString(showName);
							showPhoneNumber = StringUtils.defaultString(showPhoneNumber);
							if(showAddress.isEmpty()){
								showAddress = "まだ登録されていません";
							}
							if(showName.isEmpty()){
								showName = "まだ登録されていません";
							}
							if(showPhoneNumber.isEmpty()){
								showPhoneNumber = "まだ登録されていません";
							}
							request.setAttribute("showAddress", showAddress);
							request.setAttribute("showName", showName);
							request.setAttribute("showPhoneNumber", showPhoneNumber);
							rD = request.getRequestDispatcher("management_address.jsp");
							rD.forward(request, response);
						}else if(!getAddress){
							managementAddressMessage = "住所情報の新規登録に成功しました。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							ShowAddressDAO dao1 = new ShowAddressDAO();
							ShowAddressDTO dto1 = new ShowAddressDTO();
							dto1 = dao1.getDto();
							dao1.selectAddress(id);
							showAddress = dto1.getAddress();
							showName = dto1.getName();
							showPhoneNumber = dto1.getPhoneNumber();
							showAddress = StringUtils.defaultString(showAddress);
							showName = StringUtils.defaultString(showName);
							showPhoneNumber = StringUtils.defaultString(showPhoneNumber);
							if(showAddress.isEmpty()){
								showAddress = "まだ登録されていません";
							}
							if(showName.isEmpty()){
								showName = "まだ登録されていません";
							}
							if(showPhoneNumber.isEmpty()){
								showPhoneNumber = "まだ登録されていません";
							}
							request.setAttribute("showAddress", showAddress);
							request.setAttribute("showName", showName);
							request.setAttribute("showPhoneNumber", showPhoneNumber);
							rD = request.getRequestDispatcher("management_address.jsp");
							rD.forward(request, response);
						}
					}else if(count == 0){
						managementAddressMessage = "住所情報の新規登録に失敗しました。";
						request.setAttribute("managementAddressMessage", managementAddressMessage);
						ShowAddressDAO dao1 = new ShowAddressDAO();
						ShowAddressDTO dto1 = new ShowAddressDTO();
						dto1 = dao1.getDto();
						dao1.selectAddress(id);
						showAddress = dto1.getAddress();
						showName = dto1.getName();
						showPhoneNumber = dto1.getPhoneNumber();
						showAddress = StringUtils.defaultString(showAddress);
						showName = StringUtils.defaultString(showName);
						showPhoneNumber = StringUtils.defaultString(showPhoneNumber);
						if(showAddress.isEmpty()){
							showAddress = "まだ登録されていません";
						}
						if(showName.isEmpty()){
							showName = "まだ登録されていません";
						}
						if(showPhoneNumber.isEmpty()){
							showPhoneNumber = "まだ登録されていません";
						}
						request.setAttribute("showAddress", showAddress);
						request.setAttribute("showName", showName);
						request.setAttribute("showPhoneNumber", showPhoneNumber);
						rD = request.getRequestDispatcher("management_address.jsp");
						rD.forward(request, response);
					}
				}
			}else if(name.isEmpty()&&address.isEmpty()&&phoneNumber.isEmpty()){
				managementAddressMessage = "どれかを入力してください";
				request.setAttribute("managementAddressMessage", managementAddressMessage);
				ShowAddressDAO dao1 = new ShowAddressDAO();
				ShowAddressDTO dto1 = new ShowAddressDTO();
				dto1 = dao1.getDto();
				dao1.selectAddress(id);
				showAddress = dto1.getAddress();
				showName = dto1.getName();
				showPhoneNumber = dto1.getPhoneNumber();
				showAddress = StringUtils.defaultString(showAddress);
				showName = StringUtils.defaultString(showName);
				showPhoneNumber = StringUtils.defaultString(showPhoneNumber);
				if(showAddress.isEmpty()){
					showAddress = "まだ登録されていません";
				}
				if(showName.isEmpty()){
					showName = "まだ登録されていません";
				}
				if(showPhoneNumber.isEmpty()){
					showPhoneNumber = "まだ登録されていません";
				}
				request.setAttribute("showAddress", showAddress);
				request.setAttribute("showName", showName);
				request.setAttribute("showPhoneNumber", showPhoneNumber);
				rD = request.getRequestDispatcher("management_address.jsp");
				rD.forward(request, response);
			}
		}else if(session.getAttribute("id") == null){
			managementAddressMessage = "未ログインなのでログイン画面に移動しました。";
			request.setAttribute("loginMessage", managementAddressMessage);
			rD = request.getRequestDispatcher("login.jsp");
			rD.forward(request, response);
		}
	}
}
