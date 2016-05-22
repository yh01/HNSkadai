package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.InsertOrUpdateAddressDAO;
import DTO.InsertOrUpdateAddressDTO;

/**
 * Servlet implementation class InsertOrUpdateAddressAction
 */
@WebServlet("/InsertOrUpdateAddressAction")
public class InsertOrUpdateAddressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int count,id;
	String address,managementAddressMessage,catchAddress;
	boolean check,getAddress;
	HttpSession session;
	RequestDispatcher rD;
	InsertOrUpdateAddressDAO dao = new InsertOrUpdateAddressDAO();
	InsertOrUpdateAddressDTO dto = new InsertOrUpdateAddressDTO();
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
		if(session.getAttribute("id") != null){
			id = (int)session.getAttribute("id");
			address = request.getParameter("address");
			dto = dao.getDto();
			if(!address.isEmpty()){
				check = dao.checkAddress(id);
				if(check){
					count = dao.updateAddress(id, address);
					if(count > 0){
						getAddress = dao.getAddress(id);
						if(getAddress){
							catchAddress = dto.getAddress();
							request.setAttribute("catchAddress", catchAddress);
							managementAddressMessage = "住所情報の更新に成功しました。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							rD = request.getRequestDispatcher("management_address.jsp");
							rD.forward(request, response);
						}else if(!getAddress){
							managementAddressMessage = "住所情報の更新に成功しました。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							rD = request.getRequestDispatcher("management_address.jsp");
							rD.forward(request, response);
						}
					}else if(count == 0){
						managementAddressMessage = "住所情報の更新に失敗しました。";
						request.setAttribute("managementAddressMessage", managementAddressMessage);
						rD = request.getRequestDispatcher("management_address.jsp");
						rD.forward(request, response);
					}
				}else if(!check){
					count = dao.insertAddress(id, address);
					if(count > 0){
						getAddress = dao.getAddress(id);
						if(getAddress){
							catchAddress = dto.getAddress();
							request.setAttribute("catchAddress", catchAddress);
							managementAddressMessage = "住所情報の新規登録に成功しました。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							rD = request.getRequestDispatcher("management_address.jsp");
							rD.forward(request, response);
						}else if(!getAddress){
							managementAddressMessage = "住所情報の新規登録に成功しました。";
							request.setAttribute("managementAddressMessage", managementAddressMessage);
							rD = request.getRequestDispatcher("management_address.jsp");
							rD.forward(request, response);
						}
					}else if(count == 0){
						managementAddressMessage = "住所情報の新規登録に失敗しました。";
						request.setAttribute("managementAddressMessage", managementAddressMessage);
						rD = request.getRequestDispatcher("management_address.jsp");
						rD.forward(request, response);
					}
				}
			}else if(address.isEmpty()){
				managementAddressMessage = "住所を入力してください。";
				request.setAttribute("managementAddressMessage", managementAddressMessage);
				rD = request.getRequestDispatcher("management_address.jsp");
				rD.forward(request, response);
			}
		}else if(session.getAttribute("id") == null){
			managementAddressMessage = "未ログインなのでログイン画面に移動しました。";
			request.setAttribute("loginMessage", managementAddressMessage);
			rD = request.getRequestDispatcher("login.jsp");
			rD.forward(request, response);
		}
	}
}
