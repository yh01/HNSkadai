package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CodeTestDAO;
import DTO.CodeTestDTO;

/**
 * Servlet implementation class CodeTestAction
 */
@WebServlet("/CodeTestAction")
public class CodeTestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name,pass,hexName,hexPass;
	HttpSession session;
	RequestDispatcher rD;
	CodeTestDAO dao = new CodeTestDAO();
	CodeTestDTO dto = new CodeTestDTO();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		rD = request.getRequestDispatcher("codeTest.jsp");
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
		dao.getTest();
		dto = dao.getDto();
		hexName = dto.getName();
		hexPass = dto.getPass();
		request.setAttribute("hexName", hexName);
		request.setAttribute("hexPass", hexPass);
		rD = request.getRequestDispatcher("codeTest.jsp");
		rD.forward(request, response);
	}

}
