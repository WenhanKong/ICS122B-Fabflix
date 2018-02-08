package Fabflix;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StarList
 */
@WebServlet("/StarList")
public class StarList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StarList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String movieID = request.getParameter("movieID");
		List<Star> starList = searchStars(movieID);
		request.getSession().setAttribute("star_list",starList);
		RequestDispatcher starView = request.getRequestDispatcher("/starList.jsp");
		starView.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public List<Star> searchStars(String movieID){
		String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb?useSSL=false";
        List<Star> result = new ArrayList<Star>();
        int year;
        
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	        	Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	        	Statement statement = dbcon.createStatement();
	        	
	        	String query = "select s.id, s.name from stars s, stars_in_movies sim where s.id = sim.starID and sim.movieID = '" + movieID +"';";
//	        	System.out.println(query);
	        	ResultSet rs = statement.executeQuery(query);
	        	
	        	while(rs.next()) {
//	        		System.out.println(rs.getString(2));
	        		Star star = new Star();
	        		star.setID(rs.getString(1));
	        		star.setName(rs.getString(2));
	        		result.add(star);
	        	}
	        	rs.close();
	        	statement.close();
	        	dbcon.close();
	        	
	        	
	        	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
