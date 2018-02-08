package Fabflix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Genre {
	private int id;
	private String name;
//	private ArrayList<Movie> movies;

	public Genre(){
	}
	
	public Genre(int id, String name){
//		super();
		
		this.id = id;
		this.name = name;
//		this.movies = new ArrayList<Movie>();
	}
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
/*	
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	public void setListofMovies() {

		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/moviedb", "mytestuser", "mypassword" );
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select id, name  from movies m, genres_in_movies gim where m.id= gim.movie_id and movie_id=" + id);
			while(rs.next()){
				Movie m  = new Movie();
				m.setMovieID(rs.getString(1));
				m.setTitle(rs.getString(2));
				this.movies.add(m);
			}
			rs.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			while (e != null) {
				System.out.println("SQL Exception:  " + e.getMessage());
				e = e.getNextException();
			} 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
*/

