<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="Fabflix.*"%>
<%@ page language="java"
	import="java.sql.*, 
	java.util.*,
	java.io.IOException,
	javax.servlet.http.*,
	javax.servlet.*"%>


<% 
List<Movie> movies = (List<Movie>)session.getAttribute("movie_list");

int noOfPages = (int)session.getAttribute("noOfPages");

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
				<th></th>
				<th></th>
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
				<td>
					<form action = "shoppingCartServlet?movieID=<%=movie.getMovieID() %>&title=<%=movie.getTitle()%>" method="post" name="addtocart">
							<button type="submit" value="Add to Cart">Add to Cart</button>
					</form>
				</td>
			</tr>
			<%
			  }
			%>
		</tbody>
		</table>
		<td><a href="/Project2/search.jsp">Back to Search</a></td>
			<br>
		<br>
		<button style="float:right" onclick="window.location.href='shoppingCart.jsp'">Checkout</button>
	</body>
</html>