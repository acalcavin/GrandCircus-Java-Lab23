<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Item</title>
</head>
<body>

<h1>Add a new Item</h1>
<br>
<form action="addproduct" method="post">
Name: <input type="text" name="name" required><br>
Description: <input type="text" name="description"><br>
Quantity: <input type="text" name="quantity"><br>
<!-- these additional attributes allow us to take in a double variable -->
Price <input type="number" min="1" step="any" name="price"><br>
<br>
<input type="submit" value="Save">

</form>

</body>
</html>