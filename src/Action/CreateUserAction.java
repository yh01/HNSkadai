package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CreateUserDAO;

/**
 * Servlet implementation class CreateUserAction
 */
@WebServlet("/CreateUserAction")
public class CreateUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name,pass;
	int count;
	CreateUserDAO dao = new CreateUserDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */

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
		count = dao.insertUser(name,pass);
		if(count > 0){
			RequestDispatcher rD = request.getRequestDispatcher("success_create_user.jsp");
			rD.forward(request, response);
		}else if(count == 0){
			RequestDispatcher rD = request.getRequestDispatcher("login.jsp");
			rD.forward(request, response);
		}
	}

}
