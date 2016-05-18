package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.InsertAddressDAO;

/**
 * Servlet implementation class InsertAddressAction
 */
@WebServlet("/InsertAddressAction")
public class InsertAddressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int count;
	Object id;
	String address;
	InsertAddressDAO dao = new InsertAddressDAO();
	HttpSession session;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rD = request.getRequestDispatcher("trn_address.jsp");
		rD.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		id = session.getAttribute("id");
		address = request.getParameter("address");
		count = dao.insertAddress((int)id, address);
		if(count > 0){
			RequestDispatcher rD = request.getRequestDispatcher("success_insert.jsp");
			rD.forward(request, response);
		}else if(count == 0){
			RequestDispatcher rD = request.getRequestDispatcher("trn_address.jsp");
			rD.forward(request, response);
		}
	}

}
