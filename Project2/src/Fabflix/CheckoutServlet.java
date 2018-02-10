package Fabflix;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String cardNumber = request.getParameter("cardNumber");
		String date = request.getParameter("date");
		
//		System.out.println(firstName);
//		System.out.println(lastName);
//		System.out.println(cardNumber);
//		System.out.println(date);
		String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb?useSSL=false";

        
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	        	Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	        	Statement statement = dbcon.createStatement();
	        	
	        	String query = "select * from creditcards where id ='" + cardNumber+"' and firstName = '" + firstName+"' and lastName = '"
	        				+lastName+"' and expiration = '"+date+"';"; 
	        			
	        	ResultSet rs = statement.executeQuery(query);
	        	if(rs.next()) {
	    			request.getSession().setAttribute("purchaseResult", "sucess");	    			
	    		} else {
	    			request.getSession().setAttribute("purchaseResult", "fail");
	    		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("purchaseResult.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
