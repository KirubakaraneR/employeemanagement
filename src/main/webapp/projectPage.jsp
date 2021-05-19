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
<title>Project Page</title>
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
</style>
</head>
<body>

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
    <a href="EmployeeController?action=logout">
    <button>Logout</button></a>
  </div>
    
  <div class="offset">
    <center>
    <table class="pure-table pure-table-horizontal">
      <thead>
        <tr style="font-size: 20px;">
          <th>ID</th>
          <th>Name</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Status</th>
          <th colspan="4"><center>Options</center></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${projectList}" var="project">
          <tr style="font-size: 20px;">
            <td><c:out value="${project.id}"/></td>
            <td><c:out value="${project.name}"/></td>
            <td><fmt:formatDate pattern="dd MMM,yyyy" 
                value="${project.startDate}"/></td>
            <td><fmt:formatDate pattern="dd MMM,yyyy" 
                value="${project.endDate}"/></td>
            <td><c:out value="${project.status}"/></td>
            <td><a style="font-size: 20px; color: #ff0000;" 
                href="ProjectController?action=editProject&id=<c:out 
                value="${project.id}"/>"><b>EDIT</b></a></td>
            <td><a style="font-size: 20px; color: #000099;" 
                href="ProjectController?action=deleteProject&id=<c:out 
                value="${project.id}"/>"><b>DELETE</b></a></td>
            <td><a style="font-size: 20px; color: #e600e6;" 
                href="ProjectController?action=assignEmployee&id=<c:out 
                value="${project.id}"/>"><b>ASSIGN</b></a></td>
            <td><a style="font-size: 20px; color: #595959;" 
                href="ProjectController?action=unAssignEmployee&id=<c:out 
                value="${project.id}"/>"><b>UNASSIGN</b></a></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    </center>
    <center>
    <a style="font-size: 20px; color: #00e600;" 
        href="ProjectController?action=projectForm"><b>RESTORE</b></a>
    <center>
  </div>

</body>
</html>