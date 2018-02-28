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
	ItemAdmin
${specificItem }

<br>
<form action="searchbyproduct" method="get">
	<input type="text" name="product">
	<input type="submit" value="Search By Product">
</form>

<h1>Add Item</h1> 
<form action="addproduct" method="post">
Name: <input type="text" name="name" required>
Description: <input type="text" name="description">
Quantity: <input type="text" name="quantity">

<!-- these additional attributes allow us to take in a double variable -->
List Price <input type="number" min="1" step="any" name="price">
<input type="submit" value="Add Product">
</form>


	<table border="1">
		<c:forEach var="myVar" items="${pList}">
		<tr>
			<td>${myVar.name}</td>
			<td>${myVar.description}</td>
			<td>${myVar.quantity}</td>
			<td>${myVar.price}</td>
			<td><a href="delete?id=${myVar.name}">Delete</a>
			<td><a href="update?id=${myVar.name}">Update</a>
		
		</tr>		
		</c:forEach>
	</table>
	
	<!-- add href link to Add a new item -->
</body>
</html>