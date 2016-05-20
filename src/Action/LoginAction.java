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
import DTO.LoginDTO;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name,pass,loginMessage = null;
	int id;
	boolean result;
	LoginDAO dao = new LoginDAO();
	LoginDTO dto = new LoginDTO();
	HttpSession session;
	RequestDispatcher rD;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rD = request.getRequestDispatcher("login.jsp");
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
		result = dao.selectUser(name, pass);
			if(result){
				dto = dao.getDto();
				id = dto.getId();
				session = request.getSession();
				session.invalidate();
				session = request.getSession();
				session.setAttribute("id", id);
				rD = request.getRequestDispatcher("management_address.jsp");
				rD.forward(request, response);
			}else if(!result){
				loginMessage = "ログインに失敗しました。";
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
