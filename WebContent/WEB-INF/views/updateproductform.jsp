<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Item</title>
</head>
<body>

<h1>Edit an Item</h1> 
<br>
<form action="updateproduct" method="post">
Name: <input type="text" name="name" value="${name}" required><br>
Description: <input type="text" name="description" value="${description}"><br>
Quantity: <input type="text" name="quantity"><br>
<!-- these additional attributes allow us to take in a double variable -->
Price <input type="number" min="1" step="any" name="price"><br>
<br>
<input type="submit" value="Save">
</form>

</body>
</html>