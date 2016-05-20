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
	int count,id;
	String address,insertAddressMessage;
	InsertAddressDAO dao = new InsertAddressDAO();
	HttpSession session;
	RequestDispatcher rD;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		rD = request.getRequestDispatcher("insert_address.jsp");
		rD.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		if(session.getAttribute("id")!=null){
			id = (int)session.getAttribute("id");
			address = request.getParameter("address");
			if(!address.isEmpty()){
				count = dao.insertAddress(id, address);
				if(count > 0){
					insertAddressMessage = "住所の登録に成功しました。";
					request.setAttribute("insertAddressMessage", insertAddressMessage);
					rD = request.getRequestDispatcher("management_address.jsp");
					rD.forward(request, response);
				}else if(count == 0){
					insertAddressMessage = "住所の登録に失敗しました。既に登録済みの可能性があります。";
					request.setAttribute("insertAddressMessage", insertAddressMessage);
					rD = request.getRequestDispatcher("management_address.jsp");
					rD.forward(request, response);
				}
			}else if(address.isEmpty()){
				insertAddressMessage = "住所を入力してください。";
				request.setAttribute("insertAddressMessage", insertAddressMessage);
				rD = request.getRequestDispatcher("management_address.jsp");
				rD.forward(request, response);
			}
		}else if(session.getAttribute("id")==null){
			insertAddressMessage = "未ログインなのでログイン画面に移動しました。";
			request.setAttribute("loginMessage", insertAddressMessage);
			rD = request.getRequestDispatcher("login.jsp");
			rD.forward(request, response);
		}
	}

}
