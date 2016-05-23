package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CreateUserDAO;
import DAO.LoginDAO;
import DTO.LoginDTO;

/**
 * Servlet implementation class CreateUserAction
 */
@WebServlet("/CreateUserAction")
public class CreateUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name,pass,createUserMessage;
	int id;
	boolean check;
	int count;
	CreateUserDAO dao = new CreateUserDAO();
	RequestDispatcher rD;
	HttpSession session;

    /**
     * @see HttpServlet#HttpServlet()
     */

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
			check = dao.checkUserName(name);
			if(!check){
				count = dao.insertUser(name,pass);
				if(count > 0){
					LoginDAO dao = new LoginDAO();
					LoginDTO dto = new LoginDTO();
					dao.selectUser(name, pass);
					dto = dao.getDto();
					id = dto.getId();
					System.out.println(id);
					session = request.getSession();
					session.invalidate();
					session = request.getSession();
					session.setAttribute("id", id);
					session.setAttribute("name", name);
					rD = request.getRequestDispatcher("management_address.jsp");
					rD.forward(request, response);
				}else if(count == 0){
					createUserMessage = "登録に失敗しました。";
					request.setAttribute("createUserMessage", createUserMessage);
					rD = request.getRequestDispatcher("login.jsp");
					rD.forward(request, response);
				}
			}else if(check){
				createUserMessage = "この名前は登録済みです。";
				request.setAttribute("createUserMessage", createUserMessage);
				rD = request.getRequestDispatcher("login.jsp");
				rD.forward(request, response);
			}
		}else if(name.isEmpty() || pass.isEmpty()){
			createUserMessage = "名前とパスワード両方入力してください。";
			request.setAttribute("createUserMessage", createUserMessage);
			rD = request.getRequestDispatcher("login.jsp");
			rD.forward(request, response);
		}
	}

}
