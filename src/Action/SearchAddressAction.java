package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SearchAddressDAO;
import DAO.ShowAddressDAO;
import DTO.SearchAddressDTO;
import DTO.ShowAddressDTO;

/**
 * Servlet implementation class SearchAddressAction
 */
@WebServlet("/SearchAddressAction")
public class SearchAddressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String zip,catchAddress,showAddress;
	int id;
	boolean result;
	SearchAddressDAO dao = new SearchAddressDAO();
	SearchAddressDTO dto = new SearchAddressDTO();
	RequestDispatcher rD;
	HttpSession session;

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
		id = (int)session.getAttribute("id");
		zip = request.getParameter("zip");
		System.out.println(zip);
		result = dao.searchAddress(zip);
		if(result){
			dto = dao.getDto();
			catchAddress = dto.getZip()+dto.getKen_name()+dto.getCity_name()+dto.getWard()+dto.getTown_name();
			System.out.println(catchAddress);
			request.setAttribute("catchAddress", catchAddress);
			request.setAttribute("zip", zip);
			ShowAddressDAO dao = new ShowAddressDAO();
			ShowAddressDTO dto = new ShowAddressDTO();
			dto = dao.getDto();
			dao.selectAddress(id);
			showAddress = dto.getAddress();
			request.setAttribute("showAddress", showAddress);
			rD = request.getRequestDispatcher("management_address.jsp");
			rD.forward(request, response);
		}else if(!result){
			catchAddress = dto.getKen_name()+dto.getCity_name()+dto.getWard()+dto.getTown_name();
			System.out.println(catchAddress);
			System.out.println("エラー");
			request.setAttribute("catchAddress", catchAddress);
			ShowAddressDAO dao = new ShowAddressDAO();
			ShowAddressDTO dto = new ShowAddressDTO();
			dto = dao.getDto();
			dao.selectAddress(id);
			showAddress = dto.getAddress();
			request.setAttribute("showAddress", showAddress);
			rD = request.getRequestDispatcher("management_address.jsp");
			rD.forward(request, response);
		}

	}

}
