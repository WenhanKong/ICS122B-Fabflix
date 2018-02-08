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

public class Movie {
	private String id;
	private String title;
	private int year;
	private String director;
	protected List<Star> stars = new ArrayList<Star>();
	private List<Genre> genres;
	
	public Movie(){
	}
	
	public String getMovieID(){
		return id;
	}
	
	public void setMovieID(String id){
		this.id = id;
	}
	
	public int getYear(){
		return year;
	}
	public void setYear(int year){
		this.year = year;
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getDirector(){
		return director;
	}
	public void setDirector(String director){
		this.director = director;
	}
	
	public List<Star> getStars(){
		return stars;
	}
	
	public void setListofStars() {
		System.out.println("enter set list of stars");
		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "mytestuser", "mypassword" );
			Statement statement = connection.createStatement();
			String query = "select s.id, s.name from stars s, stars_in_movies sim where s.id=sim.starID and movieID LIKE '" + id+ "';";
			System.out.println(query);
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				System.out.println("find a star");
				Star s  = new Star();
				s.setID(rs.getString(1));
				s.setName(rs.getString(2));
				this.stars.add(s);
			}
			rs.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for(Star s:stars) {
//			System.out.println(s.getName());
//		}
	}
	
	
	public List<Genre> getGenres(){
		return genres;
	}
	
	public void setListofGenres() {
		String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
        		Statement statement = dbcon.createStatement();
			System.out.println("enter set list of genres");
			String query = "select g.id, g.name from genres g, genres_in_movies gim where g.id=gim.genreID and gim.movieID LIKE '%" + id +"%';";
//			System.out.println(query);
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				Genre g  = new Genre();
				g.setID(rs.getInt(1));
				g.setName(rs.getString(2));
				genres.add(g);
			}
			rs.close();
			statement.close();
			dbcon.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.Exception ex) {
            System.out.println(ex.getMessage());
        }
		for(Genre g: genres) {
			System.out.println(g.getName());
		}
	}
	
}
