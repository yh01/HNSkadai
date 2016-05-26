package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import DAO.SearchAddressDAO;
import DAO.ShowAddressDAO;
import DTO.SearchAddressDTO;
import DTO.ShowAddressDTO;

/**
 * Servlet implementation class SearchAddressAction
 */
@WebServlet("/SearchAddressAction")
public class SearchAddressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String zip,catchAddress,showAddress,showName,showPhoneNumber;
	int id;
	boolean result;
	SearchAddressDAO dao = new SearchAddressDAO();
	SearchAddressDTO dto = new SearchAddressDTO();
	RequestDispatcher rD;
	HttpSession session;

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
			zip = request.getParameter("zip");
			if(!zip.isEmpty()){
				System.out.println(zip);
				result = dao.searchAddress(zip);
				if(result){
					dto = dao.getDto();
					catchAddress = dto.getZip()+dto.getKenOrCapital()+dto.getCity()+dto.getWard()+dto.getTown();
					System.out.println(catchAddress);
					request.setAttribute("catchAddress", catchAddress);
					request.setAttribute("zip", zip);
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
				}else if(!result){
					dto = dao.getDto();
					catchAddress = "この郵便番号の住所はありません。";
					System.out.println(catchAddress);
					System.out.println("エラー");
					request.setAttribute("catchAddress", catchAddress);
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
			}else if(zip.isEmpty()){
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
				catchAddress = "郵便番号を入力してください";
				request.setAttribute("catchAddress", catchAddress);
				rD = request.getRequestDispatcher("management_address.jsp");
				rD.forward(request, response);
			}
		}else if(session.getAttribute("id") == null){
			request.setAttribute("loginMessage", "未ログインなのでログイン画面に移動しました。");
			rD = request.getRequestDispatcher("login.jsp");
			rD.forward(request, response);
		}

	}

}
