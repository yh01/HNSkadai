package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	String address,managementAddressMessage,masterMessage,catchAddress,showAddress,
		zip="",kenOrCapital="",cityName="",ward="",townNumAndName="";
	boolean check,getAddress,result,masterCheck;
	HttpSession session;
	RequestDispatcher rD;
	InsertOrUpdateAddressDAO dao = new InsertOrUpdateAddressDAO();
	InsertOrUpdateAddressDTO dto = new InsertOrUpdateAddressDTO();
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
			dto = dao.getDto();
			if(!address.isEmpty() && address.matches("^\\d{7}$") && address.matches(".*県*") || address.matches(".*都*") && address.matches(".*市*") ||address.matches(".*区*")){
				check = dao.checkAddress(id);
				if(check){
					count = dao.updateAddress(id, address);
					if(count > 0){
						getAddress = dao.getAddress(id);
						if(getAddress){
							catchAddress = dto.getAddress();
							request.setAttribute("catchAddress", catchAddress);
							managementAddressMessage = "住所情報の更新に成功しました。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							ShowAddressDAO dao1 = new ShowAddressDAO();
							ShowAddressDTO dto1 = new ShowAddressDTO();
							dto1 = dao1.getDto();
							dao1.selectAddress(id);
							showAddress = dto1.getAddress();
							request.setAttribute("showAddress", showAddress);
							MasterAddressDAO dao2 = new MasterAddressDAO();
							MasterAddressDTO dto2 = new MasterAddressDTO();
							masterCheck = dao2.checkAddress(address);
							if(masterCheck){
								dto2 = dao2.getDto();
								zip = dto2.getZip();
								kenOrCapital = dto2.getKenOrCapital();
								cityName = dto2.getCityName();
								ward = dto2.getWard();
								townNumAndName = dto2.getTownNum();
								insertMaster = dao2.insertMaster(zip, kenOrCapital, cityName, ward, townNumAndName);
								if(insertMaster > 0){
									masterMessage="マスタ化成功";
									request.setAttribute("masterMessage", masterMessage);
									rD = request.getRequestDispatcher("management_address.jsp");
									rD.forward(request, response);
								}else if(insertMaster == 0){
									masterMessage="マスタ化成功";
									request.setAttribute("masterMessage", masterMessage);
									rD = request.getRequestDispatcher("management_address.jsp");
									rD.forward(request, response);
								}
							}else if(!masterCheck){
								masterMessage="チェック、マスタ化失敗";
								request.setAttribute("masterMessage", masterMessage);
								rD = request.getRequestDispatcher("management_address.jsp");
								rD.forward(request, response);
							}
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
					count = dao.insertAddress(id, address);
					if(count > 0){
						getAddress = dao.getAddress(id);
						if(getAddress){
							catchAddress = dto.getAddress();
							request.setAttribute("catchAddress", catchAddress);
							managementAddressMessage = "住所情報の新規登録に成功しました。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							rD = request.getRequestDispatcher("management_address.jsp");
							rD.forward(request, response);
						}else if(!getAddress){
							managementAddressMessage = "住所情報の新規登録に成功しました。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							rD = request.getRequestDispatcher("management_address.jsp");
							rD.forward(request, response);
						}
					}else if(count == 0){
						managementAddressMessage = "住所情報の新規登録に失敗しました。";
						request.setAttribute("managementAddressMessage", managementAddressMessage);
						rD = request.getRequestDispatcher("management_address.jsp");
						rD.forward(request, response);
					}
				}
			}else if(address.isEmpty()){
				managementAddressMessage = "住所を入力してください。";
				request.setAttribute("managementAddressMessage", managementAddressMessage);
				rD = request.getRequestDispatcher("management_address.jsp");
				rD.forward(request, response);
			}else if(!address.matches("^\\d{7}$")){
				managementAddressMessage = "郵便番号をハイフン無しで入力してください。";
				request.setAttribute("managementAddressMessage", managementAddressMessage);
				rD = request.getRequestDispatcher("management_address.jsp");
				rD.forward(request, response);
			}else if(!address.matches(".*県*") && !address.matches(".*都*")){
				managementAddressMessage = "都道府県名を入力してください。";
				request.setAttribute("managementAddressMessage", managementAddressMessage);
				rD = request.getRequestDispatcher("management_address.jsp");
				rD.forward(request, response);
			}else if(!address.matches(".*市*") && !address.matches(".*区*")){
				managementAddressMessage = "市または区名を入力してください。";
				request.setAttribute("managementAddressMessage", managementAddressMessage);
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
