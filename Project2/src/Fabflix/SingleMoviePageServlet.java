package Fabflix;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SingleMoviePage
 */
@WebServlet("/SingleMoviePage")
public class SingleMoviePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleMoviePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb?useSSL=false";
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String movieID;
		movieID = request.getParameter("movieID");
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	        	Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	        	Statement statement = dbcon.createStatement();
	        	String query = "select * from movies where id = '" + movieID + "';";
//	        	System.out.println(query);
	        	ResultSet rs = statement.executeQuery(query);
	        	if(rs.next()) {
//	        		System.out.println(rs.getString(2));
	        		request.getSession().setAttribute("movieID", rs.getString(1));
	        		request.getSession().setAttribute("title", rs.getString(2));
	        		request.getSession().setAttribute("year", rs.getInt(3));
	        		request.getSession().setAttribute("director", rs.getString(4));
	        	}
	        	
	        	RequestDispatcher singleMoviePage = request.getRequestDispatcher("/singleMoviePage.jsp");
	        	singleMoviePage.forward(request, response);
	        	rs.close();
	        	statement.close();
	        	dbcon.close();
	
		} catch( Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
