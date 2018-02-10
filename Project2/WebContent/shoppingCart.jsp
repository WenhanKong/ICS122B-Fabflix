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
ShoppingCart shoppingCart;
shoppingCart = (ShoppingCart)session.getAttribute("shoppingCart");
HashMap<String, Integer> cartItems = null;
HashMap<String, Movie> movieItems = null;
if(shoppingCart == null){
} else {
	cartItems = shoppingCart.getCartItems();
	movieItems = shoppingCart.getMovieItems();
}

//session.setAttribute("shoppingCart", shoppingCart);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
			href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		
		  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
		  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
		  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
		  <script type="text/javascript">
		        $(document).ready(function () {
		            $("#cartTable").dataTable({
	              	 "sPaginationType":"full_numbers", 
				            
		            });
		        });
		  </script>
<title>Shopping Cart</title>
</head>
<body>
	<h2>Your shopping cart: </h2>
	<table id = "cartTable" class="display" cellspacing="0" width="100%">
	    <thead>
			<tr>
				<th width = "40%">
					Movie Name
				</th>
				<th width = "30%">Quantity</th>
			</tr>
		</thead>
		<tbody>
			<% 
				if(cartItems != null && cartItems.size() > 0){
					for(String m : cartItems.keySet())
					{
						Movie movie = movieItems.get(m);
						String title = movie.getTitle();
						Integer currentQuantity = cartItems.get(m);
//						System.out.println(title);
//						System.out.println(currentQuantity);
			%>
			<tr>
				
					<td><%=title %></td>
					
					
<!--				<td>
						<button onclick="updateQuantity()"> Update </button>
					</td>
  -->	
 				<form action = "UpdateShoppingCart" metod = "post">
 					<td width = "10%">	
						<input type="text" name = "currentQuantity" value = <%=currentQuantity %>>		
						<input type="submit" value="Update"></input>				
					</td>					
					<td>
						<input type = "hidden" name = "movieID" value =<%=m%>>
					</td>
				</form>
      			

			</tr>
			<%
			  		}
				} else {
			%>
			<h3>Your shopping cart is empty</h3>
			<%
				}
			%>
		</tbody>
		</table>
		<br>
		<td style="border-right: solid;"><a
				href="/Project2/search.jsp">Back to Search</a></td>
		
		<button style="float:right" onclick="goBack()">Continue Shopping</button>
		<br>
		<br>
		<button style="float:right" onclick="window.location.href='checkout.jsp'">Checkout</button>
		<script>
			function goBack() {
    				window.history.back();
			}
		</script>
		<script>
			function updateQuantity(){
				
				var quantity = document.getElementById("quantity").value;
				shoppingCart.changeQuantity(title, quantity);
				shoppingCart = (ShoppingCart)session.getAttribute("shoppingCart");
				$("cartTable").submit();
			}
		</script>
</body>
</html>