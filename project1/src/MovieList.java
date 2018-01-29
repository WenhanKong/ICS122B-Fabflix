
/* A servlet to display the contents of the MySQL movieDB database */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieList extends HttpServlet {
    public String getServletInfo() {
        return "Servlet connects to MySQL database and displays result of a SELECT";
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        response.setContentType("text/html"); // Response mime type

        PrintWriter out = response.getWriter();

        out.println("<HTML><HEAD><TITLE>Best 20 in MovieDB</TITLE></HEAD>");
        out.println("<BODY><H1>MovieDB</H1>");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
            Statement statement = dbcon.createStatement();

            String query =  "SELECT m.title, m.year, m.director, g.name as genre_name, s.name as star_name, r.rating FROM movies m,"
            		+ " genres g, genres_in_movies g2, stars s, stars_in_movies s2, "
            		+ "(SELECT r2.movieId, r2.rating FROM ratings r2\n" + "ORDER BY r2.rating desc\n" + 
            		"LIMIT 20) r WHERE r.movieId =m.id AND m.id = g2.movieId AND g2.genreId = g.id"
            		+ " AND s2.movieId= m.id AND s2.starId = s.id;";

            ResultSet rs = statement.executeQuery(query);

            out.println("<TABLE border>");
            
//            out.println("<tr>" + 
//            		"<td>" + "Movie Title" + "</td>" 
//            		+"<td>" + "Rating" + "<td>" 
//            		+"<td>" + "Released Year"+"</td>"
//            		+"<td>" + "Movie Director" + "</td>"
//        			+"<td>" + "List of Stars" + "</td>" 
//            		+"<td>" + "List of Genres" + "</td>" 
//            		+ "</tr>");
            out.println("<tr>" + "<td>" + "Movie Title" + "</td>" + "<td>" + "Released Year" + "</td>"+"<td>" + "Movie Director" + "</td>"
        			+"<td>" + "List of Stars" + "</td>" + "<td>" + "List of Genres" + "</td>" + "<td>" + "Rating" + "</td>" + "</tr>");
	        while (rs.next()) {
	            String movie_title = rs.getString("title");
	            Integer movie_year = rs.getInt("year");
	            Float movie_rating = rs.getFloat("rating");
	            String movie_director = rs.getString("director");
	            String movie_stars = (rs.getString("star_name")).replace(",", ", ");
	            String movie_genres = (rs.getString("genre_name")).replace(",", ", ");
//	            out.println("<tr>" 
//	            + "<td>" + m_title + "</td>" 
//	            	+ "<td>" + m_rating + "</td>" 
//	            + "<td>" + m_year + "</td>" 
//	            	+ "<td>" + m_director + "</td>"
//	            + "<td>" + m_stars + "</td>"
//	            	+ "<td>" + m_genres + "</td>" 
//	            + "</tr>");
	            out.println("<tr>" + "<td>" + movie_title + "</td>" + "<td>" + movie_year + "</td>" + "<td>" + movie_director + "</td>"
                        + "<td>" + movie_stars + "</td>"+ "<td>" + movie_genres + "</td>" + "<td>" + movie_rating + "</td>" + "</tr>");
	            
	        }

            out.println("</TABLE>");

            rs.close();
            statement.close();
            dbcon.close();
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("SQL Exception:  " + ex.getMessage());
                ex = ex.getNextException();
            } // end while
        } // end catch SQLException

        catch (java.lang.Exception ex) {
            out.println("<HTML>" + "<HEAD><TITLE>" + "MovieDB: Error" + "</TITLE></HEAD>\n<BODY>"
                    + "<P>SQL error in doGet: " + ex.getMessage() + "</P></BODY></HTML>");
            return;
        }
        out.close();
    }
}
