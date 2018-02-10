<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="Fabflix.*"%>
<%@ page language="java"
	import="java.sql.*, 
	java.util.*,
	java.io.IOException,
	javax.servlet.http.*,
	javax.servlet.*"
%>
<% 
//HttpSession session = request.getSession();

//String movieID = (String)session.getAttribute("movieID");
String purchaseResult = (String)session.getAttribute("purchaseResult");


//session.setAttribute("shoppingCart", shoppingCart);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Puchase Result</title>
</head>
<body>
	<h2>Your purchase is a <%=purchaseResult %> !</h2>
	<br>
		<td style="border-right: solid;"><a
				href="/Project2/search.jsp">Back to Search</a></td>
		
		<td style="border-right: solid;"><a
				href="/Project2/index.html">Back to Home</a></td>
</body>
</html>