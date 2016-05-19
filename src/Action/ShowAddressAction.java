package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ShowAddressDAO;
import DTO.ShowAddressDTO;

/**
 * Servlet implementation class ShowAddressAction
 */
@WebServlet("/ShowAddressAction")
public class ShowAddressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int id;
	String address,showAddressMessage;
	boolean result;
	ShowAddressDAO dao = new ShowAddressDAO();
	ShowAddressDTO dto = new ShowAddressDTO();
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
			result = dao.selectAddress(id);
			if(result){
				showAddressMessage = "表示します";
				request.setAttribute("showAddressMessage", showAddressMessage);
				dto = dao.getDto();
				address = dto.getAddress();
				request.setAttribute("address", address);
				rD = request.getRequestDispatcher("management_address.jsp");
				rD.forward(request, response);
			}else if(!result){
				showAddressMessage = "表示出来ませんでした。住所情報が登録されていない可能性があります。";
				request.setAttribute("showAddressMessage", showAddressMessage);
				rD = request.getRequestDispatcher("management_address.jsp");
				rD.forward(request, response);
			}
		}else if(session.getAttribute("id")==null){
			showAddressMessage = "未ログインなのでログイン画面に移動しました。";
			request.setAttribute("loginMessage", showAddressMessage);
			rD = request.getRequestDispatcher("login.jsp");
			rD.forward(request, response);
		}
	}

}
