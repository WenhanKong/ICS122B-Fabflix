<%@ page import="Fabflix.*"%>

<%@page import="java.sql.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*,
 java.util.*"
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String letter = request.getParameter("letter");
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    Connection connection =
    DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "mytestuser", "mypassword");
    Statement statement = connection.createStatement();
    ResultSet rs = statement.executeQuery("Select *  from movies where title like '" + letter +"%';");
	List<Movie> movies = new ArrayList<Movie>();

    while (rs.next()){
      Movie m = new Movie();
      m.setMovieID(rs.getString(1));
      m.setTitle(rs.getString(2));
      m.setYear(rs.getInt(3));
      m.setDirector(rs.getString(4));
      movies.add(m);
    }
    if (movies == null){
        movies = new ArrayList<Movie>();
    }
%>


<!DOCTYPE html >

<html>
	<head>
      
	 <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
	  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
	  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
	  <script type="text/javascript">
	        $(document).ready(function () {
	            $("#movieTable").dataTable({
              	 "sPaginationType":"full_numbers", 
	
	            
	            });
	        });
	        </script>
	</head>
	<body>
	  <table id = "movieTable" class="display" cellspacing="0" width="100%">
	    <thead>
			<tr>
				<th>
					Movie ID
				</th>
				<th>Movie Name</th>
				<th>Year</th>
				<th>Director</th>
				<th>List Genre</th>
				<th>Actors List</th>
				<th>Buy</th>
			</tr>
		</thead>
		<tbody>
			<% 
				for (Movie movie:movies) {
			%>
			<tr>
				<td><%=movie.getMovieID() %></td>
				<td><a href ="/Project2/singleMoviePage?movieID=<%=movie.getMovieID()%>"><%=movie.getTitle() %></a></td>
				
				<td><%=movie.getYear() %></td>
				<td><%=movie.getDirector() %></td>
				<td><a
					href="/Project2/genreList?movieID=<%=movie.getMovieID()%>">Genres</a></td>
				<td><a
					href="/Project2/starList?movieID=<%=movie.getMovieID()%>">Stars</a></td>
				<td><a
					href="/Project2/checkout?movieID=<%=movie.getMovieID()%>">Buy</a></td>
			</tr>
			<%
			  }
			%>
		</tbody>
		</table>
		<td><a href="/Project2/browse.jsp">Back to Browse</a></td>
			<br>
	<br>
	<button style="float:right" onclick="window.location.href='shoppingCart.jsp'">Checkout</button>
	</body>
</html>