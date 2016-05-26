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

import DAO.LoginDAO;
import DAO.ShowAddressDAO;
import DTO.LoginDTO;
import DTO.ShowAddressDTO;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name,pass,catchAddress,showAddress,showName,showPhoneNumber,loginMessage = null;
	int id;
	boolean result,check,getAddress;
	LoginDAO dao = new LoginDAO();
	LoginDTO dto = new LoginDTO();
	HttpSession session;
	RequestDispatcher rD;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		rD = request.getRequestDispatcher("login.jsp");
		rD.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		name = request.getParameter("name");
		pass = request.getParameter("pass");
		if(!name.isEmpty() && !pass.isEmpty()){
		check = dao.checkUser(name, pass);
			if(check){
			result = dao.selectUser(name, pass);
				if(result){
					dto = dao.getDto();
					id = dto.getId();
					session = request.getSession();
					session.invalidate();
					session = request.getSession();
					session.setAttribute("id", id);
					session.setAttribute("name", name);
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
					loginMessage = "ユーザーネームかパスワードが間違っています。";
					request.setAttribute("loginMessage", loginMessage);
					rD = request.getRequestDispatcher("login.jsp");
					rD.forward(request, response);
				}
			}else if(!check){
				loginMessage = "新規登録してください。";
				request.setAttribute("loginMessage", loginMessage);
				rD = request.getRequestDispatcher("login.jsp");
				rD.forward(request, response);
			}
		}else if(name.isEmpty() || pass.isEmpty()){
			loginMessage = "名前とパスワード両方入力してください。";
			request.setAttribute("loginMessage", loginMessage);
			rD = request.getRequestDispatcher("login.jsp");
			rD.forward(request, response);
		}
	}

}
