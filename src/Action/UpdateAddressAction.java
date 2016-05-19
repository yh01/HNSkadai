package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UpdateAddressDAO;

/**
 * Servlet implementation class UpdateAddressAction
 */
@WebServlet("/UpdateAddressAction")
public class UpdateAddressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int count,id;
	String address,updateAddressMessage;
	UpdateAddressDAO dao = new UpdateAddressDAO();
	HttpSession session;
	RequestDispatcher rD;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		rD = request.getRequestDispatcher("management_address.jsp");
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
				count = dao.updateAddress(id, address);
				if(count > 0){
					updateAddressMessage = "住所情報の更新に成功しました。";
					request.setAttribute("updateAddressMessage", updateAddressMessage);
					rD = request.getRequestDispatcher("management_address.jsp");
					rD.forward(request, response);
				}else if(count == 0){
					updateAddressMessage = "住所情報の更新に失敗しました。\n住所情報の新規登録が行われていない可能性があります。";
					request.setAttribute("updateAddressMessage", updateAddressMessage);
					rD = request.getRequestDispatcher("management_address.jsp");
					rD.forward(request, response);
				}
			}else if(address.isEmpty()){
				updateAddressMessage = "更新する住所が入力されていません。";
				request.setAttribute("updateAddressMessage", updateAddressMessage);
				rD = request.getRequestDispatcher("management_address.jsp");
				rD.forward(request, response);
			}
		}else if(session.getAttribute("id")==null){
			updateAddressMessage = "未ログインなのでログイン画面に移動しました。";
			request.setAttribute("loginMessage", updateAddressMessage);
			rD = request.getRequestDispatcher("login.jsp");
			rD.forward(request, response);
		}
	}

}
