<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"
	charset="UTF-8">
<title>Fabflix Movie Store</title>
<link rel="stylesheet" href="css/style.css">

</head>

<body>
	<section class="container">
		<div class="login">
			<h1>Fabflix Movie Search Page</h1>
			<form name="search_form" method="get" action="search"
				onSubmit="return validateForm(search_form)">
				<br>
				<p>
					<input type="text" name="title" value="" placeholder="Movie Title">
				</p>
				<p>
					<input type="text" name="year" value="" placeholder="Year">
				</p>
				<p>
					<input type="text" name="director" value="" placeholder="Director">
				</p>
				<p>
					<input type="text" name="star_name" value="" placeholder="Star Name">
				</p>
				<p>
					<input type="text" name="genreID" value ="" placeholder="Genre ID">
				</p>
				<p>
					Make a Selection: 
					<select name="nRows">
						<option value="10" selected="selected">10</option>
						<option value="25">25</option>
						<option value="50">50</option>
						<option value="100">100</option>
						<option value="200">200</option>
					</select>
				</p>
				<p>
    					<button class="submit" type="submit">Search</button>
     				<a href="index.html">Back To Home</a> 
     			</p>
			</form>
		</div>
	</section>

</body>
</html>