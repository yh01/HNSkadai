package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	String name,pass,catchAddress,showAddress,loginMessage = null;
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
					getAddress = dao.getAddress(id);
					if(getAddress){
						catchAddress = dto.getAddress();
						request.setAttribute("catchAddress", catchAddress);
						session = request.getSession();
						session.invalidate();
						session = request.getSession();
						session.setAttribute("id", id);
						session.setAttribute("name", name);
						ShowAddressDAO dao = new ShowAddressDAO();
						ShowAddressDTO dto = new ShowAddressDTO();
						dto = dao.getDto();
						dao.selectAddress(id);
						showAddress = dto.getAddress();
						request.setAttribute("showAddress", showAddress);
						rD = request.getRequestDispatcher("management_address.jsp");
						rD.forward(request, response);
					}else if(!getAddress){
						session = request.getSession();
						session.invalidate();
						session = request.getSession();
						session.setAttribute("id", id);
						session.setAttribute("name", name);
						ShowAddressDAO dao = new ShowAddressDAO();
						ShowAddressDTO dto = new ShowAddressDTO();
						dto = dao.getDto();
						dao.selectAddress(id);
						showAddress = dto.getAddress();
						request.setAttribute("showAddress", showAddress);
						rD = request.getRequestDispatcher("management_address.jsp");
						rD.forward(request, response);
					}
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
