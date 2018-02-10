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

List<Movie> movies = (List<Movie>)session.getAttribute("movie_list_of_star");
String star_name = request.getParameter("star_name");
String starID = request.getParameter("starID");
int birthYear = Integer.parseInt(request.getParameter("birthYear"));
String birthYearInfo = "";
//System.out.println(birthYear);
if(birthYear == 0){
	birthYearInfo = "We do not have the record for now.";
//	System.out.println("no birthyear");
}
if (movies == null){
    movies = new ArrayList<Movie>();
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
          <link rel="stylesheet"
			href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		
		  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
		  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
		  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
		  <script type="text/javascript">
		        $(document).ready(function () {
		            $("#movie_list_of_star").dataTable({
	              	 "sPaginationType":"full_numbers", 
				            
		            });
		        });
		  </script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Single Star Page</title>
	</head>
	<body>

		<h3>Movies of the star:<%=star_name%></h3>
		<p>ID: <%=starID%>
		<p>Birth Year: <%=birthYear %> <%=birthYearInfo %>
		<br>
		<table id = movie_list_of_star class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>Movie ID</th>
					<th>Movie Title</th>
					<th>Year</th>
					<th>Director</th>
					
				</tr>
			</thead>
			<tbody>
			<% 
				for (Movie m:movies) {
			%>
				
				<tr>
		
					<td><%=m.getMovieID()%></td>
					<td><a href = "/Project2/singleMoviePage?movieID=<%=m.getMovieID()%>"><%=m.getTitle() %></a></td>
					<td><%=m.getYear() %></td>
					<td><%=m.getDirector() %></td>
				</tr>
			<%
				  }
				
			%>
			</tbody>
		</table>
		<br>
		<td style="border-right: solid;"><a
				href="/Project2/search.jsp">Back to Search</a></td>
			<br>
	<br>
	<button style="float:right" onclick="window.location.href='shoppingCart.jsp'">Checkout</button>
</body>
</html>

