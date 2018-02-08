<%@ page import="Fabflix.*"%>
<%@ page language="java"
	import="java.sql.*, 
	java.util.*,
	java.io.IOException,
	javax.servlet.http.*,
	javax.servlet.*"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
List<Genre> genres = (List<Genre>)session.getAttribute("genreList");
List<Movie> movies = (List<Movie>)session.getAttribute("movieList");
if(genres==null){
	genres = new ArrayList<Genre>();
}
if(movies==null){
	movies = new ArrayList<Movie>();
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browsing</title>


<link rel="stylesheet"
			href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
<script type="text/javascript" src="//gyrocode.github.io/jquery-datatables-alphabetSearch/1.2.2/js/dataTables.alphabetSearch.min.js"></script>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript">
       $(document).ready(function () {
           $("#movie_table").dataTable({
            	 "sPaginationType":"full_numbers", 
            	 "aaSorting": [[ 1, "asc" ]],
            	 'dom': 'Alfrtip',
            	 'alphabetSearch': {
            	      column: 1
            	 }
           });
           
           
       });
 </script>
<script type= "text/javascript">
	$(document).ready(function(){
	    $('#showGenres').live('click', function(event) {        
	        $('#genreList').toggle('show');
	   });
	});
	$(document).ready(function(){
	    $('#showMovies').live('click', function(event) {        
	        $('#movieList').toggle('show');
	   });
	});
</script>
<style>


</style>
</head>

<body>
	<div style="width: 100%; height: 50%; clear:both" >
		<input type='button' id='showGenres' value='Browse by Genres'>
		<input type='button' id='showMovies' value='Browse by Title'>
	</div>
	<br>
	<div id='genreList' style="width: 20%; height: 80%; float:left;">
		<table id = 'genre_table' class= "display">
		<thead>
			<tr>
				<th>Genre Name</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(Genre g:genres){
			%>
			<tr>
				<td><a href = "/Project2/search?title=&year=&director=&star_name=&genreID=<%=g.getID()%>&nRows=10"><%=g.getName()%></a><td>
			</tr>
			<%
				}
			%>
		</tbody>
		</table>
		
		
	</div>
	<p></p>
	<p></p>
	<div id='movieList' style="width: 80%; height: 80%; float:right;">
		<table id = "movie_table" class="display" cellspacing="0" width="100%">
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
		<p></p>
		<td><a href="/Project2/searchByAlpha.jsp?letter=A">A</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=B">B</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=C">C</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=D">D</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=E">E</a></td>
	 	<td><a href="/Project2/searchByAlpha.jsp?letter=F">F</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=G">G</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=H">H</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=I">I</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=J">J</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=K">K</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=L">L</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=M">M</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=N">N</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=O">O</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=P">P</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=Q">Q</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=R">R</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=S">S</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=T">T</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=U">U</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=V">V</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=W">W</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=X">X</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=Y">Y</a></td>
		<td><a href="/Project2/searchByAlpha.jsp?letter=Z">Z</a></td>
	</div>
	<p></p>
	<p></p>
	<div style="width: 100%; height: 10%; clear:both">
		<td style="border-right: solid;"><a
				href="/Project2/search.jsp">Back to Search</a></td>
		<td style="border-right: solid;"><a
					href="/Project2/index.html">Back to Home</a></td>
		
		
		
	</div>
</body>
	
</html>