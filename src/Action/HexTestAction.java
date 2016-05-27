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
 * Servlet implementation class HexTestAction
 */
@WebServlet("/HexTestAction")
public class HexTestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String hName,hPass;
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
		hName = request.getParameter("hName");
		hPass = request.getParameter("hPass");
		System.out.println(hName);
		dao.insertHex(hName, hPass);
		dto = dao.getDto();
		rD = request.getRequestDispatcher("codeTest.jsp");
		rD.forward(request, response);
	}

}
