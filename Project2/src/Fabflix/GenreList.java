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
 * Servlet implementation class GenreList
 */
@WebServlet("/GenreList")
public class GenreList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenreList() {
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
		List<Genre> genreList = searchGenres(movieID);
		request.getSession().setAttribute("genre_list",genreList);
		RequestDispatcher genreView = request.getRequestDispatcher("/genreList.jsp");
		genreView.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public List<Genre> searchGenres(String movieID){
		String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb?useSSL=false";
        List<Genre> result = new ArrayList<Genre>();
        int year;
        
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	        	Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	        	Statement statement = dbcon.createStatement();
	        	
	        	String query = "select g.id, g.name from genres g, genres_in_movies gim where g.id = gim.genreID and gim.movieID = '" + movieID +"';";
//	        	System.out.println(query);
	        	ResultSet rs = statement.executeQuery(query);
	        	
	        	while(rs.next()) {
//	        		System.out.println(rs.getString(2));
	        		Genre genre = new Genre();
	        		genre.setID(rs.getInt(1));
	        		genre.setName(rs.getString(2));
	        		result.add(genre);
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
