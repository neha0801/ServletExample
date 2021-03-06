
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/LoopingDatabase")
public class LoopingDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoopingDatabase() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// PrintWriter write =response.getWriter();
		// write.println("<h1> Hello World </h1>");
		// request.setAttribute("message", "Hello World");
		// getServletContext().getRequestDispatcher("/output.jsp").forward(request,
		// response);

		String url = "jdbc:oracle:thin:testuser/password@localhost";
		String name = "";
		
		Properties props = new Properties();
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, props);
			Statement st = con.createStatement();
			System.out.println("connection established successfully...!!");

			ResultSet rs = st.executeQuery("Select * from Demo_Customers");
			name+="<br></br>";
			name += "<table border=2 width = 50% background-color:Light grey>"; 
			name += "<tr><td><b> Customer ID</b> </td><td> <b>FIRST NAME</b> </td><td> <b> LAST NAME </b></td><tr>";
			while (rs.next()) {
				name += ("<tr><td>" + rs.getInt(1) + "</td><td><a href=Details?customerId=" +rs.getInt(1) + ">"
						+ rs.getString("CUST_FIRST_NAME") + "</a></td><td>"
						+ rs.getString("CUST_LAST_NAME") + "</td></tr>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("message", name);
		getServletContext().getRequestDispatcher("/output.jsp").forward(
				request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
