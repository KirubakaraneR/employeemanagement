<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"   
href="https://unpkg.com/purecss@1.0.0/build/pure-min.css"   
integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w"   
crossorigin="anonymous">
<title>Home Page</title>
<style>
  body {
    margin: 0;
    padding: 0;
    font-family: Calibri, Helvetica, sans-serif;
    background-color: #e6e6ff;
  }
  
  .offset {
    margin-top: 0;
    padding-top: 130px;
    text-align: center;
    transition: .3s;
    font-size: 30px;
  }
  
  .header {
    position: fixed;
    width: 100%;
    height: 70px;
    font-weight: bold;
    transition: 0.3s;
    background-color: white;
    padding: 10px;
  }
  
  h1 {
    margin-top: 15px;
    margin-left: 15px;
  }
  
  button {
    text-decoration: none;
    color: black;
    font-size: 20px;
    position: relative;
    top: -82%;
    left: 36%;
    border: none;
    background-color: white;
  }
  
  button:hover {
    color: blue;
  }
   
  a {
    text-decoration: none;
    color: blue;
    font-size: 20px;
  }
  
  a:hover {
    color: red;
  }
</style>
</head>
<body>
  <%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  
  if (session.getAttribute("mailId") == null) {
	    response.sendRedirect("index.jsp");
    }
  %>
    
  <div class="header">
    <h1>Ideas2IT Technologies</h1>
    <a href="EmployeeController?action=indexPage">
    <button>Home</button></a>&nbsp;&nbsp;&nbsp;
    <a href="EmployeeController?action=employeeMainPage">
    <button>Employee</button></a>&nbsp;&nbsp;&nbsp;
    <a href="ProjectController?action=projectMainPage">
    <button>Project</button></a>&nbsp;&nbsp;&nbsp;
    <a href="EmployeeController?action=employeeForm">
    <button>Create Employee</button></a>&nbsp;&nbsp;&nbsp;
    <a href="ProjectController?action=projectForm">
    <button>Create Project</button></a>&nbsp;&nbsp;&nbsp;
    <a href="LoginAuthentication?action=changeAdminPassword">
    <button>Change Password</button></a>&nbsp;&nbsp;&nbsp;
    <a href="LoginAuthentication">
    <button>Logout</button></a>
  </div>
  
  <div class="offset">
  
    <p><b>Our family members who are celebrating their birthday on this day</b><p>
    <center>
    <table class="pure-table pure-table-horizontal">
      
      <thead>
        <tr style="font-size: 20px;">
          <th>ID</th>
          <th>Name</th>
          <th>Mobile Number</th>
          <th>Email ID</th>
          <th>Date Of Birth</th>
          <th>Wishes</th>
        </tr>
      </thead>
        
      <tbody>
        <c:forEach items="${employeeList}" var="employee">
          <tr style="font-size: 20px;">
            <td><c:out value="${employee.id}"/></td>
            <td><c:out value="${employee.name}"/></td>
            <td><c:out value="${employee.mobileNumber}"/></td>
            <td><c:out value="${employee.mailId}"/></td>
            <td><c:out value="${employee.dateOfBirth}"/></td>
            <td>If you wanna wish <a href="">click here</a></td>
          </tr>
        </c:forEach>
      </tbody>
        
    </table>
    </center>
  </div>
</body>
</html>