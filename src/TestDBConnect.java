

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDBConnect
 */
@WebServlet("/TestDBConnect")
public class TestDBConnect extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDBConnect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();

		    out.println("<html>");
		    out.println("<head>");
		    out.println("<title>データベーステスト</title>");
		    out.println("</head>");
		    out.println("<body>");

		    out.println("<p>");

		    Connection conn = null;
		    String url = "jdbc:mysql://localhost/HNS";
		    String user = "root";
		    String password = "mysql";
		    String driverName = "com.mysql.jdbc.Driver";

		    try {
		      Class.forName(driverName).newInstance();
		      out.println("ドライバのロードに成功しました<br>");

		      conn = DriverManager.getConnection(url, user, password);
		      out.println("データベース接続に成功しました<br>");
		    }catch (ClassNotFoundException e){
		      out.println("ClassNotFoundException:" + e.getMessage());
		    }catch (SQLException e){
		      out.println("SQLException:" + e.getMessage());
		    }catch (Exception e){
		      out.println("Exception:" + e.getMessage());
		    }finally{
		      try{
		        if (conn != null){
		          conn.close();
		          out.println("データベース切断に成功しました");
		        }else{
		          out.println("コネクションがありません");
		        }
		      }catch (SQLException e){
		        out.println("SQLException:" + e.getMessage());
		      }
		    }
		    out.println("</p>");
		    out.println("</body>");
		    out.println("</html>");
	}


}
