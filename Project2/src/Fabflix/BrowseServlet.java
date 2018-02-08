package Fabflix;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BrowseServlet
 */
@WebServlet("/BrowseServlet")
public class BrowseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb?useSSL=false";
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Genre> genres = new ArrayList<Genre>(); 
		List<Movie> movies = new ArrayList<Movie>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	        	Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	        	Statement statement = dbcon.createStatement();
	        	Statement statement2 = dbcon.createStatement();
	        	String query = "select * from genres;";
	        	String query2 = "select * from movies;";
//	        	System.out.println(query);
	        	ResultSet rs = statement.executeQuery(query);
	        	ResultSet rs2 = statement2.executeQuery(query2);
	        	while(rs.next()) {
	        		Genre g = new Genre();
	        		g.setID(rs.getInt(1));
	        		g.setName(rs.getString(2));
	        		genres.add(g);
//	        		System.out.println(rs.getInt(1));
	        	}
	        	while(rs2.next()) {
	        		Movie m = new Movie();
	        		m.setMovieID(rs2.getString(1));
	        		m.setTitle(rs2.getString(2));
	        		m.setYear(rs2.getInt(3));
	        		m.setDirector(rs2.getString(4));
	        		movies.add(m);
	        	}
	        	request.getSession().setAttribute("genreList", genres);
	        	request.getSession().setAttribute("movieList", movies);

	        	RequestDispatcher result = request.getRequestDispatcher("/browse.jsp");
	        	result.forward(request, response);
	        	
	        
	        	rs.close();
	        	rs2.close();
	        	statement.close();
	        	statement2.close();
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
