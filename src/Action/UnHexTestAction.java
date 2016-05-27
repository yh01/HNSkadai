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
 * Servlet implementation class UnHexTestAction
 */
@WebServlet("/UnHexTestAction")
public class UnHexTestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String nName,nPass,hexName,hexPass;
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
		dao.getUnHex();
		dto = dao.getDto();
		nName = dto.getName();
		nPass = dto.getPass();
		request.setAttribute("nName", nName);
		request.setAttribute("nPass", nPass);
		rD = request.getRequestDispatcher("codeTest.jsp");
		rD.forward(request, response);
	}

}
