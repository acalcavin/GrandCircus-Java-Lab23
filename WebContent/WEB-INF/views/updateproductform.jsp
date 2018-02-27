<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Product</title>
</head>
<body>

<h1>Update Item</h1> 
<form action="updateproduct" method="post">
<input type="hidden" name="id" value="${productID }">
Code: <input type="text" name="code" required>
Description: <input type="text" name="description">
<!-- these additional attributes allow us to take in a double variable -->
List Price <input type="number" min="1" step="any" name="listprice">
<input type="submit" value="Update Product">
</form>

</body>
</html>