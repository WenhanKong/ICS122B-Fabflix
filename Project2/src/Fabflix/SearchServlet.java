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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out = response.getWriter();
//		 System.out.println("Search: " + "redirect to index");
		 
		 String nrows = request.getParameter("nRows");
		 int nRowsToDisplay = Integer.parseInt(nrows);
		 String title, year, director, star_name, genreID;
		 int  noOfPages, currentPage;
		 currentPage = 1;
		 
		 title = request.getParameter("title");
		 year = request.getParameter("year");
		 director = request.getParameter("director");
		 star_name = request.getParameter("star_name");
		 genreID = request.getParameter("genreID");
//		 currentPage = Integer.parseInt(request.getParameter("currentPage"));	
//		 System.out.println("Now serving This title: " +title);
		 
		 List<Movie> movieList = searchMovies(title, year, director, star_name, genreID);
		 noOfPages = (movieList.size() / nRowsToDisplay);
		 
		 request.getSession().setAttribute("movie_list",movieList);
	     request.getSession().setAttribute("noOfPages", noOfPages);
	     request.getSession().setAttribute("currentPage", currentPage);
//	     request.getSession().setAttribute("nRows", nRowsToDisplay);
	     request.getSession().setAttribute("year", year);
	     request.getSession().setAttribute("director", director);
	     request.getSession().setAttribute("stare", star_name);
	     request.getSession().setAttribute("title", title);
		 RequestDispatcher movieView = request.getRequestDispatcher("/movieView.jsp");
//		 RequestDispatcher genreList = request.getRequestDispatcher("/starList.jsp");
		 movieView.forward(request, response);
		 out.close();
	 
	}

	public List<Movie> searchMovies(String title, String year_s, String director, String star_name, String genreID_s)
	{
		String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb?useSSL=false";
        List<Movie> result = new ArrayList<Movie>();
        int year, genreID;
        
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	        	Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	        	Statement statement = dbcon.createStatement();
	        	
	        	String query = "select m.id,m.title,m.year,m.director "+
	        			"from stars s, movies m, genres g, stars_in_movies sm, genres_in_movies gm "+
	        			"where m.id = sm.movieID and (s.id = sm.starID and g.id = gm.genreID and gm.movieID = m.id)";
	        	
	        	if (title != null&&!title.equals("")) {
					query = query + " AND m.title LIKE '%" + title + "%'";
	        	} 
	        	if (year_s != null&&!year_s.equals("")) {
	        			year = Integer.parseInt(year_s);
					query = query + " AND m.year LIKE '" + year + "'";
	        	}
	        	if (director != null&&!director.equals("")) {
					query = query + " AND m.director LIKE '%" + director + "%'";
	        	}
	        	if (star_name != null&&!star_name.equals("")) {
					String [] full_name = star_name.split(" ");
					if(full_name.length>1) {
						query = query + "AND s.name like '%" + full_name[0] + "%'"
									  +"OR s.name like '%" + full_name[1] + "%'";
					} else {
						query = query + "AND s.name like '%" + star_name + "%'";
					}
	        	}
	        	if (genreID_s != null && !genreID_s.equals("")) {
	        			genreID = Integer.parseInt(genreID_s);
	        			query = query + " AND g.id = '" + genreID + "'";
	        	}
	        	query = query + " GROUP BY m.id;";
//	        	System.out.println(query);
	        	ResultSet rs = statement.executeQuery(query);
	        	
	        	
	        	while (rs.next()) {
									
	        			Movie movie = new Movie();
					movie.setMovieID(rs.getString(1));
					movie.setTitle(rs.getString(2));
					movie.setYear(rs.getInt(3));
					movie.setDirector(rs.getString(4));
//					movie.setListofStars();
//					movie.setListofGenres();
					result.add(movie);
				}
	        
	        	rs.close();
	        	statement.close();
	        	dbcon.close();
	        	
//	        	for(Movie m:result) {
//	        		System.out.println("found movie");
//	        		m.setListofStars();
//	        	}
	        	} catch (SQLException ex) {
	        		while (ex != null) {
	        			System.out.println("SQL Exception:  " + ex.getMessage());
	        			ex = ex.getNextException();
	        		} // end while
	        	} catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
		return result;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
