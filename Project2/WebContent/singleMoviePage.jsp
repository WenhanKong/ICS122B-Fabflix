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

String movieID  = (String)session.getAttribute("movieID");
String title  = (String)session.getAttribute("title");
int year  = (int)session.getAttribute("year");
String director  = (String)session.getAttribute("director");

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
		            $("#single_movie_table").dataTable({
	              	 "sPaginationType":"full_numbers", 
				            
		            });
		        });
		  </script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	</head>
	<body>

		<h3>list of stars:</h3>
		<p>
		<p>
		<table id = single_movie_table class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>Movie ID</th>
					<th>Movie Title</th>
					<th>Year</th>
					<th>Director</th>
					<th>List of Genres</th>
					<th>List of Stars </th>
					
				</tr>
			</thead>
			<tbody>
				
				<tr>
		
					<td><%=movieID%></td>
					<td><%=title %></td>
					<td><%=year %></td>
					<td><%=director %></td>
					<td><a
						href="/Project2/genreList?movieID=<%=movieID%>">List of Genres</a></td>
					<td><a
						href="/Project2/StarList?movieID=<%=movieID%>">List of Stars</a></td>
				</tr>
			
			</tbody>
		</table>
		<br>
		<td style="border-right: solid;"><a
				href="/Project2/search.jsp">Back to Search</a></td>
</body>
</html>



