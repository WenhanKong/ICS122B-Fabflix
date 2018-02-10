<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Checkout</title>
<style>
	form {
	  text-align: center;
	}
</style>
</head>
<body>
	<h3>Please enter your purchase information: </h2>
	<form id="creditCardForm" method="post" action="CheckoutServlet">
		<br>
	    <label><b>Firstname:</b></label>
	    <input type="text" placeholder="Enter Firstname" name="firstName" required/>
		<br>
		<br>
	    <label><b>LastName</b></label>
	    <input type="text" placeholder="Enter Lastname" name="lastName" required/>
		<br>
		<br>
		<label><b>Credit Card Number</b></label>
	    <input type="text" placeholder="Enter card number" name="cardNumber" required/>
		<br>
		<br>
		<label><b>Expiration Date</b></label>
	    <input type="text" placeholder="Enter expiration date XXXX-XX-XX" name="date" required/>
		<br>
		<br>
	    <input style = "float:middle" type="submit" value="Confirm Purchase">
	</form>
</body>
</html>