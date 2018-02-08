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
 * Servlet implementation class SingleMoviePage
 */
@WebServlet("/SingleStarPageServlet")
public class SingleStarPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleStarPageServlet() {
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
		String starID, star_name;
		starID = request.getParameter("starID");
//		star_name = request.getParameter("star_name");
		List<Movie> movies = new ArrayList<Movie>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	        	Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	        	Statement statement = dbcon.createStatement();
	        	String query = "select m.id, m.title, m.year, m.director from movies m, stars_in_movies sim where m.id = sim.movieId and sim.starId = '" + starID + "';";
//	        	System.out.println(query);
	        	ResultSet rs = statement.executeQuery(query);
	        	while(rs.next()) {
//	        		System.out.println(rs.getString(2));
	        		Movie m = new Movie();
	        		m.setMovieID(rs.getString(1));
	        		m.setTitle(rs.getString(2));
	        		m.setYear(rs.getInt(3));
	        		m.setDirector(rs.getString(4));
	        		movies.add(m);
	        	}
	       
	        	request.getSession().setAttribute("movie_list_of_star", movies);
//	        	request.setAttribute("star_name", star_name);
//	        	request.setAttribute("starID", starID);
	        	RequestDispatcher singleStarPage = request.getRequestDispatcher("/singleStarPage.jsp");
	        	singleStarPage.forward(request, response);
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
