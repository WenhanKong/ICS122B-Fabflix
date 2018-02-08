package Fabflix;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Star {
	private String id;
	private String name;
	private int birthYear;
//	private Date date;
//	private String photo_url;
	
//	private ArrayList<Movie> movies;
	
	public Star(){
//		this.movies = new ArrayList<Movie>();
	}
	
	public Star(String id, String name) {
		super();
		this.id = id;
		this.name = name;;
//		this.movies = new ArrayList<Movie>();
	}
	
//	public void addMovie(Movie movie)
//	{
//		movies.add(movie);
//	}
	
//	public void clearMovies()
//	{
//		movies.clear();
//	}
	
	/**
	 * @return the id
	 */
	public String getID() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setID(String id) {
		this.id = id;
	}
	/**
	 * @return the first_name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param first_name the first_name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int b) {
		this.birthYear = b;
	}
}
