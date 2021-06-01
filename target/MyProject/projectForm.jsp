<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ideas2it.project.projectmanagement.model.Project"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"   
href="https://unpkg.com/purecss@1.0.0/build/pure-min.css"   
integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w"   
crossorigin="anonymous">
<title>Project Form</title>
<style>
  body {
    margin: 0;
    padding: 0;
    font-family: Calibri, Helvetica, sans-serif;
    background-color: #e6e6ff;
  }
  
  .container {
    position: fixed;
    top: 57%;
    left: 35%;
    transform: translate(-50%,-50%);
  }
  
  #restore {
    position: fixed;
    top: 57%;
    left: 65%;
    transform: translate(-50%,-50%);
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
  
  input[type=text], input[type=date] {   
    width: 100%;   
    margin: 8px 0;  
    padding: 12px 20px;   
    display: inline-block;   
    border: 2px solid green;   
    box-sizing: border-box;   
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
    
  <div class="container">
    <c:if test="${individualProject != null}">
      <form action="ProjectController?action=updateProject" method="post">
    </c:if>
    <c:if test="${individualProject == null}">
      <form action="ProjectController?action=createProject" method="post">
    </c:if>
                
    <c:if test="${individualProject != null}">
      <input type="hidden" name="id" value="<c:out value='${individualProject.id}' />" />
    </c:if>
    <label style="font-size: 20px"><center><b>CREATE PROJECT</b></center></label> <br> 
    <label>Name:</label><br>
    <input type="text" name="name" placeholder="Enter the Name"
        value="<c:out value='${individualProject.name}'/>" required><br>
    <label>Start Date:</label><br>
    <input type="date" name="startDate" 
        value="<c:out value='${individualProject.startDate}'/>" required><br>
    <label>End Date:</label><br>
    <input type="date" name="endDate" 
        value="<c:out value='${individualProject.endDate}'/>" required><br>
    <label>Status:</label><br>
    <input type="radio" name="status" value="Started">&nbsp;Started&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="radio" name="status" value="Not Started">&nbsp;Not Started
    <input type="submit" value="Submit">
    </form>
  </div>
  
  <div id="restore">
    <label style="font-size: 20px"><center><b>RESTORE PROJECT</b></center></label> <br>
    <form action="ProjectController" method="get">
      <label>ID:</label><br>
      <input type="text" name="id" placeholder="Enter the ID" required><br>
      <input type="hidden" name="action" value="restoreProject">
      <input type="submit" value="Restore">
    </form>
  </div>
</body>
</html>