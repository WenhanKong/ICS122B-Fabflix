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

List<Star> stars = (List<Star>)session.getAttribute("star_list");
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
		            $("#starTable").dataTable({
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
		<table id = starTable class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>star ID</th>
					<th>star Name</th>
			
				</tr>
			</thead>
			<tbody>
				<% 
					for (Star star:stars) {
				%>
				<tr>
		
					<td style="border-right: solid;"><%=star.getID() %></td>
					<td><a href ="/Project2/singleStarPage?starID=<%=star.getID()%>&star_name=<%=star.getName()%>&birthYear=<%=star.getBirthYear()%>"><%=star.getName() %></a></td>
		
				</tr>
			<%
				  }
				
			%>
			</tbody>
		</table>
		<br>
		<td style="border-right: solid;"><a
				href="/Project2/search.jsp">Back to Search</a></td>
</body>
</html>









