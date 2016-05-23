package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MasterAddressDAO;
import DTO.MasterAddressDTO;

/**
 * Servlet implementation class TestMasterAddressAction
 */
@WebServlet("/TestMasterAddressAction")
public class TestMasterAddressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int count,id;
	String address,testMasterAddressMessage,zip,kenOrCapital, cityName, ward, townNum;
	boolean check;
	HttpSession session;
	RequestDispatcher rD;
	MasterAddressDAO dao = new MasterAddressDAO();
	MasterAddressDTO dto = new MasterAddressDTO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		rD = request.getRequestDispatcher("test_master_address.jsp");
		rD.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		address = request.getParameter("address");
		System.out.println(address);
		check=dao.checkAddress(address);
		if(check){
			dto = dao.getDto();
			zip = dto.getZip();
			kenOrCapital = dto.getKenOrCapital();
			cityName = dto.getCityName();
			ward = dto.getWard();
			townNum = dto.getTownNum();
			System.out.println(kenOrCapital);
			count = dao.insertMaster(zip,kenOrCapital, cityName, ward, townNum);
			if(count > 0){
				testMasterAddressMessage = "マスタ化成功";
				request.setAttribute("testMasterAddressMessage", testMasterAddressMessage);
				rD = request.getRequestDispatcher("test_master_address.jsp");
				rD.forward(request, response);
			}else if(count == 0){
				testMasterAddressMessage = "マスタ化失敗";
				request.setAttribute("testMasterAddressMessage", testMasterAddressMessage);
				rD = request.getRequestDispatcher("test_master_address.jsp");
				rD.forward(request, response);
			}
		}else{
			testMasterAddressMessage = "チェック失敗";
			request.setAttribute("testMasterAddressMessage", testMasterAddressMessage);
			rD = request.getRequestDispatcher("test_master_address.jsp");
			rD.forward(request, response);
		}
	}

}
