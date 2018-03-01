<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ItemAdmin</title>
</head>
<body>
	Item Admin


<br>
<form action="searchbyproduct" method="get">
	<input type="text" name="product">
	<input type="submit" value="Search By Product">
</form>

	<table border="1">
	<tr>
	<td>Name</td>
	<td>Description</td>
	<td>Quantity</td>
	<td>Price</td>
		<c:forEach var="myVar" items="${pList}">
		<tr>
			<td>${myVar.name}</td>
			<td>${myVar.description}</td>
			<td>${myVar.quantity}</td>
			<td>${myVar.price}</td>
			<td><a href="update?id=${myVar.name}">Edit</a>
			<td><a href="delete?id=${myVar.name}">Delete</a>
		
		</tr>		
		</c:forEach>
	</table>
	<br><br>
	<!-- add href link to Add a new item -->
	<h3>
	<a href="additem.html">Add a New Item</a>
	</h3>
	<script src="script.js"></script>
</body>
</html>