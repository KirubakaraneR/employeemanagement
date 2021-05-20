<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ideas2it.project.projectmanagement.model.Project"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css"   
integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w"   
crossorigin="anonymous">
<title>Address Form</title>
<style>
  body {
    margin: 0;
    padding: 0;
    font-family: Calibri, Helvetica, sans-serif;
    background-color: #e6e6ff;
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
    color: black;
    font-size: 25px;
  }
  
  a:hover {
    color: blue;
  }
  
  #options {
    text-decoration: none;
    color: black;
    font-size: 20px;
    position: relative;
    top: -76%;
    left: 42%;
    border: none;
    background-color: white;
  }
  
  input[type=submit] {
    background-color: lightblue;
    width: 100%;
    color: black;
    padding: 15px;
    margin: 10px 0px;
    border: none;
    cursor: pointer;
  }
  
  input[type=text], input[type=date], input[type=tel], input[type=email] {   
    width: 100%;   
    margin: 8px 0;  
    padding: 5px 20px;   
    display: inline-block;   
    border: 2px solid green;   
    box-sizing: border-box;   
  }
  
  #left-box {
    position: fixed;
    top: 23%;
    left:33%;
  }
  
  #right-box {
    position: fixed;
    top: 23%;
    left:53%;
  }
</style>
</head>
<body>
  <%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  if (session.getAttribute("mailId") == null) {
	    response.sendRedirect("index.jsp");
    }%>
    
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
    <a href="EmployeeController?action=changeAdminPassword">
    <button>Change Password</button></a>&nbsp;&nbsp;&nbsp;
    <a href="LoginAuthentication">
    <button>Logout</button></a>
  </div>
    
  <div class="container">
    <c:if test="${address != null}">
      <form action="EmployeeController?action=updateAddress" method="post">
    </c:if>
    <c:if test="${address == null}">
      <form action="EmployeeController?action=createAddress" method="post">
    </c:if>
        
    <div id="left-box">
      <label>Door Number:</label><br>
      <input type="text" name="doorNumber" 
          placeholder="Enter the Door Number"
          value="<c:out value='${address.get(0).doorNumber}'/>"><br>
      <label>Street Name:</label><br>
      <input type="text" name="street" 
          placeholder="Enter the Street Name" pattern="[A-Za-z ]*"
          value="<c:out value='${address.get(0).street}'/>"><br>
      <label>Nagar/Area:</label><br>
      <input type="text" name="nagar" 
          placeholder="Enter the Nagar/Area" pattern="[A-Za-z ]*"
          value="<c:out value='${address.get(0).nagar}'/>"><br>
      <label>City:</label><br>
      <input type="text" name="city" 
          placeholder="Enter the City" pattern="[A-Za-z ]*"
          value="<c:out value='${address.get(0).city}'/>"><br>
      <label>District:</label><br>
      <input type="text" name="district" 
          placeholder="Enter the District" pattern="[A-Za-z ]*"
          value="<c:out value='${address.get(0).district}'/>"><br>
    </div>
    
    <div id="right-box">
      <label>State:</label><br>
      <input type="text" name="state" 
          placeholder="Enter the State" pattern="[A-Za-z ]*"
          value="<c:out value='${address.get(0).state}'/>"><br>
      <label>Pincode:</label><br>
      <input type="text" name="pincode" 
          placeholder="Enter the Pincode" pattern="[0-9]{6}"
          value="<c:out value='${address.get(0).pinCode}'/>"><br>
      <label>Country:</label><br>
      <input type="text" name="country" 
          placeholder="Enter the Country" pattern="[A-Za-z ]*"
          value="<c:out value='${address.get(0).country}'/>"><br>
      <label>Address Type:</label><br><br>
      <input type="radio" name="addressType" 
          value="Permanent" checked>&nbsp;Permanent&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="radio" name="addressType" 
          value="Temporary">&nbsp;Temporary<br><br>
      <input type="hidden" name="addressId" value="<c:out value="${address.get(0).id}"/>">
      <input type="hidden" name="employeeId" value="<%=request.getParameter("employeeId")%>">
      <input type="submit" value="Submit">
    </div>
    
    </form>
  </div>
  
</body>
</html>