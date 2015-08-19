

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Details
 */
@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String name = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custId = request.getParameter("customerId");
		String url = "jdbc:oracle:thin:testuser/password@localhost";
		name="";
		Properties props = new Properties();
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, props);
			Statement st = con.createStatement();
			System.out.println("connection established successfully...!!");

			ResultSet rs = st.executeQuery("Select * from Demo_Customers where CUSTOMER_ID = " + Integer.parseInt(custId));
			name+="<br></br>";
			name += "<table border=2 width = 60% background-color:Light grey>"; 
			name += "<tr><td><b> Customer Name</b> </td><td> <b>Street Address</b> </td><td> <b> City </b></td></td><td> <b> State </b></td></td><td> <b> Zip Code </b></td>" +
					"</td><td> <b> Home Number </b></td> </td><td> <b> Cell Number </b></td> </td><td> <b> Credit Limit </b></td> </td><td> <b> Email </b></td></tr>";
			
			rs.next();
			name+= "<tr><td>" + rs.getString(2) + " " + rs.getString(3) + "</td><td>"
					+ rs.getString(4) + " " + rs.getString(5) + "</td><td>" + rs.getString(6) +"</td><td>" + rs.getString(7) + "</td><td>" + rs.getString(8) 
					+ "</td><td>" + rs.getString(9) + "</td><td>" + rs.getString(10) + "</td><td>" + rs.getString(11)   + "</td><td>" + rs.getString(12) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		name+="<br></br>";
		request.setAttribute("message", name);
		getServletContext().getRequestDispatcher("/output.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
