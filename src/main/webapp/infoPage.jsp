<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
div {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}
</style>
<title>Error Page</title>
</head>
<body>
	<div>
		<%=request.getAttribute("message")%><br>
		<a href="EmployeeController?action=indexPage"><button>BACK</button></a>
	</div>
</body>
</html>